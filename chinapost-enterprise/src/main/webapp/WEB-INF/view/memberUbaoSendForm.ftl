<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/jquery.datetimepicker.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <link rel="stylesheet" href="${bath}/static/css/jPages.css" />
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js"></script>
</head>
<style type="text/css">
    @media screen and ( max-width: 1360px){
        body{
            zoom:62.5%;
            font-size:10px!important;
        }
    }
    .accountSearch{
        width:1800px;
        height:130px;
        margin-left:20px;
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
        height:42px;
        border:1px solid #ccc;
        background:#FFF;
    }
    .date_button{
        display: inline-block;
        vertical-align: middle;
        width:24px!important;
        height:24px;
        background-color: #f2f2f2;
        margin-left: -45px;
        background: url("${bath}/static/img/date_img.png") no-repeat center;

    }
    .table_list{
        width: 1600px;
        margin-left: 20px;
    }
    .table_list span.checkDetail{
        color: #54a6de;
        cursor: pointer;
    }
    .table_list th, .table_list td{
        height: 40px;
        border-bottom: 1px solid #e5e5e5;
    }
    #holder{
        margin: 30px 0px;
    }
    #holder2{
        margin: 30px 0px;
        margin-left: 100px;
    }
    #userDetailList{
        font-size: 12px;
        width:1200px;
        position:fixed;
        left:15%;
        top:15%;
        z-index:2;
        background:#FFF;
        display: none;
    }
    #userDetailList h1 i{
        display:inline-block;
        width:25px;
        height:25px;
        background:url(${bath}/static/img/XX.png) center no-repeat;
    }
    .table_listDetail{
        margin-left: 100px;
        margin-bottom: 20px;
        width: 1000px;
    }
    .table_listDetail th, .table_listDetail td{
        height: 40px;
        border-bottom: 1px solid #e5e5e5;
    }
</style>
<script type="text/javascript">
    $(document).ready(function(){
        var eid = ${enterprise.enterpriseId};
        var ename = "${enterprise.enterpriseName}";
        inventoryList(eid,ename);


    })
</script>
<body style="background: #edf3f8">
<div class="allOutShow" style="height: 1000px;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;white-space: nowrap;width: 100%;overflow-x: scroll;">
    <div  class="allheadstyle"><a href="formCenter">首页</a><span>会员邮豆发放统计报表</span><a class="leftshanow" href="memberUbaoReduceForm">会员邮豆消耗统计报表</a><a class="leftshanow" href="BaseDataform">网点基础数据统计报表</a><a class="leftshanow" href="ubaoSendForm">邮豆发放记录表</a><abbr></abbr>
    </div>
    <input type="hidden" id="newDatetimepicker_start"/>
    <input type="hidden" id="newDatetimepicker_end"/>
    <div class="accountSearch">
        <ul>
            <li class="checkplace" style="width: 350px;"><input class="allinputButton" placeholder="查看范围" value="${enterprise.enterpriseName}" style="width: 300px" readonly="readonly" type="text" id="Dopet" data-id="null"/><input value="${enterprise.enterpriseId}" type="hidden" class="enterpriseIdChoosen"> <input type="hidden" id="NewEnterpriseIdChoosen"  value="${enterprise.enterpriseId}"><abbr id="choose"  style="background:url(${bath}/static/img/chooseinout.png) center no-repeat; color:#fff;display:inline-block;position: relative; left: -50px; top: -1px; vertical-align:middle; width: 28px; height:28px; line-height:30px; text-align:center;cursor: pointer;"></abbr></li>
            <li><input class="allinputButton" placeholder="发放开始日期"   type="text" style="width:250px;" id="datetimepicker_start"/><a  id="date_start" href="#"class="date_button"/></a></li>
            <li><input class="allinputButton" placeholder="发放结束日期"   type="text" style="width:250px;" id="datetimepicker_end"/><a  id="date_end" href="#"class="date_button"/></a></li>
            <li style="width: 250px"><input style="width: 250px" class="allinputButton" placeholder="身份证号"   type="text" name="accountUserName" /></li>
            <li style="width:500px; margin-left: 20px"><input class="allseachButton" type="button" value="搜索" id="accSearch" /><input style="margin-left: 20px" class="allclickButton" type="button" id="accExport" value="导出表格" /></li>
        </ul>
    </div>

<div style="width: 1600px">
    <table class="table_list" cellpadding="0" cellspacing="0" align="center">
        <thead>
        <tr>
            <th>身份证号</th>
            <th>姓名</th>
            <th>邮豆发放笔数</th>
            <th>营销邮豆金额</th>
            <th>促销邮豆金额</th>
            <th>发放总金额</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody align="center" id="itemContainer">

        </tbody>
    </table>
</div>
    <div id="holder" class="allcpageTurnButton"></div>


<div class="allpop" id="userDetailList">
    <h1>会员邮豆发放明细<i></i></h1>
    <div style="margin-left: 20px; margin-top: 20px"><input type="button" id="outExcel" value="导出表格" class="allclickButton"> </div>
    <table class="table_listDetail" cellpadding="0" cellspacing="0">
        <thead>
        <tr>
            <th>身份证号</th>
            <th>姓名</th>
            <th>发放时间</th>
            <th>发放网点</th>
            <th>营销邮豆金额</th>
            <th>促销邮豆金额</th>
            <th>总金额</th>

        </tr>
        </thead>
        <tbody align="center" id="itemContainer2">

        </tbody>
    </table>
    <div id="holder2" class="allcpageTurnButton"></div>
</div>

    <script src="${bath}/static/js/jquery.datetimepicker.full.js"></script>
    <script src="${bath}/static/js/formCenter.js?version=${VERSION}"></script>
    <div class="chooeseDepot allpop"></div>
    <div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</div>
<script type="text/javascript">
    var array = [];
    array[15] = '';//idCard
    array[8] = '';//start
    array[9] = '';//end
    array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
//    var todayDate = new Date();//获取今天日期
//    var lastDate = new Date( todayDate.getTime() - 3600*24*1000*7 );
//    array[8] = lastDate.Format("yyyy-MM-dd hh:mm");
//    array[9] = todayDate.Format("yyyy-MM-dd hh:mm");
    var excel_idCard = '';

    $("#newDatetimepicker_start").val(array[8]);
    $("#newDatetimepicker_end").val(array[9]);

    $(document).on("click",".checkDetail",function(){
        coverHtml()
        var enterpriseId = $("#NewEnterpriseIdChoosen").val();
        var start =  $("#newDatetimepicker_start").val();
        var end =  $("#newDatetimepicker_end").val();
        var idCard = $(this).parent().siblings(".idCard").html();
        excel_idCard = idCard;
        array[15] = idCard;
        array[8] = start;
        array[9] = end;
        array[16] =enterpriseId
        ajaxPages("../web/api/report/getDetailUcoinGrand","itemContainer2","holder2","memberUbaoSendFormDetail",10,'','',array);

        $("#userDetailList").fadeIn(500);
    });

    $(document).ready(function(){
        var lock = false
        var isEnd=${isEnd}
        if(isEnd){
            $(".checkplace").hide()
        }
        $("#accSearch").click(function(){
            if(   $("#datetimepicker_end").val() >= $("#datetimepicker_start").val() ){
                lock=true
                array[15] = $("input[name='accountUserName']").val();//idCard
                array[8] =$("#datetimepicker_start").val().replace("/","-").replace("/","-");//start
                array[9] =$("#datetimepicker_end").val().replace("/","-").replace("/","-");//end
                array[16] = $(".enterpriseIdChoosen").val();//enterpriseId
                $("#NewEnterpriseIdChoosen").val( array[16]);
                $("#newDatetimepicker_start").val(array[8]);
                $("#newDatetimepicker_end").val(array[9]);
                ajaxPages("../web/api/report/getUcoinGranForm","itemContainer","holder","memberUbaoSendForm",10,'','',array);
            }else{
                data_type_alert("结束时间必须大于开始时间","error")
            }

        });

        $("#accExport").click(function(){
            if(lock){
            var outputStr = '';
            if( array[16] != undefined ){
                outputStr += 'enterpriseId=' + array[16];
            }
            if( array[15] != undefined ){
                outputStr += '&idCard=' + array[15];
            }
            if( array[8] != undefined ){
                outputStr += '&start=' + array[8];
            }
            if( array[9] != undefined ){
                outputStr += '&end=' + array[9];
            }
            if( array[8] == undefined || array[8] == '' || array[9] == undefined || array[9] == ''){
                response_ensure_alert("warning","搜索时间不能为空");
            }
            else{
                window.location.href = '../web/api/exportExcel/ucoinGrandFormDown?' + outputStr;
            }
            }else{
                data_type_alert("请先搜索后再导出","error")
            }
        });

        $("#outExcel").click(function(){
            var outputStr = '';
            if( array[16] != undefined ){
                outputStr += 'enterpriseId=' + array[16];
            }
            if( excel_idCard != undefined ){
                outputStr += '&idCard=' + excel_idCard;
            }
            if( array[8] != undefined ){
                outputStr += '&start=' + array[8];
            }
            if( array[9] != undefined ){
                outputStr += '&end=' + array[9];
            }
            window.location.href = '../web/api/exportExcel/ucoinGrandDetailDown?' + outputStr;
        });
    });
</script>
</body>