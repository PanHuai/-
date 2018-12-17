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
    <p class="div-p-buttom" id="enroll"><a href="wxapp/oauthDo">立即报名</a></p>
</div>
<script src="js/jquery.js"/>
<script type="text/javascript">
    $("#enroll").on("touchstart",function () {
        alert("呵呵");
    })
    alert("xxx");
</script>
</body>
</html>
