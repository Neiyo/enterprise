<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery.datetimepicker.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jPages.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <title>无标题文档</title>
    <style type="text/css">
        @media screen and ( max-width: 1360px){
            body{
                zoom:62.5%;
                font-size:10px!important;
            }
            .accountTable section{
                margin-top: 0px!important;
            }
            input[name='accountTel'], input[name='accountCust'], input[name='accountUserName']{
                width: 170px!important;
            }
            .returnListItem div.returnListItemTable dl dt{
                width: 892px!important;
            }
            .returnListItem div.returnListItemTable dl dl{
                width: 892px!important;
            }
            .returnListItem div.returnListItemTable dl dd{
                width: 892px!important;
            }
        }
        .sum{
            width:100%;
            height:50px;
            line-height:50px;
            background:#54a6de;
            margin-left:20px;
            margin-top:20px;
            padding-left:30px;
            color: #fff;
        }
        .accountSearch{
            width:1800px;
            height:150px;
            margin-left:20px;
            margin-top:20px;
            font-size: 12px;
        }
        .accountSearch li{
            width:300px;
            height:40px;
            line-height:40px;
            float:left;
            margin:10px 0px;
        }
        .accountSearch li input{
            width:150px;
            height:42px;
            border:1px solid #ccc;
            background:#FFF;
        }
        #accSearch{
            width:80px;
            height:42px;
            background:#54a6de;
            color:#FFF;
            border:1px solid transparent;
        }

        #accExport{
            width:120px;
            height:42px;
            background:#54a6de;
            color:#FFF;
            border:1px solid transparent;
            margin-left:30px;
        }

        #accChooseExport{
            width:120px;
            height:42px;
            background:#54a6de;
            color:#FFF;
            border:1px solid transparent;
            margin-left:30px;
        }


        .accountTable{
            width:1600px;
            height:auto;
            margin-left:20px;
            margin-top: 50px;
        }
        .accountTable ul{
            width:1000px;
            height:30px;
        }
        .accountTable ul li{
            float:left;
            width:80px;
            height:28px;
            color: #999;
            line-height:30px;
            border-bottom:2px solid transparent;
            text-align:center;
            margin-right:-1px;
            cursor:pointer;
        }
        .accountTable ul li.accOn{
            border-bottom:2px solid #54a6de;
            color: #54a6de;
        }
        .account_Table{
            width:1600px;
        }
        .account_Table abbr{
            display:inline-block;
            width:60px;
            height:20px;
            line-height:20px;
            text-align:center;
            background:#ff3300;
            color:#FFF;
            border-radius:5px;
            margin-top:30px;
            border:1px solid transparent;

        }
        .account_Table a,i{
            color:#54a6de;
            padding:0px 5px;
            cursor:pointer;

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

        .detailOne{
            width:800px;
            height:auto;
            position:absolute;
            left:15%;
            top:5%;
            border:1px solid #ccc;
            border-radius:5px;
            background:#FFF;
            z-index:2;
            display:none;
        }
        .detailImg li{
            float:left;
            width:200px;
            height:150px;
            text-align:center;
            line-height:60px;
            margin-right:-1px
        }
        .detailOne ul.checkDetailOne{
            width:760px;
            height:auto;
            overflow:hidden;
            border:1px solid #CCC;
            margin-left:20px;
        }
        .checkDetailOne dd.dt{
            width:740px;
            padding:10px;
            font-size:18px;
            font-weight:bold;
            border-bottom:1px solid #E4E4E4;
            margin-bottom:10px;
            color:#333;
        }
        .checkDetailOne dd{
            width:360px;
            float:left;
            padding:5px 0px 5px 10px;
        }
        .Purchase_table{
            margin-bottom:50px;
        }

        #xxOne{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointerl;
        }
        /*网点代客下单*/
        .detailTwo{
            width:800px;
            height:auto;
            position:absolute;
            left:15%;
            top:5%;
            border:1px solid #ccc;
            border-radius:5px;
            background:#FFF;
            z-index:2;
            display:none;
        }
        .detailImg li{
            float:left;
            width:200px;
            height:150px;
            text-align:center;
            line-height:60px;
            margin-right:-1px
        }
        .detailTwo ul.checkDetailTwo{
            width:760px;
            height:auto;
            overflow:hidden;
            border:1px solid #CCC;
            margin-left:20px;
        }
        .checkDetailTwo dd.dt{
            width:740px;
            padding:10px;
            font-size:18px;
            font-weight:bold;
            border-bottom:1px solid #E4E4E4;
            margin-bottom:10px;
            color:#333;
        }
        .checkDetailTwo dd{
            width:360px;
            float:left;
            padding:5px 0px 5px 10px;
        }
        .Purchase_table{
            margin-bottom:50px;
        }

        #xxTwo{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointerl;
        }


        /*用户自己下单*/
        .detailThree{
            width:800px;
            height:auto;
            position:absolute;
            left:15%;
            top:5%;
            border:1px solid #ccc;
            border-radius:5px;
            background:#FFF;
            z-index:2;
            display:none;
        }
        .detailImg li{
            float:left;
            width:200px;
            height:150px;
            text-align:center;
            line-height:60px;
            margin-right:-1px
        }
        .detailThree ul.checkDetailThree{
            width:760px;
            height:auto;
            overflow:hidden;
            border:1px solid #CCC;
            margin-left:20px;
        }
        .checkDetailThree dd.dt{
            width:740px;
            padding:10px;
            font-size:18px;
            font-weight:bold;
            border-bottom:1px solid #E4E4E4;
            margin-bottom:10px;
            color:#333;
        }
        .checkDetailThree dd{
            width:360px;
            float:left;
            padding:5px 0px 5px 10px;
        }
        .Purchase_table{
            margin-bottom:50px;
        }

        #xxThree{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointerl;
        }

        /*网点自提代客*/
        .detailFour{
            width:800px;
            height:auto;
            position:absolute;
            left:15%;
            top:5%;
            border:1px solid #ccc;
            border-radius:5px;
            background:#FFF;
            z-index:2;
            display:none;
        }
        .detailImg li{
            float:left;
            width:200px;
            height:150px;
            text-align:center;
            line-height:60px;
            margin-right:-1px
        }
        .detailFour ul.checkDetailFour{
            width:760px;
            height:auto;
            overflow:hidden;
            border:1px solid #CCC;
            margin-left:20px;
        }
        .checkDetailFour dd.dt{
            width:740px;
            padding:10px;
            font-size:18px;
            font-weight:bold;
            border-bottom:1px solid #E4E4E4;
            margin-bottom:10px;
            color:#333;
        }
        .checkDetailFour dd{
            width:360px;
            float:left;
            padding:5px 0px 5px 10px;
        }
        .Purchase_table{
            margin-bottom:50px;
        }

        #xxFour{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;

            cursor:pointerl;
        }

        /*网点自提用户自主下单*/


        .arrow{
            display: inline-block;
            position: absolute;
            right: 0px;
            top: -5px;
            width: 36px;
            height: 36px;
            background:url(${bath}/static/img/com_btn_arrow_black_down.png) center no-repeat;
        }
        .accountSearch li dl{
            display: inline-block;
            vertical-align: middle;
            width:120px;
            height:28px;
            background:#ffffff;
            text-align: center;
            margin-top: -8px;
            position: relative;
        }
        .accountSearch li dl dd,.accountSearch li dl dt{
            width:120px;
            height:28px;
            background:#ffffff;
            cursor: pointer;
            z-index: 3;
            line-height: 28px;
        }
        .accountSearch li dl dd{
            display: none;
        }
        .accountSearch li dl dd:hover{
            color: #fff;
            background: #54a6de;
        }
        .accountSearch li dl dd:active{
            color: #fff;
            background: #54a6de;
        }

        .returnListMoney{
            width: 680px;
            height: auto;
            overflow: hidden;
            font-size: 14px;
            position: fixed;
            left: 15%;
            top: 15%;
            background: #fff;
            display: none;
            z-index:2;
        }

        .returnListMoney h1 span{
            margin-top: 30px;
            font-size: 24px;
            margin-left: 20px;
        }
        .returnListMoney h1 i{
            display: inline-block;
            vertical-align: middle;
            width: 25px;
            height: 25px;

            background:url(${bath}/static/img/XX.png) center no-repeat;
        }
        .returnListMoney div.returnListMoneyTable{
            width: 680px;
        }
        .returnListMoney div.returnListMoneyTable dl{
            width: 623px;
            margin-left: 30px;
            margin-top: 20px;

        }
        .returnListMoney div.returnListMoneyTable dl dt{
            width: 623px;
            height: 30px;
            color: #666666;
        }
        .returnListMoney div.returnListMoneyTable dl dt abbr{
            display: inline-block;
            vertical-align: middle;
            width: 86px;
            height: 30px;
            line-height: 30px;
            border:1px solid #e5e5e5 ;
            margin-right: -4px;
            text-align: center;
            margin-left: -1px;
            background: #edf3f8;
        }
        .returnListMoney div.returnListMoneyTable dl dd{
            width: 623px;
            height: 30px;
            background: #fff;
        }
        .returnListMoney div.returnListMoneyTable dl dd abbr{
            display: inline-block;
            vertical-align: middle;
            width: 86px;
            height: 30px;
            line-height: 30px;
            border:1px solid #dedede ;
            margin-right: -4px;
            text-align: center;
            margin-left: -1px;
            white-space: nowrap;
            overflow: hidden;
            text-overflow:ellipsis;
        }
        .returnListMoney div.returnListMoneyRate{
            width: 680px;
            height:auto;
            overflow: hidden;
        }
        .returnListMoney div.returnListMoneyRate h2{
            margin-left: 20px;
            font-weight: bold;
            color: #000;
        }
        .returnListMoney div.returnListMoneyRate li{
            width: 640px;
            margin-left: 30px;
            text-align: center;
            margin-bottom: 20px;
        }
        .returnListMoney div.returnListMoneyRate li span.returnListMoneyRateNote{
            display: inline-block;
            vertical-align: middle;
            width: 200px;
            height: auto;
            overflow: hidden;
            margin-top: -5px;
        }
        .returnListMoney div.returnListMoneyAction{
            width: 680px;
            height:auto;
        }
        .returnListMoney div.returnListMoneyAction span {
            margin-left: 20px;
            font-weight: bold;
            color: #000;
        }
        .returnListMoney div.returnListMoneyAction li{
            margin: 10px 0px 10px 20px;
        }
        #returnListMoneyActionConfirm{
            width: 90px;
            height: 30px;
            border-radius: 3px;
            color: #fff;
            background: #54a6de;
            margin-left: 95px;
            border: 1px solid transparent;
        }
        #returnListMoneyActionCancel{
            width: 90px;
            height: 30px;
            border-radius: 3px;
            background: #fff;
            color: #999;
            border: 1px solid #999;
            margin-left: 30px;
        }




        .returnListItem{
            width: 900px;
            height: auto;
            overflow: hidden;
            font-size: 14px;
            position: fixed;
            left: 15%;
            top: 15%;
            background: #fff;
            display: none;
            z-index:2;
        }
        .returnListItem h1 span{
            margin-top: 30px;
            font-size: 24px;
            margin-left: 20px;
        }
        .returnListItem h1 i{
            display: inline-block;
            vertical-align: middle;
            width: 25px;
            height: 25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
        }
        .returnListItem div.returnListItemTable{
            width: 900px;
            height: 110px;
        }
        .returnListItem div.returnListItemTable dl{
            width: 888px;
            height: 65px;
            margin-left: 10px;
            margin-top: 20px;

        }
        .returnListItem div.returnListItemTable dl dt{
            width: 888px;
            height: 30px;

            color: #666666;
        }
        .returnListItem div.returnListItemTable dl dt abbr{
            display: inline-block;
            vertical-align: middle;
            width: 109px;
            height: 30px;
            line-height: 30px;
            border:1px solid #e5e5e5 ;
            margin-right: -4px;
            text-align: center;
            margin-left: -1px;
            overflow: hidden;
            background: #edf3f8;
        }
        .returnListItem div.returnListItemTable dl dd{
            width: 888px;
            height: 30px;
            background: #fff;
        }
        .returnListItem div.returnListItemTable dl dd abbr{
            display: inline-block;
            vertical-align: middle;
            width: 109px;
            height: 30px;
            line-height: 30px;
            border:1px solid #dedede ;
            margin-right: -4px;
            text-align: center;
            overflow: hidden;
            margin-left: -1px;
            overflow: hidden;
        }
        .returnListItem div.returnListItemRate{
            width: 900px;
            height:auto;
            overflow: hidden;
        }
        .returnListItem div.returnListItemRate h2{
            margin-top: 20px;
            margin-left: 20px;
            margin-bottom: 20px;
            font-weight: bold;
            color: #000;
        }
        .returnListItem div.returnListItemRate li{
            width: 840px;
            margin-left: 30px;
            text-align: center;
            margin-bottom: 20px;
        }
        .returnListItem div.returnListItemRate li span.returnListItemRateNote{
            display: inline-block;
            vertical-align: middle;
            width: 300px;
            height: auto;
            overflow: hidden;
            margin-top: -5px;
        }
        .returnListItem div.returnListItemAction{
            width: 900px;
            height:auto;
        }
        .returnListItem div.returnListItemAction h2 {
            margin-left: 20px;
            font-weight: bold;
            color: #000;
            margin-bottom: 20px;
        }
        .returnListItem div.returnListItemAction li{
            margin: 10px 0px 10px 20px;
        }
        .returnListItem div.returnListItemAction li span{
            margin-left: 50px;

        }
        .returnListItem div.returnListItemAction li input{
            width: 80px;
        }
        #returnListItemActionConfirm{
            width: 90px;
            height: 30px;
            border-radius: 3px;
            color: #fff;
            background: #54a6de;
            margin-left: 95px;
            border: 1px solid transparent;
        }
        #returnListItemActionCancel{
            width: 90px;
            height: 30px;
            border-radius: 3px;
            background: #fff;
            color: #999;
            border: 1px solid #999;
            margin-left: 30px;
        }

        .returnListItem div.returnListItemTableTwo{
            width: 900px;
        }
        .returnListItem div.returnListItemTableTwo dl{
            width: 880px;

            margin-left: 10px;
            margin-top: 20px;
        }
        .returnListItem div.returnListItemTableTwo dl dt{
            width: 880px;
            height: 30px;

            color: #666666;
        }
        .returnListItem div.returnListItemTableTwo dl dt abbr{
            display: inline-block;
            vertical-align: middle;
            width: 220px;
            height: 30px;
            line-height: 30px;
            border:1px solid #e5e5e5 ;
            margin-right: -4px;
            text-align: center;
            margin-left: -1px;
            overflow: hidden;
            background: #edf3f8;
        }
        .returnListItem div.returnListItemTableTwo dl dd{
            width: 880px;
            height: 70px;
            background: #fff;
        }
        .returnListItem div.returnListItemTableTwo dl dd abbr{
            display: inline-block;
            vertical-align: middle;
            width: 220px;
            height: 70px;
            line-height: 70px;
            border:1px solid #dedede ;
            margin-right: -4px;
            text-align: center;
            margin-left: -1px;
            overflow: hidden;
        }
        .returnListItem div.returnListItemTableTwo dl dd abbr img{
            width:70px;
            height:70px;
            margin-left: 20px;
        }
        .returnListItem div.returnListItemOrderBefore{
            margin-top: 20px;
        }
        .returnListItem div.returnListItemOrderBefore ul h2{
            margin-left: 20px;
            margin-top: 20px;
            margin-bottom: 10px;
            font-weight: bold;
            color: #000;
        }
        .returnListItem div.returnListItemOrderBefore ul{
            width: 880px;
            height: 140px;
            border: 1px solid #dedede;
            margin-left: 15px;
        }
        .returnListItem div.returnListItemOrderBefore ul li{
            float: left;
            margin: 10px 20px 0px 20px;
            width: 350px;
            height: 30px;
        }
        .returnListItem div.returnListItemlogistics h2{
            margin-left: 20px;
            margin-top: 20px;
            margin-bottom: 10px;
            font-weight: bold;
            color: #000;
        }
        .returnListItem div.returnListItemlogistics ul{

            margin-left: 20px;
        }
        .returnListItem div.returnListItemlogistics ul li{
            margin-top:10px;
        }
        .returnListItem div.returnListItemLeaveMessage{
            margin: 20px 0px;
            padding-left: 20px;
        }
        .returnListItem div.returnListItemLeaveMessage h2{
            margin-top: 20px;
            margin-bottom: 10px;
            font-weight: bold;
            color: #000;
        }
        .returnListItem div.returnListItemLeaveMessage span{
            display: inline-block;
            vertical-align: middle;
            width: 800px;
            height: auto;
            overflow: hidden;
            margin-top: -5px;
        }
        #holder_All{
            margin-top: 20px;
        }

        .orderStatus{
            color: #fff!important;
            display:inline-block;
            vertical-align: middle;
            margin: 25px 0px 0px 25px;
            border-radius: 2px;
            padding: 2px;
        }
        td section{
            margin-left: 23px!important;
        }
        td img{
            margin-top: 20px;
        }
        .detailCheck{
            color: #54a6de;
        }
    </style>
    <script type="text/javascript">

        var isEnd = ( ${isEnd} == 1 ) ? true : false;
        var isTop = false;
        var eid = ${enterpriseInfo.enterpriseId};
        var ename = "${enterpriseInfo.enterpriseName}";
    </script>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; height:auto;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>订单管理</span><abbr></abbr>
    </div>

<#--<div class="sum">-->
    <#--订单总金额：<span>13000</span>邮豆，其中现金：<span>3000</span>元，邮豆：<span>7000</span>邮豆-->
<#--</div>-->

<div class="accountSearch">
    <ul>
        <#if isEnd == "0">
            <li style="width: 320px;"><input class="allinputButton" placeholder="查看范围" value="${enterpriseInfo.enterpriseName}" style="width: 280px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterpriseInfo.enterpriseId}" type="hidden" class="enterpriseIdChoosen"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
        </#if>
        <li style="width: 200px"><input style="width: 180px" class="allinputButton" placeholder="退单号" type="text" name="accountNum" /></li>
        <li style="width: 200px"><input class="allinputButton" placeholder="手机号"  type="text" name="accountCust" /></li>
        <li style="width: 200px"><input style="width: 180px" class="allinputButton" placeholder="订单号"  type="text" name="accountTel" /></li>
        <li style="width: 200px;margin-top:14px" class="allinputButton"><span>退单状态</span>
            <dl class="select"> <i value="0" class="arrow"></i>
                <dt>全部</dt>
                <dd class="allSelectButton" value="">全部</dd>
            <#list status as object>
                <#if object.getTag() != '同意退货'>
                    <dd class="allselectbutton" value="${object.name()}">${object.getTag()}</dd>
                </#if>
            </#list>
            </dl>
        </li>
        <li style="width:300px;margin-left: 20px;"><input class="allinputButton" placeholder="退单开始时间" type="text" style="width:256px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
        <li style="width:300px;"><input class="allinputButton" placeholder="退单结束时间"  type="text" style="width:256px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
        <li style="width:500px;">
            <input class="allseachButton" type="button" value="搜索" id="accSearch" />
            <input class="allclickButton" type="button" id="accExport" value="导出退货订单" />
            <input class="allclickButton" type="button" id="accChooseExport" value="导出选中" />
        </li>
    </ul>
</div>

<div class="accountTable">
    <ul>
        <li style="width: 100px;">全部订单</li>
        <li>待付款</li>
        <li>待发货</li>
        <li>待收货</li>
        <li>待提货</li>
        <li>已完成</li>
        <li>已取消</li>
        <li class="accOn" style="margin-left:80px; width: 150px;">全部退单</li>
    </ul>
    <table style="z-index: -1;" role="none" class="account_Table" border="0" cellspacing="0" cellpadding="0">
        <thead style="display:inline-block; width:1604px; height:40px; color:#555555; margin-left:-2px;border-top: 1px solid #e5e5e5;" >
        <tr>
            <th style="display:inline-block; width:0px; height:40px; overflow:hidden;">订单号</th>
            <th style="display:inline-block; width:400px; height:40px; line-height:40px; border-style:none;">商品</th>
            <th style="display:inline-block; width:80px; height:40px; line-height:40px; border-style:none;">金额/数量</th>
            <th style="display:inline-block; margin-left:0px;margin-right:100px;width:180px; height:40px; line-height:40px; border-style:none;">收货人</th>
            <th style="display:inline-block; width:250px; height:40px; line-height:40px; text-align:left; border-style:none;">退单时间</th>
            <th style="display:inline-block; width:200px; height:40px; line-height:40px; text-align:left; border-style:none;">退单状态</th>
            <th style="display:inline-block; width:200px; height:40px; line-height:40px; text-align:left; border-style:none;">退单金额</th>
            <th style="display:inline-block; width:150px; height:40px; line-height:40px; text-align:left; border-style:none;">商家</th>
        </tr>
        </thead>
        <tbody >
        </tbody>
    </table>
    <div id="itemContainer_All" class="itemContainer"></div>
    <div id="holder_All" class="holder allcpageTurnButton"></div>
</div>

<div class="returnListMoney allpop">
    <h1><span>查看详情</span> <i id="xx1"></i></h1>
    <div class="returnListMoneyTable">
        <dl>
            <dt><abbr>退款原因</abbr>
                <abbr>申请凭据</abbr>
                <abbr>退款金额</abbr>
                <abbr>退款明细</abbr>
                <abbr>问题说明</abbr>
                <abbr>上传凭证</abbr>
                <abbr>退款状态</abbr>
            </dt>
            <dd>
                <abbr>无</abbr>
                <abbr>申请凭据</abbr>
                <abbr>退款金额</abbr>
                <abbr>退款明细</abbr>
                <abbr>无</abbr>
                <abbr>无</abbr>
                <abbr>退款状态</abbr>
            </dd>
        </dl>
    </div>
    <div class="returnListMoneyRate">
        <h2>操作日志</h2>
        <ul>

        </ul>
    </div>
    <div class="returnListMoneyAction">
        <span>操作(重要)</span>
        <ul>
            <li><input style="margin-right: 10px;" type="radio" checked="checked" name="forMoney" value="true">同意退款  <input style="margin-left: 30px;margin-right: 10px" type="radio" name="forMoney" value="false">拒绝退款 </li>
            <li><abbr style="position: relative; top: -40px;">给客户留言:</abbr><textarea style="margin-left: 20px;" rows="3" cols="30"></textarea></li>
            <li>
                <input class="allseachButton" id="returnListMoneyActionConfirmUntop" type="button" value="保存">
                <input class="allcancelButton" id="returnListMoneyActionCancelUntop" type="button" value="取消">
            </li>
        </ul>
    </div>
</div>


<div class="returnListItem allpop" >
    <h1><span>查看详情</span> <i id="xx2"></i></h1>
    <div class="returnListItemTable">
        <dl>
            <dt>
                <abbr>退款原因</abbr>
                <abbr>申请凭据</abbr>
                <abbr>退款金额(邮豆)</abbr>
                <abbr>退款现金金额(元)</abbr>
                <abbr>问题说明</abbr>
                <abbr>上传凭证</abbr>
                <abbr>商品返回方式</abbr>
                <abbr>退款状态</abbr>
            </dt>
            <dd >
                <abbr>无</abbr>
                <abbr>申请凭据</abbr>
                <abbr>退款金额</abbr>
                <abbr>退款明细</abbr>
                <abbr>无</abbr>
                <abbr>无</abbr>
                <abbr>退款状态</abbr>
                <abbr>退款状态</abbr>
            </dd>
        </dl>
    </div>

    <div class="returnListItemTableTwo">
        <dl>
            <dt><abbr>退货商品</abbr><abbr>商品货号</abbr><abbr>商城价</abbr><abbr>数量</abbr>
            </dt>
            <div></div>
        </dl>
    </div>

    <div class="returnListItemOrderBefore">

        <ul>
            <h2>原订单概况</h2>
            <li>订单编号:<span>2131231231312</span></li>
            <li>订单交易金额(含运费):<span>80.00</span>邮豆</li>
            <li>支付明细(含运费):<span>80.00</span>邮豆
                <#--+<span>30.00</span>元</li>-->
            <li>运费:<span>10.00</span>邮豆</li>
        </ul>
    </div>

    <div class="returnListItemRate">
        <h2>操作日志</h2>
        <ul>
            <li>
                <abbr>2016-03-24   16:24:24</abbr>
                <abbr><img src="${bath}/static/img/com_order_icon.png"></abbr>
                <abbr class="returnListItemRate_content"></abbr>
            </li>
        </ul>
    </div>

    <div class="returnListItemlogistics">
        <h2>退货物流信息</h2>
        <ul>
            <li>退货物流名称:<span>圆通快递</span></li>
            <li>退货物流单号:<span>54656565456465</span></li>
        </ul>
    </div>
    <div class="returnListItemLeaveMessage">
        <h2>给商城留言</h2>
        给商城留言:<span></span>
    </div>

    <div class="returnListItemAction" id="returnListItemAction1">
        <h2>操作(重要)</h2>
        <ul>
            <li><input style="margin-right: 10px;" type="radio" checked="checked" name="forAllowGoods" value="true">同意退货<input style="margin-left: 30px;margin-right: 10px" type="radio" name="forAllowGoods" value="false">拒绝退货</li>
            <li>
                <abbr style="position: relative; top: -40px;">给商城留言:</abbr><textarea style="margin-left: 20px;" rows="3" cols="30" class="backRemark"></textarea>
            </li>
            <li>
                <input class="allseachButton" id="returnListItemActionConfirmUntopAllow" type="button" value="保存" data-creditOrderNo="wait">
                <input class="allcancelButton" id="returnListItemActionCancelUntop" type="button" value="取消">
            </li>
        </ul>
    </div>
    <div class="returnListItemAction" style="display: none" id="returnListItemAction2">
        <h2>操作(重要)</h2>
        <ul>
            <li><input style="margin-right: 10px;" type="radio" checked="checked" name="forCollectGoods" value="true">确认收货<input style="margin-left: 30px;margin-right: 10px" type="radio" name="forCollectGoods" value="false">拒绝收货</li>
            <li>
                <abbr style="position: relative; top: -40px;">给商城留言:</abbr><textarea style="margin-left: 20px;" rows="3" cols="30" class="backRemark" id="backRemarkForE"></textarea>
                <abbr style="position: relative; top: -40px;">给客户留言:</abbr><textarea style="margin-left: 20px;" rows="3" cols="30" class="backRemark" id="backRemarkForC"></textarea>
            </li>
            <li>
                <input class="allseachButton" id="returnListItemActionConfirmUntopEnsure" type="button" value="保存" data-creditOrderNo="wait">
                <input class="allcancelButton" id="returnListItemActionCancelUntop2" type="button" value="取消">
            </li>
        </ul>
    </div>
</div>

<script type="text/javascript">
    $(document).ready(function(){
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        $( ".returnListMoney" ).draggable();
        $( ".returnListItem" ).draggable();

        $(".arrow").click(function(){
            var arr=$(this).val()
            $(this).siblings("dd").slideToggle()
            if(arr==0){
                $(this).css("background","url(${bath}/static/img/com_btn_arrow_black_up.png) center no-repeat")
                $(this).val("1")
            }else if(arr==1){
                $(this).css("background","url(${bath}/static/img/com_btn_arrow_black_down.png) center no-repeat")
                $(this).val("0")
            }

        })
        $(".select dd").click(function(){
            var sechtml = $(this).html();
            var secval = $(this).attr("value");
            $(this).hide().siblings("dd").hide();
            $(this).siblings("dt").html(sechtml);
            $(this).siblings("dt").attr("value",secval);
            $(this).siblings(".arrow").css("background","url(${bath}/static/img/com_btn_arrow_black_down.png) center no-repeat")
        })
        $("#returnListMoneyActionCancelUntop").click(function(){
            discoverHtml();
            $(".returnListMoney").fadeOut();
        })
    })

</script>
<div class="chooeseDepot allpop"></div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
<script src="${bath}/static/js/accountReturn.js?version=${VERSION}"></script>
    </div>
</body>
</html>
