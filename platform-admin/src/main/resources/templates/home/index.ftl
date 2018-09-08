<!DOCTYPE html>
<html lang="{{ config('app.locale') }}">
<head>
    <meta charset="UTF-8">
    <title>唯美图吧</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="/static/css/font.css">
    <link rel="stylesheet" href="/static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/js/xadmin.js"></script>

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
                    <li><a href="/home">首页</a></li>
                    <li><a href="/admin">控制台</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <footer class="footer navbar-fixed-bottom">
        <div class="container footer">
            <a href="http://www.miitbeian.gov.cn/">@2016-2018 湛江市赤坎区古卡饮品店 | 粤ICP备16004706号-1</a>
        </div>
    </footer>
</div>

</body>
</html>