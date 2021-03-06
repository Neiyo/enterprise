package com.ylife.chinapost.controller.api;


import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.GoodsManageService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.data.page.Pageable;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.product.service.GoodsInfoService;
import com.ylife.security.annotation.SecurityResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by XuKai on 2016/4/27.
 * 商品管理控制器
 */
@Controller
@SecurityResource(parent = "/web/itemManager", primary = false)
@RequestMapping(value = "/web/api/goodsManager", produces = "application/json;charset=utf-8")
public class GoodsManagerAPIController {

    @Resource
    private GoodsManageService goodsManageService;
    @Resource
    private GoodsInfoService goodsInfoService;

    @RequestMapping("/getGoodsInfo")
    @ResponseBody
    public String getGoodsInfo(@RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                               @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
                               @RequestParam(value = "goodsInfoAdded", required = false) String goodsInfoAdded,
                               @RequestParam(value = "brandId", required = false) Long brandId,
                               @RequestParam(value = "typeId", required = false) Long typeId,
                               @RequestParam(value = "thirdId", required = false) Long thirdId,
                               @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                               @RequestParam(value = "size", required = false, defaultValue = "10") int size) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoAdded = Constants.nullOrNotBlank(goodsInfoAdded);
        Page<GoodsManagerResult> goodsManagerResultPage = goodsManageService.getGoods(goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, new Pageable(page, size));
        return new JsonResponseBean(goodsManagerResultPage).toJson();
    }


    @RequestMapping("/unshelves")
    @ResponseBody
    public String unshelves(@RequestParam(value = "goodsInfoId", required = false) long goodsInfoId) {
        goodsInfoService.unshelves(goodsInfoId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

    @RequestMapping("/shelves")
    @ResponseBody
    public String shelves(@RequestParam(value = "goodsInfoId", required = false) long goodsInfoId) {
        goodsInfoService.shelves(goodsInfoId);
        return JsonResponseBean.getSuccessResponse().toJson();
    }

}
