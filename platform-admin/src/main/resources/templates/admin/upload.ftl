<!doctype html><html lang="en"><head>    <meta charset="UTF-8">    <title>后台登录-X-admin2.0</title>    <meta name="renderer" content="webkit|ie-comp|ie-stand">    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />    <meta http-equiv="Cache-Control" content="no-siteapp" />    <link rel="stylesheet" href="../static/css/font.css">    <link rel="stylesheet" href="../static/css/font.css">    <link rel="stylesheet" href="../static/css/xadmin.css">    <style>        .input-item{            width: 100%;            display: flex;            flex-direction: row;            justify-content: flex-start;            align-items: center;            padding-top: 10px;            padding-bottom: 10px;        }        .div-input input{            padding: 10px;        }        .input-item .input-sub{            margin-right: 10px;        }        .input-item .div-tips{            color: darkgray;        }        .picture-container{            width: 97%;            position: relative;            border-style:solid;            border-width:1px;            border-color: darkgray;            padding-left: 20px;            padding-right: 20px;            padding-bottom: 20px;            display: flex;            flex-direction: row;            padding-top: 40px;        }        .picture-container .picture-item{            width: 150px;            height: 150px;            z-index: 1;            box-shadow: 0px 0px 7px 2px gainsboro;            border-radius: 5px;            margin-right: 20px;        }        .picture-item img{            width: 150px;            height: 120px;            border-top-left-radius: 5px;            border-top-right-radius: 5px;        }        .picture-container .select-img{            position: absolute;            z-index: 10;            top:-20px;        }        .picture-item .upload-button{            padding: 5px;            border-bottom-left-radius: 5px;            border-bottom-right-radius: 5px;            text-align: center;        }        .picture-item .upload-none{            background: darkgray;            color: #009688;        }        .picture-item .upload-success{            background: #009688;            color: white;        }    </style></head><body><div class="x-body layui-anim layui-anim-up">    <blockquote class="layui-elem-quote" style="display: flex;flex-direction: row;justify-content: space-between;align-items: center">        上传图集        <button  class="layui-btn layui-btn-warm x-right">提交</button>    </blockquote>    <div class="layui-field-box">        <div class="input-item">            <label for="" class="input-sub">标题</label>            <div class="div-input input-sub">                <input type="text" style="width: 300px" />            </div>            <div class="div-tips input-sub">                每期的图集标题，不填将不会显示出来            </div>        </div>        <div class="input-item">            <label for="" class="input-sub">内容</label>            <div class="div-input input-sub">                <textarea rows="6" type="text" style="width: 300px;padding: 10px;"></textarea>            </div>            <div class="div-tips input-sub">                图集的内容，选填            </div>        </div>        <div class="input-item">            <input type="file" id="select" style="display: none" class="layui-input"/>        </div>    </div>    <!-- http://picture.qiuhuiyi.cn/hui_yi_15347500870002009 -->    <div class="picture-container">        <button  class="layui-btn select-img" onclick="javascript:$('#select').click()">            选择图片        </button>        <div class="picture-item">            <img src="http://picture.qiuhuiyi.cn/hui_yi_15347500870002009" alt="">            <div class="upload-button upload-none">                开始上传            </div>        </div>        <div class="picture-item">            <img src="http://picture.qiuhuiyi.cn/i5.jpg" alt="">            <div class="upload-button upload-success">                上传成功            </div>        </div>    </div></div></body><style></style><script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script><script type="text/javascript" src="https://unpkg.com/qiniu-js@2.0/dist/qiniu.min.js"></script><script src="../static/lib/layui/layui.js" charset="utf-8"></script><script type="text/javascript" src="../static/js/xadmin.js"></script><script>    var file = '';    var coverPicture = '';    var token = "";    var imageArray = [        {content:'',url:"i5.jpg"},        {content:'',picture:"u=1025809368,3179591467&fm=27&gp=0.jpg"},        {content:'',picture:"u=1574739706,1439120121&fm=27&gp=0.jpg"}    ];    var title = '';    var config = {        useCdnDomain: true,        disableStatisticsReport: false,        retryCount: 6,        region: qiniu.region.z2    };    $("#select").unbind("change").bind("change",function(){        file = this.files[0];        console.log("文件："+file);    })    function submitPicture() {        title = document.getElementById("title").value;        var fileName = file.name;        var timestamp = Date.parse(new Date());        var random = Math.round(Math.random()*10000);        var key = "hui_yi_"+timestamp+random;        var putExtra = {            fname: fileName,            params: {},            mimeType: null        };        var error = function(err) {            alert("上传出错")        };        var complete = function(res) {            console.log("返回结果："+JSON.stringify(res));        };        var next = function(response) {            var total = response.total;            console.log("进度："+total.percent);        };        var observable = qiniu.upload(file, key, token, putExtra, config);        var subObject = {            next: next,            error: error,            complete: complete        };        observable.subscribe(subObject);        /*$.post("/admin/upload",{title:title,content:'',coverPicture:'',pictures:JSON.stringify(imageArray)},function (res) {            console.log(res)        })*/    }    $.ajax({url: "/admin/token", success: function(res){            token = res.data.token;            console.log("token: "+token)        }    });</script></html>