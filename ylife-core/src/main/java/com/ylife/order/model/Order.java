/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.order.model;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单主表 2014-04-09
 */
public class Order implements Serializable {


    /****************************************************************************************************************
     *                                                    原有字段                                                   *
     ****************************************************************************************************************/

    /***/

    /*订单ID*/
    private Long orderId;
    /*订单编号*/
    private Long orderCode;
    /*订单价格*/
    private BigDecimal orderPrice;
    /*优惠价格*/
    private BigDecimal orderPrePrice;
    /*订单优惠价格*/
    private BigDecimal orderPrePriceOrder;
    /* 积分兑换金额*/
    private BigDecimal jfPrice;
    /*原始价格*/
    private BigDecimal orderOldPrice;
    /*订单状态 0未付款 1已付款未发货 2已发货未确认收获 3已经收货 4作废*/
    private OrderStatus orderStatus;
    /*用户ID*/
    private Long customerId;
    /*付款时间*/
    private Date payTime;
    /*发货时间*/
    private Date sendExpressTime;
    /*收货时间*/
    private Date getGoodsTime;
    /*收货地址ID*/
    private Long shoppingAddrId;
    /*收货省*/
    private String shippingProvince;
    /*收货城市*/
    private String shippingCity;
    /*收货区县*/
    private String shippingCounty;
    /*收货区县id*/
    private Long shippingCountyId;
    /*详细地址*/
    private String shippingAddress;
    /*收货人*/
    private String shippingPerson;
    /*电话*/
    private String shippingPhone;
    /*手机*/
    private String shippingMobile;
    /*发票抬头*/
    private String invoiceTitle;
    /*发票内容*/
    private String invoiceContent;
    /*是否删除*/
    private String delFlag;
    /*发票类型*/
    private String invoiceType;
    /*客户留言*/
    private String customerRemark;
    /*支付ID*/
    private Long payId;
    /*支付名称*/
    private String payName;
    /*订单积分*/
    private Long orderIntegral;
    /*优惠卷码*/
    private String couponNo;
    /*运费*/
    private BigDecimal expressPrice;
    /*退单金额*/
    private String shippingPostcode;
    /*退单金额*/
    private BigDecimal backPrice;
    /*商家ID*/
    private Long businessId;
    /*仓库名称*/
    private String wareName;
    /*仓库ID*/
    private Long wareId;
    /*类型 会员0 经销商1 第三方2*/
    private String dealerType;
    /*主单Code*/
    private Long orderOldCode;
    /*创建时间*/
    private Date createTime;
    /*订单取消时间*/
    private Date orderCancelTime;
    /*配送方式 0：快递配送 1：上门自提*/
    private String orderExpressType;
    /*订单付款方式 1，线上支付 0，货到付款*/
    private String orderLinePay;
    /*新增订单*/
    private String orderNewStatus;
    /*退单原因*/
    private String orderCancelRemark;
    /*订单出库状态 0:未拣货 1：已装箱 2：未出库*/
    private String orderCargoStatus;
    /*判断是否是手机订单 0：PC订单 1：手机订单 2：微信订单*/
    private String orderMType;
    /*0 商家订单 1直营店订单*/
    private String directType;
    /*卖家备注*/
    private String sellerRemark;
    /*订单使用UB数量*/
    private BigDecimal ucoinPrise;
    /*其他金额*/
    private BigDecimal otherAmount;
    /*商城ID*/
    private Integer mallId;
    /*U宝支付时间*/
    private Date ucoinPayTime;
    /*1表示从U宝端购买商品，0表示从商城端购买商品*/
    private String isUcoin;
    /*是否评价*/
    private String evaluateFlag;
    /*创建时间*/
    private Date createDate;
    /*送货人*/
    private String sendPerson;
    /*送货电话*/
    private String sendMobile;
    /*自提标志（0表示未自提，1表示自提）*/
    private Boolean selfPick;
    /*节点ID*/
    private Long enterpriseId;
    /*自提码*/
    private String deliveryCode;
    /*是否为代客*/
    private Boolean isValet;
    /*操作人ID*/
    private String operator;
    /*自提点名称*/
    private String selfPickName;


    /******************************************************************************************************************
     *                                                      扩展字段                                                   *
     ******************************************************************************************************************/

    /***/

    /*出库物流信息（用于显示在订单详情页 一个订单的会有N个商品 出库的时候也回有N个物流单）*/
    private List<OrderContainerRelation> orderContainerRelations = new ArrayList<>();
    /*订单商品*/
    private List<OrderGoods> orderGoodsList;
    /*订单自提信息*/
    private OrderPickInfo pickInfo;
    /*客户信息*/
    private CustomerInfo customerInfo;
    /*商家信息*/
    private Business business;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderPrePrice() {
        return orderPrePrice;
    }

    public void setOrderPrePrice(BigDecimal orderPrePrice) {
        this.orderPrePrice = orderPrePrice;
    }

    public BigDecimal getOrderPrePriceOrder() {
        return orderPrePriceOrder;
    }

    public void setOrderPrePriceOrder(BigDecimal orderPrePriceOrder) {
        this.orderPrePriceOrder = orderPrePriceOrder;
    }

    public BigDecimal getJfPrice() {
        return jfPrice;
    }

    public void setJfPrice(BigDecimal jfPrice) {
        this.jfPrice = jfPrice;
    }

    public BigDecimal getOrderOldPrice() {
        return orderOldPrice;
    }

    public void setOrderOldPrice(BigDecimal orderOldPrice) {
        this.orderOldPrice = orderOldPrice;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Long getShoppingAddrId() {
        return shoppingAddrId;
    }

    public void setShoppingAddrId(Long shoppingAddrId) {
        this.shoppingAddrId = shoppingAddrId;
    }

    public String getShippingProvince() {
        return shippingProvince;
    }

    public void setShippingProvince(String shippingProvince) {
        this.shippingProvince = shippingProvince;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingCounty() {
        return shippingCounty;
    }

    public void setShippingCounty(String shippingCounty) {
        this.shippingCounty = shippingCounty;
    }

    public Long getShippingCountyId() {
        return shippingCountyId;
    }

    public void setShippingCountyId(Long shippingCountyId) {
        this.shippingCountyId = shippingCountyId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingPerson() {
        return shippingPerson;
    }

    public void setShippingPerson(String shippingPerson) {
        this.shippingPerson = shippingPerson;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getShippingMobile() {
        return shippingMobile;
    }

    public void setShippingMobile(String shippingMobile) {
        this.shippingMobile = shippingMobile;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public Long getOrderIntegral() {
        return orderIntegral;
    }

    public void setOrderIntegral(Long orderIntegral) {
        this.orderIntegral = orderIntegral;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public BigDecimal getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(BigDecimal expressPrice) {
        this.expressPrice = expressPrice;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public BigDecimal getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(BigDecimal backPrice) {
        this.backPrice = backPrice;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public String getDealerType() {
        return dealerType;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
    }

    public Long getOrderOldCode() {
        return orderOldCode;
    }

    public void setOrderOldCode(Long orderOldCode) {
        this.orderOldCode = orderOldCode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getOrderCancelTime() {
        return orderCancelTime;
    }

    public void setOrderCancelTime(Date orderCancelTime) {
        this.orderCancelTime = orderCancelTime;
    }

    public String getOrderExpressType() {
        return orderExpressType;
    }

    public void setOrderExpressType(String orderExpressType) {
        this.orderExpressType = orderExpressType;
    }

    public String getOrderLinePay() {
        return orderLinePay;
    }

    public void setOrderLinePay(String orderLinePay) {
        this.orderLinePay = orderLinePay;
    }

    public String getOrderNewStatus() {
        return orderNewStatus;
    }

    public void setOrderNewStatus(String orderNewStatus) {
        this.orderNewStatus = orderNewStatus;
    }

    public String getOrderCancelRemark() {
        return orderCancelRemark;
    }

    public void setOrderCancelRemark(String orderCancelRemark) {
        this.orderCancelRemark = orderCancelRemark;
    }

    public String getOrderCargoStatus() {
        return orderCargoStatus;
    }

    public void setOrderCargoStatus(String orderCargoStatus) {
        this.orderCargoStatus = orderCargoStatus;
    }

    public String getOrderMType() {
        return orderMType;
    }

    public void setOrderMType(String orderMType) {
        this.orderMType = orderMType;
    }

    public String getDirectType() {
        return directType;
    }

    public String getSellerRemark() {
        return sellerRemark;
    }

    public void setSellerRemark(String sellerRemark) {
        this.sellerRemark = sellerRemark;
    }

    public BigDecimal getUcoinPrise() {
        return ucoinPrise;
    }

    public void setUcoinPrise(BigDecimal ucoinPrise) {
        this.ucoinPrise = ucoinPrise;
    }

    public BigDecimal getOtherAmount() {
        return otherAmount;
    }

    public void setOtherAmount(BigDecimal otherAmount) {
        this.otherAmount = otherAmount;
    }

    public Integer getMallId() {
        return mallId;
    }

    public void setMallId(Integer mallId) {
        this.mallId = mallId;
    }

    public Date getUcoinPayTime() {
        return ucoinPayTime;
    }

    public void setUcoinPayTime(Date ucoinPayTime) {
        this.ucoinPayTime = ucoinPayTime;
    }

    public String getIsUcoin() {
        return isUcoin;
    }

    public void setIsUcoin(String isUcoin) {
        this.isUcoin = isUcoin;
    }

    public String getEvaluateFlag() {
        return evaluateFlag;
    }

    public void setEvaluateFlag(String evaluateFlag) {
        this.evaluateFlag = evaluateFlag;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getSendPerson() {
        return sendPerson;
    }

    public void setSendPerson(String sendPerson) {
        this.sendPerson = sendPerson;
    }

    public String getSendMobile() {
        return sendMobile;
    }

    public void setSendMobile(String sendMobile) {
        this.sendMobile = sendMobile;
    }

    public Boolean getSelfPick() {
        return selfPick;
    }

    public void setSelfPick(Boolean selfPick) {
        this.selfPick = selfPick;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public Boolean getIsValet() {
        return isValet;
    }

    public void setIsValet(Boolean isValet) {
        this.isValet = isValet;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public List<OrderContainerRelation> getOrderContainerRelations() {
        return orderContainerRelations;
    }

    public void setOrderContainerRelations(List<OrderContainerRelation> orderContainerRelations) {
        this.orderContainerRelations = orderContainerRelations;
    }

    public List<OrderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    public OrderPickInfo getPickInfo() {
        return pickInfo;
    }

    public void setPickInfo(OrderPickInfo pickInfo) {
        this.pickInfo = pickInfo;
    }

    public CustomerInfo getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(CustomerInfo customerInfo) {
        this.customerInfo = customerInfo;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    /**
     * 获取所有商品，拼接成字符串返回
     */
    public String getOrderGoodsListNameString() {
        String result = "";
        List<OrderGoods> orderGoodsListTmp = this.getOrderGoodsList();
        if (orderGoodsListTmp != null) {
            for (OrderGoods p : orderGoodsListTmp) {
                if (result.equals("")) {
                    result = p.getGoodsInfoName() + "(" + p.getGoodsInfoNum() + ")";
                } else {
                    result = result + "；" + p.getGoodsInfoName() + "(" + p.getGoodsInfoNum() + ")";
                }

            }
        }
        return result;
    }


    /**
     * 获取商品总件数
     */
    public int getOrderGoodsListNum() {
        int result = 0;
        List<OrderGoods> orderGoodsListTmp = this.getOrderGoodsList();
        if (orderGoodsListTmp != null) {
            for (OrderGoods p : orderGoodsListTmp) {
                result += p.getGoodsInfoNum();
            }
        }
        return result;
    }

    /**
     * 获取所有快递名称，拼接成字符串返回
     */
    public String getOrderContainerRelationsNameString() {
        String result = "";
        List<OrderContainerRelation> orderContainerRelationsTmp = this.getOrderContainerRelations();
        for (OrderContainerRelation p : orderContainerRelationsTmp) {
            if (StringUtils.isNotEmpty(p.getExpressName())) {
                if (result.equals("")) {
                    result = p.getExpressName();
                } else {
                    result = result + "；" + p.getExpressName();
                }
            }
        }
        return result;
    }

    /**
     * 获取所有快递物流单号，拼接成字符串返回
     */
    public String getOrderContainerRelationsNoString() {
        String result = "";
        List<OrderContainerRelation> orderContainerRelationsTmp = this.getOrderContainerRelations();
        for (OrderContainerRelation p : orderContainerRelationsTmp) {
            if (StringUtils.isNotEmpty(p.getExpressNo())) {
                if (result.equals("")) {
                    result = p.getExpressNo();
                } else {
                    result = result + "；" + p.getExpressNo();
                }
            }
        }
        return result;
    }

    /**
     * 设置DirectType
     */
    public Order setDirectType(String directType) {
        this.directType = directType;
        return this;
    }

    /**
     * 获取SendExpressTIme
     */
    public Date getSendExpressTime() {
        if (this.sendExpressTime != null) {
            return new Date(this.sendExpressTime.getTime());
        }
        return null;
    }

    /**
     * 设置SendExpressTime
     */
    public void setSendExpressTime(Date sendExpressTime) {
        if (sendExpressTime != null) {
            Date tEmp = (Date) sendExpressTime.clone();
            if (tEmp != null) {
                this.sendExpressTime = tEmp;
            }
        }
    }

    /**
     * 获取GetGoodsTime
     */
    public Date getGetGoodsTime() {
        if (this.getGoodsTime != null) {
            return new Date(this.getGoodsTime.getTime());
        }
        return null;
    }

    /**
     * 设置GetGOodsTIme
     */
    public void setGetGoodsTime(Date getGoodsTime) {
        if (getGoodsTime != null) {
            Date tEmp = (Date) getGoodsTime.clone();
            if (tEmp != null) {
                this.getGoodsTime = tEmp;
            }
        }
    }

    public String getAddrString() {
        return shippingProvince + " " + shippingCity + " " + shippingCounty + " " + shippingAddress;
    }

    public String getSelfPickName() {
        return selfPickName;
    }

    public void setSelfPickName(String selfPickName) {
        this.selfPickName = selfPickName;
    }

    /**
     * 自提点信息
     */
    public static class OrderPickInfo {

        private Long enterpriseId;

        private String pickAddress;

        private String enterpriseName;

        public String getPickAddress() {
            return pickAddress;
        }

        public void setPickAddress(String pickAddress) {
            this.pickAddress = pickAddress;
        }

        public Long getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(Long enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public String getEnterpriseName() {
            return enterpriseName;
        }

        public void setEnterpriseName(String enterpriseName) {
            this.enterpriseName = enterpriseName;
        }
    }

    /**
     * 用户信息
     */
    public static class CustomerInfo {

        private Long customerId;

        private String username;

        private String contactPhone;

        private String contactAddr;

        private String fullName;

        public Long getCustomerId() {
            return customerId;
        }

        public void setCustomerId(Long customerId) {
            this.customerId = customerId;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getContactPhone() {
            return contactPhone;
        }

        public void setContactPhone(String contactPhone) {
            this.contactPhone = contactPhone;
        }

        public String getContactAddr() {
            return contactAddr;
        }

        public void setContactAddr(String contactAddr) {
            this.contactAddr = contactAddr;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }
    }

    public static class Business {
        private Long businessId;
        private String businessName;

        public Long getBusinessId() {
            return businessId;
        }

        public void setBusinessId(Long businessId) {
            this.businessId = businessId;
        }

        public String getBusinessName() {
            return businessName;
        }

        public void setBusinessName(String businessName) {
            this.businessName = businessName;
        }
    }
}
