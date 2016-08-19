<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/formCenter.css"/>
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script src="${bath}/static/js/highcharts.js"></script>
    <script src="${bath}/static/js/exporting.js"></script>
    <script src="${bath}/static/js/jPages.js"></script>
</head>
<style type="text/css">

    .dateSpan{
        display: inline-block;
        border: 1px solid #54b3e3;
        font:15px '黑体';
        padding: 10px;
        cursor: pointer;
        color: #b2b2b2;
    }
    .dateSpan:hover{
        background-color: #54b3e3;
    }
    .table{
        display: inline-block;
        width: 40%;
        height:550px;
        padding: 10px;
        border: 1px solid #d7d7d7;
        margin-top: 20px;
        margin-left: 20px;
        vertical-align: middle;
    }
    .table p{
        font: 20px '黑体';
    }
    .table .table_svg{
        margin-top: 13px;
        display:inline-block;
        width:100%;
        height:400px;
        border-top: 1px solid #d7d7d7;
        border-bottom: 1px solid #d7d7d7;
    }
    .table dl{
        width: 100%;
        padding: 15px;
        white-space: nowrap;
    }
    .table dd{
        display:inline-block;
        padding: 15px;
        width: 28%;
        height: 60px;
        text-align: center;
    }
    .table dd:nth-child(2){
        border-left:1px solid #d7d7d7;
        border-right:1px solid #d7d7d7;
    }
</style>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle"><span>首页</span><a class="leftshanow" href="memberUbaoSendForm">会员邮豆发放统计报表</a><a class="leftshanow" href="memberUbaoReduceForm">会员邮豆消耗统计报表</a><a class="leftshanow" href="BaseDataform">网点基础数据统计报表</a><a class="leftshanow" href="ubaoSendForm">邮豆发放记录表</a><abbr></abbr>
    </div>
    <div style="margin-left: 20px;margin-top: 15px;">
        <span style="font:20px '黑体';">统计期间 :</span>
        <span class="dateSpan">最近一周</span>
        <span class="dateSpan">最近一个月</span>
    </div>
    <div style="padding-left:20px;">
        <div class="table">
            <p>邮豆发放金额</p>
            <div id="chartFirst" class="table_svg"></div>
            <dl>
                <dd>
                    <p>期间发放邮豆</p>
                    <p class="duringUcoin">165465.05</p>
                </dd>
                <dd>
                    <p>今年发放总邮豆</p>
                    <p class="allUcoin">984.25</p>
                </dd>
                <dd>
                    <p>今日发放邮豆</p>
                    <p class="todayUcoin">242.00</p>
                </dd>
            </dl>
        </div>
        <div class="table">
            <p>邮豆发放对比</p>
            <div id="chartSecond" class="table_svg"></div>
        </div>
        <div class="table">
            <p>邮豆发放笔数</p>
            <div id="chartThird" class="table_svg"></div>
            <dl>
                <dd>
                    <p>期间发放笔数</p>
                    <p class="duringCount">165465.05</p>
                </dd>
                <dd>
                    <p>今年发放总笔数</p>
                    <p class="allCount">984.25</p>
                </dd>
                <dd>
                    <p>今日发放笔数</p>
                    <p class="todayCount">242.00</p>
                </dd>
            </dl>
        </div>
        <div class="table">
            <p>新增会员数</p>
            <div id="chartForth" class="table_svg"></div>
            <dl>
                <dd>
                    <p>期间新增会员</p>
                    <p class="duringAddCustomer">165465.05</p>
                </dd>
                <dd>
                    <p>今年新增会员</p>
                    <p class="allAddCustomer">984.25</p>
                </dd>
                <dd>
                    <p>今日新增会员</p>
                    <p class="todayAddCustomer">242.00</p>
                </dd>
            </dl>
        </div>
    </div>










<script src="${bath}/static/js/formCenter.js?version=${VERSION}"></script>
<script type="text/javascript">
    $(document).ready(function(){
       $($(".dateSpan")[0]).trigger("click");
    });
</script>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>