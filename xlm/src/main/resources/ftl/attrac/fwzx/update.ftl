
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>地址管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;overflow: auto;">
            <input type="hidden" id="fwzxId" value="${fwzx.id}">
            <div style="margin-left:25%">
                <div class="layui-form-item">
                    <label for="wx" class="layui-form-label">
                        <span class="x-red">*</span>微信
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="wx" name="wx" lay-verify="wx"
                               value="${fwzx.wx}" autocomplete="off" class="layui-input">
                    </div>
                    <div id="wxms" class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span><span id="ums">必须填写</span>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="zj" class="layui-form-label">
                        <span class="x-red">*</span>座机
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="zj" name="zj" lay-verify="zj"
                               value="${fwzx.zj}" autocomplete="off" class="layui-input">
                    </div>
                    <div id="zjms" class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span><span id="ums">必须填写</span>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="tel" class="layui-form-label">
                        <span class="x-red">*</span>联系电话
                    </label>
                    <div class="layui-input-inline">
                        <input type="text" id="tel" name="tel" lay-verify="tel"
                               value="${fwzx.tel}" autocomplete="off" class="layui-input">
                    </div>
                    <div id="telms" class="layui-form-mid layui-word-aux">
                        <span class="x-red">*</span><span id="ums">必须填写</span>
                    </div>
                </div>
                <button type="button" class="layui-btn" id="saveFwzx">
                    <i class="layui-icon">&#xe605;</i> 保存
                </button>
            </div>
        </div>
    </form>
</div>

<script>

    $('#saveFwzx').on('click', function(){
        var wx = $("#wx").val();
        if (wx == null || wx == '') {
            layer.msg('微信不能为空', {icon: 5});
            return false;
        }
        var zj = $("#zj").val();
        if (zj == null || zj == '') {
            layer.msg('座机不能为空', {icon: 5});
            return false;
        }
        var tel = $("#tel").val();
        if (tel == null || tel == '') {
            layer.msg('联系电话不能为空', {icon: 5});
            return false;
        }
        var data = {
            id: $("#fwzxId").val(),
            wx: wx,
            zj: zj,
            tel:tel
        }
        $.ajax({
            url: "/m/fwzx/updateFwzx",
            type: "post",
            data: data,
            success: function (d) {
                if (d.flag) {
                    layer.msg(d.msg, {icon: 6});
                    reloadTable();
                    window.top.layer.close(index);
                } else {
                    layer.msg(d.msg, {icon: 5});
                }
            }, error: function () {
                alert('error');
            }
        });
    });

</script>
</body>
</html>
