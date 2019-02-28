<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta name="viewport"
          content=" height = device-height , width = device-width ,initial-scale = 0.5 ,minimum-scale = 1 ,maximum-scale = 1 ,user-scalable = no"
    />
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>选择门票</title>
    <style>
        .div-top {
            width: 100%;
            margin: 0 auto;
            border-radius: 5px;
            background-color: white;
            float: left;
            margin-bottom: 3.5%;
        }

        .div-middle {
            width: 100%;
            float: left;
        }

        .div-middle div:nth-of-type(1) {
            width: 30%;
            float: left;
            padding-top: 6%;
            padding-left: 6%;
        }

        .div-middle div:nth-of-type(2) {
            width: 50%;
            float: right;
            margin-top: 11%;
            margin-right: 5%;
        }

        .left-span-one {
            width: 83%;
            display: block;
            margin-left: 10%;
            margin-bottom: 6%;
        }

        .left-span-two {
            display: block;
            width: 93%;
            font-size: 1.6rem;
            color: #f9bd06;
        }

        .right-span-one {
            width: 15%;
            display: inline-block;
            float: right;
        }

        .right-span-two {
            width: 20%;
            display: inline-block;
            float: right;
            text-align: center;
            font-size: 1.23rem;
        }

        .hr_style {
            width: 94%;
            border: none;
            text-align: center;
            background-color: #F5F5F5;
            height: 1px;
            float: left;
            margin: 4% 0 0 3%;
        }

        .div-middle-p {
            float: left;
            font-size: 0.8rem;
            width: 93%;
            margin-bottom: 4%;
            color: #AAAAAA;
            padding-left: 7%;
        }

        .span-one {
            display: block;
            float: left;
            color: #f9bd06;
            width: 9%;
        }

        .span-two {
            display: block;
            float: right;
            width: 91%;
        }

        .img-header {
            width: auto;
            height: auto;
            max-height: 100%;
            max-width: 100%;
        }

        .div-buttom {
            width: 100%;
            position: absolute;
            bottom: 0;
            left: 0;
            height: 50px;
        }

        .div-buttom p:nth-of-type(1) {
            width: 40%;
            height: 100%;
            line-height: 50px;
            float: left;
            padding-left: 3.5%;
            margin: 0;
        }

        .div-buttom p:nth-of-type(2) {
            width: 40%;
            margin: 0;
            float: right;
            height: 100%;
            text-align: center;
            line-height: 50px;
            background-color: #f9bd06;
            color: white;
            border-radius: 6% 0 0;
        }
        .select_icon{
            width: 8%;
            position: absolute;
        }
    </style>
    <script src="/js/jquery-3.2.1.min.js"></script>
</head>
<body style="background-color: whitesmoke;margin: 3.5% 3.5% 0">
<c:forEach items="${products}" var="p" varStatus="s">
    <div class="div-top" onclick="selectDo(${carts.get(s.index).id})">
        <c:if test="${carts.get(s.index).checked == 1}">
            <span class="select_icon"><img class="img-header" src="/images/selected.png"></span>
        </c:if>
        <div class="div-middle">
            <div>
                <span class="left-span-one">${p.name}</span>
                <span class="left-span-two">￥<fmt:formatNumber value="${p.orig}" type="number" maxFractionDigits="0" /></span>
            </div>
            <div>
                <span class="right-span-one" onclick="miues(${p.id},1,${s.index})"><img class="img-header" src="/images/addico.png"></span>
                <span class="right-span-two">${carts.get(s.index).count}</span>
                <span class="right-span-one" onclick="miues(${p.id},0,${s.index})"><img class="img-header" src="/images/subico.png"></span>
            </div>
        </div>
        <hr class="hr_style" <c:if test="${carts.get(s.index).checked == 1}">style="background-color: #f9bd06" </c:if>>
        <div class="div-middle">
            <p class="div-middle-p"><span class="span-one"><c:if test="${p.type == 0}">注：</c:if><c:if test="${p.type == 1}">团：</c:if></span><span class="span-two">${p.ps}</span></p>
        </div>
    </div>
</c:forEach>

<%--<editor-fold desc="注释">
&lt;%&ndash;<div class="div-top">
    <div class="div-middle">
        <div>
            <span class="left-span-one">普通票</span>
            <span class="left-span-two">￥580</span>
        </div>
        <div>
            <span class="right-span-one"><img class="img-header" src="images/addico.png"></span>
            <span class="right-span-two">2</span>
            <span class="right-span-one"><img class="img-header" src="images/subico.png"></span>
        </div>
    </div>
    <hr class="hr_style">
    <div class="div-middle">
        <p class="div-middle-p"><span class="span-one">注：</span><span class="span-two">580/人（含税开票）</span></p>
    </div>
</div>
<div class="div-top">
    <div class="div-middle">
        <div>
            <span class="left-span-one">团购票</span>
            <span class="left-span-two">￥480</span>
        </div>
        <div>
            <span class="right-span-one"><img class="img-header" src="images/addico.png"></span>
            <span class="right-span-two">23</span>
            <span class="right-span-one"><img class="img-header" src="images/subico.png"></span>
        </div>
    </div>
    <hr class="hr_style">
    <div class="div-middle">
        <p class="div-middle-p"><span class="span-one">团：</span><span class="span-two">此门票为团购票，3人（含）以上480/人（含税开票）</span>
        </p>
    </div>
</div>&ndash;%&gt;
</editor-fold>--%>

<div class="div-buttom">
    <p>合计：<span>￥${money}</span></p>
    <p onclick="submitDo()">确定</p>
</div>
</body>
<script type="text/javascript">

    function submitDo() {
        params = {
            "userId":"1"
        }
        $.ajax({
            url: '/api/user/order/add',
            type: 'post',
            data: JSON.stringify(params),
            dataType: 'json',
            headers: {
                "Content-Type": "application/json"
            },
            success: function (data) {
                payMoney(data.data);
            }
        });
    }

    function onBridgeReady(id) {
        $.ajax({
            url: '/api/user/wx_h5/pay',
            type: 'post',
            data: {
                "orderId": id
            },
            dataType: 'json',
            success: function (data) {
                WeixinJSBridge.invoke(
                    'getBrandWCPayRequest', {
                        "appId": "" + data.appId,                 //公众号名称，由商户传入
                        "timeStamp": "" + data.timeStamp,         //时间戳，自1970年以来的秒数
                        "nonceStr": "" + data.nonceStr,           //随机串
                        "package": "" + data.package,
                        "signType": "" + data.signType,           //微信签名方式：
                        "paySign": "" + data.paySign              //微信签名
                    },
                    function (res) {
                        if (res.err_msg == "get_brand_wcpay_request:ok") {
                            alert("支付成功");
                        } else if (res.err_msg == "get_brand_wcpay_request:cancel") {
                            alert("取消支付");
                        } else if (res.err_msg == "get_brand_wcpay_request:fail") {
                            alert("支付失败");
                        }
                    });
            }
        })
    }

    function payMoney(id) {
        if (typeof WeixinJSBridge == "undefined") {
            if (document.addEventListener) {
                document.addEventListener('WeixinJSBridgeReady', onBridgeReady(id), false);
            } else if (document.attachEvent) {
                document.attachEvent('WeixinJSBridgeReady', onBridgeReady(id));
                document.attachEvent('onWeixinJSBridgeReady', onBridgeReady(id));
            }
        } else {
            onBridgeReady(id);
        }
    }

    function miues(id,type,index) {
        var ele = document.getElementsByClassName("right-span-two");
        var msg = ele[index].innerHTML
        if (msg == 0 && type == 0){
            return;
        }
        $.ajax({
            url : '/api/miues',
            type: 'post',
            data : {
                productId : id,
                type : type,
                active_id : ${active_id}
            },
            dataType: 'json',
            success : function (data) {
                window.location.reload();
            }
        });
        event.stopPropagation();
    }
    function selectDo(cartId) {
        $.ajax({
            url : '/api/cart/select',
            type: 'post',
            data : {
                cartId : cartId,
                active_id : ${active_id}
            },
            dataType: 'json',
            success : function (data) {
                window.location.reload();
            }
        })
    }


</script>
</html>
