<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>定制管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="lenos-search">
    <#--查询条件-->
    <div class="select">

        <div class="len-form-item">
            <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">刷新
            </button>
        </div>
    </div>
</div>
<div class="layui-col-md12 len-button">

</div>
<table id="specList" width="100%" lay-filter="user"></table>
<script type="text/html" id="bar">

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
            , url: 'showList?id=' + '${id}'
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
                , {field: 'projectName', title: '项目名字', sort: true}
                , {field: 'projectAddress', title: '项目地址', sort: true}

                , {field: 'linkName', title: '联系人', sort: true}

                , {field: 'linkTel', title: '联系方式', sort: true}

                , {field: 'height', title: '高度', sort: true}

                , {field: 'bedCore', title: '床芯', sort: true}

                , {field: 'specialMaterial', title: '特殊材质', sort: true}
                , {field: 'size', title: '尺寸', sort: true}
                , {field: 'remark', title: '备注', sort: true}

                , {field: 'right', title: '操作', toolbar: "#bar"}
            ]]
            , page: true,
            height: 'full-100'
        });

        var $ = layui.$, active = {
            select: function () {

                table.reload('specList', {
                    where: {}
                });
            },
            reload: function () {

                table.reload('specList', {
                    where: {}
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


        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });


</script>
</body>
</html>
