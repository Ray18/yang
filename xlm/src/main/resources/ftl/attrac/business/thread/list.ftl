<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>招商线索管理</title>
    <#include "/system/base/header.ftl">
</head>

<body>
<div class="lenos-search">
    <div class="select">
        <div class="layui-input-inline">
            <a class="layui-btn layui-btn-normal layui-btn-xs" href="export" lay-event="daochu">导出</a>
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
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="details">查看详情</a>

    {{# if(d.auditState==8){ }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="details">发放佣金</a>
    {{# } }}
    {{# if(d.auditState!=9){ }}
    <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="shezhi">设置佣金比例</a>
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
        upload = layui.upload;
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
                    sort: true,
                    hide: true,
                    style: 'background-color: #009688; color: #fff;'
                }

                , {
                    field: 'city', title: '客户城市', width: '15%', sort: true, templet: function (d) {
                        if (d.isCountry) {
                            return "国内/" + d.city;
                        } else {
                            return "国外/" + d.city;
                        }


                    }
                }
                , {field: 'name', title: '姓名', sort: true}
                , {
                    field: 'auditState', title: '商机阶段', sort: true, templet: function (r) {

                        if (r.auditState == 0) {
                            return "待审核"
                        }
                        if (r.auditState == 1) {
                            return "已确认"
                        }
                        if (r.auditState == 2) {
                            return "洽谈阶段"
                        }
                        if (r.auditState == 3) {
                            return "意向金阶段"
                        }
                        if (r.auditState == 4) {
                            return "定金阶段"
                        }
                        if (r.auditState == 5) {
                            return "设计阶段"
                        }
                        if (r.auditState == 6) {
                            return "装修阶段"
                        }
                        if (r.auditState == 7) {
                            return "已落店"
                        }
                        if (r.auditState == 8) {
                            return "确认佣金"
                        }
                        if (r.auditState == 9) {
                            return "已发放"
                        }
                        if (r.auditState == 10) {
                            return "无效商机"
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
            if (obj.event === 'details') {
                detail('查看详情', '/m/attractThread/details?id=' + data.id, 700, 650);
            }

            if (obj.event === 'shezhi') {
                shezhi('设置佣金', '/m/attractThread/toUpdateRule?id=' + data.id, 700, 650);
            }
            /*            //审核操作
                        if (obj.event === 'sendYes') {
                            layer.confirm('确定通过[<label style="color: #00AA91;">' + data.linkPerson + '</label>]?', {
                                btn: ['确认']
                            }, function (index) {
                                send(data.id,true)
                                layer.close(index)
                            });
                        }
                        //审核操作
                        if (obj.event === 'sendNo') {
                            layer.confirm('确定不通过[<label style="color: #00AA91;">' + data.linkPerson + '</label>]?', {
                                btn: ['确认']
                            }, function (index) {
                                send(data.id, false)
                                layer.close(index)
                            });
                        }*/

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
            id: 'thread-detail',
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


    function shezhi(title, url, w, h) {
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
            id: 'thread-shezhi',
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


    /*    function del(id) {
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
        }*/


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
