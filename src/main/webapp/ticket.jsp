<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
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
        }

        .div-middle {
            width: 100%;
            border-bottom: 1px solid lightgray;
            float: left;
        }

        .div-middle div:nth-child(1) {
            width: 30%;
            float: left;
            padding-top: 6%;
            padding-left: 6%;
        }

        .div-middle div:nth-child(2) {
            width: 50%;
            float: right;
            margin-top: 8%;
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
        .right-span-one{
            width: 13%;
            display: inline-block;
            height: 0;
            padding-bottom: 13%;
            float: right;
            border: 2px solid #f9bd06;
            color: #f9bd06;
            border-radius: 50%;
            text-align: center;
            font-weight: bold;
            overflow: hidden;
        }
        .right-span-two{
            width: 20%;
            display: inline-block;
            float: right;
            text-align: center;
            margin-top: 1%;
            height: 0;
            padding-bottom: 20%;
            overflow: hidden;
            font-size: 1.5rem;
        }
        .right-span-three{
            width: 13%;
            display: inline-block;
            float: right;
            border: 2px solid gray;
            color: gray;
            font-weight: bold;
            border-radius: 50%;
            text-align: center;
            height: 0;
            padding-bottom: 13%;
            overflow: hidden;
        }


    </style>
</head>
<body style="background-color: lightgray;margin: 1%">
<div class="div-top">
    <div class="div-middle">
        <div>
            <span class="left-span-one">普通票</span>
            <span class="left-span-two">￥580</span>
        </div>
        <div>
            <span class="right-span-one">+</span>
            <span class="right-span-two">0</span>
            <span class="right-span-three">-</span>
        </div>
    </div>
</div>

</body>
</html>
