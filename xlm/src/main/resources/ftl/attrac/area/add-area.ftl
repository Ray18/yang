

<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="UTF-8">
    <title>添加地址</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;overflow: auto;">
<#--            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">基础信息</legend>
                </fieldset>
            </div>-->
            <div style="margin-left:25%">
                <div class="layui-form-item">
                    <label for="parentArea" class="layui-form-label">
                        <span class="x-red">*</span>父级ID
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="parentArea" name="parentArea" lay-verify="parentArea"
                               autocomplete="off" class="layui-input">
                    </div>
                    <div id="ms" class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span><span id="ums">必须填写</span>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="name" class="layui-form-label">
                        <span class="x-red">*</span>名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="name" name="name" lay-verify="name"
                               autocomplete="off" class="layui-input">
                    </div>
                    <div id="ms" class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span><span id="ums">必须填写</span>
                    </div>
                </div>
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
    console.info(flag);
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

        // //富文本
        // var layedit = layui.layedit;
        // layedit.set({
        //     uploadImage: {
        //         url: '../../m/common/layUpload' //接口url
        //         ,type: 'post' //默认post
        //     }
        //
        // });
        var index = layedit;
        form.verify({
            headImg: function (value) {
                if (value.trim() == "") {
                    return "图片不能为空";
                }
            },

            title: function (value) {
                if (value.trim() == "") {
                    return "标题不能为空";
                }
            }
            ,

            caseDetails: function () {
                if (layedit.getContent(index).replace(/\s*/g, "") == "") {
                    return "详情不能为空";
                } else {
                    layedit.sync(index);
                }
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
                $("#headImg").val(res.data.src);
            }
        });

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            layerAjax('addCityCase', data.field, 'citycaseList');
            return false;
        });
        form.render();
    });

</script>
</body>

</html>
