package com.ylife.chinapost.service;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/16.
 */
public interface NewGrandService {

    /**
     * 顶级账号批量发放扣减的导入excel解析
     * @param uploadExcel
     * @param type true发放 false扣减
     * @return
     */
    Map<String,Object> parseFile(MultipartFile uploadExcel,Boolean type) throws IOException, InvalidFormatException;


    void batchGrand(Map<String,Object> map,String paykey,Boolean type);
}
