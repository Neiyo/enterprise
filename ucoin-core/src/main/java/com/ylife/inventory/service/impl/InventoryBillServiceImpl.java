package com.ylife.inventory.service.impl;

import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.mapper.InventoryBillItemMapper;
import com.ylife.inventory.mapper.InventoryBillMapper;
import com.ylife.inventory.model.*;
import com.ylife.inventory.service.InventoryBillService;
import com.ylife.inventory.service.InventoryService;
import com.ylife.product.model.GoodsInfo;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.utils.Assert;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/6/14.
 * InventoryBillServiceImpl
 */
@Service
public class InventoryBillServiceImpl implements InventoryBillService {

    @Resource
    private InventoryBillMapper inventoryBillMapper;
    @Resource
    private InventoryBillItemMapper inventoryBillItemMapper;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private GoodsInfoService goodsInfoService;

    private Generator generator = IdGeneratorFactory.create("INVENTORY_BILL");

    @Override
    public InventoryBill getBillById(long id) {
        return inventoryBillMapper.selectBillDetailByPrimaryKey(id);
    }

    @Override
    public InventoryBill getBillByCode(long code) {
        InventoryBill bill = inventoryBillMapper.selectByCode(code);
        Assert.notNull(bill, "此单据不存在。");
        return inventoryBillMapper.selectBillDetailByPrimaryKey(bill.getBillId());
    }

    @Override
    public Page<InventoryBill> getCreatedBills(long enterpriseId, Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                               BillType billType, Pageable pageable) {
        List<InventoryBill> bills = inventoryBillMapper.selectByCreatorId(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
        int totalElements = inventoryBillMapper.countByCreatorId(enterpriseId, code, creatorName, start, end, billStatus, billType);
        return new PageImpl<>(pageable, totalElements, bills);
    }

    @Override
    public Page<InventoryBill> getHandledBills(long enterpriseId, Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                               BillType billType, Pageable pageable) {
        List<InventoryBill> handleBills = inventoryBillMapper.selectHandleHistory(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
        int totalElements = inventoryBillMapper.countHandleHistory(enterpriseId, code, creatorName, start, end, billStatus,
                billType);
        return new PageImpl<>(pageable, totalElements, handleBills);
    }

    @Override
    public Page<InventoryBill> getUnHandleBills(long enterpriseId, Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                                BillType billType, Pageable pageable) {
        List<InventoryBill> bills = inventoryBillMapper.selectByCurrentId(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
        int totalElements = inventoryBillMapper.countByCurrentId(enterpriseId, code, creatorName, start, end, billStatus, billType);
        return new PageImpl<>(pageable, totalElements, bills);
    }

    @Override
    @Transactional
    public long createBill(long creatorId, BillType type, Long nextOperatorId, Long outId, Long inId, String reason, Map<Long, Integer> sku) {
        Assert.notNull(type);
        Assert.notEmpty(sku);
        if (StringUtil.isBlank(reason)) {
            throw new UserOperationException("调拨原因没填写！");
        }
        InventoryBill bill = new InventoryBill();
        BillStatus status = null;
        switch (type) {
            case INVENTORY_TRANSFER:
                Assert.notNull(outId, "调出仓库没有填写！");
                Assert.notNull(inId, "调入仓没有填写！");
                status = BillStatus.WAIT_DELIVERY;
                bill.setCurrentId(outId);
                break;
            case REPLENISHMENT:
            case LESS_REPORT:
            case MORE_REPORT:
                Assert.notNull(nextOperatorId);
                outId = inId = null;
                status = BillStatus.CHECKING;
                bill.setCurrentId(nextOperatorId);
                break;
        }
        bill.setBillStatus(status);
        bill.setBillType(type);
        bill.setCode(generator.generate());
        bill.setCreateTime(new Date());
        bill.setCreatorId(creatorId);
        bill.setInId(inId);
        bill.setOutId(outId);
        bill.setReason(reason);
        inventoryBillMapper.insertSelective(bill);
        addItems(bill.getBillId(), sku);
        return bill.getBillId();

    }

    @Override
    @Transactional
    public void approveBill(long id, Long nextOperatorId, Map<Long, Integer> amounts) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        if (bill == null || bill.getBillType() != BillType.REPLENISHMENT) {
            throw new IllegalArgumentException("此单据无法进行批准操作。");
        }
        InventoryBill model = new InventoryBill();
        model.setBillId(bill.getBillId());
        model.setCurrentId(nextOperatorId);
        inventoryBillMapper.updateByPrimaryKeySelective(model);
        updateAmount(bill.getBillId(), amounts);
    }

    @Override
    @Transactional
    public void agreeBill(long id, Boolean isTop, Map<Long, Integer> sku) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        BillType type;
        if (bill == null || (type = bill.getBillType()) == BillType.INVENTORY_TRANSFER) {
            throw new IllegalArgumentException("此单据无法进行同意操作。");
        }
        BillStatus status = null;
        Long currentId = null;
        switch (type) {
            case REPLENISHMENT:
                Assert.notNull(isTop);
                if (!isTop) {
                    updateAmount(bill.getBillId(), sku);
                }
                status = BillStatus.CHECKED;
                currentId = bill.getCurrentId();
                break;
            case LESS_REPORT:
                //操作库存
                List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(id);
                addAvailableInventory(bill.getCreatorId(), items, true);
                addActuallyInventory(bill.getCreatorId(), items, true);
                status = BillStatus.FINISHED;
                currentId = null;
                break;
            case MORE_REPORT:
                //操作库存
                items = inventoryBillItemMapper.selectByBillId(id);
                addAvailableInventory(bill.getCreatorId(), items, false);
                addActuallyInventory(bill.getCreatorId(), items, false);
                status = BillStatus.FINISHED;
                currentId = null;
                break;
        }
        bill.setCurrentId(currentId);
        bill.setBillStatus(status);
        inventoryBillMapper.updateByPrimaryKey(bill);
    }

    @Override
    @Transactional
    public void terminateBill(long id) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        if (bill == null) {
            throw new IllegalArgumentException("此单据无法进行终止操作。");
        }
        bill.setCurrentId(null);
        bill.setBillStatus(BillStatus.TERMINATED);
        inventoryBillMapper.updateByPrimaryKey(bill);
    }

    @Override
    @Transactional
    public void finishBill(long id) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        if (bill == null || bill.getBillType() != BillType.REPLENISHMENT) {
            throw new IllegalArgumentException("此单据无法进行完成操作。");
        }
        bill.setBillStatus(BillStatus.FINISHED);
        bill.setCurrentId(null);
        inventoryBillMapper.updateByPrimaryKey(bill);
    }

    @Override
    @Transactional
    public void backBill(long id) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        BillType type;
        if (bill == null || (type = bill.getBillType()) == BillType.REPLENISHMENT) {
            throw new IllegalArgumentException("此单据无法进行退回操作。");
        }
        BillStatus status = BillStatus.WAIT_EDIT;
        InventoryBill model = new InventoryBill();
        model.setBillId(bill.getBillId());
        model.setBillStatus(status);
        switch (type) {
            case LESS_REPORT:
            case MORE_REPORT:
                model.setCurrentId(bill.getCreatorId());
                break;
            case INVENTORY_TRANSFER:
                model.setCurrentId(bill.getOutId());
                //操作库存
                List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(id);
                addAvailableInventory(bill.getOutId(), items, false);
                break;
        }
        inventoryBillMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    @Transactional
    public void submitBill(long id, Long nextOperatorId, Map<Long, Integer> sku) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        if (bill == null || (bill.getBillType() != BillType.LESS_REPORT && bill.getBillType() != BillType.MORE_REPORT)) {
            throw new IllegalArgumentException("此单据无法进行提交操作。");
        }
        inventoryBillItemMapper.deleteByBillId(id);
        addItems(id, sku);
        //操作报损报溢单状态
        BillStatus status = BillStatus.CHECKING;
        InventoryBill model = new InventoryBill();
        model.setBillId(bill.getBillId());
        model.setBillStatus(status);
        model.setCurrentId(nextOperatorId);
        inventoryBillMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    @Transactional
    public void deliveryBill(long id, Map<Long, Integer> sku) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        if (bill == null || bill.getBillType() != BillType.INVENTORY_TRANSFER) {
            throw new IllegalArgumentException("此单据无法进行发货操作。");
        }
        if (bill.getBillStatus() == BillStatus.WAIT_EDIT) {
            Assert.notEmpty(sku);
            inventoryBillItemMapper.deleteByBillId(bill.getBillId());
            addItems(bill.getBillId(), sku);
        }
        //操作库存
        List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(id);
        addAvailableInventory(bill.getOutId(), items, true);
        InventoryBill model = new InventoryBill();
        model.setBillId(bill.getBillId());
        model.setBillStatus(BillStatus.WAIT_RECEIVER);
        model.setCurrentId(bill.getInId());
        inventoryBillMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    @Transactional
    public void receiptBill(long id) {
        InventoryBill bill = inventoryBillMapper.selectByPrimaryKeyForUpdate(id);
        if (bill == null || bill.getBillType() != BillType.INVENTORY_TRANSFER) {
            throw new IllegalArgumentException("此单据无法进行收货操作。");
        }
        //操作库存
        List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(id);
        addActuallyInventory(bill.getOutId(), items, true);
        addActuallyInventory(bill.getInId(), items, false);
        addAvailableInventory(bill.getInId(), items, false);
        bill.setBillStatus(BillStatus.FINISHED);
        bill.setCurrentId(null);
        inventoryBillMapper.updateByPrimaryKey(bill);
    }

    private void updateAmount(long billId, Map<Long, Integer> sku) {
        List<InventoryBillItem> items = inventoryBillItemMapper.selectByBillId(billId);
        for (InventoryBillItem item : items) {
            InventoryBillItem itemModel = new InventoryBillItem();
            itemModel.setItemId(item.getItemId());
            Integer checkAmount = sku.get(item.getItemId());
            Assert.isTrue(checkAmount != null && checkAmount > 0, "参数中某个项目不存在或者数量小于等于0。");
            itemModel.setCheckedAmount(checkAmount);
            inventoryBillItemMapper.updateByPrimaryKeySelective(itemModel);
        }
    }

    private void addItems(long billId, Map<Long, Integer> sku) {
        for (Long infoId : sku.keySet()) {
            InventoryBillItem item = new InventoryBillItem();
            item.setBillId(billId);
            item.setGoodsInfoId(infoId);
            Integer amount = sku.get(infoId);
            GoodsInfo goodsInfo=goodsInfoService.getById(infoId);
            Assert.isTrue(amount != null && amount > 0, "参数中商品名称为：" + goodsInfo.getGoodsInfoName() + "的商品数量不存在。");
            item.setAmount(sku.get(infoId));
            inventoryBillItemMapper.insertSelective(item);
        }
    }

    private void addActuallyInventory(long enterpriseId, List<InventoryBillItem> items, boolean out) {
        for (InventoryBillItem item : items) {
            if (out) {
                inventoryService.addActuallyInventory(new InventoryKey(enterpriseId, item.getGoodsInfoId()), -item.getAmount());
            } else {
                inventoryService.addActuallyInventory(new InventoryKey(enterpriseId, item.getGoodsInfoId()), item.getAmount());
            }
        }

    }

    private void addAvailableInventory(long enterpriseId, List<InventoryBillItem> items, boolean out) {
        for (InventoryBillItem item : items) {
            if (out) {
                inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, item.getGoodsInfoId()), -item.getAmount());
            } else {
                inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, item.getGoodsInfoId()), item.getAmount());
            }
        }
    }


}
