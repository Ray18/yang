
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>地址管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<!--筛选条件-->
<blockquote class="layui-elem-quote">
    <div class="showFilter clearfix">
        <div class="iptInline">
            <div class="iptCon">
                <input type="text" class="allIpt" id="inputValue" placeholder="请输入父节点id" style="width:200px;">
                <a class="layui-btn layui-btn-sm" id="solr" ">查询</a>
                <@shiro.hasPermission name="attract:showAddCityCase">
                    <a class="layui-btn layui-btn-normal layui-btn-sm" id="add" "><i class="layui-icon">&#xe608;</i>新增</a>
                </@shiro.hasPermission>
            </div>
        </div>
    </div>
</blockquote>

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
    var tableIns;
    layui.use(['table'], function () {
        var table = layui.table;
        //方法级渲染
        tableIns=table.render({
            id: 'citycaseList',
            elem: '#citycaseList'
            , url: 'showAttracAreaList'
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
    });
    $('#solr').on('click', function(){
        reloadTable();
    });
    $('#add').on('click', function(){
        add('添加地址', '/m/attracArea/showAddArea', 700, 450);
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
            content: url,
            btn: ['确定', '取消'],
            btnAlign: 'c',
            yes: function(index, layero){
                //按钮【按钮一】的回调

            }
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


    function reloadTable() {
        //这里以搜索为例
        tableIns.reload({
            where: {
                type: $('#inputValue').val()//关键字
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        });
        //上述方法等价于
        /*table.reload('idTest', {
            where: { //设定异步数据接口的额外参数，任意设
                aaaaaa: 'xxx'
                ,bbb: 'yyy'
                //…
            }
            ,page: {
                curr: 1 //重新从第 1 页开始
            }
        }); //只重载数据*/
    }
</script>
</body>
</html>
