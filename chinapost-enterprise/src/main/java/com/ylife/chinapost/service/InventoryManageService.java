package com.ylife.chinapost.service;


import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.model.BillStatus;
import com.ylife.inventory.model.BillType;
import com.ylife.inventory.model.InventoryBill;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by XuKai on 2016/4/21.
 * 库存管理服务
 */
public interface InventoryManageService {

    /**
     * 获取当前库存货品列表的page
     *
     * @param goodsName   商品名称
     * @param goodsNumber 货号
     * @param brandId     品牌ID
     * @param thirdId     商家ID
     * @param pageable    分页信息
     */
    Page<InventoryGoodsResult> getInventoryGoodsResult(String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable);

    /**
     * 获取当前库存货品列表的page
     *
     * @param goodsName   商品名称
     * @param goodsNumber 货号
     * @param brandId     品牌ID
     * @param thirdId     商家ID
     * @param pageable    分页信息
     */
    Page<InventoryGoodsResult> getInventoryGoodsResultBYEnterpriseId(Long enterpriseId, String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable);

    /**
     * 获取具体库存货品实例
     *
     * @param goodsInfoId 货品ID
     */
    InventoryGoodsResult getInventoryGoodsResultByPrimarykey(long goodsInfoId);


    /**
     * 获取子级网点
     */
    Map<String, Object> sonEnterprise();

    /**
     * 选择货品，获取货品列表(已经删除的货品不显示)
     *
     * @param goodsName   商品名称
     * @param goodsNumber 货号
     * @param brandId     品牌ID
     * @param thirdId     商家ID
     */
    Page<InventoryGoodsResult> getGoods(String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable);

    /**
     * 增加库存值
     *
     * @param goodsInfoId 货品ID
     * @param inventory   库存数量
     */
    void addInventory(long goodsInfoId, int inventory);

    /**
     * 修改库存
     */
    void editInventory(long goodsInfoId, int inventory);

    /**
     * 修改可用库存
     *
     * @param goodsInfoId 货品ID
     * @param available   可用数量
     */
    void editAvailable(long goodsInfoId, int available);

    /**
     * 删除零库存商品
     */
    void deleteGoods(long goodsInfoId);

    /**
     * 添加仓库货品
     *
     * @param goods
     */
    void addGoods(Map<Long, Integer> goods);

    /**
     * 设置预警库存
     */
    void setWarning(int warning);

    /**
     * 获取预警货品的page
     *
     * @param goodsName   商品名称
     * @param goodsNumber 货号
     * @param brandId     品牌ID
     * @param thirdId     商家ID
     * @param pageable    分页信息
     */
    Page<InventoryGoodsResult> getWarningGoods(String goodsName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable);

    /**
     * 获取指定货品信息以及网点库存信息
     *
     * @param enterpriseId
     * @param goodsInfoId
     * @param goodsInfoName
     * @param goodsNumber
     * @param brandId
     * @param thirdId
     * @param pageable
     * @return
     */
    Page<InventoryGoodsResult> getGoodsAndInventory(Long enterpriseId, Long goodsInfoId, String goodsInfoName, String goodsNumber, Long brandId, Long thirdId, Pageable pageable);


    /**
     * 获取具体库存预警商品实例
     */
    InventoryGoodsResult getWarningGood(long goodsInfoId);

    /**
     * 获取预警值
     */
    int getWarning();

    //按仓库查询
    Page<List<InventoryGoodsResult>> queryByInventory(Long enterpriseId, String goodsInfoName,
                                                      String goodsNumber, Long brandId, Long thirdId, Pageable pageable);

    //按货品查询
    Page<InventoryGoodsResult> queryByGoodsInfo(Long enterpriseId, String goodsInfoName,
                                                String goodsNumber, Long brandId, Long thirdId, Pageable pageable);


    /*****************************************************************************************************************************/
    /*****************************************************请货，调拨，报损、报溢*****************************************************/
    /*****************************************************************************************************************************/

    /**
     * 根据ID获取单据。
     *
     * @param billId ID
     */
    InventoryBill getBillById(long billId);

    /**
     * 根据code获取单据。
     *
     * @param code 单据编号
     */
    InventoryBill getBillByCode(long code);

    /**
     * 获取创建的单据。
     */
    Page<InventoryBill> getCreatedBills(Long code, String creatorName, Date start, Date end, BillStatus billStatus,
                                        BillType billType,Pageable pageable);

    /**
     * 获取已处理的单据。
     */
    Page<InventoryBill> getHandledBills(Long code, String creatorName, Date start, Date end,BillStatus billStatus,
                                        BillType billType, Pageable pageable);

    /**
     * 获取待处理单据。
     */
    Page<InventoryBill> getUnHandleBills(Long code, String creatorName, Date start, Date end,BillStatus billStatus,
                                         BillType billType, Pageable pageable);

    /**
     * 创建单据
     *
     * @param type   单据类型
     * @param outId  出库点ID
     * @param inId   入库点ID
     * @param reason 申请原因
     * @param sku    商品SKU
     */
    void createBill(BillType type, Long outId, Long inId, String reason, Map<Long, Integer> sku);

    /**
     * 批准单据
     *
     * @param billId  单据号
     * @param comment 流转意见
     * @param amounts 审核数量表
     */
    void approveBill(long billId, String comment, Map<Long, Integer> amounts);

    /**
     * 同意单据
     *
     * @param billId  单据号
     * @param comment 流转意见
     * @param sku     商品SKU
     */
    void agreeBill(long billId, String comment, Map<Long, Integer> sku);

    /**
     * 终止单据
     *
     * @param billId  单据号
     * @param comment 流转意见
     */
    void terminateBill(long billId, String comment);

    /**
     * 完成单据
     *
     * @param billId 单据号
     */
    void finishBill(long billId);

    /**
     * 退回单据
     *
     * @param billId  单据号
     * @param comment 流转意见
     */
    void backBill(long billId, String comment);

    /**
     * 提交单据（报损报溢单经过退回，修改后重新提交。）
     *
     * @param billId  单据ID
     * @param comment 流转意见
     * @param sku     货品SKU
     */
    void submitBill(long billId, String comment, Map<Long, Integer> sku);

    /**
     * 发货
     *
     * @param billId  单据号
     * @param comment 流转意见
     * @param sku     商品SKU
     */
    void deliveryBill(long billId, String comment, Map<Long, Integer> sku);

    /**
     * 收货
     *
     * @param billId  单据号
     * @param comment 流转意见
     */
    void receiptBill(long billId, String comment);

}
