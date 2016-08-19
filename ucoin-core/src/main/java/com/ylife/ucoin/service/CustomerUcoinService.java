package com.ylife.ucoin.service;

import com.ylife.ucoin.model.CustomerUcoin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/1.
 * <p/>
 * 关于用户U宝的服务。
 */
public interface CustomerUcoinService {

    /**
     * 根据用户ID获取所有U宝。
     *
     * @param customerId 用户ID
     * @return 返回用户所拥有的所有U宝
     */
    List<CustomerUcoin> getUcoins(long customerId);

    /**
     * 根据用户ID获取U宝余额。
     *
     * @param customerId 用户ID
     */
    BigDecimal getUcoinBalance(long customerId);

    /**
     * 给用户增加U宝。
     *
     * @param customerId   用户ID
     * @param enterpriseId 发放企业ID
     * @param count        发放数量
     * @param startTime    U宝开始使用时间 如果为null默认为创建时间
     * @param endTime      U宝结束使用时间 如果为null表示无结束时间
     * @param sendType     发放类型
     * @param remark       备注
     */
    void grandUcoin(long customerId, long enterpriseId, BigDecimal count, Date startTime, Date endTime, String sendType, String remark);


    /**
     * U宝变化
     * @param customerId 用户ID
     * @param enterpriseId 企业ID
     * @param ucoinPrice U宝金额
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @param allowNegative 允许负数（true表示允许，false表示不允许)
     */
    void ChangeUcoin(long customerId,long enterpriseId,BigDecimal ucoinPrice,Date startTime,Date endTime,boolean allowNegative);

    /**
     * 兑换U宝兑换码。
     *
     * @param customerId 用户ID
     * @param code       兑换码
     */
    void redeemUcoinCode(long customerId, String code);

    /**
     * 扣减U宝。
     *
     * @param customerId   要扣减的用户ID
     * @param enterpriseId 企业ID
     * @param amount       扣减数量
     * @param remark       备注
     */
    void deductUcoin(long customerId, long enterpriseId, BigDecimal amount, String remark);

    /**
     * 使用U宝付款。
     *
     * @param customerId 用户ID
     * @param amount     付款额
     * @param remark     备注
     * @param orderId    消费购物时关联的订单ID
     */
    void ucoinPay(long customerId, BigDecimal amount, String remark, long orderId);

    /**
     * 获取用户最早创建的U宝。
     *
     * @param customerId 客户ID
     */
    CustomerUcoin getEarliestUcoin(long customerId);

}
