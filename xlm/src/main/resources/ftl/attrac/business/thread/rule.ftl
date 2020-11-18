<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>设置佣金比例</title>
    <#include "/system/base/header.ftl">
</head>
<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;height:400px;overflow: auto;">

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    客户姓名:
                </label>
                <div class="layui-input-inline">
                    <label for="title" class="layui-form-label">
                        ${model.name}
                    </label>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    客户电话:
                </label>
                <div class="layui-input-inline">
                    <label for="title" class="layui-form-label">
                        ${model.phone}
                    </label>
                </div>
            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    商机阶段
                </label>
                <div class="layui-input-block">
                    <label for="title" class="layui-form-label">
                        <#if model.auditState==0> 待审核</#if>
                        <#if model.auditState==1> 已确认</#if>
                        <#if model.auditState==2> 洽谈阶段</#if>
                        <#if model.auditState==3>意向金阶段 </#if>
                        <#if model.auditState==4>定金阶段 </#if>
                        <#if model.auditState==5>设计阶段 </#if>
                        <#if model.auditState==6> 装修阶段</#if>
                        <#if model.auditState==7> 已落店</#if>
                        <#if model.auditState==8> 确认佣金</#if>
                        <#if model.auditState==9> 已发放</#if>
                        <#if model.auditState==10> 无效商机</#if>
                    </label>
                </div>
            </div>

            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    分值评估(几分):
                </label>
                <div class="layui-input-block">
                    <label for="title" class="layui-form-label">
                        ${model.totalPoints}
                    </label>
                </div>
            </div>

                <div class="layui-form-item">
                    <input type="hidden" value="${rule.id}" name="id" id="id" lay-verify="id">
                    <input type="hidden" value="${model.id}" name="threadId" id="threadId" lay-verify="threadId">
                    <label for="title" class="layui-form-label">
                        商机提供(%)
                    </label>
                    <div class="layui-input-block">
                        <input type="text" value="${rule.submitTread}" name="submitTread" id="submitTread"
                               lay-verify="submitTread"  class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="title" class="layui-form-label">
                        洽谈阶段(%)
                    </label>
                    <div class="layui-input-block">
                        <input type="text" value="${rule.talkRatio}"  name="talkRatio" id="talkRatio"
                               lay-verify="talkRatio"   class="layui-input">
                    </div>
                </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    意向金阶段(%)
                </label>
                <div class="layui-input-block">
                    <input type="text" value="${rule.purposeRatio}" name="purposeRatio" id="purposeRatio"
                           lay-verify="purposeRatio" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    定金阶段(%)
                </label>
                <div class="layui-input-block">
                    <input type="text" value="${rule.depositTatio}"  lay-verify="depositTatio" name="depositTatio" id="depositTatio"
                           class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    已落店(%)
                </label>
                <div class="layui-input-block">
                    <input type="text" value="${rule.seated}"   lay-verify="seated"  name="seated" id="seated"
                           class="layui-input">
                </div>
            </div>
        </div>
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <div class="layui-input-block">
                    <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                        确认修改
                    </button>
                    <button class="layui-btn layui-btn-primary" id="close">
                        关闭
                    </button>
                </div>
            </div>
        </div>

    </form>
</div>
<script>
    var flag, msg;
    layui.use(['form', 'layer'], function () {
        $ = layui.jquery;
        var form = layui.form
            , layer = layui.layer;



        $('#close').click(function () {
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);
        });


        /* 自定义校验*/
        form.verify({



        })


        //监听提交
        form.on('submit(add)', function (data) {

            layerAjax('updateRule', data.field, 'proList');
            var index = parent.layer.getFrameIndex(window.name);
            parent.layer.close(index);

/*            //获取ID 金额值
            var s = $('#thisCommission').val();
            var data = {id:$('#id').val(), price:s};
            $.ajax({
                url:"updatePrice",
                type:"post",
                data:data,
                success:function(d){
                    if(d.flag){
                        $('#thisCommission').val(s)
                        window.top.layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                    }else{
                        window.top.layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                    }
                },error:function(){
                    alert('error');
                }
            });*/

        });
        form.render();
    });
</script>
</body>

</html>