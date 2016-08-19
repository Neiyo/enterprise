function ajaxPages(url,contentStr,target,type,perpage,reason,batchId,array,callbackChecked){
    var code = '';
    var goodsInfoName = '';
    var goodsInfoItemNo = '';
    var brandId = '';
    var goodsInfoAdded = '';
    var typreId = '';
    var thirdId = '';
    var lowPrice = '';
    var highPrice = '';
    var start = '';
    var end = '';
    var status = '';
    var creator = '';
    var billType = '';
    var billStatus = '';
    var idCard = '';
    var enterpriseId = '';
    if( array != undefined ){
        goodsInfoName = array[0];
        goodsInfoItemNo = array[1];
        brandId = array[2];
        goodsInfoAdded = array[3];
        typreId = array[4];
        thirdId = array[5];
        lowPrice = array[6];
        highPrice = array[7];
        start = array[8];
        end = array[9];
        status = array[10];
        code = array[11];
        creator = array[12];
        billType = array[13];
        billStatus = array[14];
        idCard = array[15];
        enterpriseId = array[16];
    }
    $.post(url,{
        "code":code,
        "goodsInfoName":goodsInfoName,
        "goodsInfoItemNo":goodsInfoItemNo,
        "brandId":brandId,
        "goodsInfoAdded":goodsInfoAdded,
        "typeId":typreId,
        "thirdId":thirdId,
        "batchId":batchId,
        "lowPrice":lowPrice,
        "highPrice":highPrice,
        "start":start,
        "end":end,
        "status":status,
        "creatorName":creator,
        "billStatus":billStatus,
        "billType":billType,
        "idCard":idCard,
        "enterpriseId":enterpriseId,
        "page":1,
        "size":perpage
    },function(data){
        if( data.response == 'success' ){
            //账单管理额外功能
            if( type == 1 || type == 'bill_second'){
                customerNum = total;
                callbackChecked();
            }
            else if( type == 'ubaosendForm' ){
                callbackChecked(data);
                data = data.data;
            }
            var total = data.data.totalElements;
            $("#" + contentStr).empty();
            for(var i = 0;i < total;i++){
                var html = get_html(type);
                $("#"+contentStr).append(html);
            }
            jPagesGet(target,contentStr,perpage);
            console.log(data.data.content);
            if( total < perpage ){
                appendHtml(contentStr,type,data.data.content,total,reason,batchId,callbackChecked);
            }
            else{
                appendHtml(contentStr,type,data.data.content,perpage,reason,batchId,callbackChecked);
            }

            if( data.data.totalPages == 1 ){

                var two = $( $("#" + target + " a")[2] ).html();
                if( two == 2 ){
                    $("#" + target + " a")[2].remove();
                }
            }
            //处理多余页
            var showElement = ( $("#" + target + " a").length - 2 ) * perpage ;
            if( showElement >= ( data.data.totalElements + perpage ) ){
                $( $("#" + target + " a")[$("#" + target + " a").length - 2]).remove();
            }

            $("#" + target).find("a").css("display","inline-block");
            $("#" + target).find("span").css("display","inline-block");
            $("#" + target).find(".jp-hidden").css("display","none");

            $("#" + target + " a").unbind("click");
            $("#" + target + " a").click(function(){
                var page = '';
                if( $(this).html() == '＜' ){
                    if( parseInt($("#" + target + " .jp-current").html()) > 1 ){
                        page = parseInt($("#" + target + " .jp-current").html()) - 1;
                    }
                    else{
                        page = parseInt($("#" + target + " .jp-current").html());
                    }
                }
                else if( $(this).html() == '＞' ){
                    if( parseInt($("#" + target + " .jp-current").html()) < ( $("#" + target + " a").length - 2 ) ){
                        page = parseInt($("#" + target + " .jp-current").html()) + 1;
                    }
                    else{
                        page = parseInt($("#" + target + " .jp-current").html());
                    }
                }
                else{
                    page = $(this).html();
                }
                $.post(url,{
                    "code":code,
                    "goodsInfoName":goodsInfoName,
                    "goodsInfoItemNo":goodsInfoItemNo,
                    "brandId":brandId,
                    "goodsInfoAdded":goodsInfoAdded,
                    "typeId":typreId,
                    "thirdId":thirdId,
                    "batchId":batchId,
                    "lowPrice":lowPrice,
                    "highPrice":highPrice,
                    "start":start,
                    "end":end,
                    "status":status,
                    "creatorName":creator,
                    "billStatus":billStatus,
                    "billType":billType,
                    "idCard":idCard,
                    "enterpriseId":enterpriseId,
                    "page":page,
                    "size":perpage
                },function(data){
                    if(data.response == 'success'){
                        if( type == 'ubaosendForm' ){
                            data = data.data;
                        }
                        var length = data.data.content.length;
                        $("#"+contentStr).empty();
                        for(var i = 0;i < length;i++){
                            var html = get_html(type);
                            $("#"+contentStr).append(html);
                        }
                        if( data.data.totalPages == 1 ){

                            var two = $( $("#" + target + " a")[2] ).html();
                            if( two == 2 ){
                                $("#" + target + " a")[2].remove();
                            }
                        }
                        //jPagesGet(contentStr,target,perpage);
                        $("#" + target).find("a").css("display","inline-block");
                        $("#" + target).find("span").css("display","inline-block");
                        $("#" + target).find(".jp-hidden").css("display","none");
                        //jPagesGet(target,contentStr);
                        if( length < perpage ){
                            appendHtml(contentStr,type,data.data.content,length,reason,batchId,callbackChecked);
                        }
                        else{
                            appendHtml(contentStr,type,data.data.content,perpage,reason,batchId,callbackChecked);
                        }
                        console.log(type);
                        if( typeof callbackChecked == 'function' && type != 'ubaosendForm'){
                            callbackChecked(page);
                        }
                    }
                },'json');
            });

        }
        else{

        }
    },'json');
}

function get_html(type){
    var html = '<dd>';
    if( type == 1){
        html += '<abbr class="myBillListName"></abbr>';
        html += '<abbr class="myBillListCreattime"></abbr>';
        html += '<abbr class="myBillListAccNum"></abbr>';
        html += '<abbr class="myBillListState"></abbr>';
        html += '<abbr class="myBillListPayway"></abbr>';
        html += '<abbr class="myBillListMoneySum"></abbr>';
        html += '<abbr class="myBillListNumSum"></abbr>';
        html += '<abbr style="width:0px; overflow:hidden;" class="myBillListReason"></abbr>';
        html += '<abbr style="width:0px; overflow:hidden;" class="myBillLisBusinessType"></abbr>';
        html += '<abbr style="width:0px; overflow:hidden;" class="myBillLisPayTime"></abbr>';
        html += '<abbr style="width:0px; overflow:hidden;" class="myBillListNote"></abbr>';
        html += '<abbr style="width:0px; overflow:hidden;" class="myBillListTarget"></abbr>';
        html += '<abbr style="width:0px; overflow:hidden;" class="myBillListBatchId"></abbr>';
        html += '<abbr class="myBillListCheck"><input type="button" value="查看详情" class="detail" /></abbr>';
    }
    else if( type == 1){
        html += '<abbr></abbr>';
        html += '<abbr></abbr>';
    }
    else if( type == 'bill_second' ){
        html += '<abbr></abbr>';
        html += '<abbr></abbr>';
        html += '<abbr></abbr>';
    }
    else if( type == 2 ){
        html += '<abbr style="padding-top:0px;"><img src="${bath}/static/img/look.png" width="50" height="50" /></abbr>';
        html += '<abbr class="invenName"></abbr>';
        html += '<abbr class="invenType"></abbr>';
        html += '<abbr class="invenId"></abbr>';
        html += '<abbr class="invenBrand"></abbr>';
        html += '<abbr class="invenSeller"></abbr>';
        html += '<abbr class="invenBrand"></abbr>';
        html += '<abbr><input type="text" class="inventory" /></abbr>';
        html += '<abbr style="width:300px;"><input type="button" value="移除" /></abbr></abbr>';
    }
    else if( type == 3 ){
        html += '<abbr style="width:30px;"><input name="invenListCheck_add" type="checkbox" /></abbr>';
        html += '<abbr style="padding-top:0px;"><img src="${bath}/static/img/look.png" width="50" height="50" /></abbr>';
        html += '<abbr class="invenName"></abbr>';
        html += '<abbr class="invenType"></abbr>';
        html += '<abbr class="invenId"></abbr>';
        html += '<abbr class="invenBrand"></abbr>';
        html += '<abbr class="invenSeller"></abbr>' ;
        html += '</abbr>';
    }
    else if( type == 4 ){
        html += '<abbr class="invenName"></abbr>';
        html += '<abbr class="invenType"></abbr>';
        html += '<abbr class="invenId"></abbr>';
        html += '<abbr class="invenNum"></abbr>';
        html += '<abbr class="invenNumUsed"></abbr>';
    }
    else if( type == 5 ){
        html += '<abbr style="width:150px;" class="invenName"></abbr>';
        html += '<abbr class="invenType"></abbr>';
        html += '<abbr class="invenId"></abbr>';
        html += '<abbr class="invenNum"></abbr>';
        html += '<abbr class="invenNumUsed"></abbr>';
        html += '<abbr class="invenNumUsed"></abbr>';
        html += '<abbr class="invenNumUsed">' ;
        html += '<select class="myRequestTodoSelect">' ;
        html += '<option selected="selected" value="0"></option>';
        html += '<option value="1"></option>';
        html += '<option value="2"></option>';
        html += '</select>';
        html += '</abbr>';
    }
    else if( type == 6 ){
        html += '<abbr class="invenName"></abbr>';
        html += '<abbr class="invenType"></abbr>';
        html += '<abbr class="invenId"></abbr>';
        html += '<abbr class="invenNum"></abbr>';
        html += '<abbr class="invenNumUsed"></abbr>';
        html += '<abbr></abbr>';
    }
    else if ( type == 7 ){
        html += '<abbr style="width:50px;"><input name="itemsListCheck" type="checkbox" /></abbr>';
        html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /></abbr>';
        html += '<abbr></abbr>';
        html += '<abbr></abbr>';
        html += '<abbr></abbr>';
        html += '<abbr></abbr>';
        html += '<abbr class="ifInOut"></abbr>';
        html += '<abbr></abbr>';
        html += '<abbr></abbr>';
        html += '<input value="上架" type="button" disabled="disabled" class="inOut" />';
        html += '<abbr></abbr>';
        html += '</abbr>';
        html += '<abbr></abbr>';
    }
    else if ( type == 8 ){
        html += '<abbr style="width:50px;"><input name="invenListCheck" type="checkbox" /></abbr>';
        html += '<abbr style="padding-top:0px;"><img src="../static/img/look.png" width="50" height="50" /></abbr>';
        html += '<abbr class="invenName"></abbr>';
        html += '<abbr class="invenType"></abbr>';
        html += '<abbr class="invenId"></abbr>';
        html += '<abbr class="invenNum"></abbr>';
        html += '<abbr class="invenNumUsed"></abbr>';
        html += '<abbr class="invenBrand"></abbr>';
        html += '<abbr class="invenSeller"></abbr>';
        html += '<abbr class="invenName"></abbr>';
        html += '<abbr class="invenId"></abbr>';
        html += '<abbr style="width:300px;">';
        html += '<select class="invenListSelect">';
        html += '<option value="0" selected="selected">可选</option>';
        html += '<option style="display: none;" value="1">修改库存</option>';
        html += '<option value="2">删除</option>';
        html += '</select></abbr>';
    }
    else if ( type == 9 ){
        var htm = '<li>';

            htm += '</li>';
        return htm;
    }
    else if ( type == 10 ){
        html += '<abbr style="width:30px;"><input name="invenListCheck_add" type="checkbox" /></abbr>';
        html += '<abbr style="padding-top:0px;"><img src="${bath}/static/img/look.png" width="50" height="50" /></abbr>';
        html += '<abbr class="invenName"></abbr>';
        html += '<abbr class="invenType"></abbr>';
        html += '<abbr class="invenId"></abbr>';
        html += '<abbr class="invenBrand"></abbr>';
        html += '<abbr class="invenSeller"></abbr>' ;
        html += '</abbr>';
    }
    else if( type == 'inventory_notHandled' ){
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
    }
    else if( type == 'inventory_done' ){
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
    }
    else if( type == 'inventory_myRequest' ){
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
        html += '<abbr>' +  + '</abbr>';
    }
    else if( type == "memberUbaoSendForm" ){
        html += '<abbr></abbr>';
    }
    else if( type == "memberUbaoSendFormDetail" ){
        html += '<abbr></abbr>';
    }
    else if( type == "memberUbaoReduceForm" ){
        html += '<abbr></abbr>';
    }
    else if( type == "memberUbaoReduceFormDetail" ){
        html += '<abbr></abbr>';
    }
    else if( type == "ubaosendForm" ){
        html += '<abbr></abbr>';
    }
    else if( type == "baseDataform" ){
        html += '<abbr></abbr>';
    }
    html += '</dd>';
    return html;
}
function appendHtml(str,type,data,perpage,reason,batchId,callbackChecked) {
    $("#" + str).empty();
    for (var i = 0; i < perpage; i++) {
        var html = '';
        var htm = '';
        html += "<dd>";
        htm += '<li>';
        if (type == 0) {
            if (reason == '邮豆发放' || reason == '邮豆扣减') {
                html += '<abbr style="overflow: hidden; width: 250px;" class="myBillListName">' + enterpriseName + '</abbr>';
            }
            else {
                html += '<abbr style="overflow: hidden; width: 250px;" class="myBillListName">' + data[i].outName + '</abbr>';
            }
            html += '<abbr class="myBillListCreattime">' + handleDate_prev(new Date(data[i].createTime)) + "  " + handleDate_next(new Date(data[i].createTime)) + '</abbr>';
            html += '<abbr class="myBillListAccNum">' + handleUndefined(data[i].code) + '</abbr>';
            html += '<abbr class="myBillListState">已完成</abbr>';
            html += '<abbr class="myBillListPayway">邮豆</abbr>';
            if (data[i].fee != undefined) {
                html += '<abbr class="myBillListMoneySum">' + data[i].fee + '</abbr>';
            }
            else {
                html += '<abbr class="myBillListMoneySum">' + handleUndefined(data[i].price) + '</abbr>';
            }
            if (reason == '邮豆发放') {
                if (data[i].ucoinCount !== undefined) {
                    html += '<abbr class="myBillListNumSum">' + data[i].ucoinCount + '</abbr>';
                } else {
                    html += '<abbr class="myBillListNumSum">1</abbr>';
                }
            } else if (reason == '财富分配') {
                if (data[i].count !== undefined) {
                    html += '<abbr class="myBillListNumSum">' + data[i].count + '</abbr>';
                } else {
                    html += '<abbr class="myBillListNumSum">1</abbr>';
                }
            }
            else {
                html += '<abbr class="myBillListNumSum">1</abbr>';
            }
            html += '<abbr style="width:0px; overflow:hidden;" class="myBillListReason">' + reason + '</abbr>';
            html += '<abbr style="width:0px; overflow:hidden;" class="myBillLisBusinessType">' + handleUndefined(data[i].sendType) + '</abbr>';
            html += '<abbr style="width:0px; overflow:hidden;" class="myBillLisPayTime"></abbr>';
            html += '<abbr style="width:0px; overflow:hidden;" class="myBillListNote">' + handleUndefined(data[i].remark) + '</abbr>';
            if (reason == '邮豆扣减') {
                html += '<abbr style="width:0px; overflow:hidden;" class="myBillListTarget">' + handleUndefined(data[i].customerInfo.idCard) + '</abbr>';
                html += '<abbr style="width:0px; overflow:hidden;" class="myBillListTargetName">' + handleUndefined(data[i].customerInfo.name) + '</abbr>';
            }
            else {
                html += '<abbr style="width:0px; overflow:hidden;" class="myBillListTarget">' + handleUndefined(data[i].inName) + '</abbr>';
            }

            html += '<abbr style="width:0px; overflow:hidden;" class="myBillListBatchId">' + data[i].id + '</abbr>';
            html += '<abbr class="myBillListCheck"><input type="button" value="查看详情" class="detail" /></abbr>';
        }
        else if (type == 1) {
            html += '<abbr>' + data[i].inName + '</abbr>';
            html += '<abbr>' + data[i].fee + '</abbr>';
        }
        else if (type == 'bill_second') {
            html += '<abbr>' + data[i].customerInfo.idCard + '</abbr>';
            html += '<abbr>' + handleUndefined(data[i].customerInfo.name) + '</abbr>';
            html += '<abbr>' + data[i].price + '</abbr>';
        }
        else if (type == 2) {
            html += '<abbr class="invenName">' + 1 + '</abbr>';
            html += '<abbr class="invenType">' + 1 + '</abbr>';
            html += '<abbr class="invenId">' + 1 + '</abbr>';
            html += '<abbr class="invenBrand">' + 1 + '</abbr>';
            html += '<abbr class="invenSeller">' + 1 + '</abbr>';
            html += '<abbr class="invenBrand">' + 1 + '</abbr>';
            html += '<abbr><input type="text" class="inventory" />' + 1 + '</abbr>';
            html += '<abbr style="width:300px;"><input type="button" value="移除" /></abbr></abbr>';
        }
        else if (type == 3) {
            html += '<abbr style="width:30px;"><input name="invenListCheck" type="checkbox" /></abbr>';
            html += '<abbr style="padding-top:0px;"><img src="${bath}/static/img/look.png" width="50" height="50" /></abbr>';
            html += '<abbr class="invenName">' + data[i].goodsName + '</abbr>';
            html += '<abbr class="invenType">' + data[i].specString + '</abbr>';
            html += '<abbr class="invenId">' + data[i].goodsNumber + '</abbr>';
            html += '<abbr class="invenBrand">' + handleUndefined(data[i].goodsBrand) + '</abbr>';
            html += '<abbr class="invenSeller">' + data[i].goodsMerchants + '</abbr>';
            html += '</abbr>';
        }
        else if (type == 4) {
            var status = handleReqStatus(data[i].status);
            html += '<input type="hidden" value="' + data[i].id + '">';
            html += '<abbr class="invenName">' + handleDate_prev(new Date(data[i].createTime)) + '  ' + handleDate_next(new Date(data[i].createTime)) + '</abbr>';
            html += '<abbr class="invenType">' + data[i].code + '</abbr>';
            html += '<abbr class="invenId">' + status + '</abbr>';
            html += '<abbr class="invenNum">' + data[i].fee + '</abbr>';
            html += '<abbr class="invenNumUsed">' + data[i].remark + '</abbr>';
        }
        else if (type == 5) {
            var status = handleTodoStatus(data[i].status);
            html += '<input type="hidden" value="' + data[i].id + '">';
            html += '<abbr style="width:150px;" class="invenName">' + data[i].enterpriseName + '</abbr>';
            html += '<abbr class="invenType">' + handleDate_prev(new Date(data[i].createTime)) + '  ' + handleDate_next(new Date(data[i].createTime)) + '</abbr>';
            html += '<abbr class="invenId">' + data[i].code + '</abbr>';
            html += '<abbr class="invenNum">' + status + '</abbr>';
            html += '<abbr class="invenNumUsed">' + data[i].fee + '</abbr>';
            html += '<abbr class="invenNumUsed">' + data[i].remark + '</abbr>';
            if (status == "待审核") {
                html += '<abbr class="invenNumUsed">';
                html += '<select class="myRequestTodoSelect">';
                html += '<option selected="selected" value="0">' + '可选' + '</option>';
                html += '<option value="1">' + '同意' + '</option>';
                html += '<option value="2">' + '拒绝' + '</option>';
                html += '</select>';
                html += '</abbr>';
            } else if (status == "已审核") {
                html += '<abbr class="invenNumUsed">';
                html += '<select class="myRequestTodoSelect">';
                html += '<option selected="selected" value="0">' + '可选' + '</option>';
                html += '<option value="3">' + '支付' + '</option>';
                html += '</select>';
                html += '</abbr>';
            }
        }
        else if (type == 6) {
            var status = handleReqStatus(data[i].status);
            html += '<input type="hidden" value="' + data[i].id + '">';
            html += '<abbr class="invenName">' + data[i].enterpriseName + '</abbr>';
            html += '<abbr class="invenType">' + handleDate_prev(new Date(data[i].createTime)) + '  ' + handleDate_next(new Date(data[i].createTime)) + '</abbr>';
            html += '<abbr class="invenId">' + data[i].code + '</abbr>';
            html += '<abbr class="invenNum">' + status + '</abbr>';
            html += '<abbr class="invenNumUsed">' + data[i].fee + '</abbr>';
            html += '<abbr>' + data[i].remark + '</abbr>';
        }
        else if (type == 7) {
            var IsAdded = '';
            var operator = 1;
            if (typeof(data[i].goodsInfoAdded) != 'undefined') {
                if (data[i].goodsInfoAdded == "0") {
                    IsAdded = '否';
                    operator = '上架';
                }
                if (data[i].goodsInfoAdded == "1") {
                    IsAdded = '是';
                    operator = '下架'
                }
            }

            var content = '';
            var h = '';
            var array = data[i].specString.split(",");
            for (var j = 0; j < array.length; j++) {
                var text = /(?:null)/;
                var flag = text.test(array[j]);
                if (!flag) {
                    h = array[j];
                    if (array.length > 1) {
                        h += '...';
                    }
                    break;
                }

            }
            for (var a = 0; a < array.length; a++) {
                var text = /(?:null)/;
                var flag = text.test(array[a]);
                if (!flag) {
                    content += array[a] + '\n';
                }
            }

            html += '<abbr style="width:50px;"><input name="itemsListCheck" type="checkbox" /></abbr>';
            html += '<abbr style="padding-top:0px;"><img src="' + data[i].goodsInfoImgId + '" width="50" height="50" /></abbr>';
            html += '<abbr>' + data[i].goodsInfoName + '</abbr>';
            html += '<abbr title="' + content + '">' + h + '</abbr>';
            html += '<abbr>' + data[i].goodsInfoItemNo + '</abbr>';
            html += '<abbr>' + data[i].goodsInfoPreferPrice + '</abbr>';
            html += '<abbr class="ifInOut">' + IsAdded + '</abbr>';
            html += '<abbr>' + data[i].goodsInfoTypeName + '</abbr>';
            html += '<abbr>' + handleUndefined(data[i].goodsBrand) + '</abbr>';
            html += '<abbr>' + handleUndefined(data[i].thirdName) + '</abbr>';
            html += '<abbr>';
            if (typeof callbackChecked == 'function') {
                var flag = callbackChecked();
                if (flag) {
                    html += '<input style="background:#eee;color:#333;" value="' + operator + '" type="button" class="inOut" />';
                }
                else {
                    html += '<input value="' + operator + '" type="button" class="inOut"/>';
                }
            }

            html += '<input type="hidden" value="' + data[i].goodsInfoId + '"/>';
            html += '</abbr>';
        }
        else if (type == 8) {
            html += '<input type="hidden" value="' + data[i].goodsInfoId + '">'
            html += '<abbr style="width:50px;"><input name="invenListCheck" type="checkbox" class="accountNumber"/></abbr>';
            html += '<abbr style="padding-top:0px;"><img src="' + data[i].goodsImg + '" width="50" height="50" /></abbr>';
            html += '<abbr class="invenName">' + data[i].goodsInfoName + '</abbr>';

            var content = '';
            var h = '';
            var array = data[i].specString.split(",");
            for (var j = 0; j < array.length; j++) {
                var text = /(?:null)/;
                var flag = text.test(array[j]);
                if (!flag) {
                    h = array[j];
                    if (array.length > 1) {
                        h += '...';
                    }
                    break;
                }

            }
            for (var a = 0; a < array.length; a++) {
                var text = /(?:null)/;
                var flag = text.test(array[a]);
                if (!flag) {
                    content += array[a] + '\n';
                }
            }
            html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';
            html += '<abbr class="invenId">' + data[i].goodsNumber + '</abbr>';
            html += '<abbr class="invenNum">' + data[i].inventory + '</abbr>';
            html += '<abbr class="invenNumUsed">' + data[i].available + '</abbr>';
            html += '<abbr class="invenBrand">' + handleUndefined(data[i].goodsBrand) + '</abbr>';
            html += '<abbr class="invenSeller">' + data[i].goodsMerchants + '</abbr>';
            html += '<abbr style="width:300px;">';
            html += '<select class="invenListSelect">';
            html += '<option value="0" selected="selected">可选</option>';
            html += '<option class="changeInventory" value="1">修改库存</option>';
            html += '<option value="2">删除</option>';
            html += '</select></abbr>';
        }
        else if (type == 9) {

            htm += '<abbr class="showImg"><input type="hidden" value="' + data[i].goodsInfoId + '"><img src="' + data[i].goodsInfoImgId + '" width="100" height="99" /></abbr>';
            htm += '<abbr class="imglist">';
            htm += '<i class="changeLeft"></i>';
            htm += '<s><span>';
            //

            data[i].images.map(function (img) {
                htm += '<img src="' + img.imageThumName + '" width="100" height="99" />';
                htm += '<input type="hidden" class="imageInName" value="' + img.imageInName + '"> ';
            })

            htm += '</span></s>';
            htm += '<i class="changeRight"></i>';
            htm += '</abbr>';
            htm += '<abbr><span>' + data[i].goodsInfoPreferPrice + '</span>邮豆</abbr>';
            htm += '<abbr>' + data[i].goodsInfoName + '</abbr>';
            htm += '<abbr><input type="button" value="立即购买" class="buy allclickButton" /></abbr>';
        }
        else if (type == 10) {
            //var flag = 0;
            //var length = $("#count input[type='checkbox']").length;
            //if( length > 0 ){
            //    for(var j = 0;j < length; j++){
            //        var id = $( $("#count").children()[j] ).find("input[type='checkbox']").val();
            //        if( data[i].goodsId == id ){
            //            flag = 1;
            //        }
            //    }
            //}
            //
            //if( flag ){
            //    html += '<abbr style="width:30px;"><input name="invenListCheck" checked="checked" type="checkbox" value="' + data[i].goodsId + '"/></abbr>';
            //}
            //else{
            //    html += '<abbr style="width:30px;"><input name="invenListCheck" type="checkbox" value="' + data[i].goodsId + '"/></abbr>';
            //}

            html += '<abbr style="width:30px;"><input name="invenListCheck_add" type="checkbox" value="' + data[i].goodsId + '"' + '/></abbr>';
            html += '<abbr style="padding-top:0px;"><img src="${bath}/static/img/look.png" width="50" height="50" /></abbr>';
            html += '<abbr class="invenName" style="padding-top: 0!important;">' + data[i].goodsName + '</abbr>';

            var content = '';
            var h = '';
            var array = data[i].specString.split(",");
            for (var j = 0; j < array.length; j++) {
                var text = /(?:null)/;
                var flag = text.test(array[j]);
                if (!flag) {
                    h = array[j];
                    if (array.length > 1) {
                        h += '...';
                    }
                    break;
                }

            }
            for (var a = 0; a < array.length; a++) {
                var text = /(?:null)/;
                var flag = text.test(array[a]);
                if (!flag) {
                    content += array[a] + '\n';
                }
            }
            html += '<abbr class="invenType" title="' + content + '">' + h + '</abbr>';

            html += '<abbr class="invenId">' + data[i].goodsNumber + '</abbr>';
            html += '<abbr class="invenBrand">' + data[i].goodsBrand + '</abbr>';
            html += '<abbr class="invenSeller">' + data[i].goodsMerchants + '</abbr>';
            html += '</abbr>';
        }
        else if (type == 'inventory_notHandled') {
            var bill_type = handleInventoryBill(data[i].billType);
            html += '<input type="hidden" value="' + data[i].billId + '">';
            html += '<abbr class="vocherState">' + bill_type + '</abbr>';
            html += '<abbr class="vocherCode">' + data[i].code + '</abbr>';
            try{
                html += '<abbr>' + data[i].tansferInfo.creatorName + '</abbr>';
            }
            catch(e){
                html += '<abbr></abbr>';
            }
            html += '<abbr>' + handleDate_prev(new Date(data[i].createTime)) + '</abbr>';
            html += '<abbr>' + handleInventoryStatus(data[i].billStatus) + '</abbr>';
            try{
                html += '<abbr>' + handleUndefined(data[i].tansferInfo.currentName) + '</abbr>';
            }
            catch(e){
                html += '<abbr></abbr>';
            }
        }
        else if (type == 'inventory_done') {
            var bill_type = handleInventoryBill(data[i].billType);
            html += '<input type="hidden" value="' + data[i].billId + '">';
            html += '<abbr class="vocherState">' + bill_type + '</abbr>';
            html += '<abbr class="vocherCode">' + data[i].code + '</abbr>';
            html += '<abbr>' + data[i].tansferInfo.creatorName + '</abbr>';
            html += '<abbr>' + handleDate_prev(new Date(data[i].createTime)) + '</abbr>';
            html += '<abbr>' + handleInventoryStatus(data[i].billStatus) + '</abbr>';
            html += '<abbr>' + handleUndefined(data[i].tansferInfo.currentName) + '</abbr>';
        }
        else if (type == 'inventory_myRequest') {
            var bill_type = handleInventoryBill(data[i].billType);
            html += '<input type="hidden" value="' + data[i].billId + '">';
            html += '<abbr class="vocherState">' + bill_type + '</abbr>';
            html += '<abbr class="vocherCode">' + data[i].code + '</abbr>';
            html += '<abbr>' + data[i].tansferInfo.creatorName + '</abbr>';
            html += '<abbr>' + handleDate_prev(new Date(data[i].createTime)) + '</abbr>';
            html += '<abbr>' + handleInventoryStatus(data[i].billStatus) + '</abbr>';
            html += '<abbr>' + handleUndefined(data[i].tansferInfo.currentName) + '</abbr>';
        }
        else if (type == "memberUbaoSendForm") {
            var trHtml = '<tr>';
            trHtml += '<td class="idCard">' +  data[i].idCard + '</td>';
            trHtml += '<td>' + data[i].fullname + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].grandAmount) + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].marketPrice,2) + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].salePrice,2) + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].price,2) + '</td>';
            trHtml += '<td><span class="checkDetail">查看明细</span></td>';
            trHtml += '</tr>';
        }
        else if (type == "memberUbaoSendFormDetail") {
            var trHtml = '<tr>';
            trHtml += '<td>'+ data[i].idCard + '</td>'
            trHtml += '<td>' + data[i].fullname + '</td>'
            trHtml += '<td>' +  handleDate_prev(new Date(data[i].createTime)) + "  " + handleDate_next(new Date(data[i].createTime)) + '</td>'
            trHtml += '<td>' + data[i].grandEnterprise + '</td>'
            trHtml += '<td>' + handleUndefinedZero(data[i].marketPrice,2) + '</td>'
            trHtml += '<td>' + handleUndefinedZero(data[i].salePrice,2) + '</td>'
            trHtml += '<td>' + handleUndefinedZero(data[i].price,2) +'</td>'
            trHtml += '</tr>'
        }
        else if (type == "memberUbaoReduceForm") {
            var trHtml = '<tr>';
            trHtml += '<td class="idCard">' +  data[i].idCard + '</td>';
            trHtml += '<td>' + data[i].fullname + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].orderAmount)  + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].totalConsumePrice,2) + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].backAmount) + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].totalRefundPrice,2) + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].resePrice,2) + '</td>';
            trHtml += '<td><span class="checkDetailReduce">查看明细</span></td>';
            trHtml += '</tr>';
        }
        else if (type == "memberUbaoReduceFormDetail") {
            var trHtml = '<tr>';
            trHtml += '<td>'+ data[i].idCard + '</td>'
            trHtml += '<td>' + data[i].fullname + '</td>'
            if(data[i].type=="UCOIN_CONSUME"){
                trHtml += '<td>订单</td>'
            }else{
                trHtml += '<td>退单</td>'
            }
            trHtml += '<td>' +  handleDate_prev(new Date(data[i].createTime)) + "  " + handleDate_next(new Date(data[i].createTime)) + '</td>'
            trHtml += '<td>' + handleUndefinedZero(data[i].orderCode) + '</td>'
            trHtml += '<td>' + handleUndefinedZero(data[i].orderPrice,2) + '</td>'
            trHtml += '<td>' + handleUndefinedZero(data[i].backPrice,2) + '</td>'
            trHtml += '</tr>'
        }
        else if (type == "ubaosendForm") {
            var trHtml = '<tr>';
            trHtml += '<td class="idCard">' +  data[i].enterpriseInfo.enterpriseName + '</td>';
            trHtml += '<td>' + data[i].customerInfo.idCard + '</td>';
            trHtml += '<td>' + handleUndefined(data[i].customerInfo.name)  + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].marketPrice,2) + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].salesPrice,2) + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].price,2) + '</td>';
            trHtml += '<td>' + handleUndefined(data[i].remark) + '</td>';
            trHtml += '<td>' +  handleDate_prev(new Date(data[i].createTime)) + "  " + handleDate_next(new Date(data[i].createTime)) + '</td>'
            trHtml += '<td>' + handleUndefined(data[i].typeName) + '</td>';
            var paramJson = JSON.parse(data[i].paramJson);

            //修正后台返回数据顺序
            var arr = [];
            $(".dynamicHead").each(function(){
                var head = $(this).html();
                arr[head] = '';
                for( var key in paramJson ){
                    if( key == head ){
                        arr[key] = paramJson[key] ;
                    }
                }
            });
            console.log(arr);
            for( x in arr ){
                if( x != 'remove' ){
                    trHtml += '<td>' + arr[x] + '</td>';
                }
            }
            trHtml += '</tr>';
        }
        else if (type == "baseDataform") {
            var trHtml = '<tr>';
            trHtml += '<td>' +  handleUndefined(data[i].grandEnterprise) + '</td>';
            trHtml += '<td>' + data[i].newCustomerAmount + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].expenditure)  + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].totalMarketPrice,2) + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].totalSalePrice,2) + '</td>';
            trHtml += '<td>' + handleUndefinedZero(data[i].totalPrice,2) + '</td>';
            trHtml += '</tr>';
        }
        html += '</dd>';
        htm += '</li>';
        if (type == 9) {
            $("#" + str).append(htm);
        }
        else if (type == 'memberUbaoSendForm') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'memberUbaoSendFormDetail') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'memberUbaoReduceForm') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'memberUbaoReduceFormDetail') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'ubaosendForm') {
            $("#" + str).append(trHtml);
        }
        else if (type == 'baseDataform') {
            $("#" + str).append(trHtml);
        }
        else {
            $("#" + str).append(html);
        }

    }
}

function checkUndefined(str){
    if( str == 'undefined' ){
        str = '';
    }
    return str;
}
function jPagesGet(content,target,perpage){
    $("#"+content).jPages({
        containerID : target,
        perPage:perpage
    });
}

function handleReqStatus(type){
    if( type == 'DENIED' ){
        return '已拒绝';
    }
    if( type == 'PAYED' ){
        return '已支付'
    }
    if( type == 'PASSED' ){
        return '已通过';
    }
    if( type == 'APPLIED' ){
        return '已申请'
    }
}
function handleTodoStatus(type){
    if( type == 'PASSED' ){
        return '已审核';
    }
    else{
        return '待审核';
    }
}

function handleInventoryBill(type){
    if( type == 'REPLENISHMENT' ){
        return '补货单';
    }
    if( type == 'LESS_REPORT' ){
        return '报损单';
    }
    if( type == 'MORE_REPORT' ){
        return '报溢单';
    }
    if( type == 'INVENTORY_TRANSFER' ){
        return '调拨单';
    }
}

function handleInventoryStatus(type){
    if( type == 'CHECKING' ){
        return '审核中';
    }
    if( type == 'CHECKED' ){
        return '已审核';
    }
    if( type == 'TERMINATED' ){
        return '已终止';
    }
    if( type == 'FINISHED' ){
        return '已完成';
    }
    if( type == 'WAIT_DELIVERY' ){
        return '待发货';
    }
    if( type == 'WAIT_RECEIVER' ){
        return '待收货';
    }
    if( type == 'WAIT_EDIT' ){
        return '待修改';
    }

}