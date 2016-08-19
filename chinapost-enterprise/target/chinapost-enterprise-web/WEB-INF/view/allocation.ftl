<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery-ui.css">
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js"></script>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jquery.datetimepicker.css" />
    <script type="text/javascript" src="${bath}/static/js/allocation.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <title>调拨流程</title>
</head>
<style type="text/css">
    @media screen and ( max-width: 1360px){
        body{
            zoom:62.5%;
            font-size:10px!important;
        }
    }
    .reTop{
        width: 100%;
        height: 50px;
        line-height: 50px;
        background: #fff;
    }
    .topButton{
        width: 50px;
        height: 30px;
        border: 1px solid #54a6de;
        color: #54a6de;
        background: #fff;
    }
    .topButton:hover{
    }
    .topButton:active{
        background: #54a6de;
        color: #fff;
    }
    .bodyFirst{
        width: 100%;
        height: auto;
        overflow: hidden;
        margin: auto;
    }
    .bodyFirst ul{
        width: 1110px;
        height: auto;
        overflow: hidden;
        margin: auto;
    }
    .bodyFirst ul li{
        width: 350px;
        height: 40px;
        line-height: 40px;
        border: 1px solid #54a6de;
        margin-left: 15px;
        margin-bottom: 15px;
        float: left;
    }
    .bodyFirst ul li span{
        display: inline-block;
        height: 100%;
        width: 30%;
        text-align: center;
        background: #54a6de;
        color: #fff;
        border-right:1px solid #54a6de;
    }
    .bodyFirst ul li abbr{
        display: inline-block;
        height: 100%;
        width: 65%;
        padding-left: 5px;
        vertical-align: middle;
        overflow: hidden;
    }
    .bodyFirst ul li abbr input{
        width: 100%;
        height: 80%;
    }
    .bodySecond{
        width: 917px;
        height: auto;
        overflow: hidden;
        margin: auto;
    }
    .bodySecond h1{
        width: 100%;
        height: 50px;
        line-height: 50px;
        padding-left: 5px;
        font-size: 24px;
    }
    .bodySecond dl{
        width: 100%;
        height: auto;
        overflow: hidden;
        margin: auto;
        border-left: 1px solid #54a6de;
        border-top: 1px solid #54a6de;
    }
    .bodySecond dt{
        background: #edf3f8;
    }
    .bodySecond dt,.bodySecond dd{
        width: 100%;
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid #54a6de;

    }
    .bodySecond dd:nth-child(2n-1){
        background: #fefefe;
    }
    .bodySecond dt abbr:first-child{
        width:128.5px ;
    }
    .bodySecond dt abbr:nth-child(2){
        width:228.5px ;
    }
    .bodySecond dd abbr:first-child{
        width:128.5px ;
    }
    .bodySecond dd abbr:nth-child(2){
        width:228.5px ;
        overflow: hidden;
    }
    .bodySecond abbr{
        display: inline-block;
        vertical-align: middle;
        width: 178.5px;
        height: 50px;
        text-align: center;
        border-right: 1px solid #54a6de;
        padding: 0px 1.8px;
        overflow: hidden;
    }
    .bodySecond abbr input[type="text"]{
        width: 80%;
        height: 80%;
    }
    #addIvent{
        width: 1000px;
        height: auto;
        position: absolute;
        left: 10%;
        top: 15%;
        background: #fff;
        display: none;
        z-index: 3;
    }
    #addIvent h1{
        width: 100%;
        height: 50px;
        font-size: 24px;
        line-height: 50px;
        border-bottom: 1px solid #bbb;
    }
    #xx{
        display:inline-block;
        vertical-align:middle;
        width:25px;
        height:25px;
        margin-left:800px;
        background:url(${bath}/static/img/XX.png) center no-repeat;
        cursor:pointer;
    }
    #addIvent li:first-child{
        width: 800px;
        margin: auto;
        height: 80px;
        line-height: 80px;
    }
    #addIvent li:first-child abbr{
        display: inline-block;
        vertical-align: middle;
        width: 200px;
    }
    #addIvent li:first-child abbr span{
        display: inline-block;
        vertical-align: middle;
        width: 80px;
        background: #f1f1f1;
        border-right: 1px solid #bbb;
        text-align: center;
    }
    #addIvent li:first-child abbr input[type='text']{
        width: 160px;
        height: 90%;
        border: none;
    }
    #addIvent li:first-child abbr input[type='button']{
        width: 80px;
        height: 50px;
        background: #54a6de;
        color: #fff;
        margin:0px 10px;
    }
    #addIvent li:nth-child(2){
        width: 800px;
        margin: auto;
        height: auto;
        overflow: hidden;
    }
    #addIvent li:nth-child(2) dl{
        width: 800px;
        height: auto;
        overflow: hidden;
    }
    #addIvent li:nth-child(2) dl dt{
        width: 798px;
        height: 30px;
        line-height: 30px;
        border: 1px solid #bbbbbb;
        background: #f3f3f3;
    }
    #addIvent li:nth-child(2) dl dd{
        width: 798px;
        height: 30px;
        line-height: 30px;
        border: 1px solid #bbbbbb;
        border-top: none;
    }
    #addIvent li:nth-child(2) dl dt abbr,#addIvent li:nth-child(2) dl dd abbr{
        display: inline-block;
        vertical-align: middle;
        width: 128px;
        height: 30px;
        line-height: 30px;
        border-right:1px solid #bbbbbb ;
        text-align: center;
        overflow: hidden;
        padding: 0px 1.8px;
    }
    #addIvent li:nth-child(2) dl abbr input{
        margin-top: 10px;
    }
    #addIvent li dl dt abbr:nth-last-child(1),#addIvent li dl dd abbr:nth-last-child(1){
        border:none;
    }
    #addIvent li dl dt abbr:first-child,#addIvent li dl dd abbr:first-child{
        width: 58px;
    }
    #addIvent li dl dt abbr:nth-child(2),#addIvent li dl dd abbr:nth-child(2){
        width: 198px;
    }

    .suggest{
        width: 90%;
        height: 150px;
        margin:auto;
        margin-top: 20px;
    }
    .suggest textarea{
        width: 100%;
        height: 100%;
        color: #666;
    }
    .circulationSuggest{
        width: 90%;
        height: auto;
        overflow: hidden;
        margin:auto;
        margin-top: 20px;
    }
    .circulationSuggest h1{
        width: 100%;
        height: 50px;
        line-height: 50px;
        font-size: 18px;
    }
    .circulationSuggest ul{
        width: 100%;
        height: auto;
        overflow: hidden;
    }
    .circulationSuggest ul li{
        width: 99%;
        height: 80px;
        line-height: 80px;
        border-bottom: 1px solid #e5e5e5;
        margin-bottom: -1px;
    }
    .circulationSuggest ul li:nth-last-child(1){
        margin-bottom: 5px;
    }
    .circulationSuggest ul li dd:first-child{
        width: 250px;
        text-align: center;
    }
    .circulationSuggest ul li dd{
        display: inline-block;
        vertical-align: middle;
        float: left;
        font-size: 14px;
    }
    .circulationSuggest ul li dd:nth-child(3){
        float: right;
        line-height: 40px;
        margin-right: 10px;
    }
    #repleExamine input,#repleAgree input{
        margin-left: -1px;
    }
    .chooeseDepot input[type="button"]{
        width: 100px;
        height: 40px;
        color: #fff;
        border: none;
        border-radius: 3px;
        margin-top: 30px;
        margin-bottom: 30px;
    }
    .chooeseDepot input[type="button"]:first-child{
        margin-left: 180px;
    }
    .chooeseDepot input[type="button"]:nth-child(2){
        background: #54a6de;
        margin-left: 20px;
    }
    .chooeseDepot{
        width: 600px;
        height: auto;
        overflow: hidden;
        position: absolute;
        left: 10%;
        top: 15%;
        border: 1px solid #bbb;
        z-index: 3;
        background: #fff;
        display: none;
    }
    .chooeseDepot div:first-child{
        height: 60px;
        line-height: 60px;
        font-size: 24px;
        text-indent: 20px;
        border-bottom: 1px solid #bbb;
    }
    .chooeseDepot div:first-child i{
        display:inline-block;
        width:25px;
        height:25px;
        background:url(${bath}/static/img/XX.png) center no-repeat;
        position:relative;
        left:440px;
        top:5px;
        cursor:pointer;
    }
    .chooeseDepot div:nth-child(2){
        height: 500px;
        overflow-y:scroll ;
    }
    .chooeseDepot div:nth-child(2) ul{
        height: auto;
        overflow: hidden;
        padding-left: 20px;
        margin-left: 20px;
    }
    .chooeseDepot div:nth-child(2) ul li{
        padding: 5px 0px;
    }
    .chooeseDepot div:nth-child(2) ul li a{
        display: inline-block;
        text-align: center;
        width: 15px;
        height: 15px;
        line-height: 15px;
        border-radius: 15px;
        border: 1px solid #bbbbbb;
        margin-right: 10px;

    }
    #count dd{
        display: none;
    }



    .chooeseDepotOut input[type="button"]{
        width: 100px;
        height: 40px;
        color: #fff;
        border: none;
        border-radius: 3px;
        margin-top: 30px;
        margin-bottom: 30px;
    }
    .chooeseDepotOut input[type="button"]:first-child{
        margin-left: 180px;
    }
    .chooeseDepotOut input[type="button"]:nth-child(2){
        background: #54a6de;
        margin-left: 20px;
    }
    .chooeseDepotOut{
        width: 600px;
        height: auto;
        overflow: hidden;
        position: absolute;
        left: 10%;
        top: 15%;
        border: 1px solid #bbb;
        z-index: 3;
        background: #fff;
        display: none;
    }
    .chooeseDepotOut div:first-child{
        height: 60px;
        line-height: 60px;
        font-size: 24px;
        text-indent: 20px;
        border-bottom: 1px solid #bbb;
    }
    .chooeseDepotOut div:first-child i{
        display:inline-block;
        width:25px;
        height:25px;
        background:url(${bath}/static/img/XX.png) center no-repeat;
        position:relative;
        left:440px;
        top:5px;
        cursor:pointer;
    }
    .chooeseDepotOut div:nth-child(2){
        height: 500px;
        overflow-y:scroll ;
    }
    .chooeseDepotOut div:nth-child(2) ul{
        height: auto;
        overflow: hidden;
        padding-left: 20px;
        margin-left: 20px;
    }
    .chooeseDepotOut div:nth-child(2) ul li{
        padding: 5px 0px;
    }
    .chooeseDepotOut div:nth-child(2) ul li a{
        display: inline-block;
        text-align: center;
        width: 15px;
        height: 15px;
        line-height: 15px;
        border-radius: 15px;
        border: 1px solid #bbbbbb;
        margin-right: 10px;

    }
    #count{
        display: none;
    }
    #holder{
        margin-top: 30px;
        margin-bottom: 30px;
    }
</style>
<script type="text/javascript">
    var isTop = "${isTop}";
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    $(document).ready(function(){
        $(".invet").hide();
        var isTop = "${isTop}";
         var isEnd = "${isEnd}";
        var state =  "${inventorybill.billStatus.getName()}";

            if( state == "" || state == "待修改" ){
                $(".invet").show();
                $(".reasonForApply").prop("readonly","");
                $(".reasonForApply").css("border","none");
                $(".bodySecond").css("width","1103px");
            }

    });

</script>

<body>
<div class="reTop">
    <input type="hidden" value="${enterpriseInfo.enterpriseId}" id="enterpriseId">
    <ul>
        <li style="float: left;margin-left: 1%; color: #54a6de;font-weight: bold">
            <span style="margin-right: 10px;">调拨流程</span>-<span style="margin-left: 10px;">
        <#if inventorybill == ''>
            创建
        <#else>
        ${inventorybill.billStatus.getName()}
        </#if></span>
        </li>
        <li style="float: right;margin-right: 1%;">

        <#if status != 'done' && status != 'myrequest'>
            <#if inventorybill == ''>
                <div style="display: inline-block;" id="repleCreate"> <input type="button" class="topButton allclickButton" value="提交" id="commit"> </div>
            <#else>
                <input type="hidden" value="${inventorybill.billId}" id="billId">
                <#if inventorybill.billStatus.name() == 'WAIT_DELIVERY'>
                    <div style="display: inline-block;"> <input style="width: 120px;" type="button" id="repleSend" class="topButton allclickButton" value="确认发货"> </div>
                <#elseif inventorybill.billStatus.name() == 'WAIT_RECEIVER'>
                    <div style="display: inline-block;"> <input style="width: 120px; margin-right: 20px;"  id="repleReceive" type="button" class="topButton allclickButton" value="确认收货"><input id="repleBack" type="button" class="topButton allclickButton" value="退回"> </div>

                <#elseif inventorybill.billStatus.name() == 'WAIT_EDIT'>
                    <div style="display: inline-block;"> <input style="width: 120px; margin-right: 20px;"  id="repleSend2" type="button" class="topButton allclickButton" value="确认发货"><input type="button" class="topButton stop allclickButton" value="终止"> </div>
                </#if>
            </#if>
        </#if>



        </li>
    </ul>
</div>

<div class="bodyFirst">
    <h1 style=" width: 200px;margin: auto;margin-top: 50px;margin-bottom: 50px; font-size: 28px; font-weight: border;">调拨流程</h1>
    <ul>
        <#if inventorybill == ''>
            <li><span>单据编号</span><abbr>新申请单</abbr></li>
            <li><span>申请账号</span><abbr>${enterpriseInfo.enterpriseName}</abbr></li>
            <li><span>申请日期</span><abbr id="time">${.now?string("yyyy-MM-dd")}</abbr></li>
        <#else>
            <li><span>单据编号</span><abbr>${inventorybill.code}</abbr></li>
            <li><span>申请账号</span><abbr>${inventorybill.getTansferInfo().getCreatorName()}</abbr></li>
            <li><span>申请日期</span><abbr id="time">${inventorybill.getCreateTime()?string("yyyy-MM-dd")}</abbr></li>
        </#if>
        <#if inventorybill == ''>
            <li><span>当前状态</span><abbr>创建</abbr></li>
        <#else>
            <li><span>当前状态</span><abbr data-status="${inventorybill.billStatus.name()}">${inventorybill.billStatus.getName()}</abbr></li>
        </#if>
        <#if inventorybill == ''>
            <#if isTop>
                <li><span>*调出仓库</span><abbr class="choosenIntOut" data-id="${enterprise.enterpriseId}" style="cursor: pointer;">${enterprise.enterpriseName}</abbr></li>
                <li><span>*调入仓库</span><abbr class="choosenInt inventoryNumber" style="cursor:pointer;" id="choosenInt">请选择</abbr></li>
            <#elseif isEnd>
                <li><span>*调出仓库</span><abbr class="choosenIntOut" data-id="${enterpriseInfo.enterpriseId}" style="cursor: pointer;">${enterpriseInfo.enterpriseName}</abbr></li>
                <li><span>*调入仓库</span><abbr class="choosenInt inventoryNumber" style="cursor:pointer;" id="choosenInt">请选择</abbr></li>
            <#else>
                <li><span>*调出仓库</span><abbr class="choosenIntOut" style="cursor: pointer;" id="choosenIntOut">请选择</abbr></li>
                <li><span>*调入仓库</span><abbr class="choosenInt inventoryNumber" style="cursor:pointer;" id="choosenInt">请选择</abbr></li>
            </#if>
        <#else>
            <li><span>*调出仓库</span><abbr class="choosenIntOut"  data-id="${inventorybill.getInId()}" style="cursor: pointer;" >${inventorybill.getTansferInfo().getOutName()}</abbr></li>
            <li><span>*调入仓库</span><abbr class="choosenInt inventoryNumber" data-id="${inventorybill.getOutId()}" style="cursor:pointer;">${inventorybill.getTansferInfo().getInName()}</abbr></li>
        </#if>
        <li style="width: 717px;"><span>*调拨原因</span><abbr><input readonly="readonly" style="border:none;" type="text" class="reasonForApply" value="${inventorybill.reason}"/> </abbr></li>
    </ul>

</div>
<div class="bodySecond">
    <h1>调拨清单<span style="float: right;margin-right: 1%;">

    <#if inventorybill == ''>
        <input style="margin-right: 20px;" class="topButton allseachButton" id="addinvet" type="button" value="添加"/><input class="topButton delete allseachButton" type="button" value="删除"/>
        <#elseif inventorybill.billStatus.name() == 'WAIT_EDIT'>
            <input style="margin-right: 20px;" class="topButton allseachButton" id="addinvet" type="button" value="添加"/><input class="topButton delete allseachButton" type="button" value="删除"/>
    </#if>
    </span></h1>
    <dl>
        <dt><abbr>序号</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr class="invet">调出仓可用库存</abbr><abbr>*调拨数量</abbr>
            <#assign x = 0>
        <#if  inventorybill.billStatus.name() == 'WAIT_EDIT'>
            <#list inventorybill.items as item>
                <#assign x=x+1>
                <dd data-id="${item.getInfo().getGoodsInfoId()}"><abbr><input type="checkbox" name="invetcheck"><b>${x}</b></abbr><abbr>${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr>${item.getInfo().getAvailable()}</abbr><abbr class="outNum"><input value="${item.getAmount()}" class="change" type="text"></abbr></dd>
            </#list>
        <#else>
            <#list inventorybill.items as item>
                <#assign x=x+1>
                <dd data-id="${item.getItemId()}"><abbr><input type="checkbox" name="invetcheck"><b>${x}</b></abbr><abbr>${item.getInfo().getGoodsInfoName()}</abbr><abbr>${item.getInfo().getSpecString()}</abbr><abbr>${item.getInfo().getGoodsInfoItemNo()}</abbr><abbr class="outNum">${item.getAmount()}</abbr></dd>
            </#list>
        </#if>
        </dt>
    </dl>
</div>

<div id="addIvent" class="allpop">
    <h1>
      选择货品 <i id="xx"></i>
    </h1>
    <ul>
        <li>
            <abbr><input class="allinputButton" placeholder="货品名称" type="text" name="item_name"/></abbr>
            <abbr><input class="allinputButton" placeholder="货品编号" type="text" name="item_no"/></abbr>
            <abbr style="border: none; width: 300px">
                <input type="button" class="topButton allseachButton" value="搜索" id="searchGoods"/>
                <input type="button" class="topButton allseachButton" value="确定" id="commitGoods"/>
            </abbr>
        </li>
        <li>
            <dl>
                <dt><abbr><input type="checkbox" name="addinventcheckDT"> </abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>品牌</abbr><abbr>所属商家</abbr>
                </dt>
                <div id="itemContainer"></div>
                <div class="allcpageTurnButton" id="holder"></div>
                <div id="count"></div>
            </dl>
        </li>
    </ul>
</div>
<#if status == 'nothandled'>
    <#if  inventorybill.billStatus.name() == 'WAIT_DELIVERY' || inventorybill.billStatus.name() == 'WAIT_RECEIVER' || inventorybill.billStatus.name() == 'WAIT_EDIT'>
    <div class="suggest">
        <textarea rows="10" cols="185" placeholder="流转意见"></textarea>
    </div>
    </#if>
</#if>

<#if inventorybill != ''>
<div class="circulationSuggest">
    <h1>流转意见</h1>
    <ul>
        <#list inventorybill.getLogs() as object>
            <li>
                <dl>
                    <dd>${object.getOperatorName()}</dd>
                    <#if object.getLogAction()=="[创建]">
                        <dd></dd>
                        <#else>
                            <#if object.getLogMsg()=="">
                                <dd></dd>
                            <#else>
                                <dd>审批意见：${object.getLogMsg()}</dd>
                            </#if>
                    </#if>
                    <dd>
                        <span>${object.getLogAction()}</span>
                        <br/>
                        <span>${object.getCreateTime()?string("yyyy-MM-dd HH:mm:ss")}</span>
                    </dd>
                </dl>
            </li>
        </#list>
    </ul>
</div>
</#if>



<div class="chooeseDepot">
    <div>选择仓库<i></i></div>
    <div class="DepotDetail">
        <ul>
            <li>
                <a data-id ="${enterprise.enterpriseId}">+</a>
                <input type="radio" name="chooeseDepotOut"/>
                <span>${enterprise.enterpriseName}</span>
            </li>
        </ul>
    </div>
    <div><input type="button" value="取消" id="chooseCancel"/> <input type="button" value="确定" id="chooseConfirm"/></div>

</div>

<div class="chooeseDepotOut">
    <div>选择仓库<i></i></div>
    <div class="DepotDetail">
        <ul>
            <li>
                <a class="chooseAvailable" data-id ="${enterpriseInfo.enterpriseId}">+</a>
                <span>${enterpriseInfo.enterpriseName}</span>
            </li>
        </ul>
    </div>
    <div><input type="button" value="取消" id="chooseCancelOut"/> <input type="button" value="确定" id="chooseConfirmOut"/></div>

</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        var creat = "${inventorybill}"
        if(creat = "") {
            var now = new Date();
            var innerHTML = "";
            innerHTML = now.getFullYear() + '-' + (now.getMonth() + 1) + '-' + now.getDate();
            $("#time").html(innerHTML)
        }
        $(document).on("click","#addIvent ul li dt input[name='addinventcheckDT']",function(){
            if( $(this).is(":checked") ){
                $("#itemContainer").find("input[type='checkbox']").each(function(){
                    var flag = $(this).is(":checked");
                    if( flag ){

                    }
                    else{
                        $(this).trigger("click");
                    }
                });
            }
            else{
                $("#itemContainer").find("input[type='checkbox']").each(function(){
                    var flag = $(this).is(":checked");
                    if( flag ){
                        $(this).trigger("click");
                    }
                    else{

                    }
                });
            }

        });
    });
</script>