$(document).ready(function(){
    var fileName = '';

    var idCard_search = '';
    var linkPhone_search = '';
    var name_search = '';
    var isActive_search = '';
    var isPhoneChecked_search = '';
    var managerNo_search = '';
    var tagId = '';
    var enterpriseId = eid;
    var array = [];
    array[0] = idCard_search;
    array[1] = linkPhone_search;
    array[2] = name_search;
    array[3] = isActive_search;
    array[4] = isPhoneChecked_search;
    array[5] = managerNo_search;
    array[6] = tagId;
    array[7] = enterpriseId;
    ajaxPages('../web/api/customer/getCustomers','itemContainer','holder',0,5,array);
    $("#newMemAddressP").change(function(){
        $("#newMemAddressC option[value!='0'],#newMemAddressD option[value!='0'],#newMemAddressR option[value!='0']").remove()
        $.post("../web/api/common/cities",{
            "provinceId":$(this).val()
        },function(data){
            if( data.response == 'success' ){
                data.data.map(function(object){
                    var html = '';
                    html += '<option value="' + object.cityId +'">';
                    html += object.cityName;
                    html += '</option>';
                    $("#newMemAddressC").append(html);
                });
            }
        },'json');
    });
    $("#newMemAddressC").change(function(){
        $("#newMemAddressD option[value!='0'],#newMemAddressR option[value!='0']").remove()
        $.post("../web/api/common/districts",{
            "cityId":$(this).val()
        },function(data){
            if( data.response == 'success' ){
                data.data.map(function(object){
                    var html = '';
                    html += '<option value="' + object.districtId +'">';
                    html += object.districtName;
                    html += '</option>';
                    $("#newMemAddressD").append(html);
                });
            }
        },'json');
    });
    $("#newMemAddressD").change(function(){
        $("#newMemAddressR option[value!='0']").remove()
        $.post("../web/api/common/streets",{
            "districtId":$(this).val()
        },function(data){
            if( data.response == 'success' ){
                data.data.map(function(object){
                    var html = '';
                    html += '<option value="' + object.streetId +'">';
                    html += object.streetName;
                    html += '</option>';
                    $("#newMemAddressR").append(html);
                });
            }
        },'json');
    });
    $("#updateMemAddressP").change(function(){
        $("#updateMemAddressC option[value!='0'],#updateMemAddressD option[value!='0'],#updateMemAddressR option[value!='0']").remove()
        $.post("../web/api/common/cities",{
            "provinceId":$(this).val()
        },function(data){
            if( data.response == 'success' ){
                data.data.map(function(object){
                    var html = '';
                    html += '<option value="' + object.cityId +'">';
                    html += object.cityName;
                    html += '</option>';
                    $("#updateMemAddressC").append(html);
                });
            }
        },'json');
    });
    $("#updateMemAddressC").change(function(){
        $("#updateMemAddressD option[value!='0'],#updateMemAddressR option[value!='0']").remove()
        $.post("../web/api/common/districts",{
            "cityId":$(this).val()
        },function(data){
            if( data.response == 'success' ){
                data.data.map(function(object){
                    var html = '';
                    html += '<option value="' + object.districtId +'">';
                    html += object.districtName;
                    html += '</option>';
                    $("#updateMemAddressD").append(html);
                });
            }
        },'json');
    });
    $("#updateMemAddressD").change(function(){
        UpdateMemAddressD();
    });
    //判断isNew
    $("input[name='newMemId']").blur(function(){
        var str = $("input[name='newMemId']").val()
        spstr = str.split("");
        var _this = this;
        var checkNum = /\d{6}[12][7890][0-9]{2}[01][0-9][0-3][0-9]{4}[xX0-9]/;
        var val = $('input[name="newMemId"]').val();
        if ( checkNum.test(val) ){
            $(this).next().html("")
            if(spstr[spstr.length-2]=="1"||spstr[spstr.length-2]=="3"||spstr[spstr.length-2]=="5"||spstr[spstr.length-2]=="7"||spstr[spstr.length-2]=="9"){
                $("input[name='newMemSex']").val("男")
            }else if(spstr[spstr.length-2]=="2"||spstr[spstr.length-2]=="4"||spstr[spstr.length-2]=="6"||spstr[spstr.length-2]=="8"||spstr[spstr.length-2]=="0"){
                $("input[name='newMemSex']").val("女")
            }
            $.post("../web/api/ucoingrand/isNew",{
                "idCard" : $(this).val()
            },function(data){
                if( data == true ){

                }
                else{
                    $(_this).focus();
                    $(_this).next().html("该用户已存在");
                }
            },'json');
        }
        else{
            $(this).focus();
            $(this).next().html("请输入正确的身份证号码")
        }
    })
    //开通新用户
    /**
     * 增加新用户。
     *
     * @param idCard     身份证号
     * @param name       姓名
     * @param linkPhone  联系电话
     * @param provinceId 省份ID
     * @param cityId     城市ID
     * @param districtId 区县ID
     * @param addr       详细地址
     * @param managerNo  客户经理号
     * @param remark     备注
     */
    $("#newMenberConfirm").click(function(){
        console.log('asd');
        var idCard = $("input[name='newMemId']").val();
        var name = $("input[name='newMemName']").val();
        var linkPhone = $("input[name='newMemTel']").val();
        var provinceId = $("#newMemAddressP").val();
        var cityId = $("#newMemAddressC").val();
        var districtId = $("#newMemAddressD").val();
        var addr = $("input[name='newMemberRoadDetail']").val();
        var managerNo = $("input[name='newMemManName']").val();
        var remark = $("textarea[name='newMemNote']").val();
        var tagList_create = '[';
        var arr = $(".tagSpan input[type='checkbox']:checked");
        var str =/^[0-9]*[1-9][0-9]*$/;
        var imgUrl=$("#newImg").prop("src");
        for(var i = 0; i < arr.length;i++){
            if($(arr[i]).prop("checked")){
                tagList_create += $(arr[i]).val() + ',';
            }
        }
        tagList_create = tagList_create.substring(0,(tagList_create.length-1));
        tagList_create += ']';
        if(tagList_create.length <= 2){
            tagList_create = '';
        }
        var pass=IdentityCodeValid(idCard);
        if( pass ){
            console.log('success');
            if( idCard!=""&& name!=""&&linkPhone!=""&&provinceId&&cityId&&districtId&&addr){
                if( str.test(linkPhone) &&  linkPhone.length == 11 ){
                    $.post("../web/api/customer/newCustomer",{
                        'idCard':idCard,
                        'name':name,
                        'linkPhone':linkPhone,
                        'provinceId':provinceId,
                        'cityId':cityId,
                        'districtId':districtId,
                        'addr':addr,
                        'managerNo':managerNo,
                        'remark':remark,
                        'tags':tagList_create,
                        'imgUrl':imgUrl
                    },function(data){
                        if( data.response == 'success' ){
                            $(parent.document.getElementById("cover")).hide();
                            $(parent.document.getElementById("cover2")).hide();
                            $("#cover1").hide();
                            if(window.localStorage){
                                localStorage['memberManager_provinceId'] = provinceId;
                                localStorage['memberManager_cityId'] = cityId;
                                localStorage['memberManager_districtId'] = districtId;
                            }else{
                                response_ensure_alert("error","浏览器版本太低，不支持保存地址功能",function(){
                                    console.log(consoleNowTime());
                                });
                            }

                            ensure_alert('memberManager')
                        }
                        else{

                        }
                    },'json')
                }else{
                    data_type_alert("请输入正确的电话号码","error");
                }
            }else{
                data_type_alert("请完善会员信息","error");
            }
        }else{
            response_ensure_alert("warning","身份证校验错误");
        }

    });

    //编辑用户
    $("#updateMemberConfirm").click(function(){
        var userId = $("#userId").val();
        var name = $('input[name="updateMemberName"]').val();
        var linkPhone = $('input[name="updateMemberTel"]').val();
        var provinceId = $('#updateMemAddressP').val();
        var cityId = $('#updateMemAddressC').val();
        var districtId = $('#updateMemAddressD').val();
        var addr = $('input[name="updateMemberRoadDetail"]').val();
        var managerNo = $('input[name="updateMemberManName"]').val();
        var remark = $('textarea[name="updateMemberNote"]').val();
        var str =/^[0-9]*[1-9][0-9]*$/;
        var tag_update = '[';
        var arr = $(".tagLi .tag:checked");
        var imgUrl=$("#imgShow").prop("src");
        for(var i = 0; i < arr.length;i++){
            if($(arr[i]).prop("checked")){
                tag_update += $(arr[i]).val() + ',';
            }
        }
        tag_update = tag_update.substring(0,(tag_update.length-1));
        tag_update += ']';
        if(tag_update.length <= 2){
            tag_update = '';
        }
        if(str.test(linkPhone) && linkPhone.length == 11 ){
        $.post("../web/api/customer/editCustomer",{
            'id':userId,
            'name':name ,
            'linkPhone':linkPhone ,
            'provinceId':provinceId ,
            'cityId':cityId ,
            'districtId':districtId ,
            'addr':addr ,
            'managerNo':managerNo ,
            'remark':remark,
            'tags':tag_update,
            "imgUrl":imgUrl,
            'remark':remark
        },function(data){
            if(data.response == 'success'){
                discoverHtml()
                response_ensure_alert("success","修改成功",function(){
                    window.location.href = 'memberManager';
                })

            }
            else{
                data_type_alert(data.data.text,"error");
            }
        });
        }else{
            data_type_alert("请输入正确的手机号","error")
        }
    });
    //激活账户
    //1.密码比对
    var password = '';
    $("input[name='password']").keyup(function(){
        if($.trim($(this).val()) == ''){
            $(this).next().html("请输入密码");
        }
        else{
            $(this).next().html("");
            password = $(this).val();
        }

    });
    $("input[name='passwordConfirm']").keyup(function(){

        if($(this).val() == password){
            $(this).next().html("");
            $("#resetPasswordConfirm").prop("disabled","").css("background","#54a6de");
        }
        else{
            $("#resetPasswordConfirm").prop("disabled","disabled").css("background","#999");
        }
    });
    //2.提交
    $("#activeAccoutConfirm").click(function(){
        var customerId = $("#userId").val();
        var password2=$("#activepassword").val()
        var confirm = $("#active_passwordConfirm").val();
        if(password2 == confirm){
            $.post("../web/api/customer/activeCustomer",{
                customerId:customerId,
                password:confirm
            },function(data){
                if(data.response == 'success'){
                    $(parent.document.getElementById("cover")).hide();
                    $(parent.document.getElementById("cover2")).hide();
                    $("#cover1").hide();
                    window.location.href = 'memberManager';
                }
                else{
                    data_type_alert(data.data.text,"error")
                }
            },'json');
        }else{
            data_type_alert("两次密码输入不一致","error")
        }
    });
    //邮豆扣减
    //金额验证
    $("#ucoin_reduce_amount").blur(function(){
        var val = $(this).val();
        var str =  /^\d+(\.\d+)?$/;
        if( str.test( val ) ){

        }
        else{
            if ( val == ''|| val == undefined ){
                data_error_alert("输入不能为空");
            }
            else{
                data_error_alert("请输入正确的数字");
            }
        }
    });
    $("#ucoinReduceConfrim").click(function(){
        var customerId = $("#userId").val();
        var amount = $("#ucoin_reduce_amount").val();
        var remark = $("#ucoin_reduce_remark").val();
        var paykey = $("#ucoin_reduce_paykey").val();
        $.post("../web/api/customer/ucoinDeduct",{
            customerId:customerId,
            amount:amount,
            remark:remark,
            paykey:paykey
        },function(data){
            if(data.response == 'success'){
                ensure_alert("memberManager");
                $(".memberDetail").fadeIn();
            }
            else{
                data_error_alert(data.data.text);
            }
        });
    });
    $("#UcoinSend").click(function(){
        var Idcard = $("input[name='menberId']").val();
        discoverHtml();
        //frame操作
        $(window.parent.frames['mainleft'].document).find(".clickOn").removeClass("clickOn");
        $(window.parent.frames['mainleft'].document).find("#uba").addClass("clickOn");

        window.location.href = "oldUser?idCard=" + Idcard;
    });
    //新建会员上传图片
    $("input[name='newMemPhoto']").click(function(){
        var updateInterval = false;
        var domId = "newMemPhoto";
        var _this = this;
        var imgUploader = setInterval(function(){
            if( updateInterval == false && $(_this).val() != '' ){
                if( $("input[name='newMemPhoto']").val() ){
                    updateInterval = UpladFile(imgUploader,domId);
                }
            }
        },500);
    });
    //更新会员上传图片
    $("input[name='updateMemberPhoto']").click(function(){
        var updateInterval = false;
        var domId = "updateMemberPhoto";
        var _this = this;
        var imgUploader = setInterval(function(){
            if( updateInterval == false && $(_this).val() != '' ){
                if( $("input[name='updateMemberPhoto']").val() ){
                    updateInterval = UpladFile(imgUploader,domId);
                }
            }
        },500);
    });
    //搜索
    $("#accSearch").click(function(){
        idCard_search = $("input[name='memberNum']").val();
        linkPhone_search = $("input[name='memberPhone']").val();
        name_search = $("input[name='memberName']").val();
        isActive_search = isOrnot($("dt[name='active']").html());
        isPhoneChecked_search = isOrnot($("dt[name='phone']").html());
        managerNo_search = $("input[name='memberUserName']").val();
        enterpriseId = $(".enterpriseIdChoosen").val();
        tagId = ( $("dt[name='tag']").html() == '全部' ) ?  '' : $("dt[name='tag']").html();
        //var tagId = ( $("dt[name='tag']").html() == '全部' ) ?  '' : $("dt[name='tag']").html();
        var array = [];
        array[0] = idCard_search;
        array[1] = linkPhone_search;
        array[2] = name_search;
        array[3] = isActive_search;
        array[4] = isPhoneChecked_search;
        array[5] = managerNo_search;
        array[6] = tagId;
        array[7] = enterpriseId;
        ajaxPages('../web/api/customer/getCustomers','itemContainer','holder',0,5,array);
    });


    //导出会员
    $("#memberOut").click(function(){
        var searchStr = '';
        if( idCard_search ){
            searchStr += 'idCard=' + idCard_search + '&';
        }
        if( name_search ){
            searchStr += 'name=' + name_search + '&';
        }
        if( linkPhone_search ){
            searchStr += 'linkPhone=' + linkPhone_search + '&';
        }
        if( isActive_search ){
            searchStr += 'isActive=' + isActive_search + '&';
        }
        if( isPhoneChecked_search ){
            searchStr += 'isPhoneChecked=' + isPhoneChecked_search + '&';
        }
        if( managerNo_search ){
            searchStr += 'managerNo=' + managerNo_search + '&';
        }
        if( tagId ){
            searchStr += 'tag=' + tagId + '&';
        }
        if( enterpriseId ){
            searchStr += 'enterpriseId=' + enterpriseId;
        }
        console.log(searchStr);
        window.location.href = '../web/api/exportExcel/customerInfoDown?' + searchStr;
    });
});
function UpladFile(interval,domId) {
    var fileObj = document.getElementById(domId).files[0]; // 获取文件对象
    var FileController = "../web/uploadPicture";                    // 接收上传文件的后台地址
    var data = [];
    // FormData 对象
    var form = new FormData();// 可以增加表单数据
    form.append("file", fileObj);                           // 文件对象
    var xhr = new XMLHttpRequest();
    xhr.open("POST", FileController, true);
    // XMLHttpRequest 对象
    xhr.send(form);
    xhr.onreadystatechange = function(){
        if(xhr.readyState == 4 && xhr.status == 200){
            str = xhr.responseText;
            data = JSON.parse(str);
            if (data.response == 'success'){
                $("#" + domId).prev().prop("src",data.data);
                $("#" + domId).val("");
                return true;
            }
            else{
                response_ensure_alert('error',data.data.text,function(){
                    console.log("上传失败" + consoleNowTime());
                    $("#" + domId).val("");
                });
                return false;
            }
        }
        else if( xhr.status == 400 ){
            response_ensure_alert("error","上传失败",function(){
                console.log("上传失败" + consoleNowTime());
                $("#" + domId).val("");
                return false;
            });
        }
        clearInterval(interval);
    };
}
function isOrnot(str){
    if( str == '是' ){
        return true;
    }
    else if ( str == '否' ){
        return false;
    }
    else if ( str == '全部' ){
        return '';
    }
}
function ajaxPages(url,contentStr,target,type,perpage,array){
    var idCard_search = array[0];
    var linkPhone_search = array[1];
    var name_search = array[2];
    var isActive_search = array[3];
    var isPhoneChecked_search = array[4];
    var managerNo_search = array[5];
    var tagId = array[6];
    var enterpriseId = array[7];
    $.post(url,{
        "idCard":idCard_search,
        "linkPhone":linkPhone_search,
        "name":name_search,
        "isActive":isActive_search,
        "isPhoneChecked":isPhoneChecked_search,
        "managerNo":managerNo_search,
        "tag":tagId,
        "enterpriseId":enterpriseId,
        "page":1,
        "size":5
    },function(data){
        $("#" + contentStr).empty();
        if(data.response == 'success'){
            var total = data.data.totalElements;
            for(var i = 0;i < total;i++){
                var html = get_html(type);
                $("#"+contentStr).append(html);
            }
            jPagesGet(target,contentStr);
            if( total < perpage ){
                appendHtml(contentStr,1,total,data.data.content,total);
            }
            else{
                appendHtml(contentStr,1,total,data.data.content,perpage);
            }
            //处理多余页
            //处理多余页
            var pageLength = $("#" + target + " a").length;
            for( var i = 2;i < (pageLength - 2);i++ ){
                var showElement = ( pageLength - 2 ) * perpage ;
                if( showElement >= ( data.data.totalElements + perpage ) ){
                    $( $("#" + target + " a")[pageLength - i]).remove();
                }
            }
            $("#" + target).find("a").css("display","inline-block");
            $("#" + target).find("span").css("display","inline-block");
            $("#" + target).find(".jp-hidden").css("display","none");

            $("#" + target + " a").unbind("click");
            $("#" + target + ' a').click(function(){
                    var page = '';
                    if( $(this).html() == '＜' ){
                        if( parseInt($(".jp-current").html()) > 1 ){
                            page = parseInt($(".jp-current").html()) - 1;
                        }
                        else{
                            page = parseInt($(".jp-current").html());
                        }
                    }
                    else if( $(this).html() == '＞' ){
                        if( parseInt($(".jp-current").html()) < ( $("#holder a").length -2 ) ){
                            page = parseInt($(".jp-current").html()) + 1;
                        }
                        else{
                            page = parseInt($(".jp-current").html());
                        }
                    }
                    else{
                        page = $(this).html();
                    }

                    var idCard_search_a = array[0];
                    var linkPhone_search_a = array[1];
                    var name_search_a = array[2];
                    var isActive_search_a = array[3];
                    var isPhoneChecked_search_a = array[4];
                    var managerNo_search_a = array[5];
                    var tagId = array[6];
                    var enterpriseId = array[7];
                    $.post(url,{
                        "idCard":idCard_search_a,
                        "linkPhone":linkPhone_search_a,
                        "name":name_search_a,
                        "isActive":isActive_search_a,
                        "isPhoneChecked":isPhoneChecked_search_a,
                        "managerNo":managerNo_search_a,
                        "tag":tagId,
                        "enterpriseIf":enterpriseId,
                        "page":page,
                        "size":perpage
                    },function(data){
                        if(data.response == 'success'){
                            var total = data.data.totalElements;
                            for(var i = 0;i < total;i++){
                                var html = get_html(type);
                                $("#"+contentStr).append(html);
                            }
                            appendHtml(contentStr,page,total,data.data.content,perpage);
                        }
                    },'json');




                //处理页数
                if( page > 4 ){
                    var length = $("#holder a").length;
                    console.log(length);
                    if( page == length-2 ){
                        $( $("#holder a")[page] ).prev().css("display","inline-block");
                        $( $("#holder a")[page] ).prev().prev().css("display","inline-block");
                        $( $("#holder a")[page] ).prev().prev().prev().css("display","inline-block");
                        $( $("#holder a")[page] ).prev().prev().prev().prev().css("display","inline-block");

                    }
                    else{
                        $( $("#holder a")[page] ).next().css("display","inline-block");
                        $( $("#holder a")[page] ).prev().css("display","inline-block");
                    }

                }
            });
        }
    },'json');

}
function get_html(type){
    var html = '';
        html = "<dd>";
        //html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /><input type="hidden" /></abbr>';
        html += '<abbr style="width:180px;" class="memId"></abbr>';
        html += '<abbr style="width:150px;" class="memfullname"></abbr>';
        html += '<abbr class="memSex"></abbr>';
        html += '<abbr class="memTel"></abbr>';
        html += '<abbr class="memTelBinding"></abbr>';
        html += '<abbr class="memUcoin"></abbr>';
        html += '<abbr class="memActive"></abbr>';
        html += '<abbr class="memManagerId"></abbr>';
        html += '<abbr style="width:200px;" class="memTab"></abbr>';
        html += '<abbr class="memLastTime"></abbr>';
        html += '<abbr style="width:300px;">';
        html += '<ul class="select2"> <i value="0" class="arrow2"></i>';
        html += '<li></li>';
        html += '<section></section>';
        html += '</ul>';
        html += '</dd>';
    return html;
}
function appendHtml(str,pageIndex,totalPage,data,perpage){
    $("#"+ str).empty();
    for(var i=0;i<perpage;i++){
        var idcardNo = '';
        var fullname = '';
        var male = '';
        var phoneNo = '';
        var mobileChecked = '';
        var isActive = '';
        var managerNo = '';
        var tag = '';
        if(typeof(data[i].idcardNo) != 'undefined'){
            idcardNo = data[i].idcardNo;
        }
        if(typeof(data[i].fullname) != 'undefined'){
            fullname = data[i].fullname;
        }
        if(typeof(data[i].male) != 'undefined'){
            male = data[i].male;
            if( male == true ){
                male = '男';
            }
            else if( male == false ){
                male = '女';
            }
        }
        if(typeof(data[i].contactPhone) != 'undefined'){
            phoneNo = data[i].contactPhone;
        }
        if(typeof(data[i].mobileChecked)!= undefined){
            mobileChecked = data[i].mobileChecked;
            if( mobileChecked == true ){
                mobileChecked = '已绑定';
            }
            else if( mobileChecked == false ){
                mobileChecked = '未绑定';
            }
        }
        if(typeof(data[i].isActive) != 'undefined'){
            isActive = data[i].isActive;
            if( isActive == true ){
                isActive = '已激活';
            }
            else if( isActive == false ){
                isActive = '未激活';
            }
        }
        if(typeof(data[i].managerNo) != 'undefined'){
            managerNo = data[i].managerNo;
        }
        if(typeof(data[i].tag) != 'undefined'){
            tag = data[i].tag;
        }
        var html = '<dd><input type="hidden" value = "' + data[i].id + '"/>';
            //html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /></abbr>';
            html += '<abbr style="width:180px;" class="memId">' + idcardNo + '</abbr>';
            html += '<abbr style="width:115px;" class="memfullname">' + fullname + '</abbr>';
            spstr2 = idcardNo.split("");
            if(spstr2[spstr2.length-2]=="1"||spstr2[spstr2.length-2]=="3"||spstr2[spstr2.length-2]=="5"||spstr2[spstr2.length-2]=="7"||spstr2[spstr2.length-2]=="9"){
                html += '<abbr class="memSex">男</abbr>';
            }else if(spstr2[spstr2.length-2]=="2"||spstr2[spstr2.length-2]=="4"||spstr2[spstr2.length-2]=="6"||spstr2[spstr2.length-2]=="8"||spstr2[spstr2.length-2]=="0"){
                html += '<abbr class="memSex">女</abbr>';
            }
            var lastLoginDate = '';
            var createTime = '';
            if( handleUndefined(data[i].lastLoginTime) != '' ){
                lastLoginDate = handleDate_prev(new Date(data[i].lastLoginTime)) + "  " + handleDate_next(new Date(data[i].lastLoginTime));
            }
            if( handleUndefined(data[i].createTime) != '' ){
                createTime = handleDate_prev(new Date(data[i].createTime)) + "  " + handleDate_next(new Date(data[i].createTime));
            }
            html += '<abbr class="memTel">' + handleUndefined(phoneNo) + '</abbr>';
            html += '<abbr class="memTelBinding">' + handleUndefined(mobileChecked) + '</abbr>';
            html += '<abbr class="memUcoin">' + handleUndefined(data[i].totalUcoin) + '</abbr>';
            html += '<abbr class="memActive">' + handleUndefined(isActive) + '</abbr>';
            html += '<abbr class="memManagerId">' + handleUndefined(managerNo) + '</abbr>';
            html += "<input class='memDeliver' value='" + JSON.stringify(data[i].deliverInfos) + "' type='hidden'>";
            html += "<input class='memCreator' value='" + handleUndefined(data[i].accountName) + "' type='hidden'>";
            html += "<input class='memCreateTime' value='" + createTime + "' type='hidden'>";
            html += "<input class='lastLoginTime' value='" + lastLoginDate + "' type='hidden'>";
            html += '<input class="memPId" value="' + handleUndefined(data[i].provinceId) + '" type="hidden">';
            html += '<input class="memCId" value="' + handleUndefined(data[i].cityId) + '" type="hidden">';
            html += '<input class="memDId" value="' + handleUndefined(data[i].districtId) + '" type="hidden">';
            html += '<input class="memRId" value="' + handleUndefined(data[i].streetId) + '" type="hidden">';
            html += '<input class="enterpriseUcoin" value="' + handleUndefined(data[i].enterpriseUcoin) + '" type="hidden">';
            html += '<input class="memDetailAddress" value="' + handleUndefined(data[i].contactAddr) + '" type="hidden">';
            html += '<input class="img" value="' + handleUndefined(data[i].imgUrl) + '" type="hidden">';
            html += '<input class="detailaddr" value="' + handleUndefined(data[i].addr) + '" type="hidden">';
            html += '<input class="remark" value="' + handleUndefined(data[i].remark) + '" type="hidden">';
            html += '<abbr class="memTag">';
            if(data[i].tags != ''){
                data[i].tags.map(function(object){
                    html += "<abbr class='memTab' value='" + object.tagId + "' title='" + object.tagDesc + "' >" + object.tagName + "</abbr>,";
                });
                html = html.substring(0,html.length-1);
            }
            else{
                html += '<abbr class="memTab">' + '无' + '</abbr>';
            }
            html += '</abbr>';
            //var lastLoginTime = data[i].lastLoginTime;
            //if( lastLoginTime != undefined ){
            html += '<abbr style="width: 300px;" class="memLastTime">' + handleUndefined(data[i].contactAddr) + '</abbr>';
            //}
            //else{
            //    html += '<abbr class="memLastTime"></abbr>';
            //}
            html += '<abbr style="width:300px;">';
            html += '<ul class="select2"> <i value="0" class="arrow2"></i>';
            html += '<li onclick="coverHtml()" class="checkDateil allSelectButton">查看详情</li>';
            if( isActive != '已激活' ){
                html += '<section onclick="coverHtml()" class="activeAcc allSelectButton">激活账户</section>';
            }
            else if ( isActive == '已激活' ){
                html += '<section onclick="coverHtml()" class="resetPword allSelectButton">重置密码</section>';
            }
            html += '</ul>';
        $("#"+ str).append(html);
    }

}
function checkUndefined(str){
    if( str == 'undefined' ){
        str = '';
    }
    return str;
}
function jPagesGet(content,target){
    $("#"+content).jPages({
        containerID : target
    });

}
function UpdateMemAddressP(callback,pid){
    $("#updateMemAddressC option[value!='0'],#updateMemAddressD option[value!='0'],#updateMemAddressR option[value!='0']").remove()
    $.post("../web/api/common/cities",{
        "provinceId":pid
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.cityId +'">';
                html += object.cityName;
                html += '</option>';
                $("#updateMemAddressC").append(html);
            });
            callback();
        }
    },'json');
}
function UpdateMemAddressC(callback,cid){
    $("#updateMemAddressD option[value!='0'],#updateMemAddressR option[value!='0']").remove()
    $.post("../web/api/common/districts",{
        "cityId":cid
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.districtId +'">';
                html += object.districtName;
                html += '</option>';
                $("#updateMemAddressD").append(html);
            });
            callback();
        }
    },'json');
}
function UpdateMemAddressD(callback,did){
    $("#updateMemAddressR option[value!='0']").remove();
    $.post("../web/api/common/streets",{
        "districtId":did
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.streetId +'">';
                html += object.streetName;
                html += '</option>';
                $("#updateMemAddressR").append(html);
            });
            callback();
        }
    },'json');
}
function newMemAddressP(callback,pid){
    $("#newMemAddressC option[value!='0'],#newMemAddressD option[value!='0'],#newMemAddressR option[value!='0']").remove()
    $.post("../web/api/common/cities",{
        "provinceId":pid
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.cityId +'">';
                html += object.cityName;
                html += '</option>';
                $("#newMemAddressC").append(html);
            });
            callback();
        }
    },'json');
}
function newMemAddressC(callback,cid){
    $("#newMemAddressD option[value!='0'],#newMemAddressR option[value!='0']").remove()
    $.post("../web/api/common/districts",{
        "cityId":cid
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.districtId +'">';
                html += object.districtName;
                html += '</option>';
                $("#newMemAddressD").append(html);
            });
            callback();
        }
    },'json');
}
function newMemAddressD(callback,did){
    $("#newMemAddressR option[value!='0']").remove();
    $.post("../web/api/common/streets",{
        "districtId":did
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.streetId +'">';
                html += object.streetName;
                html += '</option>';
                $("#newMemAddressR").append(html);
            });
            callback();
        }
    },'json');
}