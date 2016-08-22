<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/voucherManager.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <script src="${bath}/static/js/zrj_ajaxPages.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
</head>
<style type="text/css">
    @media screen and ( max-width: 1360px){
        body{
            zoom:62.5%;
            font-size:10px!important;
        }
    }
    .billButton{
        width:1850px;
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
        background:#f4f4f4;
        margin-right:10px;
        color: #333;
        cursor: pointer;
    }



    .billButton li input{
        width:215px;
        height:35px;
        border:none;
        border: 1px solid #ccc;
        background:#FFF;
        margin-left: -1px;
        font-size:16px;
    }
    .billButton li input:hover{
    }
    .billButton li:first-child{
        width:217px;
    }
    .billButton li:nth-child(2){
        width:215px;
    }
    .billButton li:nth-child(3){
        width:245px;
    }
    .billButton li:nth-child(4){
        width:245px;
    }
    .date_button{
        display: inline-block;
        vertical-align: middle;
        width:24px!important;
        margin-left: 0!important;
        height:24px;
        background-color: #f2f2f2;
        background-image: url("${bath}/static/img/date_img.png");
        position: relative;
        left: -45px;
    }
    .xdsoft_datetimepicker{
        width:310px!important;
    }
    #voucherSearch{
        width:75px;
        height:35px;
        margin-left:20px;
        color:#FFF;
        background:#54a6de;
        border:1px solid transparent;
    }

    .voucherTable{
        width:1600px;
        height:800px;
        margin-left:20px;
    }
    .voucherTable ul{
        width:1600px;
        height:35px;
    }
    .voucherTable ul li{
        width:85px;
        height:33px;
        text-align:center;
        line-height:33px;
        margin-left:20px;
        float:left;
        cursor:pointer;
        border-bottom: 2px solid transparent;
    }
    .voucherTable ul li.reqOn{
        color:#54a6de;
        border-bottom: 2px solid #54a6de;
    }

    .myVoucherTodo{
        width:1600px;
        height:700px;
        margin-left:20px;
    }
    .myVoucherTodo dt{
        width:1600px;
        height:40px;
        border-bottom: 1px solid #e5e5e5;
        border-top: 1px solid #e5e5e5;
    }
    .myVoucherTodo dd{
        width:1600px;
        height:45px;
        background:#FFF;
    }


    .myVoucherTodo dt abbr{
        display:inline-block;
        width:250px;
        height:40px;
        text-align:left;
        color:#666666;
        line-height:40px;
    }
    .myVoucherTodo dd abbr{
        display:inline-block;
        width:250px;
        height:45px;
        text-align:left;
        line-height:45px;
        color:#666666;
        vertical-align: middle;
    }


    .myVoucherDone{
        width:1600px;
        height:700px;
        margin-left:20px;
        display: none;
    }
    .myVoucherDone dt{
        width:1600px;
        height:40px;
        border-bottom: 1px solid #e5e5e5;
        border-top: 1px solid #e5e5e5;
    }
    .myVoucherDone dd{
        width:1600px;
        height:45px;
        background:#FFF;
    }


    .myVoucherDone dt abbr{
        display:inline-block;
        width:250px;
        height:40px;
        text-align:left;
        color:#666666;
        line-height:40px;
    }
    .myVoucherDone dd abbr{
        display:inline-block;
        width:250px;
        height:45px;
        text-align:left;
        line-height:45px;
        color:#666666;
        vertical-align: middle;
    }




    .myVoucherRequest{
        width:1600px;
        height:700px;
        margin-left:20px;
        display: none;
    }
    .myVoucherRequest dt{
        width:1600px;
        height:40px;
        border-bottom: 1px solid #e5e5e5;
        border-top: 1px solid #e5e5e5;
    }
    .myVoucherRequest dd{
        width:1600px;
        height:45px;
        background:#FFF;
    }


    .myVoucherRequest dt abbr{
        display:inline-block;
        width:250px;
        height:40px;
        text-align:left;
        color:#666666;
        line-height:40px;
    }
    .myVoucherRequest dd abbr{
        display:inline-block;
        width:250px;
        height:45px;
        text-align:left;
        line-height:45px;
        color:#666666;
        vertical-align: middle;
    }
    .vocherCode{
        color: #54a6de!important;
        cursor: pointer!important;
    }

    .billButton li dl{
        display: inline-block;
        vertical-align: middle;
        margin-top: 2px;
        width:120px;
        height:30px;
        margin-left:-5px;
        background:#f8f8f8;
        text-align: center;
        position: relative;
    }
    .arrow{
        display: inline-block;
        position: absolute;
        right: 0px;
        top: 0px;
        width: 36px;
        height: 35px;
        background:url(${bath}/static/img/com_btn_arrow_black_down.png) center no-repeat;
    }
    .billButton li dl dd,.billButton li dl dt{
        width:120px;
        height:28px;
        background:#ffffff;
        margin-top: -1px;
        margin-left:-1px;
        cursor: pointer;
        line-height: 28px;
    }
    .billButton li dl dd{
        display: none;
    }

</style>
<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
</script>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
    <#if isEnd == true><a href="InventoryManager">仓库管理</a></#if><#if isTop == true><a href="InventoryManager">仓库管理</a></#if><a href="inventoryInquiry">库存查询</a><#if isTop == true || isEnd == true><span>单据管理</span><#else><span>单据管理</span></#if><abbr></abbr>
    </div>

<div class="billButton">
    <ul>
        <li class="Type allinputButton"><span class="groupName">单据类型</span>
            <dl class="select"> <i value="0" class="arrow arrowType"></i>
                <dt data-typestr="" id="type"><input id="typeInput" type="hidden" value=""/><abbr>全部</abbr></dt>
                <dd class="allSelectButton" >全部</dd>
                <#list billType as object>
                    <dd class="allSelectButton" data-typestr="${object.name()}">${object.getName()}单</dd>
                </#list>
            </dl>
        </li>
        <li class="Status allinputButton"><span class="groupName">单据状态</span>
            <dl class="select"> <i value="0" class="arrow arrowStatus"></i>
                <dt data-typestr="" id="status"><input id="statusInput" type="hidden" value=""/><abbr>全部</abbr></dt>
                <dd class="now All allSelectButton">全部</dd>
                <#list billStatus as object>
                    <dd data-typestr="${object.name()}" class="now billStatus allSelectButton">${object.getName()}</dd>
                </#list>
            </dl>
        </li>
        <li><input class="allinputButton" placeholder="单据编号" type="text" id="voucherOrder"/></li>
        <li><input class="allinputButton" placeholder="创建人"  type="text" id="voucherName"/></li>
        <li>
            <input class="allinputButton" placeholder="开始时间"  type="text" style="width:197px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a>
        </li>
        <li>
            <input class="allinputButton" placeholder="结束时间"  type="text" style="width:197px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a>
        </li>
        <li> <input class="allseachButton" type="button" id="voucherSearch" value="搜索" /></li>

    </ul>
</div>


<div class="voucherTable">
    <ul>
        <li value="0" class="reqOn">待办事宜</li>
        <li value="1">已办事宜</li>
        <li value="2">我的请求</li>
    </ul>
    <dl class="myVoucherTodo">
        <dt><abbr>单据类型</abbr><abbr>单据编号</abbr><abbr>申请账号</abbr><abbr>创建日期</abbr><abbr>单据状态</abbr><abbr>当前操作者</abbr>
        </dt>
        <div id="itemContainer_notHandled"></div>
        <div class="allcpageTurnButton" id="holder_notHandled"></div>

    </dl>
    <dl class="myVoucherDone">
        <dt><abbr>单据类型</abbr><abbr>单据编号</abbr><abbr>申请账号</abbr><abbr>创建日期</abbr><abbr>单据状态</abbr><abbr>当前操作者</abbr>
        </dt>
        <div id="itemContainer_done"></div>
        <div class="allcpageTurnButton" id="holder_done"></div>
    </dl>



    <dl class="myVoucherRequest">
        <dt><abbr>单据类型</abbr><abbr>单据编号</abbr><abbr>申请账号</abbr><abbr>创建日期</abbr><abbr>单据状态</abbr><abbr>当前操作者</abbr>
        </dt>

        <div id="itemContainer_myRequest"></div>
        <div class="allcpageTurnButton" id="holder_myRequest"></div>
    </dl>

</div>
</div>
</body>