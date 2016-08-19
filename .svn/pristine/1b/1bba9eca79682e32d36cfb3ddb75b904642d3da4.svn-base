package com.ylife.product.service.impl;

import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.product.mapper.GoodsInfoMapper;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.model.GoodsInfo;
import com.ylife.product.service.GoodsInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/27.
 */
@Service
public class GoodsInfoServiceImpl implements GoodsInfoService {

    @Resource
    GoodsInfoMapper goodsInfoMapper;

    @Override
    public Page<GoodsManagerResult> getGoods(String goodsInfoName, String goodsInfoItemNo, String goodsInfoAdded, Long brandId, Long typeId, Long thirdId, Pageable pageable) {
        List<GoodsManagerResult> goodsManagerResults = goodsInfoMapper.selectGoods(goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, pageable);

        int totalElements = goodsInfoMapper.countGoodsResult(goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId);
        Page<GoodsManagerResult> page = new PageImpl<>(pageable, totalElements, goodsManagerResults);
        return page;
    }

    @Override
    public GoodsInfo getById(Long goodsInfoId) {
        return goodsInfoMapper.selectByPrimaryKey(goodsInfoId);
    }

/* @Override
    public Page<GoodsManagerResult> getValetGoods(String goodsInfoName, Float lowPrice, Float highPrice) {
        List<GoodsManagerResult> goodsManagerResults=goodsInfoMapper.selectValetGoods(goodsInfoName,lowPrice,highPrice);
        int totalElements=goodsInfoMapper.countValeGoodsResult(lowPrice,highPrice,goodsInfoName);
        Page<GoodsManagerResult> page=new PageImpl<>(pageable,totalElements,goodsManagerResults);
        return page;
    }*/

    @Deprecated
    private List<GoodsManagerResult> removeRepeate(List<GoodsManagerResult> listin) {
        Map<Long, GoodsManagerResult> resultMap = new HashMap<>();
        for (GoodsManagerResult result : listin) {
            GoodsManagerResult goodsResult = resultMap.get(result.getGoodsInfoId());
            if (goodsResult != null) {
                goodsResult.setSpecString(goodsResult.getSpecString() + "," + result.getGoodsSpecification() + ":" + result.getGoodsSpecValue());
            } else {
                result.setSpecString(result.getGoodsSpecification() + ":" + result.getGoodsSpecValue());
                result.setGoodsSpecification(null);
                result.setGoodsSpecValue(null);
                resultMap.put(result.getGoodsInfoId(), result);
            }
        }
        List<GoodsManagerResult> listout = new ArrayList<>();
        for (GoodsManagerResult result : resultMap.values()) {
            listout.add(result);
        }
        return listout;
    }

    @Override
    public List<GoodsManagerResult> getBrands() {
        return goodsInfoMapper.selectBrand();
    }

    @Override
    public List<GoodsManagerResult> getType() {
        return goodsInfoMapper.selectType();
    }

    @Override
    public List<GoodsManagerResult> getThirdName() {
        return goodsInfoMapper.selectThirdName();
    }

    @Override
    public List<GoodsManagerResult> getBrandTypeThird() {
        return goodsInfoMapper.selectBrandTypeThird();
    }

    @Override
    public int unshelves(long goodsInfoId) {
        return goodsInfoMapper.unshelves(goodsInfoId);
    }

    @Override
    public int shelves(long goodsInfoId) {
        return goodsInfoMapper.shelves(goodsInfoId);
    }
}
