<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
    <link rel="stylesheet" type="text/css" href="${bath}/static/css/xcConfirm.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script src="${bath}/static/js/xcConfirm.js"></script>
    <script type="text/javascript" src="${bath}/static/js/common.js?version=${VERSION}"></script>
    <script src="${bath}/static/js/jPages.js"></script>
    <script type="text/javascript" src="${bath}/static/js/jquery-ui.js"></script>
    <style type="text/css">
        @media screen and ( max-width: 1360px){
            body{
                zoom:62.5%;
                font-size:10px!important;
            }
        }
        input[type="text"]{
            border: 1px solid #cccccc;
            border-radius: 3px;
            background: #fff;
        }
        input[type="text"]:hover{
            border: 1px solid #333;
        }
        input[type='checkbox']:hover{
        }
        .roleFunction{
            height:80px;
            margin-left:20px;
            margin-top:25px;
        }
        .roleFunction li{
            width: 500px;
            height:30px;
            float: left;
            margin:10px 0px;
        }
        .roleFunction li input[name="roleSearch"]{
            width:134px;
            height:38px;
            background:#fff;
            border:1px solid #ccc;
            margin-left:-1px;
            margin-top:-1px;
            font-size:16px;

        }
        .roleFunction li input[name="roleSearch"]:focus{
        }

        #roleSearch{
            background:#24b35f;
            width:90px;
            height:38px;
            margin-left:90px;
            color:#fff;
            border:none;
            margin-top:-1px;
            cursor:pointer;
            font-size: 16px;
        }
        #roleSearch:hover{
            background:#00cc55;
        }
        #roleSearch:active{
            background:#008738;
        }

        .roleDetail{
            width:1600px;
            height:800px;
            overflow-y:auto;
            margin-left:20px;
            margin-top:75px;
        }
        .roleDetail dt{
            width:1600px;
            height:50px;
            color:#666666;
            border-bottom:1px solid #e5e5e5;
        }
        .roleDetail dt span{
            display:inline-block;
            vertical-align: middle;
            width:100px;
            height:42px;
            text-align:center;
            padding-top: 12px;
        }
        .roleDetail dt abbr{
            display:inline-block;
            vertical-align: middle;
            width:350px;
            height:42px;
            text-align:center;
        }

        .roleDetail dd{
            width:1600px;
            height:80px;
            background:#fff;
            border-bottom:1px solid #e5e5e5;
        }
        .roleDetail dd span{
            display:inline-block;
            vertical-align: middle;
            width:100px;
            height:80px;
            text-align:center;
            padding:35px 0px;
        }
        .roleDetail dd abbr{
            display:inline-block;
            vertical-align: middle;
            position: relative;
            width:350px;
            height:80px;
            text-align:center;
        }

        .createRole{
            width:680px;
            height:auto;
            position:fixed;
            left:15%;
            top:15%;
            background:#FFF;
            z-index:2;
            display:none;

        }
        #creatDetail{
            width:230px;
            height:300px;
            background:#f2f2f2;
            overflow-y:auto;
            margin:50px 0px 20px 150px;
            text-align:left;
            padding-left:100px;
        }



        #creatDetail dt{
            height:30px;
            padding-top:20px;

        }
        #creatDetail dd{
            padding:5px;
            margin:5px 0px 5px 20px;

        }
        #creatDetail dd li{
            padding:5px;
            margin:5px 0px 5px 20px;

        }
        #creatDetail a{
            display:inline-block;
            width:12px;
            height:12px;
            cursor:pointer;
            border-radius:10px;
            background:#b3b3b3;
            color:white;
            text-align:center;
            line-height:12px;
            margin-left:-10px;
        }
        #creatDetail input{
            margin:0px 10px;
        }
        #creatDetail dd{
            display:none;
        }
        #creatDetail dd ul{
            display:none;
        }
        #creatRoleConfirm{
            width:120px;
            height:38px;
            border-style:none;
            background:#24b35f;
            border:1ps solid transparent;
            position:absolute;
            left:140px;
            top:30px;
            color:#fff;
            cursor:pointer;
            border-radius: 3px;
        }
        #creatRoleConfirm:hover{
        }
        #creatRoleCancel:hover{
        }
        #creatRoleConfirm:active{
            background: #008738;
        }
        #creatRoleCancel:active{
            background: #008738;
            color: #fff;
        }
        #creatRoleCancel{
            width:120px;
            height:38px;
            border-style:none;
            background:#ffffff;
            border:2px solid #666666;
            position:absolute;
            left:420px;
            top:30px;
            cursor:pointer;
            border-radius: 3px;
        }

        .holder{

            margin-top: 60px;

        }

        .updateRole{
            width:600px;
            height:800px;
            position:fixed;
            left:15%;
            top:15%;
            background:#FFF;
            z-index:2;
            display:none;

        }
        #updateDetail{
            width:230px;
            height:300px;
            background:#f2f2f2;
            overflow-y:auto;
            margin:50px 0px 20px 150px;
            text-align:left;
            padding-left:100px;
        }
        #updateDetail dt{
            height:30px;
            padding-top:20px;

        }
        #updateDetail dd{
            padding:5px;
            margin:5px 0px 5px 20px;

        }
        #updateDetail dd li{
            padding:5px;
            margin:5px 0px 5px 20px;

        }
        #updateDetail a{
            display:inline-block;
            width:10px;
            height:10px;
            cursor:pointer;
            border:1px solid #E8E8E8;
            border-radius:5px;
            background:#E8E8E8;
            text-align:center;
            line-height:10px;
            margin-left:-10px;
        }
        #updateDetail input{
            margin:0px 10px;
        }

        #updateRoleConfirm{
            width:60px;
            height:40px;
            border-style:none;
            background:#24b35f;
            border:1ps solid transparent;
            position:absolute;
            left:180px;
            top:30px;
            color:#fff;
            cursor:pointer;

        }
        #updateRoleConfirm:hover{
        }
        #updateRoleConfirm:active{
            background: #008738;
        }
        #updateRoleCancel:hover{
        }
        #updateRoleCancel:active{
            background: #008738;
            color: #fff;
        }
        #updateRoleCancel{
            width:60px;
            height:40px;
            border-style:none;
            background:#fff;
            border:1px solid #999;
            position:absolute;
            left:320px;
            top:30px;
            color: #999;
            cursor:pointer;

        }
        .updateSelect{
            height:40px;
        }
        .updateSelect option{
            height:40px;
            background:#fff;
        }
        .update{
            width:125px;
            height:32px;
            background:#24b383;
            color:white;
            border:1px solid #ccc;
            position:absolute;
            left:110px;
            top:-10px;

        }


        .arrow{
            display:inline-block;
            background:url(${bath}/static/img/com_btn_arrow_bg_down.png) center no-repeat;
            width:20px;
            height:38px;
            position:absolute;
            left:197px;
            top:-11px;

        }
        .delete{
            width:125px;
            height:32px;
            background:#f1f1f1;
            color:999;
            border:1px solid #999;
            position:absolute;
            left:110px;
            top:22px;
            display:none;

        }
.show{
    display: block!important;
}
    </style>
    <script type="text/javascript">
        $(document).ready(function() {
            $(".roleDetail dt input[name='roleDetailCheck']").click(function(){
                if($(this).is(':checked')){
                    $("input[name='roleDetailCheck']").prop("checked","checked");
                }else{
                    $("input[name='roleDetailCheck']").prop("checked",null);
                }
            });
            $("#creatDetail dt a").click(function(){
                var check1=$(this).html();
                if(check1=="+"){
                    $(this).parent().siblings("dd").slideDown();
                    $(this).html("-");
                }else if(check1=="-"){
                    $(this).parent().siblings("dd").slideUp();
                    $(this).html("+");
                }
            });
            $("#creatDetail dd a").click(function(){
                var check2=$(this).html();
                if(check2=="+"){
                    $(this).parent().children("ul").slideDown();
                    $(this).html("-");
                }else if(check2=="-"){
                    $(this).parent().children("ul").slideUp();
                    $(this).html("+");
                }
            });
            $("#creatDetail dt input[name='creatRoleFunctionDT']").click(function(){
                $("input[name='creatRoleFunction']").each(function(){
                   $(this).trigger("click");
                });
            });
            $("#creatDetail dd li input[name='creatRoleFunction']").click(function(){
                if($(this).is(':checked')||$(this).parent().siblings().children("input[name='creatRoleFunction']").is(':checked')){
                   $(this).parent().parent().siblings("input[name='creatRoleFunction']").prop("checked","checked");
                }else{
                    $(this).parent().parent().siblings("input[name='creatRoleFunction']").prop("checked","");
                }
            });
            $("#updateDetail dd li input[name='updateRoleFunction']").click(function(){
                if($(this).is(':checked')||$(this).parent().siblings().children("input[name='updateRoleFunction']").is(':checked')){
                    $(this).parent().parent().siblings("input[name='updateRoleFunction']").prop("checked","checked");
                }else{
                    $(this).parent().parent().siblings("input[name='updateRoleFunction']").prop("checked","");
                }
            });
            $("#creatDetail dd input[name='creatRoleFunction']").click(function(){
                if($(this).is(':checked')){
                    $(this).parent().children("ul").children("li").children("input[name='creatRoleFunction']").prop("checked","checked");
                }else{
                    $(this).parent().children("ul").children("li").children("input[name='creatRoleFunction']").prop("checked",null);
                }

            });
            $("input[name='creatRoleFunction']").click(function(){
                var temp = "";
                var id="";
                $("dd input[name='creatRoleFunction']").each(function(){
                    if($(this).is(':checked')) {
                        temp = temp + $(this).siblings("abbr").html()+",";
                    }
                    $("#choosenVal").val(temp);
                    var s=$("#choosenVal").val();
                    s=s.substring(0,s.length-1);
                    $("#choosenVal").val(s);
                });
            });
            $("#creatRoleCancel,#xx").click(function(){
                discoverHtml();
                $(".createRole").fadeOut(500);
            });
            $("#newRole").click(function(){
                coverHtml();
                $(".createRole").fadeIn(500);
            });
            $("#updateDetail dt a").click(function(){
                var check1=$(this).html()
                if(check1=="+"){
                    $(this).parent().siblings("dd").slideDown();
                    $(this).html("-");
                }else if(check1=="-"){
                    $(this).parent().siblings("dd").slideUp();
                    $(this).html("+");
                }
            });
            $("#updateDetail dd a").click(function(){
                var check2=$(this).html();
                if(check2=="+"){
                    $(this).parent().children("ul").slideDown();
                    $(this).html("-");
                }else if(check2=="-"){
                    $(this).parent().children("ul").slideUp();
                    $(this).html("+");
                }
            });
            $("#updateDetail dt input[name='updateRoleFunctionDT']").click(function(){
                if($(this).is(':checked')){
                    $("input[name='updateRoleFunction']").prop("checked","checked");
                }else{
                    $("input[name='updateRoleFunction']").prop("checked",null);
                }

            });
            $("#updateDetail dd input[name='updateRoleFunction']").click(function(){
                if($(this).is(':checked')){
                    $(this).parent().children("ul").children("li").children("input[name='updateRoleFunction']").prop("checked","checked");
                }else{
                    $(this).parent().children("ul").children("li").children("input[name='updateRoleFunction']").prop("checked",null);
                }

            });
            $("input[name='updateRoleFunction']").click(function(){
                var temp = "";
                $("dd input[name='updateRoleFunction']").each(function(){
                    if($(this).is(':checked')) {
                        temp = temp + $(this).siblings("abbr").html()+",";
                    }
                    $("#updatechoosenVal").val(temp);
                    var s=$("#updatechoosenVal").val();
                    s=s.substring(0,s.length-1);
                    $("#updatechoosenVal").val(s);
                });
            })
            $("#updateRoleCancel,#xx1").click(function(){
                discoverHtml()
                $(".updateRole").fadeOut(500);
            })
            $(document).on("click",".update",function(){
                var update_this = this;
                var p_ids = "[";
                var a_id = $(this).parent().parent().parent().find("input[type='hidden']").val();
                $("#a_id").val(a_id);
                $.post("${bath}/web/api/rolemanager/getAuthority",{
                    "authorityId":a_id
                },function(data){
                    if(data.response == 'success'){
                        //获取所有checkbox
                        var _this = $(".updateRole").find("input[type='hidden']");
                        var length = _this.length;

                        data.data.map(function(object){
                            p_ids += '"' + object.id + '",';
                            for(var i = 0;i < length;i++) {
                                if (object.id == _this[i].value) {
                                    if( $(_this[i]).prev().prev().prop("checked") ){

                                    }
                                    else{
                                        $(_this[i]).prev().prev().trigger("click");
                                    }
                                }
                            }
                        });

                        p_ids = p_ids.substring(0,p_ids.length-1);
                        p_ids += ']';
                        $("#p_ids").val(p_ids);
                        //pangeIds赋值操作


                        var roleName=$(update_this).parent().parent().siblings(".roleName").html();
                        var note=$(update_this).parent().parent().siblings(".note").html();
                        $(".updateRole").fadeIn(500);
                        coverHtml();
                        $("#roleName").val(roleName);
                        $("#note").html(note);
                    }
                },'json');
            })
            $(document).on("mouseover",".changebutton",function(){
                $(this).children(".delete").show()
            })
            $(document).on("mouseout",".changebutton",function(){
                $(this).children(".delete").hide()
            })
        });

    </script>

    <title>无标题文档</title>
</head>

<body style="background: #edf3f8">
<div class="allOutShow" style="background:#fff;margin-left: 20px; margin-top: 20px;margin-bottom: 20px; color:#666666!important;width: 100%;overflow-x: scroll;">
<div class="allheadstyle">
    <span>角色管理</span><a class="leftshanow" href="employeeManager">员工管理</a><abbr></abbr>
</div>

<div class="roleFunction">
    <ul>
        <li><input placeholder="角色名称" class="allinputButton" type="search" name="roleSearch" /><input class="allseachButton" type="button" id="roleSearch" value="搜索" /></li>
        <li style="float: right"><input type="button" id="newRole" value="添加" class="button_role allclickButton"/><input style="margin-left: 30px;" type="button" id="deleteRole" value="删除" class="button_role allclickButton"/></li>
    </ul>
</div>
<div class="roleDetail">
    <dl>
        <dt>
            <span><input type="checkbox" name="roleDetailCheck" id="checkbox_dt_roleDetail"/><label for="checkbox_dt_roleDetail"></label></span>
            <abbr>角色名称</abbr>
            <abbr>备注</abbr>
            <abbr>成员数量</abbr>
            <abbr>操作</abbr>
        </dt>
        <div id="itemContainer">
            <#list authWithMAmountResults as object>
                <dd>
                    <span><input type="checkbox" name="roleDetailCheck" /></span>
                    <input type="hidden" value="${object.id}" name="detail_id"/>
                    <abbr class="roleName">${object.name}</abbr>
                    <abbr class="note">${object.characterization}</abbr>
                    <abbr>${object.amount}</abbr>
                    <abbr>
                        <div class="changebutton">
                        <input class="update allSelectButton" type="button" value="编辑" /><a value="0" class="arrow"></a>
                        <input class="delete allSelectButton" type="button" value="删除" />
                        </div>
                    </abbr>
                </dd>
            </#list>
        </div>
        <div id="hiddenContainer" style="display: none;">
        <#list authWithMAmountResults as object>
            <dd>
                <span><input type="checkbox" name="roleDetailCheck" /></span>
                <input type="hidden" value="${object.id}" name="detail_id"/>
                <abbr class="roleName">${object.name}</abbr>
                <abbr class="note">${object.characterization}</abbr>
                <abbr>${object.amount}</abbr>
                <abbr>
                    <div class="changebutton">
                    <input class="update allSelectButton" type="button" value="编辑" /><a value="0" class="arrow"></a>
                    <input class="delete allSelectButton" type="button" value="删除" />
                    </div>
                </abbr>
            </dd>
        </#list>
        </div>
        <div class="holder allcpageTurnButton"></div>
    </dl>
</div>

<div class="createRole allpop">
    <h1 style="height:80px;color:#000; font-size:22px; line-height:80px; padding-left:30px;">添加角色
        <i id="xx" style="background:url(${bath}/static/img/XX.png) center no-repeat; display:inline-block; width:30px; height:30px;"></i>
    </h1>
    <ul>
        <li style="height:630px; text-align:center;">
            <div style="padding-top:30px;">角色名称： <input type="text" style="width:400px;height:35px;border-radius: 3px;border: 1px solid black;" />
            </div>
            <div style="margin-top:30px;">已选功能： <input readonly="readonly" type="text" id="choosenVal" style="width:400px;height:35px;border-radius: 3px;border:1px solid black;" />
                <div id="creatDetail">
                    <dl>
                        <dt><a>+</a><span><input type="checkbox" name="creatRoleFunctionDT" id="checkbox_dt"/><label for="checkbox_dt"></label></span><abbr>全部</abbr></dt>
                        <#list pagesMap?keys as parentPage>
                            <#assign parent = (parentPage?split(","))>
                            <dd><a>+</a><input type="checkbox" name="creatRoleFunction" class="creatRoleFunction"/><label></label><abbr>${parent[1]}</abbr><input name="parentId" type="hidden" value="${parent[0]}" />
                                <ul>
                                    <#list pagesMap[parentPage] as sonPage>
                                        <li><input type="checkbox" name="creatRoleFunction" class="creatRoleFunction"/><label></label><abbr>${sonPage.designation}</abbr><input name="sonId" type="hidden" value="${sonPage.id}" /></li>
                                    </#list>
                                </ul>
                            </dd>
                        </#list>
                    </dl>
                </div>
            </div>
            <div style="padding-top:30px;position: relative;"><span style="height:65px;display: inline-block;vertical-align:middle;margin-top: -70px;">备注：</span><textarea style="width:400px;height:68px;margin-left:10px;border:1px solid black;border-radius: 3px;" cols="40" rows="3"></textarea>
            </div>
        </li>
        <li style="height:80px; border-top:1px solid #CCC; position:relative;">
            <input class="allseachButton" type="button" value="确定" id="creatRoleConfirm" />
            <input class="allcancelButton" type="button" value="取消" id="creatRoleCancel" />
        </li>
    </ul>
</div>

<div class="updateRole allpop">
    <h1 style="height:80px; color:#000; font-size:22px; line-height:80px; padding-left:30px;">编辑角色
        <i id="xx1" style="background:url(${bath}/static/img/XX.png) center no-repeat; display:inline-block; width:30px; height:30px;"></i>
    </h1>
    <ul>
        <input type="hidden" id="a_id" />
        <input type="hidden" id="p_ids" />
        <li style="height:630px; text-align:center;">
            <div style="padding-top:30px;">角色名称： <input type="text" style="width:300px;" id="roleName" />
            </div>
            <div style="margin-top:30px;">已选功能： <input readonly="readonly" type="text" id="updatechoosenVal" style="width:300px;" />
                <div id="updateDetail">
                    <dl>
                        <dt><a>-</a><input type="checkbox" name="updateRoleFunctionDT" /><abbr>全部</abbr></dt>
                        <#list pagesMap?keys as parentPage>
                            <#assign parent = (parentPage?split(","))>
                            <dd><a>-</a><input type="checkbox" name="updateRoleFunction" /><abbr>${parent[1]}</abbr><input name="parentId" type="hidden" value="${parent[0]}" />
                                <ul>
                                    <#list pagesMap[parentPage] as sonPage>
                                        <li><input type="checkbox" name="updateRoleFunction" /><abbr>${sonPage.designation}</abbr><input name="sonId" type="hidden" value="${sonPage.id}" /></li>
                                    </#list>
                                </ul>
                            </dd>
                        </#list>

                    </dl>
                </div>
            </div>
            <div style="padding-top:30px;"><span style="height:65px;display: inline-block;vertical-align:middle;margin-top: -70px;">备注：</span><textarea id="note" style="margin-left:10px;" cols="40" rows="3"></textarea>
            </div>
        </li>
        <li style="height:80px; border-top:1px solid #CCC; position:relative;">
            <input class="allseachButton" type="button" value="确定" id="updateRoleConfirm" />
            <input class="allcancelButton" type="button" value="取消" id="updateRoleCancel" />
        </li>
    </ul>
</div>


<script type="text/javascript">
    //禁止后退键 作用于Firefox、Opera
    document.onkeypress = forbidBackSpace;
    //禁止后退键  作用于IE、Chrome
    document.onkeydown = forbidBackSpace;
    $(document).ready(function(){
        $( ".createRole" ).draggable();
        $( ".updateRole" ).draggable();

        $("input[type='text']").focus(function(){
            $(this).css("background","#f1f1f1");
        })
        $("input[type='text']").blur(function(){
            $(this).css("background","#fff");
        })

        $("div.holder").jPages({
            containerID : "itemContainer",
            perPage : 6
        });
        $(document).on("click","#creatRoleConfirm",function(){
            coverHtml();
            var pageIds = '[';
            for(var i = 0; i<$(".createRole .creatRoleFunction:checked").length;i++){
                pageIds += '"' + $( $(".createRole .creatRoleFunction:checked")[i]).parent().find("input[type='hidden']").val() + '",';
            }
            pageIds = pageIds.substring(0,pageIds.length-1);
            pageIds += ']';
            console.log(pageIds)
            var name = $(".createRole").find("input[type='text']")[0].value;
            var str = $(".createRole").find("input[type='text']")[1].value;

            var textarea_remark = $($(".createRole").find("textarea")).val();

            if( name == '' ){
                data_type_alert("角色名称不能为空","warning");
            }
            else{
                if( $("#choosenVal").val() != '' ){
                    $.post("${bath}/web/api/rolemanager/addNewAuthority",{
                        "name":name,
                        "pageIds":pageIds,
                        "remark":textarea_remark
                    },function(data){
                        if( data.response == 'success' ){
                            discoverHtml();
                            ensure_alert('RoleManager')
                        }
                        else{
                            response_ensure_alert("error",data.data.text,function(){
                                console.log("新建会员失败" + consoleNowTime());
                            })
                        }
                    },'json');
                }
                else{
                    data_type_alert("功能选择不能为空","warning");
                }
            }


        });

        //删除



        //搜索
        $("#roleSearch").click(function(){
            var keywords = $.trim($("input[name='roleSearch']").val());
            if( keywords != '' ){
                var length = $("#hiddenContainer dd").length;
                var html = '';
                for(var i = 0;i < length; i++){
                    var str = $( $("#hiddenContainer dd")[i]).find("abbr")[0].innerHTML;
                    console.log(str);
                    if( str.match( keywords ) ){
                        html += $("#hiddenContainer dd")[i].outerHTML;
                    }
                }
                $("#itemContainer").empty();
                $("#itemContainer").append(html);

                $("div.holder").jPages({
                    containerID : "itemContainer",
                    perPage : "6"
                });
            }
            else{
                window.location.href = 'RoleManager';
            }
        });

        $(document).on("click","#deleteRole",function(){
            var length = $("#itemContainer input[type='checkbox']").length;
            var Ids = "[";
            for(var i = 0;i < length;i++){
                if( $( $("#itemContainer input[type='checkbox']")[i]).prop("checked") ){
                    Ids += '"' + $("input[name='detail_id']")[i].value + '",';
                }
            }
            Ids = Ids.substring( 0, Ids.length-1 );
            Ids += "]";
            if(Ids=="]"){
                data_type_alert("请勾选想要删除的角色","error")
            }
            $.post("${bath}/web/api/rolemanager/deleteAuthority",{
                "authorityIds":Ids
            },function(data){
                if(data.response == 'success'){
                    discoverHtml()
                    ensure_alert('RoleManager')
                }else{
                    data_type_alert(data.data.text,"error")
                }
            },'json');
        })
        $(document).on("click",".delete",function(){
            coverHtml();
            var _this = this;
            response_OkCancel_alert('warning','是否确认删除',function(){
                var id = $(_this).parent().parent().parent().find("input[name='detail_id']").val();
                $.post("${bath}/web/api/rolemanager/deleteAuthority", {
                    "authorityIds": "[" + id + "]"
                }, function (data) {
                    discoverHtml();
                    if (data.response == 'success') {
                        discoverHtml();
                        ensure_alert('RoleManager');
                    }
                    else{
                        response_ensure_alert("error",data.data.text,function(){

                        });
                    }
                }, 'json');
            },function(){

            });
            discoverHtml();
        });
        $(document).on("click","#updateRoleConfirm",function(){
            var a_id = $("#a_id").val();
            var remark = $("#note").val();
            var name = $("#roleName").val();
            var p_ids = '[';
            var length = $(".updateRole input[name='updateRoleFunction']").length;
            for(var i = 0;i < length;i++){
                var flag = $( $(".updateRole input[name='updateRoleFunction']")[i]).prop("checked");
                if( flag ){
                    p_ids += '"' + $( $(".updateRole input[name='updateRoleFunction']")[i]).parent().find("input[type='hidden']").val() + '",';
                }
            }
            p_ids = p_ids.substring(0,p_ids.length-1);
            p_ids += ']';
            $.post("${bath}/web/api/rolemanager/editAuthority",{
                "authorityId":a_id,
                "pageIds":p_ids,
                "remark":remark,
                "name":name
            },function(data){
                discoverHtml();
                if( data.response == 'success' ){
                    discoverHtml()
                    window.location.href = 'RoleManager';
                    window.parent.frames['mainleft'].window.location.href = 'mainleft';
                }
                else{

                }
            },'json');
        });
    });
</script>

<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
</div>


</body>
</html>
