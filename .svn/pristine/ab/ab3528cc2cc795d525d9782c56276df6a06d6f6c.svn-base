package com.ylife.enterprise.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by InThEnd on 2016/4/12.
 * <p/>
 * TestEnterpriseMapper
 */
@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestEnterpriseMapper {

    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Test
    public void testCheckPaykey() {
        boolean b = enterpriseMapper.checkPaykey(1L, "123456");
        System.out.println(b);
    }

    @Test
    public void testUpdateUcoinPriceByPrimaryKey() {
        enterpriseMapper.updateUcoinPriceByPrimaryKey(1L, new BigDecimal("2.58").negate());
    }
}
