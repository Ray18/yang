<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>走进喜临门管理</title>
    <#include "/system/base/header.ftl">

</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">

        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">修改</legend>
            </fieldset>
            <div class="layui-input-inline">
                <button type="button" class="layui-btn" id="imgs3">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
            </div>

        </div>

        <div class="layui-form-item">
            <div id="demo2">
                <img src="${model.imgs}" width="100px" height="100px">
                <input type="hidden" id="imgs"  lay-verify="imgs" value="${model.imgs}" name="imgs"/>
                <input type="hidden" id="id"   value="${model.id}" name="id"/>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="title" class="layui-form-label">
                <span class="x-red"></span>标题
            </label>
            <div class="layui-input-inline">
                <input type="text" id="title" value="${model.title}" name="title" lay-verify="title"
                       autocomplete="off" class="layui-input">
            </div>
        </div>


        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
      position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit>
                    确定
                </button>
                <button class="layui-btn layui-btn-primary" id="close">
                    取消
                </button>

            </div>
        </div>
    </form>
</div>
<script>
    var flag, msg;
    layui.use(['form', 'layer', 'upload', 'layedit'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer,
            upload = layui.upload;

        upload.render({
            elem: '#imgs3'
            , url: '../../m/common/layUpload'
            , before: function (obj) {
                //预读，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo2').find('img').remove();
                    $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" width="100px" height="100px" class="layui-upload-img">');
                });
            }, done: function (res) {
                if (res != null)
                    $("#imgs").val(res.data.src);
            }
        });

        //自定义验证规则
        form.verify({
            imgs: function (value) {
                if (value.trim()== '') {
                    return "请上传图片";
                }
            }
        })

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            layerAjax('update', data.field, 'bannerList');
            return false;
        });
        form.render();
    });
</script>
</body>

</html>