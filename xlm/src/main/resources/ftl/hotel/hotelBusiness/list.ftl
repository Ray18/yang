<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>商机管理</title>
    <#include "/system/base/header.ftl">
</head>
<style>
    .layui-table-cell {
        height: 100%;
        max-width: 100%;
    }
</style>

<body>
<div class="lenos-search">
    <#--查询条件-->
    <div class="select">
        提供者手机号：
        <span class="layui-inline">
                 <input class="layui-input" height="20px" id="memberPhone" autocomplete="off">
        </span>

    </div>
    <div class="len-form-item">
        <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">查询
        </button>
    </div>
</div>
<div class="layui-col-md12 len-button">
    <div class="layui-btn-group">

    </div>
</div>
<table id="specList" width="100%" lay-filter="user"></table>
<script type="text/html" id="bar">
    {{# if(d.audit!=3){ }}
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="yes">通过</a>
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="no">驳回
    {{# } }}
    <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="details">查看详情</a>
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
            id: 'specList',
            elem: '#specList'
            , url: 'showList'
            , parseData: function (res) {
                return {
                    "code": 0,
                    "msg": '',
                    "count": res.data.total,
                    "data": res.data.records
                };
            }
            //数据表格 设置表头
            , cols: [[
                {checkbox: true, fixed: false, width: '10%'}
                , {
                    field: 'id',
                    title: 'ID',
                    width: '20%',
                    sort: true,
                    hide: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'hotelName', title: '酒店名称', sort: true}
                , {field: 'linkName', title: '联系人', sort: true}

                , {field: 'linkPhone', title: '联系电话', sort: true}

                , {field: 'right', title: '操作', toolbar: "#bar"}
            ]]
            , page: true,
            height: 'full-100'
        });

        var $ = layui.$, active = {
            select: function () {
                var memberPhone = $('#memberPhone').val();
                table.reload('specList', {
                    where: {
                        memberPhone: memberPhone

                    }
                });
            },
            reload: function () {
                var memberPhone = $('#memberPhone').val();
                table.reload('specList', {
                    where: {
                        memberPhone: memberPhone

                    }
                });
            }
        };

        //监听表格复选框选择
        table.on('checkbox(user)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(user)', function (obj) {
            var data = obj.data;
            if (obj.event === 'details') {
                //查看详情
                update('查看详情', '/m/hotelBusiness/toDetails?id=' + data.id, 700, 700);

            }

            if (obj.event === 'yes') {
                update('通过', '/m/hotelBusiness/toUpdate?id=' + data.id, 700, 700);

            }
            if (obj.event === 'no') {
                layer.confirm('确定驳回[<label style="color: #00AA91;">' + data.nickName + '</label>]?', {
                    btn: ['删除']
                }, function (index) {
                    no(data.id);
                    layer.close(index)
                });

            }

        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });


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
            id: 'pro-update',
            type: 2,
            area: [w + 'px', h + 'px'],
            fix: false,
            maxmin: true,
            shadeClose: false,
            shade: 0.4,
            title: title,
            content: url + '&detail=false',
            end: function () {
                table.reload("specList");
            }
        });
    }

    function no(id) {
        var data = {id: id};
        $.ajax({
            url: "no",
            type: "post",
            data: data,
            success: function (d) {
                if (d.flag) {
                    window.top.layer.msg(d.msg, {icon: 6, offset: 'rb', area: ['120px', '80px'], anim: 2});
                    layui.table.reload("proList");

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
