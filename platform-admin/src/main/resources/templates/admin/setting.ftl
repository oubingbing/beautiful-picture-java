<!DOCTYPE html><html><head>    <meta charset="UTF-8">    <title>欢迎页面-X-admin2.0</title>    <meta name="renderer" content="webkit">    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />    <link rel="stylesheet" href="/static/css/font.css">    <link rel="stylesheet" href="/static/css/xadmin.css">    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css"></head><style>    .app-info{        background: #EFEEF0;        padding: 5px;        border-radius: 1px;    }</style><body><div class="x-body layui-anim layui-anim-up" id="app">    <blockquote class="layui-elem-quote">你好，在这里你可以设置自己的七牛云存储，图集logo和关于我们</blockquote>    <fieldset class="layui-elem-field">        <legend style="color: #009688">图集logo</legend>        <div class="layui-field-box">            <div><img style="width: 90px;height: 90px;margin-bottom: 10px" v-bind:src="imageUrl+logoUrl"></div>            <el-upload                    v-if="logoUrl"                    :action="upLoadDomain"                    class="upload-demo"                    :on-success="uploadSuccess"                    list-type="picture">                <el-button size="small" type="primary">点击上传</el-button>            </el-upload>            <el-upload                    v-else                    :action="upLoadDomain"                    class="upload-demo"                    :on-success="uploadSuccess"                    list-type="picture">                <el-button size="small" type="primary">修改二维吗</el-button>            </el-upload>        </div>    </fieldset>    <fieldset class="layui-elem-field" style="margin-top: 50px">        <legend style="color: #009688">七牛账号设置</legend>        <div class="layui-field-box">            <table class="layui-table">                    <tbody>                    <tr>                        <th>access_key</th>                        <td class="name">                            {{appInfo.name}}                            <div class="layui-input-inline">                                <input type="text"                                       lay-verify="required"                                       class="layui-input"                                       placeholder="app_key"                                       name="accessKey"                                       v-model="qiNiuForm.accessKey"                                       style="width: 250px;float: left">                            </div>                        </td></tr>                    <tr>                        <th>secret_key</th>                        <td class="name">                            {{appInfo.name}}                            <div class="layui-input-inline">                                <input type="text"                                       lay-verify="required"                                       class="layui-input"                                       placeholder="secret_key"                                       name="secretKey"                                       v-model="qiNiuForm.secretKey"                                       style="width: 250px;float: left">                            </div>                        </td></tr>                    <tr>                        <th>bucket_name</th>                        <td class="name">                            {{appInfo.name}}                            <div class="layui-input-inline">                                <input type="text"                                       lay-verify="required"                                       class="layui-input"                                       placeholder="bucket_name"                                       name="bucketName"                                       v-model="qiNiuForm.bucketName"                                       style="width: 250px;float: left">                            </div>                        </td></tr>                    <tr>                        <th>domain</th>                        <td class="name">                            {{appInfo.name}}                            <div class="layui-input-inline">                                <input type="text"                                       lay-verify="required"                                       class="layui-input"                                       placeholder="domain"                                       name="domain"                                       v-model="qiNiuForm.domain"                                       style="width: 250px;float: left">                            </div>                        </td></tr>                    <tr>                        <th>region</th>                        <td class="name">                            {{appInfo.name}}                            <div class="layui-input-inline">                                <input type="text"                                       lay-verify="required"                                       class="layui-input"                                       placeholder="region"                                       name="region"                                       v-model="qiNiuForm.region"                                       style="width: 250px;float: left">                            </div>                        </td></tr>                    </tbody>            </table>            <button class="layui-btn layui-btn-warm upload-img" v-on:click="submitQiNiuForm()">                一键上传            </button>        </div>    </fieldset>    <fieldset class="layui-elem-field" style="margin-top: 50px">        <legend style="color: #009688">关于我们</legend>        <div class="layui-field-box">            <table class="layui-table">                <tbody>                <tr>                    <th>access_key</th>                    <td class="name">                        {{appInfo.name}}                    </td></tr>                <tr>                    <th>secret_key</th>                    <td>                    </td></tr>                <tr>                    <th>bucket_name</th>                    <td><span class="app-info">{{appInfo.allianceKey}}</span></td></tr>                <tr>                    <th>domain</th>                    <td class="name"><span class="app-info">{{appInfo.appKey}}</span></td>                </tr>                </tbody>            </table>        </div>    </fieldset>    <form @submit.prevent="submitQiNiuForm">        <input type="text" name="name" v-model="inputtext.name">        <input type="password" name="password" v-model="inputtext.password">        <input type="submit" value="提交">    </form></div></body><script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script><script type="text/javascript" src="https://unpkg.com/qiniu-js@2.0/dist/qiniu.min.js"></script><script src="https://cdn.bootcss.com/axios/0.17.1/axios.min.js"></script><script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script><script src="https://unpkg.com/element-ui/lib/index.js"></script><script src="/static/lib/layui/layui.js" charset="utf-8"></script><script type="text/javascript" src="/static/js/xadmin.js"></script><script>    'use strict';    var app = new Vue({        el: '#app',        data: {            appInfo:[],            new_user:'-',            visit_user:'-',            all_user:'-',            upLoadDomain:'https://up-z2.qbox.me',            imageUrl:'http://picture.qiuhuiyi.cn/',            logoUrl:'',            showQiNiuEdit:false,            qiNiuForm:{},            inputtext:{}        },        created:function () {            this.getUploadToken();            //this.getUserInfo();            this.getAppInfo();        },        methods:{            submitQiNiuForm:function(){                axios.post('/admin/qiNiu/edit',this.qiNiuForm)                        .then( response=> {                            var res = response.data;                            console.log(res);                            if(res.code == 0){                                layer.msg('修改成功！');                            }else{                                layer.msg('修改失败！');                            }                        }).catch(function (error) {                    console.log(error);                });            },            /**             * 获取APP信息             *             * @author 叶子             */            getAppInfo:function () {                let _this = this;                axios.get("/admin/app/info").then( response=> {                    var res = response.data;                    if(res.code == 0){                        _this.appInfo = res.data;                    }else{                        console.log('error:'+res);                    }                }).catch(function (error) {                    console.log(error);                });            },            /**             * 监听上传成功回调             * @param res             */            uploadSuccess:function (res) {                this.logoUrl = res.key;                axios.post('/admin/app/update_logo',{logo:this.logoUrl})                        .then( response=> {                            var res = response.data;                            console.log(res);                            if(res.code == 0){                                layer.msg('修改成功！');                            }else{                                layer.msg('修改失败！');                            }                        }).catch(function (error) {                    console.log(error);                });            },            /**             * 获取七牛token             */            getUploadToken:function () {                axios.get("/admin/token")                        .then( response=> {                            let resData = response.data;                            if(resData.code == 0){                                this.upLoadDomain = this.upLoadDomain+'?token='+response.data.data.token;                            }else{                                layer.msg('获取token失败');                            }                            console.log(this.upLoadDomain);                        }).catch(function (error) {                    console.log(error);                });            },        },    });</script></html>