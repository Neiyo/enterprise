/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.customer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.ylife.customer.mapper.CustomerFollowMapper;
import com.ylife.customer.model.CustomerFollow;
import com.ylife.customer.service.CustomerFollowServiceMapper;
import org.springframework.stereotype.Service;
/**
 * @see com.ysh.customer.service.CustomerFollowServiceMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年4月1日 下午5:56:53
 * @version 0.0.1
 */
@Service("customerFollowServiceMapper")
public class CustomerFollowServiceMapperImpl implements
        CustomerFollowServiceMapper {
    // spring 注解
    private CustomerFollowMapper customerFollowMapper;

    public CustomerFollowMapper getCustomerFollowMapper() {
        return customerFollowMapper;
    }

    @Resource(name = "customerFollowMapper")
    public void setCustomerFollowMapper(
            CustomerFollowMapper customerFollowMapper) {
        this.customerFollowMapper = customerFollowMapper;
    }

    /*
     * 根据会员编号查找会员关注商品
     * 
     * @see
     * com.ysh.customer.service.CustomerFollowServiceMapper#selectByCustomerId
     * (java.lang.Long)
     */
    @Override
    public List<CustomerFollow> selectByCustomerId(Long customerId) {
        return customerFollowMapper.selectCustFollowByCustId(customerId);
    }

    /*
     * 根据货品id查询关注的会员id
     * 
     * @see com.ysh.customer.service.CustomerFollowServiceMapper#selectSendId(java.lang.Long)
     */
    @Override
    public List<CustomerFollow> selectSendId(Long goodsInfoId) {

        return customerFollowMapper.selectSendId(goodsInfoId);
    }

}
