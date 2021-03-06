package com.ylife.inventory.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.model.BillStatus;
import com.ylife.inventory.model.BillType;
import com.ylife.inventory.model.InventoryBill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.Map;

/**
 * Created by InThEnd on 2016/6/14.
 * 库存事务单据服务
 */
public interface InventoryBillService {

    /**
     * 根据ID获取单据。
     *
     * @param id ID
     */
    InventoryBill getBillById(long id);


    /**
     * 根据code获取单据。
     *
     * @param code 单据编号
     */
    InventoryBill getBillByCode(long code);

    /**
     * 获取创建的单据。
     *
     * @param enterpriseId 企业ID
     */
    Page<InventoryBill> getCreatedBills(long enterpriseId, Long code, String creatorName, Date start, Date end,BillStatus billStatus,
                                        BillType billType, Pageable pageable);

    /**
     * 获取已处理的单据。
     *
     * @param enterpriseId 企业ID
     */
    Page<InventoryBill> getHandledBills(long enterpriseId, Long code, String creatorName, Date start, Date end,BillStatus billStatus,
                                        BillType billType, Pageable pageable);

    /**
     * 获取待处理单据。
     *
     * @param enterpriseId 企业ID
     */
    Page<InventoryBill> getUnHandleBills(long enterpriseId, Long code, String creatorName, Date start, Date end,
                                         BillStatus billStatus,
                                         BillType billType, Pageable pageable);

    /**
     * 创建单据
     *
     * @param creatorId      创建人ID
     * @param type           单据类型
     * @param nextOperatorId 下一个操作者ID
     * @param outId          出库点ID
     * @param inId           入库点ID
     * @param reason         申请原因
     * @param sku            商品SKU
     */
    long createBill(long creatorId, BillType type, Long nextOperatorId, Long outId, Long inId, String reason, Map<Long, Integer> sku);

    /**
     * 批准单据
     *
     * @param id             ID
     * @param nextOperatorId 下一个操作者ID
     * @param amounts        审核数量
     */
    void approveBill(long id, Long nextOperatorId, Map<Long, Integer> amounts);

    /**
     * 同意单据
     *
     * @param id    ID
     * @param isTop 是否为顶级账号操作
     * @param sku   商品SKU
     */
    void agreeBill(long id, Boolean isTop, Map<Long, Integer> sku);

    /**
     * 终止单据
     *
     * @param id ID
     */
    void terminateBill(long id);

    /**
     * 完成单据
     *
     * @param id ID
     */
    void finishBill(long id);

    /**
     * 退回单据
     *
     * @param id ID
     */
    void backBill(long id);

    /**
     * 提交单据（报损报溢单经过退回，修改后重新提交。）
     *
     * @param id             单据ID
     * @param nextOperatorId 下一个操作者ID
     * @param sku            货品SKU
     */
    void submitBill(long id, Long nextOperatorId, Map<Long, Integer> sku);

    /**
     * 发货
     *
     * @param id  ID
     * @param sku 商品SKU
     */
    void deliveryBill(long id, Map<Long, Integer> sku);

    /**
     * 收货
     *
     * @param id ID
     */
    void receiptBill(long id);

}
