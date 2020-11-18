<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>招商员工管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="lenos-search">
    <div class="select">
        <div class="layui-input-inline">
            <button type="button" class="layui-btn" id="upload" name="upload">
                <i class="layui-icon">&#xe67c;</i>导入
            </button>
        </div>
    </div>
    <div class="len-form-item">
        <button type="button" class="layui-btn layui-btn-normal layui-btn layui-btn-sm" data-type="select">查询</button>
    </div>
</div>
<div class="layui-col-md12 len-button">

</div>
<table id="proList" width="100%" lay-filter="user"></table>
<script type="text/html" id="bar">
    <#--工具条-->
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
        upload = layui.upload;
        //方法级渲染
        table.render({
            id: 'proList',
            elem: '#proList'
            , url: 'shoList'
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
                    sort: true,
                    hide: true,
                    style: 'background-color: #009688; color: #fff;'
                }

                , {field: 'jobInfo', title: '岗位信息', sort: true}

                , {field: 'areaName', title: '所属地区', sort: true}
                , {field: 'name', title: '姓名', sort: true}
                , {field: 'phone', title: '电话', sort: true}
                , {
                    field: 'status', title: '审核状态', sort: true, templet: function (r) {

                        if (r.status == 0) {
                            return "待审核"
                        }
                        if (r.status == 1) {
                            return "已通过"
                        }
                        if (r.status == 2) {
                            return "未通过"
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
                    where: {}
                });
            },
            reload: function () {
                //查询条件
                table.reload('proList', {
                    where: {}
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

            if (obj.event === 'del') {
                layer.confirm('确定删除[<label style="color: #00AA91;">' + data.name + '</label>]?', {
                    btn: ['删除']
                }, function (index) {
                    del(data.id);
                    layer.close(index)
                });
            }


        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });



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

    upload.render({
        elem: '#upload'
        , url: 'importExcel'
        , accept: 'file' //允许上传类型
        , exts: 'xlsx|xls' //允许上传格式
        , before: function (obj) {
            //文件上传前回调
            layer.load();
        }, done: function (res) {
            //文件上传后回调
            layer.closeAll('loading'); //关闭loading
            table.reload('proList');
        }
    });


    /*
        function send(id,type) {
            var data = {id: id,type:type};
            $.ajax({
                url: "audit",
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
        }*/
</script>
</body>
</html>
