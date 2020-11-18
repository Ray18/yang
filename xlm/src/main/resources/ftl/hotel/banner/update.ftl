
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>banner管理</title>
    <#include "/system/base/header.ftl">

    <script type="text/javascript">
        $(document).ready(function () {
            var flag = '${detail}';
            if (flag) {
                $("form").disable();
            }
        });
    </script>

</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">

        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">banner修改</legend>
            </fieldset>
            <div class="layui-input-inline">
                    <button type="button" class="layui-btn" id="imgs3">
                        <i class="layui-icon">&#xe67c;</i>上传图片
                    </button>
            </div>
            <div class="layui-input-inline">
                <div id="demo2">
                    <img src="${banner.bannerImg}" width="100px" height="100px" >
                </div>
            </div>
        </div>

        <div class="layui-form-item">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                <legend style="font-size:16px;">详情修改</legend>
            </fieldset>
            <textarea id="demo" name="bannerDetails" lay-verify="bannerDetails">
                    ${banner.bannerDetails}
                </textarea>
            <input type="hidden" id="bannerImg" value="${banner.bannerImg}" name="bannerImg"/>
            <input value="${banner.id}" type="hidden" name="id">
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


        // var layedit = layui.layedit;
        // layedit.set({
        //     uploadImage: {
        //         url: '../../m/common/layUpload' //接口url
        //         ,type: 'post' //默认post
        //     }
        //
        // });


        var layedit = UE.getEditor('demo', {
            elementPathEnabled : false, //是否启用元素路径，默认是显示
            wordCount : false //是否开启字数统计
        });

        var index = layedit;

        upload.render({
            elem: '#imgs3'
            , url: '../../m/common/layUpload'
            , before: function (obj) {
                //预读，不支持ie8
                obj.preview(function (index, file, result) {
                    $('#demo2').find('img').remove();
                    $('#demo2').append('<img src="' + result + '" alt="' + file.name + '" width="100px" height="100px" class="layui-upload-img layui-circle">');
                });
            }, done: function (res) {
                if (res != null)
                    $("#bannerImg").val(res.data.src);
            }
        });

        //自定义验证规则
        form.verify({
            bannerDetails:function (){
                if (layedit.getContent(index).replace(/\s*/g,"") == "") {
                    return "详情不能为空";
                }else{
                    layedit.sync(index);
                }
            },
        })

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            layerAjax('update', data.field, 'bannerList');
            return false;
        });
        form.render();
    });
</script>
</body>

</html>