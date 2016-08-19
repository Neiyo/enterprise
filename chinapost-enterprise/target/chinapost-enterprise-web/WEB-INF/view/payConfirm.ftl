<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <title>支付页面</title>
    <style type="text/css">
        @media screen and ( max-width: 1360px){
            body{
                zoom:62.5%;
                font-size:10px!important;
            }

        }
        body{
            background:#f7f7f7;
        }

        .order{
            width:1000px;
            height:80px;
            background:#edf3f8;
            margin-left:20px;
            margin-top:30px;
            position:relative;
        }
        .order li.orderLeft{
            width:300px;
            height:80px;
            line-height:80px;
            position:absolute;
            left:20px;
            top:0px;
            color:#000;
            font-size:16px;
        }
        .order li.orderRight{
            width:300px;
            height:80px;
            line-height:80px;
            position:absolute;
            right:0px;
            top:0px;
            color:#000;
            font-size:16px;
        }
        .order li.orderRight span{
            color:#ff3300;
            font-size:22px;
        }

        .textVal{
            width:1000px;
            height:200px;
            margin-left:20px;
            margin-top:30px;
            position:relative;
            border: 1px solid #e5e5e5;
            padding: 10px;
        }
        .textVal ul li{
            float:left;
            width:120px;
            height:40px;
            text-align:center;
            line-height:40px;
            color:#666666;
            font-size:14px;
            cursor:pointer;
            padding: 0px 10px;
        }
        .textVal ul li:first-child{
            border-right:1px solid #e5e5e5 ;
        }
        .textVal ul li.on{
            background:#FFF;
            color: #54a6de;
        }
        .textVal ul li div.on1{
            background:#FFF;
            display:block;
            border-top: 1px solid #e5e5e5;
            color: #666;
        }

        .textVal ul li div{
            width:1000px;
            height:150px;
            position:absolute;
            left:0px;
            top:60px;
            display:none;
        }
        .textVal ul li div dt{
            float:none;
            background:#FFF;
            width:80%;
            text-align:left;
            padding:5px 0px;
            margin-left:20px;
        }
        .textVal ul li div dl{
            margin-top:20px;
        }
        #textName{
            width:220px;
            height:40px;
            border-style:none;
            border:1px solid #CCC;
            border-radius:3px;
            background:#FFF;
            padding-left:15px;
        }
        #textGet{
            width:120px;
            height:40px;
            border-style:none;
            border-radius:3px;
            background:#0099ff;
            margin-left:15px;
            color:#FFF;
            cursor:pointer;
        }
        #check{
            display:inline-block;
            margin-left:30px;
            font-size:12px;

        }
        #payCheck{
            width:120px;
            height:40px;
            border-style:none;
            border:1px solid #CCC;
            border-radius:3px;
            background:#ccc;
            color:#FFF;
            position:relative;
            left:900px;
            top:20px;
            cursor:pointer;
        }

        .success{
            width:600px;
            height:450px;
            background:#FFF;
            border:1px solid #ccc;
            position:fixed;
            left:15%;
            top:30%;
            z-index:3;
            display:none;

        }

        .success span{
            display:inline-block;
            height:40px;
            position:relative;
            left:150px;
            top:150px;
            color:#000;
            font-size:20px;
        }
        .success span img{
            margin-right:30px;
        }
        #paydetail{
            display:inline-block;
            width:100px;
            height:30px;
            line-height:35px;
            color:#FFF;
            font-size:14px;
            border-radius:3px;
            text-align:center;
            background:#2087fc;
            position:relative;
            top:300px;
            left:-120px;
            cursor:pointer;
        }
        #paycomplete{
            display:inline-block;
            width:100px;
            height:30px;
            line-height:35px;
            font-size:14px;
            color:#FFF;
            border-radius:3px;
            text-align:center;
            background:#2087fc;
            position:relative;
            top:300px;
            left:-60px;
            cursor:pointer;

        }
        .noMoney{
            text-align:center;
            width:600px;
            height:200px;
            border:1px solid #666;
            border-radius:3px;
            position:fixed;
            left:10%;
            top:35%;
            background:#FFF;
            color:#000;
            line-height:80px;
            display:none;
        }
        .noMoney span{
            font-size:18px;
        }
        .noMoney span i{
            display:inline-block;
            width:20px;
            height:20px;
            background:url(files/img/XX.png) center no-repeat;
            cursor:pointer;
            position:relative;
            left:200px;
        }
        .xcConfirm .popBox .ttBox{
            height:30px!important;
        }
    </style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        var payOrder = ${payOrder};
        var orderPrice = ${orderPrice};
        $(document).ready(function(e) {
            $(".textVal ul li").click(function(){
                $(this).addClass("on").children("div").addClass("on1");
                $(this).siblings("li").removeClass("on").children("div").removeClass("on1");
                console.log($(this).attr("value"));
                if( $(this).attr("value") ==  'msg'){
                    var textVal=$("#textName").val()
                    if(textVal!==""){
                        $("#payCheck").css({"background":"#0099ff"}).removeAttr("disabled");
                    }else{
                        $("#payCheck").css({"background":"#ccc"}).attr("disabled","disabled");
                    }
                }
                else if( $(this).attr("value") ==  'block' ){
                    $("#payCheck").css({"background":"#0099ff"}).removeAttr("disabled");
                }
            });
            $("#textName").blur(function(){
                var textVal=$("#textName").val()
                if(textVal!==""){
                    $("#payCheck").css({"background":"#0099ff"}).removeAttr("disabled");
                }else{
                    $("#payCheck").css({"background":"#ccc"}).attr("disabled","disabled");
                }
            });

        });

    </script>



</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="height: 800px;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>代客下单</span><a class="leftshanow" href="UserGet">网点提货</a><abbr></abbr>
    </div>
<div style=" width: 1120px;height: 500px;margin:20px;padding: 10px; border: 1px solid #e5e5e5">
<div class="order">
    <ul>
        <li class="orderLeft">订单编号:<span>${payOrder}</span></li>
        <li class="orderRight">应付金额：<span>${orderPrice}</span>邮豆</li>
    </ul>
</div>

<div class="textVal">
    <ul>
        <li class="on" value="msg">短信验证码验证
            <div class="on1" >
                <dl>
                    <dt>短信验证码已发送到手机号：<span>${contactPhone}</span>
                    </dt>
                    <dt><input type="text" id="textName" placeholder="短信验证码"/><input type="button" id="textGet" value="获取验证码" />
                    </dt>
                </dl>
            </div>
        </li>
        <li style="display:${judge};" class="judge" value="${judge}">免验证
            <div>
                <dl style="margin-top:50px;">
                    <dt>
                        <i><img src="${bath}/static/img/ok.png" width="40" height="40" /></i><span id="check">账号已经被授权，代客下单免验证</span>
                    </dt>
                </dl>
            </div>
        </li>
    </ul>
</div>
<input class="allseachButton" type="button" id="payCheck" value="确认支付" disabled="disabled"/>
</div>
<div class="success">
    <span><img src="${bath}/static/img/ok.png" width="40" height="40" />付款成功，订单已支付</span>
    <a class="allseachButton" id="paydetail">完成</a>
    <a  class="allcancelButton" id="paycomplete">查看订单</a>
</div>

<div class="noMoney">
    <span>系统提示<i></i></span>
    <br />
    账户邮豆余额不足，无法完成支付

</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>
<script src="${bath}/static/js/payConfirm.js?version=${VERSION}"></script>
</html>
