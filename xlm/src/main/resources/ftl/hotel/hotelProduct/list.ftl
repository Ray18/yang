<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>产品好物管理</title>
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
        商品名称：
        <span class="layui-inline">
                 <input class="layui-input" height="20px" id="char016" autocomplete="off">
        </span>
    </div>

    <div class="len-form-item">
        <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">查询
        </button>
    </div>
</div>
<div class="layui-col-md12 len-button">

</div>
<table id="proList" width="100%" lay-filter="user"></table>
<script type="text/html" id="bar">
    {{# if(d.up){ }}
    <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="down">下架</a>
    {{# } }}
    {{# if(!d.up){ }}
    <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="up">上架</a>
    {{# } }}
    <@shiro.hasPermission name="hotel:updateIntegralGoods">
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="edit">编辑</a>
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
            id: 'proList',
            elem: '#proList'
            , url: 'showList'
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
                {checkbox: true, fixed: false, width: '10%'}
                , {
                    field: 'id',
                    title: 'ID',
                    width: '20%',
                    sort: true,
                    hide: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'char016', title: '产品名称', sort: true}
                , {field: 'kcfl', title: '分类', sort: true}
                , {field: 'cdks', title: '床垫款式', sort: true}
                , {field: 'zycz', title: '主要材质', sort: true}

                , {field: 'thlx', title: '弹簧类型', sort: true}
                , {field: 'cpyd', title: '成品硬度', sort: true}
                , {field: 'cpg', title: '成品高', sort: true}
                , {
                    field: 'up', title: '上下架', sort: true, templet: function (d) {
                        if (d.up) {
                            return "已上架"
                        } else {
                            return "已下架"
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
                var char016 = $('#char016').val();
                table.reload('proList', {
                    where: {
                        char016: char016

                    }
                });
            },
            reload: function () {
                var char016 = $('#char016').val();
                table.reload('proList', {
                    where: {
                        char016: char016

                    }
                });
            },
        };

        //监听表格复选框选择
        table.on('checkbox(user)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(user)', function (obj) {
            var data = obj.data;

            if (obj.event === 'edit') {
                update('编辑', '/m/hotelProduct/toUpdate?id=' + data.id, 700, 700);
            }


            //上架操作
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
            content: url + '&detail=false'
        });
    }


    function upOrDown(id, type) {
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
