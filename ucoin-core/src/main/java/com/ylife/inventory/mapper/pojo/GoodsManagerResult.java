package com.ylife.inventory.mapper.pojo;


import com.ylife.product.model.GoodsImage;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2016/4/27.
 */
/*库存选品封装类*/
public class GoodsManagerResult {

    //商品详情介绍
    private String goodsDetailDesc;
    //货品id
    private Long goodsInfoId;
    // 货品图片
    private String goodsInfoImgId;
    //货品名称
    private String goodsInfoName;
    //货品规格名称
    private String goodsSpecification;
    //货品规格值
    private String goodsSpecValue;
    //规格字符串
    private String specString;
    //货品编号
    private String goodsInfoItemNo;
    //商城价
    private BigDecimal goodsInfoPreferPrice;
    //是否上架   0:下架   1:上架   2:未采集   3:线下
    private String goodsInfoAdded;
    //分类
    private String goodsInfoTypeName;
    //货品品牌
    private String goodsBrand;
    //所属商家
    private String thirdName;
    //品牌id
    private long brandId;
    //所属商家（第三方店铺）id
    private long thirdId;
    //第三方店铺id
    private long storeId;
    //第三方店铺名称
    private String storeName;
    //种类id
    private long typeId;

    private BigDecimal lowPrice;

    private BigDecimal highPrice;

    private List<GoodsImage> images;

    public String getGoodsDetailDesc() {
        return goodsDetailDesc;
    }

    public void setGoodsDetailDesc(String goodsDetailDesc) {
        this.goodsDetailDesc = goodsDetailDesc;
    }

    public BigDecimal getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(BigDecimal lowPrice) {
        this.lowPrice = lowPrice;
    }

    public BigDecimal getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(BigDecimal highPrice) {
        this.highPrice = highPrice;
    }

    public List<GoodsImage> getImages() {
        return images;
    }

    public void setImages(List<GoodsImage> images) {
        this.images = images;
    }


    public long getStoreId() {
        return storeId;
    }

    public void setStoreId(long storeId) {
        this.storeId = storeId;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
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

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public String getGoodsInfoImgId() {
        return goodsInfoImgId;
    }

    public void setGoodsInfoImgId(String goodsInfoImgId) {
        this.goodsInfoImgId = goodsInfoImgId;
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

    public BigDecimal getGoodsInfoPreferPrice() {
        return goodsInfoPreferPrice;
    }

    public void setGoodsInfoPreferPrice(BigDecimal goodsInfoPreferPrice) {
        this.goodsInfoPreferPrice = goodsInfoPreferPrice;
    }

    public String getGoodsInfoAdded() {
        return goodsInfoAdded;
    }

    public void setGoodsInfoAdded(String goodsInfoAdded) {
        this.goodsInfoAdded = goodsInfoAdded;
    }

    public String getGoodsInfoTypeName() {
        return goodsInfoTypeName;
    }

    public void setGoodsInfoTypeName(String goodsInfoTypeName) {
        this.goodsInfoTypeName = goodsInfoTypeName;
    }

    public String getGoodsBrand() {
        return goodsBrand;
    }

    public void setGoodsBrand(String goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }
}
