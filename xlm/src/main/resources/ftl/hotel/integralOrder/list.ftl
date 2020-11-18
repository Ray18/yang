<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>积分订单管理</title>
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
                 <input class="layui-input" height="20px" id="productName" autocomplete="off">
        </span>
        收货人姓名：
        <span class="layui-inline">
                 <input class="layui-input" height="20px" id="takePersonName" autocomplete="off">
        </span>
        手机号：
        <span class="layui-inline">
                 <input class="layui-input" height="20px" id="phone" autocomplete="off">
        </span>
        订单时间：
        <span class="layui-inline">
                 <input class="layui-input" height="20px" id="createDate" autocomplete="off">
        </span>
        订单状态：
        <span class="layui-inline">
            <select name="upState" id="orderState">
                 <option value="">请选择</option>
                <option value="0">待付款</option>
                 <option value="1">代发货</option>
                  <option value="3">已发货</option>
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
    {{# if(d.orderState==1){ }}
    <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="send">发货</a>
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
        var form = layui.form;
        table = layui.table;
        //初始化日期选择
        var laydate = layui.laydate;
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
                {checkbox: true, fixed: false, width: '3%'}
                , {
                    field: 'id',
                    title: 'ID',
                    width: '5%',
                    sort: true,
                    hide: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {
                    field: 'buyerHaedImg',
                    title: '头像',
                    width: 110,
                    style: 'height:100px;',
                    sort: true,
                    templet: function (d) {
                        return '<div><img src="' + d.buyerHaedImg + '"></div>'
                    }
                }
                , {field: 'productName', title: '产品名', sort: true}
                , {field: 'price', title: '单价', sort: true}
                , {field: 'specDetails', title: '规格', sort: true}
                , {field: 'count', title: '数量', sort: true}

                , {field: 'takePersonName', title: '收货人', sort: true}

                , {field: 'phone', title: '手机号', sort: true}
                , {
                    field: 'province', title: '地址', sort: true, templet: function (r) {
                        return r.province + r.city +r.district+ r.addressDetails
                    }
                }

                , {field: 'countPrice', title: '总价', sort: true}

                , {field: 'buyerNickName', title: '昵称', sort: true}
                , {field: 'buyerTel', title: '买家手机号', sort: true}
                , {
                    field: 'orderState', title: '订单状态', sort: true, templet: function (r) {

                        if (r.orderState == 0) {
                            return "待付款"
                        }
                        if (r.orderState == 1) {
                            return "已付款待发货"
                        }
                        if (r.orderState == 3) {
                            return "已发货"
                        }
                    }
                }

                , {field: 'orderNo', title: '订单号', sort: true}
                , {
                    field: 'createDate', title: '下单时间', sort: true, templet: function (d) {
                        var d = d.createDate;
                        return d;
                    }
                }


                , {field: 'right', title: '操作', toolbar: "#bar"}
            ]]
            , page: true,
            height: 'full-100'
        });

        form.render();
        //初始化日历选择器自定义日期格式
        laydate.render({
            elem: '#createDate'
            , format: 'yyyy-MM-dd' //可任意组合
        });

        var $ = layui.$, active = {
            select: function () {
                var productName = $('#productName').val();
                var takePersonName = $('#takePersonName').val();
                var phone = $('#phone').val();
                var orderState = $('#orderState').val();
                var createDate = $('#createDate').val();
                table.reload('proList', {
                    where: {
                        productName: productName,
                        takePersonName: takePersonName,
                        phone: phone,
                        orderState: orderState,
                        createDate: createDate

                    }
                });
            },
            reload: function () {
                var productName = $('#productName').val();
                var takePersonName = $('#takePersonName').val();
                var phone = $('#phone').val();
                var orderState = $('#orderState').val();
                var createDate = $('#createDate').val();
                table.reload('proList', {
                    where: {
                        productName: productName,
                        takePersonName: takePersonName,
                        phone: phone,
                        orderState: orderState,
                        createDate: createDate
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
            //发货操作
            if (obj.event === 'send') {
                layer.confirm('确定发货[<label style="color: #00AA91;">' + data.productName + '</label>]?', {
                    btn: ['确定']
                }, function (index) {
                    send(data.id)
                    layer.close(index)
                });

            }

        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });


    function send(id) {
        var data = {id: id};
        $.ajax({
            url: "send",
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
