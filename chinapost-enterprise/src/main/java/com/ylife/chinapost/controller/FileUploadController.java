package com.ylife.chinapost.controller;

import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.exception.ServerIOException;
import com.ylife.fdfs.FastDFS;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * Created by InThEnd on 2016/7/15.
 * 图片上传服务器。
 */
@Controller
@RequestMapping(value = "/web", produces = "application/json;charset=utf-8")
public class FileUploadController {

    @Resource
    private FastDFS dfs;

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/uploadPicture", method = RequestMethod.POST)
    @ResponseBody
    public String uploadPicture(@RequestParam("file") MultipartFile file) {
        try {
            String url = dfs.saveFile(file, "jpg");
            return new JsonResponseBean(url).toJson();
        } catch (Exception e) {
            throw new ServerIOException("上传失败，请稍后重试。");
        }
    }
}
