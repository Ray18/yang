

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
                <legend style="font-size:16px;">图片上传</legend>
            </fieldset>
            <div class="layui-input-inline">
                <div class="layui-upload-drag" style="margin-left:10%;" id="test10">
                    <i style="font-size:30px;" class="layui-icon"></i>
                    <p style="font-size: 10px">点击上传，或将文件拖拽到此处</p>
                </div>
            </div>
            <div class="layui-input-inline">
                <div id="demo2" style="margin-top: 20px;margin-left: 50px">
                    <img src="${introduce.headImg}" width="100px" height="100px"
                         class="layui-upload-img layui-circle">
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <label for="title" class="layui-form-label">
                <span class="x-red">*</span>类别
                <input type="hidden" value="${introduce.typeName}" name="typeName" id="typeName">
            </label>
            <div class="layui-input-inline">
                <select name="type">
                    <#list type as mode>
                        <option value="${mode.id}" <#if mode.id==introduce.type > selected</#if> >${mode.name}</option>
                    </#list>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">基本信息</legend>
            </fieldset>
            <label for="name" class="layui-form-label">
                <span class="x-red">*</span>标题
            </label>
            <div class="layui-input-inline">
                <input type="text" id="producName" name="producName" value="${introduce.producName}"
                       lay-verify="producName"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">图文详情</legend>
            </fieldset>
            <textarea id="demo" name="contentDetails" lay-verify="contentDetails">
                    ${introduce.contentDetails}
                </textarea>
            <input type="hidden" id="headImg" value="${introduce.headImg}" name="headImg"/>
            <input value="${introduce.id}" type="hidden" name="id">
        </div>


        <#if !detail>
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
        </#if>
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
        //         , type: 'post' //默认post
        //     }
        //
        // });
        var index = layedit;
        form.verify({
            contentDetails: function () {
                layedit.sync(index);
            }
        })

        upload.render({
            elem: '#test10'
            , url: '../../m/common/layUpload'
            , before: function (obj) {
                //预读，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo2').find('img').remove();
                    $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" width="100px" height="100px" class="layui-upload-img layui-circle">');
                });
            }, done: function (res) {
                if (res != null)
                    $("#headImg").val(res.data.src);
            }
        });

        //自定义验证规则

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            layerAjax('updateIntroduces', data.field, 'introduceList');
            return false;
        });
        form.render();
    });
</script>
</body>

</html>