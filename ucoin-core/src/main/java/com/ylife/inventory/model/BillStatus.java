package com.ylife.inventory.model;

/**
 * Created by InThEnd on 2016/6/13.
 * 单据状态
 */
public enum BillStatus {

    //审核中
    CHECKING("审核中"),

    //已审核
    CHECKED("已审核"),

    //已终止
    TERMINATED("已终止"),

    //已完成
    FINISHED("已完成"),

    //待发货
    WAIT_DELIVERY("待发货"),

    //待收货
    WAIT_RECEIVER("待收货"),

    //待编辑
    WAIT_EDIT("待修改");

    private String name;

    BillStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
