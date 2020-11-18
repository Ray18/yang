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
    <div class="select">
        商品名称：
        <span class="layui-inline">
                 <input class="layui-input" height="20px" id="goodsName" autocomplete="off">
        </span>
        操作类型：
        <span class="layui-inline">
            <select name="upState" id="upState">
                 <option value="">请选择</option>
                <option value="true">上架</option>
                <option value="false">下架</option>
            </select>
         </span>
    </div>

        <div class="len-form-item">
            <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">查询
            </button>
        </div>
    </div>
    <div class="layui-col-md12 len-button">
        <div class="layui-btn-group">
            <@shiro.hasPermission name="hotel:toAddIntegralGoods">
                <button class="layui-btn layui-btn-normal  layui-btn-sm" data-type="add">
                    <i class="layui-icon">&#xe608;</i>新增
                </button>
            </@shiro.hasPermission>

        </div>
    </div>
    <table id="proList" width="100%" lay-filter="user"></table>
    <script type="text/html" id="bar">
        {{# if(d.upState){ }}
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="down">下架</a>
        {{# } }}
        {{# if(!d.upState){ }}
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="up">上架</a>
        {{# } }}
        <@shiro.hasPermission name="hotel:updateIntegralGoods">
            <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="edit">编辑</a>
            <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="addGj">添加规格</a>
        </@shiro.hasPermission>
        <@shiro.hasPermission name="hotel:integralGoodsDel">
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
                        field: 'coverImg',
                        title: '封面',
                        width: 110,
                        style: 'height:100px;',
                        sort: true,
                        templet: function (d) {
                            return '<div><img src="' + d.coverImg + '"></div>'
                        }
                    }
                    , {field: 'goodsName', title: '产品名称', sort: true}
                    , {field: 'integral', title: '积分', sort: true}
                    , {field: 'salesCount', title: '销量', sort: true}
                    , {
                        field: 'newProduct', title: '新品', sort: true, templet: function (d) {
                            if (d.newProduct) {
                                return "是"
                            } else {
                                return "否"
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
                    var upState = $('#upState').val();
                    var goodsName = $('#goodsName').val();
                    table.reload('proList', {
                        where: {
                            goodsName: goodsName,
                            upState: upState

                        }
                    });
                },
                reload: function () {
                    var upState = $('#upState').val();
                    var goodsName = $('#goodsName').val();
                    table.reload('proList', {
                        where: {
                            goodsName: goodsName,
                            upState: upState

                        }
                    });
                },
                add: function () {
                    add('添加产品', '/m/integralGoods/toAdd', 650, 650);
                },
                update: function () {
                    var checkStatus = table.checkStatus('proList')
                        , data = checkStatus.data;
                    if (data.length !== 1) {
                        layer.msg('请选择一行编辑,已选[' + data.length + ']行', {icon: 5});
                        return false;
                    }
                    update('编辑', '/m/integralGoods/toUpdate?id=' + data[0].id, 700, 700);
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
                    layer.confirm('确定删除商品[<label style="color: #00AA91;">' + data.remark + '</label>]?', {
                        btn: ['删除']
                    }, function (index) {
                        del(data.id);
                        layer.close(index)
                    });
                } else if (obj.event === 'edit') {
                    update('编辑', '/m/integralGoods/toUpdate?id=' + data.id, 700, 700);
                }

                //添加规格操作
                if(obj.event=="addGj"){
                    addGj('添加规格', '/m/integralGoodsSpec/show?id=' + data.id, 1000, 700);
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




        function addGj(title, url, w, h) {
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
                content: url ,
            });
        }

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
