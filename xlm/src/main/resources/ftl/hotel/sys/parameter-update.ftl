
<html>

<head>
    <meta charset="UTF-8">
    <title>系统参数</title>
    <#include "/system/base/header.ftl">
</head>
<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;height:400px;overflow: auto;">
            <label for="title" class="layui-form-label">
                <span class="x-red">*</span>参数值
            </label>
            <div class="layui-input-inline">
                <input type="hidden" id="id" name="id" value="${model.id}"  >

                <input type="text" id="parValue" name="parValue" lay-verify="parValue"   value="${model.parValue}"   autocomplete="off" class="layui-input">

            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>参数描述
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="remark" name="remark" lay-verify="remark" value="${model.remark}"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>参数code
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="code" name="code" lay-verify="code" value="${model.code}"   disabled="disabled"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">详情修改</legend>
                </fieldset>
                <textarea id="details" name="details" lay-verify="details">
                    ${model.details}
                </textarea>
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
        var layedit = UE.getEditor('demo', {
            elementPathEnabled : false, //是否启用元素路径，默认是显示
            wordCount : false //是否开启字数统计
        });

        // var layedit = layui.layedit;
        // layedit.set({
        //     uploadImage: {
        //         url: '../../m/common/layUpload' //接口url
        //         ,type: 'post' //默认post
        //     }
        //
        // });
        //
        //
        var index = layedit;

        //自定义验证规则
        form.verify({
            parValue: function (value) {
                if (value.trim() == "") {
                    return "请输入参数值"
                }
            },
            remark: function (value) {
                if (value.trim() == "") {
                    return "请输入描述"
                }
            },
            code: function (value) {
                if (value.trim() == "") {
                    return "请填写code"
                }

            },
            details: function () {
                console.log(layedit.getContent(index))
                debugger
                return  layedit.sync(index);
            }
        })

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            layerAjax('update', data.field, 'proList');
            return false;
        });
        form.render();
    });
</script>
</body>

</html>