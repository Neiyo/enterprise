package com.ylife.ucoin.service.impl;

import com.ylife.exception.UserOperationException;
import com.ylife.ucoin.mapper.CustomerUcoinMapper;
import com.ylife.ucoin.model.CustomerUcoin;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by InThEnd on 2016/4/8.
 * CustomerUcoinServiceImpl
 */
@Service
public class CustomerUcoinServiceImpl implements CustomerUcoinService {

    @Resource
    private CustomerUcoinMapper customerUcoinMapper;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;

    @Override
    public List<CustomerUcoin> getUcoins(long customerId) {
        return customerUcoinMapper.selectByCustomerId(customerId);
    }

    @Override
    public BigDecimal getUcoinBalance(long customerId) {
        return customerUcoinMapper.sumResePriceByCustomerId(customerId);
    }

    @Override
    public void grandUcoin(long customerId, long enterpriseId, BigDecimal count, Date startTime, Date endTime, String sendType, String remark) {
        Assert.notNull(count);
        Date createDate = new Date();
        if (startTime == null) {
            startTime = createDate;
        }
        CustomerUcoin ucoin = new CustomerUcoin();
        ucoin.setCustomerId(customerId);
        ucoin.setEnterpriseId(enterpriseId);
        ucoin.setCreateTime(createDate);
        ucoin.setStartTime(startTime);
        ucoin.setEndTime(endTime);
        ucoin.setResePrice(count);
        customerUcoinMapper.insertSelective(ucoin);


    }

    @Override
    public void redeemUcoinCode(long customerId, String code) {

    }

    @Override
    @Transactional
    public void deductUcoin(long customerId, long enterpriseId, BigDecimal amount, String remark) {
        Assert.notNull(amount);
        cutUcoin(customerId, amount);
        customerUcoinHistoryService.addHistory(customerId, enterpriseId, null, HistoryType.UCOIN_DEDUCT, amount, null, null, null, remark, null, null);
    }

    @Override
    public void ucoinPay(long customerId, BigDecimal amount, String remark, long orderId) {
        Assert.notNull(amount);
        cutUcoin(customerId, amount);
        customerUcoinHistoryService.addHistory(customerId, null, null, HistoryType.UCOIN_CONSUME, amount, null, null, null, remark, orderId, null);
    }

    /**
     * 判断两个数是否为相同符号
     * @param a
     * @param b
     * @return 两个为正、两个为负返回true,一正一负返回false
     */
    private boolean isEqualSymbol(BigDecimal a,BigDecimal b){
        if((a.compareTo(BigDecimal.ZERO)>0 && b.compareTo(BigDecimal.ZERO)<0)||(a.compareTo(BigDecimal.ZERO)<0 && b.compareTo(BigDecimal.ZERO)>0)) {
            return false;
        }
        return true;
    }


    /**
     * U宝变化
     *
     * @param customerId   用户ID
     * @param enterpriseId 企业ID
     * @param ucoinPrice   U宝金额(扣减为负，发放为正)
     * @param startTime    开始时间
     * @param endTime      结束时间
     * @param allowNegative 是否允许负数
     */
    @Override
    @Transactional
    public void ChangeUcoin(long customerId, long enterpriseId, BigDecimal ucoinPrice, Date startTime, Date endTime,boolean allowNegative) {
        Assert.notNull(ucoinPrice);
        if(ucoinPrice.compareTo(BigDecimal.ZERO)==0){
            throw new UserOperationException("参数有误。");
        }
        BigDecimal remain = ucoinPrice;
        Map<String, Object> map = new HashMap<>();
        map.put("customerId", customerId);
        map.put("enterpriseId", enterpriseId);
        BigDecimal price = BigDecimal.ZERO;//抵扣累计
        int i=0;
        while(true){
            CustomerUcoin ucoin = customerUcoinMapper.selectEarliestUcoinByCustomerIdAndEnterpriseIdForUpdate(map);
            if (ucoin == null) {
                //不存在，即为发放
                if (!allowNegative && remain.compareTo(BigDecimal.ZERO) < 0) {
                    throw new UserOperationException("邮豆余额不足，不能为负。");
                } else {
                    grandUcoin(customerId, enterpriseId, remain, startTime, endTime, "", "");
                    break;
                }
            }
            BigDecimal resePrice = ucoin.getResePrice();
            if(i==0){//符号相同，不抵扣
                if (isEqualSymbol(remain, resePrice)) {
                    if (!allowNegative && remain.compareTo(BigDecimal.ZERO) < 0) {
                        throw new UserOperationException("邮豆余额不足，不能为负。");
                    } else {
                        grandUcoin(customerId, enterpriseId, remain, startTime, endTime, "", "");
                        break;
                    }
                }
            }

            //符号不同，进行抵扣
            if(remain.abs().doubleValue()>=resePrice.abs().doubleValue()){
                customerUcoinMapper.deleteByPrimaryKey(ucoin.getId());
            }else {
                customerUcoinMapper.updatePriceByPrimaryKey(ucoin.getId(), resePrice.add(remain));
            }

            //还需要抵扣多少
            remain = resePrice.add(remain);
            //抵扣了多少
            price=price.add(resePrice.abs());
            //抵扣大于等于邮豆金额，跳出
            if (price.compareTo(ucoinPrice.abs())>=0) {
                if(remain.compareTo(BigDecimal.ZERO)<0 && !allowNegative) {
                    throw new UserOperationException("邮豆余额不足，不能为负。");
                }else {
                    break;
                }
            }
            i++;
        }
    }

    /**
     * 减少U宝。
     */
    @Transactional
    private void cutUcoin(long customerId, BigDecimal amount) {
        Assert.notNull(amount);
        BigDecimal remain = amount;
        while (true) {
            CustomerUcoin ucoin = customerUcoinMapper.selectEarliestUcoinByCustomerIdForUpdate(customerId);
            if (ucoin == null) {
                throw new UserOperationException("邮豆余额不足。");
            }
            BigDecimal resePrice = ucoin.getResePrice();
            int result = resePrice.compareTo(remain);
            if (result > 0) {
                customerUcoinMapper.updatePriceByPrimaryKey(ucoin.getId(), resePrice.subtract(remain));
                break;
            } else if (result == 0) {
                customerUcoinMapper.deleteByPrimaryKey(ucoin.getId());
                break;
            } else {
                remain = remain.subtract(resePrice);
                customerUcoinMapper.deleteByPrimaryKey(ucoin.getId());
            }
        }
    }

    @Override
    public CustomerUcoin getEarliestUcoin(long customerId) {
        return null;
    }

}
