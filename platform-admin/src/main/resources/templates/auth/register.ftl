<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>注册</title>
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />

    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="../static/css/font.css">
    <link rel="stylesheet" href="../static/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="../static/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="../static/js/xadmin.js"></script>

</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
    <div class="message">注册-唯美图吧</div>
    <div id="darkbannerwrap"></div>

    <form id="register-form" method="post" class="layui-form">
        <input name="username" placeholder="用户名"  type="text" lay-verify="required" class="layui-input" >
        <hr class="hr15">
        <input name="email" lay-verify="required" placeholder="邮箱"  type="email" class="layui-input">
        <hr class="hr15">
        <input name="password" lay-verify="required" placeholder="密码"  type="password" class="layui-input">
        <hr class="hr15">
        <input name="passwordConfirmation" lay-verify="required" placeholder="确认密码"  type="password" class="layui-input">
        <hr class="hr15">
        <input value="注册" lay-submit lay-filter="login" style="width:100%;" type="submit">
        <hr class="hr20" >
    </form>
</div>

<script>
    $(function  () {
        layui.use('form', function(){
            var form = layui.form;
            form.on('submit(login)', function(data){
                var fields = data.field;

                if(fields.passwordConfirmation != fields.password){
                    layer.msg('两次输入密码不一致！');
                    return false;
                }

                $.post("/auth/register",fields,function(res){
                    if(res.code == 0){
                        layer.msg("注册成功！",function () {
                            window.location.href = "/auth/login";
                        })
                    }else{
                        layer.msg(res.message);
                    }
                });

                return false;
            });
        });
    })


</script>
</body>
</html>