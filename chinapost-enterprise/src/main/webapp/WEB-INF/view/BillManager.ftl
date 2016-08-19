<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <script type="text/javascript">
        var total_elements_up = ${total_elements_up};
        var total_elements_allocation = ${total_elements_allocation};
        var total_elements_send = ${total_elements_send};
        var total_elements_reduce = ${total_elements_reduce};
        var enterpriseName = "${enterpriseName}";
    </script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery.datetimepicker.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <script src="${bath}/static/js/zrj_ajaxPages.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <style type="text/css">
        @media screen and ( max-width: 1360px) {
            body {
                zoom: 62.5%;
                font-size: 10px !important;
            }
            .billButton li span{
                width: 95px!important;
            }
            .billButton li:nth-child(2),.billButton li:nth-child(3){
                width: 335px!important;
            }
            .billButton li:first-child{
                width: 237px!important;
            }
        }
        .billButton{
            width:1250px;
            height:130px;
            margin-left:20px;
        }
        .billButton li{
            height:35px;
            float:left;
            margin-left:20px;
            margin-top:20px;
        }

        .billButton li button{
            width:60px;
            height:35px;
            border:1px solid #CCC;
            background:#fff;
            margin-right:10px;
            color: #333;
            cursor: pointer;
        }

        .billButton li span{
            display:inline-block;
            vertical-align:middle;
            width:75px;
            height:35px;
            background:#f1f1f1;
            line-height:35px;
            text-align:center;
            border:1px solid #ccc;
        }
        .billButton li input{
            width:205px;
            height:35px;
            border:none;
            border: 1px solid #ccc;
            background:#FFF;
            margin-left: -1px;
            font-size:16px;
        }
        .billButton li input:hover{
        ;
        }
        .billButton li:first-child{
            width:217px;
        }
        .billButton li:nth-child(2){
            width:315px;
        }
        .billButton li:nth-child(3){
            width:315px;
        }
        .billButton li:nth-child(4){
            width:450px;
            border:1px solid transparent;
        }
        #billsearch{
            width:75px;
            height:35px;
            margin-left:20px;
            color:#FFF;
            background:#24b35f;
        ;
            border:1px solid transparent;
        }
        #billsearch:hover{
            background:#00cc55;
        }
        #billsearch:active{
            background:#008738;
        }

        .billTable{
            width:1600px;
            height:800px;
            margin-left:20px;
            margin-top:20px;
        }
        .billTable ul{
            width:1600px;
            height:35px;
        }
        .billTable ul li{
            width:125px;
            height:33px;
            text-align:center;
            line-height:33px;
            margin-left:20px;
            float:left;
            cursor:pointer;
            color: #666666;
            border-bottom: 2px solid transparent;
        }
        .billTable ul li.reqOn{
          border-bottom: 2px solid #54a6de;
            color:#54a6de;
        }


        .myBillList{
            width:1600px;
            height:700px;
            margin-left:20px;
        }
        .myBillList dt{
            width:1600px;
            height:40px;
            border-bottom: 1px solid #e5e5e5;
            border-top: 1px solid #e5e5e5;
        }
        .myBillList dd{
            width:1600px;
            height:45px;
            background:#FFF;
            border-bottom: 1px solid #e5e5e5;
        }

        .myBillList dt abbr{
            display:inline-block;
            vertical-align:middle;
            width:190px;
            height:40px;
            text-align:center;
            color:#666666;
            line-height:40px;
        }
        .myBillList dd abbr{
            display:inline-block;
            vertical-align:middle;
            width:190px;
            height:45px;
            text-align:center;
            line-height:45px;
            color:#333;
            white-space: nowrap;
            overflow: hidden;
        }
        .detail{
            width:100px;
            height:35px;
            border-style:none;
            margin-top:5px;
            background-color:transparent;
            border: 1px solid #999;
        }

        .detailList{
            width:880px;
            height:auto;
            overflow:hidden;
            background:#FFF;
            z-index:2;
            position:absolute;
            left:15%;
            top:15%;
            display:none;
        }
        .header{
            height:70px;
            line-height:70px;
            padding-left:15px;
            font-size:22px;
            color:#333;
            border-bottom: 1px solid #e5e5e5;
        }
        #xx{
            display:inline-block;
            vertical-align:middle;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        .maindetail{
            height:auto;
            overflow:hidden;
        }
        .maindetail h2{
            width:835px;
            margin-left:15px;
            height:40px;
            line-height:40px;
            background:#edf3f8;
            padding-left:15px;
            font-size:16px;
            color:#000;
            margin-top: 15px;

        }
        .maindetail	ul{
            width:835px;
            height:230px;
            margin-left:20px;
            margin-top:20px;
        }
        .maindetail	ul li{
            float:left;
            width:350px;
            margin:5px 5px 5px 0px;
            height:40px;
        }
        .maindetail	ul li span {
            display: inline-block;
            vertical-align: middle;
            width: 100px;
            overflow: hidden;
        }
        .maindetail	ul li input{
            border-style:none;
            background:#FFF;
            margin-left:20px;
        }
        .firstTable{
            width:835px;
            height:auto;
            overflow:hidden;
            margin-left:20px;
            display:none;
        }
        .firstTable dl{
            width:835px;
        }
        .firstTable dl dt{
            width:835px;
            height:35px;
            text-align:center;
            color:#666666;
            background:#edf3f8;
            line-height:35px;
        }
        .firstTable dl dt abbr{
            display:inline-block;
            vertical-align:middle;
            width:400px;
            height:35px;
        }
        .firstTable dl dd{
            width:835px;
            height:50px;
            text-align:center;
            background:#fff;
            border-bottom:1px solid #ccc;
            line-height:50px;
        }
        .firstTable dl dd abbr{
            display:inline-block;
            vertical-align:middle;
            width:400px;
            height:50px;
        }


        .secondTable{
            width:835px;
            height:auto;
            overflow:hidden;
            margin-left:20px;
            display:none;
        }
        .secondTable dl{
            width:835px;
        }
        .secondTable dl dt{
            width:835px;
            height:35px;

            color:#666666;
            background:#edf3f8;
            line-height:35px;
        }
        .secondTable dl dt abbr{
            display:inline-block;
            vertical-align:middle;
            text-align:center;
            width:260px;
            height:35px;
        }
        .secondTable dl dd{
            width:835px;
            height:50px;

            background:#fff;
            border-bottom:1px solid #ccc;
            line-height:50px;
        }
        .secondTable dl dd abbr{
            display:inline-block;
            vertical-align:middle;
            text-align:center;
            width:260px;
            height:50px;
            overflow: hidden;
        }
        #holder{
            margin-top: 20px;
        }


        #holder_first,#holder_second{
            margin:20px 0px;
        }


        .date_button{
            display: inline-block;
            vertical-align: middle;
            width:24px!important;
            margin-left: 0!important;
            height:24px;
            background-color: #f2f2f2;
            position: relative;
            left: -45px;
            background-image: url("${bath}/static/img/date_img.png");
        }
        .xdsoft_datetimepicker{
            width:310px!important;
        }

    </style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        var  isTop = "${isTop}";
        var customerNum = 0;
        var tabNumber = ${tabNumber};
        $(document).ready(function(e) {
            $(".billButton li input").focus(function(){
                $(this).css("border","1px solid #008738");
            });
            $(".billButton li input").blur(function(){
                $(this).css("border","1px solid #ccc");
            })
            $(".billTable li").click(function(){
                $(this).addClass("reqOn").siblings("li").removeClass("reqOn");
            });
            $("#xx").click(function(){
                discoverHtml()
                $(".detailList").fadeOut(500)
            })
            $(document).on("click",".detail",function(){
                coverHtml();
                var val=$(this).parent().siblings(".myBillListReason").html();
                if(val=="邮豆充值"||val=="上级财富分配"||val=="充值列表"){
                    var account=$(this).parent().siblings(".myBillListName").html();
                    var creatTime=$(this).parent().siblings(".myBillListCreattime").html();
                    var reason=$(this).parent().siblings(".myBillListReason").html();
                    var payway=$(this).parent().siblings(".myBillListPayway").html();
                    var sumMoney=$(this).parent().siblings(".myBillListMoneySum").html();
                    var sumNumber=$(this).parent().siblings(".myBillListNumSum").html();
                    var accountNumber=$(this).parent().siblings(".myBillListAccNum").html();
                    var businessType=$(this).parent().siblings(".myBillLisBusinessType").html();
                    var payTime=$(this).parent().siblings(".myBillLisPayTime").html();
                    var note=$(this).parent().siblings(".myBillListNote").html();
                    var target=$(this).parent().siblings(".myBillListTarget").html();

                    $(".detailList").fadeIn(500);
                    $(".detailList li[value='businessType']").hide();
                    $(".detailList li[value='note']").hide();
                    $(".firstTable").hide();
                    $(".secondTable").hide();

                    $("input[name='account']").val(account);
                    $("input[name='creatTime']").val(creatTime);
                    $("input[name='reason']").val(reason);
                    $("input[name='payway']").val(payway);
                    $("input[name='sumMoney']").val(sumMoney);
                    $("input[name='sumNumber']").val(sumNumber);
                    $("input[name='accountNumber']").val(accountNumber);
                    $("input[name='businessType']").val(businessType);
                    $("input[name='payTime']").val(payTime);
                    $("input[name='note']").val(note);
                    $("input[name='target']").val(target);

                }else if(val=="财富分配"){
                    var account=$(this).parent().siblings(".myBillListName").html();
                    var creatTime=$(this).parent().siblings(".myBillListCreattime").html();
                    var reason=$(this).parent().siblings(".myBillListReason").html();
                    var payway=$(this).parent().siblings(".myBillListPayway").html();
                    var sumMoney=$(this).parent().siblings(".myBillListMoneySum").html();
                    var sumNumber=$(this).parent().siblings(".myBillListNumSum").html();
                    var accountNumber=$(this).parent().siblings(".myBillListAccNum").html();
                    var businessType=$(this).parent().siblings(".myBillLisBusinessType").html();
                    var payTime=$(this).parent().siblings(".myBillLisPayTime").html();
                    var note=$(this).parent().siblings(".myBillListNote").html();
                    var batchId = $(this).parent().siblings(".myBillListBatchId").html();

                    $(".detailList").fadeIn(500);

                    $(".firstTable").show();
                    $(".secondTable").hide();
                    $(".detailList li[value='businessType']").hide();
                    $(".detailList li[value='note']").hide();


                    $("input[name='account']").val(account);
                    $("input[name='creatTime']").val(creatTime);
                    $("input[name='reason']").val(reason);
                    $("input[name='payway']").val(payway);
                    $("input[name='sumMoney']").val(sumMoney);
                    $("input[name='sumNumber']").val(sumNumber);
                    $("input[name='accountNumber']").val(accountNumber);
                    $("input[name='businessType']").val(businessType);
                    $("input[name='payTime']").val(payTime);
                    $("input[name='note']").val(note);
                    $("input[name='target']").val(target);

                    //
                    ajaxPages("../web/api/billmanage/getAllocatByBatch","itemContainer_first","holder_first",1,5,'',batchId,'',function(){
                        if( customerNum > 1 ){
                            $("input[name='target']").val('多人');
                        }
                        else{
                            $("input[name='target']").val('一人');
                        }
                    });

                }else if(val=="邮豆发放"){
                    var account=$(this).parent().siblings(".myBillListName").html();
                    var creatTime=$(this).parent().siblings(".myBillListCreattime").html();
                    var reason=$(this).parent().siblings(".myBillListReason").html();
                    var payway=$(this).parent().siblings(".myBillListPayway").html();
                    var sumMoney=$(this).parent().siblings(".myBillListMoneySum").html();
                    var sumNumber=$(this).parent().siblings(".myBillListNumSum").html();
                    var accountNumber=$(this).parent().siblings(".myBillListAccNum").html();
                    var businessType=$(this).parent().siblings(".myBillLisBusinessType").html();
                    var payTime=$(this).parent().siblings(".myBillLisPayTime").html();
                    var note=$(this).parent().siblings(".myBillListNote").html();
                    var batchId = $(this).parent().siblings(".myBillListBatchId").html();
                    console.log(batchId)
                    $(".detailList").fadeIn(500);
                    $(".firstTable").hide();
                    $(".secondTable").show();
                    $(".detailList li[value='businessType']").show();
                    $(".detailList li[value='note']").show();


                    $("input[name='account']").val(account);
                    $("input[name='creatTime']").val(creatTime);
                    $("input[name='reason']").val(reason);
                    $("input[name='payway']").val(payway);
                    $("input[name='sumMoney']").val(sumMoney);
                    $("input[name='sumNumber']").val(sumNumber);
                    $("input[name='accountNumber']").val(accountNumber);
                    $("input[name='businessType']").val(businessType);
                    $("input[name='payTime']").val(payTime);
                    $("input[name='note']").val(note);

                    //
                    ajaxPages("../web/api/billmanage/getGrandByBatch","itemContainer_second","holder_second",'bill_second',5,'',batchId,'',function(){
                        if( customerNum > 1 ){
                            $("input[name='target']").val('多人');
                        }
                        else{
                            $("input[name='target']").val('一人');
                        }
                    });
                }else if(val=="邮豆扣减"){
                    var account=$(this).parent().siblings(".myBillListName").html();
                    var creatTime=$(this).parent().siblings(".myBillListCreattime").html();
                    var reason=$(this).parent().siblings(".myBillListReason").html();
                    var payway=$(this).parent().siblings(".myBillListPayway").html();
                    var sumMoney=$(this).parent().siblings(".myBillListMoneySum").html();
                    var sumNumber=$(this).parent().siblings(".myBillListNumSum").html();
                    var accountNumber=$(this).parent().siblings(".myBillListAccNum").html();
                    var businessType=$(this).parent().siblings(".myBillLisBusinessType").html();
                    var payTime=$(this).parent().siblings(".myBillLisPayTime").html();
                    var note=$(this).parent().siblings(".myBillListNote").html();
                    var target=$(this).parent().siblings(".myBillListTarget").html();
                    var targetName=$(this).parent().siblings(".myBillListTargetName").html();
                    $(".detailList").fadeIn(500);
                    $(".firstTable").hide();
                    $(".secondTable").hide();
                    $(".detailList li[value='businessType']").hide();
                    $(".detailList li[value='note']").show();


                    $("input[name='account']").val(account);
                    $("input[name='creatTime']").val(creatTime);
                    $("input[name='reason']").val(reason);
                    $("input[name='payway']").val(payway);
                    $("input[name='sumMoney']").val(sumMoney);
                    $("input[name='sumNumber']").val(sumNumber);
                    $("input[name='accountNumber']").val(accountNumber);
                    $("input[name='businessType']").val(businessType);
                    $("input[name='payTime']").val(payTime);
                    $("input[name='note']").val(note);
                    $("input[name='target']").val(target + "      " + targetName);
                }
            });
        });
    </script>
    <title>无标题文档</title>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; height:auto;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>账单管理</span><abbr></abbr>
    </div>

<div class="billButton">
    <ul>
        <li><input placeholder="交易单号" class="allinputButton" type="text" id="billNo"/></li>
        <li>
            <input placeholder="开始时间" class="allinputButton" type="text" style="width:257px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a>
        </li>
        <li>
            <input placeholder="结束时间" class="allinputButton" type="text" style="width:257px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a>
        </li>
        <li style="width: 800px">
            <button class="today allinputButton">今天</button>
            <button class="thisWeed allinputButton">本周</button>
            <button class="lastWeek allinputButton">上周</button>
            <button class="thisMonth allinputButton">本月</button>
            <button class="lastMonth allinputButton">上月</button>
            <button class="thisYear allinputButton">今年</button>
            <input type="button" class="allseachButton" id="billsearch" value="搜索" />
        </li>

    </ul>
</div>

<div class="billTable">
    <ul>
        <li id="firstLi" value="0" class="reqOn">上级财富分配</li>
        <li value="1">财富分配</li>
        <li value="2">邮豆发放</li>
        <li value="3">邮豆扣减</li>
    </ul>
    <dl class="myBillList">
        <dt><abbr style="overflow: hidden; width: 250px;">所属账号</abbr><abbr>创建时间</abbr><abbr>交易单号</abbr><abbr>账单状态</abbr><abbr>支付方式</abbr><abbr>总金额</abbr><abbr>总笔数</abbr><abbr style="width:0px; overflow:hidden;">理由</abbr><abbr>操作</abbr>
        </dt>

        <div id="itemContainer"></div>
        <div class="allcpageTurnButton" id="holder"></div>
    </dl>
</div>




<div class="detailList allpop">
    <h1 class="header">账单详情<i id="xx"></i></h1>
    <div class="maindetail">
        <h2>交易成功</h2>
        <ul>
            <li><span>所属账号:</span><input type="text" readonly="readonly" name="account" /></li>
            <li style="width: 450px;"><span>交易对象:</span><input style="width: 250px" type="text" readonly="readonly" name="target" /></li>
            <li><span>账单类型:</span><input type="text" readonly="readonly" name="reason" /></li>
            <li><span>支付方式:</span><input type="text" readonly="readonly" name="payway" /></li>
            <li><span>总金额:</span><input type="text" readonly="readonly" name="sumMoney" /></li>
            <li><span>总笔数:</span><input type="text" readonly="readonly" name="sumNumber" /></li>
            <li><span>支付时间:</span><input type="text" readonly="readonly" name="creatTime" /></li>
            <li><span>交易单号:</span><input type="text" readonly="readonly" name="accountNumber" /></li>
            <li value="businessType"><span>业务类型:</span><input type="text" readonly="readonly" name="businessType" /></li>
            <li value="note"><span>备注:</span><input type="text" readonly="readonly" name="note" /></li>
        </ul>
    </div>
    <div class="firstTable">
        <dl>
            <dt>
                <abbr>对象账号</abbr>
                <abbr>分配金额</abbr>
            </dt>
            <div id="itemContainer_first"></div>
            <div class="allcpageTurnButton" id="holder_first"></div>
        </dl>

    </div>
    <div class="secondTable">
        <dl>
            <dt>
                <abbr>发放对象</abbr>
                <abbr>姓名</abbr>
                <abbr>发放金额</abbr>
            </dt>
            <div id="itemContainer_second"></div>
            <div class="allcpageTurnButton" id="holder_second"></div>
        </dl>

    </div>

</div>
<script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>

<script src="${bath}/static/js/BillManager.js?version=${VERSION}"></script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>

<script type="text/javascript">
         $(document).ready(function(){
             $( ".detailList" ).draggable();

            if(${isTop}){
                $("#firstLi").html("充值列表")

            }
            $(".today").click(function(){
                var date = new Date();
                var seperator1 = "/";
                var seperator2 = ":";
                var month = date.getMonth() + 1;
                var strDate = date.getDate();
                if(strDate<10){
                    strDate="0"+strDate

                }
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }

                var todaybegin = date.getFullYear() + seperator1 + month + seperator1 + strDate
                        + " " + "00" + seperator2 + "00"
                        + seperator2 + "00"
                var todayover = date.getFullYear() + seperator1 + month + seperator1 + strDate
                        + " " + "23" + seperator2 + "59"
                        + seperator2 + "59"
                $("#datetimepicker_start").val(todaybegin);
                $("#datetimepicker_end").val(todayover);
            })
            $(".thisWeed").click(function() {
                var date = new Date();
                var year=date.getFullYear()
                var seperator1 = "/";
                var seperator2 = ":";
                var month = date.getMonth() + 1;
                var month2=month
                var strDate = date.getDate();

                var d=new Date()
                var day=new Array(7)
                day[0]="7"
                day[1]="1"
                day[2]="2"
                day[3]="3"
                day[4]="4"
                day[5]="5"
                day[6]="6"
                strDate=strDate-(day[d.getDay()]-1)
                console.log(day[d.getDay()]-1)
                strDate2=strDate+6
                if(strDate<0){
                    newDate=0-strDate
                    if(month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                        strDate=30-newDate
                        month=month-1
                    }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                        strDate = 29 - newDate
                        month=month-1
                    }else if(month=="2"){
                        if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                            strDate = 28 - newDate
                            month=month-1
                        }else{
                            strDate = 27 - newDate
                            month=month-1
                        }
                    }else if(month=="1"){
                        year=year-1
                        strDate=30-newDate
                        month=12
                    }
                }
                if(strDate2>31){
                    if(month=="1"||month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                        strDate2=strDate2-31
                        month2=month2+1
                    }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                        strDate2=strDate2-30
                        month2=month2+1
                    }else if(month=="2"){
                        if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                            strDate2=strDate2-29
                            month2=month2+1
                        }else{
                            strDate2=strDate2-28
                            month2=month2+1
                        }
                    }
                }
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (month2 >= 1 && month2 <= 9) {
                    month2 = "0" + month2;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }

                if(strDate2<10){
                    strDate2="0"+strDate2
                }
                var todaybegin = year + seperator1 + month + seperator1 + strDate
                        + " " + "00" + seperator2 + "00"
                        + seperator2 + "00"
                var todayover = date.getFullYear() + seperator1 + month2 + seperator1 + strDate2
                        + " " + "23" + seperator2 + "59"
                        + seperator2 + "59"
                $("#datetimepicker_start").val(todaybegin);
                $("#datetimepicker_end").val(todayover);
            })
            $(".lastWeek").click(function() {
                var date = new Date();
                var lastWeekStartSecond = date.getTime() - 604800000;
                var date = new Date(lastWeekStartSecond);
                var year=date.getFullYear();
                var seperator1 = "/";
                var seperator2 = ":";
                var month = date.getMonth() + 1;
                var month2=month
                var strDate = date.getDate();

                var d=new Date()
                var day=new Array(7)
                day[0]="7"
                day[1]="1"
                day[2]="2"
                day[3]="3"
                day[4]="4"
                day[5]="5"
                day[6]="6"
                strDate=strDate-(day[d.getDay()]-1)
                strDate2=strDate+6
                if(strDate<0){
                    newDate=0-strDate
                    if( month=="1" || month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                        strDate=30-newDate
                        month=month-1
                    }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                        strDate = 29 - newDate
                        month=month-1
                    }else if(month=="2"){
                        if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                            strDate = 28 - newDate
                            month=month-1
                        }else{
                            strDate = 27 - newDate
                            month=month-1
                        }
                    }
                }
                if(strDate2>31){
                    if(month=="1"||month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                        strDate2=strDate2-31
                        month2=month2+1
                    }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                        strDate2=strDate2-30
                        month2=month2+1
                    }else if(month=="2"){
                        if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                            strDate2=strDate2-29
                            month2=month2+1
                        }else{
                            strDate2=strDate2-28
                            month2=month2+1
                        }
                    }
                }
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }
                if (month2 >= 1 && month2 <= 9) {
                    month2 = "0" + month2;
                }
                if (strDate >= 0 && strDate <= 9) {
                    strDate = "0" + strDate;
                }
                if(strDate2<10){
                    strDate2="0"+strDate2
                }
                var todaybegin = date.getFullYear() + seperator1 + month + seperator1 + strDate
                        + " " + "00" + seperator2 + "00"
                        + seperator2 + "00"
                var todayover = date.getFullYear() + seperator1 + month2 + seperator1 + strDate2
                        + " " + "23" + seperator2 + "59"
                        + seperator2 + "59"
                $("#datetimepicker_start").val(todaybegin);
                $("#datetimepicker_end").val(todayover);
            })
            $(".thisMonth").click(function() {
                var date = new Date();
                var seperator1 = "/";
                var seperator2 = ":";
                var year=date.getFullYear();
                var month = date.getMonth() + 1;
                if(month=="1"||month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                    strDate=31
                }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                    strDate=30
                }else if(month=="2"){
                    if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                        strDate=29
                    }else{
                        strDate=28
                    }
                }
                if(strDate<10){
                    strDate="0"+strDate

                }
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }

                var todaybegin = date.getFullYear() + seperator1 + month + seperator1 + "01"
                        + " " + "00" + seperator2 + "00"
                        + seperator2 + "00"
                var todayover = date.getFullYear() + seperator1 + month + seperator1 + strDate
                        + " " + "23" + seperator2 + "59"
                        + seperator2 + "59"
                $("#datetimepicker_start").val(todaybegin);
                $("#datetimepicker_end").val(todayover);


            })
            $(".lastMonth").click(function() {
                var date = new Date();
                var seperator1 = "/";
                var seperator2 = ":";
                var year=date.getFullYear();
                var month = date.getMonth();
                if(month=="0"){
                    month=12;
                    year=year-1
                }
                if(month=="1"||month=="3"||month=="5"||month=="7"||month=="8"||month=="10"||month=="12"){
                    strDate=31
                }else if(month=="4"||month=="6"||month=="9"||month=="11") {
                    strDate=30
                }else if(month=="2"){
                    if(((year % 4)==0) && ((year % 100)!=0) || ((year % 400)==0)){
                        strDate=29
                    }else{
                        strDate=28
                    }
                }
                if(strDate<10){
                    strDate="0"+strDate

                }
                if (month >= 1 && month <= 9) {
                    month = "0" + month;
                }

                var todaybegin = year + seperator1 + month + seperator1 + "01"
                        + " " + "00" + seperator2 + "00"
                        + seperator2 + "00"
                var todayover = year + seperator1 + month + seperator1 + strDate
                        + " " + "23" + seperator2 + "59"
                        + seperator2 + "59"
                $("#datetimepicker_start").val(todaybegin);
                $("#datetimepicker_end").val(todayover);


            })
            $(".thisYear").click(function() {
                var date = new Date();
                var seperator1 = "/";
                var seperator2 = ":";
                var year=date.getFullYear();
                var todaybegin = year + seperator1 + "01" + seperator1 + "01"
                        + " " + "00" + seperator2 + "00"
                        + seperator2 + "00"
                var todayover = year + seperator1 + "12" + seperator1 + "31"
                        + " " + "23" + seperator2 + "59"
                        + seperator2 + "59"
                $("#datetimepicker_start").val(todaybegin);
                $("#datetimepicker_end").val(todayover);


            })
            })
</script>
</html>
