<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>酒店会员管理</title>
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

    <div class="len-form-item">
        <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">查询
        </button>
    </div>
</div>
<div class="layui-col-md12 len-button">
    <div class="layui-btn-group">


    </div>
</div>
<table id="proList" width="100%" lay-filter="user"></table>
<script type="text/html" id="bar">
    {{# if(d.status==0){ }}
    <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="down">驳回</a>
    <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="up">通过</a>
    {{# } }}
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
                {checkbox: true, fixed: false, width: '10%'}
                , {
                    field: 'id',
                    title: 'ID',
                    width: '20%',
                    sort: true,
                    hide: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'name', title: '姓名', sort: true}
                , {field: 'phone', title: '联系方式', sort: true}
                , {field: 'jobNumber', title: '工号', sort: true}
                , {
                    field: 'proofOne',
                    title: '凭证1',
                    width: 110,
                    style: 'height:100px;',
                    sort: true,
                    templet: function (d) {
                        return '<div><img src="' + d.proofOne + '"></div>'
                    }
                }, {
                    field: 'proofTwo',
                    title: '凭证2',
                    width: 110,
                    style: 'height:100px;',
                    sort: true,
                    templet: function (d) {
                        return '<div><img src="' + d.proofTwo + '"></div>'
                    }
                }
                , {
                    field: 'proofThree',
                    title: '凭证3',
                    width: 110,
                    style: 'height:100px;',
                    sort: true,
                    templet: function (d) {
                        return '<div><img src="' + d.proofThree + '"></div>'
                    }
                }
                , {
                    field: 'status', title: '审核标识', sort: true, templet: function (d) {
                        if (d.status == 0) {
                            return "待审核"
                        } else if (d.status == 1) {
                            return "审核通过"
                        } else if (d.status == 2) {
                            return "审核未通过"
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
                    where: {

                    }
                });
            },
            reload: function () {
                table.reload('proList', {
                    where: {

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
                upOrDown(data.id, true)
            }
            //下架操作
            if (obj.event === 'down') {
                upOrDown(data.id, false)
            }
        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });




    function upOrDown(id, type) {
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
