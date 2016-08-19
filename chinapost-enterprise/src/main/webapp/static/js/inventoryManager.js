//function ajaxPages(total,url,contentStr,target,type,perpage){
//    for(var i = 0;i < total;i++){
//        var html = get_html(type);
//        $("#"+contentStr).append(html);
//    }
//    jPagesGet(target,contentStr);
//    $("#"+contentStr).empty();
//    $("a").click(function(){
//        var page = $(this).html();
//        $.post(url,{
//            "page":page,
//            "size":perpage
//        },function(data){
//            if(data.response == 'success'){
//                appendHtml(contentStr,page,total,data.data.content,perpage);
//            }
//        },'json');
//    });
//    $("#holder").children()[1].click();
//}
//
//function get_html(type){
//    var html = '';
//    html = "<dd>";
//    html += '<abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr>';
//    html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /></abbr>';
//    html += '<abbr class="invenName"></abbr>';
//    html += '<abbr class="invenType"></abbr>';
//    html += '<abbr class="invenId"></abbr>';
//    html += '<abbr class="invenNum"></abbr>';
//    html += '<abbr class="invenNumUsed"></abbr>';
//    html += '<abbr class="invenBrand"></abbr>';
//    html += '<abbr class="invenSeller"></abbr>';
//    html += '<abbr class="invenName"></abbr>';
//    html += '<abbr class="invenId"></abbr>';
//    html += '<abbr style="width:300px;">';
//    html += '<select class="invenListSelect">';
//    html += '<option value="0" selected="selected">可选</option>';
//    html += '<option value="1">修改库存</option>';
//    html += '<option value="2">删除</option>';
//    html += '</select></abbr></dd>';
//    return html;
//}
//function appendHtml(str,pageIndex,totalPage,data,perpage){
//    $("#"+ str).empty();
//    for(var i=0;i<perpage;i++){
//        var html = "<dd>";
//        html += '<input type="hidden" value="' + data[i].goodsId + '">'
//        html += '<abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr>';
//        html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /></abbr>';
//        html += '<abbr class="invenName">' + data[i].goodsName + '</abbr>';
//
//        var content = '';
//        var h = '';
//        var array = data[i].specString.split(",");
//        for ( var j = 0; j < array.length;j++){
//            var text= /(?:null)/;
//            var flag = text.test(array[j]);
//            if ( !flag ){
//                h = array[j];
//                if( array.length > 1 ){
//                    h += '...';
//                }
//                break;
//            }
//
//        }
//        for(var a = 0;a < array.length;a++){
//            var text= /(?:null)/;
//            var flag = text.test(array[a]);
//            if ( !flag ){
//                content += array[a] + '\n';
//            }
//        }
//        html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';
//        html += '<abbr class="invenId">' + data[i].goodsNumber + '</abbr>';
//        html += '<abbr class="invenNum">' + data[i].inventory + '</abbr>';
//        html += '<abbr class="invenNumUsed">' + data[i].available + '</abbr>';
//        html += '<abbr class="invenBrand">' + data[i].goodsBrand + '</abbr>';
//        html += '<abbr class="invenSeller">' + data[i].goodsMerchants + '</abbr>';
//        html += '<abbr style="width:300px;">';
//        html += '<select class="invenListSelect">';
//        html += '<option value="0" selected="selected">可选</option>';
//        html += '<option value="1">修改库存</option>';
//        html += '<option value="2">删除</option>';
//        html += '</select></abbr></dd>';
//        $("#"+ str).append(html);
//    }
//
//}
//function checkUndefined(str){
//    if( str == 'undefined' ){
//        str = '';
//    }
//    return str;
//}
//
//
//function jPagesGet(content,target){
//    $("#"+content).jPages({
//        containerID : target
//    });
//
//}


$(document).ready(function(){
    var goodsInfoName = '';
    var goodsInfoItemNo = '';
    var brandId = '';
    var goodsInfoAdded = '';
    var typreId = '';
    var thirdId = '';
    var array = [];

    array[0] = goodsInfoName;
    array[1] = goodsInfoItemNo;
    array[2] = brandId;
    array[3] = goodsInfoAdded;
    array[4] = typreId;
    array[5] = thirdId;


    //ajaxPages(total_elements,'../web/api/inventory/getInventoryGoods','itemContainer','holder',0,5);
    ajaxPages('../web/api/inventory/getInventoryGoods','itemContainer','holder',8,5,'','',array,function(pageId){
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

    $(document).on("click",".accountNumber",function(){
        var pageIndex=$(".jp-current").html();
        var orderCode=$(this).parent().prev().val();
        if( checked_array[pageIndex] == undefined ){
            checked_array[pageIndex] = new Array();
            checked_array.length++;
            outport_array[checked_array.length-1] = new Array();
        }
        if($(this).is(":checked")){
            checked_array[pageIndex].push(orderCode);
            outport_array[checked_array.length-1].push(orderCode);
        }
        else{
            checked_array[pageIndex].map(function(code){
                if( code == orderCode ){
                    checked_array[pageIndex].remove(code);
                    outport_array[checked_array.length-1].remove(orderCode);
                }
            })
        }
        console.log(checked_array,outport_array);
    });

    $("#updateInvenNumConfirm").click(function(){
        var inventory = ( $("input[name='editInventory']").val() != '' ) ? $("input[name='editInventory']").val() : 'a';
        var goodsId = $("#goodsId").val();
        console.log(inventory)
            if(/^[0-9]*[1-9][0-9]*$/.test(inventory)){
            $.post("../web/api/inventory/editInventory",{
                goodsInfoId:goodsId,
                inventory:inventory
            },function(data){
                if( data.response == 'success' ){
                    discoverHtml();
                    var lival=$(".accOn").val()
                    if(lival=="0") {
                        ensure_alert( 'InventoryManager')
                    }else if(lival=="1"){
                        data_type_alert("修改库存成功","success")
                        $(".updateInvenNum").fadeOut(100);
                        $(".accountTable li[value='1']").trigger("click")
                    }
                }
                else{
                    window.wxc.xcConfirm(data.data.text, window.wxc.xcConfirm.typeEnum.error);
                }
            },'json');
            }else{
                data_type_alert("库存输入错误","error")
            }

    });

    $("#deleteComConfirm").click(function(){
        var goodsId = $("#goodsId").val();
        $.post("../web/api/inventory/deleteInventory",{
            goodsInfoId:goodsId
        },function(data){
            if( data.response == 'success' ){
                discoverHtml()
                ensure_alert('InventoryManager');
            }
            else{
                window.wxc.xcConfirm(data.data.text, window.wxc.xcConfirm.typeEnum.error);
            }
        },'json');
    });

    $("#warningNumConfirm").click(function(){
        var warningNumber = $("input[name='setWarningNumber']").val();
        if(/^\+?[1-9][0-9]*$/.test(warningNumber)||warningNumber==0){
        $.post("../web/api/inventory/setWarning",{
            warning:warningNumber
        },function( data ){
            if( data.response == 'success' ){
                discoverHtml()
                response_ensure_alert("success","设置预警值成功",function(){
                    $(".warningNum").fadeOut(500)
                    $(".accountTable li[value='1']").trigger("click")
                })

            }else{
                data_type_alert(data.data.text,"error")
            }
        },'json');
        }else{
            coverHtml()
            data_type_alert("预警值必须为正整数","error")
        }
    });

    $("#search").click(function(){
        checked_array = {};
        outport_array = {};
        checked_array.length = 0;

        goodsInfoName = $("input[name='memberNum']").val();
        goodsInfoItemNo = $("input[name='memberCust']").val();
        if ($("#brand_select option:selected").val() == '全部') {
            brandId = '';
        }
        else {
            brandId = $("#brand_select option:selected").val();
        }
        if ($("#thirdname_select option:selected").val() == '全部') {
            thirdId = '';
        }
        else {
            thirdId = $("#thirdname_select option:selected").val();
        }


        var array = [];
        array[0] = goodsInfoName;
        array[1] = goodsInfoItemNo;
        array[2] = brandId;
        array[3] = goodsInfoAdded;
        array[4] = typreId;
        array[5] = thirdId;
        ajaxPages("../web/api/inventory/getInventoryGoods", "itemContainer", "holder", 8, 5, '', '', array,function(pageId){
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


    });
    $("#search2").click(function(){
        checked_array = {};
        outport_array = {};
        checked_array.length = 0;

        goodsInfoName = $("input[name='memberNum']").val();
        goodsInfoItemNo = $("input[name='memberCust']").val();
        if ($("#brand_select option:selected").val() == '全部') {
            brandId = '';
        }
        else {
            brandId = $("#brand_select option:selected").val();
        }
        if ($("#thirdname_select option:selected").val() == '全部') {
            thirdId = '';
        }
        else {
            thirdId = $("#thirdname_select option:selected").val();
        }


        var array = [];
        array[0] = goodsInfoName;
        array[1] = goodsInfoItemNo;
        array[2] = brandId;
        array[3] = goodsInfoAdded;
        array[4] = typreId;
        array[5] = thirdId;
        ajaxPages("../web/api/inventory/getWarningGoods", "itemContainer_warning", "holder_warning", 8, 5, '', '', array,function(pageId){
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

    });

    $("#outputAll").click(function(){
        var goodsInfoName = $("input[name='memberNum']").val();
        var goodsNumber = $("input[name='memberCust']").val();

        var flag = $(".accOn").html();

        if( flag == '全部货品' ){
            window.location.href = '../web/api/exportExcel/inventoryGoodsDown?goodsInfoId=all' + '&goodsNumber=' + goodsNumber + '&goodsInfoName=' + goodsInfoName;
        }
        else{
            window.location.href = '../web/api/exportExcel/inventoryWarningGoodsDown?goodsInfoId=all' + '&goodsNumber=' + goodsNumber + '&goodsInfoName=' + goodsInfoName;
        }
    });

    $("#outputPart").click(function(){
        var flag = $(".accOn").html();

        var itemIds = '[';
        for(var i = 0;i < checked_array.length;i++){
            for(var j = 0;j < outport_array[i].length;j++){
                itemIds += '"' + outport_array[i][j] + '",';
            }
        }
        itemIds = itemIds.substring(0,itemIds.length - 1);
        itemIds += ']';

        if(itemIds.length > 2){
            if( flag == '全部货品' ){
                window.location.href = '../web/api/exportExcel/inventoryGoodsDown?goodsInfoId=' + itemIds;
            }
            else{
                window.location.href = '../web/api/exportExcel/inventoryWarningGoodsDown?goodsInfoId=' + itemIds;
            }

        }
    });
});