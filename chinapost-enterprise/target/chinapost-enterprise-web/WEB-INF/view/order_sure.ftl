<#assign bath = request.contextPath>
<!doctype html>
<html>
<head>
    <meta charset="utf-8" />
    <title>代客下单</title>
    <!-- 主要样式 -->
    <link rel="stylesheet" href="${bath}/static/css/style.css">
    <!-- 全局样式 -->
    <link rel="stylesheet" href="${bath}/static/css/g.css?version=${VERSION}">
    <!-- 分页样式 -->
    <link rel="stylesheet" href="${bath}/static/css/jPages.css">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="${bath}/static/css/xcConfirm.css">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${bath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${bath}/static/js/xcConfirm.js"></script>
    <script src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>

    <style type="text/css">
        @media screen and ( max-width: 1360px) {
            body {
                zoom: 62.5%;
                font-size: 10px !important;
            }
        }
        body{
            background-color: #f7f7f7;
        }
        .hidden_address{
            display: none;;
        }
        .newAddress{
            z-index:55;
            width:700px;
            height:auto;
            background:#FFF;
            position:fixed;
            left:10%;
            top:30%;
            border:1px solid #e5e5e5;
            border-radius:2px;

            display: none;
        }
        .newAddress span{
            display:inline-block;
            font-size:14px;
            color:#000;
            padding:5px 0px 0px 20px;
            width:698px;
            height:50px;
            background:#f7f7f7;
        }
        .newAddress ul{
            margin-top:20px;
        }

        .newAddress ul li{
            padding:5px 0px;
            font-size:12px;
        }
        .newAddress ul li abbr{
            display:inline-block;
            font-size:12px;
            width:100px;
            color:#666;
            text-align:right;
            padding:5px;
        }

        .newAddress ul li i{
            display:inline-block;
            margin:0px 3px;
            color:#ff3300;
        }
        .newAddress ul li input{
            width:180px;
            height:25px;
            border-style:none;
            border:1px solid #CCC;
            border-radius:3px;
            background:#fff;
        }
        .newAddress ul li select{
            width:100px;
            height:45px;
            padding:5px;
            border-style:none;
            border:1px solid #CCC;
            border-radius:3px;
            background:#FFF;

        }


        .inputval{
            border-style:none;
            background:transparent;
            margin-left:30px;
            width:400px;
            margin-top:-5px;
        }
        .confirm,.cancel{
            margin:0px 10px;
            display:none;
        }
        #saveAdd{
            width: 100px;
            height: 30px;
            background: #54a6de;
            border-radius: 3px;
            border:1px solid transparent;
            color: #fff;
            margin-left: 100px;
        }
        #xx{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            position:relative;

            cursor:pointer;
        }
        .hidden_address{
            width:100%;
            padding: 5px 0px;
            border-bottom: 1px solid #bbbbbb;
            font-size: 14px;
        }
        .hidden_address:hover{
            cursor:pointer;
            border-bottom:2px solid #54a6de;
        }
        .xcConfirm .popBox .ttBox{
            height:60px!important;
        }
        .hidden_address:hover .deleteAdd{
            display: inline-block;
        }
        .deleteAdd{
            display:none;
            z-index: 2;
        }
        .sendWay{
            width: 980px;
            border: 1px solid #e5e5e5;
        }
        .sendWay .tabs{
            margin-left: 10px;
            margin-top: 10px;
            margin-right: 10px;
            color: #e1e1e1;
            height: 50px;
            border-bottom: 1px solid #e5e5e5;
        }
        .sendWay .tabs .tab1{
            border-right: 1px solid #e5e5e5;
        }
        .sendWay .tabs .on{
            color: #54a6de;
        }
    </style>
</head>
<body style="background: #edf3f8">
<div class="allOutShow" style="height: auto;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>代客下单</span><a class="leftshanow" href="UserGet">网点提货</a><abbr></abbr>
    </div>
<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    var goodsId = ${id};
    var count = ${count};

$(document).ready(function(){
    var enterprisePId = $("#provinceId").val();
    var enterpriseCId = $("#cityId").val();
    var enterpriseDId = $("#districtId").val();
    $.get("${bath}/web/api/common/provincies", function (data) {
        if (data.response == 'success') {
            data.data.map(function (object) {
                var html = '';
                html += '<option value="' + object.provinceId + '">';
                html += object.provinceName;
                html += '</option>';
                $(".newAddressP").append(html);
            });
            if( window.localStorage ){
                var PID = localStorage['addDeliverInfo_provinceId'];
                var CID = localStorage['addDeliverInfo_cityId'];
                var DID = localStorage['addDeliverInfo_districtId'];
                if( PID == '' || PID == undefined){
                    PID = enterprisePId;
                }
                if( CID == '' || CID == undefined){
                    CID = enterpriseCId;
                }
                if( DID == '' || PID == undefined){
                    DID = enterpriseDId;
                }
            }
            else{
                var PID = enterprisePId;
                var CID = enterpriseCId;
                var DID = enterpriseDId;
            }
            $(".newAddressP option[value='" + PID + "']").prop("selected","selected");
            if(PID){
                UpdateMemAddressP(function(){
                    $(".newAddressC option[value='" + CID + "']").prop("selected","selected");
                },PID);
            }
            if(CID){
                UpdateMemAddressC(function(){
                    $(".newAddressD option[value='" + DID + "']").prop("selected","selected");
                },CID);
            }
        }
    }, 'json');

    $(".newAddressP").change(function () {
        $(".newAddressC option[value!='0'],.newAddressD option[value!='0'],.newAddressR option[value!='0']").remove();
        $.post("../web/api/common/cities", {
            "provinceId": $(this).val()
        }, function (data) {
            if (data.response == 'success') {
                data.data.map(function (object) {
                    var html = '';
                    html += '<option value="' + object.cityId + '">';
                    html += object.cityName;
                    html += '</option>';

                    $(".newAddressC").append(html);
                });
            }
        }, 'json');
    });
    $(".newAddressC").change(function () {
        $(".newAddressD option[value!='0'],.newAddressR option[value!='0']").remove()
        $.post("../web/api/common/districts", {
            "cityId": $(this).val()
        }, function (data) {
            if (data.response == 'success') {
                data.data.map(function (object) {
                    var html = '';
                    html += '<option value="' + object.districtId + '">';
                    html += object.districtName;
                    html += '</option>';
                    $(".newAddressD").append(html);
                });
            }
        }, 'json');
    });
    $(".newAddressD").change(function () {
        $(".newAddressR option[value!='0']").remove()
        $.post("../web/api/common/streets", {
            "districtId": $(this).val()
        }, function (data) {
            if (data.response == 'success') {
                data.data.map(function (object) {
                    var html = '';
                    html += '<option value="' + object.streetId + '">';
                    html += object.streetName;
                    html += '</option>';
                    $(".newAddressR").append(html);
                });
            }
        }, 'json');
    });
})
</script>
<input type="hidden" id="provinceId" value="${enterpriseInfo.provinceId}">
<input type="hidden" id="cityId" value="${enterpriseInfo.cityId}">
<input type="hidden" id="districtId" value="${enterpriseInfo.districtId}">
<article class="userItem">
    <header>下单用户身份证号</header>
    <section>
        <input maxlength="18" placeholder="请输入身份证号" type="text" pattern="^\d{17}[\w\d]$" id="checkUser"/><i style="font-size: 24px; color: #ff3300;" id="tixing"></i>
        <div id="userInfo" style="display: none;">
            <img id="userImg"  width="110" height="110"/>
            <input type="hidden" id="customerId" />
            <span style="font-size:20px;" id="line_1"></span>
            <span id="line_2">&nbsp;&nbsp;&nbsp;<abbr id="line_4"></abbr> </span>
            <span id="line_3">本网点邮豆余额:<b></b>邮豆</span>
        </div>
    </section>
</article>
<article class="delivery" style="height:auto;">
    <header>配送方式</header>
    <section class="sendWay">
        <div class="tabs">
            <span class="tab1 on">网点自提</span><span class="tab2">物流配送</span>
            <a id="addAddress" style="position:absolute;display:inline-block;right:43px;color:#0099ff;text-decoration:none;cursor:pointer;font-weight:normal;">新增收货地址</a>
        </div>
        <div class="tabs_content" >
            <header>提货网点</header>
            <div>
                <span id="enterpriseName"></span>&nbsp;&nbsp;&nbsp;<span  id="enterpriseAddress"></span>
                <#if hasValet == false>
                    <br /><span style="display:inline-block;margin-top:20px;color:red;">! 本网点对于该商品的库存不足，请及时补充</span>
                </#if>
            </div>
        </div>
        <div class="tabs_content post" style="position:absolute;top:50px;display: none;height:auto;min-height:150px;z-index:1;">
            <header style="position:relative;">收货人信息</header>
            <div id="container">
                <span id="defaultAddress">
                    <input type="hidden" id="defaultAddressId" />
                    <#--<span style="display:inline-block;border:1px solid #0099ff;width:130px;height:40px;line-height:40px;font-weight:bold;" id="defaultAddressSquare">-->
                    <#--<span style="margin-left:35px;" class="address_fullname" id="defaultFullName"></span>-->
                </span>
                <span style="margin-left:30px;" class="address_fullname" id="defaultUserName">
                    <#---->
                </span><span style="margin-left:30px;" id="address_address"></span><span style="margin-left:30px;" id="address_mobile"></span>
          </span>
                <div style="margin-left:23px;" id="moreAddress">更多地址<img src="${bath}/static/img/blue_down_arrow.png" style="margin-left:10px;"></div>
            </div>
        </div>
    </section>

</article>
<article class="list" style="padding-top:40px;">
    <header>商品清单</header>
    <section style="padding-top:15px;">
        <table style="border:1px solid #e5e5e5;width:970px;font-size:14px;">
            <tr style="color:#666666;border-bottom: 1px solid #e5e5e5; height: 40px;">
                <td style="padding-left:20px; border-style:none;">商品</td>
                <td>单价</td>
                <td>数量</td>
            </tr>
            <tr>
                <td style="padding:20px 0 20px 28px;position:relative; height:120px;">
                    <img src="${goodInfo.goodsInfoImgId}" style="width:90px;height:90px;vertical-align:top;"/><span style="position:absolute;top:30px;left:130px;">${goodInfo.goodsInfoName}</span>
                </td>
                <td>${goodInfo.goodsInfoPreferPrice?string("#.##")}邮豆</td>
                <td>${count}</td>
            </tr>
        </table>
    </section>
</article>
<article class="bill" style="width:970px;">
    <section style="text-align:right;font-size:17px;font-weight:bold;">
        <div><span style="">${count}件商品，总商品金额：</span><span style="display: inline-block;width: 150px;margin-left: 20px"><b id="itemPrice">${ ( count * goodInfo.goodsInfoPreferPrice )?string("#.##") }</b>邮豆</span></div>
        <div><span style="">运费：</span><span style="display: inline-block;width: 150px;margin-left: 20px"><b id="yunfei">0</b>邮豆</span></div>
        <div><span style="">应付金额：</span><span style="display: inline-block;width: 150px;margin-left: 20px"><b class="sumPrice">${ ( count * goodInfo.goodsInfoPreferPrice )?string("#.##") }</b>邮豆</span></div>
    </section>
</article>
<article style="width:970px;height:80px;border-radius:2px;margin-bottom:40px;position:relative;"><!--2087fc-->
    <span style="font-size:24px;position:absolute;top:24px;left:20px;font-weight:bold;">应付金额  :</span><span style="color:#ff3300;font-size:25px;position:absolute;top:25px;right:170px;font-weight:bold;"><b class="sumPrice">${ ( count * goodInfo.goodsInfoPreferPrice )?string("#.##") }</b>邮豆</span>
    <button class="allseachButton" id="order_submit" style="width:130px;height:45px!important;position:absolute;top:18px;right:30px;border-radius:4px;color:#fff;line-height:45px!important;font-size:18px;font-weight:bold; border-style:none;" disabled="disabled">提交订单</button>
    <script type="text/javascript">
        <#if hasValet == false>
        $("#order_submit").css("disabled","disabled");
            $("#order_submit").css("background-color","#bbbbbb");
        <#else>
            $("#order_submit").css("background-color","#54a6de");
        </#if>


    </script>
</article>
<form>
        <div class="newAddress allpop">
            <h1>新增收货人信息<i id="xx"></i></h1>
        <ul>
            <li><abbr><i>*</i>收货人：</abbr><input maxlength="8" type="text" name="adName"  />
            </li>
            <li><abbr><i>*</i>所在地区：</abbr><select class="newAddressP">
                <option value="0" selected="selected">请选择省份</option>
            </select>
                <select class="newAddressC">
                    <option value="0" selected="selected">请选择城市</option>
                </select>
                <select class="newAddressD">
                    <option value="0" selected="selected">请选择区县</option>
                </select>
                <select class="newAddressR">
                    <option value="0" selected="selected">请选择街道</option>
                </select>
            </li>
            <li><abbr><i>*</i>详细地址：</abbr><input type="text" name="adAddress"  />
            </li>
            <li><abbr><i>*</i>手机号码：</abbr><input maxlength="11" type="text" name="adCell"  /><abbr>固定电话：</abbr><input maxlength="13" type="text" name="adTel"  />
            </li>
            <li><abbr>邮政编码：</abbr><input maxlength="6" type="text" name="adCell"  />
            </li>
            <li style="height: 50px;"><input id="saveAdd" type="button" value="保存收货人信息" />
            </li>
        </ul>
    </div>
</form>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<script src="${bath}/static/js/order_sure.js?version=${VERSION}"></script>
    </div>
</body>
</html>
