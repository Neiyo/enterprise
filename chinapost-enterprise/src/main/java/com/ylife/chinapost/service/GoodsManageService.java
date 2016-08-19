package com.ylife.chinapost.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/16.
 * <p/>
 * 商品管理服务。
 */
public interface GoodsManageService {

    /**
     * 商品管理的货品列表
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param goodsInfoAdded
     * @param brandId
     * @param typeId
     * @param thirdId
     * @param pageable
     * @return
     */
    Page<GoodsManagerResult> getGoods(String goodsInfoName, String goodsInfoItemNo,
                                      String goodsInfoAdded, Long brandId,
                                      Long typeId, Long thirdId, Pageable pageable);


    List<GoodsManagerResult> selectBrand();

    List<GoodsManagerResult> selectType();

    List<GoodsManagerResult> selectThirdName();

    List<GoodsManagerResult> getBrandTypeThird();

    GoodsManagerResult getByGoodsInfoId(long goodsInfoId);


    /**
     * 导出excel
     * @return
     */

    Boolean exportExcel();


}
