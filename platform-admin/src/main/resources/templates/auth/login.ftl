<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>登录</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/static/images/main-logo.jpg" type="image/x-icon" />
    <link rel="stylesheet" href="/static/css/font.css">
	<link rel="stylesheet" href="/static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="/static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="/static/js/xadmin.js"></script>

</head>
<body class="login-bg">
    
    <div class="login layui-anim layui-anim-up">
        <div class="message"><a href="/" style="color: white">首页 | </a>后台登录 - 唯美图吧</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form" >
            <input name="email" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit">
            <hr class="hr20" >
        </form>
        <div><a href="/register" style="color: #2a88bd">没有账号？快去注册吧</a></div>
    </div>

    <script>
        $(function  () {
            layui.use('form', function(){
                var form = layui.form;

                form.on('submit(login)', function(data){
                    var fields = data.field;
                    $.post("/login",fields,function(res){
                        console.log(res);
                        if(res.code == 0 && res.data == true){
                            layer.msg("登录成功，跳转中...");
                            setTimeout(function () {
                                window.location.href = "/admin/index";
                            },1000)
                        }else{
                            if(res.code == 0){
                                layer.msg("登录失败！");
                            }else{
                                layer.msg(res.message);
                            }
                        }
                    });
                    return false;
                });
            });
        })
    </script>
</body>
</html>