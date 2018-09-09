<!DOCTYPE html>
<html lang="">
<head>
    <meta name="keywords" content="唯美图吧，图片小程序，一键部署小程序，小程序，图片，小程序">
    <meta name="description" content="简单配置就能部署上线的图片小程序">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>唯美图吧</title>
    <link rel="stylesheet" href="/static/lib/layui/font/font.css">
    <link href="/static/css/app.css" rel="stylesheet">

    <style>
        .footer{
            background: #EEEEEE;
            text-align: center;
            padding: 5px;
        }
    </style>
</head>
<body>
<div id="app">
    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">唯美图吧</a>
            </div>

            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/">首页</a></li>
                    <#if (username != "")>
                        <li><a href="/admin/index">控制台</a></li>
                        <li><a href="javascript:;">${username}</a></li>
                        <li><a href="/logout">退出</a></li>
                    <#else>
                        <li><a href="/login">登录</a></li>
                        <li><a href="/register">注册</a></li>
                    </#if>
                </ul>
            </div>
        </div>
    </nav>

    <footer class="footer navbar-fixed-bottom">
        <div class="container footer">
            <a href="http://www.miitbeian.gov.cn/">@2018 唯美图吧 | 粤ICP备16004706号-1</a>
        </div>
    </footer>
</div>

<script src="/static/js/app.js"></script>
</body>
</html>