package com.ylife.order.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.order.model.Order;
import com.ylife.order.model.OrderStatus;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单支付service
 */
public interface OrderService {


    /**
     * 获取Order
     *
     * @param model       查询模型
     * @param username    用户名
     * @param createStart 创建开始时间
     * @param createEnd   创建结束时间
     * @param payStart    付款开始时间
     * @param payEnd      付款结束时间
     * @param pageable    分页信息
     */
    Page<Order> getOrder(Order model, Long maxCatalog, Long minCatalog, String username, Date createStart, Date createEnd, Date payStart, Date payEnd, Pageable pageable);

    /**
     * 新流程保存订单
     *
     * @param customerId     客户ID
     * @param shoppingCartId 购物车
     * @param isSelfPickUp   是否为自提
     * @param remark         买家备注
     * @param enterpriseId   自提节点ID
     * @param addressId      收货地址ID
     */
    List<Order> newsubmitOrder(long customerId, Long addressId, Long[] shoppingCartId, boolean isSelfPickUp, String remark, Long enterpriseId);

    /**
     * B端代客下单 保存订单
     *
     * @param customerId   客户ID
     * @param goodsInfoId  货品
     * @param count        数量
     * @param isSelfPickUp 自提标志
     * @param enterpriseId 自提节点ID
     * @param operator     操作人账号
     */
    List<Order> submitOrder(long customerId, Long addressId, long goodsInfoId, int count, boolean isSelfPickUp, Long enterpriseId, String operator);


    /**
     * 保存订单
     *
     * @param customerId    用户ID
     * @param addressId     用户地址ID
     * @param goodsInfoList 购买的商品信息 key为货品Id，value为购买的数量
     * @param isSelfPickUp  自提标志(true代表自提，false代表不是自提)
     * @param enterpriseId  自提节点ID
     * @param operator      操作人账号
     * @param isValet       是否为代客下单（true表示为代客下单，false表示自主下单）
     */
    List<Order> submitOrder(long customerId, Long addressId, Map<Long, Integer> goodsInfoList, boolean isSelfPickUp, Long enterpriseId, String operator, boolean isValet, String remark);


    /**
     * 订单提货
     *
     * @param orderCode 订单号
     */
    void pickUpOrder(long orderCode);

    /**
     * 确认支付
     *
     * @param orderCode 订单编号
     */
    void payOrder(long orderCode);

    /**
     * 确认收货 订单操作
     *
     * @param orderCode 订单编号
     */
    void confirmOrder(long orderCode);

    /**
     * 取消订单
     *
     * @param orderCode 订单编号
     */
    void cancelOrder(long orderCode);

    /**
     * 根据订单号获取订单。如果订单不存在抛出异常。
     *
     * @param orderCode 订单号
     * @throws com.ylife.exception.RecordNotFoundException 当订单不存在时抛出此异常。
     */
    Order getOrder(long orderCode);

    /**
     * 同步订单状态（邮宝已付款未发货的订单）
     */
    void updateOrderStatus() throws IOException;

    /**
     * 退单。
     *
     * @param orderCode 订单号
     */
    void backOrder(long orderCode);

    /**
     * 修改状态(非常规操作)。
     *
     * @param orderCode 订单号
     * @param status    要修改的状态
     */
    void editStatus(long orderCode, OrderStatus status);

    /**
     * 修改金额(非常规操作)。
     *
     * @param orderCode 订单号
     * @param price     要修改的金额
     */
    void editPrice(long orderCode, BigDecimal price);

    /**
     * 定时取消订单（代客下单未支付）
     */
    List<Order> selectByValetSubmit();
}
