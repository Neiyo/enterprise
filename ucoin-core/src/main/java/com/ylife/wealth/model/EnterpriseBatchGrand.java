package com.ylife.wealth.model;

import java.math.BigDecimal;
import java.util.Date;

public class EnterpriseBatchGrand {

    private Long id;

    private Long enterpriseId;

    private Long code;

    private BigDecimal fee;

    private String sendType;

    private Date createTime;

    private String remark;

    private Integer ucoinCount;

    private String grandType;

    private BigDecimal ucoinPrice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getUcoinCount() {
        return ucoinCount;
    }

    public void setUcoinCount(Integer ucoinCount) {
        this.ucoinCount = ucoinCount;
    }

    public String getGrandType() {
        return grandType;
    }

    public void setGrandType(String grandType) {
        this.grandType = grandType == null ? null : grandType.trim();
    }

    public BigDecimal getUcoinPrice() {
        return ucoinPrice;
    }

    public void setUcoinPrice(BigDecimal ucoinPrice) {
        this.ucoinPrice = ucoinPrice;
    }
}