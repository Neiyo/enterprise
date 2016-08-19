<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script src="${bath}/static/js/jPages.js"></script>
    <script src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <script src="${bath}/static/js/zrj_ajaxPages.js?version=${VERSION}"></script>
    <style type="text/css">
        @media screen and ( max-width: 1360px) {
            body {
                zoom: 62.5%;
                font-size: 10px !important;
            }
        }
        .head{
            width:100%;
            height:140px;
            background:#fcfcfc;
        }
        .head ul{
            margin-top:30px;
        }

        .head ul li.left{
            width:190px;
            height:70px;
            margin-left:140px;
            float:left;
        }
        .head ul li.left img{
            width:190px;
            height:100px;
        }
        .head ul li.right{
            width:1270px;
            height:70px;
            margin-left:440px;
        }
        #itemText{
            width:390px;
            height:40px;
            border:1px solid #ccc;

        }
        #itemSearch{
            width:80px;
            height:40px;
            border-style:none;
            border-radius:3px;
            color:#fff;
            background:#dd2c00;

        }
        .head ul li.right a{
            display:inline-block;
            vertical-align:middle;
            margin-right:20px;
            margin-top:20px;
        }
        .head ul li.right i{
            display:inline-block;
            vertical-align:middle;
            margin-top:20px;
        }
        .head ul li.right input{
            width:80px;
            height:25px;
            margin:20px 10px 0px 10px;
            border-radius: 3px;
        }
        .head ul li.right abbr{
            display:block;
        }
        #itemSearch2{
            width:40px;
            height:25px;
            color:#fff;
            border-style:none;
            background:#dd2c00;
            border-radius:3px;

        }
        .itemTable{
            width:100%;
            height:auto;
            overflow:hidden;
            background:#fcfcfc;
        }
        .itemTable ul{
            margin-left:120px;
            width:1350px;
        }
        .itemTable ul li{
            width:235px;
            height:410px;
            border: 1px solid #e5e5e5;
            background:#fff;
            float:left;
            margin-bottom:20px;
            margin-right:20px;
        }
        .itemTable ul li abbr:first-child{

            display:block;
            width:190px;
            height:190px;
            margin-top:20px;
            margin-left:20px;

        }
        .itemTable ul li abbr:first-child img{
            width:190px;
            height:190px;
        }
        .itemTable ul li abbr:nth-child(2){
            display:inline-block;
            width:210px;
            height:60px;
            border-top:1px solid #ccc;
            margin-left:10px;
            margin-top:20px;
            overflow:hidden;
        }
        .itemTable ul li abbr:nth-child(2) s{
            display:inline-block;
            width:180px;
            height:60px;
            overflow:hidden;
        }
        .itemTable ul li abbr:nth-child(2) i.changeLeft{
            display:inline-block;
            vertical-align:middle;
            width:15px;
            line-height:60px;
            height:60px;
            float:left;
            background:url(${bath}/static/img/item_left.gif) center no-repeat;
        }
        .itemTable ul li abbr:nth-child(2) i.changeRight{
            display:inline-block;
            vertical-align:middle;
            width:15px;
            line-height:60px;
            height:60px;
            float:right;
            background:url(${bath}/static/img/item_right.gif) center no-repeat;
        }
        .itemTable ul li abbr:nth-child(2) span{
            display:inline-block;
            height:60px;
            overflow:hidden;
            position:relative;
            left:0;
            width:9999px;
        }
        .itemTable ul li abbr:nth-child(2) img{
            width:30px;
            height:30px;
            margin:15px 5px;
            border:1px solid transparent;

        }
        .itemTable ul li abbr:nth-child(2) img:hover{
            border:1px solid #ff3300;
        }
        .itemTable ul li abbr:nth-child(3){
            display:block;
            width:190px;
            height:30px;
            margin-left:20px;
            font-size:16px;

        }
        .itemTable ul li abbr:nth-child(3) span{
            color:#ff3300;
            font-size:22px;
            margin-right:10px;
            font-weight:bold;
        }
        .itemTable ul li abbr:nth-child(4){
            display:block;
            width:190px;
            height:30px;
            margin-left:20px;
            white-space: nowrap;
            overflow: hidden;
            font-size:12px;

        }
        .itemTable ul li abbr:nth-child(5){
            display:block;
            width:190px;
            height:30px;
            margin-left:20px;
            margin-top:10px;
            font-size:12px;

        }
        .itemTable ul li abbr:nth-child(5) input{
            color:#FFF;
            background:#ff4400;
            border-style:none;
            border-radius:3px;
            width:140px;
            height:30px;
            margin-left:15px;
        }
       #holder{
           margin: 20px;
       }
    </style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        $(document).ready(function(e) {
            $(document).on("mouseover",".imglist span img",function(){
                var src=$(this).next().attr("value");
                $(this).parent().parent().parent().siblings(".showImg").children("img").prop("src",src);
            });

            $(document).on("click",".changeLeft",function(){
                var left=parseInt($(this).siblings("s").children("span").css("left"));
                if(left<0){
                    $(this).siblings("s").children("span").animate({"left":left+45},100);
                }
            });

            $(document).on("click",".changeRight",function(){
                var left=parseInt($(this).siblings("s").children("span").css("left"));
                var imgNum=$(this).siblings("s").children("span").children("img").length
                console.log(imgNum)
                if(left>0-(imgNum-3)*45){
                    $(this).siblings("s").children("span").animate({"left":left-45},100);
                }
            });


            $(document).on("click",".itemTable abbr.showImg img",function(){
                var itemId = $(this).prev().val();
                console.log($(this).prev());
                window.location.href = "itemListDetail?id=" + itemId;
            });
            $(document).on("click",".buy",function(){
                var itemId = $(this).parent().siblings(".showImg").children("input").val();
                window.location.href = "itemListDetail?id=" + itemId;
            });

        });


    </script>

    <title>无标题文档</title>
</head>
<body style="background: #edf3f8">
<div class="allOutShow" style="height: auto;background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle">
        <span>代客下单</span><a class="leftshanow" href="UserGet">网点提货</a><abbr></abbr>
    </div>

<div class="head">
    <ul>
        <li class="left">

            <img src="${bath}/static/img/youdoulogo.png" width="100" height="99" />
        </li>
        <li class="right">
        	<span>
            	<input id="itemText" placeholder="请输入商品名" class="allinputButton" type="search" /><input class="allseachButton" type="button" value="搜索" id="itemSearch" />
            </span>
        <#if isTop=="true" || isEnd == "true">
            <input checked="checked" style="margin-top: 20px" type="checkbox" name="selfItemList"><span style="position: relative; top: 10px">本网点商品</span>
        </#if>
            <abbr>
                <a>按价格搜索:</a><a class="a_href">0-1250</a><a class="a_href">1250-3750</a><a class="a_href">3750-100000</a><a class="a_href">100000-1875000</a><a class="a_href">1875000以上</a>

                <input type="text" id="input_low"/><i>-</i><input type="text" id="input_high"/> <input class="allseachButton" id="itemSearch2" type="button" value="确定" />
            </abbr>


            </span>
        </li>
    </ul>
</div>


<div class="itemTable">
    <ul>
     <div id="itemContainer"></div>
    </ul>


</div>
<div id="holder" class="allcpageTurnButton"></div>
<script src="${bath}/static/js/itemList.js?version=${VERSION}"></script>
</div>
</body>
</html>
