package com.ylife.chinapost.service.impl;

import com.ylife.address.service.AddressService;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.CustomerManageService;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.customer.service.CustomerWithUcoinInfoService;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.UserOperationException;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.Assert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/18.
 * CustomerManageServiceImpl
 */
@Service
public class CustomerManageServiceImpl implements CustomerManageService {
    @Resource
    private CustomerUcoinService customerUcoinService;
    @Resource
    private CurrentLoginService currentLoginService;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;
    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private CustomerWithUcoinInfoService customerWithUcoinInfoService;
    @Resource
    private AddressService addressService;

    @Override
    public Page<ChinapostCustomer> getCustomers(String idCard,
                                                String linkPhone,
                                                String name,
                                                Boolean isActive,
                                                Boolean isPhoneChecked,
                                                String managerNo,
                                                String tagName,
                                                Long enterpriseId,
                                                Pageable pageable) {
        EnterpriseFunction current = currentLoginService.getCurrentLoginEnterpriseFunc();
        EnterpriseFunction function;
        Long minIndex = null;
        Long maxIndex = null;
        if (enterpriseId == null) {
            if (current.getParentId() != null) {
                minIndex = current.getMinCatalog();
                maxIndex = current.getMaxCatalog();
            }
        } else {
            function = enterpriseService.getEnterpriseFunction(enterpriseId);
            Assert.notNull(function, "企业不存在。");
            Assert.isTrue(function.getCatalog() >= current.getMinCatalog() && function.getCatalog() <= current.getMaxCatalog(), "无权获取此企业信息。");
            if (function.getParentId() != null) {
                minIndex = function.getMinCatalog();
                maxIndex = function.getMaxCatalog();
            }
        }
        ChinapostCustomer queryModel = new ChinapostCustomer();
        queryModel.setIdcardNo(idCard);
        queryModel.setContactPhone(linkPhone);
        queryModel.setFullname(name);
        queryModel.setIsActive(isActive);
        queryModel.setMobileChecked(isPhoneChecked);
        queryModel.setManagerNo(managerNo);
        return customerWithUcoinInfoService.getInfos(queryModel, tagName, current.getId(), minIndex, maxIndex, pageable);
    }

    @Transactional
    @Override
    public void ucoinDeduct(long customerId, BigDecimal amount, String paykey, String remark) {
        long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        if (!enterpriseService.checkPaykey(enterpriseId, paykey)) {
            throw new UserOperationException("支付密码错误。");
        }
        customerUcoinService.deductUcoin(customerId, enterpriseId, amount, remark);
        enterpriseService.addUcoinAmount(enterpriseId, amount);
    }

    @Override
    public void activeCustomer(long customerId, String password) {
        chinapostCustomerService.activateCustomer(customerId, password);
    }

    @Override
    public void newCustomer(String idCard,
                            String name,
                            String linkPhone,
                            long provinceId,
                            long cityId,
                            long districtId,
                            String addr,
                            String image,
                            String managerNo,
                            List<Integer> tags,
                            String remark) {
        String contactAddr = addressService.getAddressString(provinceId, cityId, districtId, addr);
        Long enterpriseId = currentLoginService.getCurrentLoginEnterpriseId();
        Long creatorId = currentLoginService.getCurrentLoginEnterpriseManagerId();
        chinapostCustomerService.addCustomer(idCard, name, linkPhone, provinceId, cityId, districtId, addr, image, managerNo, enterpriseId, creatorId, tags, remark, contactAddr);
    }

    @Override
    public void editCustomer(long id,
                             String name,
                             String linkPhone,
                             long provinceId,
                             long cityId,
                             long districtId,
                             String addr,
                             String managerNo,
                             List<Integer> tags,
                             String remark,
                             String imgUrl) {
        String contactAddr = addressService.getAddressString(provinceId, cityId, districtId, addr);
        chinapostCustomerService.editCustomer(id, name, linkPhone, provinceId, cityId, districtId, addr, managerNo, tags, remark, contactAddr, imgUrl);
    }

    @Override
    public void resetPassword(long customerId, String password) {
        Assert.hasLength(password.trim());
        chinapostCustomerService.setLoginPassword(customerId, password);
    }

    @Override
    public void resetPaykey(long customerId, String paykey) {
        Assert.hasLength(paykey.trim());
        chinapostCustomerService.setPaymentPassword(customerId, paykey);
    }
}
