$(document).ready(function(){
   $("#payCheck").click(function(){
       $.post("../web/api/valet/payOrder",{
           orderCode:payOrder
       },function(data){
           if( data.response == 'success' ){
              coverHtml();
               $(".success").fadeIn()
           }
           else{
               response_ensure_alert("error",data.data.text,function(){})
           }
       });
   });
    $("#paydetail").click(function(){
        discoverHtml()
        window.location.href="UserGet"
    })
    $("#paycomplete").click(function(){
        discoverHtml()
        var deliveryCode=$(".orderLeft span").html()
        window.location.href = 'UserGetListDetail?code=' + deliveryCode;
    })
});