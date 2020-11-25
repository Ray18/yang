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
                    <span class="x-red">*</span>规格名称
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" name="goodsId" value="${goodsId}">
                    <input type="text" id="name" name="name" lay-verify="name"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>库存(个)
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="inventory" name="inventory" lay-verify="inventory"
                           autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>价格
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="price" name="price" lay-verify="price"
                           autocomplete="off" class="layui-input">
                </div>
            </div>


            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>封面
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" id="specImg" value="" name="specImg" lay-verify="specImg"/>
                    <button type="button" class="layui-btn" id="coverImgBut">
                        <i class="layui-icon">&#xe67c;</i>上传封面
                    </button>
                </div>
            </div>
            <div class="layui-form-item" id="coverImgDiv">

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
                $("#specImg").val(res.data.src);

            }
        });


        //自定义校验
        form.verify({
            inventory: function (value) {
                if (value.trim() == "") {
                    return "请设置库存"
                }
            },
            price: function (value) {
                if (value.trim() == "") {
                    return "请设置积分"
                }
            },
            specImg: function (value) {
                if (value.trim() == "") {
                    return "请上传封面"
                }
            }


        })
        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            layerAjax('add', data.field, 'specList');
            return false;
        });
        form.render();
    });

</script>
</body>

</html>
