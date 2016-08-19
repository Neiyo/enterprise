$(document).ready(function(){
    var backOrderNo = '';
    var phoneNo = '';
    var orderNo = '';
    var status = '';
    var start = '';
    var end = '';
    var enterpriseId = eid;
    var array = [];

    var checked_array = {
        length:0
    };
    var outport_array = {};

    array[0] = backOrderNo;
    array[1] = phoneNo;
    array[2] = orderNo;
    array[3] = status;
    array[4] = start;
    array[5] = end;
    array[6] = enterpriseId;
    jPages("../web/api/ordermanage/getBackOrders","itemContainer_All","holder_All",1,6,'','',array,function(pageId){
        if( checked_array[pageId] != undefined ) {
            $(".accountNumber").next().each(function () {
                var _this = this;
                checked_array[pageId].map(function(orderCode){
                    if($(_this).html() == orderCode){
                        $(_this).prev().prop("checked","checked");
                    }
                });
            });
        }
        return isTop;
    });

    //仓库列表
    inventoryList(eid,ename);

    $(document).on("click",".accountNumber",function(){
        var pageIndex=$(".jp-current").html();
        var orderCode=$(this).next().html();
        if( checked_array[pageIndex] == undefined ){
            checked_array[pageIndex] = new Array();
            checked_array.length++;
            outport_array[checked_array.length-1] = new Array();
        }
        if($(this).is(":checked")){
            checked_array[pageIndex].push(orderCode);
            outport_array[checked_array.length-1].push(orderCode);
        }
        else{
            checked_array[pageIndex].map(function(code){
                if( code == orderCode ){
                    checked_array[pageIndex].remove(code);
                    outport_array[checked_array.length-1].remove(orderCode);
                }
            })
        }
    });

    //时间控件
    $("#date_start").click(function(e){
        $("#datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y'
        });
        $("#datetimepicker_start").trigger("focus");
    })
    $("#datetimepicker_start").blur(function(){
        $("#datetimepicker_start").datetimepicker('destroy');
    });

    $("#date_end").click(function(e){
        $("#datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y'
        });
        $("#datetimepicker_end").trigger("focus");
    })
    $("#datetimepicker_end").blur(function(){
        $("#datetimepicker_end").datetimepicker('destroy');
    });

    //搜索
    $("#accSearch").click(function(){
        checked_array = {};
        outport_array = {};
        backOrderNo = $("input[name='accountNum']").val();
        phoneNo = $("input[name='accountCust']").val();
        orderNo = $("input[name='accountTel']").val();
        status = $(".select dt").attr("value");
        start = $("#datetimepicker_start").val();
        end = $("#datetimepicker_end").val();
        enterpriseId = $(".enterpriseIdChoosen").val();
        array[0] = backOrderNo;
        array[1] = phoneNo;
        array[2] = orderNo;
        array[3] = status;
        array[4] = start;
        array[5] = end;
        array[6] = enterpriseId;
       if(end>=start){
           jPages("../web/api/ordermanage/getBackOrders","itemContainer_All","holder_All",1,6,'','',array,function(pageId){
               if( checked_array[pageId] != undefined ) {
                   $(".accountNumber").next().each(function () {
                       var _this = this;
                       checked_array[pageId].map(function(orderCode){
                           if($(_this).html() == orderCode){
                               $(_this).prev().prop("checked","checked");
                           }
                       });
                   });
               }
               return isTop;
           });
       }else{
           data_type_alert("结束时间不能小于开始时间","error")
       }
    });

    var creditOrderNo = '';
    $(document).on("click",".detailCheck",function(){
        var html = "";
        creditOrderNo = $(this).parent().parent().find("span").html();
        coverHtml();
        //获取退单号
        $("#returnListItemActionConfirm").data("creditOrderNo",$(this).parent().parent().find("span").html());

        var data = JSON.parse($(this).parent().parent().parent().find(".data").val());

        var backWay = handleBackWay(data.backWay);
        var backCheck = handlebackCheck(data.backCheck);
        //var backCheck = '待退款';
        //对退款单填充数据
        //line1

        var backReason = '<abbr style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;" title="' + handleBackReason(data.backReason) + '">' + handleBackReason(data.backReason) + '</abbr>';
        $($(".returnListMoneyTable dl dd").children()[0]).empty().append(backReason);//退款原因
        $(".returnListMoneyTable dl dd").children()[1].innerHTML = handleCredential(data.applyCredentials);//申请凭据
        $(".returnListMoneyTable dl dd").children()[2].innerHTML = data.backPrice +"邮豆";//退款金额
        $(".returnListMoneyTable dl dd").children()[3].innerHTML = data.backPrice +"邮豆";//退款明细

        var problemShow = '<abbr style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;" title="' + handleUndefined(data.backRemark) + '">' + handleUndefined(data.backRemark) + '</abbr>';
        console.log(problemShow,backReason)
        $($(".returnListMoneyTable dl dd").children()[4]).empty().append(problemShow);//问题说明
        try{
        if( data.uploadDocuments != "" ){
            var str= data.uploadDocuments;
            str = str.split(",");
            for( i=0;i<str.length-1 ;i++ ){
                html += '<img width="25px" height="25px" src="' + str[i] + '"/>';
            }
            $(".returnListMoneyTable dl dd").children()[5].innerHTML = html;
        }
        else{
            $(".returnListMoneyTable dl dd").children()[5].innerHTML = "无"
        }
        }
        catch(e){

        }
        $(".returnListMoneyTable dl dd").children()[6].innerHTML = backCheck;//退款状态
        //line2
        $(".returnListMoneyRate ul").empty();
        data.logs.map(function(object){
            var handledTime = '';
            handledTime += handleDate_prev(new Date(object.createTime));
            handledTime += '  ';
            handledTime += handleDate_next(new Date(object.createTime));
            var html = '<li>';
            html += '<abbr>' + handledTime + '</abbr>';
            html += '<abbr><img src="../static/img/com_order_icon.png"></abbr>';
            html += '<abbr class="returnListItemRate_content">' + handleUndefined(object.logStr) + '</abbr>';
            html += '</li>';
            $(".returnListMoneyRate ul").append(html);
        });
        //line3

        $("#returnListItemActionCancelUntop2").click(function(){
            discoverHtml();
            $(".returnListItem").fadeOut();
        })
        

        //对退货单填充数据
        //line_1
        var html = "";
        var backRemark = ( data.backRemark != undefined ) ? data.backRemark : '';
        var applyCredentials = ( data.applyCredentials != undefined ) ? handleCredential(data.applyCredentials) : '';
        var backPrice = ( data.backPrice != undefined ) ? data.backPrice : '';
        var uploadDocuments = ( data.uploadDocuments != undefined ) ? data.uploadDocuments : '';
        var backWay = ( handleUndefined(data.order.selfPick) == 0 ) ? '快递' : '返回自提点';
        var backReason = '<abbr style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;" title="' + handleBackReason(data.backReason) + '">' + handleBackReason(data.backReason) + '</abbr>';
        $($(".returnListItemTable dl dd").children()[0]).empty().append(backReason);//退款原因
        $(".returnListItemTable dl dd").children()[1].innerHTML = applyCredentials;//申请凭据
        if(backCheck == "退单结束"||backCheck == "审核退款"||backCheck == "退款成功"){
        $(".returnListItemTable dl dd").children()[2].innerHTML = backPrice + "邮豆";//退款金额
        $(".returnListItemTable dl dd").children()[3].innerHTML = 0+"元";//退款现金金额
        }else{
            $(".returnListItemTable dl dd").children()[2].innerHTML = "";//退款金额
            $(".returnListItemTable dl dd").children()[3].innerHTML = "";//退款现金金额
        }
        var problemShow = '<abbr style="overflow:hidden;text-overflow:ellipsis;white-space:nowrap;" title="' + backRemark + '">' + backRemark + '</abbr>';
        $($(".returnListItemTable dl dd").children()[4]).empty().append(problemShow);//问题说明
        try{
        if(data.uploadDocuments!=""){
        var str= data.uploadDocuments;
        str = str.split(",")
        for ( i=0;i<str.length-1 ;i++ )
        {
            html += '<img width="25px" height="25px" src="' + str[i] + '"/>';
        }
        $(".returnListItemTable dl dd").children()[5].innerHTML = html;
        }
        else{
            $(".returnListItemTable dl dd").children()[5].innerHTML = "无"
        }
        }
        catch(e){

        }

        $(".returnListItemTable dl dd").children()[6].innerHTML = backWay;//商品返回方式
        $(".returnListItemTable dl dd").children()[7].innerHTML = backCheck;//退款状态

        //line_2
        $(".returnListItemTableTwo dl div").empty();
        data.backOrderGoods.map(function(object){
            var content_html = '<dd>';
                content_html += '<abbr style="overflow: hidden"><img class="backGoodsImg" src="' + handleUndefined(object.goodsInfoImage) + '"/>' + handleUndefined(object.goodsInfoName) + '</abbr>';//退货商品
                content_html += '<abbr style="overflow: hidden">' + handleUndefined(object.goodsInfoItemNo) + '</abbr>';//商品货号
                content_html += '<abbr style="overflow: hidden">' + handleUndefined(object.goodsPrice) + '邮豆</abbr>';//商城价
                content_html += '<abbr style="overflow: hidden">' + handleUndefined(object.goodsNum) + '</abbr>';//数量
                content_html += '</dd>';
            $(".returnListItemTableTwo dl div").append(content_html);
        });
        //line3
        $( $(".returnListItemOrderBefore ul").children()[1]).find("span").html(data.order.orderCode);
        //订单交易金额 = 邮豆金额 + 人民币金额*系数
        $( $(".returnListItemOrderBefore ul").children()[2]).find("span").html(data.order.orderPrice);
        //支付明细 = 邮豆 + 人民币
        $( $(".returnListItemOrderBefore ul").children()[3]).find("span").html(data.order.orderPrice + '邮豆+0元');
        $( $(".returnListItemOrderBefore ul").children()[4]).find("span").html(data.order.expressPrice);

        //line4



        $(".returnListItemRate ul").empty();
        data.logs.map(function(object){
            var handledTime = '';
            handledTime += handleDate_prev(new Date(object.createTime));
            handledTime += '  ';
            handledTime += handleDate_next(new Date(object.createTime));
            var html = '<li>';
                html += '<abbr>' + handledTime + '</abbr>';
                html += '<abbr><img src="../static/img/com_order_icon.png"></abbr>';
                html += '<abbr class="returnListItemRate_content">' + handleUndefined(object.logStr) + '</abbr>';
                html += '</li>';
            $(".returnListItemRate ul").append(html);
        });


        //退货物流信息
        try{
            $(".returnListItemlogistics li:nth-child(1) span").html(handleUndefined(data.logisticses[0].npLogisticsName));
            $(".returnListItemlogistics li:nth-child(2) span").html(handleUndefined(data.logisticses[0].npLogisticsNo));
        }catch(e){

        }

        //商城留言
        $(".returnListItemLeaveMessage span").html(data.backRemark);


        if ( $(this).html() == '查看详情' ){
            if( backCheck == '退款成功' || backCheck == '拒绝退款' || backCheck == '退款待退款'){
                $(".returnListItem h1 span").html("退款");
                $(".returnListMoney").fadeIn(500);
            }
            else if( backCheck == '审核退款' ){
                $(".returnListItem h1 span").html("查看详情");
                $(".returnListMoney").fadeIn(500);
                $(".returnListMoneyAction").css("display","none");
            }
            else{
                if( isTop == false && isEnd == false ){
                    $(".returnListItem h1 span").html("查看详情");
                    $(".returnListMoney").fadeIn(500);
                }
                else{
                    $(".returnListItem h1 span").html("查看详情");
                    $(".returnListItem").fadeIn(500);
                }
            }
        }
        else if( $(this).html() == '退款' ){
            $(".returnListItem h1 span").html("退款");
            $(".returnListMoney").fadeIn(500);
        }
        else if( $(this).html() == '退单审核' ){
            if( backCheck == '退货申请' ){
                $(".returnListItem h1 span").html("退单审核");
                $(".returnListItem").fadeIn(500);
            }
        }
        //backCheck,type
        var type = $(this).parent().parent().parent().find(".order_type").val();
        //退款单
        if( backCheck == '退款待退款' ){
            if( isTop ){
                array = getArray(1, 1, 1, 1, 1, 1, 1);
                showModule(array);
            }
            else{
                $(".returnListMoneyAction").css("display","none");
                array = getArray(1, 1, 1, 1, 1, 1, 0);
                showModule(array);
            }
        }
        else if( backCheck == '审核退款' ){
            if( isTop ){
                $(".returnListMoney").fadeIn(500);
            }
            else{
                $(".returnListMoneyAction").css("display","block");
            }
        }
        else if( backCheck == '退货待退款' ){
            if( isTop ){
                if (type == 'true,false') {
                    $(".returnListItem").css("display","block");
                    array = getArray(1, 1, 1, 1, 0, 1, 1);
                    showModule(array);
                    $(".returnListMoney").css("display","none");
                }else{
                    $(".returnListItem").css("display","block");
                    array = getArray(1, 1, 1, 1, 1, 1, 1);
                    showModule(array);
                    $(".returnListMoney").css("display","none");
                }

            }
            else{
                array = getArray(1, 1, 1, 1, 0, 0, 0);
                showModule(array);
            }
        }
        else if( backCheck == '退款成功' || backCheck == '拒绝退款'){
            $(".returnListMoneyAction").css("display","none");
            array = getArray(1, 1, 1, 1, 1, 1, 0);
            showModule(array);
        }

        //退货单
        else {
            var array = [];
            array = getArray(1, 1, 1, 1, 0, 0, 0);
            showModule(array);
            if (isTop) {
                if( backCheck == '退货申请' ){
                    $(".returnListItemTable dl dd").children()[2].innerHTML = '';//退款金额
                    $(".returnListItemTable dl dd").children()[3].innerHTML = '';//退款现金金额
                }
                if (type == 'true,false') {

                    //自主下单自提
                    //$(".returnListItemTable").show();
                    //$(".returnListItemTableTwo").show();
                    //$(".returnListItemOrderBefore").show();
                    //$(".returnListItemRate").show();
                    if (backCheck == '退货申请' || backCheck == '待收货') {
                        array = getArray(1, 1, 1, 1, 0, 0, 0);
                        showModule(array);
                    }
                    if (backCheck == '待退款') {
                        //$(".returnListItemLeaveMessage").show();
                        //$( $(".returnListItemAction")[0] ).show();
                        array = getArray(1, 1, 1, 1, 0, 1, 1);
                        showModule(array);
                    }
                    if (backCheck == '退单结束' || backCheck == '收货失败') {
                        //$(".returnListItemLeaveMessage").show();
                        array = getArray(1, 1, 1, 1, 0, 1, 0);
                        showModule(array);
                    }

                }
                if (type == 'true,true') {
                    //代客自提
                    //$(".returnListItemTable").show();
                    //$(".returnListItemTableTwo").show();
                    //$(".returnListItemOrderBefore").show();
                    //$(".returnListItemRate").show();
                    if (backCheck == '待退款') {
                        //$(".returnListItemLeaveMessage").show();
                        //$( $(".returnListItemAction")[0] ).show();
                        array = getArray(1, 1, 1, 1, 0, 1, 1);
                        showModule(array);
                    }
                    if (backCheck == '退单结束' || backCheck == '收货失败') {
                        //$(".returnListItemLeaveMessage").show();
                        array = getArray(1, 1, 1, 1, 0, 1, 0);
                        showModule(array);
                    }
                }
                if (type == 'false,true') {
                    //代客物流配送------------------并不存在

                }
                if (type == 'false,false') {
                    //自主下单物流配送
                    //$(".returnListItemTable").show();
                    //$(".returnListItemTableTwo").show();
                    //$(".returnListItemOrderBefore").show();
                    //$(".returnListItemRate").show();
                    if (backCheck == '退货申请' || backCheck == '待客户填写物流地址') {
                        array = getArray(1, 1, 1, 1, 0, 0, 0);
                        showModule(array);
                    }
                    if (backCheck == '待收货') {
                        array = getArray(1, 1, 1, 1, 1, 0, 0);
                        showModule(array);
                        //$(".returnListItemlogistics").show();
                    }
                    if (backCheck == '待退款' || backCheck == '收货失败') {
                        array = getArray(1, 1, 1, 1, 1, 1, 1);
                        showModule(array);
                        //$(".returnListItemlogistics").show();
                        //$(".returnListItemLeaveMessage").show();
                        //$( $(".returnListItemAction")[0] ).show();
                    }
                    if ( backCheck == '收货失败') {
                        array = getArray(1, 1, 1, 1, 1, 1, 0);
                        showModule(array);
                        //$(".returnListItemlogistics").show();
                        //$(".returnListItemLeaveMessage").show();
                        //$( $(".returnListItemAction")[0] ).show();
                    }
                    if (backCheck == '已退款') {
                        array = getArray(1, 1, 1, 1, 1, 1, 0);
                        showModule(array);
                        //$(".returnListItemlogistics").show();
                        //$(".returnListItemLeaveMessage").show();
                    }
                    if (backCheck == '退单结束') {
                        array = getArray(1, 1, 1, 1, 1, 1, 0);
                        showModule(array);
                        //$(".returnListItemlogistics").show();
                        //$(".returnListItemLeaveMessage").show();
                    }
                    if ( backCheck == '退货待退款' ){

                        array = getArray(1, 1, 1, 1, 1, 1, 1);
                        showModule(array);
                    }
                }
            }
            else {
                if( backCheck == '退货申请' ){
                    array = getArray(1,1,1,1,0,0,1);
                    showModule(array);
                }
                else{
                    if (type == 'true,false') {
                        //自主下单自提
                        if ( backCheck == '待收货' ){
                            array = getArray(1,1,1,1,0,0,0);
                            showModule(array);
                            $("#returnListItemAction2").show();
                        }
                        if ( backCheck == '退货待退款' || backCheck == '退单结束'){
                            array = getArray( 1,1,1,1,0,1,0);
                            showModule(array);
                        }
                        if( backCheck == '同意退货' || backCheck == '待客户填写物流地址' || backCheck == '收货失败'){
                            array = getArray( 1,1,1,1,1,1,0);
                            showModule(array);
                        }
                        if( backCheck == '退货申请' ){
                            array = getArray( 1,1,1,1,0,1,1);
                            showModule(array);
                        }
                        if( backCheck == '审核退款' ){

                        }
                    }
                    if (type == 'true,true') {
                        //代客自提
                        if( backCheck == '退货待退款' || backCheck == '退单结束' || backCheck == '收货失败'){
                            array = getArray(1,1,1,1,0,1,0);
                            showModule(array);
                        }
                        if( backCheck == '待收货' ){
                            array = getArray(1,1,1,1,0,1,0);
                            showModule(array);
                            $("#returnListItemAction2").show();
                        }
                    }
                    if (type == 'false,true') {
                        //代客物流配送------------------并不存在

                    }
                    if (type == 'false,false') {
                        //自主下单物流配送
                        if( backCheck == '待客户填写物流地址' || backCheck == '收货失败'){
                            array = getArray(1,1,1,1,1,1,0);
                            showModule(array);
                        }
                        if (backCheck == '退单结束') {
                            array = getArray(1, 1, 1, 1, 1, 1, 0);
                            showModule(array);
                            //$(".returnListItemlogistics").show();
                            //$(".returnListItemLeaveMessage").show();
                        }

                    }
                }

            }


        }

        //非顶级非网点无任何操作
        if( isTop == false && isEnd == false  ){
            $(".returnListMoneyAction").hide();
            $(".returnListItemAction").hide();
        }
    });




    $(".accountTable ul li").click(function(){
        var type = $(this).html();
        if ( type == '全部订单' ){
            window.location.href = 'accountManager?type=0';
        }
        else if ( type == '待付款' ){
            window.location.href = 'accountManager?type=1';
        }
        else if ( type == '待发货' ){
            window.location.href = 'accountManager?type=2';
        }
        else if ( type == '待收货' ){
            window.location.href = 'accountManager?type=3';
        }
        else if ( type == '待提货' ){
            window.location.href = 'accountManager?type=4';
        }
        else if ( type == '已完成' ){
            window.location.href = 'accountManager?type=5';
        }
        else if ( type == '已取消' ){
            window.location.href = 'accountManager?type=6';
        }
    });

    $("#xx1").click(function(){
        $(".returnListMoney").fadeOut(500);
        discoverHtml();
    });
    $("#xx2").click(function(){
        $(".returnListItem").fadeOut(500);
        discoverHtml();
    });
    $("#returnListItemActionCancel").click(function(){
        $(".returnListItem").fadeOut(500);
        discoverHtml();
    });

    $("#returnListMoneyActionCancel").click(function(){
        $(".returnListMoney").fadeOut(500);
        discoverHtml();
    });

    //退款保存
    $("#returnListItemActionConfirm").click(function(){
        var creditOrderNo = $("#returnListItemActionConfirm").data("creditOrderNo");
        var price = $(".backCoin").val();
        var msg = $(".backRemark").val();
        var args = price.split(".");
        var temp = ( args[1] == undefined ) ? '' : args[1];
        if(temp.length < 3 && args[1] !=""){
        $.post("../web/api/ordermanage/refund",{
            creditOrderNo:creditOrderNo,
            price:price,
            msg:msg
        },function(data){
            if(data.response == 'success'){
                ensure_alert("accountReturn");
            }
            else{
                response_ensure_alert("error",data.data.text,function(){
                   console.log(data.data.text + consoleNowTime);
                });
            }
        },'json');
        }else{
            data_type_alert("请输入正确的数字","error")
        }
    });


        //顶级账号保存
    $("#returnListMoneyActionConfirm").click(function(){
        var agree = $(".returnListMoneyAction input[type='radio']:checked").val();
        var message = $(".returnListMoneyAction textarea").val();
        var price = $( $(".returnListMoneyTable dl dd").children()[2] ).html();
        $.post("../web/api/ordermanage/refund",{
            creditOrderNo:creditOrderNo,
            price:price.substring(0,price.length-2),
            msg:message
        },function(data){
            if( data.response == 'success' ){
                ensure_alert('accountReturn');
            }
            else{
                response_ensure_alert("error","操作失败",function(){

                });
            }
        },'json');
    });

    //非顶级账号保存
    //审核钱
    $("#returnListMoneyActionConfirmUntop").click(function(){
        var agree = $(".returnListMoneyAction input[type='radio']:checked").val();
        var message = $(".returnListMoneyAction textarea").val();
        $.post("../web/api/ordermanage/reviewCreditOrder",{
            creditOrderNo:creditOrderNo,
            agree:agree,
            message:message
        },function(data){
            if( data.response == 'success' ){
                ensure_alert('accountReturn');
            }
            else{

            }
        },'json');
    });

    //审核货
    $("#returnListItemActionConfirmUntopAllow").click(function(){
        var agree = $("#returnListItemAction1 input[type='radio']:checked").val();
        var message = $("#returnListItemAction1 textarea").val();
        $.post("../web/api/ordermanage/reviewCreditOrder",{
            creditOrderNo:creditOrderNo,
            agree:agree,
            message:message
        },function(data){
            if( data.response == 'success' ){
                ensure_alert('accountReturn');
            }
            else{

            }
        },'json');
    });
    $("#returnListItemActionConfirmUntopEnsure").click(function(){
        var receive = $("#returnListItemAction2 input[type='radio']:checked").val();
        var enterpriseMsg = $("#backRemarkForE").val();
        var customerMsg =$("#backRemarkForC").val() ;
        $.post("../web/api/ordermanage/receiveCreditOrder",{
            creditOrderNo:creditOrderNo,
            receive:receive,
            enterpriseMsg:enterpriseMsg,
            customerMsg:customerMsg
        },function(data){
            if( data.response == 'success' ){
                ensure_alert('accountReturn');
            }
            else{

            }
        },'json');
    });

    //全部订单
    $("#accExport").click(function(){
        window.location.href = '../web/api/exportExcel/backOrderDown?backOrderNo=all' + '&status=' + status + "&receiver=" + '' + "&contactPhone=" + phoneNo + "&createStart=" + start + "&createEnd=" + end + "&payStart=" + '' + "&payEnd=" + '' + "&enterpriseId=" + enterpriseId;
    });
    //导出选中
    $("#accChooseExport").click(function(){
        var backorderInfoNumber = '[';
        for(var i = 0;i < checked_array.length;i++){
            for(var j = 0;j < outport_array[i].length;j++){
                backorderInfoNumber += '"' + outport_array[i][j] + '",';
            }
        }
        backorderInfoNumber = backorderInfoNumber.substring(0,backorderInfoNumber.length - 1);
        backorderInfoNumber += ']';
        if( backorderInfoNumber.length > 2 ){
            window.location.href = '../web/api/exportExcel/backOrderDown?backOrderNo=' + backorderInfoNumber;
        }
    });
});

function jPages(url,contentStr,target,type,perpage,reason,batchId,array,callbackChecked){
    $("#" + contentStr).empty();
    var backOrderNo = '';
    var phoneNo = '';
    var orderNo = '';
    var status = '';
    var start = '';
    var end = '';
    var enterpriseId = '';
    if( array != undefined ){
        backOrderNo = array[0];
        phoneNo = array[1];
        orderNo = array[2];
        status = array[3];
        start = array[4];
        end = array[5];
        enterpriseId = array[6];
    }

    $.post(url,{
        backOrderNo:backOrderNo,
        phoneNo:phoneNo,
        orderNo:orderNo,
        status:status,
        start:start,
        end:end,
        enterpriseId:enterpriseId,
        page:1,
        size:perpage
    },function( data ){
        if( data.response == 'success' ){
            var total = data.data.totalElements;
            if( total > 500 ){
                total = 500
            }
            for(var i = 0;i < total;i++){
                var html = get_html(type);
                $("#"+contentStr).append(html);
            }
            jPagesGet(target,contentStr,perpage);

            if( total < perpage ){
                appendHtml(contentStr,type,data.data.content,total);
            }
            else{
                appendHtml(contentStr,type,data.data.content,perpage);
            }

            $("#" + target).find("a").css("display","inline-block");
            $("#" + target).find("span").css("display","inline-block");
            $("#" + target).find(".jp-hidden").css("display","none");


            //处理多余页
            var pageLength = $("#" + target + " a").length;
            for( var i = 2;i < (pageLength - 2);i++ ){
                var showElement = ( pageLength - 2 ) * perpage ;
                if( showElement >= ( data.data.totalElements + perpage ) ){
                    $( $("#" + target + " a")[pageLength - i]).remove();
                }
            }

            //处理前一页和后一页
            $("#" + target + " .jp-previous").after('<a id="' + target + '_previous">前一百页</a>');
            $("#" + target + " .jp-previous").remove();

            $("#" + target + " .jp-next").after('<a id="' + target + '_next">后一百页</a>');
            $("#" + target + " .jp-next").remove();

            $(document).off("click","#" + target + " a");
            $(document).on("click","#" + target + " a",function(){
                backOrderNo = array[0];
                phoneNo = array[1];
                orderNo = array[2];
                status = array[3];
                start = array[4];
                end = array[5];
                enterpriseId = array[6];
                var page = '';
                if( $(this).html() == '前一百页' || $(this).html() == '后一百页' ){
                    page = 'not';
                }

                else{
                    page = $(this).html();
                }
                if( page != 'not'){
                    $.post(url,{
                        backOrderNo:backOrderNo,
                        phoneNo:phoneNo,
                        orderNo:orderNo,
                        status:status,
                        start:start,
                        end:end,
                        enterpriseId:enterpriseId,
                        page:page,
                        size:perpage
                    },function(data){
                        if(data.response == 'success'){
                            var length = data.data.content.length;
                            $("#"+contentStr).empty();
                            if( length > 500 ){
                                length = 500;
                            }
                            for(var i = 0;i < length;i++){
                                var html = get_html(type);
                                $("#"+contentStr).append(html);
                            }
                            //jPagesGet(contentStr,target,perpage);
                            $("#" + target).find("a").css("display","inline-block");
                            $("#" + target).find("span").css("display","inline-block");
                            $("#" + target).find(".jp-hidden").css("display","none");
                            //jPagesGet(target,contentStr);
                            if( length < perpage ){
                                appendHtml(contentStr,type,data.data.content,length);
                            }
                            else{
                                appendHtml(contentStr,type,data.data.content,perpage);
                            }
                            if( typeof callbackChecked == 'function' ){
                                callbackChecked(page);
                            }
                        }
                    },'json');
                }
            });


            //当点击前一百页时，减少一百页
            $("#" + target + "_previous").click(function(){
                var first = parseInt( $("#" + target + " a")[1].innerHTML );
                var length = $("#" + target + " a").length - 2;
                if( first > 100 ){
                    for( var i = 0; i < length; i++){
                        $("#" + target + " a")[i + 1].innerHTML = parseInt( $("#" + target + " a")[i + 1].innerHTML ) - 100;
                    }
                }
            });

            //当点击后一百页时，增加一百页
            $("#" + target + "_next").click(function(){
                var first = parseInt( $("#" + target + " a")[1].innerHTML );
                var length = $("#" + target + " a").length - 2;
                var last = parseInt( $("#" + target + " a")[length].innerHTML );
                if( ( last + 100 ) <= data.data.totalPages ){
                    for( var i = 0; i < length; i++){
                        $("#" + target + " a")[i + 1].innerHTML =parseInt( $("#" + target + " a")[i + 1].innerHTML ) + 100;
                    }
                }
                else{
                    var html = '';
                    for( var i = 1;i <= ( data.data.totalPages - last );i++){
                        html += '<a href="#" style="display: inline-block">' + ( parseInt(last) + i )  + '</a>';
                    }
                    $("#" + target + "_next").before(html);
                }
            });
        }
        else{

        }
    });


}

function get_html(type){
    var html = '';
    if( type == 1 ){
        html += '<abbr></abbr>';
    }
    return html;
}
function appendHtml(str,type,data,perpage){
    var html = '';
    for(var i=0;i<perpage;i++){
        if( type == 1 ){
            //获取商品总数
            var count = 0;
            var countPrice = 0;
            data[i].backOrderGoods.map(function(object){
                count = count + object.goodsNum;
                countPrice = countPrice + object.goodsPrice;
            });

            var prev_time = handleDate_prev(new Date(data[i].backTime));
            var next_time = handleDate_next(new Date(data[i].backTime));
            //获取退单状态
            var color = '';
            var status_content = '';
            var orderStatus = handlebackCheck(data[i].backCheck);
            var selfPick = data[i].order.selfPick;
            if( orderStatus == '审核退款' || orderStatus == '退货申请' ){
                if( isTop ){
                    status_content = '查看详情';
                }
                else{
                    if( isEnd == false  ){
                        status_content = '查看详情';
                    }
                    else{
                        status_content = '退单审核';
                    }

                }
            }
            else if( orderStatus == '同意退款' ){
                if( isTop ){
                    status_content = '退款';
                }
                else{
                    status_content = '查看详情';
                }
            }
            else if ( orderStatus == '退款待退款' || orderStatus == '退货待退款' ){
                if( isTop ){
                    status_content = '退款';
                }
                else{
                    status_content = '查看详情'
                }
            }
            else{
                status_content = '查看详情';
            }
            if( orderStatus == '退货申请' || orderStatus == '审核退款' ){
                color = '54a6de';
            }
            else{
                color = 'ff0000';
            }
            try{
                var imgUrl = data[i].backOrderGoods[0].goodsInfoImage;
            }
            catch(e){
                var imgUrl = '';
            }
            html += '<tr style="display:inline-block;vertical-align: middle; width:1600px; height:160px; margin-bottom:10px;">';
            html += "<td><input class='data' type='hidden' value='" + JSON.stringify(data[i]) + "' /></td>";
            html += '<input type="hidden" class="order_type" value="' + data[i].order.selfPick + ',' + data[i].order.isValet + '" />';
            try{
                html += '<td style="display:inline-block; width:1550px; height:60px; text-align:left; background:#edf3f8;height:60px; line-height:60px; padding-left:50px;"><input name="accountNumberSelect" class="accountNumber" type="checkbox" />退单号：<span>' + data[i].backOrderCode + '</span>' + "<span style='margin-left: 80px;'>" + "订单号："+"<span style='margin-right: 50px'>" + data[i].order.orderCode + "</span></span>"+'所属网点：<span>' + handleUndefined(data[i].accountInfo.accountName) + '</span>';
            }catch(e){
                html += '<td style="display:inline-block; width:1550px; height:60px; text-align:left; background:#edf3f8;height:60px; line-height:60px; padding-left:50px;"><input name="accountNumberSelect" class="accountNumber" type="checkbox" />退单号：<span>' + data[i].backOrderCode + '</span>' + "<span style='margin-left: 80px;'>" + "订单号："+"<span style='margin-right: 50px'>" + data[i].order.orderCode + "</span></span>"+'所属网点：<span>暂无网点</span>';
            }

            html += '<div style="width: 300px;position:relative; right:-73.5%; top:-95%;"><a class="detailCheck">' + status_content + '</a></span></div></td>';
            html += '<td style="display:inline-block; width:400px; height:100px; line-height:100px; text-align:center; ">';

            var flag = 0;
            data[i].backOrderGoods.map(function(object){
                flag=flag + 1;
                if( flag < 3){
                    html += '<img src="' + handleUndefined(object.goodsInfoImage) + '" width="50" height="50" />';
                }
            });
            html += '</td>';



            html += '<td style="display:inline-block; overflow: hidden; text-overflow: ellipsis;white-space: nowrap; width:80px; height:100px;"><section style="margin-top:20px;margin-left: 0px!important;">' + countPrice + '邮豆<br />(&nbsp;<span>' + count + '</span>件&nbsp;)</section></td>';
            html += '<td style="display:inline-block; width:250px; height:100px;"><section style="margin-top:20px;">收件人：<span>' + handleUndefined(data[i].backCollectPerson) + '</span><br />';
            html += '联系电话：<span>' + handleUndefined(data[i].backCollectPhone) + '</span><br /></section></td>';
            html += '<td style="display:inline-block; width:250px; height:100px; padding:10px 0px;"><section style="margin-top:20px;">' + prev_time + '<br />' + next_time + '</section></td>';
            html += '<td style="display:inline-block; width:200px; height:100px;"><abbr class="orderStatus" style="width:auto;background: #' + color + '"data-orderStatus="' + handleUndefined(data[i].backCheck) + '">' + orderStatus + '</abbr></td>';
            html += '<td style="display:inline-block; width:200px; height:100px; line-height:80px;"><span style="margin-left: 20px;">' + handleUndefined(data[i].backPrice) + '邮豆</span></td>';
            try{
            html += '<td style="display:inline-block; width:180px; height:100px; line-height:80px;"><span class="checkstate" data-storeId="' + handleUndefined(data[i].business.businessId) + '">' + handleUndefined(data[i].business.businessName) + '</span></td></tr>';
            }
            catch(e){
                html += '<td style="display:inline-block; width:180px; height:100px; line-height:80px;"><span class="checkstate" /></span></td></tr>';
            }
        }
    }
    $("#"+ str).append(html);
}
function isNumber(obj) {
    return obj === +obj
}
function jPagesGet(content,target,perpage){
    $("#"+content).jPages({
        containerID : target,
        perPage:perpage
    });
    $("")
}

function getData( type ){
    if ( type == '0' ){
        $(".accountTable li:nth-child(1)").trigger("click");
    }
    else if ( type == '1' ){
        $(".accountTable li:nth-child(2)").trigger("click");
    }
    else if ( type == '2' ){
        $(".accountTable li:nth-child(3)").trigger("click");
    }
    else if ( type == '3' ){
        $(".accountTable li:nth-child(4)").trigger("click");
    }
    else if ( type == '4' ){
        $(".accountTable li:nth-child(5)").trigger("click");
    }
    else if ( type == '5' ){
        $(".accountTable li:nth-child(6)").trigger("click");
    }
    else if ( type == '6' ){
        $(".accountTable li:nth-child(7)").trigger("click");
    }
}

function handleFullTime(time,x,y){
    var result = '';
    result = time.substring(0,x);
    result += " " + time.substring(x + y + 1);
    return result;
}

function showModule(array){
    handleMoudle($(".returnListItemTable"),array[0]);
    handleMoudle($(".returnListItemTableTwo"),array[1]);
    handleMoudle($(".returnListItemOrderBefore"),array[2]);
    handleMoudle($(".returnListItemRate"),array[3]);
    handleMoudle($(".returnListItemlogistics"),array[4]);
    handleMoudle($(".returnListItemLeaveMessage"),array[5]);
    handleMoudle($( $(".returnListItemAction")[0] ),array[6]);
}
function handleMoudle(dom,str){
    if( str == 'show' ){
        dom.show();
    }
    else if( str == 'hide' ){
        dom.hide();
    }
}

function getArray(a,b,c,d,e,f,g){
    var array = [];
    array[0] = handleArray(a);
    array[1] = handleArray(b);
    array[2] = handleArray(c);
    array[3] = handleArray(d);
    array[4] = handleArray(e);
    array[5] = handleArray(f);
    array[6] = handleArray(g);
    return array;
}

function handleArray(i){
    if( i == 1 ){
        return 'show';
    }
    else if( i == 0 ){
        return 'hide';
    }
}