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
        <div style="width:100%;overflow: auto;">
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>图片
                </label>
                <div class="layui-input-inline">
                    <button type="button" class="layui-btn" id="imgs3">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>

                </div>

            </div>
            <div class="layui-form-item" id="demo2">
                <input type="hidden" id="imgs"  lay-verify="imgs" value="" name="imgs"/>

            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red"></span>标题
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="title" name="title" lay-verify="title"
                           autocomplete="off" class="layui-input">
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
<script>

    var flag, msg;
    console.info(flag);
    layui.use(['form', 'layer', 'upload'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer,
            upload = layui.upload;


        form.verify({
            imgs: function (value) {
                if (value.trim() == "") {
                    return "图片不能为空";
                }
            }

        })


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
                $("#imgs").val(res.data.src);
            }
        });

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            layerAjax('add', data.field, 'bannerList');
            return false;
        });

    });

</script>
</body>

</html>
