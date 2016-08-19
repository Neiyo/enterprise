package com.ylife.chinapost.controller.api;

import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.enterprise.model.EnterpriseInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by InThEnd on 2016/5/24.
 * 当前登录用户接口。
 */
@Controller
@RequestMapping(value = "/web/api/current", produces = "application/json;charset=utf-8")
public class CurrentLoginAPIController {

    @Resource
    private CurrentLoginService currentLoginService;

    @RequestMapping("/getInfo")
    @ResponseBody
    public String getInfo() {
        String username = currentLoginService.getCurrentLoginUsername();
        EnterpriseInfo info = currentLoginService.getCurrentLoginEnterpriseInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        map.put("enterpriseName", info.getEnterpriseName());
        map.put("logo", info.getImgUrl());
        return new JsonResponseBean(map).toJson();
    }

}
