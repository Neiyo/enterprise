<#assign bath = request.contextPath>
<head>
    <meta charset="utf-8" />
    <title>邮豆系统_登录界面</title>
    <!-- 主要样式 -->
    <!-- 全局样式 -->
    <link rel="stylesheet" href="${bath}/static/css/g.css">
    <link rel="stylesheet" href="${bath}/static/css/xcConfirm.css">
    <link rel="stylesheet" href="${bath}/static/css/CheckSystem.css?version=${VERSION}">
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script>
<script type="text/javascript">alert("您的浏览器版本过低，有些功能无法使用，建议您使用IE9及以上版本浏览器，体验更加")</script>
    <![endif]-->
    <script src="${bath}/static/js/xcConfirm.js"></script>
    <script src="${bath}/static/js/login.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/CheckSystem.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style>
        @media screen and ( max-width: 1360px){
            body{
                zoom:62.5%;
                font-size:10px!important;
            }
        }
        input,button,select,textarea{outline:none;
            autocomplete="off";
        }
        .mainform{
            margin:auto;
            width: 560px;
            height: 380px;
            position: relative;
            top: 15%;
            border-radius: 5px;
            background:#fff;
            background-size: 100%;

        }
        ::-webkit-input-placeholder { /* WebKit browsers */
            color: #666666;
            opacity: 0.7;
        }
        :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
            color: #666666;
            opacity: 0.7;
        }
        ::-moz-placeholder { /* Mozilla Firefox 19+ */
            color: #666666;
            opacity: 0.7;
        }
        :-ms-input-placeholder { /* Internet Explorer 10+ */
            color: #666666;
            opacity: 0.7;
        }
        .mainform ul{
            width: 100%;
            height: 36px;
        }
        .mainform ul li{
            width: 280px;
            height: 50px;
            line-height: 50px;
            font-size: 24px;
            color: #999;
            font-family: 宋体;
            float: left;
            text-align: center;
            background: #f6f6f6;
            font-weight: bold;
            box-shadow: 0px -1px 3px 0px #666 inset;
            cursor: pointer;
        }

        .mainform ul li.clickOn{
            color: #54a6de;
            box-shadow:none;
            background: #ffffff;
            height: 53px;
            position: relative;
            top: -3px;
            border-radius: 3px;
        }
        .mainform div.welcomelogin{
            width: 480px;
            height: 220px;
            margin-left: 40px;
            margin-top: 30px;
        }
        .mainform div.welcomelogin dd{
            width: 480px;
            height: 48px;
            border: 1px solid #e1e1e1;
            border-radius: 6px;
            margin-bottom: 24px;
            background: url(${bath}/static/img/login_pic_input-box_bg.png);
        }

        .mainform div.welcomelogin dd img{
            margin: 10px 24px 10px 16px;
        }
        .mainform div.welcomelogin dd input{
            width: 395px;
            height: 46px;
            border-style: none;
            color: #666666;
            background: transparent;
            font-size: 20px;
        }
        #login_button{
            width: 480px;
            height: 48px;
            color: #fff;
            background: #54a6de;
            border-style: none;
            border-radius: 6px;
            font-size: 24px;
            font-family: 宋体;
        }

        .mainform div.ucoinSearch{
            width: 480px;
            height: 220px;
            margin-left: 40px;
            margin-top: 30px;
            display: none;
        }
        .mainform div.ucoinSearch dd{
            width: 480px;
            height: 48px;
            border: 1px solid #e1e1e1;
            border-radius: 6px;
            margin:80px 0px;
            background: url(${bath}/static/img/login_pic_input-box_bg.png);
            background-size: 100%;
        }
        .mainform div.ucoinSearch dd img{
            margin: 10px 24px 10px 16px;
        }
        .mainform div.ucoinSearch dd input{
            width: 360px;
            height: 46px;
            border-style: none;
            background: transparent;
            color: #666666;
            font-size: 20px;
        }
        .UbaoSearch{
            display:block;
            width: 480px;
            height: 48px;
            color: #fff;
            background: #999;
            border-style: none;
            border-radius: 6px;
            font-size: 24px;
            font-family: 宋体;
            line-height: 48px;
            text-align: center;
        }

        .ubaosearch_alert{
            float: right;
            margin-top: 15px;
            font-size:24px;
            color:#666666;
            white-space: nowrap;
        }
        .mainhead{
            width: 100%;
            height: 80px;
            position: fixed;
            top: 0;
            left: 0;
            right: 0;
            background:#1a7dc2;
        }
        .mainhead li img{
            margin-right: 24px;
            margin-top: -10px;
        }
        .mainhead li.left{
            height: 80px;
            line-height: 80px;
            position: absolute;
            left: 10%;
            font-size: 40px;
            color: #ffffff;
        }
        .mainhead li.right{
            height: 80px;
            line-height: 80px;
            position: absolute;
            right: 10%;
            font-size: 20px;
            color: #ffffff;
        }
        .mainhead li.right span{
            position: relative;
            top:13%;
            line-height: 30px;
        }
        .mainfoot{
            width:500px;
            height: 40px;
            text-align: center;
            line-height: 40px;
            position: fixed;
            bottom: 16px;
            right: 5%;
            font-size: 20px;
            color: #fff;
        }
        .backgroundbody{
            width: 1024px;
            height: 768px;
            margin: auto;
            margin-top: 50px;
            background: url(${bath}/static/img/login_pic_big_bg.png) center no-repeat;
        }
    </style>
</head>
<body style="background:#1d8bd8;">
<div class="backgroundbody">
    <div style="width:500px; height:150px; color:#fff; font-size:36px; text-align:center;margin: auto;position: relative; top:110px">欢迎使用邮豆系统</div>
<div class="mainform">
<ul>
    <li value="0" class="clickOn">欢迎登录</li>
    <li value="1">邮豆查询</li>
</ul>
    <div class="welcomelogin">
        <dl>
            <dd><img src="${bath}/static/img/login_icon_account.png">
                <input class="lg_username" type="text" placeholder="账号">
                <input class="val_username" type="hidden" readonly />
            </dd>
            <dd><img src="${bath}/static/img/login_icon_password.png">
                <input maxlength="16" class="lg_password" type="password" placeholder="密码">
                <input class="val_password" type="hidden" readonly />
            </dd>
            <dd class="form_captcha" style="width: 240px; margin-bottom: -20px;display: none;">
                <img src="${bath}/static/img/login_icon_verification.png">
                <input maxlength="5" name="capt" style="width: 150px" type="text" placeholder="验证码">
            </dd>
            <img style="position: relative; left: 280px; top: -30px;display: none;" class="captcha" src="../web/captcha"/>
        </dl>
        <div>
            <input type="hidden" value="${bath}" id="url_head" />
            <input type="button" value="登录" id="login_button"/>
        </div>
    </div>


    <div class="ucoinSearch">
        <dl>
            <dd><img src="${bath}/static/img/login_icon_id.png">
                <input maxlength="18" id="idCard" type="text" placeholder="身份证号">
                <div>
                    <div class="ubaosearch_alert"></div>
                </div>
            </dd>
        </dl>
        <div>
            <a class="UbaoSearch" href="javascript:void(0)" onclick="disabled()">查询</a>
            <script type="text/javascript">
                //禁止后退键 作用于Firefox、Opera
                document.onkeypress = forbidBackSpace;
                //禁止后退键  作用于IE、Chrome
                document.onkeydown = forbidBackSpace;
                function disabled(){
                    return 0;
                }
                function goSearch() {
                    var idCard = $("#idCard").val();
                    var a = window.open("_blank");
                    var url_head = $("#url_head").val();
                    a.location = url_head + '/web/Ubao_Search?idCard=' + idCard;
                }
            </script>
        </div>
    </div>
</div>


</div>

<div class="mainhead">
    <ul>
        <li class="left"><img src="${bath}/static/img/login_icon_youbao.png">邮豆系统</li>

        <li class="right"><img src="${bath}/static/img/login_icon_tel.png"><span style="display: inline-block;">客服电话<abbr style="font-size: 22px; display: block">400-966-1900</abbr></span></li>
    </ul>
</div>

<div class="mainfoot">
   Copyright YLife Technology —version ${VERSION}
</div>
<input type="hidden" id="loginUrl" value="login">
</body>