
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>加盟产品管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="lenos-search">
    <div class="len-form-item">
        <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">查询</button>
    </div>
</div>
<div class="layui-col-md12 len-button">
    <div class="layui-btn-group">
        <#--        <@shiro.hasPermission name="attract:productAdd">-->
        <button class="layui-btn layui-btn-normal  layui-btn-sm" data-type="add">
            <i class="layui-icon">&#xe608;</i>新增
        </button>
        <#--        </@shiro.hasPermission>-->

    </div>
</div>
<table id="proList" width="100%" lay-filter="user"></table>
<script type="text/html" id="bar">
    <@shiro.hasPermission name="attract:productUpdate">
        {{# if(d.upState){ }}
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="down">下架</a>
        {{# } }}
        {{# if(!d.upState){ }}
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="up">上架</a>
        {{# } }}

    </@shiro.hasPermission>
    <@shiro.hasPermission name="attract:productUpdate">
        <a class="layui-btn layui-btn-xs  layui-btn-normal" lay-event="edit">编辑</a>
    </@shiro.hasPermission>
    <@shiro.hasPermission name="attract:productDel">
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
        table= layui.table;
        //方法级渲染
        table.render({
            id: 'proList',
            elem: '#proList'
            , url: 'showAttractProductList'
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
                {checkbox: true, fixed: true, width: '10%'}
                , {
                    field: 'id',
                    title: 'ID',
                    width: '20%',
                    sort: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {field: 'productName', title: '产品名称', width: '20%', sort: true}
                , {field: 'price', title: '单价', width: '20%', sort: true}
                , {field: 'upState', title: '上下架', width: '20%', sort: true,templet : function (r) {
                            if(r.upState){
                                return "上架"
                            }else {
                                return "下架"
                            }
                    }}
                , {field: 'right', title: '操作', width: '20%', toolbar: "#bar"}
            ]]
            , page: true,
            height: 'full-100'
        });

        var $ = layui.$, active = {
            select: function () {
                var uname = $('#uname').val();
                var email = $('#email').val();
                table.reload('proList', {
                    where: {
                        username: uname,
                        email: email
                    }
                });
            },
            reload: function () {
                $('#uname').val('');
                $('#email').val('');
                table.reload('proList', {
                    where: {
                        username: null,
                        email: null
                    }
                });
            },
            add: function () {
                add('添加产品', '/m/attractProduct/toAdd', 650, 650);
            },
            update: function () {
                var checkStatus = table.checkStatus('proList')
                    , data = checkStatus.data;
                if (data.length !== 1) {
                    layer.msg('请选择一行编辑,已选[' + data.length + ']行', {icon: 5});
                    return false;
                }
                update('编辑产品', '/m/attractProduct/toUpdate?id=' + data[0].id, 700, 450);
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
            } else if (obj.event === 'del') {
                layer.confirm('确定删除商品[<label style="color: #00AA91;">' + data.productName + '</label>]?', {
                    btn: ['删除']
                }, function (index) {
                    del(data.id);
                    layer.close(index)
                });
            } else if (obj.event === 'edit') {
                update('编辑产品', '/m/attractProduct/toUpdate?id=' + data.id, 700, 650);
            }
            //上架操作
            if(obj.event === 'up'){
                upOrDown(data.id,"up")
            }
            //下架操作
            if(obj.event === 'down'){
                upOrDown(data.id,"down")
            }
        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });



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
    
    function upOrDown(id,type) {
        var data={id:id,type:type};
        $.ajax({
            url:"upOrDown",
            type:"post",
            data:data,
            success:function(d){
                if(d.flag){
                    window.top.layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                    layui.table.reload("proList");

                }else{
                    window.top.layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                }
            },error:function(){
                alert('error');
            }
        });
    }
    
    
    function del(id) {
        var data={id:id};
        $.ajax({
            url:"del",
            type:"post",
            data:data,
            success:function(d){
                if(d.flag){
                    window.top.layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                    layui.table.reload("proList");

                }else{
                    window.top.layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                }
            },error:function(){
                alert('error');
            }
        });
    }
</script>
</body>
</html>
