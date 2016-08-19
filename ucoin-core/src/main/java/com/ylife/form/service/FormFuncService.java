package com.ylife.form.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.form.model.CustomerConsume;
import com.ylife.form.model.FormFunc;
import com.ylife.form.model.FormTime;
import com.ylife.form.model.UcoinGrandForm;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface FormFuncService {

    /**
     * 根据时间获取报表信息
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */
    FormTime getReportInfoByTime(Long maxCatalog,Long minCatalog,Date start,Date end);

    Long getNewCustomerByTime(Long maxCatalog,Long minCatalog,Date start,Date end);


    /**
     * 每日邮豆发放报表
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */
    List<FormTime> getReportInfoEveryDay(Long maxCatalog,Long minCatalog,Date start,Date end);

    /**
     * 每日新增会员数量
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */
    List<FormTime> newCustomerAmount(Long maxCatalog,Long minCatalog,Date start,Date end);

    /**
     * 会员邮豆发放统计
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<UcoinGrandForm> getucoinGrandForm(String idCard,Long maxCatalog,Long minCatalog,Date start,Date end,Pageable pageable);


    /**
     * 会员邮豆发放详情
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<UcoinGrandForm> getDetailGrandForm(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end,Pageable pageable);

    /**
     * 会员消耗邮豆统计
     * @param idCard
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<CustomerConsume> getCustomerConsume(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end,Pageable pageable);

    Page<CustomerConsume> getDetailConsume(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end,Pageable pageable);

    //Page<FormFunc> getDetailRefund(String idCard, Long maxCatalog, Long minCatalog, Date start, Date end,Pageable pageable);

    /**
     * 网点基础数据统计
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<FormFunc> getNetdata(Long maxCatalog,Long minCatalog,Date start,Date end,Pageable pageable);


}

