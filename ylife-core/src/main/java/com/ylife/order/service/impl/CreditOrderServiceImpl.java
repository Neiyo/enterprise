package com.ylife.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.ylife.client.bean.SyncStatus;
import com.ylife.client.mapper.SyncStatusMapper;
import com.ylife.client.service.OpenOrderService;
import com.ylife.client.service.PlatformClient;
import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.exception.ServerIOException;
import com.ylife.exception.UserOperationException;
import com.ylife.goods.mapper.GoodsProductMapper;
import com.ylife.goods.model.GoodsProduct;
import com.ylife.order.mapper.*;
import com.ylife.order.model.*;
import com.ylife.order.service.CreditOrderService;
import com.ylife.order.service.OrderService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by InThEnd on 2016/5/3.
 * CreditOrderServiceImpl
 */
@Service
public class CreditOrderServiceImpl implements CreditOrderService {

    @Resource
    private CreditOrderMapper creditOrderMapper;
    @Resource
    private CreditOrderLogMapper creditOrderLogMapper;
    @Resource
    private LogisticsMapper logisticsMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OpenOrderService openOrderService;
    @Resource
    private OrderService orderService;
    @Resource
    private SyncStatusMapper syncStatusMapper;
    @Resource
    private BackOrderGoodsMapper backOrderGoodsMapper;
    @Resource
    private GoodsProductMapper goodsProductMapper;


    private Generator generator = IdGeneratorFactory.create("CREDIT_ORDER");

    @Override
    @Transactional
    public void createCreditOrder(long orderNo, Map<Long, Integer> goodsIdMap, CreditOrderReason reason, CredentialType credential, String credentialDoc, String remark, String backWay, boolean valet) {
        Order order = orderMapper.selectByOrderCode(orderNo);
        Assert.notNull(order, "订单不存在。");

        if (order.getOrderStatus() != OrderStatus.RECIEVED) {
            throw new UserOperationException("此订单无法申请退货。");
        }

        CreditOrderStatus status = CreditOrderStatus.RETURN_APPLIED;
        OrderStatus orderStatus = OrderStatus.RETURN_APPLIED;
        if (order.getSelfPick() && valet) {
            status = CreditOrderStatus.RETURN_WAIT_REFUND;
            orderStatus = OrderStatus.RETURN_WAIT_REFUND;
        }

        //1、退货
        CreditOrder creditOrder = new CreditOrder();
        creditOrder.setOrderId(order.getOrderId());
        creditOrder.setBusinessId(order.getBusinessId());
        creditOrder.setBackReason(reason);
        creditOrder.setBackRemark(remark);
        creditOrder.setBackTime(new Date());
        creditOrder.setCustomerId(order.getCustomerId());
        creditOrder.setApplyCredentials(credential);
        creditOrder.setUploadDocuments(credentialDoc);
        creditOrder.setBackPrice(order.getOrderPrice().subtract(order.getExpressPrice() != null ? order.getExpressPrice() : BigDecimal.ZERO));
        creditOrder.setBackCollectAddress(order.getShippingAddress());
        creditOrder.setBackCollectPerson(order.getShippingPerson());
        creditOrder.setBackCollectPhone(order.getShippingMobile());
        creditOrder.setBackOrderCode(generator.generate());
        creditOrder.setBackCheck(status);
        creditOrder.setIsBack("1");
        creditOrder.setBackWay(backWay);
        creditOrderMapper.insertSelective(creditOrder);

        // 1.1 退货商品
        BigDecimal backPrice = BigDecimal.ZERO;
        for (Long goodsInfoId : goodsIdMap.keySet()) {
            BackOrderGoods backOrderGoods = new BackOrderGoods();
            backOrderGoods.setBackorderId(creditOrder.getBackOrderId());
            backOrderGoods.setGoodsInfoId(goodsInfoId);
            backOrderGoods.setGoodsNum(goodsIdMap.get(goodsInfoId));
            GoodsProduct goodsProduct = goodsProductMapper.queryByGoodsInfoDetail(goodsInfoId);
            backOrderGoods.setGoodsPrice(goodsProduct.getGoodsInfoPreferPrice());
            backOrderGoods.setGoodsInfoName(goodsProduct.getGoodsInfoName());
            backOrderGoods.setGoodsInfoImage(goodsProduct.getGoodsInfoImgId());
            backOrderGoods.setGoodsInfoItemNo(goodsProduct.getGoodsInfoItemNo());
            backOrderGoodsMapper.insertSelective(backOrderGoods);

            BigDecimal price = goodsProduct.getGoodsInfoPreferPrice().multiply(new BigDecimal(goodsIdMap.get(goodsInfoId)));
            backPrice = backPrice.add(price);
        }

        // 1.2 修改backPrice
        CreditOrder item = new CreditOrder();
        item.setBackPrice(backPrice);
        item.setBackOrderId(creditOrder.getBackOrderId());
        creditOrderMapper.updateByPrimaryKeySelective(item);


        //2、同步到优生活商城（//自提单暂不同步，退款审核后再同步过去）
        if (!order.getSelfPick()) {
            syncCreateCreditOrder(orderNo);
        }
        //3、退单日志（退货）
        CreditOrderLog creditOrderLog = new CreditOrderLog();
        creditOrderLog.setCreateTime(new Date());
        creditOrderLog.setBackOrderId(creditOrder.getBackOrderId());
        if (!order.getSelfPick()) {
            creditOrderLog.setLogStr("申请退货审核（操作：顾客）");
        } else {
            if (valet) {
                creditOrderLog.setLogStr("代客退货（操作：" + order.getPickInfo().getEnterpriseName() + "）");
            } else {
                creditOrderLog.setLogStr("申请退货（操作：顾客）");
            }
        }
        creditOrderLogMapper.insertSelective(creditOrderLog);
        orderService.editStatus(orderNo, orderStatus);
    }

    @Override
    public void createRefundOrder(long orderNo, CreditOrderReason reason, CredentialType credential, String credentialDoc, String remark) {
        Order order = orderMapper.selectByOrderCode(orderNo);
        Assert.notNull(order, "退单不存在。");

        if (order.getOrderStatus() != OrderStatus.PAYED) {
            throw new UserOperationException("此订单无法申请退款。");
        }

        //1、退款
        CreditOrder creditOrder = new CreditOrder();
        creditOrder.setOrderId(order.getOrderId());
        creditOrder.setBackReason(reason);
        creditOrder.setBackRemark(remark);
        creditOrder.setBusinessId(order.getBusinessId());
        creditOrder.setCustomerId(order.getCustomerId());
        creditOrder.setBackTime(new Date());
        creditOrder.setApplyCredentials(credential);
        creditOrder.setUploadDocuments(credentialDoc);
        creditOrder.setBackPrice(order.getOrderPrice());
        creditOrder.setBackCollectAddress(order.getShippingAddress());
        creditOrder.setBackCollectPerson(order.getShippingPerson());
        creditOrder.setBackCollectPhone(order.getShippingMobile());
        creditOrder.setBackOrderCode(generator.generate());
        creditOrder.setBackCheck(CreditOrderStatus.REFUND_APPLIED);
        creditOrder.setIsBack("2");
        creditOrderMapper.insertSelective(creditOrder);

        // 1.1 退款商品
        for (OrderGoods orderGoods : order.getOrderGoodsList()) {
            BackOrderGoods backOrderGoods = new BackOrderGoods();
            backOrderGoods.setBackorderId(creditOrder.getBackOrderId());
            backOrderGoods.setGoodsInfoId(orderGoods.getGoodsInfoId());
            backOrderGoods.setGoodsNum(Integer.parseInt(orderGoods.getGoodsInfoNum().toString()));
            backOrderGoods.setGoodsPrice(orderGoods.getGoodsInfoPrice());
            backOrderGoods.setGoodsInfoName(orderGoods.getGoodsInfoName());
            backOrderGoods.setGoodsInfoImage(orderGoods.getGoodsImg());
            backOrderGoods.setGoodsInfoItemNo(orderGoods.getGoodsInfoItemNo());
            backOrderGoodsMapper.insertSelective(backOrderGoods);
        }

        //2、同步到优生活商城（//自提单暂不同步，退款审核后再同步过去）
        if (!order.getSelfPick()) {
            syncCreateCreditOrder(orderNo);
        }
        //3、退单日志（退款）
        CreditOrderLog creditOrderLog = new CreditOrderLog();
        creditOrderLog.setCreateTime(new Date());
        creditOrderLog.setBackOrderId(creditOrder.getBackOrderId());
        creditOrderLog.setLogStr("申请退款审核（操作：顾客）");
        creditOrderLogMapper.insertSelective(creditOrderLog);


        //4、修改订单状态为申请退款
        orderService.editStatus(orderNo, OrderStatus.REFUND_APPLIED);
    }

    @Override
    @Transactional
    public void reviewCreditOrder(long creditOrderNo, boolean agree, String operator, String message) {
        CreditOrder creditOrder = creditOrderMapper.selectByCreditOrderCode(creditOrderNo);
        Order order = orderMapper.selectByPrimaryKey(creditOrder.getOrderId());
        if (!order.getSelfPick()) {
            throw new UserOperationException("非自提单的退单无法审核。");
        }
        Assert.notNull(creditOrder, "退单不存在。");
        OrderStatus status;
        CreditOrderStatus creditOrderStatus;

        String log = "同意退款（操作：" + operator + "）留言：" + message;
        //1、修改退单状态
        if (creditOrder.getBackCheck() == CreditOrderStatus.REFUND_APPLIED) {//审核退款
            if (agree) {
                creditOrderStatus = CreditOrderStatus.REFUND_AGREED;
                status = OrderStatus.REFUND_WAIT_REFUND;
            } else {
                creditOrderStatus = CreditOrderStatus.REFUND_DENIED;
                status = OrderStatus.REFUND_DENIED;
                log = "拒绝退款（操作：" + operator + "）留言：" + message;
            }
        } else if (creditOrder.getBackCheck() == CreditOrderStatus.RETURN_APPLIED) {//审核退货
            if (agree) {
                creditOrderStatus = CreditOrderStatus.RETURN_DELIVERING;
                status = OrderStatus.RETURN_WAIT_RECEIVE;
                log = "同意退货（操作：" + operator + "）留言：" + message;
            } else {
                creditOrderStatus = CreditOrderStatus.RETURN_DENIED;
                status = OrderStatus.RETURN_DENIED;
                log = "拒绝退货（操作：" + operator + "）留言：" + message;
            }
        } else {
            return;
        }

        CreditOrder model = new CreditOrder();
        model.setBackOrderId(creditOrder.getBackOrderId());
        model.setBackCheck(creditOrderStatus);
        creditOrderMapper.updateByPrimaryKeySelective(model);

        //2、添加退单日志（非顶级帐号审核退款、退货）
        CreditOrderLog creditOrderLog = new CreditOrderLog();
        creditOrderLog.setCreateTime(new Date());
        creditOrderLog.setBackOrderId(creditOrder.getBackOrderId());
        creditOrderLog.setLogStr(log);
        creditOrderLogMapper.insertSelective(creditOrderLog);

        orderService.editStatus(creditOrder.getOrder().getOrderCode(), status);
    }


    @Override
    public void editDeliveryNo(long orderNo, String deliveryNo, String logisticName) {
        CreditOrder creditOrder = creditOrderMapper.selectByOrderCode(orderNo);
        Assert.notNull(creditOrder, "退单不存在。");

        //1、填写物流信息
        Logistics logistics = new Logistics();
        logistics.setNpLogisticsName(logisticName);
        logistics.setCreatetime(new Date());
        logistics.setBackOrderId(creditOrder.getBackOrderId());
        logistics.setNpLogisticsNo(deliveryNo);
        logisticsMapper.insertSelective(logistics);
        //2、修改退单状态为待商家收货
        creditOrder.setBackCheck(CreditOrderStatus.RETURN_DELIVERING);
        creditOrderMapper.updateByPrimaryKeySelective(creditOrder);
        //3、退单日志
        CreditOrderLog creditOrderLog = new CreditOrderLog();
        creditOrderLog.setCreateTime(new Date());
        creditOrderLog.setLogStr("填写快递信息（操作：顾客）");
        creditOrderLog.setBackOrderId(creditOrder.getBackOrderId());
        creditOrderLogMapper.insertSelective(creditOrderLog);
        //4、同步物流信息到boss端
        syncBackOrderGeneral(creditOrder.getBackOrderCode(), logisticName, deliveryNo);
        //5、修改订单状态为待收货
        orderService.editStatus(orderNo, OrderStatus.RETURN_WAIT_RECEIVE);
    }

    @Override
    public void receiveCreditOrder(long creditOrderNo, boolean receive, String enterpriseMsg, String customerMsg) {
        CreditOrder order = creditOrderMapper.selectByCreditOrderCode(creditOrderNo);
        Assert.notNull(order, "退单不存在。");
        OrderStatus status;

        //1、修改退单信息
        order.setCustomerMsg(enterpriseMsg);//对商城留言
        if (order.getBackCheck() != CreditOrderStatus.RETURN_DELIVERING) {
            return;
        }
        String logString = "确认收货（操作：商城） 给买家留言：" + customerMsg + "\n" + "给商城留言：" + enterpriseMsg;
        if (receive) {
            order.setBackCheck(CreditOrderStatus.RETURN_WAIT_REFUND);
            status = OrderStatus.RETURN_WAIT_REFUND;
        } else {
            order.setBackCheck(CreditOrderStatus.RETURN_DELIVER_FAILURE);
            status = OrderStatus.RETURN_DELIVER_FAILURE;
            logString = "拒绝收货（操作：商城） 给买家留言：" + customerMsg + "\n" + "给商城留言：" + enterpriseMsg;
        }
        creditOrderMapper.updateByPrimaryKeySelective(order);


        //2、添加退单日志（非顶级帐号商家收货）
        CreditOrderLog creditOrderLog = new CreditOrderLog();
        creditOrderLog.setCreateTime(new Date());
        creditOrderLog.setLogStr(logString);
        creditOrderLog.setBackOrderId(order.getBackOrderId());
        creditOrderLogMapper.insertSelective(creditOrderLog);

        orderService.editStatus(order.getOrder().getOrderCode(), status);
    }


    @Override
    public void refund(long creditOrderNo, BigDecimal price, String operator, String msg) {
        CreditOrder order = creditOrderMapper.selectByCreditOrderCode(creditOrderNo);
        Assert.notNull(order, "退单不存在。");
        OrderStatus orderStatus;

        //1、修改退单信息
        CreditOrderStatus status = order.getBackCheck();
        if (status == CreditOrderStatus.RETURN_WAIT_REFUND) {
            order.setBackCheck(CreditOrderStatus.RETURN_FINISHED);
            orderStatus = OrderStatus.RETURN_FINISHED;
        } else if (status == CreditOrderStatus.REFUND_AGREED) {
            order.setBackCheck(CreditOrderStatus.REFUND_FINISHED);
            orderStatus = OrderStatus.REFUND_FINISHED;
        } else {
            return;
        }
        if (status == CreditOrderStatus.RETURN_WAIT_REFUND) {
            order.setBackPrice(price);
        }
        creditOrderMapper.updateByPrimaryKeySelective(order);

        //2、添加退单日志（顶级帐号退款、退货退款）
        CreditOrderLog creditOrderLog = new CreditOrderLog();
        String log = "退款成功（操作：商城） 留言：" + msg;
        creditOrderLog.setLogStr(log);
        creditOrderLog.setBackOrderId(order.getBackOrderId());
        creditOrderLog.setCreateTime(new Date());
        creditOrderLogMapper.insertSelective(creditOrderLog);

        //3、同步数据
        if (order.getOrder().getSelfPick()) {//自提单退单同步
            syncRefundsForSelfPick(creditOrderNo);
        } else {//物流单退单同步
            syncRefundsOrder(creditOrderNo, order.getIsBack(), msg);
        }
        orderService.editStatus(order.getOrder().getOrderCode(), orderStatus);
    }

    @Override
    public Page<CreditOrder> getCreditOrders(Pageable pageable) {
        return null;
    }

    @Override
    public Page<CreditOrder> getBackOrder(Long backOrderNo,
                                          Long minCatalog,
                                          Long maxCatalog,
                                          String phoneNo,
                                          Long orderNo,
                                          CreditOrderStatus status,
                                          Date start, Date end,
                                          Pageable pageable) {
        CreditOrder model = new CreditOrder();
        Order order = orderMapper.selectByOrderCode(orderNo);
        if (order != null) {
            model.setOrderId(order.getOrderId());
        }
        model.setBackOrderCode(backOrderNo);
        model.setBackCollectPhone(phoneNo);
        if (status != null) {
            model.setBackCheck(status);
        }
        List<CreditOrder> list = creditOrderMapper.selectByModelSelectiveAndEnterpriseIdAndCreateDate(model, maxCatalog, minCatalog, orderNo, start, end, pageable);
        int totalElements = creditOrderMapper.countByModelSelectiveAndEnterpriseIdAndCreateDate(model, maxCatalog, minCatalog, orderNo, start, end);
        return new PageImpl<>(pageable, totalElements, list);
    }


    /**
     * 同步退单数据（用户提交退单完成调用）
     *
     * @param orderCode 订单号
     */
    @Override
    public void syncCreateCreditOrder(long orderCode) {
        CreditOrder backOrder = creditOrderMapper.selectByOrderCode(orderCode);
        if (backOrder == null) {
            return;
        }
        //同步退单
        String url = "order/applyBackOrder.htm";
        Map<String, String> params = new HashMap<>();
        params.put("orderCode", Long.toString(orderCode));
        params.put("backOrderCode", backOrder.getBackOrderCode().toString());
        params.put("backPrice", backOrder.getBackPrice().toString());
        params.put("singleReason", backOrder.getBackRemark());
        params.put("status", backOrder.getIsBack());

        List<OrderProduct> oOrderGoodsList = new ArrayList<>();
        for (BackOrderGoods backOrderGoods : backOrder.getBackOrderGoods()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setGoodsInfoItemNo(backOrderGoods.getGoodsInfoItemNo());
            orderProduct.setGoodsInfoNum(Long.parseLong(backOrderGoods.getGoodsNum().toString()));
            oOrderGoodsList.add(orderProduct);
        }

        params.put("backOrderGoodsList", JSON.toJSONString(oOrderGoodsList, SerializerFeature.DisableCircularReferenceDetect));
        try {
            PlatformClient.post(url, params);
        } catch (Exception ex) {
            throw new ServerIOException("网络错误，请稍后重试。");
        }
    }

    /**
     * 同步顾客填写退单物流
     *
     * @param backOrderCode 退单号
     * @param logisticsName 物流公司
     * @param logisticsNo   物流单号
     */
    @Override
    public void syncBackOrderGeneral(long backOrderCode, String logisticsName, String logisticsNo) {
        CreditOrder backOrder = creditOrderMapper.selectByCreditOrderCode(backOrderCode);
        if (backOrder == null) {
            return;
        }
        //同步退单
        String url = "order/saveBackOrderGeneral.htm";
        Map<String, String> params = new HashMap<>();
        params.put("backOrderCode", backOrder.getBackOrderCode().toString());
        params.put("logisticsName", logisticsName);//
        params.put("logisticsNo", logisticsNo);
        try {
            PlatformClient.post(url, params);
        } catch (Exception ex) {
            throw new ServerIOException("网络错误，请稍后重试。");
        }
    }

    /**
     * 审核退款订单
     *
     * @param backOrderCode 退单号
     * @param status        1同意退货退款 2同意退款 3拒绝退款
     * @param backRemark    平台退款留言
     */
    @Override
    public void syncRefundsOrder(long backOrderCode, String status, String backRemark) {
        CreditOrder backOrder = creditOrderMapper.selectByCreditOrderCode(backOrderCode);
        if (backOrder == null) {
            return;
        }
        //同步审核退款
        String url = "order/doRefundsOrder.htm";
        Map<String, String> params = new HashMap<>();
        params.put("backOrderCode", backOrder.getBackOrderCode().toString());
        params.put("status", status);
        params.put("backPrice", backOrder.getBackPrice().toString());
//        params.put("cashPrice", "0");//（组合支付时，现金部分）
        params.put("backRemark", backRemark);
        try {
            PlatformClient.post(url, params);
        } catch (Exception ex) {
            throw new ServerIOException("网络错误，请稍后重试。");
        }
    }

    /**
     * 自提单退款/退货退款成功操作
     *
     * @param backOrderCode 订单号
     */
    @Override
    public void syncRefundsForSelfPick(long backOrderCode) {
        CreditOrder backOrder = creditOrderMapper.selectByCreditOrderCode(backOrderCode);
        if (backOrder == null) {
            return;
        }
        //同步审核退款
        String url = "order/applyBackOrderForSelfPick.htm";
        Map<String, String> params = new HashMap<>();
        params.put("backOrderCode", backOrder.getBackOrderCode().toString());
        params.put("orderCode", backOrder.getOrder().getOrderCode().toString());
        params.put("singleReason", backOrder.getBackRemark());
        params.put("status", backOrder.getIsBack());
        params.put("backPrice", backOrder.getBackPrice().toString());
//        params.put("cashPrice", "0");//（组合支付时，现金部分）
        List<OrderProduct> oOrderGoodsList = new ArrayList<>();
        for (BackOrderGoods backOrderGoods : backOrder.getBackOrderGoods()) {
            OrderProduct orderProduct = new OrderProduct();
            orderProduct.setGoodsInfoItemNo(backOrderGoods.getGoodsInfoItemNo());
            orderProduct.setGoodsInfoNum(Long.parseLong(backOrderGoods.getGoodsNum().toString()));
            oOrderGoodsList.add(orderProduct);
        }
        params.put("backOrderGoodsList", JSON.toJSONString(oOrderGoodsList, SerializerFeature.DisableCircularReferenceDetect));
        try {
            PlatformClient.post(url, params);
        } catch (Exception ex) {
            throw new ServerIOException("网络错误，请稍后重试。");
        }
    }

    /**
     * @return
     */
    @Transactional
    public boolean beginSynchronization(String syncGoodsProduct) {
        SyncStatus syncStatus = syncStatusMapper.selectByPrimaryKey(syncGoodsProduct);
        // LOCK TABLES ysh_sync_type WRITE;
        if (syncStatus.getSyncstatus().equals("1")) {
            return true;
        } else {
            //修改的getGoodsItemNoList 为1
            syncStatus.setSyncstatus("1");
            syncStatusMapper.updateByPrimaryKeySelective(syncStatus);
            return false;
        }
        //UNLOCK TABLES;
    }

    /**
     * @return
     */
    public boolean endSynchronization(String syncGoodsProduct) {
        SyncStatus syncStatus = new SyncStatus();
        syncStatus.setSynctype(syncGoodsProduct);
        syncStatus.setSyncstatus("0");
        syncStatusMapper.updateByPrimaryKeySelective(syncStatus);
        return true;
    }

    /**
     * 同步退单状态
     */
    @Override
    public void updateBackOrderStatus() throws IOException {
        if (!beginSynchronization("syncBackOrder")) {
            try {
                //1、查询申请退款、申请退货、客户填写物流信息的退单
                List<CreditOrder> creditOrderList = creditOrderMapper.selectBackOrderListByCondition();
                List<com.ylife.client.bean.BackOrder> oBackOrderList = new ArrayList<>();
                for (CreditOrder creditOrder : creditOrderList) {
                    com.ylife.client.bean.BackOrder backOrder = new com.ylife.client.bean.BackOrder();
                    backOrder.setBackOrderCode(creditOrder.getBackOrderCode().toString());
                    backOrder.setBackCheck(creditOrder.getBackCheck().getCode());
                    oBackOrderList.add(backOrder);
                }
                //1、查询已提交的退单
                List<com.ylife.client.bean.BackOrder> backOrderList = openOrderService.updateBackOrderStatus(JSON.toJSONString(oBackOrderList, SerializerFeature.DisableCircularReferenceDetect));
                if (backOrderList != null) {
                    for (com.ylife.client.bean.BackOrder item : backOrderList) {
                        item.setBackCheck(CreditOrderStatus.getName(item.getBackCheck()));

                        if (item.getBackCheck().toUpperCase().equals("RETURN_AGREED")) {//同意退货 不需要写日志
                            break;
                        }

                        //1.1、退单日志
                        CreditOrderLog creditOrderLog = new CreditOrderLog();

                        for (CreditOrder creditOrder : creditOrderList) {
                            if (creditOrder.getBackOrderCode().toString().equals(item.getBackOrderCode())) {
                                creditOrderLog.setBackOrderId(creditOrder.getBackOrderId());
                                break;
                            }
                        }
                        switch (item.getBackCheck()) {
                            case "REFUND_AGREED"://同意退款
                                creditOrderLog.setLogStr("同意退款（操作：优生活）");
                                break;
                            case "REFUND_DENIED"://拒绝退款
                                creditOrderLog.setLogStr("拒绝退款（操作：优生活）");
                                break;
                            case "RETURN_WAIT_DELIVER_INFO"://同意退货
                                creditOrderLog.setLogStr("同意退货（操作：优生活）");
                                break;
                            case "RETURN_DENIED"://拒绝退货
                                creditOrderLog.setLogStr("拒绝退货（操作：优生活）");
                                break;
                            case "RETURN_WAIT_REFUND"://确认收货
                                creditOrderLog.setLogStr("确认收货（操作：优生活）");
                                break;
                            case "RETURN_DELIVER_FAILURE"://收货失败
                                creditOrderLog.setLogStr("收货失败（操作：优生活）");
                                break;
                        }

                        creditOrderLog.setCreateTime(new Date());
                        creditOrderLogMapper.insertSelective(creditOrderLog);

                        //2、修改退单状态
                        creditOrderMapper.updateStatusByBackOrderCode(item);
                    }
                }
            } finally {
                //syncBackOrder 为0
                endSynchronization("syncBackOrder");
            }
        }
    }

    /**
     * 根据退单号来获取退单信息
     *
     * @param backOrderCode
     * @return
     */
    @Override
    public CreditOrder selectByCreditOrderCode(long backOrderCode) {
        return creditOrderMapper.selectByCreditOrderCode(backOrderCode);
    }
}
