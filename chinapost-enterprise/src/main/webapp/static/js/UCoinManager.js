$(document).ready(function(e) {
    var keyUpLock = true;
    $("div.holder").jPages({
        containerID : "itemContainer"
    });


    $("#BudGo").click(function(){
        coverHtml();
        $(".Budget").fadeIn(500);
        $(".CashAssign").fadeOut(500);
        $(".CashPwd").fadeOut(500);
    })
    $(".Budget h1 i").click(function(){
        discoverHtml()
        $(".Budget").fadeOut(500);
    })
    $("#CashGo").click(function(){
        coverHtml()
        $(".CashAssign").fadeIn(500);
        $(".Budget").fadeOut(500);
        $(".CashPwd").fadeOut(500);
    })
    $(".CashAssign h1 i").click(function(){
        discoverHtml()
        $(".CashAssign").fadeOut(500);
    })
    $("#Cashsub").click(function(){
        var checkNum1 =/^\d+(\.\d+)?$/;
        val1=$("#checkval").val()
        var flag = true;
        var flag2 = true;

        $(".CashChkbox:checked").each(function(){
            var args = $(this).siblings(".Cashtext").val().split(".");
            if(args[1] == "" ){
                $(this).siblings(".Cashtext").val("");
                flag = false;
                flag2 = false;
            }else if(parseInt( $(this).siblings(".Cashtext").val()) < 0){
                $(this).siblings(".Cashtext").val("");
                flag = false;
                flag2 = false;
            }
        })
        if(flag2){
            if(flag) {
                if (checkNum1.test(val1)&&val1!=0) {
                    $(".CashAssign").fadeOut(500);
                    $("#CashPwd").val("");
                    $(".CashPwd").fadeIn(500);
                } else {
                    data_type_alert("金额不正确", "error")
                }
            }
        }else{
            data_type_alert("金额输入不正确","error")
        }

    })
    $("#CashCancel").click(function(){
        discoverHtml();
        $(".CashPwd").fadeOut(500);
    })
    $(".CashChkbox").click(function() {
        var temp = 0;
        $(".CashChkbox").each(function(){

            if($(this).is(':checked')) {
                temp = temp + Number($(this).siblings(".Cashtext").val());
            }
            $("#checkval").attr("value",temp);
        });
    })
    $(document).on("input",".Cashtext",function(){

    })
    $(".Cashtext").on("input",function(){
        var temp = 0;
        var _this = this;
        var checkNum =/^\+?(\d*\.\d{3})$/
        val=$(this).val()
        if(checkNum.test(val)){
            var s = $(_this).val();
            s= s.substring(0, s.length-1);
            $(_this).val(s);
            if( keyUpLock == true ){
                response_ensure_alert("error","请输入正确的数字",function(){
                    keyUpLock = true;
                    $(_this).removeAttr("disabled");

                });
                $(this).prop("disabled","disabled");
                keyUpLock = false;
            }
        }else{
            var checkval=$("#checkval").val();
            $(".CashChkbox").each(function(){

                if($(this).is(':checked')) {
                    temp = temp + Number($(this).siblings(".Cashtext").val());
                }
                $("#checkval").attr("value",temp);
            });
        }
    })
});

$(document).ready(function(){
    $("#Ucoinsub").click(function(){
        console.log(1);
        var value = $("#BudAmout").val();
        var reason = $("#BudReason").val();
        var args = value.split(".");
        var temp = ( args[1] == undefined ) ? '' : args[1];
        if( temp.length < 3 && args[1] !="" ){
            $.post("../web/api/wealth/requisition",{
                'amount': value,
                'reason': reason
            },function(data){
                if( data.response == 'success' ){
                    discoverHtml();
                    response_ensure_alert("success","申请预算成功",function(){
                        window.location.href = 'UCoinManager';
                    });
                }
                else{
                    data_type_alert(data.data.text,"error")
                }
            },'json');
        }else{
            data_type_alert("请输入正确的金额，必须是正整数或者2位小数","error")
        }
    });
    $("#CashPwdSub").click(function(){
        var paykey = $("#CashPwd").val();
        var str = '{';
        var length = $("#CashAssign li").length;
        var _this = this;
        for(var i = 0;i<length;i++){
            var id_dom = $("#CashAssign li input[type='checkbox']")[i];
            var value_dom = $("#CashAssign li input[type='text']")[i]
            if( id_dom.checked ){
                var id = id_dom.value;
                var value = value_dom.value;
                str += '"';
                str += id;
                str += '":';
                str += value;
                str += ',';
            }
        }
        str = str.substring(0,str.length-1);
        str += '}';
        console.log(str);
        $.post("../web/api/wealth/allocation",{
            'paykey':paykey ,
            'allocationJson':str
        },function( data ){
            if( data.response == 'success' ){
                $("#CashPwd").val("");
                discoverHtml();
                ensure_alert('UCoinManager');

            }
            else{
                $("#CashPwd").val("");
                data_type_alert(data.data.text,"error")
            }
        },'json');
    });

    //处理表格占比
    $(".percent").html(function(){
        var percent = '0%';
        var sonWealth = $(this).data("son");
        var totalWealth = $(this).data("total");
        var result =  (parseInt(sonWealth) / parseInt(totalWealth)).toFixed(9);
        result = result.substring(0,6);
        if( isNaN(result) ){
            return percent;
        }
        else{
            return (result*100).toFixed(2) + '%';
        }
    });
});


