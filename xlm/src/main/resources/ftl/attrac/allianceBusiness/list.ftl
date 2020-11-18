<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>经销商管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="lenos-search">
    <div class="select">

    </div>
    <div class="len-form-item">
        <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">查询</button>
    </div>
</div>
<div class="layui-col-md12 len-button">

</div>
<table id="proList" width="100%" lay-filter="user"></table>
<script type="text/html" id="bar">
    <@shiro.hasPermission name="attract:attractDealerInfoAudit">
        {{# if(d.status==0){ }}
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="sendYes">通过</a>
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="sendNo">不通过</a>
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
            , url: 'showAttractDealerInfoList'
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
                    width: '10%',
                    sort: true,
                    hide: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {
                    field: 'joinCity', title: '加盟城市', width: '15%', sort: true, templet: function (d) {
                        if (d.isHome) {
                            return "国内/" + d.joinCity;
                        } else {
                            return "国外/" + d.joinCity;
                        }


                    }
                }
                , {field: 'industry', title: '经营行业', width: '7%', sort: true}

                , {field: 'linkPerson', title: '联系人', width: '10%', sort: true}
                , {
                    field: 'status', title: '审核状态', width: '10%', sort: true, templet: function (r) {

                        if (r.status == 0) {
                            return "待审核"
                        }
                        if (r.status == 1) {
                            return "已通过"
                        }
                        if (r.status == 2) {
                            return "未通过"
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
            //审核操作
            if (obj.event === 'sendYes') {
                layer.confirm('确定通过[<label style="color: #00AA91;">' + data.linkPerson + '</label>]?', {
                    btn: ['确认']
                }, function (index) {
                    send(data.id, true)
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
