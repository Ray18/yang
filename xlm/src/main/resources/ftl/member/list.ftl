<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>会员管理</title>
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
    <div class="select">
        <#--   //顶部工具条-->
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
                {checkbox: true, fixed: false, width: '5%'}
                , {
                    field: 'id',
                    title: 'ID',
                    sort: true,
                    hide: true,
                    style: 'background-color: #009688; color: #fff;'
                }
                , {
                    field: 'avatarUrl',
                    title: '头像',
                    width: 110,
                    style: 'height:100px;',
                    sort: true,
                    templet: function (d) {
                        return '<div><img src="'+ d.avatarUrl+'"></div>'
                    }
                }
                , {field: 'nickName', title: '微信昵称', sort: true}
                , {field: 'phone', title: '手机号', sort: true}
                , {field: 'integral', title: '账户总积分', sort: true}
                , {field: 'sumMoney', title: '账户金额', sort: true}
                , {
                    field: 'memberClass', title: '会员等级', sort: true, templet: function (d) {
                        if(d.memberClass==null){
                            return "";
                        }
                        if (d.memberClass == 1) {
                            return "白银";
                        }
                        if (d.memberClass == 2) {
                            return "黄金";
                        }
                        if (d.memberClass == 3) {
                            return "白金";
                        }
                        if (d.memberClass == 4) {
                            return "钻石";
                        }
                        if (d.memberClass == 5) {
                            return "至尊";
                        }
                    }
                }
                , {field: 'unionId', title: 'unionId', sort: true, hide: true}
                , {field: 'wxOpenId', title: 'wxOpenId', sort: true, hide: true}
                , {
                    field: 'gender', title: '性别', sort: true, templet: function (d) {
                        if (d.gender == 1) {
                            return "男"
                        }
                        if (d.gender == 2) {
                            return "女"
                        }
                        if (d.gender == null || d.gender==0) {
                            return "未知"
                        }
                    }
                }
                , {
                    field: 'country', title: '地区', sort: true, templet: function (d) {
                        if(d.country==null && d.province == null && d.city ==null){
                            return "";
                        }


                        return d.country + "/" + d.province + "/" + d.city;
                    }
                }

                , {
                    field: 'hotelFlag', title: '酒店会员', sort: true, templet: function (d) {
                        if (d.hotelFlag) {
                            return "是"
                        }
                        if (!d.hotelFlag) {
                            return "否"
                        }
                    }
                }
                , {
                    field: 'hotelFlag', title: '招商员工', sort: true, templet: function (d) {
                        if (d.attractFlag) {
                            return "是"
                        }
                        if (!d.attractFlag) {
                            return "否"
                        }
                    }
                }
                , {
                    field: 'hotelFlag', title: '经销商', sort: true, templet: function (d) {
                        if (d.dealerFlag) {
                            return "是"
                        }
                        if (!d.dealerFlag) {
                            return "否"
                        }
                    }
                }
                , {
                    field: 'hotelFlag', title: '同喜汇', sort: true, templet: function (d) {
                        if (d.tongxihuiFlag) {
                            return "是"
                        }
                        if (!d.tongxihuiFlag) {
                            return "否"
                        }
                    }
                }

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

        });

        $('.len-form-item .layui-btn,.layui-col-md12 .layui-btn').on('click', function () {
            var type = $(this).data('type');
            active[type] ? active[type].call(this) : '';
        });

    });


</script>
</body>
</html>
