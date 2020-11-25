<#--Created by IntelliJ IDEA.
User: Administrator
Date:
Time: 12:40
To change this template use File | Settings | File Templates.-->

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>物资管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi"/>
    <link rel="stylesheet" href="${re.contextPath}/plugin/layui/css/layui.css">
    <script type="text/javascript" src="${re.contextPath}/plugin/jquery/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${re.contextPath}/plugin/tools/tool.js"></script>
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;overflow: auto;">
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">物资添加</legend>
                </fieldset>

            </div>
            <div class="layui-form-item">
                <fieldset class="layui-elem-field layui-field-title" style="margin-top: 10px;">
                    <legend style="font-size:16px;">基础信息</legend>
                </fieldset>
            </div>
            <div class="layui-form-item">
                <label for="goodsName" class="layui-form-label">
                    <span class="x-red">*</span>物资名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="goodsName" name="goodsName" lay-verify="goodsName"
                           autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="goodsNum" class="layui-form-label">
                        <span class="x-red">*</span>数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="goodsNum" name="goodsNum" lay-verify="goodsNum" autocomplete="off"
                               class="layui-input" lay-verify="number">
                    </div>
                </div>
                <div class="layui-inline">
                    <label for="goodsUnit" class="layui-form-label">
                        <span class="x-red">*</span>单位
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="goodsUnit" name="goodsUnit" lay-verify="goodsUnit"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>

            </div>
            <div class="layui-form-item">
                <div class="layui-inline">
                    <label for="goodsUseDay" class="layui-form-label" style="width: 150px">
                        <span class="x-red">*</span>预计使用天数
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="goodsUseDay" name="goodsUseDay" lay-verify="number"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-inline">
                    <label for="goodsAccumulateNum" class="layui-form-label" style="width: 150px">
                        <span class="x-red">*</span>累积使用数量
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="goodsAccumulateNum" name="goodsAccumulateNum" lay-verify="number"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
            </div>
            <div>
                <label for="goodsType" class="layui-form-label">
                    物资类型
                </label>
                <div class="layui-input-block">
                    <input type="radio" name="goodsType" value="1" title="口罩" checked="">
                    <input type="radio" name="goodsType" value="2" title="消毒液">
                    <input type="radio" name="goodsType" value="3" title="手套">
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
    console.info(flag);
    layui.use(['form', 'layer', 'upload'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;

        //自定义验证规则
        form.verify({
            goodsName: function (value) {
                if (value.trim() == "") {
                    return "不能为空";
                }
            }
        });

        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        //监听提交
        form.on('submit(add)', function (data) {
            layerAjax('addGood', data.field, 'goodsList');
            return false;
        });
        form.render();
    });
</script>
</body>

</html>
