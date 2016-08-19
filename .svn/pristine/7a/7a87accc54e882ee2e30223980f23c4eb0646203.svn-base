package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.OrderManageService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.model.InventoryKey;
import com.ylife.inventory.service.InventoryService;
import com.ylife.order.mapper.CreditOrderMapper;
import com.ylife.order.mapper.OrderMapper;
import com.ylife.order.model.*;
import com.ylife.order.service.CreditOrderService;
import com.ylife.order.service.OrderService;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/5/2.
 * OrderManageServiceImpl
 */
@Service
public class OrderManageServiceImpl implements OrderManageService {

    @Resource
    private CreditOrderService creditOrderService;
    @Resource
    private CustomerUcoinService customerUcoinService;
    @Resource
    private OrderService orderService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;
    @Resource
    private CreditOrderMapper creditOrderMapper;
    @Resource
    private OrderMapper orderMapper;


    @Override
    public Page<Order> getOrders(Long orderNo,
                                 Long enterpriseId,
                                 OrderStatus status,
                                 Boolean selfPick,
                                 String username,
                                 String receiver,
                                 String contactPhone,
                                 Date createStart,
                                 Date createEnd,
                                 Date payStart,
                                 Date payEnd,
                                 Pageable pageable) {

        EnterpriseFunction current = currentLoginService.getCurrentLoginEnterpriseFunc();
        EnterpriseFunction function;
        Long minIndex = null;
        Long maxIndex = null;
        if (enterpriseId == null) {
            if (current.getParentId() != null) {
                minIndex = current.getMinCatalog();
                maxIndex = current.getMaxCatalog();
            }
        } else {
            function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
            Assert.notNull(function, "企业不存在。");
            Assert.isTrue(function.getCatalog() >= current.getMinCatalog() && function.getCatalog() <= current.getMaxCatalog(), "无权获取此企业信息。");
            if (function.getParentId() != null) {
                minIndex = function.getMinCatalog();
                maxIndex = function.getMaxCatalog();
            }
        }
        Order model = new Order();
        if (status != null) {
            model.setOrderStatus(status);
        }
        model.setSelfPick(selfPick);
        model.setOrderCode(orderNo);
        //model.setShippingPhone(contactPhone);
        model.setShippingPerson(receiver);
        model.setShippingMobile(contactPhone);
        return orderService.getOrder(model, maxIndex, minIndex, username, createStart, createEnd, payStart, payEnd, pageable);
    }

    @Override
    public Page<CreditOrder> getBackOrder(Long backOrderNo,
                                          Long enterpriseId,
                                          String phoneNo,
                                          Long orderNo,
                                          CreditOrderStatus status,
                                          Date start,
                                          Date end,
                                          Pageable pageable) {
        EnterpriseFunction current = currentLoginService.getCurrentLoginEnterpriseFunc();
        EnterpriseFunction function;
        Long minIndex = null;
        Long maxIndex = null;
        if (enterpriseId == null) {
            if (current.getParentId() != null) {
                minIndex = current.getMinCatalog();
                maxIndex = current.getMaxCatalog();
            }
        } else {
            function = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
            Assert.notNull(function, "企业不存在。");
            Assert.isTrue(function.getCatalog() >= current.getMinCatalog() && function.getCatalog() <= current.getMaxCatalog(), "无权获取此企业信息。");
            if (function.getParentId() != null) {
                minIndex = function.getMinCatalog();
                maxIndex = function.getMaxCatalog();
            }
        }
        return creditOrderService.getBackOrder(backOrderNo, minIndex, maxIndex, phoneNo, orderNo, status, start, end, pageable);
    }

    @Override
    @Transactional
    public void reviewCreditOrder(long creditOrderNo, boolean agree, String message) {
        //1、非顶级企业审核退货单
        CreditOrder creditOrder = creditOrderMapper.selectByCreditOrderCode(creditOrderNo);
        List<BackOrderGoods> orderProductList = creditOrder.getBackOrderGoods();
        Long orderCode = creditOrder.getOrder().getOrderCode();
        Order order = orderMapper.selectByOrderCode(orderCode);
        Long enterpriseId = order.getEnterpriseId();
        if (order.getSelfPick()) {
            for (BackOrderGoods backOrderGoods : orderProductList) {
                Long goodsInfoId = backOrderGoods.getGoodsInfoId();
                int amount = backOrderGoods.getGoodsNum();
                InventoryKey key = new InventoryKey(enterpriseId, goodsInfoId);
                inventoryService.addActuallyInventory(key, amount);
                inventoryService.addAvailableInventory(key, amount);
            }
        }
        creditOrderService.reviewCreditOrder(creditOrderNo, agree, currentLoginService.getCurrentLoginEnterpriseInfo().getEnterpriseName(), message);
    }

    @Override
    @Transactional
    public void receiveCreditOrder(long creditOrderNo, boolean receive, String enterpriseMsg, String customerMsg) {
        //1、非顶级企业退货单收货
        creditOrderService.receiveCreditOrder(creditOrderNo, receive, enterpriseMsg, customerMsg);
    }

    @Override
    @Transactional
    public void refund(long creditOrderNo, BigDecimal price, String msg) {
        if (!currentLoginService.isPrimaryEnterprise()) {
            throw new UserOperationException("非顶级账户无权操作。");
        }
        //顶级企业退款
        creditOrderService.refund(creditOrderNo, price, currentLoginService.getCurrentLoginEnterpriseInfo().getEnterpriseName(), msg);

        CreditOrder creditOrder = creditOrderService.selectByCreditOrderCode(creditOrderNo);
        //3、退款给客户
        customerUcoinService.grandUcoin(creditOrder.getCustomerId(), 1L, price, null, null, null, msg);
        //4、退款日志
        customerUcoinHistoryService.addHistory(creditOrder.getCustomerId(), 1L, null, HistoryType.UCOIN_REFUND, price, null, null, null, null, creditOrder.getOrderId(), null);
    }

    @Override
    public CreditOrderStatus[] getStatuses() {
        return CreditOrderStatus.values();
    }

    @Override
    public OrderStatus[] getOrderStatuses() {
        return OrderStatus.values();
    }

    @Override
    @Transactional
    public void statusToPayed(long orderCode) {
        Order order = orderService.getOrder(orderCode);
        Assert.notNull(order, "订单不存在。");
        long enterpriseId = order.getEnterpriseId();
        long goodsId = order.getOrderGoodsList().get(0).getGoodsInfoId();
        int count = (int) (long) (order.getOrderGoodsList().get(0).getGoodsInfoNum());
        orderService.payOrder(orderCode);
        inventoryService.addActuallyInventory(new InventoryKey(enterpriseId, goodsId), -count);
    }

    @Override
    @Transactional
    public void cancelOrder(long orderCode) {
        Order order = orderService.getOrder(orderCode);
        Assert.notNull(order, "订单不存在。");
        long enterpriseId = order.getEnterpriseId();
        long goodsId = order.getOrderGoodsList().get(0).getGoodsInfoId();
        int count = (int) (long) (order.getOrderGoodsList().get(0).getGoodsInfoNum());
        orderService.cancelOrder(orderCode);
        inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, goodsId), count);

    }

    @Override
    public void editPrice(long orderCode, BigDecimal price) {
        if (!currentLoginService.isPrimaryEnterprise()) {
            throw new UserOperationException("非顶级账户无权操作。");
        }
        orderService.editPrice(orderCode, price);
    }


    /**
     * 定时取消订单（代客下单未支付）
     */
    @Override
    public void cancelOrderTiming() {
        List<Order> valetSubmitOrders = orderService.selectByValetSubmit();
        for (Order order : valetSubmitOrders) {
            cancelOrder(order.getOrderCode());
        }
    }


    @Override
    @Transactional
    public void valetBackOrder(long orderNo, Map<Long, Integer> goodsIdMap, CreditOrderReason reason, CredentialType credential, String credentialDoc, String remark, String backWay) {
        Order order = orderService.getOrder(orderNo);
        if (order.getOrderStatus() == OrderStatus.RECIEVED && order.getSelfPick() && order.getEnterpriseId().equals(currentLoginService.getCurrentLoginEnterpriseId())) {
            creditOrderService.createCreditOrder(orderNo, goodsIdMap, reason, credential, credentialDoc, remark, "2", true);
        }
    }
}
