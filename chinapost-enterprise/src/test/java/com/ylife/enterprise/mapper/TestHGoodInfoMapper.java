package com.ylife.enterprise.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.product.mapper.GoodsInfoMapper;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2016/5/1.
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestHGoodInfoMapper {
    @Resource
    GoodsInfoMapper goodsInfoMapper;

    @Test
    public void testSelectGoods(){
        List<GoodsManagerResult> list=goodsInfoMapper.selectGoods("测试商品01(1)",null,null,null,null,null,new Pageable(1,1000));
        for(GoodsManagerResult goodsManagerResult:list){
            System.out.println(goodsManagerResult.getSpecString()+goodsManagerResult.getGoodsInfoId());

        }
    }
}
