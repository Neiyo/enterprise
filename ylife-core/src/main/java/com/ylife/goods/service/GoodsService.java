/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service;

import com.ylife.data.page.PageBean;
import com.ylife.goods.model.Goods;
import com.ylife.goods.model.GoodsMoifiedVo;
import com.ylife.goods.model.GoodsOpenSpecValueVo;
import com.ylife.goods.model.GoodsSearchBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 商品Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午5:17:44
 * @version 1.0
 */
public interface GoodsService {

    /**
     * 查询商品是否下架
     * */
    String selectCheckGoods(int goodsId);

    /**
     * 根据pagebean查询GoodsListVo
     * 
     * @param pb
     *            分页辅助对象 {@link com.ysh.util.PageBean}
     * @return 封装好的分页对象
     */
    PageBean queryListVo(PageBean pb, String isThird);

    /**
     * 根据pagebean查询GoodsListVo
     *
     * @param pb
     *            分页辅助对象 {@link com.ysh.util.PageBean}
     * @return 封装好的分页对象
     */
    PageBean queryListVoBrandCate(PageBean pb, String isThird);

    /**
     * 根据分类ID查询相关商品
     * 
     * @param catId
     *            商品分类ID {@link java.lang.Long }
     * @return 查询到的商品列表 {@link java.util.List}
     */
    List<Object> queryGoodsListByCatId(Long catId);

    /**
     * 第三方上传商品时查询关联商品
     * 
     * @param pb
     * @param searchBean
     * @return
     */
    PageBean searchThirdBySearchBeanAndPageBean(PageBean pb,
            GoodsSearchBean searchBean);

    /**
     * 根据商品编号查询商品是否可用
     * 
     * @param goodsNo
     * @return 可用 返回true 不可用返回false
     */
    boolean queryCountByGoodsNo(String goodsNo);

    /**
     * 查询所有的商品列表进行导出
     * 
     * @return 查询到的商品列表
     */
    List<Object> queryAllGoodsForExport(GoodsSearchBean searchBean);

    /**
     * 根据商品ID数组查询商品列表
     * 
     * @param goodsIds
     *            商品ID数组
     * @return 查询到的集合
     */
    List<Object> queryGoodsListVoListForExportByGoodsIds(Long[] goodsIds);

    /**
     * 查询所有的商品信息用于导出
     * 
     * @return 查询到的集合
     */
    List<Object> queryAllGoodsForExport(String isThird);

    /**
     * 获取商品审核开关标记
     * 
     * @return
     */
    String selectAuditAction();

    /**
     * 根据条件查询商品信息
     * 
     * @param pb
     * @param thirdId
     * @param productNo
     * @return
     */
    PageBean queryGoodsForCoupon(PageBean pb, Long thirdId, String productNo);

    /**
     * 根据ID获取商品信息
     * 
     * @param goodsId
     * @return
     */
    Goods queryGoodsByGoodsId(Long goodsId);


    /**
     * 批量下架商品
     *
     * @param goodsIds
     *            需要下架的商品的ID的集合
     * @return 操作结果
     */
    int batchDown(Long[] goodsIds, String username);

    /**
     * 批量上架商品
     *
     * @param goodsIds
     * @return
     */
    int batchUp(Long[] goodsIds, String username);

    /**
     * 批量修改库存
     *用于后台批量修改库存
     *
     * @auhor houyichang  2015/8/24
     * @param goodsId
     * */
    int batchUpdateStock(Long[] goodsId,String stock);

}
