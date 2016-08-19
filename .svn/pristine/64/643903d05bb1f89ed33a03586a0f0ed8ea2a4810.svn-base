$(document).ready(function(){
    var goodsInfoName = '';
    var goodsInfoItemNo = '';
    var brandId = '';
    var goodsInfoAdded = '';
    var typreId = '';
    var thirdId = '';
    var array = [];

    var checked_array = {};
    var outport_array = {};
    checked_array.length = 0;
    var itemIds = '[';

    array[0] = goodsInfoName;
    array[1] = goodsInfoItemNo;
    array[2] = brandId;
    array[3] = goodsInfoAdded;
    array[4] = typreId;
    array[5] = thirdId;
    ajaxPages("../web/api/goodsManager/getGoodsInfo","itemContainer","holder",7,5,'','',array,function(pageId){
        if( checked_array[pageId] != undefined ) {
            $(".inOut").next().each(function () {
                var _this = this;
                checked_array[pageId].map(function(itId){
                    if($(_this).val() == itId){
                        $(_this).parent().siblings().children("input[name='itemsListCheck']").prop("checked","checked");
                    }
                });
            });
        }
        return isTop;
    });

    $(document).on("click",".itemsList dd input[name='itemsListCheck']",function(){
        var pageIndex=$(".jp-current").html();
        var itemID=$(this).parent().siblings().children(".inOut").next().val();
        if( checked_array[pageIndex] == undefined ){
            checked_array[pageIndex] = new Array();
            checked_array.length++;
            outport_array[checked_array.length-1] = new Array();
        }
        if($(this).is(":checked")){
            checked_array[pageIndex].push(itemID);
            outport_array[checked_array.length-1].push(itemID);
            console.log(checked_array[pageIndex],outport_array[checked_array.length-1]);
        }
        else{
            checked_array[pageIndex].map(function(id){
                if( id == itemID ){
                    checked_array[pageIndex] = checked_array[pageIndex].remove(id);
                    outport_array[pageIndex-1] = outport_array[pageIndex-1].remove(id);
                }
            })
        }
    });

    $(document).on("click",".inOut",function(){
        var goodsInfoId = $(this).next().val();
        if( isTop ){
            if( $(this).val() == '上架' )
                $.post("../web/api/goodsManager/shelves",{
                    goodsInfoId:goodsInfoId
                },function(data){
                    if ( data.response == 'success' ){
                        response_ensure_alert('success','操作成功',function(){
                            $(".jp-current").trigger("click");
                        });
                    }
                    else{
                        response_ensure_alert('error','操作失败',function(){
                            $(".jp-current").trigger("click");
                        });
                    }
                });
            else if( $(this).val() == '下架' ){
                $.post("../web/api/goodsManager/unshelves",{
                    goodsInfoId:goodsInfoId
                },function(data){
                    if ( data.response == 'success' ){
                        response_ensure_alert('success','操作成功',function(){
                            $(".jp-current").trigger("click");
                        });
                    }
                    else{
                        response_ensure_alert('success','操作成功',function(){
                            $(".jp-current").trigger("click");
                        });
                    }
                });
            }
        }
        else{

        }

    });

    $("#itemsSearch").click(function(){
        goodsInfoName = $("input[name='itemManager_Name']").val();
        goodsInfoItemNo = $("input[name='itemManager_Number']").val();
        if( $("select[name='itemManager_Brand'] option:selected").val() == '全部' ){
            brandId = '';
        }
        else{
            brandId = $("select[name='itemManager_Brand'] option:selected").val();
        }
        if( $("select[name='itemManager_Shelves'] option:selected").val() == '全部' ){
            goodsInfoAdded = '';

        }
        else{
            goodsInfoAdded = $("select[name='itemManager_Shelves'] option:selected").val();
        }
        if( $("select[name='itemManager_Type'] option:selected").val() == '全部' ){
            typreId = '';
        }
        else{
            typreId = $("select[name='itemManager_Brand'] option:selected").val();
        }
        if( $("select[name='itemManager_ThirdName'] option:selected").val() == '全部' ){
            thirdId = '';
        }
        else{
            thirdId = $("select[name='itemManager_ThirdName'] option:selected").val();
        }


        array[0] = goodsInfoName;
        array[1] = goodsInfoItemNo;
        array[2] = brandId;
        array[3] = goodsInfoAdded;
        array[4] = typreId;
        array[5] = thirdId;

        ajaxPages("../web/api/goodsManager/getGoodsInfo","itemContainer","holder",7,5,'','',array,function(pageId){
        });

    });

    $("#outputAll").click(function(){
        var inventoryName = $("input[name='itemManager_Name']").val();
        var inventoryNumber = $("input[name='itemManager_Number']").val();
        var inOut = $("select[name='itemManager_Shelves'] option:selected").val();
        if( inOut == '全部'){
            inOut = '';
        }
        window.location.href = '../web/api/exportExcel/goodsManagerDown?goodsInfoId=' + 'all&goodsInfoName=' + inventoryName + "&goodsInfoItemNo=" + inventoryNumber + "&goodsInfoAdded=" + inOut;
    });
    $("#outputPart").click(function(){
        var itemIds = '[';
        for(var i = 0;i < checked_array.length;i++){
            for(var j = 0;j < outport_array[i].length;j++){
                itemIds += '"' + outport_array[i][j] + '",';
            }
        }
        itemIds = itemIds.substring(0,itemIds.length - 1);
        itemIds += ']';
        if(itemIds.length > 2){
            window.location.href = '../web/api/exportExcel/goodsManagerDown?goodsInfoId=' + itemIds;
        }
    });

});

