package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.DataAccessService;
import com.ylife.chinapost.service.InventoryManageService;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.mapper.EnterpriseMapper;
import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.mapper.InventoryMapper;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.model.BillStatus;
import com.ylife.inventory.model.BillType;
import com.ylife.inventory.model.InventoryBill;
import com.ylife.inventory.model.InventoryKey;
import com.ylife.inventory.service.InventoryBillHistoryService;
import com.ylife.inventory.service.InventoryBillLogService;
import com.ylife.inventory.service.InventoryBillService;
import com.ylife.inventory.service.InventoryService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by XuKai on 2016/4/21.
 * InventoryManageServiceImpl
 */
@Service
public class InventoryManageServiceImpl implements InventoryManageService {

    @Resource
    private InventoryService inventoryService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private InventoryMapper inventoryMapper;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private InventoryBillService inventoryBillService;
    @Resource
    private InventoryBillHistoryService inventoryBillHistoryService;
    @Resource
    private EnterpriseMapper enterpriseMapper;
    @Resource
    private DataAccessService dataAccessService;
    @Resource
    private InventoryBillLogService inventoryBillLogService;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;


    @Override
    public Page<InventoryGoodsResult> getInventoryGoodsResult(String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        List<InventoryGoodsResult> inventoryGoodsResults = inventoryMapper.selectInventoryGoods(enterpriseId, goodsName, goodsNumber, brandId, thirdId, pageable);
        int totalElements = inventoryMapper.countByEnterpriseId(enterpriseId, goodsName, goodsNumber, brandId, thirdId);
        return new PageImpl<>(pageable, totalElements, inventoryGoodsResults);
    }

    @Override
    public Page<InventoryGoodsResult> getInventoryGoodsResultBYEnterpriseId(Long enterpriseId, String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        List<InventoryGoodsResult> inventoryGoodsResults = inventoryMapper.selectInventoryGoods(enterpriseId, goodsName, goodsNumber, brandId, thirdId, pageable);
        int totalElements = inventoryMapper.countByEnterpriseId(enterpriseId, goodsName, goodsNumber, brandId, thirdId);
        return new PageImpl<>(pageable, totalElements, inventoryGoodsResults);
    }

    @Override
    public InventoryGoodsResult getInventoryGoodsResultByPrimarykey(long goodsInfoId) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        InventoryKey key = new InventoryKey(enterpriseId, goodsInfoId);
        return inventoryMapper.selectInventoryGoodsResultByPrimarykey(key);
    }

    @Override
    public Map<String, Object> sonEnterprise() {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return getMap(enterpriseId);
    }

    public Map<String, Object> getMap(long enterpriseId) {
        Enterprise info = enterpriseService.getEnterprise(enterpriseId);
        EnterpriseFunction function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Map<String, Object> map = new HashMap<>();
        map.put("id", info.getId());
        map.put("name", info.getEnterpriseName());
        map.put("end", function.getEnd());
        List<Enterprise> sons = enterpriseMapper.selectByParentId(enterpriseId);
        List<Map> sonsMaps = new ArrayList<>();
        for (Enterprise enterprise : sons) {
            sonsMaps.add(getMap(enterprise.getId()));
        }
        map.put("sons", sonsMaps);
        return map;
    }

    @Override
    public Page<InventoryGoodsResult> getGoods(String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        List<InventoryGoodsResult> inventoryGoodsResults = inventoryMapper.selectGoods(goodsName, goodsNumber, brandId, thirdId, pageable);
        int totalElements = inventoryMapper.countGoodsInfoId(goodsName, goodsNumber, brandId, thirdId);
        return new PageImpl<>(pageable, totalElements, inventoryGoodsResults);
    }

    @Override
    public void addInventory(long goodsInfoId, int inventory) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        InventoryKey key = new InventoryKey(enterpriseId, goodsInfoId);
        inventoryService.addActuallyInventory(key, inventory);
    }

    @Override
    public void editInventory(long goodsInfoId, int inventory) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        inventoryService.editInventory(enterpriseId, goodsInfoId, inventory);
    }


    @Override
    public void editAvailable(long goodsInfoId, int available) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        inventoryService.setAvailable(enterpriseId, goodsInfoId, available);
    }

    @Override
    public void deleteGoods(long goodsInfoId) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        inventoryService.deleteGood(enterpriseId, goodsInfoId);
    }

    @Override
    public void addGoods(Map<Long, Integer> goods) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        inventoryService.setGoods(enterpriseId, goods);
    }

    @Override
    public void setWarning(int warning) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        enterpriseService.setInventoryForewarn(enterpriseId, warning);
    }

    @Override
    public int getWarning() {
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        return function.getInventoryForewarn() == null ? 0 : function.getInventoryForewarn();
    }

    @Override
    public Page<InventoryGoodsResult> getWarningGoods(String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        if (inventoryService.getWarning(enterpriseId) < 0) {
            return null;
        }
        List<InventoryGoodsResult> warningGoodsResults = inventoryMapper.getWarningGoods(enterpriseId, goodsName, goodsNumber, brandId, thirdId, pageable);
        int totalElements = inventoryMapper.countWarningGoodsByEnterpriseId(enterpriseId, goodsName, goodsNumber, brandId, thirdId);
        return new PageImpl<>(pageable, totalElements, warningGoodsResults);
    }

    @Override
    public InventoryGoodsResult getWarningGood(long goodsInfoId) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        InventoryKey key = new InventoryKey(enterpriseId, goodsInfoId);
        return inventoryMapper.selectWarningGood(key);
    }

    @Override
    public Page<InventoryGoodsResult> getGoodsAndInventory(Long enterpriseId, Long goodsInfoId, String goodsInfoName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        return inventoryService.getGoodsInventory(enterpriseId, goodsInfoId, goodsInfoName, goodsNumber, brandId, thirdId, pageable);
    }

    @Override
    public Page<List<InventoryGoodsResult>> queryByInventory(Long enterpriseId, String goodsInfoName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        Page<InventoryGoodsResult> resultPage = inventoryService.queryByInventory(enterpriseId, goodsInfoName, goodsNumber, brandId, thirdId, pageable);
        List<List<InventoryGoodsResult>> list = new ArrayList<>();
        long eId = -1L;
        List<InventoryGoodsResult> sonList = new ArrayList<>();
        for (InventoryGoodsResult result : resultPage.getContent()) {
            if (result.getEnterpriseId() == eId) {
                sonList.add(result);
            } else {
                if (sonList.size() != 0) {
                    list.add(sonList);
                }
                eId = result.getEnterpriseId();
                sonList = new ArrayList<>();
                sonList.add(result);
            }
        }
        list.add(sonList);
        return new PageImpl<>(pageable, resultPage.getTotalElements(), list);
    }

    @Override
    public Page<InventoryGoodsResult> queryByGoodsInfo(Long enterpriseId, String goodsInfoName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable) {
        return inventoryService.queryByGoodsInfo(enterpriseId, goodsInfoName, goodsNumber, brandId, thirdId, pageable);
    }

    /*****************************************************************************************************************************/
    /*****************************************************请货，调拨，报损、报溢*****************************************************/
    /*****************************************************************************************************************************/

    /***/

    @Override
    public InventoryBill getBillById(long billId) {
        return inventoryBillService.getBillById(billId);
    }

    @Override
    public InventoryBill getBillByCode(long code) {
        return inventoryBillService.getBillByCode(code);
    }

    @Override
    public Page<InventoryBill> getCreatedBills(Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                               BillType billType, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return inventoryBillService.getCreatedBills(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
    }

    @Override
    public Page<InventoryBill> getHandledBills(Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                               BillType billType, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return inventoryBillService.getHandledBills(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
    }

    @Override
    public Page<InventoryBill> getUnHandleBills(Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                                BillType billType, Pageable pageable) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        return inventoryBillService.getUnHandleBills(enterpriseId, code, creatorName, start, end, billStatus, billType, pageable);
    }

    @Override
    @Transactional
    public void createBill(BillType type, Long outId, Long inId, String reason, Map<Long, Integer> sku) {
        Assert.notNull(type);
        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        long enterpriseId = function.getId();
        Long currentId = null;
        Long parentId = function.getParentId();
        switch (type) {
            case REPLENISHMENT:
            case MORE_REPORT:
                Assert.notNull(parentId, "当前账号无法创建此类型单据。");
                currentId = parentId;
                break;
            case LESS_REPORT:
                Assert.notNull(parentId, "当前账号无法创建此类型单据。");
                currentId = parentId;
                for (Long goodsInfoId : sku.keySet()) {
                    InventoryGoodsResult inventoryGoodsResult = inventoryMapper.selectInventoryGoodsResultByPrimarykey(new InventoryKey(enterpriseId, goodsInfoId));
                    if (inventoryGoodsResult == null) {
                        throw new UserOperationException("有货品库存为0，无需报损！");
                    }
                    if (inventoryGoodsResult.getAvailable() < sku.get(goodsInfoId)) {
                        throw new UserOperationException("报损数量不能大于该商品库存量");
                    }
                }
                break;
            case INVENTORY_TRANSFER:
                if (parentId == null) {
                    outId = function.getId();
                }
                for (Long goodsInfoId : sku.keySet()) {
                    InventoryGoodsResult inventoryGoodsResult = inventoryMapper.selectInventoryGoodsResultByPrimarykey(new InventoryKey(outId, goodsInfoId));
                    if (inventoryGoodsResult.getAvailable() < sku.get(goodsInfoId)) {
                        throw new UserOperationException("调拨数量不能大于该商品的库存量！！");
                    }
                }
        }
        long billId = inventoryBillService.createBill(function.getId(), type, currentId, outId, inId, reason, sku);
        inventoryBillLogService.addBillLog(enterpriseId, billId, null, "[创建]");
    }

    @Override
    @Transactional
    public void approveBill(long billId, String comment, Map<Long, Integer> amounts) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.approveBill(billId, enterpriseService.getTopEnterprise().getId(), amounts);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[批准]");
    }

    @Override
    @Transactional
    public void agreeBill(long billId, String comment, Map<Long, Integer> sku) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.agreeBill(billId, currentLoginService.isPrimaryEnterprise(), sku);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[同意]");
    }

    @Override
    @Transactional
    public void terminateBill(long billId, String comment) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.terminateBill(billId);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[终止]");
    }

    @Override
    @Transactional
    public void finishBill(long billId) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.finishBill(billId);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, null, "[完成]");
    }

    @Override
    @Transactional
    public void backBill(long billId, String comment) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.backBill(billId);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[退回]");
    }

    @Override
    @Transactional
    public void submitBill(long billId, String comment, Map<Long, Integer> sku) {
        EnterpriseFunction enterpriseFunction = currentLoginService.getCurrentLoginEnterpriseFunc();
        long enterpriseId = enterpriseFunction.getId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.submitBill(billId, enterpriseFunction.getParentId(), sku);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[提交]");
    }

    @Override
    @Transactional
    public void deliveryBill(long billId, String comment, Map<Long, Integer> sku) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.deliveryBill(billId, sku);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[发货]");
    }

    @Override
    @Transactional
    public void receiptBill(long billId, String comment) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        dataAccessService.enterpriseAcessBill(enterpriseId, billId);
        inventoryBillService.receiptBill(billId);
        inventoryBillHistoryService.operate(enterpriseId, billId);
        inventoryBillLogService.addBillLog(enterpriseId, billId, comment, "[收货]");
    }
}




