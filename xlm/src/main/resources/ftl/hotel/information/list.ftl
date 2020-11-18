<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>积分商品管理</title>
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
            <@shiro.hasPermission name="hotel:toAddInformation">
                <button class="layui-btn layui-btn-normal  layui-btn-sm" data-type="add">
                    <i class="layui-icon">&#xe608;</i>新增
                </button>
            </@shiro.hasPermission>

        </div>
    </div>
    <table id="proList" width="100%" lay-filter="user"></table>
    <script type="text/html" id="bar">

        <@shiro.hasPermission name="hotel:updateInformation">
            <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="edit">编辑</a>

        </@shiro.hasPermission>
        <@shiro.hasPermission name="hotel:informationDel">
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
                    }, {
                        field: 'headImg',
                        title: '封面',
                        width: 110,
                        style: 'height:100px;',
                        sort: true,
                        templet: function (d) {
                            return '<div><img src="' + d.headImg + '"></div>'
                        }
                    }
                    , {field: 'title', title: '标题', sort: true}
                    , {
                        field: 'type', title: '类型', sort: true, templet: function (d) {
                            if (d.type==1) {
                                return "工具"
                            } else {
                                return "内容"
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
                },
                add: function () {
                    add('添加产品', '/m/hotelInformation/toAdd', 650, 650);
                },
                update: function () {
                    var checkStatus = table.checkStatus('proList')
                        , data = checkStatus.data;
                    if (data.length !== 1) {
                        layer.msg('请选择一行编辑,已选[' + data.length + ']行', {icon: 5});
                        return false;
                    }
                    update('编辑', '/m/hotelInformation/toUpdate?id=' + data[0].id, 700, 700);
                }
            };

            //监听表格复选框选择
            table.on('checkbox(user)', function (obj) {
                console.log(obj)
            });
            //监听工具条
            table.on('tool(user)', function (obj) {
                var data = obj.data;
                if (obj.event === 'detail') {
                    detail('编辑用户', '' + data.id, 700, 800);
                } else if (obj.event === 'del') {
                    layer.confirm('确定删除商品[<label style="color: #00AA91;">' + data.title + '</label>]?', {
                        btn: ['删除']
                    }, function (index) {
                        del(data.id);
                        layer.close(index)
                    });
                } else if (obj.event === 'edit') {
                    update('编辑', '/m/hotelInformation/toUpdate?id=' + data.id, 700, 700);
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
                url: "del",
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