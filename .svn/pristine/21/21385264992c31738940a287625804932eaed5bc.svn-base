package com.ylife.chinapost.controller.api;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.CustomerManageService;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.utils.Assert;
import com.ylife.utils.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by InThEnd on 2016/4/21.
 * 会员管理控制器。
 */
@Controller
@SecurityResource(parent = "/web/memberManager", primary = false)
@RequestMapping(value = "/web/api/customer", produces = "application/json;charset=utf-8")
public class CustomerManageAPIController {
    @Resource
    private CustomerManageService customerManageService;

    /**
     * 获取客户的分页数据。
     * 所有的参数都是可选的，都为空则返回所有用户。
     *
     * @param idCard         身份证号
     * @param linkPhone      联系电话
     * @param name           姓名
     * @param isActive       是否激活
     * @param isPhoneChecked 手机号是否验证
     * @param managerNo      客户经理号
     */
    @RequestMapping("/getCustomers")
    @ResponseBody
    public String getCustomers(@RequestParam(value = "idCard", required = false) String idCard,
                               @RequestParam(value = "linkPhone", required = false) String linkPhone,
                               @RequestParam(value = "name", required = false) String name,
                               @RequestParam(value = "isActive", required = false) Boolean isActive,
                               @RequestParam(value = "isPhoneChecked", required = false) Boolean isPhoneChecked,
                               @RequestParam(value = "managerNo", required = false) String managerNo,
                               @RequestParam(value = "tag", required = false) String tag,
                               @RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        tag = StringUtil.nullOrNotBlank(tag);
        idCard = StringUtil.nullOrNotBlank(idCard);
        linkPhone = StringUtil.nullOrNotBlank(linkPhone);
        name = StringUtil.nullOrNotBlank(name);
        managerNo = StringUtil.nullOrNotBlank(managerNo);
        Page<ChinapostCustomer> data = customerManageService.getCustomers(idCard, linkPhone, name, isActive,
                isPhoneChecked, managerNo, tag, enterpriseId, new Pageable(page, size));
        return new JsonResponseBean(data).toJson();
    }

    /**
     * U宝扣减。
     *
     * @param customerId 客户ID
     * @param amount     扣减金额
     */
    @RequestMapping(value = "/ucoinDeduct", method = RequestMethod.POST)
    @ResponseBody
    public String ucoinDeduct(@RequestParam(value = "customerId") long customerId,
                              @RequestParam(value = "amount") BigDecimal amount,
                              @RequestParam(value = "paykey") String paykey,
                              @RequestParam(value = "remark", required = false) String remark) {
        Assert.isTrue(amount.compareTo(BigDecimal.ZERO) == 1, "扣减金额必须大于0");
        customerManageService.ucoinDeduct(customerId, amount, paykey, remark);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 激活用户。
     *
     * @param customerId 用户ID
     * @param password   用户密码
     */
    @RequestMapping("/activeCustomer")
    @ResponseBody
    public String activeCustomer(@RequestParam(value = "customerId") long customerId,
                                 @RequestParam(value = "password") String password) {
        Assert.isValid(Constants.PASSWORD_VALIDATOR, password, "密码格式不正确，为6-16位");
        customerManageService.activeCustomer(customerId, password);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 增加新用户。
     *
     * @param idCard     身份证号
     * @param name       姓名
     * @param linkPhone  联系电话
     * @param provinceId 省份ID
     * @param cityId     城市ID
     * @param districtId 区县ID
     * @param addr       详细地址
     * @param managerNo  客户经理号
     * @param remark     备注
     */
    @RequestMapping("/newCustomer")
    @ResponseBody
    public String newCustomer(@RequestParam(value = "idCard") String idCard,
                              @RequestParam(value = "name") String name,
                              @RequestParam(value = "linkPhone", required = false) String linkPhone,
                              @RequestParam(value = "provinceId") long provinceId,
                              @RequestParam(value = "cityId") long cityId,
                              @RequestParam(value = "districtId") long districtId,
                              @RequestParam(value = "addr") String addr,
                              @RequestParam(value = "imgUrl") String image,
                              @RequestParam(value = "managerNo", required = false) String managerNo,
                              @RequestParam(value = "tags", required = false) String tags,
                              @RequestParam(value = "remark", required = false) String remark) {
        Assert.lengthBetween(addr, 0, 30, "详细地址长度不能超过30.");
        List<Integer> tagIds = Constants.SIMPLE_PARSER.parseJSON(tags, new TypeToken<List<Integer>>() {
        });
        customerManageService.newCustomer(idCard, name, linkPhone, provinceId, cityId, districtId, addr, image, managerNo, tagIds, remark);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 编辑用户。
     *
     * @param id         客户ID
     * @param name       姓名
     * @param linkPhone  联系电话
     * @param provinceId 省份ID
     * @param cityId     城市ID
     * @param districtId 区县ID
     * @param addr       详细地址
     * @param managerNo  客户经理号
     * @param image      图片
     * @param remark     备注
     */
    @RequestMapping("/editCustomer")
    @ResponseBody
    public String editCustomer(@RequestParam(value = "id") long id,
                               @RequestParam(value = "name") String name,
                               @RequestParam(value = "linkPhone", required = false) String linkPhone,
                               @RequestParam(value = "provinceId") long provinceId,
                               @RequestParam(value = "cityId") long cityId,
                               @RequestParam(value = "districtId") long districtId,
                               @RequestParam(value = "addr") String addr,
                               @RequestParam(value = "managerNo", required = false) String managerNo,
                               @RequestParam(value = "tags", required = false) String tags,
                               @RequestParam(value = "imgUrl", required = false) String image,
                               @RequestParam(value = "remark", required = false) String remark) {
        Assert.lengthBetween(addr, 0, 30, "详细地址长度不能超过30.");
        List<Integer> tagIds = Constants.SIMPLE_PARSER.parseJSON(tags, new TypeToken<List<Integer>>() {
        });
        customerManageService.editCustomer(id, name, linkPhone, provinceId, cityId, districtId, addr, managerNo, tagIds, remark, image);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 重置登录密码。
     */
    @RequestMapping("/editPassword")
    @ResponseBody
    public String editPassword(@RequestParam(value = "customerId") long customerId,
                               @RequestParam(value = "password") String password) {
        Assert.hasLength(password.trim());
        customerManageService.resetPassword(customerId, password);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    /**
     * 重置支付密码。
     */
    @RequestMapping("/editPaykey")
    @ResponseBody
    public String editPaykey(@RequestParam(value = "customerId") long customerId,
                             @RequestParam(value = "paykey") String paykey) {
        Assert.hasLength(paykey.trim());
        customerManageService.resetPaykey(customerId, paykey);
        return JsonResponseBean.getSuccessResponse().toJson();
    }
}
