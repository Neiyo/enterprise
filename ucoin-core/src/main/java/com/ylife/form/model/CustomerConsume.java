package com.ylife.form.model;

import com.ylife.ucoin.model.HistoryType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Administrator on 2016/8/8.
 */
public class CustomerConsume {

    String idCard;

    Long customerId;

    Long orderId;

    String fullname;

    Long orderAmount;

    BigDecimal orderPrice;

    Long backAmount;

    BigDecimal backPrice;

    BigDecimal resePrice;

    HistoryType type;

    private  BigDecimal totalConsumePrice;

    private  BigDecimal totalRefundPrice;

    Date createTime;

    Long orderCode;

    public BigDecimal getTotalConsumePrice() {
        return totalConsumePrice;
    }

    public void setTotalConsumePrice(BigDecimal totalConsumePrice) {
        this.totalConsumePrice = totalConsumePrice;
    }

    public BigDecimal getTotalRefundPrice() {
        return totalRefundPrice;
    }

    public void setTotalRefundPrice(BigDecimal totalRefundPrice) {
        this.totalRefundPrice = totalRefundPrice;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Long getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(Long orderAmount) {
        this.orderAmount = orderAmount;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Long getBackAmount() {
        return backAmount;
    }

    public void setBackAmount(Long backAmount) {
        this.backAmount = backAmount;
    }

    public BigDecimal getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(BigDecimal backPrice) {
        this.backPrice = backPrice;
    }

    public BigDecimal getResePrice() {
        return resePrice;
    }

    public void setResePrice(BigDecimal resePrice) {
        this.resePrice = resePrice;
    }

    public HistoryType getType() {
        return type;
    }

    public void setType(HistoryType type) {
        this.type = type;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(Long orderCode) {
        this.orderCode = orderCode;
    }
}
