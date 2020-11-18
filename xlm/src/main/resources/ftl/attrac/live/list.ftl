<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>直播间管理</title>
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
    <div class="select">
        <#--查询条件-->
    </div>
    <div class="len-form-item">
        <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">查询</button>
    </div>
</div>
<div class="layui-col-md12 len-button">
    <#--工具栏-->
    <div class="layui-btn-group">
        <@shiro.hasPermission name="attract:liveSyn">
            <button class="layui-btn layui-btn-normal  layui-btn-sm" data-type="tongBuLive">
                <i class="layui-icon">&#xe608;</i>同步直播间
            </button>
        </@shiro.hasPermission>
    </div>
</div>
<table id="proList" width="100%" lay-filter="user"></table>
<script type="text/html" id="bar">
    <#--工具条-->
    <@shiro.hasPermission name="attract:liveUpOrDown">
        {{# if(d.up){ }}
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="down">不显示</a>
        {{# } }}
        {{# if(!d.up){ }}
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="up">显示</a>

        {{# } }}
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
        var form = layui.form;
        table = layui.table;
        upload = layui.upload;
        //方法级渲染
        table.render({
            id: 'proList',
            elem: '#proList'
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
                {checkbox: true, fixed: false, width: '5%'}
                , {
                    field: 'id',
                    title: 'ID',
                    sort: true,
                    hide: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {
                    field: 'coverImg',
                    title: '背景图',
                    width: 110,
                    style: 'height:100px;',
                    templet: function (d) {
                        return '<div><img src="' + d.coverImg + '"></div>'
                    }
                }
                , {
                    field: 'name', title: '直播间名称', width: '15%', sort: true,
                }
                , {
                    field: 'liveStatus', title: '直播状态', sort: true, templet: function (r) {

                        if (r.liveStatus == '101') {
                            return "直播中"
                        }
                        if (r.liveStatus == '102') {
                            return "未开始"
                        }
                        if (r.liveStatus == '103') {
                            return "已结束"
                        }
                        if (r.liveStatus == '104') {
                            return "禁播"
                        }
                        if (r.liveStatus == '105') {
                            return "暂停"
                        }
                        if (r.liveStatus == '106') {
                            return "异常"
                        }
                        if (r.liveStatus == '107') {
                            return "已过期"
                        }

                    }
                }

                , {field: 'right', title: '操作', toolbar: "#bar"}
            ]]
            , page: true,
            height: 'full-100'
        });


        var $ = layui.$, active = {
            select: function () {
                table.reload('proList', {
                    where: {}
                });
            },
            reload: function () {
                //查询条件
                table.reload('proList', {
                    where: {}
                });
            },
            tongBuLive: function () {
                layer.load();
                $.ajax({
                    url: "syn",
                    type: "get",
                    success: function (d) {
                        table.reload('proList')
                        layer.closeAll('loading');
                    }, error: function () {
                        alert('error');
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
            //审核操作
            if (obj.event === 'up') {
                layer.confirm('确定上架[<label style="color: #00AA91;">' + data.name + '</label>]?', {
                    btn: ['确认']
                }, function (index) {
                    send(data.id, true)
                    layer.close(index)
                });
            }
            //审核操作
            if (obj.event === 'down') {
                layer.confirm('确定下架[<label style="color: #00AA91;">' + data.name + '</label>]?', {
                    btn: ['确认']
                }, function (index) {
                    send(data.id, false)
                    layer.close(index)
                });
            }

        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });

    function send(id, type) {
        var data = {id: id, type: type};
        $.ajax({
            url: "upOrDown",
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
