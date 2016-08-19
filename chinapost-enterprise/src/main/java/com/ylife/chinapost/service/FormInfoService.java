package com.ylife.chinapost.service;

import com.ylife.chinapost.service.pojo.FormInfo;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.form.model.CustomerConsume;
import com.ylife.form.model.FormFunc;
import com.ylife.form.model.FormTime;
import com.ylife.form.model.UcoinGrandForm;
import com.ylife.ucoin.model.CustomerUcoinHistory;

import java.util.Date;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface FormInfoService {

    /**
     * 获取报表信息
     * @param start
     * @param end
     * @return
     */
    FormInfo getReportInfo(Date start,Date end);

    /**
     * 会员邮豆发放统计报表
     * @param enterpriseId
     * @param start
     * @param end
     * @param idCard
     * @return
     */
    Page<UcoinGrandForm> getUcoingrandReport(Long enterpriseId,Date start,Date end,String idCard,Pageable pageable);

    /**
     * 根据idcard获取会员发放邮豆的详情
     * @param idCard 会员身份证号
     * @return
     */
    Page<UcoinGrandForm> getDetailGrandInfo(Long enterpriseId,Date start,Date end,String idCard,Pageable pageable);

    /**
     * 会员邮豆消耗统计
     * @param idCard
     * @param start
     * @param end
     * @return
     */
    Page<CustomerConsume> getCustomerconsumeReport(String idCard,Date start,Date end,Pageable pageable);

    Page<CustomerConsume> getDetailConsume(String idCard,Date start,Date end,Pageable pageable);

    /**
     * 网点基础数据统计
     * @param enterpriseId
     * @param start
     * @param end
     * @return
     */
    Page<FormFunc> getBaseReport(Long enterpriseId,Date start,Date end,Pageable pageable);


    Map<String,Object> getHistoryMap(Long enterpriseId,Integer typeId,Date start,Date end,Pageable pageable);

    Page<CustomerUcoinHistory> getGrandHistory(Long enterpriseId,Integer typeId,Date start,Date end,Pageable pageable);



}
