<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">

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
                    <span class="x-red"></span>项目名称
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" name="id" value="${model.id}">
                    <input type="text" id="name" value="${model.productName}" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red"></span>报价人姓名
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="name" value="${model.memberName}" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red"></span>原价
                </label>
                <div class="layui-input-inline">
                    <input type="text" id="countPrice" value="${model.countPrice}"
                           autocomplete="off" class="layui-input">

                </div>
            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red"></span>折后价
                </label>
                <div class="layui-input-inline">
                    <input type="text" value="${model.discountPrice}"  autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    <span class="x-red"></span>产品详情
                </label>
                <div class="">
                    <#list model.reqs as resp>
                        <span>
                            产品名称:${resp.productName},
                            产品数量:${resp.productCount},
                            产品单价:${resp.productPrice};
                            商品属性：<#if resp.productType==true>赠品</#if><#if resp.productType!=true>非赠品</#if>
                            </br>
                        </span>
                    </#list>
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
