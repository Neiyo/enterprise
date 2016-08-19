$(document).ready(function(){
    var flag = false;
    $(".tab1").click(function(){
        $(this).attr("class","tab1 on");
        $(".tab2").attr("class","tab2");
        $(".tabs_content").css("display","block");
        $(".post").css("display","none");
        if( flag ){
            $("#order_submit").removeAttr("disabled");
        }
        else{
            $("#order_submit").attr("disabled","disabled");
        }
    });
    $(document).on("click",".deleteAdd",function(){
        var addressId=$(this).siblings().children(".addressId").val();
        response_OkCancel_alert("warning","是否删除地址",function(){
            $.post("../web/api/valet/deleteShippingAddress",{
                addressId:addressId
            },function(data){
                if(data.response=="success"){
                    response_ensure_alert("success","删除成功",function(){
                        $("input[value='" + addressId + "']").parent().parent(".hidden_address").remove()
                    })
                }else{
                    data_type_alert(data.data.text,"error")
                }
            },'json')

        },function(){

        })
    })
    $(".tab2").click(function(){
        $(this).attr("class","tab2 on");
        $(".tab1").attr("class","tab1");
        $(".tabs_content").css("display","none");
        $(".tabs_content").after().css("display","block");
        if( $("#defaultAddressId").val() != '' ){
            $("#order_submit").removeAttr("disabled");
        }
        else{
            $("#order_submit").attr("disabled","disabled");
        }
    });

    $("#checkUser").on('input',function(){
        var userId = $("#checkUser").val();
        var checkNum = /^\d{17}[xX0-9]$/;
        if( checkNum.test(userId) && userId.length == 18 ){
            $("#tixing").html("")
            $.post("../web/api/common/isNew",{
                idCard:userId
            },function(data){
                if(data.response == 'success'){
                    if(data.data == false){
                        flag = true;
                        $.post("../web/api/valet/getCustomerInfo",{
                            idCard:userId
                        },function( data ){
                            if( data.response == 'success' ){
                                var fullname = '';
                                var phoneNo = '';
                                var totalUcoin = '';
                                var customerId = data.data.id;
                                if ( data.data.fullname != undefined ){
                                    fullname = data.data.fullname;
                                }
                                if ( data.data.contactPhone != undefined ){
                                    phoneNo = data.data.contactPhone;
                                }
                                if ( data.data.enterpriseUcoin != undefined ){
                                    totalUcoin = data.data.enterpriseUcoin;
                                }
                                $("#line_1").html( userId );
                                $("#userImg").prop("src",data.data.imgUrl)
                                $("#line_2").html( fullname + '&nbsp;&nbsp;&nbsp;' + phoneNo);
                                $("#line_3 b").html( totalUcoin );
                                $("#customerId").val( customerId );

                                $("#userInfo").fadeIn(500);
                                $.post("../web/api/valet/getAddresses",{
                                       customerId:customerId
                                   },function( data ){
                                       if( data.response == 'success' ){
                                           $(".address_fullname").html(fullname);
                                           try {
                                               //$("#address_province").html(data.data[0].addressName);
                                               $("#address_address").html(data.data[0].province.provinceName + data.data[0].city.cityName + data.data[0].district.districtName + data.data[0].addressDetail);
                                               $("#defaultAddressId").val(data.data[0].addressId);
                                               $("#address_mobile").html(data.data[0].addressMoblie);
                                           }
                                           catch (e) {
                                               if($(".on").html() == '物流配送') {
                                                   $("#address_address").html("暂无收货地址 请重新添加");
                                                   $("#address_mobile").html("");
                                                   $("#defaultUserName").html("");
                                                   $("#tixing").html("缺少收货地址或者手机，请先完善你的资料");
                                               }
                                           }
                                           finally{
                                               if($(".on").html() == '物流配送') {
                                                   $(".tab2").trigger("click");
                                               }
                                               if($(".on").html() == '网点自提') {
                                                   $(".tab1").trigger("click");
                                               }
                                           }
                                           var html = '';
                                           for (var i = 1; i < data.data.length; i++) {
                                               var fullAddress = '';

                                               html += "<div class='hidden_address'>";
                                               html += "<abbr>";
                                               html += '<input type="hidden" value="' + data.data[i].addressId + '" class="addressId">';
                                               html += '<span style="margin-left:30px;" class="address_fullname">' + data.data[i].addressName + '</span>';
                                               try{
                                                   fullAddress += data.data[i].province.provinceName;
                                               }
                                               catch(e){

                                               }
                                               try{
                                                   fullAddress += data.data[i].city.cityName;
                                               }
                                               catch(e){

                                               }
                                               try{
                                                   fullAddress += data.data[i].district.districtName;
                                               }
                                               catch(e){

                                               }
                                               try{
                                                   fullAddress += data.data[i].addressDetail;
                                               }
                                               catch(e){

                                               }
                                               html += '<span style="margin-left:30px;" class="address_address">' + fullAddress + '</span>';
                                               html += '<span style="margin-left:30px;" class="address_mobile">' + data.data[i].addressMoblie + '</span>';
                                               html += "</abbr>";
                                               html += '<span style="color:#24b35f;margin-left:30px;" class="deleteAdd">删除</span>';
                                               html += "</div>";
                                           }
                                           $(".hidden_address").remove();
                                           $("#container").append(html);
                                           $("#order_submit").css("background-color", "#24b35f");
                                           //if($(".on").html() == '物流配送'){
                                           //    $(".tab1").trigger("click");
                                           //}
                                           //else if( $(".on").html() == '网点自提' ){
                                           //    $(".tab2").trigger("click");
                                           //}
                                       }
                                       else {
                                           $("#tixing").html("请先完善你的资料");
                                           if($(".on").html() == '物流配送'){
                                               $(".tab1").trigger("click");
                                           }
                                           else if( $(".on").html() == '网点自提' ){
                                               $(".tab2").trigger("click");
                                           }
                                       }
                                   },'json');
                                if(!data.data.isActive && $(".on").html()=="物流配送"){
                                    $("#tixing").html("该用户未激活无法发物流单，请选择网点自提")
                                    $("#order_submit").css("background-color", "#bbbbbb").prop("disabled","disabled");
                                }
                            }
                            else{
                                user_error_alert('OrderSure')
                            }
                        },'json');
                    }
                    else{
                        flag = false;
                        $("#tixing").html("该用户不存在,请重新输入")
                        $("#order_submit").css("background-color", "#bbbbbb").prop("disabled","disabled");
                    }
                }
                else{
                    $("#tixing").html("该用户不存在,请重新输入")
                    $("#order_submit").css("background-color", "#bbbbbb").prop("disabled","disabled");
                }
            },'json');
        }
        else{
            $("#tixing").html("请输入正确的身份证号")
            $("#order_submit").css("background-color", "#bbbbbb").prop("disabled","disabled");
        }

    });

    $("#moreAddress").click(function(){
       $(".hidden_address").toggle();
    });

    $("#addAddress").click(function(){
        coverHtml();
        $(".newAddress").fadeIn(500);
    });

    $(".newAddress i").click(function(){
        discoverHtml();
        $(".newAddress").fadeOut(500);
    });

    $.post("../web/api/valet/getEnterpriseInfo",{},function(data){
        if( data.response == 'success' ){
            $("#enterpriseName").html( data.data.enterpriseName );
            $("#enterpriseAddress").html( data.data.address );
        }
        else{

        }
    },'json');

    $(document).on("click",".hidden_address abbr",function(){
        var defaultUserName = $(this).find(".address_fullname").html();
        var address_address = $(this).find(".address_address").html();
        var address_mobile = $(this).find(".address_mobile").html();
        var defaultAddressId = $(this).find(".addressId").val();
        var beforedefaultUserName=$("#defaultUserName").html();
        var beforeaddress_address = $("#address_address").html();
        var beforeaddress_mobile = $("#address_mobile").html();
        var beforedefaultAddressId = $("#defaultAddressId").val();
        $("#defaultUserName").html(defaultUserName);
        $("#address_address").html(address_address);
        $("#address_mobile").html(address_mobile);
        $("#defaultAddressId").val(defaultAddressId);
        $(this).find(".address_fullname").html(beforedefaultUserName);
        $(this).find(".address_address").html(beforeaddress_address);
        $(this).find(".address_mobile").html(beforeaddress_mobile);
        $(this).find(".addressId").val(beforedefaultAddressId);
        $("#moreAddress").trigger("click");
    });

    //提交订单
    $("#order_submit").click(function(){
        var customerId = $("#customerId").val();
        var isOnline = '';
        var idCard = $("#checkUser").val();
        if( $(".on").html()=="网点自提" ){
            isOnline = false;
        }
        if( $(".on").html()=="物流配送" ){
            isOnline = true;
        }
        var addressId = $("#defaultAddressId").val();
        var totalNum=parseFloat($("#line_3 b").html());
        var payNum=parseFloat($(".sumPrice").html());
        if(totalNum>=payNum){
        $.post("../web/api/valet/placeOrder",{
            customerId:customerId,
            isOnline:isOnline,
            addressId:addressId,
            goodsId:goodsId,
            count:count
        },function(data){
            if( data.response == 'success' ){
                response_ensure_alert('success','订单提交成功',function(){
                    var url = 'payConfirm?payOrder=' + data.data.orderCode + '&orderPrice=' + data.data.orderPrice + '&idCard=' + idCard;

                    window.location.href = url;
                });
            }
            else{
                response_ensure_alert("error",data.data.text,function(){})
            }
        },'json');
        }
        else{
            data_type_alert("账户邮豆余额不足，无法完成支付","error")
        }
    });

    //保存收货地址
    $("#saveAdd").click(function(){
        var customerId = $("#customerId").val();
        var addressName = $("input[name='adName']").val();
        var addressDetail = $("input[name='adAddress']").val();
        var addressMoblie = $("input[name='adCell']").val();
        var isDefault = 0;
        var infoProvince = $(".newAddressP option:selected").val();
        var infoCity = $(".newAddressC option:selected").val();
        var infoCounty = $(".newAddressD option:selected").val();
        var infoStreet = $(".newAddressR option:selected").val();

        var nameProvince = $(".newAddressP option:selected").html();
        var nameCity = $(".newAddressC option:selected").html();
        var nameCounty = $(".newAddressD option:selected").html();
        var nameStreet = $(".newAddressR option:selected").html();

        if( nameProvince == '请选择省份' ){
            nameProvince = '';
        }
        if( nameCity == '请选择城市' ){
            nameCity = '';
        }
        if( nameCounty == '请选择区县' ){
            nameCounty = '';
        }
        if( nameStreet == '请选择街道' ){
            nameStreet = '';
        }

        var addressZip = $("input[name='adCell']").val();
        var addressPhone = $("input[name='adTel']").val();
        if(addressName && infoProvince && infoCity && infoCounty && addressDetail && addressMoblie){
        $.post("../web/api/valet/addAddress",{
            customerId:customerId,
            addressName:addressName,
            addressDetail:addressDetail,
            addressMoblie:addressMoblie,
            isDefault:isDefault,
            infoProvince:infoProvince,
            infoCity:infoCity,
            infoCounty:infoCounty,
            infoStreet:infoStreet,
            addressZip:addressZip,
            addressPhone:addressPhone
        },function(data){
            if( data.response == 'success' ){
                response_ensure_alert('success','添加成功',function(){
                    discoverHtml();
                    var html = '';
                    html += "<div class='hidden_address'>";
                    html += '<input type="hidden" value="' + data.data.addressId + '" class="addressId">';
                    html += '<span style="margin-left:30px;" class="address_fullname">' + data.data.addressName + '</span>';
                    html += '<span style="margin-left:30px;" class="address_address">' + nameProvince + nameCity + nameCounty + nameStreet + data.data.addressDetail + '</span>';
                    html += '<span style="margin-left:30px;" class="address_mobile">' + data.data.addressMoblie + '</span>';
                    html += "</div>";
                    $("#container").append(html);
                    $(".newAddress").fadeOut(500);
                    if(window.localStorage){
                        localStorage['addDeliverInfo_provinceId'] = infoProvince;
                        localStorage['addDeliverInfo_cityId'] = infoCity;
                        localStorage['addDeliverInfo_districtId'] = infoCounty;
                    }else{
                        response_ensure_alert("error","浏览器版本太低，不支持保存地址功能",function(){
                            console.log(consoleNowTime());
                        });
                    }
                    $("#checkUser").trigger("input");
                });
            }
            else{
                response_ensure_alert('error',data.data.text,function(){
                   console.log("收货地址新增失败" + new Date().toString());
                });
            }
        },'json');
        }else{
            data_type_alert("请完善您的收货地址","error")
        }
    });

    $(".tab1").click(function(){
        $("#yunfei").html("0");
        $("#tixing").html("");
        $(".sumPrice").html($("#itemPrice").html())
    })
    $(".tab2").click(function(){
        $("#yunfei").html("6250");
        if($("#defaultAddressId").val() == ""){
            $("#tixing").html("缺少资料，请先完善你的资料");
        }
        $(".sumPrice").html(parseFloat($("#itemPrice").html())+6250)
    });

});


function UpdateMemAddressP(callback,pid){
    $(".newAddressC option[value!='0'],.newAddressD option[value!='0'],.newAddressR option[value!='0']").remove();
    $.post("../web/api/common/cities",{
        "provinceId":pid
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.cityId +'">';
                html += object.cityName;
                html += '</option>';
                $(".newAddressC").append(html);
            });
            callback();
        }
    },'json');
}

function UpdateMemAddressC(callback,cid){
    $(".newAddressD option[value!='0'],.newAddressR option[value!='0']").remove();
    $.post("../web/api/common/districts",{
        "cityId":cid
    },function(data){
        if( data.response == 'success' ){
            data.data.map(function(object){
                var html = '';
                html += '<option value="' + object.districtId +'">';
                html += object.districtName;
                html += '</option>';
                $(".newAddressD").append(html);
            });
            callback();
        }
    },'json');
}