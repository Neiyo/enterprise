$(document).ready(function(e) {
    var arr = [];
    arr[8] = '';
    arr[9] = '';
    arr[11] = '';
    arr[12] = '';
    arr[13] = '';
    arr[14] = '';

    //轮询刷新
    setInterval(function(){
        var action = localStorage["systemAction"];
        if( action == 'refresh' ){
            localStorage['systemAction'] = 'active';
            window.location.href = 'voucherManager';
        }
    },1000);

    $(".voucherTable ul li").click(function(){
        if( $(this).html() == '待办事宜' ){
            ajaxPages('../web/api/inventory/getUnHandleBills','itemContainer_notHandled','holder_notHandled','inventory_notHandled',5,'','',arr);
        }
        if( $(this).html() == '已办事宜' ){
            ajaxPages('../web/api/inventory/getHandledBills','itemContainer_done','holder_done','inventory_done',5,'','',arr);
        }
        if( $(this).html() == '我的请求' ){
            ajaxPages('../web/api/inventory/getCreatedBills','itemContainer_myRequest','holder_myRequest','inventory_myRequest',5,'','',arr);
        }
    });
    //搜索
    $("#voucherSearch").click(function(){
        var checked = $(".reqOn").html();
        var code = $("#voucherOrder").val();
        var creator = $("#voucherName").val();
        var start = $("#datetimepicker_start").val();
        var end = $("#datetimepicker_end").val();
        var billType = '';
        if( $("#type").html() != '全部' ){
            billType = $("#type").children().val();
        }
        var billStatus = '';
        if( $("#status").html() != '全部' ){
            billStatus = $("#status").children().val();
        }

        arr[8] = start;
        arr[9] = end;
        arr[11] = code;
        arr[12] = creator;
        arr[13] = billType;
        arr[14] = billStatus;

        if( start > end ){
            response_ensure_alert("error","结束时间不得小于开始时间");
        }
        else{
            if( checked == '待办事宜' ){
                ajaxPages('../web/api/inventory/getUnHandleBills','itemContainer_notHandled','holder_notHandled','inventory_notHandled',5,'','',arr);
            }
            else if( checked == '已办事宜' ){
                ajaxPages('../web/api/inventory/getHandledBills','itemContainer_done','holder_done','inventory_done',5,'','',arr);
            }
            else if( checked ==  '我的请求'){
                ajaxPages('../web/api/inventory/getCreatedBills','itemContainer_myRequest','holder_myRequest','inventory_myRequest',5,'','',arr);
            }
        }

    });


    $(".voucherTable ul li[value='0']").trigger("click");


    $("#date_start").click(function(e){
        $("#datetimepicker_start").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y'
        });
        $("#datetimepicker_start").trigger("focus");
    });
    $("#datetimepicker_start").blur(function(){
        $("#datetimepicker_start").datetimepicker('destroy');
    });
    $("#date_end").click(function(e){
        $("#datetimepicker_end").datetimepicker({
            step:5,
            lang:'ch',
            formatTime:'H:i',
            formatDate:'d.m.Y'
        });
        $("#datetimepicker_end").trigger("focus");
    });
    $("#datetimepicker_end").blur(function(){
        $("#datetimepicker_end").datetimepicker('destroy');
    });

    $(".voucherTable li").click(function(){
        var lival=$(this).val();
        $(this).addClass("reqOn").siblings("li").removeClass("reqOn");
        if(lival==0){
            //待办事宜
            $(".myVoucherTodo").show().siblings("dl").hide();
        }else if(lival==1){
            //已办事宜
            $(".myVoucherDone").show().siblings("dl").hide();
        }else if(lival==2){
            //我的请求
            $(".myVoucherRequest").show().siblings("dl").hide();
        }
    });
    $(document).on("click",".vocherCode",function(){
        var status = $(".reqOn").html();
        status = handleStatus(status)
        var vocherCode=$(this).siblings("input[type='hidden']").val();
        var vocherState=$(this).siblings(".vocherState").html();
       if(vocherState == "报损单" || vocherState == "报溢单" ){
           window.open("reimburse?id=" + vocherCode + "&status=" + status) ;
       }
        else if(vocherState == "补货单"  ){
            window.open("replenishment?id=" + vocherCode + "&status=" + status) ;
        }
       else if(vocherState == "调拨单"  ){
           window.open("allocation?id=" + vocherCode + "&status=" + status) ;
       }
    });
    $(".arrowType").click(function(){
        var arr=$(this).val();
        $(this).siblings("dd").slideToggle();
        if(arr==0){
            $(this).css("background","url(../static/img/com_btn_arrow_black_up.png) center no-repeat");
            $(this).val("1")
        }else if(arr==1){
            $(this).css("background","url(../static/img/com_btn_arrow_black_down.png) center no-repeat");
            $(this).val("0")
        }

    });
    $(".arrowStatus").click(function(){
        var arr=$(this).val();
        $(".now").slideToggle();
        if(arr==0){
            $(this).css("background","url(../static/img/com_btn_arrow_black_up.png) center no-repeat");
            $(this).val("1")
        }else if(arr==1){
            $(this).css("background","url(../static/img/com_btn_arrow_black_down.png) center no-repeat");
            $(this).val("0")
        }

    });


    $(".select dd").click(function(){
        var secval=$(this).html();
        var secStr=$(this).data("typestr");
        var type = $(this).parent().prev().html();
        $(this).hide().siblings("dd").hide();
        $(this).siblings("dt").children("abbr").html(secval);
        $(this).siblings("dt").children("input").val(secStr);
        $(this).siblings(".arrow").css("background","url(../static/img/com_btn_arrow_black_down.png) center no-repeat");

        if( secStr == 'REPLENISHMENT' ){
            console.log(1);
            $(".billStatus").each(function(){
                $(this).css("display","none");
                if( $(this).data("typestr") == 'CHECKING' || $(this).data("typestr") == 'CHECKED' || $(this).data("typestr") == 'TERMINATED' || $(this).data("typestr") == 'FINISHED' ){
                    $(this).css("display","block");
                    $(this).addClass("now");
                }
                else{
                    $(this).removeClass("now");
                }
            });
        }
        else if( secStr == 'INVENTORY_TRANSFER' ){
            console.log(1);
            $(".billStatus").each(function(){
                $(this).css("display","none");
                if( $(this).data("typestr") == 'WAIT_DELIVERY' || $(this).data("typestr") == 'WAIT_RECEIVER' || $(this).data("typestr") == 'TERMINATED' || $(this).data("typestr") == 'FINISHED' || $(this).data("typestr") == 'WAIT_EDIT'){
                    $(this).css("display","block");
                    $(this).addClass("now");
                }
                else{
                    $(this).removeClass("now");
                }
            });
        }
        else if( secStr == 'MORE_REPORT' || secStr == 'LESS_REPORT'){
            console.log(1);
            $(".billStatus").each(function(){
                $(this).css("display","none");
                if( $(this).data("typestr") == 'CHECKING' || $(this).data("typestr") == 'WAIT_EDIT' || $(this).data("typestr") == 'TERMINATED' || $(this).data("typestr") == 'FINISHED' ){
                    $(this).css("display","block");
                    $(this).addClass("now");
                }
                else{
                    $(this).removeClass("now");
                }
            });
        }
        else{
            //单据状态全部
            if( type == '单据类型' ){
                console.log("fuck");
                $(".All").css("display","block");
                $(".billStatus").each(function(){
                    $(this).addClass("now");
                    $(this).css("display","block");
                });
            }
        }
    });

});

function handleStatus( str ){
    if( str == '待办事宜' ){
        return 'nothandled';
    }
    if( str == '已办事宜' ){
        return 'done';
    }
    if( str == '我的请求' ){
        return 'myrequest';
    }
}