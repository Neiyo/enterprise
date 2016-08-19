package com.ylife.ucoin.mapper;

import com.ylife.ucoin.model.CustomerUcoin;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/8.
 */
public interface CustomerUcoinMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CustomerUcoin record);

    int insertSelective(CustomerUcoin record);

    CustomerUcoin selectByPrimaryKey(Long id);

    List<CustomerUcoin> selectByCustomerId(Long id);

    int updateByPrimaryKeySelective(CustomerUcoin record);

    int updateByPrimaryKey(CustomerUcoin record);

    int updatePriceByPrimaryKey(@Param("id") Long id, @Param("resePrice") BigDecimal resPrice);

    BigDecimal sumResePriceByCustomerId(Long customerId);

    /**
     * 加锁获取日期最早的U宝。
     */
    CustomerUcoin selectEarliestUcoinByCustomerIdForUpdate(Long customerId);

    /**
     * 加锁获取指定用户、企业的最早的U宝
     * @param map
     * @return
     */
    CustomerUcoin selectEarliestUcoinByCustomerIdAndEnterpriseIdForUpdate(Map<String,Object> map);
}
