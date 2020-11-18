<#-- Created by IntelliJ IDEA.
 User: Administrator
 Date: 6
 Time: 14:00
 To change this template use File | Settings | File Templates.
 用户管理-->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Banner管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>

<div class="layui-col-md12 len-button">
    <div class="layui-btn-group">
        <@shiro.hasPermission name="attract:showAddBanner">
            <button class="layui-btn layui-btn-normal  layui-btn-sm" data-type="add">
                <i class="layui-icon">&#xe608;</i>新增
            </button>
        </@shiro.hasPermission>

    </div>
</div>
<table id="bannerList" width="100%" lay-filter="banner"></table>
<script type="text/html" id="bar">
    <@shiro.hasPermission name="attract:updateBanner">
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="edit">编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="attract:upstateBanner">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="show">上架</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="attract:delBanner">
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </@shiro.hasPermission>
</script>

<script>
    document.onkeydown = function (e) { // 回车提交表单
        var theEvent = window.event || e;
        var code = theEvent.keyCode || theEvent.which;
        if (code == 13) {
            $(".select .select-on").click();
        }
    }
    layui.use('table', function () {
        table = layui.table;
        //方法级渲染
        table.render({
            id: 'bannerList',
            elem: '#bannerList'
            , url: 'showBannerList'
            , parseData: function (res) {
                console.log(JSON.stringify(res.data.records))
                return {
                    "code": 0,
                    "msg": '',
                    "count": res.data.total,
                    "data": res.data.records
                };
            }
            //数据表格 设置表头
            , cols: [[
                {checkbox: true, fixed: true, width: '5%'}
                , {
                    field: 'id',
                    title: 'ID',
                    width: '10%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'bannerImg', title: '照片', width: '20%', sort: true}
                , {field: 'bannerDetails', title: '图文详情', width: '30%'}
                , {
                    field: 'upState', title: '上架情况', width: '20%', templet: function (d) {
                        if (d.upState == "0") {
                            return '未上架'
                        }
                        if (d.upState == "1") {
                            return '已上架'
                        }
                    }
                }
                , {field: 'right', title: '操作', width: '20%', toolbar: "#bar"}
            ]]
            , page: true,
            height: 'full-100'
        });

        var $ = layui.$, active = {
            select: function () {
                var uname = $('#uname').val();
                var email = $('#email').val();
                table.reload('bannerList', {});
            },
            reload: function () {

                table.reload('bannerList',);
            },
            add: function () {
                add('添加Banner', '/qj/banner/showAddBanner', 700, 450);
            }
        };

        //监听表格复选框选择
        table.on('checkbox(banner)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(banner)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('确定删除?', {
                    btn: ['删除']
                }, function (index) {
                    bannerdel(data.id);
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                update('编辑Banner', 'qj/banner/updateBanner?id=' + data.id, 700, 450);
            } else if (obj.event === 'show') {
                layer.confirm('确定上架?', {
                    btn: ['上架']
                }, function (index) {
                    show(data.id);
                    layer.close(index);
                });
            }
        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
    /*弹出层*/
    /*
     参数解释：
     title   标题
     url     请求的url
     id      需要操作的数据id
     w       弹出层宽度（缺省调默认值）
     h       弹出层高度（缺省调默认值）
     */

    function add(title, url, w, h) {
        if (title == null || title == '') {
            title = false;
        }
        if (url == null || url == '') {
            url = "404.html";
        }
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }

        window.top.layer.open({
            id: 'banner-add',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: false,
            shade: 0.4,
            title: title,
            content: url,
        });
    }

    function update(title, url, w, h) {
        if (title == null || title == '') {
            title = false;
        }
        if (url == null || url == '') {
            url = "404.html";
        }
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }

        window.parent.layer.open({
            id: 'banner-update',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: false,
            shade: 0.4,
            title: title,
            content: url + '&detail=false'
        });
    }

    function show(id) {
        var data = {id: id};
        $.ajax({
            url: "upstateBanner",
            type: "post",
            data: data,
            success: function (d) {
                if (d.flag) {
                    window.top.layer.msg(d.msg, {icon: 6, offset: 'rb', area: ['120px', '80px'], anim: 2});
                    layui.table.reload("bannerList");

                } else {
                    window.top.layer.msg(d.msg, {icon: 5, offset: 'rb', area: ['120px', '80px'], anim: 2});
                }
            }, error: function () {
                alert('error');
            }
        });
    }

    function bannerdel(id) {
        var data = {id: id};
        $.ajax({
            url: "delBanner",
            type: "post",
            data: data,
            success: function (d) {
                if (d.flag) {
                    window.top.layer.msg(d.msg, {icon: 6, offset: 'rb', area: ['120px', '80px'], anim: 2});
                    layui.table.reload("bannerList");

                } else {
                    window.top.layer.msg(d.msg, {icon: 5, offset: 'rb', area: ['120px', '80px'], anim: 2});
                }
            }, error: function () {
                alert('error');
            }
        });
    }


</script>
</body>
</html>
