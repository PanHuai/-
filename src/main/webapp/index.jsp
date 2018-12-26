<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<html>
<head>
    <meta name="viewport"
          content=" height = device-height , width = device-width ,initial-scale = 0.5 ,minimum-scale = 1 ,maximum-scale = 1 ,user-scalable = no"
    />
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>活动详情</title>
    <style>
        .img-header {
            width: auto;
            height: auto;
            max-height: 100%;
            max-width: 100%;
        }

        .div-all {
            border-bottom: 1px solid lightgray;
            padding-left: 1%;
            float: left;
            display: block;
            width: 100%;
        }

        .div-all-p {
            float: left;
            width: 100%;
        }

        .div-all-p span:nth-child(1) {
            display: inline-block;
            float: left;
            width: 15%;
        }

        .div-all-p span:nth-child(2) {
            display: inline-block;
            float: right;
            width: 85%;
        }

        .div-p-buttom {
            width: 40%;
            text-align: center;
            color: white;
            background-color: #f9bd06;
            border-radius: 5px;
            height: 3rem;
            line-height: 2.875rem;
            margin: 3.8% auto;
        }
        a:-webkit-any-link {
            color: white;
            cursor: auto;
            text-decoration: none;
        }
    </style>
    <script src="js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div>
    <img class="img-header" src="images/timg.jpg">
</div>
<div class="div-all">
    <p style="font-weight: bold;text-align: center">《社保由税务征收对企业影响与应对》</p>
</div>
<div class="div-all">
    <p class="div-all-p"><span>时间：</span><span>2018-12-23 08:30 ~ 2018-12-23 12:30</span></p>
</div>
<div class="div-all">
    <p class="div-all-p"><span>地点：</span><span>江苏淮安市区软件园城的南路22好五一山东省</span></p>
</div>
<div class="div-all">
    <p class="div-all-p"><span>费用：</span><span>￥480-580</span></p>
</div>
<div class="div-all" style="border-bottom: none">
    <p class="div-p-buttom" id="enroll"><a href="">立即报名</a></p>
</div>
<script src="http://res.wx.qq.com/open/js/jweixin-1.4.0.js"></script>
<script type="text/javascript">
    wx.config({
        debug: true, // 开启调试模式,调用的所有api的返回值会在客户端alert出来，若要查看传入的参数，可以在pc端打开，参数信息会通过log打出，仅在pc端时才会打印。
        appId: '', // 必填，公众号的唯一标识
        timestamp:, // 必填，生成签名的时间戳
        nonceStr: '', // 必填，生成签名的随机串
        signature: '',// 必填，签名
        jsApiList: [] // 必填，需要使用的JS接口列表
    });
    wx.errer(function (data) {
        alert("出错了：" + data.errMsg);
    });
    wx.ready(function () {
        $("#enroll").click(function () {

        })
    });
    function onBridgeReady(){
        WeixinJSBridge.invoke(
            'getBrandWCPayRequest', {
                "appId":"wx2421b1c4370ec43b",     //公众号名称，由商户传入
                "timeStamp":"1395712654",         //时间戳，自1970年以来的秒数
                "nonceStr":"e61463f8efa94090b1f366cccfbbb444", //随机串
                "package":"prepay_id=u802345jgfjsdfgsdg888",
                "signType":"MD5",         //微信签名方式：
                "paySign":"70EA570631E4BB79628FBCA90534C63FF7FADD89" //微信签名
            },
            function(res){
                if(res.err_msg == "get_brand_wcpay_request:ok" ){
                    // 使用以上方式判断前端返回,微信团队郑重提示：
                    //res.err_msg将在用户支付成功后返回ok，但并不保证它绝对可靠。
                }else if (res.err_msg == "get_brand_wcpay_request:cancel" ){

                }else if (res.err_msg == "get_brand_wcpay_request:fail" ){

                }
            });
    }

    function payMoney() {
        if (typeof WeixinJSBridge == "undefined"){
            if( document.addEventListener ){
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
            }else if (document.attachEvent){
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
            }
        }else{
            onBridgeReady();
        }
    }


</script>
</body>
</html>
