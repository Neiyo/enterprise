package com.ylife.chinapost.service;

import com.ylife.authority.model.ResourcePage;
import com.ylife.data.page.Page;
import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.model.EnterpriseManager;

import java.util.List;

/**
 * Created by InThEnd on 2016/4/8.
 * 当前登录用户服务
 */
public interface CurrentLoginService {

    Long getCurrentLoginEnterpriseId();

    Enterprise getCurrentLoginEnterprise();

    EnterpriseInfo getCurrentLoginEnterpriseInfo();

    EnterpriseFunction getCurrentLoginEnterpriseFunc();

    EnterpriseManager getCurrentLoginEnterpriseManager();

    Long getCurrentLoginEnterpriseManagerId();

    String getCurrentLoginUsername();

    /**
     * 是否为顶级企业。
     */
    boolean isPrimaryEnterprise();

    /**
     * 是否为管理员账户。
     */
    boolean isPrimaryManager();

    List<ResourcePage> getCurrentPages();

}
