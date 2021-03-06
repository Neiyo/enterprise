/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import com.ylife.client.bean.GoodsProductDetailVo;
import com.ylife.client.bean.GoodsProductReleSpecVo;
import com.ylife.client.bean.SyncStatus;
import com.ylife.client.mapper.SyncMapper;
import com.ylife.client.mapper.SyncStatusMapper;
import com.ylife.client.service.OpenGoodsProductService;
import com.ylife.goods.mapper.*;
import com.ylife.goods.model.*;
import com.ylife.goods.service.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 货品信息Service实现
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月27日 下午4:31:46
 */
@Service
public class GoodsProductServiceImpl implements GoodsProductService {

    private static final String CATID = "catId";
    private static final String ROWCOUNT = "rowCount";
    @Resource
    private GoodsProductMapper goodsProductMapper;
    @Resource
    private GoodsCateService cateService;
    @Resource
    private GoodsReleTagMapper goodsReleTagMapper;
    @Resource
    private GoodsBrandService goodsBrandService;
    @Resource
    private GoodsReleExpandParamMapper expandParamMapper;
    @Resource
    private GoodsReleParamMapper goodsReleParamMapper;
    @Resource
    private GoodsRelatedGoodsMapper goodsRelatedGoodsMapper;
    @Resource
    private ProductWareService productWareService;
    // 货品关联规格值Service
    @Resource
    private GoodsProductReleSpecService goodsProductReleSpecService;
    //同步货品
    @Resource
    private OpenGoodsProductService openGoodsProductService;

    //商品mapper
    @Resource
    private GoodsMapper goodsMapper;
    //货品图片mapper
    @Resource
    private GoodsImageMapper goodsImageMapper;
    //属性mapper
    @Resource
    private GoodsProductReleSpecMapper goodsProductReleSpecMapper;
    //属性mapper
    @Resource
    private GoodsOpenSpecMapper goodsOpenSpecMapper;
    @Resource
    private GoodsOpenSpecValueMapper goodsOpenSpecValueMapper;
    //同步时间
    @Resource
    private SyncMapper syncMapper;
    @Resource
    private SyncStatusMapper syncStatusMapper;

    Logger logger=Logger.getLogger(this.getClass());

    /**
     * 根据商品ID查询货品列表
     *
     * @param goodsId 商品ID
     * @return 货品列表
     */
    public List<Object> queryAllProductListByGoodsId(Long goodsId) {
        return this.goodsProductMapper.queryProductByGoodsId(goodsId);
    }

    /**
     * 根据货品ID查询货品信息
     *
     * @param productId 货品信息ID
     * @return 查询到的货品信息实体
     */
    public GoodsProductVo queryProductByProductId(Long productId) {
        return this.goodsProductMapper.queryPrductByProductId(productId);
    }

    /**
     * 根据分类ID查询货品信息的集合
     *
     * @param catId    分类ID
     * @param rowCount 查询的行数
     * @return 查询到的集合
     */
    public List<GoodsProduct> queryTopSalesByCatIds(Long catId, Integer rowCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CATID, catId);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.queryTopSalesInfoByCatIds(map);
        } finally {
            map = null;
        }
    }

    /**
     * 查询最近一月该分类下的热销商品
     *
     * @param catId    分类ID {@link Long}
     * @param rowCount 查询行数 {@link Integer}
     * @return 查询到的商品集合 {@link List}
     */
    public List<GoodsProduct> queryHotSalesTopSix(Long catId, Integer rowCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CATID, catId);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.queryHotSalesByCatId(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据分类ID查询最新上架的货品
     *
     * @param catId    分类ID
     * @param rowCount 查询的行数
     * @return 查询到的货品
     */
    public List<GoodsProduct> queryTopNewByCatIds(Long catId, Integer rowCount) {
        GoodsCateVo cate = this.cateService.queryCateById(catId);
        Map<String, Object> map = new HashMap<String, Object>();
        List<Long> catIds = new ArrayList<Long>();
        try {
            cateService.calcAllSonCatIds(cate, catIds);
            map.put("catIds", catIds);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.queryTopNewInfoByCatIds(map);
        } finally {
            cate = null;
            map = null;
            catIds = null;
        }
    }

    @Override
    public List<GoodsProduct> queryProductByUcoinAreaId(Long ucoinAreaId, Long distinctId) {
        List<GoodsProduct> productList = goodsProductMapper.queryProductByUcoinAreaId(ucoinAreaId);
        for (GoodsProduct item : productList) {
            // 如果不是第三方商品就去查分仓库存
            if ("0".equals(item.getIsThird()) && null != distinctId && distinctId > 0) {
                /* 根据选择的地区查询库存及价格信息 */
                ProductWare productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(item.getGoodsInfoId(), distinctId);
                    /* 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
                if (null != productWare) {
                    item.setGoodsInfoStock(productWare.getWareStock());
                } else {
                    item.setGoodsInfoStock(0L);
                }
            }
        }
        return productList;
    }

    /**
     * 根据货品ID查询
     *
     * @param productId  货品ID {@link java.lang.Long }
     * @param distinctId 选择的地区ID
     * @return 查询到的组装商品详细页的Bean
     */
    public GoodsDetailBean queryDetailBeanByProductId(Long productId, Long distinctId, Long subjectId) {
        // 商品详情
        GoodsDetailBean detailBean = new GoodsDetailBean();
        List<GoodsProduct> relaProduct = new ArrayList<GoodsProduct>();
//        GoodsProductVo productVo;
//        ProductWare productWare;
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("productId", productId);
            map.put("subjectId", subjectId);
            // 获取货品的详细信息
            detailBean.setProductVo(this.goodsProductMapper.queryDetailByParam(map));
            if (detailBean.getProductVo() == null || "0".equals(detailBean.getProductVo().getGoodsInfoAdded())) {
                return null;
            }
            /*// 判断商品是否已经下架和在手机端不显示
            if (detailBean.getProductVo() == null || "0".equals(detailBean.getProductVo().getShowMobile()) || "0".equals(detailBean.getProductVo().getGoodsInfoAdded())) {
                return null;
            }*/
            if (null != detailBean.getProductVo()) {
                // 如果查询到的货品信息不为空就查询所属的分类信息
                detailBean.setCateVo(this.cateService.queryCateAndParCateByCatId(detailBean.getProductVo().getGoods().getCatId()));
                // 查询商品关联的标签
                detailBean.setTags(goodsReleTagMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询品牌
                detailBean.setBrand(goodsBrandService.queryBrandById(detailBean.getProductVo().getGoods().getBrandId()));
                // 查询商品关联的扩展属性
                detailBean.setExpandPrams(expandParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询商品关联的详细参数
                detailBean.setParam(goodsReleParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询商品关联的货品
                List<GoodsRelatedGoodsVo> relaProducts = this.goodsRelatedGoodsMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId());
                // 如果关联的不为空
                if (null != relaProducts && !relaProducts.isEmpty()) {
                    for (int i = 0; i < relaProducts.size(); i++) {
                        relaProduct.add(this.goodsProductMapper.queryFirstProductByGoodsId(relaProducts.get(i).getReleatedGoods().get(0).getGoodsId()));
                    }
                    detailBean.setReleProductList(relaProduct);
                }
                // 查询组合集合
                //detailBean.setGroupVos(this.goodsPub.getGoodsGroupService().queryGroupVoListWithOutProductId(detailBean.getProductVo().getGoodsInfoId()));
            }
            // 如果不是第三方商品就去查分仓库存
//            if ("0".equals(detailBean.getProductVo().getIsThird()) && null != distinctId && distinctId > 0) {
//                /* 根据选择的地区查询库存及价格信息 */
//                productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(productId, distinctId);
//                    /* 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
//                if (null != productWare) {
//                    productVo = detailBean.getProductVo();
//                    productVo.setGoodsInfoPreferPrice(productWare.getWarePrice());
//                    productVo.setGoodsInfoStock(productWare.getWareStock());
//                    detailBean.setProductVo(productVo);
//                } else {
//                    productVo = detailBean.getProductVo();
//                    productVo.setGoodsInfoStock(0L);
//                    detailBean.setProductVo(productVo);
//                }
//            }
            /* 查询所有的关联的服务支持 */
            /*
             * detailBean.setSuppList(this.goodsProductSuppService.
             * queryAllSuppVoByProId
             * (detailBean.getProductVo().getGoodsInfoId()));
             */
            return detailBean;
        } finally {
//            productVo = null;
//            productWare = null;
            detailBean = null;
        }
    }

    /**
     * 保存商品咨询
     *
     * @param type    咨询类型
     * @param comment 咨询内容
     * @return 是否保存成功
     */
    public int saveProductCommentAsk(int type, String comment, Long custId, Long productId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("type", type);
            map.put("comment", comment);
            map.put("custId", custId);
            map.put("productId", productId);
            return this.goodsProductMapper.saveAskComment(map);
        } finally {
            map = null;
        }
    }

    /**
     * 进行对比
     *
     * @param productIds 需要对比的商品列表 {@link List}
     * @return 需要对比的货品详细信息的集合 {@link List}
     */
    public List<GoodsDetailBean> execCompProduct(List<Long> productIds) {
        List<GoodsDetailBean> lists = new ArrayList<GoodsDetailBean>();
        try {
            if (null != productIds && !productIds.isEmpty()) {
                for (int i = 0; i < productIds.size(); i++) {
                    GoodsDetailBean detailBean = new GoodsDetailBean();
                    try {
                        detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productIds.get(i)));
                        if (null != detailBean.getProductVo()) {
                            // 如果查询到的货品信息不为空就查询所属的分类信息
                            detailBean.setCateVo(this.cateService.queryCateAndParCateByCatId(detailBean.getProductVo().getGoods().getCatId()));
                            // 查询商品关联的标签
                            detailBean.setTags(goodsReleTagMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                            // 查询品牌
                            detailBean.setBrand(goodsBrandService.queryBrandById(detailBean.getProductVo().getGoods().getBrandId()));
                            // 查询商品关联的扩展属性
                            detailBean.setExpandPrams(expandParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                            // 查询商品关联的详细参数
                            detailBean.setParam(goodsReleParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                        }
                        lists.add(detailBean);
                    } finally {
                        detailBean = null;
                    }
                }
            }
            return lists;
        } finally {
            lists = null;
        }
    }

    /**
     * 减少库存
     *
     * @param list 计算库存辅助Bean集合
     * @return 更新的行数
     */
    public int minStock(List<CalcStockUtil> list) {
        Integer count = 0;
        CalcStockUtil stoUtil = null;
        // 定义一个Hashmap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (null != list && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    map.clear();
                    stoUtil = list.get(i);
                    map.put("productId", stoUtil.getProductId());
                    map.put("stock", stoUtil.getStock());
                    if ("1".equals(stoUtil.getIsThird())) {
                        // 执行减少 库存方法
                        count += this.goodsProductMapper.minBaseStock(map);
                    } else {
                        map.put("distinctId", stoUtil.getDistinctId());
                        // 执行减少 库存方法
                        count += this.goodsProductMapper.minStockToWare(map);
                    }
                }
            }
            return count;
        } finally {
            count = null;
            stoUtil = null;
            map = null;
        }
    }

    /**
     * 进行对比,传递请求
     *
     * @param productIds 需要对比的商品列表 {@link List}
     * @return 需要对比的货品详细信息的集合 {@link List}
     */
    public List<GoodsDetailBean> execCompProduct(List<Long> productIds, HttpServletRequest request) {
        List<GoodsDetailBean> lists = new ArrayList<GoodsDetailBean>();
        try {
            if (null != productIds && !productIds.isEmpty()) {
                for (int i = 0; i < productIds.size(); i++) {
                    GoodsDetailBean detailBean = new GoodsDetailBean();
                    try {
                        detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productIds.get(i)));
                        if (null != detailBean.getProductVo()) {
                            // 如果查询到的货品信息不为空就查询所属的分类信息
                            detailBean.setCateVo(this.cateService.queryCateAndParCateByCatId(detailBean.getProductVo().getGoods().getCatId()));
                            // 查询商品关联的标签
                            detailBean.setTags(goodsReleTagMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                            // 查询品牌
                            detailBean.setBrand(goodsBrandService.queryBrandById(detailBean.getProductVo().getGoods().getBrandId()));
                            // 查询商品关联的扩展属性
                            detailBean.setExpandPrams(expandParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                            // 查询商品关联的详细参数
                            detailBean.setParam(goodsReleParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                        }
                        lists.add(detailBean);
                    } finally {
                        detailBean = null;
                    }
                }
            }
            /* 取出session中的 */
            // 如果不是第三方商品就去查分仓库存
            Long distinctId = Long.parseLong(request.getSession().getAttribute("distinctId").toString());
            GoodsDetailBean detailBean;
            ProductWare productWare;
            GoodsProductVo productVo;
            for (int i = 0; i < lists.size(); i++) {
                detailBean = lists.get(i);
                if ("0".equals(detailBean.getProductVo().getIsThird()) && null != distinctId && distinctId > 0) {
                    /* 根据选择的地区查询库存及价格信息 */
                    productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(detailBean.getProductVo().getGoodsInfoId(), distinctId);
                        /* 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
                    if (null != productWare) {
                        productVo = detailBean.getProductVo();
                        productVo.setGoodsInfoPreferPrice(productWare.getWarePrice());
                        productVo.setGoodsInfoStock(productWare.getWareStock());
                        detailBean.setProductVo(productVo);
                    } else {
                        productVo = detailBean.getProductVo();
                        productVo.setGoodsInfoStock(0L);
                        detailBean.setProductVo(productVo);
                    }
                }
            }
            return lists;
        } finally {
            lists = null;
        }
    }

    /**
     * 根据货品ID和查询行数查询销售最高的几条记录
     *
     * @param productId 货品ID
     * @param rowCount  查询的行数
     * @return 查询到的货品集合
     */
    public List<GoodsProduct> queryTopSalesByProductId(Long productId, Integer rowCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("productId", productId);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.queryTopSalesInfoByProductId(map);
        } finally {
            map = null;
        }
    }

    /**
     * 新增兑换积分
     *
     * @param map
     * @return
     */
    @Override
    public int insertExchangeCusmomer(Map<String, Object> map) {
        return this.goodsProductMapper.insertExchangeCusmomer(map);
    }

    /**
     * 根据地区ID查询关联的仓库ID
     *
     * @param distinctId 地区ID
     * @return 仓库ID
     */
    @Override
    public Long selectWareIdByDistinctId(Long distinctId) {
        WareHouse wareHouse = productWareService.findWare(distinctId);
        return wareHouse == null ? null : wareHouse.getWareId();
    }

    /**
     * 更新货品信息
     *
     * @param goodsProduct  待更新的货品实体
     * @param username      操作人名称
     * @param specId        规格ID的数组
     * @param specDetailIds 规格值得ID数组
     * @return 更新影响的行数
     */
    @Transactional
    public int updateProduct(GoodsProduct goodsProduct, String username, String[] specId, String[] specDetailIds, String[] specRemark) {
        //判断是否在商城显示
        if (goodsProduct.getShowMobile().equals("1")) {
            goodsProduct.setShowList("1");
            goodsProduct.setShowMobile("1");
        } else {
            goodsProduct.setShowList("0");
            goodsProduct.setShowMobile("0");
        }
        goodsProduct.setGoodsInfoModifiedName(username);
        // 如果关联的规格不等于空，则更新规格
        if (null != specId && specId.length > 0) {
            for (int i = 0; i < specId.length; i++) {
                String specR = null;
                if (specRemark != null) {
                    if (specRemark[i] != null) {
                        specR = specRemark[i];

                    }
                }
                // 更新货品规格信息
                this.goodsProductReleSpecService.updateProductReleSpec(goodsProduct.getGoodsInfoId(), Long.parseLong(specId[i]), Long.parseLong(specDetailIds[i]), specR);
            }
        }
        // 如果库存大于0就发送点击了到货通知的会员
        if (null != goodsProduct.getGoodsInfoStock() && goodsProduct.getGoodsInfoStock() > 0) {
            // 发送到货通知
            //this.lackRegisterService.updateStatusByProductId(goodsProduct.getGoodsInfoId());
        }
        // 打印日志
        //LOGGER.info(ValueUtil.UPDATEPRODUCT + username);
        // 更新货品信息，并返回结果
        return this.goodsProductMapper.updateByPrimaryKeySelective(goodsProduct);
    }

    /**
     * 根据货品ID查询货品预览页的Vo
     *
     * @param productId 货品ID {@link java.lang.Long}
     * @return 查询到的Vo
     */
    public GoodsProductDetailViewVo queryViewVoByProductIdGoods(Long productId) {
        ArrayList<String> advMappings = new ArrayList<String>();
        advMappings.add("com.ysh.goods.dao.GoodsMapper.queryModeifiedVoByGoodsId");
        return queryViewVoByProductId(productId, advMappings);
    }

    /**
     * 根据货品ID查询货品预览页的Vo
     *
     * @param productId 货品ID {@link java.lang.Long}
     * @return 查询到的Vo
     */
    public GoodsProductDetailViewVo queryViewVoByProductId(Long productId, ArrayList<String> advMappings) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 把参数放入map集合中
            map.put("productId", productId);
            map.put("AdvMappings", advMappings);
            // 根据货品id查询货品预览页的Vo
            return this.goodsProductMapper.queryByProductId(map);
        } finally {
            map = null;
        }
    }

    /**
     * 根据货品ID查询
     *
     * @param productId  货品ID {@link java.lang.Long }
     * @param distinctId 选择的地区ID
     * @return 查询到的组装商品详细页的Bean
     */
    public GoodsDetailBean queryDetailByProductId(Long productId, Long distinctId) {
        // 商品详情
        GoodsDetailBean detailBean = new GoodsDetailBean();
        List<GoodsProduct> relaProduct = new ArrayList<GoodsProduct>();
        GoodsProductVo productVo;
        ProductWare productWare;
        try {

            // 获取货品的详细信息
            detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productId));
            if (null != detailBean.getProductVo()) {
                // 如果查询到的货品信息不为空就查询所属的分类信息
                detailBean.setCateVo(this.cateService.queryCateAndParCateByCatId(detailBean.getProductVo().getGoods().getCatId()));
                // 查询商品关联的标签
                detailBean.setTags(goodsReleTagMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询品牌
                detailBean.setBrand(goodsBrandService.queryBrandById(detailBean.getProductVo().getGoods().getBrandId()));
                // 查询商品关联的扩展属性
                detailBean.setExpandPrams(expandParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询商品关联的详细参数
                detailBean.setParam(goodsReleParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询商品关联的货品
                List<GoodsRelatedGoodsVo> relaProducts = this.goodsRelatedGoodsMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId());
                // 如果关联的不为空
                if (null != relaProducts && !relaProducts.isEmpty()) {
                    for (int i = 0; i < relaProducts.size(); i++) {
                        relaProduct.add(this.goodsProductMapper.queryFirstProductByGoodsId(relaProducts.get(i).getReleatedGoods().get(0).getGoodsId()));
                    }
                    detailBean.setReleProductList(relaProduct);
                }
                // 查询组合集合
                //detailBean.setGroupVos(this.goodsPub.getGoodsGroupService().queryGroupVoListWithOutProductId(detailBean.getProductVo().getGoodsInfoId()));
            }
            // 如果不是第三方商品就去查分仓库存
            if ("0".equals(detailBean.getProductVo().getIsThird()) && null != distinctId && distinctId > 0) {
                /* 根据选择的地区查询库存及价格信息 */
                productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(productId, distinctId);
                    /* 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
                if (null != productWare) {
                    productVo = detailBean.getProductVo();
                    productVo.setGoodsInfoPreferPrice(productWare.getWarePrice());
                    productVo.setGoodsInfoStock(productWare.getWareStock());
                    detailBean.setProductVo(productVo);
                } else {
                    productVo = detailBean.getProductVo();
                    productVo.setGoodsInfoStock(0L);
                    detailBean.setProductVo(productVo);
                }
            }
            /* 查询所有的关联的服务支持 */
            /*
             * detailBean.setSuppList(this.goodsProductSuppService.
             * queryAllSuppVoByProId
             * (detailBean.getProductVo().getGoodsInfoId()));
             */
            return detailBean;
        } finally {
            productVo = null;
            productWare = null;
            detailBean = null;
        }
    }

    public GoodsCateService getCateService() {
        return cateService;
    }

    /*
     * 查询货品信息，不关联查询其他信息
     * (non-Javadoc)
     * @see com.ysh.m.goods.service.GoodsProductService#queryByGoodsInfoDetail(java.lang.Long)
     */
    @Override
    public GoodsProduct queryByGoodsInfoDetail(Long goodsInfoId) {
        return this.goodsProductMapper.queryByGoodsInfoDetail(goodsInfoId);
    }

    /*
     * 查询货品，按销量降序
     * (non-Javadoc)
     * @see com.ysh.m.goods.service.GoodsProductService#queryInfosOrderBySales(java.util.Map)
     */
    @Override
    public List<GoodsProduct> queryInfosOrderBySales(Long count) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("rowCount", count);
        return this.goodsProductMapper.queryTopSalesInfos(map);
    }

    /**
     * @return
     */
    @Transactional
    public boolean beginSynchronization(String syncGoodsProduct) {
        SyncStatus syncStatus = syncStatusMapper.selectByPrimaryKey(syncGoodsProduct);
        // LOCK TABLES ysh_sync_type WRITE;
        if (syncStatus.getSyncstatus().equals("1")) {
            return true;
        } else {
            //修改的getGoodsItemNoList 为1
            syncStatus.setSyncstatus("1");
            syncStatusMapper.updateByPrimaryKeySelective(syncStatus);
            return false;
        }
        //UNLOCK TABLES;
    }

    /**
     * @return
     */
    public boolean endSynchronization(String syncGoodsProduct) {
        SyncStatus syncStatus = new SyncStatus();
        syncStatus.setSynctype(syncGoodsProduct);
        syncStatus.setSyncstatus("0");
        syncStatusMapper.updateByPrimaryKeySelective(syncStatus);
        return true;
    }


    /**
     * 同步货品信息
     */
    @Override
    public void saveGoodsProducts() throws Exception {
        if (!beginSynchronization("syncGoodsProduct")) {
            try {

                Date syncDate = goodsProductMapper.selectGoodsInfoByModifiedDesc();
                List<GoodsProductDetailVo> goodsProductList = openGoodsProductService.GetSyncGoodsProducts(syncDate);
                if(goodsProductList !=null) {
                    logger.info("获取商品数量".concat(String.valueOf(goodsProductList.size())));
                }
                else
                {
                    logger.info("获取商品数量0");
                }

                //1、保存商品等信息
                if (goodsProductList != null) {
                    for (GoodsProductDetailVo goodsProductDetailItem : goodsProductList) {
                        if(goodsProductDetailItem.getGoodsInfoDelflag().equals("1")){//删除
                            deleteGoods(goodsProductDetailItem);
                        }else {//新增、修改
                            //3.1 保存goods
                            saveGoods(goodsProductDetailItem);

                            //3.2 保存goodsInfo
                            saveGoodsProduct(goodsProductDetailItem);

                            //3.3 保存货品图片
                            saveGoodsImages(goodsProductDetailItem);

                            //3.4 保存货品规格
                            saveGoodsProductReleSpec(goodsProductDetailItem);
                        }
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
                logger.error("获取商品错误",e);
            } finally {
                endSynchronization("syncGoodsProduct");
            }
        }
    }

    /**
     * 货品删除同步
     * @param goodsProductDetailItem
     */
    private void deleteGoods(GoodsProductDetailVo goodsProductDetailItem){
        GoodsProduct goodsProduct = new GoodsProduct();
        goodsProduct.setGoodsInfoId(goodsProductDetailItem.getGoodsInfoId());
        goodsProduct.setGoodsInfoDelflag("1");
        goodsProduct.setGoodsInfoModifiedTime(goodsProductDetailItem.getGoodsInfoModifiedTime());
        goodsProductMapper.updateByPrimaryKeySelective(goodsProduct);
    }

    /**
     * 同步商品
     *
     * @param goodsProductDetailItem
     */
    private void saveGoods(GoodsProductDetailVo goodsProductDetailItem) {
        Goods goods = goodsMapper.selectByPrimaryKey(goodsProductDetailItem.getGoodsId());
        if (goods != null) {
            goods.setGoodsName(goodsProductDetailItem.getGoodsInfoName());
            goods.setGoodsAdded(goodsProductDetailItem.getGoodsInfoAdded());
            goods.setCatId(goodsProductDetailItem.getCatId());
            goods.setBrandId(goodsProductDetailItem.getBrandId());
            goods.setTypeId(goodsProductDetailItem.getTypeId());
            goods.setGoodsDetailDesc(goodsProductDetailItem.getGoodsDetailDesc());
            goods.setMobileDesc(goodsProductDetailItem.getMobileDesc());
            goods.setGoodsDelflag(goodsProductDetailItem.getGoodsInfoDelflag());
            goodsMapper.updateByPrimaryKeySelective(goods);
        } else {
            goods = new Goods();
            goods.setGoodsId(goodsProductDetailItem.getGoodsId());
            goods.setGoodsName(goodsProductDetailItem.getGoodsInfoName());
            goods.setGoodsAdded(goodsProductDetailItem.getGoodsInfoAdded());
            goods.setCatId(goodsProductDetailItem.getCatId());
            goods.setBrandId(goodsProductDetailItem.getBrandId());
            goods.setTypeId(goodsProductDetailItem.getTypeId());
            goods.setGoodsDetailDesc(goodsProductDetailItem.getGoodsDetailDesc());
            goods.setMobileDesc(goodsProductDetailItem.getMobileDesc());
            goods.setGoodsDelflag(goodsProductDetailItem.getGoodsInfoDelflag());
            goodsMapper.insertSelective(goods);
        }
    }

    /**
     * 同步货品
     *
     * @param goodsProductDetailItem
     */
    private void saveGoodsProduct(GoodsProductDetailVo goodsProductDetailItem) {
        GoodsProduct goodsProduct = goodsProductMapper.queryByGoodsInfoDetail1(goodsProductDetailItem.getGoodsInfoId());
        if (goodsProduct != null) {
            goodsProduct.setGoodsId(goodsProductDetailItem.getGoodsId());
            goodsProduct.setGoodsInfoItemNo(goodsProductDetailItem.getGoodsInfoItemNo());
            goodsProduct.setGoodsInfoName(goodsProductDetailItem.getGoodsInfoName());
            goodsProduct.setGoodsInfoSubtitle(goodsProductDetailItem.getGoodsInfoSubtitle());
            goodsProduct.setGoodsInfoAdded(goodsProductDetailItem.getGoodsInfoAdded());
            goodsProduct.setGoodsInfoStock(goodsProductDetailItem.getGoodsInfoStock());
            goodsProduct.setGoodsInfoPreferPrice(goodsProductDetailItem.getGoodsInfoPreferPrice());
            goodsProduct.setGoodsInfoDelflag(goodsProductDetailItem.getGoodsInfoDelflag());
            goodsProduct.setThirdId(goodsProductDetailItem.getThirdId());
            goodsProduct.setThirdName(goodsProductDetailItem.getThirdName());
            goodsProduct.setIsThird(goodsProductDetailItem.getIsThird());
            goodsProduct.setGoodsInfoImgId(goodsProductDetailItem.getGoodsInfoImgId());
            goodsProduct.setShowMobile(goodsProductDetailItem.getShowMobile());
            goodsProduct.setGoodsInfoModifiedTime(goodsProductDetailItem.getGoodsInfoModifiedTime());
            goodsProduct.setShowList(goodsProductDetailItem.getShowList());
            goodsProductMapper.updateByPrimaryKeySelective(goodsProduct);
        } else {
            goodsProduct = new GoodsProduct();
            goodsProduct.setGoodsInfoId(goodsProductDetailItem.getGoodsInfoId());
            goodsProduct.setGoodsId(goodsProductDetailItem.getGoodsId());
            goodsProduct.setGoodsInfoItemNo(goodsProductDetailItem.getGoodsInfoItemNo());
            goodsProduct.setGoodsInfoName(goodsProductDetailItem.getGoodsInfoName());
            goodsProduct.setGoodsInfoSubtitle(goodsProductDetailItem.getGoodsInfoSubtitle());
            goodsProduct.setGoodsInfoAdded(goodsProductDetailItem.getGoodsInfoAdded());
            goodsProduct.setGoodsInfoStock(goodsProductDetailItem.getGoodsInfoStock());
            goodsProduct.setGoodsInfoPreferPrice(goodsProductDetailItem.getGoodsInfoPreferPrice());
            goodsProduct.setGoodsInfoDelflag(goodsProductDetailItem.getGoodsInfoDelflag());
            goodsProduct.setThirdId(goodsProductDetailItem.getThirdId());
            goodsProduct.setGoodsInfoModifiedTime(goodsProductDetailItem.getGoodsInfoModifiedTime());
            goodsProduct.setThirdName(goodsProductDetailItem.getThirdName());
            goodsProduct.setIsThird(goodsProductDetailItem.getIsThird());
            goodsProduct.setGoodsInfoImgId(goodsProductDetailItem.getGoodsInfoImgId());
            goodsProduct.setShowMobile(goodsProductDetailItem.getShowMobile());
            goodsProduct.setShowList(goodsProductDetailItem.getShowList());
            goodsProductMapper.insertSelective(goodsProduct);
        }
    }

    /**
     * 同步货品图片
     *
     * @param goodsProductDetailItem
     */
    private void saveGoodsImages(GoodsProductDetailVo goodsProductDetailItem) {
        goodsImageMapper.deleteByGoodsInfoId(goodsProductDetailItem.getGoodsInfoId());
        if (goodsProductDetailItem.getProductImages() != null) {
            for (com.ylife.client.bean.GoodsImage item : goodsProductDetailItem.getProductImages()) {
                GoodsImage goodsImage = new GoodsImage();
                goodsImage.setGoodsInfoId(item.getGoodsInfoId());
                //goodsImage.setGoodsImgId(item.getGoodsImgId());
                goodsImage.setGoodsImgCreateName(item.getGoodsImgCreateName());
                goodsImage.setGoodsImgSort(item.getGoodsImgSort());
                goodsImage.setImageInName(item.getImageInName());
                goodsImage.setImageThumName(item.getImageThumName());
                goodsImage.setImageBigName(item.getImageBigName());
                goodsImage.setImageArtworkName(item.getImageArtworkName());
                goodsImageMapper.insertSelective(goodsImage);
            }
        }
    }

    /**
     * 同步货品规格
     *
     * @param goodsProductDetailItem
     */
    private void saveGoodsProductReleSpec(GoodsProductDetailVo goodsProductDetailItem) {
        goodsProductReleSpecMapper.deleteByProductId(goodsProductDetailItem.getGoodsInfoId());
        for (GoodsProductReleSpecVo info : goodsProductDetailItem.getSpecVo()) {
            if (info != null && info.getGoodsSpecDetail() != null) {
                GoodsProductReleSpec item = new GoodsProductReleSpec();
                item.setSpecId(info.getGoodsSpecDetail().getSpecId());
                item.setSpecDetailId(info.getGoodsSpecDetail().getSpecDetailId());
                item.setSpecValueRemark(info.getSpecValueRemark());
                //item.setReleSpecDetailId(info.getReleSpecDetailId());
                item.setGoodsInfoId(goodsProductDetailItem.getGoodsInfoId());
                goodsProductReleSpecMapper.insertSelective(item);
            }
        }

    }

    /**
     * 同步货品属性
     *
     * @param goodsProductDetailItem
     */
    private void saveGoodsOpenSpec(GoodsProductDetailVo goodsProductDetailItem) {
        goodsOpenSpecMapper.deleteByGoodsId(goodsProductDetailItem.getGoodsId());
        goodsOpenSpecValueMapper.deleteByGoodsId(goodsProductDetailItem.getGoodsId());
        if (goodsProductDetailItem.getSpecVo().size() > 0) {
            GoodsOpenSpec openSpecItem = new GoodsOpenSpec();
            openSpecItem.setGoodsId(goodsProductDetailItem.getGoodsId());
            openSpecItem.setSpecId(goodsProductDetailItem.getSpecVo().get(0).getSpec().getSpecId());
            goodsOpenSpecMapper.insertSelective(openSpecItem);

            GoodsOpenSpecValue openSpecValueItem = new GoodsOpenSpecValue();
            openSpecValueItem.setGoodsId(goodsProductDetailItem.getGoodsId());
            openSpecValueItem.setSpecId(goodsProductDetailItem.getSpecVo().get(0).getSpec().getSpecId());
            openSpecValueItem.setSpecValueId(goodsProductDetailItem.getSpecVo().get(0).getGoodsSpecDetail().getSpecDetailId());
            openSpecValueItem.setSpecValueRemark(goodsProductDetailItem.getSpecVo().get(0).getSpecValueRemark());
            goodsOpenSpecValueMapper.insertSelective(openSpecValueItem);
        }
    }
}
