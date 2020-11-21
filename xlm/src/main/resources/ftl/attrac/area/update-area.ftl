
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>案例管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <input type="hidden" id="areaId" value="${attracArea.id}" />
        <div style="width:100%;height:400px;overflow: auto;">
            <div style="margin-left:25%">
                <div class="layui-form-item">
                    <label for="name" class="layui-form-label">
                        <span class="x-red">*</span>名称
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="name" name="name" lay-verify="name"
                               value="${attracArea.name}" autocomplete="off" class="layui-input">
                    </div>
                    <div id="ms" class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span><span id="ums">必须填写</span>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<script>

</script>
</body>

</html>