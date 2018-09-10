<!doctype html><html lang="en"><head>    <meta charset="UTF-8">    <title>上传图集</title>    <meta name="renderer" content="webkit|ie-comp|ie-stand">    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />    <meta http-equiv="Cache-Control" content="no-siteapp" />    <link rel="stylesheet" href="/static/css/font.css">    <link rel="stylesheet" href="/static/css/font.css">    <link rel="stylesheet" href="/static/css/xadmin.css">    <style>        .input-item{            width: 100%;            display: flex;            flex-direction: row;            justify-content: flex-start;            align-items: center;            padding-top: 10px;            padding-bottom: 10px;        }        .div-input input{            padding: 10px;        }        .input-item .input-sub{            margin-right: 10px;        }        .input-item .div-tips{            color: darkgray;        }        .picture-container{            width: 97%;            position: relative;            border-style:solid;            border-width:1px;            border-color: darkgray;            padding-left: 20px;            padding-right: 20px;            padding-bottom: 20px;            display: flex;            flex-direction: row;            padding-top: 40px;            flex-wrap: wrap;        }        .picture-container .picture-item{            width: 150px;            height: 150px;            z-index: 1;            box-shadow: 0px 0px 7px 2px gainsboro;            border-radius: 5px;            margin-right: 20px;            margin-bottom: 20px;            word-break:break-all        }        .picture-item img{            width: 150px;            height: 120px;            border-top-left-radius: 5px;            border-top-right-radius: 5px;        }        .picture-container .select-img{            position: absolute;            z-index: 10;            top:-20px;        }        .picture-container .upload-img{            position: absolute;            z-index: 10;            bottom:-20px;            right: 20px;        }        .picture-item .upload-button{            padding: 5px;            border-bottom-left-radius: 5px;            border-bottom-right-radius: 5px;            text-align: center;        }        .picture-item .upload-none{            background: darkgray;            color: #009688;            cursor:pointer        }        .picture-item .upload-success{            background: #009688;            color: white;        }        .picture-item .delete-container{            width: 150px;            height: 120px;            z-index: 20;            position: absolute;            display: flex;            flex-direction: column;            justify-content: center;            align-items: center;            background:rgba(2,2,2,0.6);            border-top-left-radius: 5px;            border-top-right-radius: 5px;            color: #009688;        }        [v-cloak] {            display: none;        }    </style></head><body><div class="x-body layui-anim layui-anim-up" id="app">    <blockquote class="layui-elem-quote" style="display: flex;flex-direction: row;justify-content: space-between;align-items: center">        上传图集        <button  class="layui-btn layui-btn-danger x-right" v-on:click="submit()">提交</button>    </blockquote>    <div class="layui-field-box">        <div class="input-item">            <label for="" class="input-sub">封面</label>            <div class="input-sub" v-cloak>                <div class="picture-item" v-if="coverPicture != null" v-cloak >                    <div @mouseenter="enterCover()" @mouseleave="leaveCover()">                        <div v-if="coverPicture.show" class="delete-container">                            <img style="width: 25px;height: 25px;" src="/static/images/delete.png" alt="删除" v-on:click="deleteCoverImage()">                        </div>                        <img v-if="coverPicture.status == 1" v-bind:src="url+coverPicture.key" alt="" v-cloak>                        <img v-else src="" v-bind:alt=" coverPicture.name" style="background: gainsboro;border-top-left-radius: 5px;border-top-right-radius: 5px;word-break:break-all" v-cloak>                    </div>                    <div>                        <div v-if="coverPicture.status == 0 && coverPicture.progress == 0" class="upload-button upload-none" v-on:click="uploadCover(coverPicture.name)" v-cloak>开始上传</div>                        <div v-else-if="coverPicture.progress > 0 && coverPicture.status == 0" class="upload-button upload-success" v-cloak>已上传 {{coverPicture.progress}}%</div>                        <div v-else class="upload-button upload-success" v-cloak>上传成功</div>                    </div>                </div>            </div>            <div class="div-input input-sub" v-if="coverPicture == null" v-cloak>                <button  class="layui-btn" onclick="javascript:$('#cover-picture').click()">                    选择图片                </button>                <input type="file" id="cover-picture" style="display: none" @change="selectCoverPicture($event)" class="layui-input"/>            </div>            <div class="div-tips input-sub">                专辑封面图            </div>        </div>        <div class="input-item">            <label for="" class="input-sub">头像</label>            <div class="input-sub" v-cloak>                <div class="picture-item" v-if="avatarPicture != null" v-cloak >                    <div @mouseenter="enterAvatar()" @mouseleave="leaveAvatar()">                        <div v-if="avatarPicture.show" class="delete-container">                            <img style="width: 25px;height: 25px;" src="/static/images/delete.png" alt="删除" v-on:click="deleteAvatarImage()">                        </div>                        <img v-if="avatarPicture.status == 1" v-bind:src="url+avatarPicture.key" alt="" v-cloak>                        <img v-else src="" v-bind:alt=" avatarPicture.name" style="background: gainsboro;border-top-left-radius: 5px;border-top-right-radius: 5px;word-break:break-all" v-cloak>                    </div>                    <div>                        <div v-if="avatarPicture.status == 0 && avatarPicture.progress == 0" class="upload-button upload-none" v-on:click="uploadAvatar(avatarPicture.name)" v-cloak>开始上传</div>                        <div v-else-if="avatarPicture.progress > 0 && avatarPicture.status == 0" class="upload-button upload-success" v-cloak>已上传 {{avatarPicture.progress}}%</div>                        <div v-else class="upload-button upload-success" v-cloak>上传成功</div>                    </div>                </div>            </div>            <div class="div-input input-sub" v-if="avatarPicture == null" v-cloak>                <button  class="layui-btn" onclick="javascript:$('#avatar-picture').click()">                    选择图片                </button>                <input type="file" id="avatar-picture" style="display: none" @change="selectAvatarPicture($event)" class="layui-input"/>            </div>            <div class="div-tips input-sub">                专辑头像图            </div>        </div>        <div class="input-item">            <label for="" class="input-sub">标题</label>            <div class="div-input input-sub">                <input type="text" style="width: 300px" id="title" v-model="title"/>            </div>            <div class="div-tips input-sub">                每期的图集标题，不填将不会显示出来            </div>        </div>        <div class="input-item">            <label for="" class="input-sub">内容</label>            <div class="div-input input-sub">                <textarea rows="6" type="text" style="width: 300px;padding: 10px;" v-model="content"></textarea>            </div>            <div class="div-tips input-sub">                图集的内容，选填            </div>        </div>        <div class="input-item">            <input type="file" id="select" style="display: none" class="layui-input"/>        </div>    </div>    <div class="picture-container">        <button  class="layui-btn select-img" onclick="javascript:$('#select').click()">            选择图片        </button>        <button v-cloak v-if="images.length > 0" class="layui-btn layui-btn-warm upload-img" v-on:click="submitPicture()">            一键上传        </button>        <div class="picture-item" v-for="image in images" v-cloak>            <div @mouseenter="enter(image.name)" @mouseleave="leave(image.name)">                <div v-if="image.show" class="delete-container">                    <img style="width: 25px;height: 25px;" src="/static/images/delete.png" alt="删除" v-on:click="deleteImage(image.name)">                </div>                <img v-if="image.status == 1" v-bind:src="url+image.key" alt="" v-cloak>                <img v-else src="" v-bind:alt=" image.name" v-cloak>            </div>            <div>                <div v-if="image.status == 0 && image.progress == 0" class="upload-button upload-none" v-on:click="uploadSingle(image.name)" v-cloak>开始上传</div>                <div v-else-if="image.progress > 0 && image.status == 0" class="upload-button upload-success" v-cloak>已上传 {{image.progress}}%</div>                <div v-else class="upload-button upload-success" v-cloak>上传成功</div>            </div>        </div>    </div></div></body><style></style><script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script><script type="text/javascript" src="https://unpkg.com/qiniu-js@2.0/dist/qiniu.min.js"></script><script src="/static/lib/layui/layui.js" charset="utf-8"></script><script type="text/javascript" src="/static/js/xadmin.js"></script><script type="text/javascript" src="/static/js/upload.js"></script><script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script><script src="https://cdn.jsdelivr.net/npm/vue-resource@1.5.1"></script><script>   'use strict';    var file = '';    var coverPicture = null;    var token = "";    var imageArray = [];    var title = '';    var baseUrl = "${domain}/";    var zone = "${zone}";    Vue.config.productionTip = false;    new Vue({        el: '#app',        data: {            images:imageArray,            name:"bingbing",            url:baseUrl,            coverPicture:null,            avatarPicture:null,            coverPictureWidth:0,            coverPictureHeight:0,            title:'',            content:''        },        created:function () {            console.log('初始化');        },        methods:{            submit:function(){                var cover = this.coverPicture.key;                var coverWidth = this.coverPictureWidth;                var coverHeight = this.coverPictureHeight;                var pictureList = this.images;                var titleValue = this.title;                var contentValue = this.content;                var avatar = this.avatarPicture.key;                if(cover == null){                    alert("封面不能为空！");                    return false;                }                if(pictureList.length == 0){                    alert("图集不能为空！");                    return false;                }                var pictures = [];                pictureList.map(function (item) {                    pictures.push({picture:item.key,title:'',content:'',pictureWidth:item.width,pictureHeight:item.height})                });                this.$http.post("/admin/picture/upload",{                    title:titleValue,                    content:contentValue,                    coverPicture:cover,                    avatar:avatar,                    coverWidth:coverWidth,                    coverHeight:coverHeight,                    pictures:JSON.stringify(pictures)                },{emulateJSON: true}).then(function(res){                    let resData = res.data;                    if(resData.code == 0){                        layer.msg('提交成功！');                        setTimeout(function () {                            window.location.href = "/admin/picture";                        },1500)                    }else{                        layer.msg('提交失败！');                    }                },function(res){                    layer.msg('网络错误');                });            },            /**             * 选择封面图片             **/            selectCoverPicture:function(e){                var file = e.target.files[0];                this.coverPicture = {                    file:file,                    name:file.name,                    key:'',                    progress:0,                    status:0,                    show:false,                    width:0,height:0                };            },            /**             * 进入封面事件             **/            enterCover:function(name){                var cover = this.coverPicture;                cover.show = true;                this.coverPicture = cover;            },            /**             * 封面移出事件             **/            leaveCover:function(name){                var cover = this.coverPicture;                cover.show = false;                this.coverPicture = cover;            },            /**             * 监控图集进入事件             **/            enter:function(name){                imageArray.map(function (item) {                    if(name == item.name){                        item.show = true;                    }                    return item;                })                this.images = imageArray;            },            /**             * 监控图集移除事件             * */            leave:function(name){                imageArray.map(function (item) {                    if(name == item.name){                        item.show = false;                    }                    return item;                })                this.images = imageArray;            },            /**             * 删除封面             **/            deleteCoverImage:function(){                this.coverPicture = null;            },            /**             * 选择头像图片             **/            selectAvatarPicture:function(e){                var file = e.target.files[0];                this.avatarPicture = {                    file:file,                    name:file.name,                    key:'',                    progress:0,                    status:0,                    show:false,                    width:0,height:0                };            },            /**             * 进入头像事件             **/            enterAvatar:function(name){                var cover = this.avatarPicture;                cover.show = true;                this.avatarPicture = cover;            },            /**             * 头像移出事件             **/            leaveAvatar:function(name){                var cover = this.avatarPicture;                cover.show = false;                this.avatarPicture = cover;            },            /**             * 删除头像             **/            deleteAvatarImage:function(){                this.avatarPicture = null;            },            /**             * 删除图集             **/            deleteImage:function(name){                console.log(name);                var imagesMap = imageArray.filter(function (item) {                    if(item.name != name){                        return item;                    }                })                imageArray = imagesMap;                this.images = imagesMap;            },            refreshUploadToken:function (call) {                let _this = this;                axios.get("/admin/qiniu/refresh_token")                        .then( response=> {                    let resData = response.data;                if(resData.code == 0){                    this.upLoadDomain = this.upLoadDomain+'?token='+response.data.data.token;                    call();                }else{                    layer.msg('获取token失败');                }                console.log(this.upLoadDomain);            }).catch(function (error) {                    console.log(error);                });            },            /**             * 上传头像             **/            uploadAvatar:function(name){                var avatar = this.avatarPicture;                var _this = this;                uploadPicture(avatar.file,function (res) {                    avatar.key = res.key;                    avatar.status = 1;                },function (res) {                    var total = res.total;                    avatar.progress = Math.round(total.percent);                    _this.avatar = avatar;                },function (res) {                    console.log("出错了")                    _this.refreshUploadToken(_this.uploadAvatar(name));                },zone)                this.avatarPicture = avatar;            },            /**             * 上传封面             **/            uploadCover:function(name){                var cover = this.coverPicture;                var _this = this;                uploadPicture(cover.file,function (res) {                    cover.key = res.key;                    _this.coverPictureHeight = res.height;                    _this.coverPictureWidth = res.width;                    cover.status = 1;                },function (res) {                    var total = res.total;                    cover.progress = Math.round(total.percent);                    _this.cover = cover;                },function (res) {                    console.log("出错了")                    console.log(JSON.stringify(res))                    _this.refreshUploadToken(_this.uploadCover(name));                },zone)                this.coverPicture = cover;            },            /**             * 上传单个图片             * @param name             */            uploadSingle:function(name){                let _this = this;                imageArray.map(function (item) {                    if(item.name == name){                        uploadPicture(item.file,function (res) {                            item.key = res.key;                            item.status = 1;                            item.width = res.width;                            item.height = res.height;                        },function (res) {                            var total = res.total;                            item.progress = total.percent                            var theImages = _this.images;                            theImages.map(function (progressItem) {                                if(progressItem.name == item.name){                                    progressItem.progress = Math.round(total.percent);                                }                                return progressItem;                            })                            _this.images = theImages;                        },function (res) {                            _this.refreshUploadToken(_this.uploadSingle(name));                        },zone)                    }                    return item;                })                this.images = imageArray;            },            /**             * 上传多张图片             * @param e             * @returns {boolean}             */            submitPicture:function (e) {                if(imageArray.length == 0){                    alert("图片不能为空！")                    return false;                }                let _this = this;                imageArray.map(function (item) {                    if(item.status == 0){                        uploadPicture(item.file,function (res) {                            console.log("图片集信息：",JSON.stringify(res));                            item.key = res.key;                            item.status = 1;                            item.width = res.width;                            item.height = res.height;                        },function (res) {                            var total = res.total;                            item.progress = total.percent                            var theImages = _this.images;                            theImages.map(function (progressItem) {                                if(progressItem.name == item.name){                                    progressItem.progress = Math.round(total.percent);                                }                                return progressItem;                            })                            _this.images = theImages;                        },function (res) {                            //_this.refreshUploadToken(_this.submitPicture(e));                        },zone)                    }                    return item;                })                this.images = imageArray;            }        }    })    $("#select").unbind("change").bind("change",function(){        file = this.files[0];        console.log(this.files);        imageArray.push({file:file,name:file.name,key:'',progress:0,status:0,show:false,width:0,height:0});    })    /**     * 获取token     */    $.ajax({url: "/admin/qiniu/token", success: function(res){            token = res.data.token;        }    });</script></html>