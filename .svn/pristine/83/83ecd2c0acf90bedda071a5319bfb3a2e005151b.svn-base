package com.ylife.chinapost.mobile.service.impl;

import com.ylife.chinapost.mobile.service.CurrentLoginService;
import com.ylife.chinapost.mobile.service.PlaceOrderService;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.exception.UserOperationException;
import com.ylife.inventory.model.InventoryKey;
import com.ylife.inventory.service.InventoryService;
import com.ylife.order.model.Order;
import com.ylife.order.model.OrderGoods;
import com.ylife.order.service.OrderService;
import com.ylife.shoppingcart.mapper.ShoppingCartMapper;
import com.ylife.shoppingcart.model.ShoppingCart;
import com.ylife.shoppingcart.service.ShoppingCartService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.Assert;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by InThEnd on 2016/5/8.
 * PlaceOrderServiceImpl
 */
@Service
public class PlaceOrderServiceImpl implements PlaceOrderService {

    @Resource
    private OrderService orderService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private CustomerUcoinService customerUcoinService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private ShoppingCartMapper shoppingCartMapper;
    @Resource
    private ShoppingCartService shoppingCartService;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;

    @Override
    @Transactional
    public String submitOrderAndPay(Long addressId, Long[] shoppingCartId, boolean selfPick, String remark, Long enterpriseId, String paykey) {
        List<Order> orders = submitOrder(addressId, shoppingCartId, selfPick, remark, enterpriseId);
        long[] orderCodes = new long[orders.size()];
        for (int i = 0; i < orderCodes.length; i++) {
            orderCodes[i] = orders.get(i).getOrderCode();
        }
        //付款
        payOrder(orderCodes, paykey);
        if (selfPick) {
            return new JsonResponseBean(getDeliveryCode(orders.get(0).getOrderCode())).toJson();
        } else {
            return JsonResponseBean.getSuccessResponse().toJson();
        }
    }

    @Override
    @Transactional
    public List<Order> submitOrder(Long addressId, Long[] shoppingCartId, boolean selfPick, String remark, Long enterpriseId) {
        if (selfPick) {
            Assert.notNull(enterpriseId);
        }
        long customerId = currentLoginService.getCurrentLoginCustomerId();
        // 1、获取所有所有选中商品信息
        List<ShoppingCart> cartlist = shoppingCartMapper.shopCartListByIds(Arrays.asList(shoppingCartId));
        if (cartlist.size() == 0) {
            throw new UserOperationException("购物车为空。");
        }
        Map<Long, Integer> goodsInfoList = new HashMap<>();
        for (ShoppingCart item : cartlist) {
            long goodsInfoId = item.getGoodsInfoId();
            int goodsNum = item.getGoodsNum().intValue();
            goodsInfoList.put(goodsInfoId, goodsNum);
            //如果自提,修改可用库存。
            if (selfPick) {
                inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, goodsInfoId), -goodsNum);
            }
        }
        //2、提交订单
        List<Order> orders = orderService.submitOrder(customerId, addressId, goodsInfoList, selfPick, enterpriseId, null, false, remark);
        //3、修改所有购买商品为已经删除
        shoppingCartService.deleteShoppingCartByIds(shoppingCartId);
        return orders;
    }

    @Override
    @Transactional
    public void payOrder(long[] orderCodes, String paykey) {
        long customerId = currentLoginService.getCurrentLoginCustomerId();
        if (!chinapostCustomerService.checkPaykey(customerId, paykey)) {
            throw new UserOperationException("支付密码错误！");
        }
        for (long orderCode : orderCodes) {
            orderService.payOrder(orderCode);
            Order order = orderService.getOrder(orderCode);
            customerUcoinService.ucoinPay(customerId, order.getOrderPrice(), null, order.getOrderId());
        }
    }

    @Override
    @Transactional
    public void cancelOrder(long orderCode) {
        //取消订单。
        orderService.cancelOrder(orderCode);
        //如果为自提，增加可用库存。
        Order order = orderService.getOrder(orderCode);
        if (order.getSelfPick()) {
            long enterpriseId = order.getEnterpriseId();
            for (OrderGoods goods : order.getOrderGoodsList()) {
                inventoryService.addAvailableInventory(new InventoryKey(enterpriseId, goods.getGoodsInfoId()), goods.getGoodsInfoNum().intValue());
            }
        }
    }

    @Override
    @Transactional
    public void confirmOrder(long orderCode) {
        orderService.confirmOrder(orderCode);
    }

    @Override
    public String getDeliveryCode(long orderCode) {
        return orderService.getOrder(orderCode).getDeliveryCode();
    }

    @Override
    public Order getOrder(long orderCode) {
        return orderService.getOrder(orderCode);
    }

    @Override
    public boolean isPaykeySet() {
        long customerId = currentLoginService.getCurrentLoginCustomerId();
        ChinapostCustomer customer = chinapostCustomerService.getCustomer(customerId);
        return !StringUtil.isBlank(customer.getPaykey());
    }

    @Override
    public void setPaykey(String paykey) {
        Assert.hasLength(paykey);
        long customerId = currentLoginService.getCurrentLoginCustomerId();
        chinapostCustomerService.setPaymentPassword(customerId, paykey.trim());
    }

    @Override
    public List<EnterpriseInfo> getSuggestEnterprise() {
        long customerId = currentLoginService.getCurrentLoginCustomerId();
        return enterpriseInfoService.getCustomerLinkedEnterpriseInfo(customerId);
    }

    @Override
    public List<EnterpriseInfo> getEnterpriseByDistrictId(long districtId) {
        return enterpriseInfoService.getEnterpriseInfosByDistrictId(districtId);
    }
}
