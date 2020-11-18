<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>提现审核管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="lenos-search">
    <div class="select">
        <#--   //顶部工具条-->
    </div>
    <div class="len-form-item">
        <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">查询</button>
    </div>
</div>
<div class="layui-col-md12 len-button">

</div>
<table id="proList" width="100%" lay-filter="user"></table>
<script type="text/html" id="bar">
    <#--工具条-->
    <@shiro.hasPermission name="attract:withdrawEpositAudit">
        {{# if(d.isState==0){ }}
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="yes">同意</a>
        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="no">不同意</a>
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
                {checkbox: true, fixed: true, width: '5%'}
                , {
                    field: 'id',
                    title: 'ID',
                    sort: true,
                    hide: true,
                    style: 'background-color: #009688; color: #fff;'
                }

                , {field: 'money', title: '提现金额', sort: true}
                , {
                    field: 'isState', title: '审核状态', sort: true, templet: function (d) {
                        if (d.isState == 0) {
                            return "待审核"
                        }
                        if (d.isState == 1) {
                            return "审核通过"
                        }
                        if (d.isState == 2) {
                            return "审核不通过"
                        }
                    }
                }
                , {
                    field: 'flas', title: '提现状态', sort: true, templet: function (d) {

                        if (d.flas) {
                            return "提现成功"
                        }
                        if (!d.flas) {
                            return "提现失败:"+d.remark
                        }

                    }
                }
                , {field: 'nickName', title: '姓名', sort: true}
                , {field: 'phone', title: '电话', sort: true}
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
            }
        };

        //监听表格复选框选择
        table.on('checkbox(user)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(user)', function (obj) {
            var data = obj.data;

            if (obj.event === 'yes') {
                send(data.id, true);
            }

            if (obj.event === 'no') {
                send(data.id, false);
            }
            /*            //审核操作
                        if (obj.event === 'sendYes') {
                            layer.confirm('确定通过[<label style="color: #00AA91;">' + data.linkPerson + '</label>]?', {
                                btn: ['确认']
                            }, function (index) {
                                send(data.id,true)
                                layer.close(index)
                            });
                        }
                        //审核操作
                        if (obj.event === 'sendNo') {
                            layer.confirm('确定不通过[<label style="color: #00AA91;">' + data.linkPerson + '</label>]?', {
                                btn: ['确认']
                            }, function (index) {
                                send(data.id, false)
                                layer.close(index)
                            });
                        }*/

        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });


    function send(id, type) {
        var data = {id: id, type: type};
        $.ajax({
            url: "audit",
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
