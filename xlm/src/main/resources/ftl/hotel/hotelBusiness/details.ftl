<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>商机管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;overflow: auto;">
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>获得积分
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id" value="${model.id}">
                    <input type="text" id="name" value="${model.thisIntegral}" name="thisIntegral"
                           lay-verify="thisIntegral"
                           autocomplete="off" class="layui-input">

                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>会员名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="name" value="${model.nickName}" name="nickName"
                           autocomplete="off" class="layui-input">

                </div>
            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>会员手机号
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="memberPhone" value="${model.memberPhone}" name="memberPhone"
                           autocomplete="off" class="layui-input">

                </div>
            </div>


            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>酒店名称
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="hotelName" value="${model.hotelName}" name="hotelName"
                           autocomplete="off" class="layui-input">

                </div>
            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>联系人
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="linkName" value="${model.linkName}" name="linkName"
                           autocomplete="off" class="layui-input">

                </div>
            </div>


            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>联系电话
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="linkPhone" value="${model.linkPhone}" name="linkPhone"
                           autocomplete="off" class="layui-input">

                </div>
            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>备注
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="remark" value="${model.remark}" name="remark"
                           autocomplete="off" class="layui-input">

                </div>
            </div>


            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>本次积分
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="thisIntegral" value="${model.thisIntegral}" name="thisIntegral"
                           autocomplete="off" class="layui-input">

                </div>
            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red">*</span>是否通过
                </label>
                <div class="layui-input-inline">
                    <#if model.audit==1>
                        待审核

                    </#if>
                    <#if model.audit==2>
                        驳回

                    </#if>
                    <#if model.audit==3>
                        通过
                    </#if>

                </div>
            </div>


        </div>
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <button class="layui-btn layui-btn-primary" id="close">
                    取消
                </button>
            </div>
        </div>

    </form>
</div>
<script>

    var flag, msg;
    layui.use(['form', 'layer', 'upload', 'layedit'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer,
            upload = layui.upload;


        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });
        form.render();
    });

</script>
</body>

</html>
