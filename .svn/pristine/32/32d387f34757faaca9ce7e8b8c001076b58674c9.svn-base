<#assign bath = request.contextPath>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<link rel="stylesheet" type="text/css" href="${bath}/static/css/g.css?version=${VERSION}"/>
<link rel="stylesheet" type="text/css" href="${bath}/static/css/mainright.css"/>
    <!--[if !IE]><!--> <script type="text/javascript" src="${bath}/static/js/jquery-2.2.0.min.js"></script> <!--<![endif]-->
    <!--[if lt IE 9]> <script src="${bath}/static/js/jquery-1.9.1.js"></script> <![endif]-->
    <script type="text/javascript" src="${bath}/static/js/common.js"></script>
    <script src="${bath}/static/js/pouchdb.min.js"></script>
<style type="text/css">
    body{
        width: 100%;
        height: 100%;
        background: #f7f7f7;
    }
    .background{
        width: 438px;
        height: 314px;
        background:url(../static/img/welcome.png) no-repeat center;
        position: fixed;
        top:30%;
        left:35%;
    }
</style>
</head>

<body style="background: #edf3f8">
<div style="margin: 20px" class="background"></div>
<div id="cover1"  style="width: 100%; height: 100%; z-index: 1; background: #000; display: none; opacity:0.5;position:fixed; left: 0px; top: 0px;"></div>
<script type="text/javascript">
    var enterpriseId = '${enterpriseId}';
    var enterpriseName = '${enterpriseName}';
    var accountName = '${accountName}';
    var isTop = '${isTop}';
    var isEnd = '${isEnd}';

    if( window.sessionStorage ){
        put('enterpriseId',enterpriseId);
        put('enterpriseName',enterpriseName);
        put('accountName',accountName);
        put('isTop',isTop);
        put('isEnd',isEnd);
    }


    function put(key,value){
        sessionStorage.setItem(key,value);
    }

</script>
<script src="${bath}/static/js/enterpriseInfo.js"></script>
<script type="text/javascript">
    var enterprise = getCurrentEnterpriseInfo();
</script>
</body>
</html>
