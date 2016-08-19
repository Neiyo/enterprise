package com.ylife.inventory.model;

public class InventoryBillItem {

    private Long itemId;

    private Long billId;

    private Long goodsInfoId;

    private Integer amount;

    private Integer checkedAmount;

    private ItemGoodsInfo info;

    public ItemGoodsInfo getInfo() {
        return info;
    }

    public void setInfo(ItemGoodsInfo info) {
        this.info = info;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public Long getBillId() {
        return billId;
    }

    public void setBillId(Long billId) {
        this.billId = billId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getCheckedAmount() {
        return checkedAmount;
    }

    public void setCheckedAmount(Integer checkedAmount) {
        this.checkedAmount = checkedAmount;
    }

    public static class ItemGoodsInfo {
        private Long goodsInfoId;

        private String goodsInfoName;

        private String goodsSpecification;
        //货品规格值
        private String goodsSpecValue;
        //规格字符串
        private String specString;

        private String goodsInfoItemNo;

        private Integer inventory;

        private Integer available;

        public Long getGoodsInfoId() {
            return goodsInfoId;
        }

        public void setGoodsInfoId(Long goodsInfoId) {
            this.goodsInfoId = goodsInfoId;
        }

        public Integer getInventory() {
            return inventory;
        }

        public void setInventory(Integer inventory) {
            this.inventory = inventory;
        }

        public Integer getAvailable() {
            return available;
        }

        public void setAvailable(Integer available) {
            this.available = available;
        }

        public String getGoodsInfoName() {
            return goodsInfoName;
        }

        public void setGoodsInfoName(String goodsInfoName) {
            this.goodsInfoName = goodsInfoName;
        }


        public String getGoodsSpecification() {
            return goodsSpecification;
        }

        public void setGoodsSpecification(String goodsSpecification) {
            this.goodsSpecification = goodsSpecification;
        }

        public String getGoodsSpecValue() {
            return goodsSpecValue;
        }

        public void setGoodsSpecValue(String goodsSpecValue) {
            this.goodsSpecValue = goodsSpecValue;
        }

        public String getSpecString() {
            return specString;
        }

        public void setSpecString(String specString) {
            this.specString = specString;
        }

        public String getGoodsInfoItemNo() {
            return goodsInfoItemNo;
        }

        public void setGoodsInfoItemNo(String goodsInfoItemNo) {
            this.goodsInfoItemNo = goodsInfoItemNo;
        }
    }
}