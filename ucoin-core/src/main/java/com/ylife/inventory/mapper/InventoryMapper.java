package com.ylife.inventory.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.model.Inventory;
import com.ylife.inventory.model.InventoryKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by XiaoBiaoGe on 2016/4/18.
 * 库存Mapper
 */
public interface InventoryMapper {

    Inventory selectByPrimaryKey(InventoryKey key);

    Inventory selectByPrimaryKeyForUpdate(InventoryKey key);



    //根据企业id批量增加货品
    int insertGoods(long enterpriseId);

    //根据库存id和货品id增加单件货品
    int insertGood(long enterpriseId, long goodsId);

    int deleteByPrimaryKey(InventoryKey key);

    int insert(Inventory record);

    int insertSelective(Inventory record);

    int updateByPrimaryKeySelective(InventoryKey key);

    int updateByPrimaryKey(InventoryKey key);

    //批量获取库存信息
    List<Inventory> getInventory(@Param("enterpriseId") long enterpriseId, @Param("pageable") Pageable pageable);

    /**
     * 增加或减少可用库存
     *
     * @param key 库存的联合主键
     * @param add 可用库存的真实变化值，可为负值
     */
    int addAvailableInventory(@Param("key") InventoryKey key, @Param("amount") int add);

    //修改可用库存
    int updateAvailable(@Param("key") InventoryKey key, @Param("available") int available);


    /**
     * 增加或减少库存
     *
     * @param key 库存的联合主键
     * @param add 库存变化量，可为负值
     */
    int addActuallyInventory(@Param("key") InventoryKey key, @Param("amount") int add);

    //修改库存
    int updateInventory(@Param("key") InventoryKey key, @Param("inventory") int inventory);

    //批量获取仓库货品及库存信息
    List<InventoryGoodsResult> selectInventoryGoods(@Param("enterpriseId") long enterpriseId,
                                                    @Param("goodsInfoName") String goodsInfoName,
                                                    @Param("goodsNumber") String goodsNumber,
                                                    @Param("brandId") Long brandId,
                                                    @Param("thirdId") Long thirdId,
                                                    @Param("pageable") Pageable pageable);



    //选择货品
    List<InventoryGoodsResult> selectGoods(
            @Param("goodsInfoName") String goodsInfoName,
            @Param("goodsNumber") String goodsNumber,
            @Param("brandId") Long brandId,
            @Param("thirdId") Long thirdId,
            @Param("pageable") Pageable pageable);

    List<InventoryGoodsResult> getWarningGoods(@Param("enterpriseId") long enterpriseId,
                                               @Param("goodsInfoName") String goodsInfoName,
                                               @Param("goodsNumber") String goodsNumber,
                                               @Param("brandId") Long brandId,
                                               @Param("thirdId") Long thirdId,
                                               @Param("pageable") Pageable pageable);

    InventoryGoodsResult selectWarningGood(InventoryKey key);

    InventoryGoodsResult selectInventoryGoodsResultByPrimarykey(InventoryKey key);


    /**
     * 搜索仓库货品数量
     *
     * @param enterpriseId
     * @param goodsInfoName
     * @param goodsNumber
     * @param brandId
     * @param thirdId
     */
    int countByEnterpriseId(@Param("enterpriseId") long enterpriseId,
                            @Param("goodsInfoName") String goodsInfoName,
                            @Param("goodsNumber") String goodsNumber,
                            @Param("brandId") Long brandId,
                            @Param("thirdId") Long thirdId);

    /**
     * 搜索商品数量
     *
     * @param goodsInfoName
     * @param goodsNumber
     * @param brandId
     * @param thirdId
     */
    int countGoodsInfoId(@Param("goodsInfoName") String goodsInfoName,
                         @Param("goodsNumber") String goodsNumber,
                         @Param("brandId") Long brandId,
                         @Param("thirdId") Long thirdId);

    /**
     * 预警货品数量
     *
     * @param enterpriseId
     * @param goodsInfoName
     * @param goodsNumber
     * @param brandId
     * @param thirdId
     */
    int countWarningGoodsByEnterpriseId(@Param("enterpriseId") long enterpriseId,
                                        @Param("goodsInfoName") String goodsInfoName,
                                        @Param("goodsNumber") String goodsNumber,
                                        @Param("brandId") Long brandId,
                                        @Param("thirdId") Long thirdId);

    int selectWarning(long enterpriseId);


    //获取最底级网点的库存信息（只有顶级和次级有仓库）
    List<InventoryGoodsResult> selectEndEnterpriseidByCatalog(@Param("minCatalog")Long minCatalog,
                                                              @Param("maxCatalog")Long maxCatalog,
                                                              @Param("goodsInfoName") String goodsInfoName,
                                                              @Param("goodsNumber") String goodsNumber,
                                                              @Param("brandId") Long brandId,
                                                              @Param("thirdId") Long thirdId,
                                                              @Param("pageable")Pageable pageable);

    int countEndEnterpriseGoods(@Param("minCatalog")Long minCatalog,
                                @Param("maxCatalog")Long maxCatalog,
                                @Param("goodsInfoName") String goodsInfoName,
                                @Param("goodsNumber") String goodsNumber,
                                @Param("brandId") Long brandId,
                                @Param("thirdId") Long thirdId,
                                @Param("pageable")Pageable pageable);



    //查看次级网点库存货品的总库存信息
    List<InventoryGoodsResult> selectTotalInventoryGoodsByCatage (@Param("minCatalog")Long minCatalog,
                                                                 @Param("maxCatalog")Long maxCatalog,
                                                                 @Param("goodsInfoName") String goodsInfoName,
                                                                 @Param("goodsNumber") String goodsNumber,
                                                                 @Param("brandId") Long brandId,
                                                                 @Param("thirdId") Long thirdId,
                                                                 @Param("pageable")Pageable pageable);


     int countTotalInventoryGoodsByCatage(@Param("minCatalog")Long minCatalog,
                                          @Param("maxCatalog")Long maxCatalog,
                                          @Param("goodsInfoName") String goodsInfoName,
                                          @Param("goodsNumber") String goodsNumber,
                                          @Param("brandId") Long brandId,
                                          @Param("thirdId") Long thirdId);


    List<InventoryGoodsResult> selectGoodsAndInventory(@Param("enterpriseId")Long enterpriseId,
                                                       @Param("goodsInfoId")Long goodsInfoId,
                                                       @Param("goodsInfoName") String goodsInfoName,
                                                       @Param("goodsNumber") String goodsNumber,
                                                       @Param("brandId") Long brandId,
                                                       @Param("thirdId") Long thirdId,
                                                       @Param("pageable")Pageable pageable);

    int countGoodsAndInventory(@Param("enterpriseId")Long enterpriseId,
                               @Param("goodsInfoId")Long goodsInfoId,
                               @Param("goodsInfoName") String goodsInfoName,
                               @Param("goodsNumber") String goodsNumber,
                               @Param("brandId") Long brandId,
                               @Param("thirdId") Long thirdId);

}
