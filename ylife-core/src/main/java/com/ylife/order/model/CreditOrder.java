package com.ylife.order.model;

import com.ylife.client.bean.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 退单
 */
public class CreditOrder {

    private Long backOrderId;

    private Long backOrderCode;

    private Long orderId;

    private Date backTime;

    private String backRemark;

    private BigDecimal backPrice;

    private CreditOrderStatus backCheck;

    private String backDelFlag;

    private String backRealName;

    private String backBankName;

    private String backBankAccount;

    private String dealerType;

    private Long businessId;

    private String businessName;

    private String authorName;

    private Date authorTime;

    private String authorLog;

    private String customerMsg;

    private String backCollectPhone;

    private String backCollectAddress;

    private String backCollectPerson;

    private Long backStatus;

    private String isBack;

    private CreditOrderReason backReason;
    //申请凭据 1 有发票 2 有质检报告 3 没有任何凭据
    private CredentialType applyCredentials;

    private String uploadDocuments;

    private String backWay;

    private String backGidGsum;

    private Long customerId;

    private Integer mallId;

    /**
     * *************************************扩展字段******************************************
     */

    private List<OrderProduct> products;

    private List<BackOrderGoods> backOrderGoods;

    private List<CreditOrderLog> logs;

    private OriginalOrder order;

    private AccountInfo accountInfo;

    private Business business;

    private List imageList;

    private List<Logistics> logisticses;


    public Long getBackOrderId() {
        return backOrderId;
    }

    public void setBackOrderId(Long backOrderId) {
        this.backOrderId = backOrderId;
    }

    public Long getBackOrderCode() {
        return backOrderCode;
    }

    public void setBackOrderCode(Long backOrderCode) {
        this.backOrderCode = backOrderCode;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Date getBackTime() {
        return backTime;
    }

    public void setBackTime(Date backTime) {
        this.backTime = backTime;
    }

    public String getBackRemark() {
        return backRemark;
    }

    public void setBackRemark(String backRemark) {
        this.backRemark = backRemark == null ? null : backRemark.trim();
    }

    public BigDecimal getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(BigDecimal backPrice) {
        this.backPrice = backPrice;
    }

    public CreditOrderStatus getBackCheck() {
        return backCheck;
    }

    public void setBackCheck(CreditOrderStatus backCheck) {
        this.backCheck = backCheck;
    }

    public String getBackDelFlag() {
        return backDelFlag;
    }

    public void setBackDelFlag(String backDelFlag) {
        this.backDelFlag = backDelFlag == null ? null : backDelFlag.trim();
    }

    public String getBackRealName() {
        return backRealName;
    }

    public void setBackRealName(String backRealName) {
        this.backRealName = backRealName == null ? null : backRealName.trim();
    }

    public String getBackBankName() {
        return backBankName;
    }

    public void setBackBankName(String backBankName) {
        this.backBankName = backBankName == null ? null : backBankName.trim();
    }

    public String getBackBankAccount() {
        return backBankAccount;
    }

    public void setBackBankAccount(String backBankAccount) {
        this.backBankAccount = backBankAccount == null ? null : backBankAccount.trim();
    }

    public String getDealerType() {
        return dealerType;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType == null ? null : dealerType.trim();
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName == null ? null : authorName.trim();
    }

    public Date getAuthorTime() {
        return authorTime;
    }

    public void setAuthorTime(Date authorTime) {
        this.authorTime = authorTime;
    }

    public String getAuthorLog() {
        return authorLog;
    }

    public void setAuthorLog(String authorLog) {
        this.authorLog = authorLog == null ? null : authorLog.trim();
    }

    public String getBackCollectPhone() {
        return backCollectPhone;
    }

    public void setBackCollectPhone(String backCollectPhone) {
        this.backCollectPhone = backCollectPhone == null ? null : backCollectPhone.trim();
    }

    public String getBackCollectAddress() {
        return backCollectAddress;
    }

    public void setBackCollectAddress(String backCollectAddress) {
        this.backCollectAddress = backCollectAddress == null ? null : backCollectAddress.trim();
    }

    public String getBackCollectPerson() {
        return backCollectPerson;
    }

    public void setBackCollectPerson(String backCollectPerson) {
        this.backCollectPerson = backCollectPerson == null ? null : backCollectPerson.trim();
    }

    public Long getBackStatus() {
        return backStatus;
    }

    public void setBackStatus(Long backStatus) {
        this.backStatus = backStatus;
    }

    public String getIsBack() {
        return isBack;
    }

    public void setIsBack(String isBack) {
        this.isBack = isBack == null ? null : isBack.trim();
    }

    public CreditOrderReason getBackReason() {
        return backReason;
    }

    public void setBackReason(CreditOrderReason backReason) {
        this.backReason = backReason;
    }

    public CredentialType getApplyCredentials() {
        return applyCredentials;
    }

    public void setApplyCredentials(CredentialType applyCredentials) {
        this.applyCredentials = applyCredentials;
    }

    public String getUploadDocuments() {
        return uploadDocuments;
    }

    public void setUploadDocuments(String uploadDocuments) {
        this.uploadDocuments = uploadDocuments == null ? null : uploadDocuments.trim();
    }

    public String getBackWay() {
        return backWay;
    }

    public void setBackWay(String backWay) {
        this.backWay = backWay == null ? null : backWay.trim();
    }

    public String getBackGidGsum() {
        return backGidGsum;
    }

    public void setBackGidGsum(String backGidGsum) {
        this.backGidGsum = backGidGsum == null ? null : backGidGsum.trim();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getMallId() {
        return mallId;
    }

    public void setMallId(Integer mallId) {
        this.mallId = mallId;
    }

    public List<CreditOrderLog> getLogs() {
        return logs;
    }

    public void setLogs(List<CreditOrderLog> logs) {
        this.logs = logs;
    }

    public OriginalOrder getOrder() {
        return order;
    }

    public void setOrder(OriginalOrder order) {
        this.order = order;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getCustomerMsg() {
        return customerMsg;
    }

    public void setCustomerMsg(String customerMsg) {
        this.customerMsg = customerMsg;
    }

    public List<BackOrderGoods> getBackOrderGoods() {
        return backOrderGoods;
    }

    public void setBackOrderGoods(List<BackOrderGoods> backOrderGoods) {
        this.backOrderGoods = backOrderGoods;
    }

    public List getImageList() {
        if (this.uploadDocuments != null && !"".equals(this.uploadDocuments)) {
            imageList = new ArrayList();
            String[] imgs = this.uploadDocuments.split(",");
            Collections.addAll(imageList, imgs);
        }

        return imageList;
    }

    public List<OrderProduct> getProducts() {
        return products;
    }

    public void setProducts(List<OrderProduct> products) {
        this.products = products;
    }

    public List<Logistics> getLogisticses() {
        return logisticses;
    }

    public void setLogisticses(List<Logistics> logisticses) {
        this.logisticses = logisticses;
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

    public static class AccountInfo{
        private Long enterpriseId;
        String accountName;

        public Long getEnterpriseId() {
            return enterpriseId;
        }

        public void setEnterpriseId(Long enterpriseId) {
            this.enterpriseId = enterpriseId;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }
    }
    public static class OriginalOrder {
        private Long orderId;

        private Long orderCode;

        private BigDecimal orderPrice;

        private BigDecimal orderOldPrice;

        private BigDecimal expressPrice;

        private Boolean selfPick;

        private Boolean isValet;

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

        public BigDecimal getOrderOldPrice() {
            return orderOldPrice;
        }

        public void setOrderOldPrice(BigDecimal orderOldPrice) {
            this.orderOldPrice = orderOldPrice;
        }

        public BigDecimal getExpressPrice() {
            return expressPrice;
        }

        public void setExpressPrice(BigDecimal expressPrice) {
            this.expressPrice = expressPrice;
        }

        public Boolean getSelfPick() {
            return selfPick;
        }

        public void setSelfPick(Boolean selfPick) {
            this.selfPick = selfPick;
        }

        public Boolean getIsValet() {
            return isValet;
        }

        public void setIsValet(Boolean isValet) {
            this.isValet = isValet;
        }
    }
}