<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>积分商品管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;overflow: auto;">
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="name" name="name" lay-verify="name"
                           autocomplete="off" class="layui-input">

                </div>
            </div>



            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>参数分类
                </label>
                <div class="layui-input-inline">
                    <input type="radio" name="type" value="1" title="高度" checked="">
                    <input type="radio" name="type" value="2" title="床芯">
                    <input type="radio" name="type" value="3" title="特殊材质">
                    <input type="radio" name="type" value="4" title="尺寸">
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
    layui.use(['form', 'layer', 'upload', 'layedit'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer,
            upload = layui.upload;


        //自定义校验
        form.verify({
            name: function (value) {
                if (value.trim() == "") {
                    return "请输入内容"
                }
            },

        })
        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            layerAjax('add', data.field, 'proList');
            return false;
        });
        form.render();
    });

</script>
</body>

</html>
