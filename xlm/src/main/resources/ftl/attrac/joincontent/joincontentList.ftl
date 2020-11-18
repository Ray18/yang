
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>加盟管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>

<table id="joincontentList" width="100%" lay-filter="joincontent"></table>
<script type="text/html" id="bar">
    <@shiro.hasPermission name="attract:updateJoinContent">
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
            id: 'joincontentList',
            elem: '#joincontentList'
            , url: 'showJoinContentList'
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
                {checkbox: true, fixed: true, width: '5%'},
                {
                    field: 'type', title: '内容', width: '20%',
                    style: 'background-color: #009688; color: #fff;', templet: function (d) {
                        if (d.type == "1") {
                            return '加盟优势'
                        }

                        if (d.type == "2") {
                            return '加盟流程'
                        }
                        if (d.type == "3") {
                            return '加盟政策'
                        }
                        if (d.type == "4") {
                            return '活动规则'
                        }
                        if (d.type == "5") {
                            return '走进喜临门'
                        }
                    }
                }
                , {field: 'details', title: '详情', width: '30%'}
                , {field: 'right', title: '操作', width: '20%', toolbar: "#bar"}
            ]]
            , page: true,
            height: 'full-100'
        });


        //监听表格复选框选择
        table.on('checkbox(joincontent)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(joincontent)', function (obj) {
            var data = obj.data;
            if (obj.event === 'edit') {
                update('编辑加盟', 'qj/joincontent/updateJoinContent?id=' + data.id, 700, 450);
            }
        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });
    /*弹出层*/
    /*
     参数解释：
     title   标题
     url     请求的url
     id      需要操作的数据id
     w       弹出层宽度（缺省调默认值）
     h       弹出层高度（缺省调默认值）
     */

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
            id: 'joincontent-update',
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

</script>
</body>
</html>
