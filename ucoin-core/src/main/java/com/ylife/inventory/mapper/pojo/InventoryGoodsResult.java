package com.ylife.inventory.mapper.pojo;

/**
 * Created by Administrator on 2016/4/21.
 */
public class InventoryGoodsResult {
    //货品id
    private Long goodsInfoId;
    //货品图片
    private String goodsImg;
    //货品名称
    private String goodsInfoName;
    //货品规格名称
    private String goodsSpecification;
    //货品规格值
    private String goodsSpecValue;
    //规格字符串
    private String specString;
    //货品编号
    private String goodsNumber;
    //库存量
    private int inventory;
    //可用库存
    private int available;
    //货品品牌
    private String goodsBrand;

    private long brandId;
    //货品所属商家
    private String goodsMerchants;

    private long thirdId;
    //货品库存预警值
    private int warning;

    private Long enterpriseId;

    private String sumAvailable;

    private String sumInventory;

    private String accountName;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getSumAvailable() {
        return sumAvailable;
    }

    public void setSumAvailable(String sumAvailable) {
        this.sumAvailable = sumAvailable;
    }

    public String getSumInventory() {
        return sumInventory;
    }

    public void setSumInventory(String sumInventory) {
        this.sumInventory = sumInventory;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public long getThirdId() {
        return thirdId;
    }

    public void setThirdId(long thirdId) {
        this.thirdId = thirdId;
    }

    public int getWarning() {
        return warning;
    }

    public void setWarning(int warning) {
        this.warning = warning;
    }

    public String getSpecString() {
        return specString;
    }

    public void setSpecString(String specString) {
        this.specString = specString;
    }

    public String getGoodsSpecValue() {
        return goodsSpecValue;
    }

    public void setGoodsSpecValue(String goodsSpecValue) {
        this.goodsSpecValue = goodsSpecValue;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
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

    public String getGoodsNumber() {
        return goodsNumber;
    }

    public void setGoodsNumber(String goodsNumber) {
        this.goodsNumber = goodsNumber;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getGoodsMerchants() {
        return goodsMerchants;
    }

    public void setGoodsMerchants(String goodsMerchants) {
        this.goodsMerchants = goodsMerchants;
    }
}
