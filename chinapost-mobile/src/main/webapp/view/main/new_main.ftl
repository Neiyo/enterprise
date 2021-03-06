<#assign basePath=request.contextPath>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="keywords" content="${(seo.meteKey)!''}">
    <meta name="description" content="${(seo.meteDes)!''}">
<#if (sys.bsetName)??>
    <title>${sys.bsetName}</title>
    <input type="hidden" id="bsetName" value="${(sys.bsetName)!''}">
    <input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
<#else>
    <title>${(seo.mete)!''}</title>
    <input type="hidden" id="bsetName" value="${(seo.mete)!''}">
    <input type="hidden" id="bsetDesc" value="${(sys.bsetDesc)!''}">
</#if>
    <input type="hidden" id="basePath" value="${(basePath)!''}">

    <link href="${basePath}/mobile_home_page/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${basePath}/mobile_home_page/css/style.css" rel="stylesheet"/>
    <!-- Bootstrap -->
    <link href="${basePath}/mobile_home_page/css/bootstrap.min.css" rel="stylesheet">
    <link href="${basePath}/static/css/idangerous.swiper.css" rel="stylesheet">

    <link href="${basePath}/static/css/style.min.css?v=201601091628" rel="stylesheet">
    <link href="${basePath}/static/css/ui-dialog.css" rel="stylesheet">
    <link rel="stylesheet" href="${basePath}/static/css/ui-dialog.css"/>
    <link rel="stylesheet" href="${basePath}/static/css/index.min.css"/>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    <script src="${basePath}/static/js/jquery-1.10.1.js"></script>
    <script src="${basePath}/static/js/idangerous.swiper.js"></script>
    <script src="${basePath}/static/js/dialog-min.js"></script>
    <![endif]-->
    <style>
        <#if (mobSiteBasic.isShowBuffer)?? && (mobSiteBasic.isShowBuffer=='1')>
        .curtain_wp {
            background: url(${(mobSiteBasic.backgroudImage)!''}) ${(mobSiteBasic.backgroudColor)!''} no-repeat center 30%;
            background-size: 50% auto;
        }
        <#else>
        .curtain_wp {
            background: url(images/curtain_word.png) #2589c9 no-repeat center 30%;
            background-size: 50% auto;
        }
        </#if>
        .curtain_wp p {
            position: absolute;
            width: 100%;
            left: 0;
            bottom: 10%;
            text-align: center;
            color: #fff;
            font-family: Arial;
            font-size: 16px;
        }
    </style>
    <script type="text/javascript">
        //basePath值为''时，重新静态化
        //if($("#basePath").val()==""){
        //	location = "toStaticizeIndex.htm";
        //}

        function browserRedirect() {
            var sUserAgent = navigator.userAgent.toLowerCase();
            var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
            var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";
            var bIsMidp = sUserAgent.match(/midp/i) == "midp";
            var bIsUc7 = sUserAgent.match(/rv:1.2.3.4/i) == "rv:1.2.3.4";
            var bIsUc = sUserAgent.match(/ucweb/i) == "ucweb";
            var bIsAndroid = sUserAgent.match(/android/i) == "android";
            var bIsCE = sUserAgent.match(/windows ce/i) == "windows ce";
            var bIsWM = sUserAgent.match(/windows mobile/i) == "windows mobile";
            if (bIsIpad) {
            <#if siteProjectUrl?? && (siteProjectUrl?length>0)>
                location = "${(siteProjectUrl)!''}";
            </#if>
            }
            if (!(bIsIphoneOs || bIsMidp || bIsUc7 || bIsUc || bIsAndroid || bIsCE || bIsWM)) {
                //跳转到pc版
            <#if siteProjectUrl?? && (siteProjectUrl?length>0)>
                location = "${(siteProjectUrl)!''}";
            </#if>
            }
        }
        //根据设备跳转
        //browserRedirect();
        //显示主体
        function showmain() {
            $(".ps-head").removeAttr("style");
            $("#main").fadeIn(function () {
                //设置全屏轮播
                var fullRolls = $(".fullRoll");
                if (fullRolls.length > 0) {
                    $(".more_goods").hide();
                    $(".foot").hide();
                    $(".smart_menu").hide();

                    var item = $(".app_item");
                    for (var i = 0; i < item.length; i++) {
                        var n = $(item[i]).find(".fullRoll").length;
                        if (!n) {
                            $(item[i]).hide();
                        }
                    }

                    for (var i = 0; i < fullRolls.length; i++) {
                        var sd = $(fullRolls[i]).find(".fullRollSD").val();
                        if (sd == "sh") {
                            mySwiper = new Swiper('.full-swiper-container', {
                                mode: 'horizontal',
                                loop: true,
                                pagination: '.full-pagination',
                                paginationClickable: true,
                            });
                            $(fullRolls[i]).find(".full-pagination").show();
                            $(fullRolls[i]).find(".vtc-img").hide();
                        } else {
                            mySwiper = new Swiper('.full-swiper-container', {
                                mode: 'vertical',
                                loop: true,
                                pagination: '.full-pagination',
                                paginationClickable: true,
                            });
                            $(fullRolls[i]).find(".full-pagination").hide();
                            $(fullRolls[i]).find(".vtc-img").show().delay(3000).fadeOut(500);
                        }
                        ;

                        //控制音乐播放
                        $(".bgd_music a").click(function () {
                            var obj = $(this),
                                    audio = document.getElementById('bg_music');
                            if (audio.paused) {
                                audio.play();
                                obj.html('暂停');
                                obj.removeClass("music_pause");
                                return;
                            }
                            audio.pause();
                            obj.html('播放');
                            obj.addClass('music_pause');
                        });
                    }
                }
                //轮播
                var rolls = $(".roll_adv");
                for (var i = 0; i < rolls.length; i++) {
                    var rollId = "#" + $(rolls[i]).prop("id");
                    $(rollId + ' .roll_banner,' + rollId + ' .roll_banner img').css('height', parseInt(($(window).width() * 35) / 72) + 'px');
                    var mySwiper = new Swiper(rollId + ' .swiper-container', {
                        pagination: '.swiper-pagination',
                        loop: true,
                        grabCursor: true,
                        autoplay: 3000
                    });
                }
                //文字
                var texts = $(".text_app");
                for (var i = 0; i < texts.length; i++) {
                    var text = $(texts[i]);
                    var textid = text.prop("id");
                    var textCont = $("#textCont" + textid.substring(8)).text();
                    text.html(textCont);
                }
            });
        }


        //站点设置信息不显示缓冲页
        <#if (mobSiteBasic.isShowBuffer)?? && (mobSiteBasic.isShowBuffer=='0')>
        //不显示缓冲页
        $(function () {
            showmain();
        });
        <#else>
        //没有站点设置，或显示缓冲页
        $(function () {
//            if (sessionStorage.isFirst == undefined) {
//                sessionStorage.isFirst = false;
//                $(".curtain_wp").width($(window).width()).height($(window).height()).delay(3000).fadeOut(500, function () {
//                    showmain();
//                });
//            }
//            else {
                showmain();
//            }
        });
        </#if>


    </script>
</head>
<body>
<#--新加头部－加搜索-->
<#--<style>-->
    <#--.ps-head {-->
        <#--padding: 0.5em;-->
        <#--background: url(http://st.360buyimg.com/m/images/index/header-bg.png?v=2) repeat-x #efefef;-->
        <#--background-size: 100% 44px;-->
    <#--}-->

    <#--.ps-back {-->
        <#--width: 15%;-->
        <#--float: left;-->
        <#--text-align: center;-->
    <#--}-->

    <#--.ps-back a {-->
        <#--display: block;-->
        <#--background: url(images/arrow_left.png) no-repeat center center;-->
        <#--width: 100%;-->
        <#--background-size: auto 50%;-->
        <#--height: 2.8em;-->
        <#--text-indent: -99999px;-->
    <#--}-->

    <#--.ps-search {-->
        <#--border: 1px solid #d6d6d6;-->
        <#--border-radius: 4px;-->
        <#--height: 2.3em;-->
        <#--margin-top: 0.2em;-->
        <#--width: 100%;-->
    <#--}-->

    <#--.ps-search input {-->
        <#--width: 85%;-->
        <#--height: 2.1em;-->
        <#--background: #fff;-->
        <#--border: none;-->
        <#--margin-top: 0;-->
        <#--float: left;-->
        <#--padding-left: 0.5em-->
    <#--}-->

    <#--.ps-search button {-->
        <#--float: right;-->
        <#--width: 15%;-->
        <#--background: url(${basePath}/images/search.png) no-repeat center center;-->
        <#--text-indent: 1.8em;-->
        <#--background-size: auto 50%;-->
        <#--border: none;-->
        <#--cursor: pointer;-->
        <#--height: 2.1em;-->
        <#--border-left: 1px solid #d6d6d6;-->
        <#--text-indent: -99999px;-->
    <#--}-->

    <#--.ps-clk {-->
        <#--width: 15%;-->
        <#--float: left;-->
        <#--text-align: center;-->
        <#--height: 2.8em;-->
    <#--}-->

    <#--.ps-clk a {-->
        <#--display: block;-->
        <#--background: url(imagesst.png) no-repeat center center;-->
        <#--width: 100%;-->
        <#--background-size: auto 50%;-->
        <#--height: 2.8em;-->
        <#--text-indent: -99999px;-->
    <#--}-->

    <#--.more_goods {-->
        <#--border-top: 1px solid #f5f5f5;-->
        <#--background: #fff;-->
    <#--}-->

    <#--.more_goods a {-->
        <#--display: block;-->
        <#--padding: 0.8em 0;-->
        <#--text-align: center;-->
        <#--font-size: 1.2em;-->
    <#--}-->
<#--</style>-->

<div id="seckilling" class="app_item app_cube" style="display: none;">
    <div class="app_itemTitle">
        <h1>&nbsp;&nbsp;限时秒杀</h1>

        <div class="app_right1" id="countdown">
            <span class="text">距开始还剩</span>
            <span class="num hour">03</span>
            <span class="dot"></span>
            <span class="num min">16</span>
            <span class="dot"></span>
            <span class="num second">20</span>
        </div>
    </div>
    <ul class="app-goodTime clearfix gd-02">
    <#if mobPageTag??>
        <#list mobPageTag.pageTagGoods as pageTagGoods>
            <li class="magin-left">
                <a href="${basePath}/item/${pageTagGoods.goodsproductId!''}.html"><img
                        src="${pageTagGoods.goodsproductImgsrc!''}"></a>
                <div class="goods-info">
                    <h3>${pageTagGoods.goodsproductName!''}</h3>
                    <span goodsproductPrice="￥${pageTagGoods.goodsproductPrice!''}"
                          class="good-price goodsPromotionPrice">￥${pageTagGoods.goodsPromotionPrice!''}</span>
                    <span class="price-text goodsproductPrice">原价</span>
                    <s class="original-price goodsproductPrice">￥:${pageTagGoods.goodsproductPrice!''}</s>
                </div>
            </li>
        </#list>
    </#if>
    </ul>
</div>

<#--<#include "new_search.ftl">-->
<script>
    $(function () {
        $(".bar-bottom a:eq(0)").addClass("selected");
    })
</script>
<#--
<div id="index" style="background:url(${basePath}/images/roll_1.jpg) #e16434 no-repeat center center;background-size:100% auto; height: inherit;"></div>
<div id="index" style="background:url(${basePath}/images/roll_1_2.png) #e16434 no-repeat center center;background-size:100% 100%;"></div>
-->
<div class="curtain_wp">
<#--<#if (mobSiteBasic)??>
    <p>© ${(mobSiteBasic.copyright)!''}</p>
<#else>
    <p>© NINGPAI</p>
</#if>-->
</div><!--/curtain_wp-->

<div id="main" style="display: none;">

    <input class="storeId" type="hidden" value="0">
    <!--引用静态页面-->
<#--<#include "9gdemo.html"/>-->
${mobIndexHtml}
<#--<div class="more_goods">-->
<#--<a href="${basePath}/list/allproduct.html">查看更多商品&gt;&gt;</a>-->
<#--</div><!-- /more_goods &ndash;&gt;-->
    <div class="foot">
    <#--        <p>由${(mobSiteBasic.technicalSupport)!''}提供技术支持</p>-->
    </div><!-- /foot -->


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${basePath}/static/bootstrap/js/bootstrap.min.js"></script>
    <script src="${basePath}/static/js/fastclick.min.js"></script>
    <script src="${basePath}/static/js/idangerous.swiper-2.1.min.js"></script>
    <script src="${basePath}/static/js/jquery.keleyi.js"></script>
    <script src="${basePath}/static/js/jquery.lazyload.js"></script>
    <#--<script src="${basePath}/static/js/wxforward.js"></script>-->
    <script src="${basePath}/static/js/flushMenu.js"></script>
<#--抢购活动，暂时关闭-->
<#--    <script src="${basePath}/js/touchmove.js"></script>-->
    <script src="${basePath}/static/js/countdown.js?v=201511271451"></script>
    <script src="${basePath}/static/js/common.js?v=20151110234"></script>
    <#include "/common/smart_menu.ftl" />
    <script>
        $(".text_app").parent().each(function () {
            $(this).html(HTMLDecode($(this).html()));
        });
    </script>

<#if mobPageTag??>
    <!--处理限时抢购-->
    <script>
        var html = $("#seckilling").html();
        $("#h_seckilling").parent().html(html);
        $("#seckilling").html("");
        var startTime = new Date("${mobPageTag.startTime?string("yyyy/MM/dd HH:mm:ss")}").getTime();
        var endTime = new Date("${mobPageTag.endTime?string("yyyy/MM/dd HH:mm:ss")}").getTime();
        seckilling();
    </script>
</#if>

    <script>
        $(function () {

            //处理魔方高度
            Array.ExistsSameValues = function (a1, a2) {
                var exists = false;
                if (a1 instanceof Array && a2 instanceof Array) {
                    for (var i = 0, iLen = a1.length; i < iLen; i++) {
                        for (var j = 0, jLen = a2.length; j < jLen; j++) {
                            if (a1[i] === a2[j]) {
                                return true;
                            }
                            ;
                        }
                        ;
                    }
                    ;
                }
                ;
                return exists;
            };
            $(".app_cont").each(function () {
                var _this = $(this),
                        _hg = "",
                        arr1 = new Array(),
                        arr2 = new Array(),
                        _th = "",
                        _tp = "",
                        _c = $(window).width() / 4;
                _this.find("img").each(function () {
                    _th = $(window).width() * (parseInt($(this).attr("heightb")) / 100);
                    var styleval = $(this).attr("style");
                    var topval = styleval.substring((styleval.indexOf("top:") + 4), styleval.lastIndexOf("%"));
                    _tp = $(window).width() * (parseInt(topval) / 100);
                    $(this).height(_th);
                    $(this).css("top", _tp + "px");
                    _hg = _tp + _th;
                    arr1.push(_hg);
                    arr2.push(_tp);
                });
                if (Array.ExistsSameValues(arr1, arr2) == true) {
                    var _top = Math.max.apply(null, arr1);
                    _this.height(_top);
                } else {
                    _this.find("img").each(function () {
                        var _t = $(this),
                                _th = parseInt($(this).attr("heightb")) / 100 * $(window).width(),
                                _tp = parseInt($(this).css("top")),
                                _tr = _tp / _c + 1,
                                _rowspan = _th / _c;
                        for (var r = _tr; r < _rowspan + _tr; r++) {
                            _t.addClass("tr" + r + " ");
                        }
                        ;
                    });
                    var arr3 = new Array();
                    for (var n = 1; n < 5; n++) {
                        if (_this.find(".tr" + n).length == 0) {
                            arr3.push(n);
                        }
                        ;
                    }
                    ;
                    _this.height($(window).width() - _c * arr3.length);
                    for (x in arr3) {
                        _this.find("img").each(function () {
                            var _t = $(this),
                                    _n = _t.prop("class").substring(9, 10),
                                    _top = parseInt(_t.css("top"));
                            if (_n > arr3[x]) {
                                _t.css("top", _top - _c + "px");
                            }
                            ;
                        });
                    }
                    ;
                }
                ;
            });

            $(".app_cont_3").each(function () {

                var _this = $(this);
                var _c = $(window).width() / 3;
                var cur_rows = 1;  //得到图片行数
                var cur_cols = 1;  //得到图片列数

                _this.find("img").each(function () {


                    cur_cols = $(this).attr("id").substr($(this).attr("id").length - 3, 1);

                    $(this).css("height", _c * cur_cols);
                    $(this).attr("height", _c * cur_cols);


                    if ($(this).attr("id").indexOf("imgS2") >= 0) {
                        cur_rows = 2;
                        $(this).css("top", _c);
                    }

                    if ($(this).attr("id").indexOf("imgS3") >= 0) {
                        cur_rows = 3;
                        $(this).css("top", _c * 2);
                    }


                });
                $(this).css("height", _c * cur_rows);

            });

        <#--
            $(".col-sm-12").load(function(){
                  $(".col-sm-12").css("height",$(".col-sm-12").parent().parent().prev().css("height")/2);

            });
            var obj = new Image();
            obj.src = $(".img2 img").attr("src");
            obj.onload = function(){
              $(".img_s img").css("height",obj.height/2);
            };
        -->

        });
        var app_cont = $(".app_cont*");
        for (var i = 0; i < app_cont.length; i++) {
            $(app_cont[i]).height($(window).width());
        }

        $(".app_itemTitle").parent().each(function () {
            $(this).addClass("bottom_line_v1");
        });

        $(".blank-box").each(function () {
            $(this).addClass("parting_box");
            $(this).css("height", 15);
        });

        $(function () {
            $(".gd-02 li").css("width", $(window).width() / 2 - 10);
        });
    </script>


</div><!--/main-->
</body>

</html>