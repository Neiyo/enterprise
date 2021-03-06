package com.ylife.ucoin.service;

import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.ucoin.model.CustomerUcoin;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.ucoin.model.CustomerUcoinHistoryVo;
import com.ylife.ucoin.model.HistoryType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/1.
 * <p/>
 * 关于用户U宝获取消耗记录的Service.
 */
public interface CustomerUcoinHistoryService {

    /**
     * 根据用户ID获取历史记录。
     *
     * @param customerId 用户ID
     */
    List<CustomerUcoinHistory> getHistory(long customerId);

    /**
     * 根据用户ID获取历史记录分页数据。
     *
     * @param customerId 用户ID
     * @param pageable   分页参数
     */
    Page<CustomerUcoinHistory> getHistory(long customerId, Pageable pageable);

    /**
     * 根据企业ID,类型获取历史记录。
     *
     * @param enterpriseId 企业ID
     * @param code         单号
     * @param type         历史记录类型
     * @param pageable     分页信息
     */
    Page<CustomerUcoinHistory> getHistory(long enterpriseId, Long code, HistoryType type, Date start, Date end, Pageable pageable);

    /**
     * 根据企业ID，获取发放记录。
     *
     * @param enterpriseId 企业ID
     * @param typeId       业务类型ID
     * @param start
     * @param end
     * @param pageable
     * @return
     */
    Page<CustomerUcoinHistory> getHistory(Long enterpriseId, Integer typeId, Date start, Date end, Pageable pageable);


    /**
     * 根据批量发放记录获取历史记录。
     *
     * @param batchId  批量发放ID
     * @param pageable 分页参数
     */
    Page<CustomerUcoinHistory> getBatchHistories(long batchId, Pageable pageable);


    /**
     * 获取用户的历史记录条数。
     *
     * @param customerId 用户ID
     */
    int historyCount(long customerId);

    /**
     * 添加新的历史记录。
     *
     * @param customerId   用户ID
     * @param enterpriseId 企业ID
     * @param batchId      批量分配ID
     * @param type         历史类型
     * @param price        发放的邮豆金额
     * @param marketPrice  营销邮豆金额
     * @param salesPrice   促销邮豆金额
     * @param typeId       业务类型ID
     * @param remark       备注
     * @param orderId      消费购物时关联的订单ID
     */
    void addHistory(long customerId, Long enterpriseId, Long batchId, HistoryType type, BigDecimal price, BigDecimal marketPrice, BigDecimal salesPrice, Integer typeId, String remark, Long orderId, String paramJson);

    /**
     * 根据邮宝使用记录ID获取邮宝信息
     *
     * @param id 记录ID
     */
    CustomerUcoinHistory selectByPrimaryKey(Long id);

    /**
     * 根据邮宝使用记录ID获取邮宝详细信息
     *
     * @param id 记录ID
     */
    CustomerUcoinHistoryVo selectDetailByPrimaryKey(Long id);

    /**
     * 根据企业id，统计当前账号以下所有网点邮豆发放信息
     * @param maxCatalog
     * @param minCatalog
     * @param start
     * @param end
     * @return
     */


}
