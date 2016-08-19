package com.ylife.chinapost.controller;

import com.google.gson.reflect.TypeToken;
import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.*;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.data.json.json.Parser;
import com.ylife.data.json.json.SimpleParser;
import com.ylife.data.page.Page;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.form.model.CustomerConsume;
import com.ylife.form.model.FormFunc;
import com.ylife.form.model.UcoinGrandForm;
import com.ylife.inventory.mapper.pojo.GoodsManagerResult;
import com.ylife.inventory.mapper.pojo.InventoryGoodsResult;
import com.ylife.inventory.service.InventoryService;
import com.ylife.order.model.*;
import com.ylife.order.service.CreditOrderService;
import com.ylife.order.service.OrderService;
import com.ylife.security.annotation.SecurityResource;
import com.ylife.system.model.*;
import com.ylife.system.service.BusinessTypeService;
import com.ylife.ucoin.model.CustomerUcoinHistory;
import com.ylife.utils.Assert;
import com.ylife.utils.DateUtil;
import com.ylife.utils.ExcelUtil;
import com.ylife.utils.StringUtil;
import com.ylife.wealth.model.EnterpriseBatchGrand;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.CellRangeAddressList;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by InThEnd on 2016/5/30.
 * Excel导出
 */
@Controller
@SecurityResource(parent = "/web/UCoinManager", primary = false)
@RequestMapping(value = "/web/api/exportExcel")
public class    ExcelExportController {

    @Resource
    private GoodsManageService goodsManageService;
    @Resource
    private InventoryManageService inventoryManageService;
    @Resource
    private OrderManageService orderManageService;
    @Resource
    private OrderService orderService;
    @Resource
    private CreditOrderService creditOrderService;
    @Resource
    private UcoinBillManageService ucoinBillManageService;
    @Resource
    private UcoinGrandService ucoinGrandService;
    @Resource
    private BusinessTypeService businessTypeService;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;
    @Resource
    private CustomerManageService customerManageService;
    @Resource
    private InventoryService inventoryService;
    @Resource
    private EnterpriseInfoService enterpriseInfoService;
    @Resource
    private SystemManageService systemManageService;
    @Resource
    private FormInfoService formInfoService;

    private Parser parser = new SimpleParser();

    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/ucoinGrandFormDown", method = RequestMethod.GET)
    public ModelAndView exportUcoinGrandForm(@RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                             @RequestParam(value = "idCard",required = false)String idCard,
                                             @RequestParam(value = "start",required=false)String start,
                                             @RequestParam(value = "end",required = false)String end) {
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        idCard=StringUtil.nullOrNotBlank(idCard);
        Date startTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        Date endTime=null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }
        Page<UcoinGrandForm> formFunc=formInfoService.getUcoingrandReport(enterpriseId,startTime,endTime,idCard,null);
        List<UcoinGrandForm> ucoinGrandForms =formFunc.getContent();
        Workbook wb;
        Map<String,String> map=new LinkedHashMap<>();
        map.put("idCard","身份证号");
        map.put("fullname","姓名");
        map.put("grandAmount","邮豆发放笔数");
        map.put("marketPrice","营销邮豆总金额");
        map.put("salePrice","促销邮豆总金额");
        map.put("price","发放总金额");
        wb=ExcelUtil.excelExport(map, ucoinGrandForms);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "会员邮豆发放报表.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/ucoinGrandDetailDown", method = RequestMethod.GET)
    public ModelAndView exportUcoinGrandDetail(@RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                               @RequestParam(value = "idCard")String idCard,
                                               @RequestParam(value = "start",required=false)String start,
                                               @RequestParam(value = "end",required = false)String end) {
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Assert.notNull(idCard,"身份证号必填！");
        Date startTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        Date endTime=null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }
        Page<UcoinGrandForm> formFunc=formInfoService.getDetailGrandInfo(enterpriseId, startTime, endTime, idCard, null);
        List<UcoinGrandForm> formFuncList =formFunc.getContent();
        Workbook wb;
        Map<String,String> map=new LinkedHashMap<>();
        map.put("idCard","身份证号");
        map.put("fullname","姓名");
        map.put("createTime","发放时间");
        map.put("grandEnterprise","发放网点");
        map.put("marketPrice","营销邮豆总金额");
        map.put("salePrice","促销邮豆总金额");
        map.put("price","发放总金额");
        wb=ExcelUtil.excelExport(map, formFuncList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "会员邮豆发放明细报表.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }


    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/customerConsumeDown", method = RequestMethod.GET)
    public ModelAndView exportCustomerConsume(@RequestParam(value = "idCard",required = false)String idCard,
                                              @RequestParam(value = "start",required=false)String start,
                                              @RequestParam(value = "end",required = false)String end) {
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        idCard=StringUtil.nullOrNotBlank(idCard);
        Date startTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        Date endTime=null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }
        Page<CustomerConsume> formFunc=formInfoService.getCustomerconsumeReport(idCard, startTime, endTime, null);
        List<CustomerConsume> formFuncList =formFunc.getContent();
        Workbook wb;
        Map<String,String> map=new LinkedHashMap<>();
        map.put("idCard","身份证号");
        map.put("fullname","姓名");
        map.put("orderAmount","产生订单数");
        map.put("totalConsumePrice","订单总金额");
        map.put("backAmount","退单成功数");
        map.put("totalRefundPrice","退单总金额");
        map.put("resePrice","当前账户余额");
        wb=ExcelUtil.excelExport(map, formFuncList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "会员邮豆消耗报表.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/consumeDetailDown", method = RequestMethod.GET)
    public ModelAndView exportConsumeDetail(@RequestParam(value = "idCard")String idCard,
                                            @RequestParam(value = "start",required=false)String start,
                                            @RequestParam(value = "end",required = false)String end) {
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Assert.notNull(idCard,"身份证号必填");
        Date startTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
        }
        Date endTime=null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
        }
        Page<CustomerConsume> formFunc=formInfoService.getDetailConsume(idCard, startTime, endTime, null);
        List<CustomerConsume> formFuncList =formFunc.getContent();
        Workbook wb;
        Map<String,String> map=new LinkedHashMap<>();
        map.put("idCard","身份证号");
        map.put("fullname","姓名");
        map.put("type","单据类型");
        map.put("createTime","时间");
        map.put("orderId","单据编号");
        map.put("orderPrice","订单金额");
        map.put("backPrice","退单金额");
        wb=ExcelUtil.excelExport(map, formFuncList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "邮豆消耗明细.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/formCenter", primary = false)
    @RequestMapping(value = "/netDataDown", method = RequestMethod.GET)
    public ModelAndView exportNetData(@RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                      @RequestParam(value = "start",required=false)String start,
                                      @RequestParam(value = "end",required = false)String end) {
        start = StringUtil.nullOrNotBlank(start);
        end = StringUtil.nullOrNotBlank(end);
        Date startTime=null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DAY_FORMAT);
        }
        Date endTime=null;
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DAY_FORMAT);
        }
        Page<FormFunc> formFunc=formInfoService.getBaseReport(enterpriseId, startTime, endTime, null);
        List<FormFunc> formFuncList =formFunc.getContent();
        Workbook wb;
        Map<String,String> map=new LinkedHashMap<>();
        map.put("grandEnterprise","网点名称");
        map.put("newCustomerAmount","新增会员数");
        map.put("expenditure","邮豆发放笔数");
        map.put("totalMarketPrice","营销邮豆发放金额");
        map.put("totalSalePrice","促销邮豆发放金额");
        map.put("totalPrice","发放总金额");
        wb=ExcelUtil.excelExport(map, formFuncList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "网点基础数据统计报表.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }


    @SecurityResource(parent = "/web/itemManager", primary = false)
    @RequestMapping(value = "/goodsManagerDown", method = RequestMethod.GET)
    public ModelAndView exportExcel(
            @RequestParam(value = "goodsInfoId", required = false) String goodsInfoIds,
            @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
            @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
            @RequestParam(value = "goodsInfoAdded", required = false) String goodsInfoAdded,
            @RequestParam(value = "brandId", required = false) Long brandId,
            @RequestParam(value = "typeId", required = false) Long typeId,
            @RequestParam(value = "thirdId", required = false) Long thirdId) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);
        goodsInfoAdded = Constants.nullOrNotBlank(goodsInfoAdded);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("goodsInfoName", "货品名称");
        map.put("specString", "货品规格");
        map.put("goodsInfoItemNo", "货品编号");
        map.put("goodsInfoPreferPrice","商城价");
        map.put("goodsInfoAdded","是否上架");
        map.put("goodsInfoTypeName", "货品类型");
        map.put("goodsBrand", "货品品牌");
        map.put("thirdName", "货品所属商家");

        Workbook wb;
        if (goodsInfoIds.equals("all")) {
            Page<GoodsManagerResult> page = goodsManageService.getGoods(goodsInfoName, goodsInfoItemNo, goodsInfoAdded, brandId, typeId, thirdId, null);
            List<GoodsManagerResult> list = page.getContent();

            wb = ExcelUtil.excelExport(map, list);
        } else {
            long[] ids = parser.parseJSON(goodsInfoIds, new TypeToken<long[]>() {
            });
            List<GoodsManagerResult> goodsManagerResults = new ArrayList<>();
            for (long id : ids) {
                GoodsManagerResult goodsManagerResult = goodsManageService.getByGoodsInfoId(id);
                goodsManagerResults.add(goodsManagerResult);
            }
            wb = ExcelUtil.excelExport(map, goodsManagerResults);
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "商品列表.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }



    @SecurityResource(parent = "/web/businessType",primary = false)
    @RequestMapping(value = "/systemChangeHistory", method = RequestMethod.GET)
    public ModelAndView exportSystemChangeHistory(@RequestParam(value = "typeId", required = false) Integer typeId,
                               @RequestParam(value = "start", required = false) String start,
                               @RequestParam(value = "end", required = false) String end
                               ) {
        Date startTime = null;
        Date endTime = null;
        if (start != null) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DATE_FORMAT);
        }
        if (end != null) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DATE_FORMAT);
        }
        Page<BusinessTypeHistory> page = systemManageService.getHistories(typeId, startTime, endTime, null);
        List<BusinessTypeHistory> list=page.getContent();
        Map<String,String> map=new LinkedHashMap<>();
        map.put("date","时间");
        map.put("typeName","业务类型");
        map.put("beforeEx","变动前计算公式");
        map.put("afterEx","变动后计算公式");
        map.put("operation","变动说明");
        map.put("note","备注");
        map.put("operator","操作者账号");
        Workbook wb;
        wb=ExcelUtil.excelExport(map,list);

        Map<String, Object> outMaqp = new HashMap<>();
        outMaqp.put("wb", wb);
        outMaqp.put("fileName", "营销邮豆计算公式变更记录.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, outMaqp);
    }

    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    @RequestMapping(value = "/inventoryGoodsDown", method = RequestMethod.GET)
    public ModelAndView exportInventoryExcel(
            @RequestParam(value = "goodsInfoId", required = false) String goodsInfoIds,
            @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
            @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
            @RequestParam(value = "brandId", required = false) Long brandId,
            @RequestParam(value = "thirdId", required = false) Long thirdId) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("goodsInfoName", "货品名称");
        map.put("goodsBrand", "货品品牌");
        map.put("specString", "货品规格");
        map.put("goodsNumber", "货品编号");
        map.put("available", "可用库存");
        map.put("inventory", "库存总量");
        map.put("goodsMerchants","货品所属商家");
        Workbook wb;

        if (goodsInfoIds.equals("all")) {
            Page<InventoryGoodsResult> page = inventoryManageService.getInventoryGoodsResult(goodsInfoName, goodsInfoItemNo, brandId, thirdId, null);
            List<InventoryGoodsResult> list = page.getContent();
            wb = ExcelUtil.excelExport(map, list);
        } else {
            long[] ids = parser.parseJSON(goodsInfoIds, new TypeToken<long[]>() {
            });
            List<InventoryGoodsResult> inventoryGoodsResults = new ArrayList<>();
            for (long id : ids) {
                InventoryGoodsResult inventoryGoodsResult = inventoryManageService.getInventoryGoodsResultByPrimarykey(id);
                inventoryGoodsResults.add(inventoryGoodsResult);
            }
            wb = ExcelUtil.excelExport(map, inventoryGoodsResults);
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "库存货品.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    @RequestMapping(value = "/inventoryWarningGoodsDown", method = RequestMethod.GET)
    public ModelAndView exportInventoryWarningExcel(
            @RequestParam(value = "goodsInfoId", required = false) String goodsInfoIds,
            @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
            @RequestParam(value = "goodsInfoItemNo", required = false) String goodsInfoItemNo,
            @RequestParam(value = "brandId", required = false) Long brandId,
            @RequestParam(value = "thirdId", required = false) Long thirdId) {
        goodsInfoName = Constants.nullOrNotBlank(goodsInfoName);
        goodsInfoItemNo = Constants.nullOrNotBlank(goodsInfoItemNo);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("goodsInfoName", "货品名称");
        map.put("goodsBrand", "货品品牌");
        map.put("specString", "货品规格");
        map.put("goodsNumber", "货品编号");
        map.put("available", "可用库存");
        map.put("inventory", "库存总量");
        map.put("warning", "预警值");
        map.put("goodsMerchants","货品所属商家");
        Workbook wb;
        if (goodsInfoIds.equals("all")) {

            Page<InventoryGoodsResult> page = inventoryManageService.getWarningGoods(goodsInfoName, goodsInfoItemNo, brandId, thirdId, null);
            List<InventoryGoodsResult> list = page.getContent();
            wb = ExcelUtil.excelExport(map, list);
        } else {
            long[] ids = parser.parseJSON(goodsInfoIds, new TypeToken<long[]>() {
            });
            List<InventoryGoodsResult> inventoryGoodsResults = new ArrayList<>();
            for (long id : ids) {
                InventoryGoodsResult inventoryGoodsResult = inventoryManageService.getWarningGood(id);
                inventoryGoodsResults.add(inventoryGoodsResult);
            }
            wb = ExcelUtil.excelExport(map, inventoryGoodsResults);
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "库存预警货品.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/accountManager", primary = false)
    @RequestMapping(value = "/orderDown", method = RequestMethod.GET)
    public ModelAndView exportOrderExcel(@RequestParam(value = "orderNoString", required = false) String orderNoString,
                                         @RequestParam(value = "status", required = false) OrderStatus status,
                                         @RequestParam(value = "selfPick", required = false) Boolean selfPick,
                                         @RequestParam(value = "username", required = false) String username,
                                         @RequestParam(value = "receiver", required = false) String receiver,
                                         @RequestParam(value = "contactPhone", required = false) String contactPhone,
                                         @RequestParam(value = "createStart", required = false) String createStart,
                                         @RequestParam(value = "createEnd", required = false) String createEnd,
                                         @RequestParam(value = "payStart", required = false) String payStart,
                                         @RequestParam(value = "payEnd", required = false) String payEnd) {
        if (status != null && status == OrderStatus.WAIT_DELIVER) {
            status = OrderStatus.PAYED;
            selfPick = false;
        } else if (status != null && status == OrderStatus.WAIT_PICKUP) {
            status = OrderStatus.PAYED;
            selfPick = true;
        }
        username = Constants.nullOrNotBlank(username);
        receiver = Constants.nullOrNotBlank(receiver);
        contactPhone = Constants.nullOrNotBlank(contactPhone);
        Date createStartTime = null;
        if (!StringUtil.isBlank(createStart)) {
            createStartTime = DateUtil.fromString(createStart, Constants.DEFAULT_DATE_FORMAT);
        }
        Date createEndTime = null;
        if (!StringUtil.isBlank(createEnd)) {
            createEndTime = DateUtil.fromString(createEnd, Constants.DEFAULT_DATE_FORMAT);
        }

        Date payStartTime = null;
        if (!StringUtil.isBlank(payStart)) {
            payStartTime = DateUtil.fromString(payStart, Constants.DEFAULT_DATE_FORMAT);
        }
        Date payEndTime = null;
        if (!StringUtil.isBlank(payEnd)) {
            payEndTime = DateUtil.fromString(payEnd, Constants.DEFAULT_DATE_FORMAT);
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFCellStyle style = wb.createCellStyle();
        DataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat("@"));
        sheet.setDefaultColumnStyle(0, style);
        sheet.setDefaultColumnStyle(1, style);
        sheet.setDefaultColumnStyle(7, style);
        //构造表头
        HSSFRow headRow = sheet.createRow(0);
        List<String> headList = Arrays.asList("订单号", "下单用户身份证号", "下单用户姓名", "下单时间", "付款时间",
                "商品名称", "购买数量", "订单金额", "订单状态", "配送方式", "商家",
                "收件人姓名", "收件人电话", "收件人地址", "自提网点", "快递公司",
                "快递单号");
        for (int i = 0; i < headList.size(); i++) {
            HSSFCell cell = headRow.createCell(i);
            cell.setCellValue(headList.get(i));
        }
        //填充表格内容
        List<Order> orderList = new ArrayList<>();
        if (orderNoString.equals("all")) {
            Page<Order> orderPage = orderManageService.getOrders(null,null, status, selfPick, username, receiver, contactPhone, createStartTime, createEndTime, payStartTime, payEndTime, null);
            orderList = orderPage.getContent();
        } else {
            Long[] orderNos = parser.parseJSON(orderNoString, new TypeToken<Long[]>() {
            });
            for (Long orderNo : orderNos) {
                Page<Order> orderPage = orderManageService.getOrders(orderNo,null, status, selfPick, username, receiver, contactPhone, createStartTime, createEndTime, payStartTime, payEndTime, null);
                Order order=orderPage.getContent().get(0);
                orderList.add(order);
            }
        }

        for (int i = 0; i < orderList.size(); i++) {
            HSSFRow orderRow = sheet.createRow(i + 1);
            Order order = orderList.get(i);
            Order.CustomerInfo customerInfo = order.getCustomerInfo();
            Order.Business business = order.getBusiness();
            Long id = order.getEnterpriseId();
            HSSFCell cell = orderRow.createCell(0);
            cell.setCellValue(order.getOrderCode() + "");
            cell = orderRow.createCell(1);
            cell.setCellValue(customerInfo.getUsername());
            cell = orderRow.createCell(2);
            cell.setCellValue(customerInfo.getFullName());
            cell = orderRow.createCell(3);
            Date createTime = order.getCreateTime();
            cell.setCellValue(order.getCreateTime() == null ? "" : DateUtil.formatToString(createTime, "yyyy/MM/dd HH:mm"));
            cell = orderRow.createCell(4);
            Date payTime = order.getPayTime();
            cell.setCellValue(order.getPayTime() == null ? "" : DateUtil.formatToString(payTime, "yyyy/MM/dd HH:mm"));

            //拼写商品名称信息
            cell = orderRow.createCell(5);
            List<OrderGoods> orderGoodsList = order.getOrderGoodsList();
            String goodsNameString = "";
            Long amount = 0L;
            for (OrderGoods orderGoods : orderGoodsList) {
                String goodsName = orderGoods.getGoodsInfoName() + "(" + orderGoods.getGoodsInfoNum() + ")";
                goodsNameString += goodsName + ",";
                amount += orderGoods.getGoodsInfoNum();
            }
            goodsNameString = goodsNameString.substring(0, goodsNameString.length() - 1);
            cell.setCellValue(goodsNameString);
            cell = orderRow.createCell(6);
            cell.setCellValue(amount);
            cell = orderRow.createCell(7);
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String orderPrice = decimalFormat.format(order.getOrderPrice());
            cell.setCellValue(orderPrice);

            if (order.getSelfPick()) {
                cell = orderRow.createCell(8);
                OrderStatus orderStatus=order.getOrderStatus();
                if (orderStatus.equals(OrderStatus.PAYED)) {
                    cell.setCellValue("待提货");
                } else {
                    cell.setCellValue(order.getOrderStatus().toString());
                }
                cell = orderRow.createCell(9);
                cell.setCellValue("用户自提");
                //商家
                cell = orderRow.createCell(10);

                cell.setCellValue(id == null || enterpriseInfoService.getEnterpriseInfo(id) == null ? "" : enterpriseInfoService.getEnterpriseInfo(id).getEnterpriseName());
                cell = orderRow.createCell(11);
                cell.setCellValue(customerInfo.getFullName());
                cell = orderRow.createCell(12);
                cell.setCellValue(customerInfo.getContactPhone());
                cell = orderRow.createCell(13);
                cell.setCellValue("");
                cell = orderRow.createCell(14);
                cell.setCellValue(id == null || enterpriseInfoService.getEnterpriseInfo(id) == null ? "" : enterpriseInfoService.getEnterpriseInfo(id).getAccountName());
                //快递公司
                cell = orderRow.createCell(15);
                cell.setCellValue("");
                //快递单号
                cell = orderRow.createCell(16);
                cell.setCellValue("");
            } else {
                cell = orderRow.createCell(8);
                cell.setCellValue(order.getOrderStatus().toString());
                cell = orderRow.createCell(9);
                cell.setCellValue("快递配送");
                cell = orderRow.createCell(10);
                cell.setCellValue(business.getBusinessName());
                cell = orderRow.createCell(11);
                cell.setCellValue(order.getShippingPerson());
                cell = orderRow.createCell(12);
                cell.setCellValue(order.getShippingMobile());
                cell = orderRow.createCell(13);
                String province=order.getShippingProvince();
                String city=order.getShippingCity();
                String district=order.getShippingCounty();
                String detailAddress=order.getShippingAddress();
                String address=province+city+district+detailAddress;
                cell.setCellValue(address);
                //自提网点
                cell = orderRow.createCell(14);
                cell.setCellValue("");
                //快递公司
                List<OrderContainerRelation> orderContainerRelationList=order.getOrderContainerRelations();
                if(orderContainerRelationList.size()!=0){
                OrderContainerRelation orderContainerRelation=orderContainerRelationList.get(0);
                    cell = orderRow.createCell(15);
                   cell.setCellValue(orderContainerRelation.getExpressName());
                    cell = orderRow.createCell(16);
                    cell.setCellValue(orderContainerRelation.getExpressNo());}
                else {cell=orderRow.createCell(15);
                    cell.setCellValue("");
                    cell=orderRow.createCell(16);
                    cell.setCellValue("");

                }

                //快递单号

            }
        }


        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "订单.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/accountManager", primary = false)
    @RequestMapping(value = "/backOrderDown", method = RequestMethod.GET)
    public ModelAndView exportBackOrderExcel(@RequestParam(value = "backOrderNo", required = false) String backOrderNos,
                                             @RequestParam(value = "phoneNo", required = false) String phoneNo,
                                             @RequestParam(value = "orderNo", required = false) Long orderNo,
                                             @RequestParam(value = "status", required = false) CreditOrderStatus status,
                                             @RequestParam(value = "start", required = false) String start,
                                             @RequestParam(value = "end", required = false) String end) {
        phoneNo = Constants.nullOrNotBlank(phoneNo);
        Date startTime = null;
        if (!StringUtil.isBlank(start)) {
            startTime = DateUtil.fromString(start, Constants.DEFAULT_DATE_FORMAT);
        }
        Date endTime = null;
        if (!StringUtil.isBlank(end)) {
            endTime = DateUtil.fromString(end, Constants.DEFAULT_DATE_FORMAT);
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFCellStyle style = wb.createCellStyle();
        DataFormat format = wb.createDataFormat();
        style.setDataFormat(format.getFormat("@"));
        sheet.setDefaultColumnStyle(0, style);
        sheet.setDefaultColumnStyle(1, style);
        sheet.setDefaultColumnStyle(2, style);
        sheet.setDefaultColumnStyle(8, style);
        sheet.setDefaultColumnStyle(9, style);
        //构造表头
        HSSFRow headRow = sheet.createRow(0);
        List<String> headList = Arrays.asList("退单号", "订单号", "下单用户身份证号", "下单用户姓名", "订单付款时间",
                "退单申请时间", "退货商品名称", "退货商品数量", "订单金额", "退单金额",
                "订单快递", "订单快递单号", "收件人姓名", "收件人电话", "商家", "退单状态",
                "退单原因", "问题说明");
        for (int i = 0; i < headList.size(); i++) {
            HSSFCell cell = headRow.createCell(i);
            cell.setCellValue(headList.get(i));
        }

        //填充表格内容
        List<CreditOrder> creditOrderList = new ArrayList<>();
        if (backOrderNos.equals("all")) {
            Page<CreditOrder> creditOrderPage = orderManageService.getBackOrder(null,null, phoneNo, orderNo, status, startTime, endTime, null);
            creditOrderList = creditOrderPage.getContent();
        } else {
            Long[] crederOrderNo = parser.parseJSON(backOrderNos, new TypeToken<Long[]>() {
            });
            for (Long backOrderNo : crederOrderNo) {
                CreditOrder backOrder = creditOrderService.selectByCreditOrderCode(backOrderNo);
                creditOrderList.add(backOrder);
            }
        }

        for (int i = 0; i < creditOrderList.size(); i++) {
            HSSFRow backOrderRow = sheet.createRow(i + 1);
            CreditOrder creditOrder = creditOrderList.get(i);
            CreditOrder.Business business = creditOrder.getBusiness();
            CreditOrder.OriginalOrder originalOrder = creditOrder.getOrder();
            Long orderCode = originalOrder.getOrderCode();
            Order order = orderService.getOrder(orderCode);
            Order.CustomerInfo customerInfo = order.getCustomerInfo();

            HSSFCell cell = backOrderRow.createCell(0);
            cell.setCellValue(creditOrder.getBackOrderCode() + "");
            cell = backOrderRow.createCell(1);
            cell.setCellValue(orderCode + "");
            cell = backOrderRow.createCell(2);
            cell.setCellValue(customerInfo.getUsername());
            cell = backOrderRow.createCell(3);
            cell.setCellValue(customerInfo.getFullName());
            cell = backOrderRow.createCell(4);
            Date payTime = order.getPayTime();
            DateUtil.formatToString(payTime, "yyyy/MM/dd HH/mm");
            cell.setCellValue(payTime == null ? "" : DateUtil.formatToString(payTime, "yyyy/MM/dd HH:mm"));
            cell = backOrderRow.createCell(5);
            Date backTime = creditOrder.getBackTime();
            cell.setCellValue(DateUtil.formatToString(backTime, "yyyy/MM/dd HH:mm"));
            cell = backOrderRow.createCell(6);
            List<BackOrderGoods> backOrderGoodses = creditOrder.getBackOrderGoods();
            String backGoodsName = " ";
            Long backAmount = 0L;
            for (BackOrderGoods backOrderGoods : backOrderGoodses) {
                String goodsINfoName = backOrderGoods.getGoodsInfoName() + "(" + backOrderGoods.getGoodsNum() + ")";
                backGoodsName += goodsINfoName + ",";
                backAmount += backOrderGoods.getGoodsNum();
            }

            backGoodsName = backGoodsName.substring(0, backGoodsName.length() - 1);
            cell.setCellValue(backGoodsName);
            cell = backOrderRow.createCell(7);
            cell.setCellValue(backAmount);
            cell = backOrderRow.createCell(8);
            DecimalFormat decimalFormat = new DecimalFormat();
            String orderPrice = decimalFormat.format(originalOrder.getOrderPrice());
            cell.setCellValue(orderPrice);
            cell = backOrderRow.createCell(9);
            String backPrice = decimalFormat.format(creditOrder.getBackPrice());
            cell.setCellValue(backPrice);
            if (!originalOrder.getSelfPick()) {
                List<OrderContainerRelation> orderContainerRelationList=order.getOrderContainerRelations();
                if(orderContainerRelationList.size()!=0){
                    OrderContainerRelation orderContainerRelation=orderContainerRelationList.get(0);
                    cell = backOrderRow.createCell(10);
                    cell.setCellValue(orderContainerRelation.getExpressName());
                    cell = backOrderRow.createCell(11);
                    cell.setCellValue(orderContainerRelation.getExpressNo());}
                else {cell=backOrderRow.createCell(10);
                    cell.setCellValue("");
                    cell=backOrderRow.createCell(11);
                    cell.setCellValue("");}
              /*      cell = backOrderRow.createCell(10);
                cell.setCellValue("订单快递");
                cell = backOrderRow.createCell(11);
                cell.setCellValue("订单快递单号");*/
                cell = backOrderRow.createCell(12);
                cell.setCellValue(creditOrder.getBackCollectPerson());
                cell = backOrderRow.createCell(13);
                cell.setCellValue(creditOrder.getBackCollectPhone());
                cell = backOrderRow.createCell(14);
                cell.setCellValue(business.getBusinessName());
            } else {
                cell = backOrderRow.createCell(10);
                cell.setCellValue("");
                cell = backOrderRow.createCell(11);
                cell.setCellValue("");
                cell = backOrderRow.createCell(12);
                cell.setCellValue(customerInfo.getFullName());
                cell = backOrderRow.createCell(13);
                cell.setCellValue(customerInfo.getContactPhone());
                cell = backOrderRow.createCell(14);
                Long id = order.getEnterpriseId();
                cell.setCellValue(id == null ? "" : enterpriseInfoService.getEnterpriseInfo(id).getEnterpriseName());
            }
            cell = backOrderRow.createCell(15);
            cell.setCellValue(creditOrder.getBackCheck().toString());
            cell = backOrderRow.createCell(16);
            cell.setCellValue(creditOrder.getBackReason().getTag());
            cell = backOrderRow.createCell(17);
            cell.setCellValue(creditOrder.getBackRemark());
        }

        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "退单.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/BillManager", primary = false)
    @RequestMapping(value = "/myGrandDown", method = RequestMethod.GET)
    public ModelAndView exportGrandExcel(@RequestParam(value = "code", required = false) String tradeCodes,
                                         @RequestParam(value = "start", required = false) String start,
                                         @RequestParam(value = "end", required = false) String end) {
        Date startTime = Constants.nullOrMorning(start);
        Date endTime = Constants.nullOrNight(end);

        Map<String, String> map = new LinkedHashMap<>();
        map.put("code", "交易单号");
        map.put("fee", "发放邮宝金额");
        map.put("sendType", "交易类型");
        Workbook wb;
        if (tradeCodes.equals("all")) {
            Page<EnterpriseBatchGrand> page = ucoinBillManageService.getGrands(null, startTime, endTime, null);
            List<EnterpriseBatchGrand> list = page.getContent();
            wb = ExcelUtil.excelExport(map, list);
        } else {
            Long[] codes = parser.parseJSON(tradeCodes, new TypeToken<Long[]>() {
            });
            List<EnterpriseBatchGrand> enterpriseBatchGrands = new ArrayList<>();
            for (long code : codes) {
                Page<EnterpriseBatchGrand> page = ucoinBillManageService.getGrands(code, startTime, endTime, null);
                enterpriseBatchGrands = page.getContent();
                //EnterpriseBatchGrand enterpriseBatchGrand=enterpriseBatchGrandMapper;
                //enterpriseBatchGrands.add(enterpriseBatchGrand);
            }
            wb = ExcelUtil.excelExport(map, enterpriseBatchGrands);
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "账单管理.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/UbaoSend", primary = false)
    @RequestMapping(value = "/grandHistory", method = RequestMethod.GET)
    public ModelAndView grandHistory(@RequestParam(value = "enterpriseId") Long enterpriseId,
                                     @RequestParam(value = "typeId") Integer typeId,
                                     @RequestParam(value = "start") String start,
                                     @RequestParam(value = "end") String end) {

        Date startTime = null;
        Date endTime = null;
        if(start!=null){
            startTime=DateUtil.fromString(start,Constants.DEFAULT_DAY_FORMAT);
            startTime=DateUtil.getMorning(startTime);
        }
        if(end!=null){
            endTime=DateUtil.fromString(end,Constants.DEFAULT_DAY_FORMAT);
            endTime=DateUtil.getNight(endTime);
        }
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow(0);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //构造表头
        Page<CustomerUcoinHistory> customerUcoinHistoryPage = ucoinGrandService.getGrandHistory(enterpriseId, typeId, startTime, endTime, null);
        List<CustomerUcoinHistory> customerUcoinHistoryList = customerUcoinHistoryPage.getContent();
        List<String> jsonKey = new ArrayList<>();
        for (CustomerUcoinHistory customerUcoinHistory : customerUcoinHistoryList) {
            String paramjson = customerUcoinHistory.getParamJson();
            Map<String, String> map = parser.parseJSON(paramjson, new TypeToken<Map<String, String>>() {
            });
            if (map != null) {
                for (String key : map.keySet()) {
                    if (!jsonKey.contains(key)) {
                        jsonKey.add(key);
                    }
                }
            }
        }

        HSSFCell cell = row.createCell(0);
        sheet.setColumnWidth(0, 24 * 256);
        cell.setCellValue("网点名称");
        cell.setCellStyle(style);

        cell = row.createCell(1);
        sheet.setColumnWidth(1, 30 * 256);
        cell.setCellValue("身份证号");
        cell.setCellStyle(style);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell = row.createCell(2);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        cell = row.createCell(3);
        cell.setCellValue("营销邮豆金额");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("促销邮豆金额");
        cell.setCellStyle(style);
        cell = row.createCell(5);
        cell.setCellValue("邮豆总金额");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("备注");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        sheet.setColumnWidth(7, 30 * 256);
        cell.setCellValue("邮豆发放时间");
        cell.setCellStyle(style);

        cell = row.createCell(8);
        cell.setCellValue("业务类型");
        cell.setCellStyle(style);
        for (int i = 0; i < jsonKey.size(); i++) {
            cell = row.createCell(i + 9);
            cell.setCellValue(jsonKey.get(i));
            cell.setCellStyle(style);
        }

    /*    //key：网点id；value:该网点发放记录总条数
        Map<Long, Integer> keyMap = new LinkedHashMap<>();
        Long eId = -1l;
        int k = 0;*/
        //填充表格内容
        for (int j = 0; j < customerUcoinHistoryList.size(); j++) {
            row = sheet.createRow(j + 1);
            CustomerUcoinHistory customerUcoinHistory = customerUcoinHistoryList.get(j);
            ChinapostCustomer customer = chinapostCustomerService.getCustomer(customerUcoinHistory.getCustomerId());
            cell = row.createCell(0);
            Long id = customerUcoinHistory.getEnterpriseId();
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.getEnterpriseInfo(id);
            cell.setCellValue(enterpriseInfo.getEnterpriseName());
            cell = row.createCell(1);
            cell.setCellValue(customer.getIdcardNo());
            cell.setCellType(Cell.CELL_TYPE_STRING);
            row.createCell(2).setCellValue(customer.getFullname());
            BusinessType businessType = businessTypeService.getByTypeId(customerUcoinHistory.getTypeId());
            row.createCell(3).setCellValue(customerUcoinHistory.getMarketPrice().toString());
            Assert.notNull(customerUcoinHistory.getSalesPrice());
            row.createCell(4).setCellValue(customerUcoinHistory.getSalesPrice().toString());
            row.createCell(5).setCellValue(customerUcoinHistory.getPrice().toString());
            Date createTime = customerUcoinHistory.getCreateTime();
            String remark = customerUcoinHistory.getRemark();
            remark = remark == null ? "" : remark;
            row.createCell(6).setCellValue(remark);
            row.createCell(7).setCellValue(createTime == null ? "" : DateUtil.formatToString(createTime, "yyyy/MM/dd HH:mm"));
            row.createCell(8).setCellValue(businessType.getTypeName());
          /*  if (customerUcoinHistory.getEnterpriseId() == eId) {
                k++;
                keyMap.put(eId, k);
            } else {
                eId = customerUcoinHistory.getEnterpriseId();
                k++;
                keyMap.put(eId, k);
            }*/
            String paramJson = customerUcoinHistory.getParamJson();
            Map<String, String> map = parser.parseJSON(paramJson, new TypeToken<Map<String, String>>() {
            });
            if (map == null) {
                map = new HashMap<>();
            }
            for (int i = 0; i < jsonKey.size(); i++) {
                String value = map.get(jsonKey.get(i));
                /*int paramValueId=Integer.valueOf(map.get(jsonKey.get(i))).intValue();
                ParamValue paramValue=paramValueService.getParamValue(paramValueId);
                String value=paramValue.getParamValueName();*/
                if (value == null) {
                    value = "";
                }
                row.createCell(i + 9).setCellValue(value);
            }
        }

    /*    //合并网点名单元格
        int s = 1;
        for (Long id : keyMap.keySet()) {
            int amount = keyMap.get(id);
            CellRangeAddress cellAddress = new CellRangeAddress(s, amount, 0, 0);
            sheet.addMergedRegion(cellAddress);
            row = sheet.getRow(s);
            cell = row.createCell(0);
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.getEnterpriseInfo(id);
            cell.setCellValue(enterpriseInfo.getEnterpriseName());
            cell.setCellStyle(style);
            s = amount + 1;
        }*/


        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        map.put("fileName", "邮宝发放记录.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }

    @SecurityResource(parent = "/web/memberManager", primary = false)
    @RequestMapping(value = "/customerInfoDown", method = RequestMethod.GET)
    public ModelAndView exportCustomerInfoExcel(@RequestParam(value = "idCard", required = false) String idCard,
                                                @RequestParam(value = "linkPhone", required = false) String linkPhone,
                                                @RequestParam(value = "name", required = false) String name,
                                                @RequestParam(value = "isActive", required = false) Boolean isActive,
                                                @RequestParam(value = "isPhoneChecked", required = false) Boolean isPhoneChecked,
                                                @RequestParam(value = "managerNo", required = false) String managerNo,
                                                @RequestParam(value = "enterpriseId",required = false)Long enterpriseId,
                                                @RequestParam(value = "tag", required = false) String tag) {

        idCard = StringUtil.nullOrNotBlank(idCard);
        linkPhone = StringUtil.nullOrNotBlank(linkPhone);
        name = StringUtil.nullOrNotBlank(name);
        managerNo = StringUtil.nullOrNotBlank(managerNo);
        List<String> heads = new ArrayList<>();
        heads.add("身份证号");
        heads.add("姓名");
        heads.add("性别");
        heads.add("联系地址");
        heads.add("联系电话");
        heads.add("客户经理号");
        heads.add("账户邮豆余额");
        heads.add("账号状态");
        heads.add("账号创建的网点名称");
        heads.add("账号创建者账号");
        heads.add("创建时间");

        Workbook wb;
        Page<ChinapostCustomer> page = customerManageService.getCustomers(idCard, linkPhone, name, isActive,
                isPhoneChecked, managerNo, tag, enterpriseId, null);
        List<ChinapostCustomer> sonList = page.getContent();

        List<List<String>> outList = new ArrayList<>();
        for (ChinapostCustomer customer : sonList) {
            List<String> content = new ArrayList<>();
            content.add(customer.getIdcardNo());
            content.add(customer.getFullname());
            String male = customer.getIdcardNo().substring(16, 17);
            content.add(Integer.parseInt(male) % 2 == 0 ? "女" : "男");
            content.add(customer.getContactAddr());
            content.add(customer.getContactPhone());
            content.add(customer.getManagerNo());
            content.add(customer.getTotalUcoin().toString());
            content.add(customer.getIsActive() ? "已激活" : "未激活");
            content.add(customer.getAccountName());
            content.add(customer.getUsername());
            Date createTime = customer.getCreateTime();
            content.add(createTime == null ? "" : DateUtil.formatToString(createTime, "yyyy/MM/dd HH:mm"));
            outList.add(content);
        }

        wb = ExcelUtil.excelExport(heads, outList);

        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "会员信息.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    @RequestMapping(value = "/downByGoodsInfo", method = RequestMethod.GET)
    public ModelAndView exportInventoryGoodsByGoodsInfo(@RequestParam("enterpriseId") long enterpriseId,
                                                        @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                                        @RequestParam(value = "goodsNumber", required = false) String goodsNumber,
                                                        @RequestParam(value = "brandId", required = false) Long brandId,
                                                        @RequestParam(value = "thirdId", required = false) Long thirdId) {
        goodsInfoName = StringUtil.nullOrNotBlank(goodsInfoName);
        goodsNumber = StringUtil.nullOrNotBlank(goodsNumber);
        List<String> heads = new ArrayList<>();
        heads.add("货品名称");
        heads.add("货品规格");
        heads.add("货品编号");
        heads.add("库存");
        heads.add("可用库存");
        heads.add("品牌");
        heads.add("所属商家");
        Workbook wb;
        Page<InventoryGoodsResult> inventoryGoodsResults = inventoryManageService.queryByGoodsInfo(enterpriseId, goodsInfoName, goodsNumber, brandId, thirdId, null);
        List<InventoryGoodsResult> sonList = inventoryGoodsResults.getContent();
        List<List<String>> outList = new ArrayList<>();
        for (InventoryGoodsResult model : sonList) {
            List<String> content = new ArrayList<>();
            content.add(model.getGoodsInfoName());
            content.add(model.getSpecString());
            content.add(model.getGoodsNumber());
            content.add(model.getSumInventory());
            content.add(model.getSumAvailable());
            content.add(model.getGoodsBrand());
            content.add(model.getGoodsMerchants());
            outList.add(content);
        }
        wb = ExcelUtil.excelExport(heads, outList);
        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "根据货品查询库存.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/InventoryManager", primary = false)
    @RequestMapping(value = "/downByInventory", method = RequestMethod.GET)
    public ModelAndView exportInventoryGoodsByInventory(@RequestParam("enterpriseId") long enterpriseId,
                                                        @RequestParam(value = "goodsInfoName", required = false) String goodsInfoName,
                                                        @RequestParam(value = "goodsNumber", required = false) String goodsNumber,
                                                        @RequestParam(value = "brandId", required = false) Long brandId,
                                                        @RequestParam(value = "thirdId", required = false) Long thirdId) {
        goodsInfoName = StringUtil.nullOrNotBlank(goodsInfoName);
        goodsNumber = StringUtil.nullOrNotBlank(goodsNumber);
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet();
        HSSFRow row = sheet.createRow(0);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        //构造表头
        //第一行
        HSSFCell cell = row.createCell(0);
        sheet.setColumnWidth(0, 20 * 256);
        cell.setCellValue("网点名称");
        cell.setCellStyle(style);
        cell.setCellType(Cell.CELL_TYPE_STRING);
        cell = row.createCell(1);
        cell.setCellValue("货品名称");
        sheet.setColumnWidth(1, 40 * 256);
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("货品规格");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("货品编号");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("库存");
        sheet.setColumnWidth(4, 10 * 256);
        cell.setCellStyle(style);
        cell = row.createCell(5);
        sheet.setColumnWidth(5, 10 * 256);
        cell.setCellValue("可用库存");
        cell.setCellStyle(style);
        cell = row.createCell(6);
        cell.setCellValue("商家");
        cell.setCellStyle(style);
        cell = row.createCell(7);
        cell.setCellValue("品牌");
        cell.setCellStyle(style);
        //填充表格内容
        Page<InventoryGoodsResult> page = inventoryService.queryByInventory(enterpriseId, goodsInfoName, goodsNumber, brandId, thirdId, null);
        List<InventoryGoodsResult> sonList = page.getContent();
        //key：网点id；value：网点商品数量
        Map<Long, Integer> map = new LinkedHashMap<>();
        Long eId = -1L;
        int j = 0;
        for (int i = 0; i < sonList.size(); i++) {
            InventoryGoodsResult model = sonList.get(i);
            row = sheet.createRow(i + 1);
            cell = row.createCell(1);
            cell.setCellValue(model.getGoodsInfoName());
            cell = row.createCell(2);
            cell.setCellValue(model.getSpecString());
            cell = row.createCell(3);
            cell.setCellValue(model.getGoodsNumber());
            cell = row.createCell(4);
            cell.setCellValue(model.getInventory());
            cell = row.createCell(5);
            cell.setCellValue(model.getAvailable());
            cell = row.createCell(6);
            cell.setCellValue(model.getGoodsMerchants());
            cell = row.createCell(7);
            cell.setCellValue(model.getGoodsBrand());
            if (model.getEnterpriseId().equals(eId)) {
                j++;
                map.put(eId, j);
            } else {
                eId = model.getEnterpriseId();
                j++;
                map.put(eId, j);
            }
        }

        //合并网点名单元格
        int s = 1;
        for (Long id : map.keySet()) {
            int amount = map.get(id);
            CellRangeAddress cellAddress = new CellRangeAddress(s, amount, 0, 0);
            sheet.addMergedRegion(cellAddress);
            row = sheet.getRow(s);
            cell = row.createCell(0);
            EnterpriseInfo enterpriseInfo = enterpriseInfoService.getEnterpriseInfo(id);
            cell.setCellValue(enterpriseInfo.getAccountName());
            cell.setCellStyle(style);
            s = amount + 1;
        }


        Map<String, Object> map1 = new HashMap<>();
        map1.put("wb", wb);
        map1.put("fileName", "根据仓库下载库存货品.xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map1);
    }

    @SecurityResource(parent = "/web/UbaoSend", primary = false)
    @RequestMapping(value = "/templateDown", method = RequestMethod.GET)
    public ModelAndView ExportTemplateExcel(Integer typeId) {

        HSSFWorkbook wb = new HSSFWorkbook();
        //创建主表
        HSSFSheet templateSheet = wb.createSheet("模板");
     /*   //创建省市数据源表
        HSSFSheet provinceSheet = wb.createSheet("省市信息表");
        //创建市区数据源表
        HSSFSheet citySheet = wb.createSheet("市区信息表");*/
        //创建参数信息表
        HSSFSheet paramSheet = wb.createSheet("参数信息表");

     /*   //获取省份信息
        List<Province> provinces = addressService.getProvinces();
        int rowNumber = 0;
        for (int i = 0; i < provinces.size(); i++) {
            HSSFRow provinceRow = provinceSheet.createRow(i);
            HSSFCell provinceCell = provinceRow.createCell(0);
            provinceCell.setCellValue(provinces.get(i).getProvinceName());
            //根据省份获取相应市
            List<City> cities = addressService.getCities(provinces.get(i).getProvinceId());
            //为市创建省份名称
            String provincName = provinces.get(i).getProvinceName();
            createName(wb, provincName, "省市信息表!$B$" + (i + 1) + ":$" + getcellColumnFlag(cities.size() + 1) + "$" + (i + 1));
            for (int j = 0; j < cities.size(); j++) {
                HSSFCell cityCell = provinceRow.createCell(j + 1);
                cityCell.setCellValue(cities.get(j).getCityName());
                HSSFRow cityRow = citySheet.createRow(rowNumber);
                HSSFCell cell = cityRow.createCell(0);
                cell.setCellValue(cities.get(j).getCityName());
                //根据市获取相应区
                List<District> districts = addressService.getDistricts(cities.get(j).getCityId());
                //为区创建市名称
                String cityName = cities.get(j).getCityName();
                cityName = cityName.equals(provincName) ? cityName + "市级" : cityName;
                createName(wb, cityName, "市区信息表!$B$" + (rowNumber + 1) + ":$" + getcellColumnFlag(districts.size() + 1) + "$" + (rowNumber + 1));
                rowNumber++;
                for (int k = 0; k < districts.size(); k++) {
                    HSSFCell districtCell = cityRow.createCell(k + 1);
                    districtCell.setCellValue(districts.get(k).getDistrictName());
                }
            }
        }*/

        HSSFRow row = templateSheet.createRow(0);
        // 生成一个样式
        HSSFCellStyle style = wb.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = wb.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);
        HSSFCell cell = row.createCell(0);
        cell.setCellValue("身份证号");
        cell.setCellStyle(style);
        HSSFCellStyle style2 = wb.createCellStyle();
        DataFormat format = wb.createDataFormat();
        style2.setDataFormat(format.getFormat("@"));
        templateSheet.setDefaultColumnStyle(0, style2);
        cell = row.createCell(1);
        cell.setCellValue("省");
        cell.setCellStyle(style);
        cell = row.createCell(2);
        cell.setCellValue("市");
        cell.setCellStyle(style);
        cell = row.createCell(3);
        cell.setCellValue("区");
        cell.setCellStyle(style);
        cell = row.createCell(4);
        cell.setCellValue("详细地址");
        cell.setCellStyle(style);
        templateSheet.setDefaultColumnStyle(4, style2);
        cell = row.createCell(5);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);

        cell = row.createCell(6);
        cell.setCellValue("联系电话");
        cell.setCellStyle(style);
        templateSheet.setDefaultColumnStyle(6, style2);

        cell = row.createCell(7);
        cell.setCellValue("促销邮豆金额");
        cell.setCellStyle(style);
        cell = row.createCell(8);
        cell.setCellValue("备注");
        cell.setCellStyle(style);
       /* //给省设置数据有效性
        createName(wb, "province", "省市信息表!$A$1:$A$34");
        HSSFDataValidation valiProvince = setDataValidation("province", 1, 1000, 1, 1);
        templateSheet.addValidationData(valiProvince);
        //市及区设置有效性
        for (int i = 2; i < 1000; i++) {
            HSSFDataValidation validCity = setDataValidation("INDIRECT(B" + i + ")", (i - 1), (i - 1), 2, 2);
            templateSheet.addValidationData(validCity);
            HSSFDataValidation validDistrict = setDataValidation("INDIRECT(C" + i + ")", (i - 1), (i - 1), 3, 3);
            templateSheet.addValidationData(validDistrict);
        }
*/
        //给excel动态增加参数列，同时将参数信息插入参数信息表
        BusinessType businessType = businessTypeService.getDetails(typeId);
       /* cell=row.createCell(9);
        cell.setCellValue("业务类型");*/

        List<Param> params = businessType.getParams();
        //调整列宽
        for (int i = 0; i < params.size() + 9; i++) {
            templateSheet.setColumnWidth(i, 14 * 256);
        }
        for (int i = 0; i < params.size(); i++) {
            cell = row.createCell(i + 9);
            String paramName = params.get(i).getParamName();
            cell.setCellValue(paramName);
            cell.setCellStyle(style);
            //参数信息表插入数据
            HSSFRow paramRow = paramSheet.createRow(i);
            HSSFCell paramCell = paramRow.createCell(0);
            paramCell.setCellValue(paramName);
            List<ParamValue> paramValues = params.get(i).getParamValues();
            if (params.get(i).getParamType() == ParamType.TYPE_ENUM) {
                createName(wb, "_" + paramName, "参数信息表!$B$" + (i + 1) + ":$" + getcellColumnFlag(paramValues.size() + 1) + "$" + (i + 1));
                //选项参数的有效性
                HSSFDataValidation validParam = setDataValidation("_" + paramName, 1, 1000, i + 9, i + 9);
                templateSheet.addValidationData(validParam);
            }
            for (int j = 0; j < paramValues.size(); j++) {
                paramCell = paramRow.createCell(j + 1);
                paramCell.setCellValue(paramValues.get(j).getParamValueName());
            }

        }
        Map<String, Object> map = new HashMap<>();
        map.put("wb", wb);
        map.put("fileName", businessType.getTypeName() + ".xls");
        ViewExcel viewExcel = new ViewExcel();
        return new ModelAndView(viewExcel, map);
    }

    /**
     * 名称管理器
     */
    public static HSSFName createName(HSSFWorkbook wb, String name, String expression) {
        HSSFName refer = wb.createName();
        refer.setRefersToFormula(expression);
        refer.setNameName(name);
        return refer;
    }

    /**
     * 数据有效性
     */
    public static HSSFDataValidation setDataValidation(String name, int firstRow, int endRow, int firstCol, int endCol) {
        //加载下拉列表内容
        DVConstraint constraint = DVConstraint.createFormulaListConstraint(name);
        // 设置数据有效性加载在哪个单元格上。
        // 四个参数分别是：起始行、终止行、起始列、终止列
        org.apache.poi.ss.util.CellRangeAddressList regions = new CellRangeAddressList((short) firstRow, (short) endRow, (short) firstCol, (short) endCol);
        // 数据有效性对象
        return new HSSFDataValidation(regions, constraint);
    }

    //根据数据量查询所在列
    private String getcellColumnFlag(int num) {
        String columFiled = "";
        int chuNum;
        int yuNum;
        if (num >= 1 && num <= 26) {
            columFiled = this.doHandle(num);
        } else {
            chuNum = num / 26;
            yuNum = num % 26;

            columFiled += this.doHandle(chuNum);
            columFiled += this.doHandle(yuNum);
        }
        return columFiled;
    }

    private String doHandle(final int num) {
        String[] charArr = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M"
                , "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        return charArr[num - 1];
    }

}