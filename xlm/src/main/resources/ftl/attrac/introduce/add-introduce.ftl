

<!DOCTYPE html>
<html xmlns:th="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="UTF-8">
    <title>物资管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;overflow: auto;">
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
                        <img src="${re.contextPath}/plugin/x-admin/images/bg.png" width="100px" height="100px"
                             class="layui-upload-img layui-circle">
                    </div>
                </div>
            </div>

            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">基本信息</legend>
                </fieldset>
                <div class="layui-inline">
                    <label for="title" class="layui-form-label">
                        <span class="x-red">*</span>标题
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="producName" name="producName" lay-verify="producName"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

                <div class="layui-inline">

                    <label for="title" class="layui-form-label">
                        <span class="x-red">*</span>种类
                    </label>
                    <div class="layui-input-inline">
                        <select name="type" lay-verify="type" lay-filter="sel" id="sel">
                            <option value="">请选择种类</option>
                        </select>
                    </div>
                </div>

                <div class="layui-form-item">
                    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                        <legend style="font-size:16px;">图文详情</legend>
                    </fieldset>
                    <textarea id="demo" name="contentDetails" lay-verify="contentDetails">
                </textarea>
                    <input type="hidden" id="headImg" value="" name="headImg" lay-verify="headImg"/>
                    <input type="hidden" id="typeName" value="" name="typeName" lay-verify="typeName"/>

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
        // var layedit = layui.layedit;
        // layedit.set({
        //     uploadImage: {
        //         url: '../../m/common/layUpload' //接口url
        //         ,type: 'post' //默认post
        //     }
        //
        // });

        //富文本
        var layedit = UE.getEditor('demo', {
            elementPathEnabled : false, //是否启用元素路径，默认是显示
            wordCount : false //是否开启字数统计
        });
        var index = layedit;
        form.verify({
            contentDetails: function () {
                if (layedit.getContent(index).replace(/\s*/g, "") == "") {
                    return "详情不能为空";
                }else{
                    layedit.sync(index);
                }
            },
            producName: function (value) {
                if (value.trim() == "") {
                    return "标题不能为空";
                }
            },
            headImg: function (value) {
                if (value.trim() == "") {
                    return "图片不能为空";
                }
            },
            type: function (value) {
                if (value.trim() == "") {
                    return "种类不能为空";
                }
            },


        })


        $.ajax({
            url: "../apit/selectAll",
            type: "GET",
            dataType: "json",
            success: function (result) {
                $.each(result.data, function (l) {
                    var option = document.createElement("option");  // 创建添加option属性
                    option.setAttribute("value", result.data[l].id); // 给option的value添加值
                    option.innerText = result.data[l].name;     // 打印option对应的纯文本
                    sel.appendChild(option);           //给select添加option子标签
                    form.render("select");
                })
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

            var typename = $("#sel option:selected").text();
            data.field.typeName = typename;

            layerAjax('addIntroduce', data.field, 'introduceList');
            return false;
        });
        form.render();
    });

</script>
</body>

</html>
