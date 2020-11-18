<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>加盟产品订单管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="lenos-search">
    <div class="select">
        <span>订单编号：</span>
        <span class="layui-inline">
            <input class="layui-input" height="20px" id="orderNo" name="orderNo" autocomplete="off">
         </span>
        <span>订单时间：</span>
        <span class="layui-inline">
              <input class="layui-input" height="20px" id="createDate"  autocomplete="off">
        </span>
        <span>订单状态：</span>
        <span class="layui-inline">
            <select  name = "orderState" id="orderState">
                 <option value="">请选择</option>
                <option value="0">待付款</option>
                <option value="2">待发货</option>
                <option value="3">已发货</option>
            </select>
        </span>

    </div>
    <div class="len-form-item">
        <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">查询</button>
    </div>
</div>
<div class="layui-col-md12 len-button">

</div>
<table id="proList" width="100%" lay-filter="user"></table>
<script type="text/html" id="bar">
    <@shiro.hasPermission name="attract:productUpdate">
        {{# if(d.orderState==2){ }}
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="send">发货</a>
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
        //初始化日期选择
        var laydate = layui.laydate;
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
                    width: '10%',
                    sort: true,
                    hide: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'productName', title: '产品名', width: '15%', sort: true}
                , {field: 'price', title: '单价', width: '7%', sort: true}

                , {field: 'takePersonName', title: '收货人', width: '10%', sort: true}
                , {field: 'phone', title: '手机号', width: '7%', sort: true}

                , {
                    field: 'province', title: '地址', width: '15%', sort: true, templet: function (r) {
                        return r.province + r.city + r.addressDetails
                    }
                }

                , {field: 'buyerNickName', title: '昵称', width: '15%', sort: true}
                , {field: 'buyerTel', title: '买家手机号', width: '10%', sort: true}
                , {
                    field: 'orderState', title: '订单状态', width: '10%', sort: true, templet: function (r) {

                        if (r.orderState == 0) {
                            return "待付款"
                        }
                        if (r.orderState == 1) {
                            return "已付款"
                        }
                        if (r.orderState == 2) {
                            return "待发货"
                        }
                        if (r.orderState == 3) {
                            return "已发货"
                        }
                    }
                }

                , {field: 'orderNo', title: '订单号', width: '15%', sort: true}
                , {field: 'createDate', title: '下单时间', width: '15%', sort: true,templet:function (d) {
                        var d =d.createDate;
                      /*  return d.year+"年-"+d.monthValue+"月-"+d.dayOfMonth+'日 '+d.hour+':'+d.minute+':'+d.second*/
                    return d;
                }}

                , {field: 'right', title: '操作', width: '20%', toolbar: "#bar"}
            ]]
            , page: true,
            height: 'full-100'
        });

        form.render();
        //初始化日历选择器自定义日期格式
        laydate.render({
            elem: '#createDate'
            ,format: 'yyyy-MM-dd' //可任意组合
        });

        var $ = layui.$, active = {
            select: function () {
                var createDate = $('#createDate').val();
                var orderState = $('#orderState').val();
                var orderNo = $('#orderNo').val();

                table.reload('proList', {
                    where: {
                        createDate: createDate,
                        orderState: orderState,
                        orderNo:orderNo
                    }
                });
            },
            reload: function () {
                //查询条件
                var createDate = $('#createDate').val();
                var orderState = $('#orderState').val();
                var orderNo = $('#orderNo').val();
                table.reload('proList', {
                    where: {
                        createDate: createDate,
                        orderState: orderState,
                        orderNo:orderNo
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
            if (obj.event === 'detail') {
                detail('编辑用户', '' + data.id, 700, 450);
            }
            //发货操作
            if (obj.event === 'send') {
                layer.confirm('确定发货商品[<label style="color: #00AA91;">' + data.productName + '</label>]?', {
                    btn: ['确认']
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


</script>
</body>
</html>
