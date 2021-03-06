/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ylife.customer.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * 会员积分记录信息
 *
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2014年3月20日 上午11:08:26
 */
public class CustomerPoint implements Serializable {
    /**
     * 记录编号
     *
     * @see #getPointId()
     * @see #setPointId(Long)
     */
    private Long pointId;
    /**
     * 会员ID
     *
     * @see #getCustomerId()
     * @see #setCustomerId(Long)
     */
    private Long customerId;
    /**
     * 积分说明
     *
     * @see #getPointDetail()
     * @see #setPointDetail(String)
     */
    private String pointDetail;
    /**
     * 积分类型
     *
     * @see #getPointType()
     * @see #setPointType(String)
     */
    private String pointType;
    /**
     * 积分数量
     *
     * @see #getPoint()
     * @see #setPoint(Integer)
     */
    private Integer point;
    /**
     * 创建时间
     *
     * @see #getCreateTime()
     * @see #setCreateTime(Date)
     */
    private Date createTime;
    /**
     * 删除标记
     *
     * @see #getDelFlag()
     * @see #setDelFlag(String)
     */
    private String delFlag;
    /**
     * 会员名称
     *
     * @see #getCustomerUsername()
     * @see #setCustomerUsername(String)
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String customerUsername;
    /**
     * 创建时间到
     *
     * @see #getCreateTimeTo()
     * @see #setCreateTimeTo(Date)
     */
    private Date createTimeTo;

    // 分页开始的条数
    private int startRowNum;

    // 分页结束的条数
    private int endRowNum;

    /**
     * 会员总积分
     *
     * @see #getPointSum()
     */
    private Long pointSum;

    /**
     * 获取createTimeTo的值
     *
     * @return createTimeTo
     */
    public Date getCreateTimeTo() {
        if (this.createTimeTo != null) {
            return new Date(this.createTimeTo.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置createTime的值
     */
    public void setCreateTimeTo(Date createTimeTo) {
        if (createTimeTo != null) {
            Date timeTemp = (Date) createTimeTo.clone();
            if (timeTemp != null) {
                this.createTimeTo = timeTemp;
            }
        }
    }

    /**
     * 获取customerUsername的值
     *
     * @return customerUsername
     */
    public String getCustomerUsername() {
        return customerUsername;
    }

    /**
     * 设置customerUsername的值
     *
     * @param customerUsername {@link java.lang.String}
     */
    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    /**
     * 获取pointId的值
     */
    public Long getPointId() {
        return pointId;
    }

    /**
     * 设置pointId的值
     *
     * @param pointId {@link java.lang.Long}
     */
    public void setPointId(Long pointId) {
        this.pointId = pointId;
    }

    /**
     * 获取CustomerId的值
     *
     * @return customerId
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置CustomerId的值
     *
     * @param customerId {@link java.lang.Long}
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取pointDetail的值
     *
     * @return pointDetail
     */
    public String getPointDetail() {
        return pointDetail;
    }

    /**
     * 设置pointDetail的值
     *
     * @param pointDetail {@link java.lang.String}
     */
    public void setPointDetail(String pointDetail) {
        this.pointDetail = pointDetail;
    }

    /**
     * 获取pointType的值
     *
     * @return pointType
     */
    public String getPointType() {
        return pointType;
    }

    /**
     * 设置pointType的值
     *
     * @param pointType {@link java.lang.String}
     */
    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    /**
     * 获取point的值
     */
    public Integer getPoint() {
        return point;
    }

    /**
     * 设置point的值
     *
     * @param point {@link java.lang.Integer}
     */
    public void setPoint(Integer point) {
        this.point = point;
    }

    /**
     * 获取createTime的值
     *
     * @return createTime
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置createTime的值
     *
     * @param createTime {@link java.util.Date}
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date timeTemp = (Date) createTime.clone();
            if (timeTemp != null) {
                this.createTime = timeTemp;
            }
        }
    }

    /**
     * 获取delFlag的值
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置delFlag的值
     *
     * @param delFlag {@link java.lang.String}
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getPointSum() {
        return pointSum;
    }

    public void setPointSum(Long pointSum) {
        this.pointSum = pointSum;
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

}
