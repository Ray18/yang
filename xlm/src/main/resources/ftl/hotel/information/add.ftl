<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>资讯管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;height:400px;overflow: auto;">
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>标题
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="title" name="title" lay-verify="title"
                           autocomplete="off" class="layui-input">

                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>封面
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" id="headImg" value="" name="headImg" lay-verify="headImg"/>
                    <button type="button" class="layui-btn" id="coverImgBut">
                        <i class="layui-icon">&#xe67c;</i>上传封面
                    </button>
                </div>
            </div>
            <div class="layui-form-item" id="coverImgDiv">

            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red"></span>文件
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" id="fileUrl" value="" name="fileUrl" lay-verify="fileUrl"/>

                    <button type="button" class="layui-btn" id="fileUrlBut">
                        <i class="layui-icon">&#xe67c;</i>文件
                    </button>
                </div>
            </div>
            <div class="layui-form-item" id="fileDiv">

            </div>


            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red"></span>图文详情
                </label>
                <textarea id="demo" name="details" lay-verify="details">
                </textarea>
            </div>


            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>类型
                </label>
                <div class="layui-input-inline">
                    <input type="radio" name="type" value="1" title="工具" checked="" lay-verify="type">
                    <input type="radio" name="type" value="2" title="内容" lay-verify="type">
                </div>
            </div>


        </div>
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                    确认
                </button>
                <button class="layui-btn layui-btn-primary" id="close">
                    取消
                </button>
            </div>
        </div>

    </form>
</div>
<script type="text/javascript" charset="utf-8" src="../../plugin/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="../../plugin/ueditor/ueditor.all.min.js"> </script>
<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
<script type="text/javascript" charset="utf-8" src="../../plugin/ueditor/lang/zh_CN/zh_CN.js"></script>
<script>

    var flag, msg;
    layui.use(['form', 'layer', 'upload', 'layedit'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer,
            upload = layui.upload;

        //富文本
        // var layedit = layui.layedit;
        // layedit.set({
        //     uploadImage: {
        //         url: '../../m/common/layUpload' //接口url
        //         , type: 'post' //默认post
        //     }
        //
        // });
        //富文本
        var layedit = UE.getEditor('demo', {
            elementPathEnabled : false, //是否启用元素路径，默认是显示
            wordCount : false //是否开启字数统计
        });
        var index = layedit;


        upload.render({
            elem: '#coverImgBut'
            , url: '../../m/common/layUpload'
            , before: function (obj) {
                //预读，不支持ie8
                obj.preview(function (index, file, result) {

                    $('#coverImgDiv').find('img').remove();
                    $('#coverImgDiv').append('<img src="' + result + '" alt="' + file.name + '" width="100px" height="100px" class="layui-upload-img layui-circle">');

                });
            }, done: function (res) {
                $("#headImg").val(res.data.src);

            }
        });

        upload.render({
            elem: '#fileUrlBut'
            , url: '../../m/common/layUpload'
            , exts: 'xls|doc|docx|pdf|PDF|txt|png|xlsx|ppt|mp4|mov|rmvb|AVI|jpg|png|gif|bmp|jpeg|'
            , before: function (obj) {
                //预读，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#fileDiv').find('span').remove();
                    $('#fileDiv').append('<span>' + file.name + "</span>");
                });
            }, done: function (res) {
                $("#fileUrl").val(res.data.src);

            }
        });


        //自定义校验
        form.verify({
            title: function (value) {
                if (value.trim() == "") {
                    return "请输入标题"
                }
            },
            headImg: function (value) {
                if (value.trim() == "") {
                    return "请上传封面"
                }
            },
            details: function () {
                return layedit.sync(index);
            },

            type: function (value) {



            }


        })
        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            var type = data.field.type;
            var details = data.field.details;
            var fileUrl = data.field.fileUrl;
            if (type == 1) {
                debugger
                if (fileUrl.trim() == "") {

                    return "请上传文件";
                }
            }
            if (type == 2) {
                if (details.trim() == "") {

                    return "请填写详情";
                }
            }

            layerAjax('add', data.field, 'proList');
            return false;
        });
        form.render();
    });

</script>
</body>

</html>
