package com.ylife.wealth.service.impl;

import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.wealth.mapper.EnterpriseBatchGrandMapper;
import com.ylife.wealth.model.EnterpriseBatchGrand;
import com.ylife.wealth.service.EnterpriseBatchGrandService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/25.
 * <p/>
 * EnterpriseBatchGrandServiceImpl
 */
@Service
public class EnterpriseBatchGrandServiceImpl implements EnterpriseBatchGrandService {

    @Resource
    private EnterpriseBatchGrandMapper enterpriseBatchGrandMapper;

    private Generator generator = IdGeneratorFactory.create("ENTERPRISE_GRAND");



    @Override
    public long
    addBatchGrand(long enterpriseId, String sendType, String remark) {
        long code = generator.generate();
        EnterpriseBatchGrand grand = new EnterpriseBatchGrand();
        grand.setCode(code);
        grand.setEnterpriseId(enterpriseId);
        grand.setSendType(sendType);
        grand.setRemark(remark);
        grand.setCreateTime(new Date());
        enterpriseBatchGrandMapper.insertSelective(grand);
        return grand.getId();
    }

    @Override
    public void updateFee(long batchId, BigDecimal amount, Integer count) {
        EnterpriseBatchGrand grand = new EnterpriseBatchGrand();
        grand.setId(batchId);
        grand.setFee(amount);
        grand.setUcoinCount(count);
        enterpriseBatchGrandMapper.updateByPrimaryKeySelective(grand);
    }

    @Override
    public Page<EnterpriseBatchGrand> getBatchGrands(Long code,long enterpriseId, Date start, Date end, Pageable pageable) {
        List<EnterpriseBatchGrand> list = enterpriseBatchGrandMapper.selectByEnterpriseIdAndCreateDate(code,enterpriseId, start, end, pageable);
        int totalElements = enterpriseBatchGrandMapper.countByEnterpriseIdAndCreateDate(code,enterpriseId, start, end);
        return new PageImpl<>(pageable, totalElements, list);
    }

}
