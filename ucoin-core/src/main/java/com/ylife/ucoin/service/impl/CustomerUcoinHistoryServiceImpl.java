package com.ylife.ucoin.service.impl;

import com.ylife.data.order.Generator;
import com.ylife.data.order.IdGeneratorFactory;
import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.system.mapper.ParamMapper;
import com.ylife.system.model.Param;
import com.ylife.ucoin.mapper.CustomerUcoinHistoryMapper;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.ucoin.model.CustomerUcoinHistoryVo;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/16.
 * CustomerUcoinHistoryServiceImpl
 */
@Service
public class CustomerUcoinHistoryServiceImpl implements CustomerUcoinHistoryService {

    @Resource
    private CustomerUcoinHistoryMapper customerUcoinHistoryMapper;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;

    public static final Generator generator = IdGeneratorFactory.create("UCOIN_HISTORY");

    @Override
    public List<CustomerUcoinHistory> getHistory(long customerId) {
        return customerUcoinHistoryMapper.selectByCustomerId(customerId, null);
    }

    @Override
    public Page<CustomerUcoinHistory> getHistory(long customerId, Pageable pageable) {
        List<CustomerUcoinHistory> content = customerUcoinHistoryMapper.selectByCustomerId(customerId, pageable);
        int totalElements = customerUcoinHistoryMapper.countByCustomerId(customerId);
        return new PageImpl<>(pageable, totalElements, content);
    }

    @Override
    public Page<CustomerUcoinHistory> getHistory(long enterpriseId, Long code, HistoryType type, Date start, Date end, Pageable pageable) {
        List<CustomerUcoinHistory> histories = customerUcoinHistoryMapper.selectByEnterpriseIdAndCodeAndTypeAndCreateTime(enterpriseId, code, type, start, end, pageable);
        int totalElements = customerUcoinHistoryMapper.countByEnterpriseIdAndCodeAndTypeAndCreateTime(enterpriseId, code, type, start, end);
        return new PageImpl<>(pageable, totalElements, histories);
    }

    @Override
    public Page<CustomerUcoinHistory> getHistory(Long enterpriseId, Integer typeId, Date start, Date end, Pageable pageable) {
        EnterpriseFunction enterpriseFunction = enterpriseFunctionMapper.selectByPrimaryKey(enterpriseId);
        Long maxCatalog = enterpriseFunction.getMaxCatalog();
        Long minCatalog = enterpriseFunction.getMinCatalog();
        List<CustomerUcoinHistory> histories = customerUcoinHistoryMapper.selectByEnterpriseIdAndTypeIdAndCreatTime(minCatalog, maxCatalog, typeId, start, end, pageable);
        int totalElements = customerUcoinHistoryMapper.countByEnterpriseIdAndTypeIdAndCreatTime(minCatalog, maxCatalog, typeId, start, end);
        return new PageImpl<>(pageable, totalElements, histories);
    }

    @Override
    public Page<CustomerUcoinHistory> getBatchHistories(long batchId, Pageable pageable) {


        List<CustomerUcoinHistory> list = customerUcoinHistoryMapper.selectByBatchId(batchId, pageable);
        int totalElements = customerUcoinHistoryMapper.countByBatchId(batchId);
        return new PageImpl<>(pageable, totalElements, list);
    }

    @Override
    public int historyCount(long customerId) {
        return customerUcoinHistoryMapper.countByCustomerId(customerId);
    }

    @Override
    public void addHistory(long customerId, Long enterpriseId, Long batchId, HistoryType type, BigDecimal price, BigDecimal marketPrice, BigDecimal salesPrice, Integer typeId, String remark, Long orderId, String paramJson) {
        Assert.notNull(type);
        if (type == HistoryType.ENTERPRISE_GRAND || type == HistoryType.UCOIN_DEDUCT || type == HistoryType.UCOIN_REFUND) {
            Assert.notNull(enterpriseId);
        }
        long code = generator.generate();
        CustomerUcoinHistory history = new CustomerUcoinHistory();
        history.setEnterpriseId(enterpriseId);
        history.setCustomerId(customerId);
        history.setBatchId(batchId);
        history.setType(type);
        history.setPrice(price);
        history.setMarketPrice(marketPrice);
        history.setSalesPrice(salesPrice);
        history.setCreateTime(new Date());
        history.setTypeId(typeId);
        history.setRemark(remark);
        history.setOrderId(orderId);
        history.setParamJson(paramJson);
        history.setCode(code);
        customerUcoinHistoryMapper.insertSelective(history);
    }

    @Override
    public CustomerUcoinHistory selectByPrimaryKey(Long id) {
        return customerUcoinHistoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public CustomerUcoinHistoryVo selectDetailByPrimaryKey(Long id) {
        return customerUcoinHistoryMapper.selectDetailByPrimaryKey(id);
    }
}