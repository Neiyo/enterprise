/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ylife.main.model;

import java.io.Serializable;
import java.util.List;

/**
 * VO类-移动版楼层数据封装类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月22日下午2:03:15
 */
public class MobStoreyInfo  implements Serializable {
    /** 楼层对象 */
    private MobStorey mobStorey;

    /** 楼层广告集合 */
    private List<MobAdver> storeyAdvers;

    public MobStorey getMobStorey() {
        return mobStorey;
    }

    public void setMobStorey(MobStorey mobStorey) {
        this.mobStorey = mobStorey;
    }

    public List<MobAdver> getStoreyAdvers() {
        return storeyAdvers;
    }

    public void setStoreyAdvers(List<MobAdver> storeyAdvers) {
        this.storeyAdvers = storeyAdvers;
    }

}
