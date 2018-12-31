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
            width: 6%;
            margin-left: 3%;
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
    <p style="font-weight: bold;text-align: center">《${active.title}》</p>
</div>
<div class="div-all">
    <p class="div-all-p"><span><img class="img-header" src="images/times.png"></span><span>${active.beginTime} ~ ${active.endTime}</span></p>
</div>
<div class="div-all">
    <p class="div-all-p"><span><img class="img-header" src="images/address.png"></span><span class="ssss">${active.address}</span></p>
</div>
<div class="div-all">
    <p class="div-all-p"><span><img class="img-header" src="images/money.png"></span><span>￥${start}-${end}</span></p>
</div>
<div class="div-all" style="border-bottom: none">
    <p class="div-p-buttom" id="enroll"><a href="wxapp/oauthDo?id=${active.id}">立即报名</a></p>
</div>
</body>
</html>
