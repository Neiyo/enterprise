/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ylife.goods.service.impl;

import com.ylife.data.page.PageBean;
import com.ylife.goods.mapper.GoodsBrandMapper;
import com.ylife.goods.model.GoodsBrand;
import com.ylife.goods.model.ValueUtil;
import com.ylife.goods.service.GoodsBrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品品牌service实现类
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2013年12月16日 下午8:36:16
 */
@Service("GoodsBrandService")
public class GoodsBrandServiceImpl implements GoodsBrandService {
    // 注入GoodsBrandMapper
    @Resource
    private GoodsBrandMapper goodsBrandMapper;

    /**
     * 根据PageBean 查询分页列表
     *
     * @param pageBean
     * @return 查询到的列表封装到PageBean中
     */
    public PageBean queryByPageBean(PageBean pageBean) {
        // 查询总行数
        pageBean.setRows(this.goodsBrandMapper.queryTotalCount());
        // 定义一个HashMap集合
        Map<String, Integer> map = new HashMap<String, Integer>();
        try {
            // 封装分页参数
            map.put(ValueUtil.STARTROWNUM, pageBean.getStartRowNum());
            map.put(ValueUtil.ENDROWNUM, pageBean.getEndRowNum());
            // 设置列表
            pageBean.setList(this.goodsBrandMapper.queryByPageBean(map));
        } finally {
            // 参数置空
            map = null;
        }
        // 返回pageBean
        return pageBean;
    }

    /**
     * 根据Id查询商品品牌
     *
     * @param brandId 商品品牌ID {@link java.lang.Long}
     * @return {@link com.ysh.goods.bean.GoodsBrand}
     */
    public GoodsBrand queryBrandById(Long brandId) {
        // 根据Id查询商品品牌
        return this.goodsBrandMapper.selectByPrimaryKey(brandId);
    }

    /**
     * 查询所有的商品品牌
     *
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     * {@link com.ysh.goods.bean.GoodsBrand}
     */
    public List<GoodsBrand> queryAllBrand() {
        // 查询所有的商品品牌
        return this.goodsBrandMapper.queryAllBrand();
    }

    /**
     * 根君名称查询商品品牌
     *
     * @return 查询到的商品品牌的集合 {@link java.util.List}
     * {@link com.ysh.goods.bean.GoodsBrand}
     */
    @Override
    public List<GoodsBrand> queryallbrandbyName(String brandName) {
        // 根君名称查询商品品牌
        return this.goodsBrandMapper.queryallbrandbyName(brandName);
    }

    /**
     * 查询所有品牌
     *
     * @return List
     */
    public List<GoodsBrand> queryAllBrandList() {
        // 查询所有品牌
        return goodsBrandMapper.queryAllBrandList();
    }

    /**
     * 验证品牌名称是否可用
     *
     * @param brandNmae 待验证的品牌名称
     * @return 可用返回true 不可用返回false
     */
    public boolean checkBrandName(String brandNmae) {
        // 验证品牌名称是否可用
        return this.goodsBrandMapper.selectByBrandName(brandNmae) > 0 ? false
                : true;
    }

    /**
     * 验证品牌名称，不可重复添加
     *
     * @param brandName 待验证的品牌名称
     * @return 返回查询条数，若=0则可以添加，反之不可添加
     */
    @Override
    public int selectByBrandName(String brandName) {
        // 验证品牌名称，不可重复添加
        return goodsBrandMapper.selectByBrandName(brandName);
    }
}
