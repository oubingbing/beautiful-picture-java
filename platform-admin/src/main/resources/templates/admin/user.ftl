<!DOCTYPE html><html><head>    <meta charset="UTF-8">    <title>欢迎页面-X-admin2.0</title>    <meta name="renderer" content="webkit">    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />    <link rel="stylesheet" href="/static/css/font.css">    <link rel="stylesheet" href="/static/css/xadmin.css"></head><body><div class="x-body layui-anim layui-anim-up">    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">    <div class="x-nav">      <span class="layui-breadcrumb">        <a href="">首页</a>        <a href="">演示</a>        <a>          <cite>导航元素</cite></a>      </span>        <a class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="javascript:location.replace(location.href);" title="刷新">            <i class="layui-icon" style="line-height:30px">ဂ</i></a>    </div>    <div class="x-body" id="app">        <blockquote class="layui-elem-quote">共有数据：{{total}} 条</blockquote>        <table class="layui-table">            <thead>            <tr>                <th>头像</th>                <th>微信昵称</th>                <th>性别</th>                <th>国家</th>                <th>省</th>                <th>市</th>                <th>创建时间</th>            </thead>            <tbody>            <tr v-for="user in users">                <td><img v-bind:src=user.avatar style="width: 40px;width: 40px"/></td>                <td>{{ user.name }}</td>                <td>{{ user.genderString }}</td>                <td>{{ user.country }}</td>                <td>{{ user.province }}</td>                <td>{{ user.city }}</td>                <td>{{ user.date }}</td>            </tr>            </tbody>        </table>        <div class="page">            <el-pagination                    background                    @current-change="handleCurrentChange"                    layout="prev, pager, next"                    :page-size="page_size"                    :current-page="current_page"                    :total="total">            </el-pagination>        </div>    </div></div></body><script src="https://cdn.bootcss.com/vue/2.5.16/vue.min.js"></script><script src="https://unpkg.com/element-ui/lib/index.js"></script><script src="https://cdn.bootcss.com/axios/0.17.1/axios.min.js"></script><script>    'use strict';    var baseUrl = "http://picture.qiuhuiyi.cn/";    new Vue({        el: '#app',        data: {            users:[],            url:baseUrl,            total:0,            page_size:20,            current_page:1        },        created:function () {            this.getUsers();            console.log('用户首页');        },        methods:{            handleCurrentChange:function (e) {                console.log(e);                this.current_page = e;                this.getUsers();            },            getUsers:function () {                var url = "/admin/user/list";                let _this = this;                axios.get(url+"?pageSize="+this.page_size+'&pageNumber='+this.current_page+'&order_by=created_at&sort_by=desc')                        .then( response=> {                            var res = response.data;                            if(res.code == 0){                                _this.users = res.data.content;                                _this.total = res.data.totalNumber;                            }else{                                console.log('error:'+res);                            }                        }).catch(function (error) {                    console.log(error);                });            }        }    })</script></html>