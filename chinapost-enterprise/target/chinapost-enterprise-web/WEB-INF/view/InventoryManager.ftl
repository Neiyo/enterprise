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
    <script type="text/javascript" src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/zrj_ajaxPages.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/inventoryManager.js?version=${VERSION}"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style type="text/css">
        @media screen and ( max-width: 1360px){
            body{
                zoom:62.5%;
                font-size:10px!important;
            }
        }
        .memberSearch{
            /*width:1300px;*/
            width:800px;
            height:150px;
            margin-left:20px;
            margin-top:20px;
        }
        .memberSearch li{
            width:250px;
            height:36px;
            line-height:40px;
            float:left;
        }
        .memberSearch li input,.memberSearch liselect{
            width:200px;
            height:35px;
            margin-left: -6px;
            border:1px solid #ccc;
            background:#FFF;
            margin-top: -1px;
        }
        .memberSearch li select{
            height: 38px;
        }
        option{
            font-size: 15px;
        }
        .memberSearch li span{
            display:inline-block;
            width:90px;
            height:36px;
            text-align:center;
            font-size:15px;
            background:#f1f1f1;
            line-height:36px;
            border:1px solid #ccc;

        }

        #invenAdd{
            width:110px;
            height:37px;
            background:#24b383;
            color:#FFF;
            border:1px solid transparent;
            font-size:15px;
        }
        #invenAdd:hover{
        }
        #invenAdd:active{
            background: #009966;
        }
        #warningSet:hover{
        }
        #warningSet:active{
            background: #009966;
        }
        #warningSet{
            width:110px;
            height:37px;
            background:#24b383;
            color:#FFF;
            font-size: 15px;
            border:1px solid transparent;
            margin-left:30px;
        }
        #inverExplor:hover{
        }
        #inverExplor:active{
            background: #009966;
        }
        #inverExplor{
            width:110px;
            height:37px;
            background:#24b383;
            color:#FFF;
            font-size: 15px;
            border:1px solid transparent;
            margin-left:30px;
            text-align:center;
        }
        #inverExplor option{
            background:#f2f2f2;
            color:#333;
        }
        .accountTable{
            width:1600px;
            height:auto;
            margin-left:20px;
        }
        .accountTable ul{
            width:1000px;
            height:30px;
        }
        .accountTable ul li{
            float:left;
            width:100px;
            height:28px;
            line-height:30px;
            border-bottom:2px solid transparent;
            text-align:center;
            margin-right:-1px;
            cursor:pointer;
            color: #666666;
        }
        .accountTable ul li.accOn{
            color: #54a6de;;
            border-bottom:2px solid #54a6de;

        }
        .invenList{
            width:1600px;
            height:800px;
            overflow-y:auto;
        }
        .invenList dt{
            width:1600px;
            height:50px;
            border-bottom:1px solid #e5e5e5;
            border-top:1px solid #e5e5e5;
            color:#666666;
        }
        .invenList dt abbr{
            display:inline-block;
            width:150px;
            height:50px;
            text-align:center;
            padding:15px 0px;
        }
        .invenList dd{
            width:1600px;
            height:80px;
            background:#fff;
            border-bottom:1px solid #CCC;
        }
        .invenList dd abbr{
            display:inline-block;
            text-align:center;
            width:150px;
            height:80px;
            vertical-align:middle;
            padding:20px 0px;

        }
        .invenList dd abbr.invenName{
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }
        .invenListSelect{
            text-align:center;
        }

        .updateInvenNum{
            width:600px;
            height:auto;
            position:absolute;
            left:15%;
            top:30%;  C;
            background:#FFF;
            display:none;
            z-index: 4;
        }
        #xx{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #updateInvenNumConfirm{
            width:80px;
            height:30px;
            border-style:none;
            border:1px solid transparent;
            background:#24b383;
            position:relative;
            left:180px;
            top:40px;
            color:#FFF;
        }
        #updateInvenNumConfirm:hover{
        }
        #updateInvenNumConfirm:active{
            background: #009966;
        }
        #updateInvenNumCancel{
            width:80px;
            height:30px;
            border-style:none;
            border:1px solid #CCC;
            background:#fff;
            position:relative;
            left:210px;
            top:40px;
        }
        #updateInvenNumCancel:hover{
        }
        #updateInvenNumCancel:active{
            background: #009966;
            color: #fff;
        }


        .warningNum{
            width:600px;
            height:auto;
            position:absolute;
            left:15%;
            top:30%;  C;
            background:#FFF;
            display:none;
            z-index: 4;
        }
        #xx1{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #warningNumConfirm{
            width:80px;
            height:30px;
            border-style:none;
            border:1px solid transparent;
            background:#24b383;
            position:relative;
            left:180px;
            top:40px;
            color:#FFF;
        }
        #warningNumConfirm:hover{
        }
        #warningNumConfirm:active{
           background: #009966;
        }
        #warningNumCancel:hover{
        }
        #warningNumCancel:active{
            background: #009966;
            color: #fff;
        }
        #warningNumCancel{
            width:80px;
            height:30px;
            border-style:none;
            border:1px solid #CCC;
            background:#fff;
            position:relative;
            left:210px;
            top:40px;
        }


        .deleteWarn{
            width:600px;
            height:auto;
            position:absolute;
            left:15%;
            top:30%;
            border:1px solid #CCC;  C;
            background:#FFF;
            display:none;
            z-index: 4;
        }
        #deleteWarnConfirm{
            width:80px;
            height:30px;
            border-style:none;
            border:1px solid transparent;
            background:#24b383;
            position:relative;
            left:220px;
            top:40px;
            color:#FFF;
        }
        #deleteWarnConfirm:hover{
        }
        #deleteWarnConfirm:active{
            background: #009966;
        }

        .deleteCom{
            width:600px;
            height:350px;
            position:absolute;
            left:15%;
            top:30%;  C;
            background:#FFF;
            display:none;
            z-index: 4;
        }
        #xx4{
            display:inline-block;
            width:25px;
            height:25px;
            background:url(${bath}/static/img/XX.png) center no-repeat;
            cursor:pointer;
        }
        #deleteComConfirm{
            width:80px;
            height:30px;
            border-style:none;
            border:1px solid transparent;
            background:#24b383;
            position:relative;
            left:180px;
            top:40px;
            color:#FFF;
        }
        #deleteComConfirm:hover{

          }
        #deleteComConfirm:active{
            background: #009966;
        }
        #deleteComCancel:hover{
        }
        #deleteComCancel:active{
            background: #009966;
            color: #fff;
        }
        #deleteComCancel{
            width:80px;
            height:30px;
            border-style:none;
            border:1px solid #CCC;
            background:#fff;
            position:relative;
            left:210px;
            top:40px;
        }
        #holder_warning{
            margin-top: 20px;
        }
        #holder{
            margin-top: 20px;
        }

        #search{
            background: #24b383;
            color: #fff;
        }

        #search2{
            background: #24b383;
            color: #fff;
        }

        .select{
            display: inline-block;
            vertical-align: middle;
            width:120px;
            height:38px;
            margin-left:20px;
            background:#f8f8f8;
            text-align: center;
            position: relative;
        }
        .select dt,.select dd {
            width:120px;
            height:38px;
            border:1px solid #ccc;
            background:#fff;
            margin-top: -1px;
            margin-left:-1px;
            cursor: pointer;
            line-height: 38px;
            color: #666666;
        }
        .arrow{
            display: inline-block;
            position: absolute;
            right: 0px;
            top: 0px;
            width: 36px;
            height: 38px;
            background:url(${bath}/static/img/com_btn_arrow_white_down.png) center no-repeat;
        }
        .select dd{
            display: none;
        }
        .select dd:hover{
        }
        .select dd:active{
            background: #009988;
        }
        .select dt:hover{
        }
        .select dt:active{
            background: #009988;
        }
        #invenLocation,#invenAsk,#invenWarnning{
            background: #24b383;
            color: #fff;
            height: 38px;
        }
    <#if isTop == 'false'>
        .changeInventory{
            display:none;
        }
    </#if>

</style>
    <script type="text/javascript">
        //禁止后退键 作用于Firefox、Opera
        document.onkeypress = forbidBackSpace;
        //禁止后退键  作用于IE、Chrome
        document.onkeydown = forbidBackSpace;
        var checked_array = {
            length:0
        };
        var outport_array = {};

        $(document).ready(function(e) {
            $( ".deleteWarn" ).draggable();
            $( ".warningNum" ).draggable();
            $( ".updateInvenNum" ).draggable();
            $( ".deleteCom" ).draggable();

            $(document).on("click",".invenList dt input[name='invenListCheck']",function(){
                if(!$(this).is(':checked')){
                    $("#itemContainer input[name='invenListCheck']:checked").trigger("click");
                }else{
                    $("#itemContainer input[name='invenListCheck']:not(:checked)").trigger("click")
                }

            })



            $("#updateInvenNumCancel,#xx").click(function(){
                discoverHtml()
                $(".updateInvenNum").fadeOut(500);
            })
            $("#warningNumCancel,#xx1").click(function(){
                discoverHtml()
                $(".warningNum").fadeOut(500);
            })
            $("#deleteComCancel,#xx4").click(function(){
                discoverHtml()
                $(".deleteCom").fadeOut(500);
            })
            $("#deleteWarnConfirm").click(function(){
                discoverHtml()
                $(".deleteWarn").fadeOut(500);
            })

            $("#warningSet").click(function(){
                coverHtml();
                $.post("../web/api/inventory/getWarning",{},function( data ){
                    if( data.response == 'success' ){
                        $("input[name='setWarningNumber']").val(data.data)
                    }
                    else{
                    }
                },'json')
                $(".warningNum").fadeIn(500);
            })

            $(document).on("change",".invenListSelect",function(){
                $("#goodsId").val($(this).parent().parent().children()[0].value);
                if($(this).val()==1){
                    coverHtml()
                    $(this).val(0);
                    $(".updateInvenNum").fadeIn(500);

                }
                else if($(this).val()==2){
                    $(this).val(0)
                    var num1=$(this).parent().siblings(".invenNum").html();
                    var num2=$(this).parent().siblings(".invenNumUsed").html();
                    if(num1>0&&num2>0||num1>0&&num2>=0){
                        coverHtml()
                        $(".deleteWarn").fadeIn(500);
                    }else if(num1==0&&num2==0){
                        coverHtml()
                        $(".deleteCom").fadeIn(500);
                    }
                }
                else if($(this).val()==3){
                    $(this).val(0)
                }
            });
            $("#invenAsk").click(function(){
                window.open("replenishment");
            });
            $("#invenAdd").click(function(){
                window.location.href="AddInventory"
            });
            $(document).on("click","#invenWarnning",function(){
                window.open("reimburse");
            })
            $(".accountTable li").click(function(){
                $(this).addClass("accOn").siblings("li").removeClass("accOn");
                if( $(this).val() == 0 ){
                    $("dl[name='first']").css("display","block");
                    $("dl[name='second']").css("display","none");

                    checked_array = {};
                    outport_array = {};
                    checked_array.length = 0;
                    ajaxPages('../web/api/inventory/getInventoryGoods','itemContainer','holder',8,5,'','','',function(pageId){
                        if( checked_array[pageId] != undefined ) {
                            $(".accountNumber").each(function () {
                                var _this = this;
                                checked_array[pageId].map(function(orderCode){
                                    if($(_this).parent().prev().val() == orderCode){
                                        $(_this).prop("checked","checked");
                                    }
                                });
                            });
                        }
                    });
                    $("#search").show()
                    $("#search2").hide()
                }
                else if( $(this).val() == 1 ){
                    $("dl[name='first']").css("display","none");
                    $("dl[name='second']").css("display","block");

                    checked_array = {};
                    outport_array = {};
                    checked_array.length = 0;
                    ajaxPages('../web/api/inventory/getWarningGoods','itemContainer_warning','holder_warning',8,5,'','','',function(pageId){
                        if( checked_array[pageId] != undefined ) {
                            $(".accountNumber").each(function () {
                                var _this = this;
                                checked_array[pageId].map(function(orderCode){
                                    if($(_this).parent().prev().val() == orderCode){
                                        $(_this).prop("checked","checked");
                                    }
                                });
                            });
                        }
                    });
                    $("#search2").show()
                    $("#search").hide()
                }
            })
        });


    </script>
    <title>无标题文档</title>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
    <div class="allheadstyle"><span>仓库管理</span><a class="leftshanow" href="inventoryInquiry">库存查询</a><a class="leftshanow" href="voucherManager">单据管理</a><abbr></abbr>
    </div>

<div class="memberSearch">
    <ul>
        <li>
            <input class="allinputButton" placeholder="货品名称" type="text" name="memberNum" />
        </li>
        <li>
            <input class="allinputButton" placeholder="货品编号" type="text" name="memberCust" />
        </li>
        <#--<li>-->
            <#--<span>品牌</span>-->
            <#--<select id="brand_select">-->
                <#--<option selected="selected">全部</option>-->
                <#--<#list brandResult as object>-->
                    <#--<option value="${object.brandId}">${object.goodsBrand}</option>-->
                <#--</#list>-->
            <#--</select>-->
        <#--</li>-->
        <#--<li>-->
            <#--<span>商家</span>-->
            <#--<select id="thirdname_select">-->
                <#--<option selected="selected">全部</option>-->
                <#--<#list thirdNameResult as object>-->
                    <#--<option value="${object.thirdId}">${object.thirdName}</option>-->
                <#--</#list>-->
            <#--</select>-->
        <#--</li>-->
        <li>
            <input class="allseachButton" type="button" value="搜索" id="search"/>
            <input class="allseachButton"  style="display: none;" type="button" value="搜索" id="search2"/>
        </li>
        <li style="width:800px; margin-top:20px;">

            <#if isTop == true>
            <input class="allclickButton"  type="button" value="添加货品" id="invenAdd" />
            </#if>

            <input class="allclickButton"  onclick="coverHtml()" type="button" id="warningSet" value="预警设置" />
            <#if isTop == true>
                <input class="allclickButton"  style="margin-left:20px;" type="button" value="库存调拨" class="invenBut" id="invenLocation" />
            <#elseif isEnd == true>
                <input class="allclickButton"  style="margin-left:20px;" type="button" value="库存调拨" class="invenBut" id="invenLocation" />
            </#if>
            <#if isEnd == true>
                <input class="allclickButton"  style="margin-left: 20px;" type="button" value="补货申请" id="invenAsk" />
                <input class="allclickButton"  style="margin-left: 20px;" type="button" value="报损，报溢" id="invenWarnning" />
            </#if>
                <dl class="select"> <i value="0" class="arrow"></i>
                    <dt class="allseachButton" id="outputAll">导出所有</dt>
                    <dd class="allselectbutton" id="outputPart">导出选中</dd>
                </dl>

        </li>
    </ul>
</div>


<div class="accountTable">
    <ul>
        <li class="accOn" value="0">全部货品</li>
        <li value="1">预警货品</li>
    </ul>
    <dl class="invenList" name="first">
        <dt><abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr><abbr>图片</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>库存</abbr><abbr>可用库存</abbr><abbr>品牌</abbr><abbr>所属商家</abbr><abbr style="width:300px;">操作</abbr>
        </dt>
        <div id="itemContainer"></div>
        <div class="allcpageTurnButton" id="holder"></div>
    </dl>
    <dl class="invenList" name="second" style="display: none;">
        <dt><abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr><abbr>图片</abbr><abbr>货品名称</abbr><abbr>货品规格</abbr><abbr>货品编号</abbr><abbr>库存</abbr><abbr>可用库存</abbr><abbr>品牌</abbr><abbr>所属商家</abbr><abbr style="width:300px;">操作</abbr>
        </dt>
        <div id="itemContainer_warning"></div>
        <div class="allcpageTurnButton" id="holder_warning"></div>
    </dl>

</div>


<div class="updateInvenNum allpop">
    <h1>修改库存<i onclick="discoverHtml()" id="xx"></i></h1>
    <ul>
        <li style="height:150px; line-height:150px;">
            <span style="padding-left:100px; font-size:22px;"><i style="color:#ff3300;">*</i>库存:<input style="width:220px; height:40px; margin-left:40px;" type="text" name="editInventory" /></span>
        </li>
        <li style="height:120px; border-top:1px solid #CCC;">
            <input type="hidden" id="goodsId"/>
            <input class="allseachButton" type="button" id="updateInvenNumConfirm" value="确定" />
            <input class="allcancelButton" onclick="discoverHtml()" type="button" id="updateInvenNumCancel" value="取消" />
        </li>
    </ul>
</div>


<div class="warningNum allpop">
    <h1>预警设置<i onclick="discoverHtml()" id="xx1"></i></h1>
    <ul>
        <li style="height:150px; line-height:150px;">
            <span style="padding-left:100px; font-size:22px;">设置预警值:<input style="width:220px; height:40px; margin-left:40px;" type="text" name="setWarningNumber" /></span>
        </li>
        <li style="height:120px; border-top:1px solid #CCC;">
            <input class="allseachButton" onclick="discoverHtml()" type="button" id="warningNumConfirm" value="确定" />
            <input class="allcancelButton" onclick="discoverHtml()" type="button" id="warningNumCancel" value="取消" />
        </li>
    </ul>
</div>

<div class="deleteWarn allpop">
    <h1>提示</h1>
    <ul>
        <li style="height:150px; line-height:150px;">
            <span style="padding-left:100px; font-size:22px;">货品库存大于0，无法删除！</span>
        </li>
        <li style="height:120px; border-top:1px solid #CCC;">
            <input class="allseachButton" onclick="discoverHtml()" type="button" id="deleteWarnConfirm" value="确定" />
        </li>
    </ul>
</div>


<div class="deleteCom allpop">
    <h1>确认提示<i onclick="discoverHtml()" id="xx4"></i></h1>
    <ul>
        <li style="height:150px; line-height:150px;">
            <span style="padding-left:100px; font-size:22px;">您确定删除该货品吗？</span>
        </li>
        <li style="height:120px; border-top:1px solid #CCC;">
            <input class="allseachButton" type="button" id="deleteComConfirm" value="确定" />
            <input class="allcancelButton" onclick="discoverHtml()" type="button" id="deleteComCancel" value="取消" />
        </li>
    </ul>
</div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
    </div>
</body>
<script>
    $(document).ready(function(){
        $(".arrow").click(function(){
            var arr=$(this).val();
            $(this).siblings("dd").slideToggle()
            if(arr==0){
                $(this).css("background","url(${bath}/static/img/com_btn_arrow_white_up.png) center no-repeat")
                $(this).val("1")
            }else if(arr==1){
                $(this).css("background","url(${bath}/static/img/com_btn_arrow_white_down.png) center no-repeat")
                $(this).val("0")
            }

        });
        $(document).on("click","#invenLocation",function(){
            window.open("allocation");
        });
    })
</script>
</html>
