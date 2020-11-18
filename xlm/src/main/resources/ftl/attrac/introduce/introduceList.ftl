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
    <title>用户管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>

<div class="layui-col-md12 len-button">
    <div class="layui-btn-group">
        <@shiro.hasPermission name="attract:showAddIntroduce">
            <button class="layui-btn layui-btn-normal  layui-btn-sm" data-type="add">
                <i class="layui-icon">&#xe608;</i>新增
            </button>
        </@shiro.hasPermission>

    </div>
</div>
<table id="introduceList" width="100%" lay-filter="user"></table>
<script type="text/html" id="bar">
    <@shiro.hasPermission name="attract:showIntroduceDetail">
        <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">查看</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="attract:updateIntroduce">
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="edit">编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="attract:delIntroduce">
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
            id: 'introduceList',
            elem: '#introduceList'
            , url: 'showIntroduceList'
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
                {checkbox: true, fixed: true, width: '10%'}
                , {
                    field: 'id',
                    title: 'ID',
                    width: '10%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'headImg', title: '头图', width: '15%', sort: true}
                , {field: 'contentDetails', title: '图文详情', width: '15%', sort: true}
                , {field: 'type', title: '种类', width: '10%', sort: true}
                , {field: 'typeName', title: '种类名', width: '10%', sort: true}
                , {field: 'producName', title: '标题', width: '10%', sort: true}
                , {field: 'right', title: '操作', width: '15%', toolbar: "#bar"}
            ]]
            , page: true,
            height: 'full-100'
        });

        var $ = layui.$, active = {
            add: function () {
                add('添加产品', '/qj/introduce/showAddIntroduce', 700, 450);
            },
        };

        //监听表格复选框选择
        table.on('checkbox(user)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(user)', function (obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                detail('查看详情', 'qj/introduce/showIntroduceDetail?id=' + data.id, 700, 450);
            } else if (obj.event === 'del') {
                layer.confirm('确定删除?', {
                    btn: ['删除']
                }, function (index) {
                    del(data.id);
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                update('编辑产品', 'qj/introduce/updateIntroduce?id=' + data.id, 700, 450);
            }
        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });


    function detail(title, url, w, h) {
        if (title == null || title === '') {
            title = false;
        }
        if (url == null || url == '') {
            url = "error/404";
        }
        if (w == null || w == '') {
            w = ($(window).width() * 0.9);
        }
        if (h == null || h == '') {
            h = ($(window).height() - 50);
        }
        window.top.layer.open({
            id: 'user-detail',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: true,
            shade: 0.4,
            title: title,
            content: url + '&detail=true',
        });
    }

    /**
     * 更新用户
     */
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
            id: 'user-update',
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
            id: 'user-add',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: false,
            shade: 0.4,
            title: title,
            content: url
        });
    }

    function del(id) {
        var data = {id: id};
        $.ajax({
            url: "delIntroduce",
            type: "post",
            data: data,
            success: function (d) {
                if (d.flag) {
                    window.top.layer.msg(d.msg, {icon: 6, offset: 'rb', area: ['120px', '80px'], anim: 2});
                    layui.table.reload("introduceList");

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
