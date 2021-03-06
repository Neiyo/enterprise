package com.ylife.product.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.model.GoodsInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsInfoMapper {
    int deleteByPrimaryKey(Long goodsInfoId);

    int insert(GoodsInfo record);

    int insertSelective(GoodsInfo record);

    GoodsInfo selectByPrimaryKey(Long goodsInfoId);

    int updateByPrimaryKeySelective(GoodsInfo record);

    int updateByPrimaryKey(GoodsInfo record);

    GoodsManagerResult selectByGoodsInfoId(long goodsInfoId);

    /**
     * 获取所有货品列表
     *
     * @param goodsInfoName
     * @param goodsInfoItemNo
     * @param brandId
     * @param goodsInfoAdded
     * @param typeId
     * @param thirId
     * @param pageable
     */
    List<GoodsManagerResult> selectGoods(@Param("goodsInfoName") String goodsInfoName,
                                         @Param("goodsInfoItemNo") String goodsInfoItemNo,
                                         @Param("goodsInfoAdded") String goodsInfoAdded,
                                         @Param("brandId") Long brandId,
                                         @Param("typeId") Long typeId,
                                         @Param("thirdId") Long thirId,
                                         @Param("pageable") Pageable pageable);

    /**
     * 获取代课下单商品页面
     *
     * @param goodsInfoName
     * @param lowPrice
     * @param highPrice
     * @param pageable
     */
    List<GoodsManagerResult> selectValetGoods(@Param("goodsInfoName") String goodsInfoName,
                                              @Param("lowPrice") BigDecimal lowPrice,
                                              @Param("highPrice") BigDecimal highPrice,
                                              @Param("pageable") Pageable pageable);


    int countValeGoodsResult(@Param("goodsInfoName") String goodsInfoName,
                             @Param("lowPrice") BigDecimal lowPrice,
                             @Param("highPrice") BigDecimal highPrice);


    /**
     * 获取本网点库存大于0的商品
     * @param goodsInfoName
     * @param lowPrice
     * @param highPrice
     * @param pageable
     * @return
     */
    List<GoodsManagerResult> selectNetWorkGoods(@Param("enterpriseId")Long enterpriseId,
                                                @Param("goodsInfoName") String goodsInfoName,
                                                @Param("lowPrice") BigDecimal lowPrice,
                                                @Param("highPrice") BigDecimal highPrice,
                                                @Param("pageable") Pageable pageable);

    int countNetWorkGoods(@Param("enterpriseId")Long enterpriseId,
                          @Param("goodsInfoName") String goodsInfoName,
                          @Param("lowPrice") BigDecimal lowPrice,
                          @Param("highPrice") BigDecimal highPrice);



    /**
     * 获取品牌列表
     *
     * @return
     */
    List<GoodsManagerResult> selectBrand();

    /**
     * 获取类型列表
     *
     * @return
     */
    List<GoodsManagerResult> selectType();

    /**
     * 获取商家列表
     *
     * @return
     */
    List<GoodsManagerResult> selectThirdName();

    List<GoodsManagerResult> selectBrandTypeThird();

    /**
     * 总数totalElements
     *
     * @return
     */
    int countGoodsResult(@Param("goodsInfoName") String goodsInfoName,
                         @Param("goodsInfoItemNo") String goodsInfoItemNo,
                         @Param("goodsInfoAdded") String goodsInfoAdded,
                         @Param("brandId") Long brandId,
                         @Param("typeId") Long typeId,
                         @Param("thirdId") Long thirId);

    /**
     * 货品下架
     *
     * @param goodsId
     * @return
     */
    int unshelves(long goodsId);

    /**
     * 货品上架
     *
     * @param goodsId
     * @return
     */
    int shelves(long goodsId);

}