package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.service.GoodsManageService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.product.mapper.GoodsInfoMapper;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.utils.ExcelUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/28.
 */
@Service
public class GoodsManagerSrerviceImpl implements GoodsManageService {

    @Resource
    private GoodsInfoService goodsInfoService;
    @Resource
    private GoodsInfoMapper goodsInfoMapper;


    @Override
    public Page<GoodsManagerResult> getGoods(String goodsInfoName, String goodsInfoItemNo, String goodsInfoAdded,
                                             Long brandId, Long typeId, Long thirdId, Pageable pageable) {
        return goodsInfoService.getGoods(goodsInfoName,goodsInfoItemNo,goodsInfoAdded,brandId,typeId,thirdId,pageable);
    }

    @Override
    public List<GoodsManagerResult> selectBrand() {
        return goodsInfoMapper.selectBrand();
    }

    @Override
    public List<GoodsManagerResult> selectType() {
        return goodsInfoMapper.selectType();
    }

    @Override
    public List<GoodsManagerResult> selectThirdName() {
        return goodsInfoMapper.selectThirdName();
    }

    @Override
    public List<GoodsManagerResult> getBrandTypeThird() {
        return null;
    }

    @Override
    public GoodsManagerResult getByGoodsInfoId(long goodsInfoId) {
        return goodsInfoMapper.selectByGoodsInfoId(goodsInfoId);
    }

    @Override
    public Boolean exportExcel() {
        return null;
    }
}
