package com.ylife.inventory.model;

import java.util.Date;
import java.util.List;

public class InventoryBill {

    private Long billId;

    private Long code;

    private Long creatorId;

    private Date createTime;

    private Long currentId;

    private BillType billType;

    private BillStatus billStatus;

    private String reason;

    private Long outId;

    private Long inId;

    private TransferInfo tansferInfo;

    private List<InventoryBillItem> items;

    private List<InventoryBillLog> logs;

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Long currentId) {
        this.currentId = currentId;
    }

    public BillType getBillType() {
        return billType;
    }

    public void setBillType(BillType billType) {
        this.billType = billType;
    }

    public BillStatus getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(BillStatus billStatus) {
        this.billStatus = billStatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Long getOutId() {
        return outId;
    }

    public void setOutId(Long outId) {
        this.outId = outId;
    }

    public Long getInId() {
        return inId;
    }

    public void setInId(Long inId) {
        this.inId = inId;
    }

    public TransferInfo getTansferInfo() {
        return tansferInfo;
    }

    public void setTansferInfo(TransferInfo tansferInfo) {
        this.tansferInfo = tansferInfo;
    }

    public List<InventoryBillItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryBillItem> items) {
        this.items = items;
    }

    public List<InventoryBillLog> getLogs() {
        return logs;
    }

    public void setLogs(List<InventoryBillLog> logs) {
        this.logs = logs;
    }

    public static class TransferInfo {

        private String inName;

        private String outName;

        private String creatorName;

        private String currentName;

        public String getInName() {
            return inName;
        }

        public void setInName(String inName) {
            this.inName = inName;
        }

        public String getOutName() {
            return outName;
        }

        public void setOutName(String outName) {
            this.outName = outName;
        }

        public String getCreatorName() {
            return creatorName;
        }

        public void setCreatorName(String creatorName) {
            this.creatorName = creatorName;
        }

        public String getCurrentName() {
            return currentName;
        }

        public void setCurrentName(String currentName) {
            this.currentName = currentName;
        }
    }


}