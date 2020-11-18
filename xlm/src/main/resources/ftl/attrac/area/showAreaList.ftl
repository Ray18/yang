
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>地址管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>


<div class="lenos-search">
    <div class="len-form-item">
         <div class="layui-btn-group">
                <@shiro.hasPermission name="attract:showAddCityCase">
                    <button class="layui-btn layui-btn-normal  layui-btn-sm" data-type="add">
                        <i class="layui-icon">&#xe608;</i>新增
                    </button>
                </@shiro.hasPermission>
                <input class="layui-input" height="20px" value="" id="inputValue">
                <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">查询</button>
        </div>
    </div>
</div>
<table id="citycaseList" width="100%" lay-filter="citycase"></table>
<script type="text/html" id="bar">
    <@shiro.hasPermission name="attract:updateCityCase">
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="edit">编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="attract:delCityCase">
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
    var val=$("#inputValue").val();
    layui.use('table', function () {
        table = layui.table;
        //方法级渲染
        table.render({
            id: 'citycaseList',
            elem: '#citycaseList'
            , url: 'showAttracAreaList?type='+val
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
                 {
                    field: 'id',
                    title: 'ID',
                    width: '20%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'name', title: '名称', width: '20%', sort: true}
                , {field: 'parent', title: '父节点id', width: '20%', sort: true}
                , {field: 'right', title: '操作', width: '20%', toolbar: "#bar"}
            ]]
            , page: true,
            height: 'full-100'
        });

        var $ = layui.$, active = {

            add: function () {
                add('添加地址', '/m/attracArea/showAddArea', 700, 450);
            }
        };

        //监听表格复选框选择
        table.on('checkbox(citycase)', function (obj) {
            console.log(obj)
        });
        //监听工具条
        table.on('tool(citycase)', function (obj) {
            var data = obj.data;
            if (obj.event === 'del') {
                layer.confirm('确定删除?', {
                    btn: ['删除']
                }, function (index) {
                    del(data.id);
                    layer.close(index);
                });
            } else if (obj.event === 'edit') {
                update('编辑案例', 'qj/citycase/updateCityCase?id=' + data.id, 700, 450);
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
            id: 'citycase-update',
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
            url: "delArea",
            type: "post",
            data: data,
            success: function (d) {
                if (d.flag) {
                    window.top.layer.msg(d.msg, {icon: 6, offset: 'rb', area: ['120px', '80px'], anim: 2});
                    layui.table.reload("citycaseList");

                } else {
                    window.top.layer.msg(d.msg, {icon: 5, offset: 'rb', area: ['120px', '80px'], anim: 2});
                }
            }, error: function () {
                alert('error');
            }
        });
    }

    function cx(){
        var val=$("#inputValue").val();
        layui.table.reload("citycaseList");
    }
</script>
</body>
</html>
