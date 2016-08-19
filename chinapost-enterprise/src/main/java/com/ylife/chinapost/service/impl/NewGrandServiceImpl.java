package com.ylife.chinapost.service.impl;

import com.ylife.chinapost.controller.utils.Constants;
import com.ylife.chinapost.service.CurrentLoginService;
import com.ylife.chinapost.service.NewGrandService;
import com.ylife.chinapost.service.UcoinGrandService;
import com.ylife.customer.mapper.ChinapostCustomerMapper;
import com.ylife.customer.model.ChinapostCustomer;
import com.ylife.customer.service.ChinapostCustomerService;
import com.ylife.customer.service.CustomerEnterpriseService;
import com.ylife.enterprise.model.EnterpriseFunction;
import com.ylife.enterprise.model.EnterpriseInfo;
import com.ylife.enterprise.service.EnterpriseInfoService;
import com.ylife.enterprise.service.EnterpriseService;
import com.ylife.exception.AuthorizationException;
import com.ylife.exception.UserOperationException;
import com.ylife.system.model.BusinessType;
import com.ylife.ucoin.model.HistoryType;
import com.ylife.ucoin.service.CustomerUcoinHistoryService;
import com.ylife.ucoin.service.CustomerUcoinService;
import com.ylife.utils.CheckIdcard;
import com.ylife.utils.DateUtil;
import com.ylife.utils.ExcelUtil;
import com.ylife.utils.StringUtil;
import com.ylife.wealth.service.EnterpriseBatchGrandService;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2016/8/16.
 */
@Service
public class NewGrandServiceImpl implements NewGrandService {

    @Resource
    private CurrentLoginService currentLoginService;

    @Resource
    private EnterpriseInfoService enterpriseInfoService;

    @Resource
    private CustomerUcoinService customerUcoinService;

    @Resource
    private EnterpriseService enterpriseService;
    @Resource
    private EnterpriseBatchGrandService enterpriseBatchGrandService;
    @Resource
    private ChinapostCustomerService chinapostCustomerService;
    @Resource
    private CustomerUcoinHistoryService customerUcoinHistoryService;
    @Resource
    private  CustomerEnterpriseService customerEnterpriseService;
    @Resource
    private  UcoinGrandService ucoinGrandService;
    @Resource
    private ChinapostCustomerMapper chinapostCustomerMapper;


    private static final String HEAD_ACCOUNT_NAME="机构编号";
    private static final String HEAD_ID_CARD = "身份证号";
    private static final String HEAD_NAME = "姓名";
    private static final String HEAD_PHONE = "电话";
    private static final String HEAD_MARKET_PRICE = "标准积分";
    private static final String HEAD_SALE_PRICE = "促销积分";
    private static final String BUSINESS_TIME = "日期";

    private static final String ERROR_IDCARD = "身份证不合法";

    @Override
    public Map<String, Object> parseFile(MultipartFile uploadExcel, Boolean type) throws IOException, InvalidFormatException {
        List<List<String>> errorList=new ArrayList<>();
        List<List<String>> excelList;
        excelList= ExcelUtil.readExcel(uploadExcel.getInputStream(), 1);
        List<String> head=excelList.get(0);
        checkHead(head);

        for(int i=1;i<excelList.size();i++) {
            List<String> body = excelList.get(i);
            List<String> error=new ArrayList<>();
            String accountName=body.get(0);
            if(accountName.length()==0){
                error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(0 + 1) +"^_^"+ "列" +"【" + HEAD_ACCOUNT_NAME + "】"+ "不能为空");
            }else {
               if(enterpriseInfoService.getEnterpriseInfoByOrganization(accountName)==null) {
                   error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(0 + 1) +"^_^"+ "列" + "【" + HEAD_ACCOUNT_NAME + "】" + "该编号不存在");
               }
            }

            String idCard=body.get(1);
            if(!CheckIdcard.isValid(idCard)){
                error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(1 + 1) +"^_^"+ "列" +"【" + HEAD_ID_CARD + "】"+ ERROR_IDCARD);
            }

            String phone = body.get(3);
            if (phone.length() != 0) {
                if (!Constants.PHONE_VALIDATOR.isValid(phone)) {
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(3 + 1) + "^_^" + "列" + "【" + HEAD_PHONE + "】" + "电话号码错误");
                }
            }


            String marketPrice = body.get(4);
            if (marketPrice.length() == 0) {
                body.set(4, "0");
            } else {

                try {
                    BigDecimal mPrice = new BigDecimal(marketPrice);
                    if (mPrice.compareTo(BigDecimal.ZERO) == -1) {
                        error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(4 + 1) + "^_^" + "列" + "【" + HEAD_MARKET_PRICE + "】" + "营销邮豆金额输入错误");
                    }
                    if (!type) {
                        mPrice = mPrice.negate();
                        body.set(4, mPrice.toString());
                    }
                } catch (NumberFormatException e) {
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(4 + 1) + "^_^" + "列" + "【" + HEAD_MARKET_PRICE + "】" + "营销邮豆金额输入错误");
                }
            }

            String salePrice = body.get(5);
            if (salePrice.length() == 0) {
                body.set(5, "0");
            } else {

                try {
                    BigDecimal sPrice = new BigDecimal(salePrice);
                    if (sPrice.compareTo(BigDecimal.ZERO) == -1) {
                        error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(5 + 1) + "^_^" + "列" + "【" + HEAD_MARKET_PRICE + "】" + "营销邮豆金额输入错误");
                    }
                    if (!type) {
                        sPrice = sPrice.negate();
                        body.set(5, sPrice.toString());
                    }
                } catch (NumberFormatException e) {
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(5 + 1) + "^_^" + "列" + "【" + HEAD_MARKET_PRICE + "】" + "营销邮豆金额输入错误");
                }
            }

            String time=body.get(6);
            try {
                DateUtil.fromString(time,"yyyy-MM-dd");
            }catch (IllegalArgumentException e){
                error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(6 + 1) + "^_^" + "列" + "【" + BUSINESS_TIME + "】" + "日期格式不对");
            }

           /* if(!type){
                try {
                    BigDecimal mPrice=new BigDecimal(marketPrice);
                    if(mPrice.compareTo(BigDecimal.ZERO)==-1){
                        error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(4 + 1) +"^_^"+ "列" +"【" + HEAD_MARKET_PRICE + "】"+ "营销邮豆金额输入错误");
                    }
                    mPrice=mPrice.negate();
                    body.set(4,mPrice.toString());
                }catch (NumberFormatException e){
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(4 + 1) +"^_^"+ "列" +"【" + HEAD_MARKET_PRICE + "】"+ "营销邮豆金额输入错误");
                }

                try {
                    BigDecimal sPrice=new BigDecimal(salePrice);
                    if(sPrice.compareTo(BigDecimal.ZERO)==-1){
                        error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(4 + 1) +"^_^"+ "列" +"【" + HEAD_MARKET_PRICE + "】"+ "营销邮豆金额输入错误");
                    }
                    sPrice=sPrice.negate();
                    body.set(5,sPrice.toString());
                }catch (NumberFormatException e){
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(5 + 1)+"^_^" + "列" +"【" + HEAD_SALE_PRICE + "】"+ "促销邮豆金额输入错误");
                }
            }else {
                try {
                    BigDecimal mPrice=new BigDecimal(marketPrice);
                    if(mPrice.compareTo(BigDecimal.ZERO)==-1){
                        error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(4 + 1) +"^_^"+ "列" +"【" + HEAD_MARKET_PRICE + "】"+ "营销邮豆金额输入错误");
                    }
                    mPrice=mPrice.negate();
                    body.set(4,mPrice.toString());
                }catch (NumberFormatException e){
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(4 + 1) +"^_^"+ "列" +"【" + HEAD_MARKET_PRICE + "】"+ "营销邮豆金额输入错误");
                }

                try {
                    BigDecimal sPrice=new BigDecimal(salePrice);
                    if(sPrice.compareTo(BigDecimal.ZERO)==-1){
                        error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(4 + 1) +"^_^"+ "列" +"【" + HEAD_MARKET_PRICE + "】"+ "营销邮豆金额输入错误");
                    }
                    sPrice=sPrice.negate();
                    body.set(5,sPrice.toString());
                }catch (NumberFormatException e){
                    error.add(i + "^_^" + ExcelUtil.getcellColumnFlag(5 + 1)+"^_^" + "列" +"【" + HEAD_SALE_PRICE + "】"+ "促销邮豆金额输入错误");
                }
            }
*/
            errorList.add(error);
        }

        Map<String,Object> map=new HashMap<>();
        map.put("head", head);
        map.put("content",excelList);
        map.put("error",errorList);
        return map;
    }



    @Override
    @Transactional
    public void batchGrand(Map<String, Object> excelAndErrorList, String paykey,Boolean type) {
        Object object=excelAndErrorList.get("content");
        List<List<String>> lists = (List<List<String>>) object;

        EnterpriseFunction function = currentLoginService.getCurrentLoginEnterpriseFunc();
        long enterpriseId = function.getId();
        if (!enterpriseService.checkPaykey(enterpriseId, paykey)) {
            throw new AuthorizationException("支付密码错误。");
        }

        Long batchId=null;
        if(type){
             batchId = enterpriseBatchGrandService.addBatchGrand(enterpriseId, "批量发放", null);}
        else{
            batchId=enterpriseBatchGrandService.addBatchGrand(enterpriseId,"批量扣减",null);
        }
        int size = lists.size();
        BigDecimal totalFee=BigDecimal.ZERO;
        for (int i = 1; i < size; i++) {
            List<String> contents = lists.get(i);

            //price:发放的邮豆金额 marketPrice:公式计算出的邮豆金额（营销邮豆金额） salePrice:促销邮豆金额
            BigDecimal fee = doGrand(function, batchId,contents,type);
            totalFee=totalFee.add(fee);
        }
        enterpriseBatchGrandService.updateFee(batchId, totalFee, size - 1);
    }


    private BigDecimal doGrand(EnterpriseFunction function, long batchId,List<String> content,Boolean type) {
        BigDecimal totalFee = BigDecimal.ZERO;
        String accountName= content.get(0);
        String customerName = content.get(2);
        String idCard = content.get(1);
        String contactPhone=content.get(3);
        BigDecimal salePrice = StringUtil.isBlank(content.get(5)) ? BigDecimal.ZERO : new BigDecimal(content.get(5));
        //如果为新用户，保存用户
        Long enterpriseId = enterpriseInfoService.getEnterpriseInfoByOrganization(accountName).getEnterpriseId();
        Long creatorId = currentLoginService.getCurrentLoginEnterpriseManagerId();


        if (ucoinGrandService.isNewCustomer(idCard)) {
            chinapostCustomerService.addCustomer(idCard, customerName, contactPhone, null, null,
                    null, null, null, null, enterpriseId, creatorId, null, null, null);
        } else {
            ChinapostCustomer exitCustomer = chinapostCustomerService.getCustomer(idCard);
            chinapostCustomerService.editCustomer(exitCustomer.getId(), customerName, contactPhone, null,
                    null, null, null, null, null, null, null, null);
            customerEnterpriseService.addRelation(exitCustomer.getId(), enterpriseId);
        }
        Long id = chinapostCustomerMapper.selectId(idCard);


        BigDecimal marketPrice;
        BigDecimal price=BigDecimal.ZERO;

        marketPrice = new BigDecimal(content.get(4));

        if (marketPrice.compareTo(BigDecimal.ZERO)!=0){
        price = marketPrice.add(salePrice);
        totalFee = totalFee.add(price);
        //添加U宝
        String time=content.get(6);
        Date createDate=null;
        if(time.length()==0){
            createDate=new Date();
        }else {
            DateUtil.fromString(time, "yyyy-MM-dd");

        }
        customerUcoinService.ChangeUcoin(id,enterpriseId,price,createDate,null,true);
        //customerUcoinService.grandUcoin(id, function.getId(), price, null, null, sendType, remark);
       if(type){
           customerUcoinHistoryService.addHistory(id, enterpriseId, batchId, HistoryType.ENTERPRISE_GRAND, price,
                   marketPrice, salePrice, null, null, null, null);
       }else {
           customerUcoinHistoryService.addHistory(id, enterpriseId, batchId, HistoryType.UCOIN_DEDUCT, price,
                   marketPrice, salePrice, null, null, null,null);
       }

        //扣款
        enterpriseService.addUcoinAmount(enterpriseId, price.negate());}
        return price;
    }


    private void checkHead(List<String> head){
        if(head.size()!=7|| !Objects.equals(head.get(0),HEAD_ACCOUNT_NAME)||!Objects.equals(head.get(1),HEAD_ID_CARD)||
                !Objects.equals(head.get(2),HEAD_NAME)||!Objects.equals(head.get(3),HEAD_PHONE)||!Objects.equals(head.get(4),HEAD_MARKET_PRICE)||
                !Objects.equals(head.get(5),HEAD_SALE_PRICE)||!Objects.equals(head.get(6),BUSINESS_TIME)){
            throw  new UserOperationException("模板不匹配，请重新下载");
        }
    }



}
