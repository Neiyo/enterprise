package com.ylife.form.mapper;

import com.ylife.data.page.Pageable;
import com.ylife.form.model.CustomerConsume;
import com.ylife.form.model.FormFunc;
import com.ylife.form.model.FormTime;
import com.ylife.form.model.UcoinGrandForm;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/5.
 */
public interface FormFuncMapper {

    FormTime selectFormByTime(@Param("maxCatalog")Long maxCatalog,
                                @Param("minCatalog")Long minCatalog,
                                @Param("start")Date start,
                                @Param("end")Date End);

    Long selectNewCustomerByTime(@Param("maxCatalog")Long maxCatalog,
                                     @Param("minCatalog")Long minCatalog,
                                     @Param("start")Date start,
                                     @Param("end")Date End);

    List<FormTime> selectReportByDay(@Param("maxCatalog")Long maxCatalog,
                                     @Param("minCatalog")Long minCatalog,
                                     @Param("start")Date start,
                                     @Param("end")Date End);

    List<FormTime> selectNewcustomerAmount(@Param("maxCatalog")Long maxCatalog,
                                           @Param("minCatalog")Long minCatalog,
                                           @Param("start")Date start,
                                           @Param("end")Date end);


    List<UcoinGrandForm> selectUcoinGrand(@Param("idCard") String idCard,
                                    @Param("maxCatalog") Long maxCatalog,
                                    @Param("minCatalog") Long minCatalog,
                                    @Param("start") Date start,
                                    @Param("end") Date end,
                                    @Param("pageable")Pageable pageable);

    int countUcoinGrandForm(@Param("idCard") String idCard,
                            @Param("maxCatalog") Long maxCatalog,
                            @Param("minCatalog") Long minCatalog,
                            @Param("start") Date start,
                            @Param("end") Date end);

    List<UcoinGrandForm> selectDetailUcoinGrand(@Param("idCard") String idCard,
                                          @Param("maxCatalog") Long maxCatalog,
                                          @Param("minCatalog") Long minCatalog,
                                          @Param("start") Date start,
                                          @Param("end") Date end,
                                          @Param("pageable")Pageable pageable);

    int countDetailUcoinGrand(@Param("idCard") String idCard,
                              @Param("maxCatalog") Long maxCatalog,
                              @Param("minCatalog") Long minCatalog,
                              @Param("start") Date start,
                              @Param("end") Date end);

    List<CustomerConsume> selectCustomerConsume(@Param("idCard") String idCard,
                                         @Param("maxCatalog") Long maxCatalog,
                                         @Param("minCatalog") Long minCatalog,
                                         @Param("start") Date start,
                                         @Param("end") Date end,
                                         @Param("pageable")Pageable pageable);

    int countCustomerConsume(@Param("idCard") String idCard,
                             @Param("maxCatalog") Long maxCatalog,
                             @Param("minCatalog") Long minCatalog,
                             @Param("start") Date start,
                             @Param("end") Date end);


    List<CustomerConsume> selectDetailConsume(@Param("idCard") String idCard,
                                       @Param("maxCatalog") Long maxCatalog,
                                       @Param("minCatalog") Long minCatalog,
                                       @Param("start") Date start,
                                       @Param("end") Date end,
                                       @Param("pageable")Pageable pageable);

    int countDetailConsume(@Param("idCard") String idCard,
                           @Param("maxCatalog") Long maxCatalog,
                           @Param("minCatalog") Long minCatalog,
                           @Param("start") Date start,
                           @Param("end") Date end);

    List<CustomerConsume> selectDetailRefund(@Param("idCard") String idCard,
                                      @Param("maxCatalog") Long maxCatalog,
                                      @Param("minCatalog") Long minCatalog,
                                      @Param("start") Date start,
                                      @Param("end") Date end,
                                      @Param("pageable")Pageable pageable);

    int countDetailRefund(@Param("idCard") String idCard,
                          @Param("maxCatalog") Long maxCatalog,
                          @Param("minCatalog") Long minCatalog,
                          @Param("start") Date start,
                          @Param("end") Date end);

    List<FormFunc> selectNetData(@Param("maxCatalog") Long maxCatalog,
                                 @Param("minCatalog") Long minCatalog,
                                 @Param("start") Date start,
                                 @Param("end") Date end,
                                 @Param("pageable")Pageable pageable);

    int countNetData(@Param("maxCatalog") Long maxCatalog,
                     @Param("minCatalog") Long minCatalog);



}
