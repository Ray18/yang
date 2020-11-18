

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>物资管理</title>
    <#include "/system/base/header.ftl">


</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">


        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">图片</legend>
            </fieldset>

            <div class="layui-input-inline">
                <div id="demo2" style="margin-top: 20px;margin-left: 50px">
                    <img src="${introduce.headImg}" width="100px" height="100px"
                         class="layui-upload-img layui-circle">
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">基本信息</legend>
            </fieldset>

            <div class="layui-inline">
                <label for="name" class="layui-form-label">
                    <span class="x-red">*</span>标题
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="producName" name="producName" value="${introduce.producName}"
                           lay-verify="producName"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>

            <div class="layui-inline">
                <label for="name" class="layui-form-label">
                    <span class="x-red">*</span>种类
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="typeName" name="typeName" value="${introduce.typeName}"
                           lay-verify="typeName"
                           autocomplete="off" class="layui-input" readonly>
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">图文详情(编辑无效)</legend>
            </fieldset>
            <textarea id="demo" name="contentDetails" lay-verify="contentDetails">
                    ${introduce.contentDetails}
                </textarea>
            <input type="hidden" id="headImg" value="${introduce.headImg}" name="headImg"/>
            <input value="${introduce.id}" type="hidden" name="id">
        </div>


            <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
      position: fixed;bottom: 1px;margin-left:-20px;">
                <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                    <button class="layui-btn layui-btn-primary" id="close">
                        关闭
                    </button>
                </div>
            </div>
    </form>
</div>
<script>
    var flag, msg;
    layui.use(['form', 'layedit'], function () {
        $ = layui.jquery;
        var form = layui.form;

        var layedit = layui.layedit;
        var index = layedit.build('demo');
        form.verify({
            caseDetails: function () {
                layedit.sync(index);
            }
        })


        //自定义验证规则

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });


        form.render();
    });
</script>
</body>

</html>