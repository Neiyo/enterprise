package com.ylife.form.service.impl;

import com.ylife.data.page.Page;
import com.ylife.data.page.PageImpl;
import com.ylife.data.page.Pageable;
import com.ylife.form.mapper.FormFuncMapper;
import com.ylife.form.model.CustomerConsume;
import com.ylife.form.model.FormFunc;
import com.ylife.form.model.FormTime;
import com.ylife.form.model.UcoinGrandForm;
import com.ylife.form.service.FormFuncService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
@Service
public class FormFuncServiceImpl implements FormFuncService {

    @Resource
    private FormFuncMapper formFuncMapper;

    @Override
    public FormTime getReportInfoByTime(Long maxCatalog, Long minCatalog, Date start, Date end) {
        return formFuncMapper.selectFormByTime(maxCatalog,minCatalog,start,end);
    }

    @Override
    public Long getNewCustomerByTime(Long maxCatalog, Long minCatalog, Date start, Date end) {
        return formFuncMapper.selectNewCustomerByTime(maxCatalog,minCatalog,start,end);
    }

    @Override
    public List<FormTime> getReportInfoEveryDay(Long maxCatalog, Long minCatalog, Date start, Date end) {
        return formFuncMapper.selectReportByDay(maxCatalog,minCatalog,start,end);
    }

    @Override
    public List<FormTime> newCustomerAmount(Long maxCatalog, Long minCatalog, Date start, Date end) {
        return formFuncMapper.selectNewcustomerAmount(maxCatalog,minCatalog,start,end);
    }

    @Override
    public Page<UcoinGrandForm> getucoinGrandForm(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end,Pageable pageable) {
        List<UcoinGrandForm> formFuncs =formFuncMapper.selectUcoinGrand(idCard,maxCatalog,minCatalog,start,end,pageable);
        int totalElements=formFuncMapper.countUcoinGrandForm(idCard,maxCatalog,minCatalog,start,end);
        return new PageImpl<>(pageable,totalElements, formFuncs);
    }

    @Override
    public Page<UcoinGrandForm> getDetailGrandForm(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end,Pageable pageable) {
        List<UcoinGrandForm> formFuncs =formFuncMapper.selectDetailUcoinGrand(idCard,maxCatalog,minCatalog,start,end,pageable);
        int totalElements=formFuncMapper.countDetailUcoinGrand(idCard,maxCatalog,minCatalog,start,end);
        return new PageImpl<>(pageable,totalElements, formFuncs);
    }

    @Override
    public Page<CustomerConsume> getCustomerConsume(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end, Pageable pageable) {
        List<CustomerConsume> formFuncs =formFuncMapper.selectCustomerConsume(idCard,maxCatalog,minCatalog,start,end,pageable);
        int totalElements=formFuncMapper.countCustomerConsume(idCard,maxCatalog,minCatalog,start,end);
        return new PageImpl<>(pageable,totalElements, formFuncs);
    }

    @Override
    public Page<CustomerConsume> getDetailConsume(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end, Pageable pageable) {
        List<CustomerConsume> consume=formFuncMapper.selectDetailConsume(idCard,maxCatalog,minCatalog,start,end,pageable);
        int totalConsume=formFuncMapper.countDetailConsume(idCard, maxCatalog, minCatalog, start, end);
        return  new PageImpl<>(pageable,totalConsume,consume);
    }


    @Override
    public Page<FormFunc> getNetdata(Long maxCatalog, Long minCatalog, Date start, Date end, Pageable pageable) {
        List<FormFunc> formFuncs =formFuncMapper.selectNetData(maxCatalog,minCatalog,start,end,pageable);
        int totalElements=formFuncMapper.countNetData(maxCatalog,minCatalog);
        return new PageImpl<>(pageable,totalElements, formFuncs);
    }
}
