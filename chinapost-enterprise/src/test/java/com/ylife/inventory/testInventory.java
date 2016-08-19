package com.ylife.inventory;

/**
 * Created by Administrator on 2016/5/31.
 */

import com.ylife.chinapost.service.InventoryManageService;
import com.ylife.data.json.message.JsonResponseBean;
import com.ylife.enterprise.mapper.EnterpriseFunctionMapper;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.inventory.mapper.InventoryBillMapper;
import com.ylife.inventory.mapper.InventoryMapper;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.model.InventoryBill;
import com.ylife.inventory.model.InventoryKey;
import com.ylife.inventory.service.InventoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@ContextConfiguration(value = {"classpath:applicationContext-common.xml", "classpath:applicationContext-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class testInventory {

    @Resource
    private InventoryManageService inventoryManageService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private InventoryBillMapper inventoryBillMapper;
    @Resource
    private EnterpriseFunctionMapper enterpriseFunctionMapper;
    @Resource
    private InventoryMapper inventoryMapper;


    @Test
    public void testInventoryService() {
        InventoryKey key = new InventoryKey(1l, 3014l);
        long enterpriseId = 1;

        InventoryGoodsResult inventoryGoodsResult = inventoryManageService.getInventoryGoodsResultByPrimarykey(3014);
        System.out.println(inventoryGoodsResult.getGoodsInfoName());
    }

    @Test
    public void testCatalog() {
        EnterpriseFunction enterpriseFunction = enterpriseFunctionMapper.selectByPrimaryKey(1l);
        Long maxCatalog = enterpriseFunction.getMaxCatalog();
        Long minCatalog = enterpriseFunction.getMinCatalog();

        System.out.println(maxCatalog);
        System.out.println(minCatalog);
    }


    @Test
    public void testInventoryBillMapper() {
        Long billId = 3l;
        InventoryBill inventoryBill = inventoryBillMapper.selectBillDetailByPrimaryKey(billId);
        System.out.println(new JsonResponseBean(inventoryBill).toJson());

        Long code = 1606200335622000l;
        //InventoryBill inventoryBillCode = inventoryBillMapper.selectBillDetailByCode(code);
        //System.out.println(new JsonResponseBean(inventoryBillCode).toJson());
    }


    @Test
    public void testGetByInventory() {
        EnterpriseFunction enterpriseFunction = enterpriseFunctionMapper.selectByPrimaryKey(1l);
        Long maxCatalog = enterpriseFunction.getMaxCatalog();
        Long minCatalog = enterpriseFunction.getMinCatalog();

        List<InventoryGoodsResult> inventoryGoodsResultList = inventoryMapper.selectTotalInventoryGoodsByCatage(minCatalog, maxCatalog, null, null, null, null, null);

        System.out.println(new JsonResponseBean(inventoryGoodsResultList).toJson());

    }
}
