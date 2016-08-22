$(document).ready(function(){
    $("input").attr("autocomplete","off");

    //全局变量
});






function coverHtml(){
    $(parent.document.getElementById("cover")).show();
    $(parent.document.getElementById("cover2")).show();
    $("#cover1").show();
}
function discoverHtml(){
    $(parent.document.getElementById("cover")).hide();
    $(parent.document.getElementById("cover2")).hide();
    $("#cover1").hide();
}

function delete_warning_alert(){
    var txt = "您确认要删除吗";
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
}
function password_alert(){
    var txt = "密码输入有误，请重新输入";
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
}

function money_error_alert(url){
    var txt=  "余额不足";
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error,{
            onOk:function(){
                window.location.href = url;
            }
        }
    );
}

function data_error_alert(text){
    data_type_alert(text,'warning');
}

function data_type_alert(txt,type){
    if(type == 'success'){
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success);
    }
    else if( type == 'warning' ){
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
    }
    else if( type == 'error' ){
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error);
    }
}

function createDuplicate_alert(){
    var txt = "创建失败，账号重复";
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
}

function captcha_alert(){
    var txt = "验证码输入有误，请重新输入";
    var count = $(".popBox").length;
    if( count < 1 ){
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
    }

}


function password_alert(){
    var txt = "密码输入有误，请重新输入";
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning);
}

function ensure_alert(url){
    var txt=  "操作成功";
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
            onOk:function(){
                discoverHtml();
                window.location.href = url;
            }
        }
    );
}
function error_alert(url){
    type_alert(url,"操作失败",'error')
}

function type_alert(url,txt,type){
    var option = {
            title: "提示",
            btn: parseInt("0011",2),
        onOk: function(){
            window.location.href = url;
        }
    }
    window.wxc.xcConfirm(txt,type,option);
}

function input_alert(url){
    var txt=  "输入不能为空";
    var option = {
        title: "提示",
        btn: parseInt("0011",2),
        onOk: function(){
            window.location.href = url;
        }
    }
    window.wxc.xcConfirm(txt, "warning", option);
}

function selfPick_alert(type,text,okArr,cancelArr){
    var txt = text;
    var option = {
        title: "提示",
        btn: parseInt("0011",2),
        onOk: function(){
            okArr[1]();
        },
        onCancel:function(){
            cancelArr[1]();
        }
    }
    window.wxc.xcConfirm(txt, type, option);
    $(".ok").html(okArr[0]);
    $(".cancel").html(cancelArr[0]);
}

function input_error_alert(txt,fun){
    window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error,{
        onOk:function(){
            discoverHtml();
            fun();
        }
    });
}

function response_ensure_alert(type,txt,fun1,fun2){
    var a = function(){
        var lock = true;
        if( lock == true ){
            lock = false;
            if( type == 'success' ){
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
                    onOk:function(){
                        lock = true;
                        try{
                            fun1();
                        }
                        catch(e){

                        }
                    },
                    onClose:function(){
                        lock = true;
                        try{
                            fun2();
                        }
                        catch(e){

                        }
                    }
                });
            }
            if( type == 'error' ){
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error,{
                    onOk:function(){
                        lock = true;
                        try{
                            fun1();
                        }
                        catch(e){

                        }
                    },
                    onClose:function(){
                        lock = true;
                        try{
                            fun2();
                        }
                        catch(e){

                        }
                    }
                });
            }
            if( type == 'warning' ){
                window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning,{
                    onOk:function(){
                        lock = true;
                        try{
                            fun1();
                        }
                        catch(e){

                        }
                    },
                    onClose:function(){
                        lock = true;
                        try{
                            fun2();
                        }
                        catch(e){

                        }
                    }
                });
            }
        }
    }
    a();
    return a;
}

function response_OkCancel_alert(type,txt,okFun,cancelFun){
    if( type == 'success' ){
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.success,{
            onOk:function(){
                discoverHtml();
                okFun();
            },
            onCancel:function(){
                discoverHtml();
                cancelFun();
            }
        });
    }
    if( type == 'error' ){
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.error,{
            onOk:function(){
                discoverHtml();
                okFun();
            },
            onCancle:function(){
                discoverHtml();
                cancelFun();
            }
        });
    }
    if( type == 'warning' ){
        window.wxc.xcConfirm(txt, window.wxc.xcConfirm.typeEnum.warning,{
            onOk:function(){
                discoverHtml();
                okFun();
            },
            onCancle:function(){
                discoverHtml();
                cancelFun();
            }
        });
    }
}

function user_error_alert(url){
    var txt=  "该用户不存在";
    var option = {
        title: "提示",
        btn: parseInt("0011",2),
        onOk: function(){
            window.location.href = url;
        }
    }
    window.wxc.xcConfirm(txt, "error", option);
}

function handleDate_prev(time){
    var year = time.getFullYear();
    var month = time.getMonth() + 1;
    var day = time.getDate();
    if( month < 9 ){
        month = '0' + month;
    }
    if( day < 10 ){
        day = '0' + day;
    }
    return year + '-' + month + '-' + day;
}

function handleDate_next(time){
    var hour = time.getHours();
    var minute = time.getMinutes();
    var second = time.getSeconds();
    if(minute < 10){
        minute = '0' + minute;
    }
    if( second < 10 ){
        second = '0' + second;
    }
    return hour + ':' + minute + ':' + second;
}

function handleBackReason(text){
    if( text == 'NOT_WANT_BUY' ){
        return '不想买了';
    }
    if( text == 'WRONG_INFORMATION' ){
        return '收货人信息有误';
    }
    if( text == 'GOODS_DAMAGE' ){
        return '商品损坏';
    }
    if( text == 'GOODS_DESC_ERROR' ){
        return '收到商品与描述不符';
    }
    if( text == 'NOT_DELIVERY_TIMED' ){
        return '未按指定时间发货';
    }
    if( text == 'OTHER' ){
        return '其他';
    }
}

function backCheck_Handle(num){
    if( num == 0){
        return '退货申请';
    }
    if( num == 1){
        return '同意退货';
    }
    if( num == 2){
        return '拒绝退货';
    }
    if( num == 3){
        return '待收货';
    }
    if( num == 4){
        return '退单结束';
    }
    if( num == 6){
        return '审核退款';
    }
    if( num == 7){
        return '拒绝退款';
    }
    if( num == 8){
        return '收货失败';
    }
    if( num == 9){
        return '带客户填写物流地址';
    }
    if( num == 10 ){
        return '退款成功';
    }
    if( num == 11 ){
        return '退货待退款';
    }
    if( num == 12 ){
        return '退款待退款';
    }
}

function orderStatus_backHandle(str){
    if( str == '待付款'){
        return 'SUBMITED';
    }
    if( str == '待发货'){
        return 'PAYED';
    }
    if( str == '已发货'){
        return 'DELIVERED';
    }
    if( str == '已完成'){
        return 'RECIEVED';
    }
    if( str == '已取消'){
        return 'CANCELED';
    }
    if( str == '待发货'){
        return 'WAIT_DELIVER';
    }
    if( str == '待提货'){
        return 'WAIT_PICKUP';
    }
    if( str == '待收货'){
        return 'RETURN_WAIT_RECEIVE';
    }
    if( str == '全部订单' ){
        return '';
    }
}

function orderStatus_handle(str){
    if( str == 'REFUND_APPLIED' ){
        return '审核退款';
    }
    if( str == 'SUBMITED'){
        return '待付款';
    }
    if( str == 'PAYED'){
        return '待发货';
    }
    if( str == 'DELIVERED'){
        return '已发货';
    }
    if( str == 'RECIEVED'){
        return '已完成';
    }
    if( str == 'CANCELED'){
        return '已取消';
    }
    if( str == 'WAIT_DELIVER'){
        return '待发货';
    }
    if( str == 'WAIT_PICKUP'){
        return '待提货';
    }
    if( str == 'RETURN_APPLIED'){
        return '退货申请';
    }
    if( str == 'RETURN_AGREED'){
        return '同意退货';
    }
    if( str == 'RETURN_DENIED'){
        return '拒绝退货';
    }
    if( str == 'RETURN_DELIVERING'){
        return '待收货';
    }
    if( str == 'RETURN_FINISHED'){
        return '退单结束';
    }
    if( str == 'REFUND_APPLIED'){
        return '审核退款';
    }
    if( str == 'REFUND_DENIED'){
        return '拒绝退款';
    }
    if( str == 'RETURN_DELIVER_FAILURE'){
        return '收货失败';
    }
    if( str == 'RETURN_WAIT_DELIVER_INFO'){
        return '待客户填写物流地址';
    }
    if( str == 'RETURN_WAIT_REFUND' ){
        return '退货待退款';
    }
    if( str == 'REFUND_FINISHED' ){
        return '退款成功';
    }
    if( str == 'REFUND_AGREED' ){
        return '同意退款';
    }
    if( str == 'RETURN_WAIT_RECEIVE'){
        return '待收货';
    }
    if( str == 'REFUND_WAIT_REFUND'){
        return '退款待退款';
    }
}

function handlebackCheck(str){
    if( str == 'RETURN_APPLIED'){
        return '退货申请';
    }
    if( str == 'RETURN_AGREED'){
        return '同意退货';
    }
    if( str == 'RETURN_DENIED'){
        return '拒绝退货';
    }
    if( str == 'RETURN_DELIVERING'){
        return '待收货';
    }
    if( str == 'RETURN_FINISHED'){
        return '退单结束';
    }
    if( str == 'REFUND_APPLIED'){
        return '审核退款';
    }
    if( str == 'REFUND_DENIED'){
        return '拒绝退款';
    }
    if( str == 'RETURN_WAIT_RECEIVE'){
        return '待收货';
    }
    if( str == 'RETURN_DELIVER_FAILURE'){
        return '收货失败';
    }
    if( str == 'RETURN_WAIT_DELIVER_INFO'){
        return '待客户填写物流地址';
    }
    if( str == 'REFUND_FINISHED' ){
        return '退款成功';
    }
    if( str == 'REFUND_AGREED' ){
        return '退款待退款';
    }
    if( str == 'RETURN_WAIT_REFUND' ){
        return '退货待退款';
    }
}

function handleBackWay(type){
    if( type == 0 ){
        return '快递';
    }
    if( type == 1 ){
        return
    }
}

function handleUndefined(str){
    if( str == undefined ){
        return '';
    }
    else{
        return str;
    }
}
function handleUndefinedZero(str,i){
    if( i == undefined ){
        i = 0;
    }
    if( str == undefined ){
        var a = 0;
        return a.toFixed(i);
    }
    else{
        return str.toFixed(i);
    }
}

Array.prototype.indexOf = function(val) {
    for (var i = 0; i < this.length; i++) {
        if (this[i] == val) return i;
    }
    return -1;
};
Array.prototype.remove = function(val) {
    var index = this.indexOf(val);
    if (index > -1) {
        this.splice(index, 1);
    }
    return this;
};


function moveWindow(discount){
    var _this = this;
    var inputLock = true;


    var windowHeight = window.parent.window.screen.height;
    var windowWidth = window.parent.window.screen.width;

    var domHeight = $(this).css("height");
    domHeight = parseInt(domHeight.substring(0,domHeight.length-2));

    var windowWidth = $(window).width();
    var addWidth = window.parent.window.screen.width - windowWidth;
    var domWidth = $(this).css("width");
    domWidth = parseInt(domWidth.substring(0,domWidth.length-2));

    var top = (windowHeight - domHeight)/2 - 250 + 'px';
    var left = (windowWidth - domWidth)/2 - addWidth/4 + 'px';

    //固定窗口位置
    $(this).addClass("moveWindow");
    $(this).css("position","fixed");
    $(this).css("top",'150px');
    $(this).css("left",'300px');

    $("input").focus(function(){
        inputLock = false;
    }).blur(function(){
        inputLock = true;
    });
    $("textarea").focus(function(){
        inputLock = false;
    }).blur(function(){
        inputLock = true;
    });
    $("select").focus(function(){
        inputLock = false;
    }).blur(function(){
        inputLock = true;
    });
    //限定可拖动窗口位置
    $(this).mousedown(function (event) {
        var _event = event;
        var domWidth = $(_this).css("width");
        var domHeight = $(_this).css("height");

            domWidth = parseInt(domWidth.substring(0,domWidth.length-2));
            domHeight = parseInt(domHeight.substring(0,domHeight.length-2));


        var isMove = true;

        var offsetLeft = $(this).css('left');
        var offsetTop = $(this).css('top');
        offsetLeft = parseInt(offsetLeft.substring(0,offsetLeft.length - 2));
        offsetTop = parseInt(offsetTop.substring(0,offsetTop.length - 2));
        var abs_x = event.pageX - offsetLeft;
        var abs_y = event.pageY - offsetTop;

        var mouseX = event.pageX;
        var mouseY = event.pageY;
        if( mouseY < offsetTop + discount * domHeight && mouseY > offsetTop ){
            $(document).mousemove(function (event) {
                if (isMove && inputLock ) {
                    var obj = $(_this);
                    var left = event.pageX - abs_x;
                    var top = event.pageY - abs_y;
                    if( left > 0 && left < windowWidth - domWidth && top > 0 ){
                        obj.css({'left':left, 'top':top});
                    }
                    else{

                    }

                }
            }).mouseup(
                function () {
                    isMove = false;
                }
            );
        }

    });
}

function handleParameter(a){
    if( a == 0 ){
        return 'TYPE_ENUM';
    }
    else if( a == 1 ){
        return 'TYPE_FLOAT';
    }
    else if( a == 2 ){
        return 'TYPE_TXT';
    }
}
function handleParameterRequired(a){
    if( a == 0 ){
        return false;
    }
    if( a == 1 ){
        return true;
    }
}
function JsonGroup(data,key){
    var arr = [];
    var response = '';
    data.map(function(object){
        if(key == object[0]){
            response = object[1];
        }
    });

    return response;
}

function handleSpecString(data){
    //规格处理
    var content = '';
    var h = '';
    var array = data.split(",");
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
    return [h,content];
}

function consoleNowTime(){
    return new Date().toString();
}


function xround(x, num){
    Math.round(x * Math.pow(10, num)) / Math.pow(10, num) ;
}

function handleCredential(txt){
    if( txt == 'NO_CREDENTIAL' ){
        return '无凭据';
    }
    if( txt == 'INVOICE' ){
        return '有发票';
    }
    if( txt == 'QUALITY_REPORT' ){
        return '有质检报告';
    }
}

function tryUndefined(a){
    try{
        a
        return a;
    }
    catch(e){
        console.log(e);
    }
}
function forbidBackSpace(e) {
    var ev = e || window.event; //获取event对象
    var obj = ev.target || ev.srcElement; //获取事件源
    var t = obj.type || obj.getAttribute('type'); //获取事件源类型
    //获取作为判断条件的事件类型
    var vReadOnly = obj.readOnly;
    var vDisabled = obj.disabled;
    //处理undefined值情况
    vReadOnly = (vReadOnly == undefined) ? false : vReadOnly;
    vDisabled = (vDisabled == undefined) ? true : vDisabled;
    //当敲Backspace键时，事件源类型为密码或单行、多行文本的，
    //并且readOnly属性为true或disabled属性为true的，则退格键失效
    var flag1 = ev.keyCode == 8 && (t == "password" || t == "text" || t == "textarea" || t == "search") && (vReadOnly == true || vDisabled == true);
    //当敲Backspace键时，事件源类型非密码或单行、多行文本的，则退格键失效
    var flag2 = ev.keyCode == 8 && t != "password" && t != "text" && t != "textarea" && t != "search" ;
    //判断
    if (flag2 || flag1) return false;
}
function IdentityCodeValid(code) {
    var city={11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江 ",31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北 ",43:"湖南",44:"广东",45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏 ",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外 "};
    var tip = "";
    var pass= true;
    if( !code || !/^[0-9]{6}(19[0-9]{2}|20[01]{1}[0-9]{1})(((0[469]{1}|(11))([0]{1}[1-9]{1}|[12]{1}[0-9]{1}|(30))|[02]{2}(([0]{1}[1-9]{1}|[1]{1}[0-9])|[2]{1}[0-8]{1})|(0[13578]{1}|(10)|(12))([0]{1}[1-9]{1}|[12]{1}[0-9]{1}|(30)|(31))))[0-9]{3}[0-9xX]{1}$/.test(code)){
        tip = "身份证号格式错误";
        pass = false;
    }

    else if(!city[code.substr(0,2)]){
        tip = "身份证地址编码错误";
        pass = false;
    }
    else{
       // 18位身份证需要验证最后一位校验位
        if(code.length == 18){
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 ];
            //校验位
            var parity = [ 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 ];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++)
            {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if(parity[sum % 11] != code[17]){
                tip = "校验位错误";
                pass =false;
            }
        }
    }

    return pass;
}

function inventoryList(id,name){
    var html = '';
    html += '<h1>查看范围<i></i></h1>';
    html += '<div class="DepotDetail">';
    html += '<ul><li>';
    html += '<a data-id="' + id + '">+</a>';
    html += '<input type="radio" name="DepotRadio"/>';
    html += '<span>' + name + '</span>';
    html += '</li></ul></div>';
    html += '<div><input class="allcancelButton" type="button" value="取消" id="chooseCancel"/> <input class="allseachButton" type="button" value="确定" id="chooseConfirm"/></div>';

    $( ".chooeseDepot").append(html);

    $( ".chooeseDepot" ).draggable();

    $("#choose").click(function(){
        coverHtml();
        $(".chooeseDepot").show();
    });
    $(".chooeseDepot div:first-child i,#chooseCancel").click(function(){
        discoverHtml();
        $(".chooeseDepot").hide();
    });

    $(".DepotDetail a").each(function(){
        if($(this).html()=="+"){
            $(this).siblings("ul").hide();
        }
    });

    $(document).on("click",".DepotDetail li a",function(){
        var _this = this;
        if( $(this).html() == '+' && $(this).parent().find("ul").length == 0){
            var enterpriseId = $(this).data("id");
            $.post("../web/api/inventory/getSonEnterpriseInfo",{
                enterpriseId:enterpriseId
            },function(data){
                if( data.response == 'success' ){
                    var html = '<ul>';
                    data.data.map(function(object){
                        html += '<li>'
                        if( object.end  ){
                            html += '<a data-id="' + object.id + '">-</a>';
                        }
                        else{
                            html += '<a data-id="' + object.id + '">+</a>';
                        }

                        html += '<input type="radio" name="DepotRadio"/><span>' + object.enterpriseName + '</span>'
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

    $(document).on("click",'.DepotDetail a',function(){
        if( $(this).html( ) == "+" ){
            $(this).siblings("ul").slideDown();
            $(this).html("-");
        }else if( $(this).html( ) == "-" ){
            $(this).siblings("ul").slideUp();
            $(this).html("+");
        }
    });
    $("#chooseConfirm").click(function(){
        discoverHtml();
        var id = $("input[name='DepotRadio']:checked").siblings("a").data("id");
        $("#Dopet").val($("input[name='DepotRadio']:checked").siblings("span").html());
        $("#Dopet").next().val(id);
        $(".chooeseDepot").hide();

    });

}


// 对Date的扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
// 例子：
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18
Date.prototype.Format = function(fmt)
{ //author: meizz
    var o = {
        "M+" : this.getMonth()+1,                 //月份
        "d+" : this.getDate(),                    //日
        "h+" : this.getHours(),                   //小时
        "m+" : this.getMinutes(),                 //分
        "s+" : this.getSeconds(),                 //秒
        "q+" : Math.floor((this.getMonth()+3)/3), //季度
        "S"  : this.getMilliseconds()             //毫秒
    };
    if(/(y+)/.test(fmt))
        fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    for(var k in o)
        if(new RegExp("("+ k +")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));
    return fmt;
};

function handleNaNZero(a){
    if(isNaN(a)){
        return 0;
    }
    else{
        return a;
    }
}

function getWindowHeight(){
    var height=document.documentElement.clientHeight;
    if (height > 700){
        return height;
    }else{
        return 1100;
    }
}
$(document).ready(function(){
    var height = getWindowHeight()
    $(".allOutShow").css("height",height-50);
    $(window).resize(function(){
        height = getWindowHeight()
        $(".allOutShow").css("height",height-50);
    })
});

function handleAZ(str){
    var arr = [];
    var sum = 0;
    for(var i = 0;i < str.length;i++){
        arr.push( AtoZ(str.charAt(i)) );
    }
    var length = arr.length;
    for(var j = 0;j < length;j++){
        sum += arr.pop() * Math.pow(27,j);
    }
    return sum;
}

function AtoZ(a){
    return (a.charCodeAt(0) - 64);
}
