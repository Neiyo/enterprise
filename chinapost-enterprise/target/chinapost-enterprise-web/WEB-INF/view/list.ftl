<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>邮豆系统_登录界面</title>
    <!-- 主要样式 -->
    <link rel="stylesheet" href="${bath}/static/css/style.css">
    <!-- 全局样式 -->
    <link rel="stylesheet" href="${bath}/static/css/g.css?version=${VERSION}">

    <link rel="stylesheet" href="${bath}/static/css/xcConfirm.css">
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap.min.css">

    <!-- 可选的Bootstrap主题文件（一般不用引入） -->
    <link rel="stylesheet" href="${bath}/static/bootstrap/css/bootstrap-theme.min.css">

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>

    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${bath}/static/bootstrap/js/bootstrap.min.js"></script>

    <!-- jPage分页 -->
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
</head>
<style>
    @media screen and ( max-width: 1360px){
        body{
            zoom:62.5%;
            font-size:10px!important;
        }

    }
    .showError{
        color:#ff3300!important;
        position:relative;
    }
    #autoWarning{
        width: auto;
        height: auto;
        z-index: 2;
        position: fixed;
        display: none;
    }
    #arrow{
        width: 16px;
        height: 16px;
        z-index: 3;
        position: relative;
        left: 20px;
        top: -2.5px;
        background:url(${bath}/static/img/warningArrow.png) center no-repeat;
    }
    #massageShow{
        padding: 10px;
        border: 2px solid #666;
        z-index: 2;
        background: #fff;
    }
    .importLog{
        width:680px;
        height:680px;
        position:fixed;
        left:15%;
        top:15%;
        background:#FFF;
        box-shadow: 5px 5px 5px 5px #999;
        z-index:99;
        display:block;
    }
    .importLog ul{
        width: 650px;
        margin-left: 15px;
        border-top:1px solid #e0e0e0;
        padding-top:50px;
        overflow-x: hidden;
        overflow-y: scroll;
    }
    .importLog li{
        height:40px;
        margin-top: 14px;
    }
    .importLog li div{
        font-size: 14px;
        text-align:left;
        margin-left: 80px;
    }
    #xx{
        display:inline-block;
        background:url(${bath}/static/img/XX.png) center no-repeat;
        width:25px;
        height:25px;
        cursor:pointer;
    }
    .leftshanow{
        width: 100%;
        height: 100%;
        z-index: 1;
        color:#54b3e3!important;
        background-color: #fff;
        border:none!important;
        box-shadow: none!important;
        margin-left: 20px;
        font:  20px '黑体';
    }
</style

<body style="">
<div style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;height:1000px;width: 100%;overflow-x: scroll;">
    <div style="width:100%; height:70px;line-height:70px;color:#333333;font-size:16px;">
        <#--<a style="box-shadow:-3px -2px 3px 0px #dedede inset;font:20px '黑体'; background: #f2f2f2; color:#b2b2b2; display:inline-block; width: 10%;line-height: 70px;text-indent: 30px;" href="UbaoSend">单个转账</a>-->

        <#--<span style="font:20px '黑体';text-indent: 30px; color:#2c97de; display:inline-block; line-height: 70px; ">批量转账</span>-->
        <a class="leftshanow" href="piliangdaoru" style="line-height: 70px;">批量导入</a>
    </div>




<div class="containe">
    <button class="re_import allclickButton">重新导入</button>
    <div id="notice">通过Excel导入，实现批量发放。具体发放到用户身份证号对应的账户中，会员来网点激活账户，使用邮豆消费。一次最多只能导入一千条</div>

    <div class="row">
        <div style="text-align:left;font-size:20px;margin-top:2%;font-weight:bold;margin-left:40px;"><abbr id="listTitle"></abbr>发放列表(<span id="num"></span>)</div>
    </div>
</div>
<hr style="width:98%;border-bottom:2px solid #dcdcdc;margin-left:22px;margin-top:40px;"/>
<input type="hidden" id="typeId" value="${typeId}" />
<table class="table_list" style="width:98%;margin-left:22px;font-size:15px;font-weight:550;border:0;">
    <thead>
    <tr style="background-color:#d6d6d6;" class="thead">
        <script type="text/javascript">
            //禁止后退键 作用于Firefox、Opera
            document.onkeypress = forbidBackSpace;
            //禁止后退键  作用于IE、Chrome
            document.onkeydown = forbidBackSpace;
            var data = ${data};
            var business_type = ${business_type};
//            含业务类型
//            data.data.excel[1][0].map(function(object){
//                //填充数据
//                var html = '<th>' + object + '</th>';
//                $('.thead').append(html);
//            });
//            var title = data.data.excel[3][0][0];
//            $("#listTitle").html(title + '  ');

//          不含业务类型
            data.data.excel.content[0].map(function(object){
                //填充数据
                var html = '<th>' + object + '</th>';
                $('.thead').append(html);
            });

        </script>
    </tr>
    </thead>
    <tbody id="itemContainer">
        <script type="text/javascript">
            $(document).ready(function(){
                //移动窗口

                $( ".importLog" ).draggable();
                $("#xx").click(function(){
                   $(".importLog").css("display","none");
                });
                //填充信息
//                含业务类型
//                for(var i = 1;i < data.data.excel[1].length;i++){
//                    var html = '<tr>';
//                    data.data.excel[1][i].map(function(object){
//                        //填充数据
//                        html += '<td class="">' + object + '</td>';
//                    });
//                    html += '</tr>';
//                    $('#itemContainer').append(html);
//                }
//                //定位并加入错误信息
//                var trCount = 0;
//                var errLogHtml = '';
//                data.data.excel[2].map(function(arr){
//                    if(arr.length != 0){
//                        for( var i = 0;i < arr.length;i++ ){
//                            var args = arr[i].split("^_^");
//                            var index = args[0];
//                            var errorContent = args[1];
//                            $($($("tbody tr")[trCount]).children()[index]).addClass("showError");
//                            $($($("tbody tr")[trCount]).children()[index]).attr("value",errorContent);
//
//                            //显示错误日志
//                            errLogHtml += '<li>';
//                            errLogHtml += '<div class="errorContent">' + '第' + ( parseInt(trCount) + 1 ) + '行错误:' + errorContent + '</div>';
//                            errLogHtml += '</li>';
//                        }
//                    }
//                    trCount++;
//                });



//              不含业务类型
                for(var i = 1;i < data.data.excel.content.length;i++){
                    var html = '<tr>';
                    data.data.excel.content[i].map(function(object){
                        //填充数据
                        html += '<td class="">' + object + '</td>';
                    });
                    html += '</tr>';
                    $('#itemContainer').append(html);
                }
                //定位并加入错误信息
                var trCount = 0;
                var errLogHtml = '';
                data.data.excel.error.map(function(arr){
                    if(arr.length != 0){
                        for( var i = 0;i < arr.length;i++ ){
                            var args = arr[i].split("^_^");
                            var index = handleAZ(args[1]);
                            var errorContent = args[1] + args[2];
                            $($($("tbody tr")[trCount]).children()[index-1]).addClass("showError");
                            $($($("tbody tr")[trCount]).children()[index-1]).attr("value",errorContent);

                            //显示错误日志
                            errLogHtml += '<li>';
                            errLogHtml += '<div class="errorContent">' + '第' + ( parseInt(trCount) + 1 ) + '行错误:' + errorContent + '</div>';
                            errLogHtml += '</li>';
                        }
                    }
                    trCount++;
                });



                if( errLogHtml == '' ){
                    errLogHtml = '导入正确';
                }
                $(".importLog ul").append(errLogHtml);

                //列表总数
                var num="0";
                $("#itemContainer tr").each(function(){
                    num = parseInt(num) + parseInt($(this).length);
                })
                $("#num").html(num);
                $(".showError").each(function(){
                    if($(this).html()== ""){
                        $(this).html("- -")
                    }
                });
                //绘制canvas
                $(".showError").mouseover(function(){
                    $("#autoWarning").show()
                    var left = window.event.clientX
                    var top = window.event.clientY
//                    var width = $(this).css("width");
//                    var height = $(this).css("height");
//                    var html = '<canvas id="myCanvas" style="z-index: 999;position: absolute;top:-20px;left: ' + width + ';"></canvas>';
//                    $(this).append(html);
//                    var canvas = document.getElementById("myCanvas");
                    var text = $(this).attr("value");
                    $("#massageShow").html(text);
                    $("#autoWarning").css({left:left,top:top-50});
//                    if(canvas.getContext){
//                        //获取对应的CanvasRenderingContext2D对象(画笔)
//                        var ctx = canvas.getContext("2d");
//                        //设置字体样式
//                        ctx.font = "22px Courier New";
//                        //设置字体填充颜色
//                        ctx.fillStyle = "#ff3300";
//                        //从坐标点(50,50)开始绘制文字
//                        ctx.fillText(text, 10, 10);
//                    }
                });
                $(".showError").mouseout(function(){
                    $("#autoWarning").hide()
                   $("#myCanvas").remove();
                });
            });

        </script>
    </tbody>
</table>
  <span class="footer allcpageTurnButton" style="margin-top:60px;">
  </span>
  <span class="buttonGroup" style="display:inline-block;">
    <button class="ensure allseachButton" id="ensure">确定转账</button>
    <button class="cancel allcancelButton" id="cancel">取消</button>
  </span>
<div class="pwd allpop">
    <h1>输入支付密码<i style="width: 25px; height: 25px; background:url(${bath}/static/img/XX.png) center no-repeat;"></i></h1>
    <input type="password" name="pwd" placeholder="支付密码" id="list_password"/><br />
    <span style="line-height: 36px;" class="allseachButton" id="confirm">确认支付</span>
</div>

<div class="success">
    <span style="left:110px;" ><img src="${bath}/static/img/ok.png" width="40" height="40" />支付成功，<b>3</b>秒后返回邮豆发放页面</span>
    <a id="detail" class="allcancelButton">查看发放详情</a>
    <a id="complete" class="allseachButton">完成</a>
</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<div id="autoWarning">
    <div id="massageShow"></div>
    <div id="arrow"></div>
</div>
<div class="importLog allpop">
    <h1>导入错误信息汇总<i onclick="discoverHtml()" id="xx"></i></h1>
    <ul style="height:90%;"></ul>
</div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function(){
        $("#cancel").click(function(){
            window.location.href = 'piliangdaoru';
        })
    })
</script>
<script src="${bath}/static/js/list.js?version=${VERSION}"></script>