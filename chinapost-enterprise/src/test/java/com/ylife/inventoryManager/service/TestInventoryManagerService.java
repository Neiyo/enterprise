package com.ylife.inventoryManager.service;

import com.ylife.chinapost.service.InventoryManageService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.data.page.Page;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.mapper.EnterpriseMapper;
import com.ylife.enterprise.model.Enterprise;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.inventory.mapper.InventoryMapper;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.service.InventoryService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/5/10.
 */

@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestInventoryManagerService {

    @Resource
    private  InventoryService inventoryService;

    @Resource
    private InventoryManageService inventoryManageService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;
    @Resource
    private InventoryMapper inventoryMapper;

    @Test
    public void testInventoryGoods(){
        List<EnterpriseInfo> enterpriseInfos= enterpriseInfoService.getEnterpriseInfos(288);
        List<InventoryGoodsResult> inventoryGoodsResults=new ArrayList<>();
        List<InventoryGoodsResult> inventoryGoodsResultList=inventoryMapper.selectInventoryGoods(288,null,null,null,null,null);
        for(InventoryGoodsResult inventoryGoodsResult:inventoryGoodsResultList){
            Long goodsInfoId=inventoryGoodsResult.getGoodsInfoId();
            if (!inventoryGoodsResults.contains(goodsInfoId)){
                inventoryGoodsResults.add(inventoryGoodsResult);
            }
        }
        for(int i=0;i<inventoryGoodsResults.size();i++){
        System.out.println(inventoryGoodsResults.get(i).getGoodsInfoId());}
    }


    @Test
    public void testqueryByInventory() {
        Page<InventoryGoodsResult> page = inventoryService.queryByInventory(1l, null, null, null, null, null);
        List<InventoryGoodsResult> sonList = page.getContent();
        Map<Long, Integer> map = new HashMap<>();
        Long eId = -1l;
        int j = 0;
        for (int i = 0; i < sonList.size(); i++) {

            InventoryGoodsResult model = sonList.get(i);
            if (model.getEnterpriseId() == eId) {
                j++;
                map.put(eId, j);
            } else {
                eId = model.getEnterpriseId();
                j++;
                map.put(eId,j);
            }
        }
    }

    @Test
    public  void main() throws IOException {
        FileOutputStream fos=new FileOutputStream("E:/测试.xls");

        Workbook wb=new HSSFWorkbook();

        Sheet sheet=wb.createSheet();
        /*
         * 设定合并单元格区域范围
         *  firstRow  0-based
         *  lastRow   0-based
         *  firstCol  0-based
         *  lastCol   0-based
         */
        Page<InventoryGoodsResult> page = inventoryService.queryByInventory(1l, null, null, null, null, null);
        List<InventoryGoodsResult> sonList = page.getContent();
        Map<Long, Integer> map = new HashMap<>();
        Long eId = -1l;
        int j = 0;
        for (int i = 0; i < sonList.size(); i++) {
            InventoryGoodsResult model = sonList.get(i);
            if (model.getEnterpriseId() == eId) {
                j++;
                map.put(eId, j);
            } else {
                eId = model.getEnterpriseId();
                j++;
                map.put(eId, j);
            }

        }
        int s=1;
        for(Long id:map.keySet()){
            int amount=map.get(id);
            CellRangeAddress cellAddress = new CellRangeAddress(s,amount,0,0);

            sheet.addMergedRegion(cellAddress);
            Row row=sheet.createRow(s);
            Cell cell=row.createCell(0);
            EnterpriseInfo enterpriseInfo=enterpriseInfoService.getEnterpriseInfo(id);
            cell.setCellValue(enterpriseInfo.getAccountName());
            s=amount+1;
        }
        wb.write(fos);

        fos.close();
    }
}
