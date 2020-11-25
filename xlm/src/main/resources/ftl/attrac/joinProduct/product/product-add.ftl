

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>加盟产品管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;overflow: auto;">
            <label for="title" class="layui-form-label">
                <span class="x-red">*</span>封面
            </label>
            <div class="layui-input-inline">
                <input type="hidden" id="coverImg" value="" name="coverImg" lay-verify="coverImg"/>
                <button type="button" class="layui-btn" id="upload">
                    <i class="layui-icon">&#xe67c;</i>上传图片
                </button>
            </div>
            <div class="layui-form-item" id="imgs">

            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>套餐名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="productName" name="productName" lay-verify="productName"
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
                    <span class="x-red">*</span>图文详情
                </label>
                <textarea id="demo" name="productDetails" lay-verify="productDetails">
                </textarea>
            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>上下架
                </label>
                <div class="layui-input-block">
                    <input type="radio" name="upState" value="1" title="上架" checked="">
                    <input type="radio" name="upState" value="0" title="下架" >
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
    console.info(flag);
    layui.use(['form', 'layer', 'upload', 'layedit'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer,
            upload = layui.upload;

        // //富文本
        // var layedit = layui.layedit;

        //富文本
        var layedit = UE.getEditor('demo', {
            elementPathEnabled : false, //是否启用元素路径，默认是显示
            wordCount : false //是否开启字数统计
        });
        var index = layedit;


        upload.render({
            elem: '#upload'
            , url: '../../m/common/layUpload'
            , before: function (obj) {
                //预读，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#imgs').find('img').remove();
                    $('#imgs').append('<img src="' + result + '" alt="' + file.name + '" width="100px" height="100px" class="layui-upload-img layui-circle">');
                });
            }, done: function (res) {
                $("#coverImg").val(res.data.src);
                // if (!res.flag) {
                //     layer.msg(res.msg, {icon: 5, anim: 6});
                // } else {
                //     $("#photo").val(res.msg);
                //     console.info($('#photo').val());
                // }
            }
        });
        //自定义校验
        form.verify({
            price: function(value){
                if(value.trim()==""){
                    return "请输入价格"
                }
            },
            coverImg :function(value){
                if (value.trim() == "") {
                    return "请上传图片"
                }
            },
            productName:function (value) {
                if (value.trim() == "") {
                    return "请填写物品名称"
                }

            }

        })
        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            layedit.sync(index);
            layerAjax('add', data.field, 'proList');
            return false;
        });
        form.render();
    });

</script>
</body>

</html>
