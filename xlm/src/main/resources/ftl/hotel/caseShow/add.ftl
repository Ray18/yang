<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>系统参数管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;overflow: auto;">
            <label for="title" class="layui-form-label">
                <span class="x-red">*</span>产品名称
            </label>
            <div class="layui-input-inline">
                <input type="text" id="caseName" name="caseName" lay-verify="caseName"
                       autocomplete="off" class="layui-input">

            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>案例
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="caseInfo" name="caseInfo" lay-verify="caseInfo"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>图1
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" id="img1" value="" name="img1" lay-verify="img1"/>
                    <button type="button" class="layui-btn" id="imgs1">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                </div>
            </div>
            <div class="layui-form-item" id="image1">

            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>图2
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" id="img2" value="" name="img2" lay-verify="img2"/>
                    <button type="button" class="layui-btn" id="imgs2">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                </div>
            </div>
            <div class="layui-form-item" id="image2">

            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>图3
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" id="img3" value="" name="img3" lay-verify="img3"/>
                    <button type="button" class="layui-btn" id="imgs3">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
                </div>
            </div>
            <div class="layui-form-item" id="image3">

            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>类别
                </label>
                <div class="layui-input-inline">
                    <select name="type" >
                        <#list model as mode>
                            <option value="${mode.id}">${mode.name}</option>
                        </#list>
                    </select>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red"></span>图文详情
                </label>
                <textarea id="demo" name="contentDetails" lay-verify="contentDetails">
                </textarea>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>上下架
                </label>
                <div class="layui-input-inline">
                    <input type="radio" name="up" value="true" title="上架" checked="">
                    <input type="radio" name="up" value="false" title="下架" >
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
            elem: '#imgs1'
            , url: '../../m/common/layUpload'
            , before: function (obj) {
                //预读，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#image1').find('img').remove();
                    $('#image1').append('<img src="' + result + '" alt="' + file.name + '" width="100px" height="100px" class="layui-upload-img layui-circle">');
                });
            }, done: function (res) {
                $("#img1").val(res.data.src);

            }
        });


        upload.render({
            elem: '#imgs2'
            , url: '../../m/common/layUpload'
            , before: function (obj) {
                //预读，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#image2').find('img').remove();
                    $('#image2').append('<img src="' + result + '" alt="' + file.name + '" width="100px" height="100px" class="layui-upload-img layui-circle">');
                });
            }, done: function (res) {
                $("#img2").val(res.data.src);

            }
        });


        upload.render({
            elem: '#imgs3'
            , url: '../../m/common/layUpload'
            , before: function (obj) {
                //预读，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#image3').find('img').remove();
                    $('#image3').append('<img src="' + result + '" alt="' + file.name + '" width="100px" height="100px" class="layui-upload-img layui-circle">');
                });
            }, done: function (res) {
                $("#img3").val(res.data.src);

            }
        });


        //自定义校验
        form.verify({
            caseName: function (value) {
                if (value.trim() == "") {
                    return "请输入产品名称"
                }
            },
            caseInfo: function (value) {
                if (value.trim() == "") {
                    return "请输入案例"
                }
            },

            contentDetails: function () {
                return layedit.sync(index);
            }

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
