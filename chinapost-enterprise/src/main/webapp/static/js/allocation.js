$(document).ready(function(){
    var inId = '';
    var outId = $(".choosenIntOut").data("id");
    $(document).on("click","#addinvet",function(){
        $("#cover1").show();
        $("#addIvent").show();
        var enterpriseId = $(".choosenIntOut").data("id");
        ajaxPages("../web/api/inventory/getOutInventoryGoods",enterpriseId,'itemContainer','holder',10);
    });
    $(document).on("click","#xx",function(){
        $("#cover1").hide();
        $("#addIvent").hide();
    });
    $( "#addIvent" ).draggable();
    $(".DepotDetail a").each(function(){
        if($(this).html()=="+"){
            $(this).siblings("ul").hide();
        }
    });
    $(".chooeseDepot div:first-child i,#chooseCancel").click(function(){
        $("#cover1").hide();
        $(".chooeseDepot").hide();
    });

    $(document).on("click",".chooeseDepotOut li a.chooseAvailable",function(){
        var _this = this;
        if( $(this).html() == '+' && $(this).parent().find("ul").length == 0){
            var enterpriseId = $(this).data("id");
            $.post("../web/api/inventory/getSonEnterpriseInfo",{
                enterpriseId:enterpriseId
            },function(data){
                if( data.response == 'success' ){
                    var html = '<ul>';
                    data.data.map(function(object){
                        html += '<li>';
                        html += '<a data-id="' + object.id + '" style="display:none;"></a>';
                        if( object.end == false ){
                            html += '<span>' + object.enterpriseName + '</span>';
                        }
                        else{
                            html += '<input type="radio" name="chooeseDepotOut"/><span>' + object.enterpriseName + '</span>';
                        }

                        html += '</li>';
                    });
                    html += '</ul>';
                    $(_this).parent().append(html);
                }
                else{

                }
            },'json');
        }
    });




    $(document).on("click",".chooeseDepot li a",function(){
        console.log(1);
        var _this = this;
        if( $(this).html() == '+' ){
            var enterpriseId = $(this).data("id");
            $.post("../web/api/inventory/getSonEnterpriseInfo",{
                enterpriseId:enterpriseId
            },function(data){
                if( data.response == 'success' ){
                    var html = '<ul>';
                    data.data.map(function(object){
                        html += '<li>'
                        if( object.end == true ){
                            html += '<a data-id="' + object.id + '">-</a>';
                        }
                        else{
                            html += '<a data-id="' + object.id + '">+</a>';
                        }
                        if( object.end == false ){
                            html += '<span>' + object.enterpriseName + '</span>';
                        }
                        else{
                            html += '<input type="radio" name="chooeseDepotOut"/><span>' + object.enterpriseName + '</span>';
                        }
                        html += '</li>';
                    });
                    html += '</ul>';
                    $(_this).siblings("ul").remove();
                    $(_this).parent().append(html);
                }
                else{

                }
            },'json');
        }
    });

    $(document).on("click",'.DepotDetail a',function(){
        if( $(this).html( ) == "+" ){
            $(this).siblings("ul").slideDown();
            $(this).html("-");
        }else if( $(this).html( ) == "-" ){
            $(this).siblings("ul").slideUp();
            $(this).html("+");
        }
    });
    $("#choosenInt").click(function(){
        $("#cover1").show();
        $(".chooeseDepot").show();
    });
    $("#chooseConfirm").click(function(){
        $("#cover1").hide();
        var dataid =  $("input[name='chooeseDepotOut']:checked").siblings("a").data("id");
        $("#choosenInt").html($("input[name='chooeseDepotOut']:checked").siblings("span").html());
        inId = dataid;
        $(".chooeseDepot").hide();
    });
    $(".chooeseDepotOut div:first-child i,#chooseCancelOut").click(function(){
        $("#cover1").hide();
        $(".chooeseDepotOut").hide();
    });
    $("#choosenIntOut").click(function(){
        $.post('../web/api/inventory/');
        $("#cover1").show();
        $(".chooeseDepotOut").show();
    });
    $("#chooseConfirmOut").click(function(){
        $("#cover1").hide();
        var dataid =  $("input[name='chooeseDepotOut']:checked").siblings("a").data("id");
        $("#choosenIntOut").html($("input[name='chooeseDepotOut']:checked").siblings("span").html());
        outId = dataid;
        $(".chooeseDepotOut").hide();
        $("#choosenIntOut").data("id",dataid);
    });
    $(document).on("click","input[name='addinventcheck']",function(){
        if($(this).prop("checked")){
            $("#count").append($(this).parent().parent()[0].outerHTML);
        }
        else{
            $("#count").find("input[value='" + $(this).val() + "']").parent().parent().remove();
        }
    });

    $("#searchGoods").click(function(){
        var goodsInfoName = $("input[name='item_name']").val();
        var goodsInfoItemNo = $("input[name='item_no']").val();
        var enterpriseId = $(".choosenIntOut").data("id");
        var array = [];
        array[0] = goodsInfoName;
        array[1] = goodsInfoItemNo;
        ajaxPages("../web/api/inventory/getOutInventoryGoods",enterpriseId,"itemContainer","holder",10,array);
        ////清空count
        //$("#count").empty();
        //
        //$.post("../web/api/inventory/getOutInventoryGoods",{
        //    goodsInfoName:goodsInfoName,
        //    goodsInfoItemNo:goodsInfoItemNo,
        //    enterpriseId:enterpriseId,
        //    brandId:'',
        //    thirdId:'',
        //    page:1,
        //    size:10
        //},function(data){
        //    if( data.response == 'success' ){
        //        var total = data.data.totalElements;
        //        for(var i = 0;i < total;i++){
        //            var html = get_html(1);
        //            $("#itemContainer").append(html);
        //        }
        //        jPagesGet("holder","itemContainer",10);
        //        //处理多余页
        //        var showElement = ( $("#holder a").length - 2 ) * 5 ;
        //        console.log(showElement,data.data.totalElements + 5);
        //        if( showElement >= ( data.data.totalElements + 5 ) ){
        //            $( $("#holder a")[$("#holder a").length - 2]).remove();
        //        }
        //        $("#holder").find("a").css("display","inline-block");
        //        $("#holder").find("span").css("display","inline-block");
        //        $("#holder").find(".jp-hidden").css("display","none");
        //        $("#itemContainer").empty();
        //        $("#holder a").click(function(){
        //            var enterpriseId = $(".choosenIntOut").data("id");
        //            var page = '';
        //            if( $(this).html() == '上一页' ){
        //                page = parseInt($(".jp-current").html()) - 1;
        //            }
        //            else if( $(this).html() == '下一页' ){
        //                page = parseInt($(".jp-current").html()) + 1;
        //            }
        //            else{
        //                console.log(1);
        //                page = $(this).html();
        //            }
        //            $.post("../web/api/inventory/getOutInventoryGoods",{
        //                goodsInfoName:goodsInfoName,
        //                goodsInfoItemNo:goodsInfoItemNo,
        //                enterpriseId:enterpriseId,
        //                brandId:'',
        //                thirdId:'',
        //                page:page,
        //                size:10
        //            },function(data){
        //                if( data.response == 'success' ){
        //                    var length = data.data.content.length;
        //                    $("#itemContainer").empty();
        //                    for(var i = 0;i < length;i++){
        //                        var html = get_html(1);
        //                        $("#itemContainer").append(html);
        //                    }
        //                    //jPagesGet("holder","itemContainer",5);
        //                    if( length < 10 ){
        //                        console.log(length);
        //                        goodsSearch_appendHtml("itemContainer",page,length,data.data.content,10);
        //                    }
        //                    else{
        //                        goodsSearch_appendHtml("itemContainer",page,data.data.size,data.data.content,10);
        //                    }
        //                }
        //            },'json');
        //        });
        //        $("#holder").children()[1].click();
        //    }
        //},'json');
    });

    //删除
    $(".delete").click(function(){
        $(".bodySecond input[type='checkbox']:checked").each(function(){
            var id = $(this).parent().parent().data("id");
            $(this).parent().parent().remove();
            $("#count dd").each(function(){
                if( $(this).find("input[name='addinventcheck']").val() == id ){
                    $(this).remove();
                }
            });
        });

        var count = 1;
        $(".bodySecond input[type='checkbox']").each(function(){
            $(this).next().html(count);
            count++;
        });
    });

    $("#commitGoods").click(function(){
        var length = 0;
        var html = '';
        $("#count dd").each(function(){
            length++;
            html += '<dd data-id="' + $(this).find("input[type='checkbox']").val() + '">';
            html += '<abbr><input type="checkbox" name="invetcheck"/><b>' + length + '</b></abbr>';
            html += '<abbr>' + $(this).find("abbr:nth-child(2)").html() + '</abbr>';
            html += '<abbr>' + $(this).find("abbr:nth-child(3)").html() + '</abbr>';
            html += '<abbr>' + $(this).find("abbr:nth-child(4)").html() + '</abbr>';
            html += '<abbr>' + $(this).find("abbr:nth-child(6)").data("available") + '</abbr>';
            html += '<abbr><input type="text"> </abbr>';
            html += '</dd>';
        });
        $(".bodySecond dl dd").remove();
        $(".bodySecond dl").append(html);
        $("#addIvent").fadeOut(500);
        discoverHtml();
    });

    $("#commit").click(function(){
        var reason = $(".reasonForApply").val();
        var flag = true;
        var skuJson = '{';
        if( $(".bodySecond dd").length > 0 ){
            $(".bodySecond dd").each(function(){
                console.log(this);
                if( $(this).find("input[type='text']").val() != ''){
                    skuJson += '"' + $(this).data("id") + '":' + $(this).find("input[type='text']").val() + ',';
                    console.log( $(this).find("input[type='text']").val() );
                }
                else{
                    flag = false;
                }
            });
            skuJson = skuJson.substring(0,skuJson.length-1);
            skuJson += '}';
            console.log(flag);
            if(inId != '' && outId != inId && flag == true && reason != '') {
                $.post("../web/api/inventory/createBill", {
                    type: 'INVENTORY_TRANSFER',
                    outId: outId,
                    inId: inId,
                    reason: reason,
                    skuJson: skuJson
                }, function (data) {
                    if (data.response == 'success') {
                        response_ensure_alert("success", "提交成功", function () {
                            localStorage['systemAction'] = 'refresh';
                            window.close();
                        });
                    } else {
                        response_ensure_alert('error',data.data.text,function(){
                            console.log("创建失败" + new Date().toString());
                        });
                    }
                }, 'json');
            }
            else if( inId == '' ){
                response_ensure_alert("error","调入仓库不能为空",function(){
                    console.log("调入仓库为空" + new Date().toString());
                });
            }
            else if( outId == inId ){
                data_type_alert("调出仓库和调入仓库不能为同一个", "error");
            }
            else if( flag == false ){
                response_ensure_alert("error","调拨数量不能为空",function(){
                   console.log("调拨数量为空" + new Date().toString());
                });
            }
            else if( reason == '' ){
                response_ensure_alert("error","调拨原因不能为空",function(){
                    console.log("调拨原因不能为空" + new Date().toString());
                });
            }
        }else{
            data_type_alert("没有选择货品","error")
        }


    });
    //发货
    $("#repleSend").click(function(){
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        var skuJson = '{';
        if( $(".bodySecond dd").length > 0 ){
            $(".bodySecond dd").each(function(){
                skuJson += '"' + $(this).data("id") + '":' + $(this).find(".outNum").html() + ',';
            });
            skuJson = skuJson.substring(0,skuJson.length-1);
        }else{
            data_type_alert("没有选择货品","error")
        }
        skuJson += '}';
        $.post("../web/api/inventory/deliveryBill",{
            billId:billId,
            comment:comment,
            skuJson:skuJson
        },function(data){
            if(data.response == 'success'){
                response_ensure_alert("success","发货成功",function(){
                    window.close();
                });
            }else{
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    $("#repleSend2").click(function(){
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        var skuJson = '{';
        if( $(".bodySecond dd").length > 0 ){
            $(".bodySecond dd").each(function(){
                skuJson += '"' + $(this).data("id") + '":' + $(this).find(".change").val() + ',';
            });
            skuJson = skuJson.substring(0,skuJson.length-1);
        }else{
            data_type_alert("没有选择货品","error")
        }
        skuJson += '}';
        $.post("../web/api/inventory/deliveryBill",{
            billId:billId,
            comment:comment,
            skuJson:skuJson
        },function(data){
            if(data.response == 'success'){
                response_ensure_alert("success","发货成功",function(){
                    window.close();
                });
            }else{
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    //收货
    $("#repleReceive").click(function(){
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        $.post("../web/api/inventory/receiptBill",{
            billId:billId,
            comment:comment
        },function(data){
            if(data.response == 'success'){
                response_ensure_alert("success","收货成功",function(){
                    window.close();
                });
            }else{
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    //退回
    $("#repleBack").click(function(){
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        $.post("../web/api/inventory/backBill",{
            billId:billId,
            comment:comment
        },function(data){
            if(data.response == 'success'){
                response_ensure_alert("success","退回",function(){
                    window.close();
                });
            }else{
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    //终止
    $(".stop").click(function(){
        var billId = $("#billId").val();
        var comment = $(".suggest textarea").val();
        $.post("../web/api/inventory/terminateBill",{
            billId:billId,
            comment:comment
        },function(data){
            if(data.response == 'success'){
                response_ensure_alert("success","终止成功",function(){
                    window.close();
                });
            }else{
                data_type_alert( data.data.text ,"error")
            }
        },'json');
    });
    $(".topButton").click(function(){
        localStorage['systemAction'] = 'refresh';
    });
});

function ajaxPages(url,enterpriseId,contentStr,target,perpage,array){
    if( array != undefined ){
        var goodsInfoName = array[0];
        var goodsInfoItemNo = array[1];
    }
    $.post(url,{
        "page":1,
        "size":perpage,
        "enterpriseId":enterpriseId,
        "goodsInfoName":goodsInfoName,
        "goodsInfoItemNo":goodsInfoItemNo
    },function(data){
        if(data.response == 'success'){
            var total = data.data.totalElements;
            for(var i = 0;i < total;i++){
                var html = get_html();
                $("#"+contentStr).append(html);
            }
            jPagesGet(target,contentStr);
            if( total < perpage ){
                goodsChoose_appendHtml(contentStr,1,total,data.data.content,total);
            }
            else{
                goodsChoose_appendHtml(contentStr,1,total,data.data.content,perpage);
            }
            //处理多余页
            var calPage = 1;
            if( $("#" + target + " a").length - 2 < 0 ){
                calPage = 1;
            }
            else{
                calPage = $("#" + target + " a").length - 2;
            }
            var showElement = calPage * perpage ;
            if( showElement >= ( data.data.totalElements + perpage) ){
                $( $("#" + target + " a")[$("#" + target + " a").length - 2]).html($( $("#" + target + " a")[$("#" + target + " a").length - 3]).html());
                $( $("#" + target + " a")[$("#" + target + " a").length - 3]).remove();
            }
            $("a").unbind("click");
            $("a").click(function(){
                var page = '';
                if( $(this).html() == '＜' ){
                    page = parseInt($(".jp-current").html()) - 1;
                    if( page ==  $("#" + target + " a").length - 3 ){
                        $($("#" + target + " a")[$("#" + target + " a").length - 3]).addClass("jp-current");
                        $($("#" + target + " a")[$("#" + target + " a").length - 2]).attr("class","");
                    }
                }
                else if( $(this).html() == '＞' ){
                    page = parseInt($(".jp-current").html()) + 1;
                    if( page ==  $("#" + target + " a").length - 2){
                        $(this).prev().addClass("jp-current");
                        $($("#" + target + " a")[$("#" + target + " a").length - 3]).attr("class","");
                    }

                }
                else{
                    page = $(this).html();
                }
                if( page > $("#" + target + " a").length - 2 || page < 1){

                }
                else{
                    if( !isNaN(page) ){
                        $.post(url,{
                            "page":page,
                            "size":perpage,
                            "enterpriseId":enterpriseId,
                            "goodsInfoName":goodsInfoName,
                            "goodsInfoItemNo":goodsInfoItemNo
                        },function(data){
                            if(data.response == 'success'){
                                goodsChoose_appendHtml(contentStr,page,total,data.data.content,10);
                            }
                        },'json');
                    }
                }

            });
        }
    },'json');

}
function get_html(){
    var html = '';
    html = "<dd>";
    html += '<abbr><input type="checkbox" name="addinventcheck"> </abbr>';
    html += '<abbr>非尼膜属高透iPhone5</abbr>';
    html += '<abbr>颜色：红色</abbr>';
    html += '<abbr>0270301010007</abbr>';
    html += '<abbr>ma</abbr>';
    html += '<abbr>邮政</abbr>';
    html += "</dd>";
    return html;
}

function goodsChoose_appendHtml(str,pageIndex,totalPage,data,perpage){
    $("#"+ str).empty();
    var allCheckedCount = 0;
    for(var i=0;i<perpage;i++){
        var flag = 0;
        var html = "<dd>";
        var length = $("#count input[type='checkbox']").length;
        if( length > 0 ){
            for(var j = 0;j < length; j++){
                var id = $( $("#count").children()[j] ).find("input[type='checkbox']").val();
                if( data[i].goodsInfoId == id ){
                    flag = 1;
                }
            }
        }
        try{
            if( flag ){
                allCheckedCount++;
                html += '<abbr><input name="addinventcheck" checked="checked" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
            }
            else{
                html += '<abbr><input name="addinventcheck" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
            }
            html += '<abbr>' + data[i].goodsInfoName + '</abbr>';
            //html += '<abbr class="invenName" style="padding-top: 0!important;">' + data[i].goodsInfoName + '</abbr>';

            var content = '';
            var h = '';
            var array = data[i].specString.split(",");
            for ( var j = 0; j < array.length;j++){
                var text= /(?:null)/;
                var flag = text.test(array[j]);
                if ( !flag ){
                    h = array[j];
                    if( array.length > 1 ){
                        h += '...';
                    }
                    break;
                }

            }
            for(var a = 0;a < array.length;a++){
                var text= /(?:null)/;
                var flag = text.test(array[a]);
                if ( !flag ){
                    content += array[a] + '\n';
                }
            }
            html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';

            html += '<abbr class="invenId">' + data[i].goodsNumber + '</abbr>';
            html += '<abbr class="invenBrand">' + data[i].goodsBrand + '</abbr>';
            html += '<abbr class="invenSeller" data-available="' + data[i].available + '">' + data[i].goodsMerchants + '</abbr>' ;
            html += '</abbr>';
            html += "</dd>";

            $("#"+ str).append(html);
            if( allCheckedCount == perpage ){
                $("input[name='addinventcheckDT']").prop("checked","checked");
            }
            else{
                $("input[name='addinventcheckDT']").prop("checked","");
            }
        }
        catch(e){

        }

    }

}
function goodsSearch_appendHtml(str,pageIndex,totalPage,data,perpage){
    $("#"+ str).empty();
    for(var i=0;i<perpage;i++){
        var flag = 0;
        var html = "<dd>";
        var length = $("#count input[type='checkbox']").length;
        if( length > 0 ){
            for(var j = 0;j < length; j++){
                var id = $( $("#count").children()[j] ).find("input[type='checkbox']").val();
                if( data[i].goodsInfoId == id ){
                    flag = 1;
                }
            }
        }

        try{
            if( flag ){
                html += '<abbr><input name="addinventcheck" checked="checked" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
            }
            else{
                html += '<abbr><input name="addinventcheck" type="checkbox" value="' + data[i].goodsInfoId + '"/></abbr>';
            }

            html += '<abbr>' + data[i].goodsInfoName + '</abbr>';

            var content = '';
            var h = '';
            var array = data[i].specString.split(",");
            for ( var j = 0; j < array.length;j++){
                var text= /(?:null)/;
                var flag = text.test(array[j]);
                if ( !flag ){
                    h = array[j];
                    if( array.length > 1 ){
                        h += '...';
                    }
                    break;
                }

            }
            for(var a = 0;a < array.length;a++){
                var text= /(?:null)/;
                var flag = text.test(array[a]);
                if ( !flag ){
                    content += array[a] + '\n';
                }
            }
            html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';
            html += '<abbr class="invenId">' + data[i].goodsNumber + '</abbr>';
            html += '<abbr class="invenBrand">' + data[i].goodsBrand + '</abbr>';
            html += '<abbr class="invenSeller" data-available="' + data[i].available + '">' + data[i].goodsMerchants + '</abbr>' ;
            html += '</abbr>';
            html += "</dd>";
            $("#"+ str).append(html);
        }
        catch(e){

        }
    }

}

function jPagesGet(content,target){
    $("#"+content).jPages({
        containerID : target,
        perPage:10
    });

}