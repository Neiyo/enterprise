package com.ylife.chinapost.service.impl;

import com.ylife.address.service.AddressService;
import com.ylife.chinapost.service.AccountCenterService;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.mapper.EnterpriseInfoMapper;
import com.ylife.enterprise.mapper.EnterpriseManagerMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.model.EnterpriseManager;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.enterprise.service.EnterpriseManagerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by InThEnd on 2016/4/16.
 * AccountCenterServiceImpl
 */
@Service
public class AccountCenterServiceImpl implements AccountCenterService {

    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;
    @Resource
    private EnterpriseInfoMapper enterpriseInfoMapper;
    @Resource
    private EnterpriseManagerMapper enterpriseManagerMapper;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;
    @Resource
    private AddressService addressService;

    @Override
    public EnterpriseInfo getEnterpriseInfo() {
        return enterpriseInfoService.getEnterpriseInfo(currentLoginService.getCurrentLoginEnterpriseId());
    }

    @Override
    public EnterpriseManager getEnterpriseManagerInfo() {
        return currentLoginService.getCurrentLoginEnterpriseManager();
    }

    @Override
    public EnterpriseInfo getTopEnterpriseInfo() {
        return enterpriseInfoService.getTopEnterpriseInfo();
    }

    @Override
    public void editEnterPriseInfo(String enterpriseName, String profile, String imgUrl, String registerAddress, String linkman,
                                   Long provinceId, Long cityId, Long districtId, String addrDetail, String mobile) {
        EnterpriseInfo model = new EnterpriseInfo();
        long enterpriseId = enterpriseInfoService.getTopEnterpriseInfo().getEnterpriseId();
        String address;
        if (provinceId == null || cityId == null || districtId == null || addrDetail == null) {
            provinceId = null;
            cityId = null;
            districtId = null;
            addrDetail = null;
            address = null;
        } else {
            address = addressService.getAddressString(provinceId, cityId, districtId, addrDetail);
        }
        model.setEnterpriseId(enterpriseId);
        model.setEnterpriseName(enterpriseName);
        model.setProfile(profile);
        model.setImgUrl(imgUrl);
        model.setRegisterAddress(registerAddress);
        model.setLinkman(linkman);
        model.setProvinceId(provinceId);
        model.setCityId(cityId);
        model.setDistrictId(districtId);
        model.setAddrDetail(addrDetail);
        model.setAddress(address);
        model.setMobile(mobile);
        enterpriseInfoMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public void editAccountInfo(String accountName, Long provinceId, Long cityId, Long districtId, String addrDetail, String linkman, String linkMobile) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        EnterpriseInfo model = new EnterpriseInfo();
        String address;
        if (provinceId == null || cityId == null || districtId == null || addrDetail == null) {
            provinceId = null;
            cityId = null;
            districtId = null;
            addrDetail = null;
            address = null;
        } else {
            address = addressService.getAddressString(provinceId, cityId, districtId, addrDetail);
        }
        model.setEnterpriseId(enterpriseId);
        model.setAccountName(accountName);
        model.setProvinceId(provinceId);
        model.setCityId(cityId);
        model.setDistrictId(districtId);
        model.setAddrDetail(addrDetail);
        model.setAddress(address);
        model.setLinkman(linkman);
        model.setLinkMobile(linkMobile);
        enterpriseInfoMapper.updateByPrimaryKeySelective(model);
    }

    @Override
    public void editPassword(String password) {
        EnterpriseManager managerModel = new EnterpriseManager();
        managerModel.setId(currentLoginService.getCurrentLoginEnterpriseManagerId());
        managerModel.setPassword(password);
        enterpriseManagerMapper.updateByPrimaryKeySelective(managerModel);
    }

    @Override
    public void editPaykey(String paykey) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        EnterpriseFunction funcModel = new EnterpriseFunction();
        funcModel.setId(enterpriseId);
        funcModel.setPaykey(paykey);
        enterpriseFunctionMapper.updateByPrimaryKeySelective(funcModel);

    }
}
