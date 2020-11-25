<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>招商线索管理</title>
    <#include "/system/base/header.ftl">
</head>
<body>
<div class="x-body">
    <form class="layui-form layui-form-pane" style="margin-left: 20px;">
        <div style="width:100%;overflow: auto;">
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    客户城市:
                </label>
                <div class="layui-input-inline">
                    <input type="hidden" value="${model.id}" name="id" id="id" autocomplete="off" class="layui-input">
                    <#if model.isCountry==true>
                        <label for="title" class="layui-form-label">国内/${model.city}</label>
                    </#if>
                    <#if model.isCountry!=true>
                        <label for="title" class="layui-form-label">国外/${model.city}</label>
                    </#if>
                </div>
            </div>
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
                    从业经验:
                </label>
                <div class="layui-input-block">
                    <label for="title" class="layui-form-label">
                        ${model.workingExperience}
                    </label>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    运营行业:
                </label>
                <div class="layui-input-block">
                    <label for="title" class="layui-form-label">
                        ${model.industry}
                    </label>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    经营理念:
                </label>

                <div class="layui-input-block">
                    <label for="title" class="layui-form-label">
                        ${model.industryIdea}
                    </label>

                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    资金实力:
                </label>
                <div class="layui-input-block">

                    <label for="title" class="layui-form-label">
                        ${model.deepPocket}
                    </label>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    年营业额:
                </label>
                <div class="layui-input-block">
                    <label for="title" class="layui-form-label">
                        ${model.yearBusiness}
                    </label>
                </div>
            </div>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    管理团队:
                </label>
                <div class="layui-input-block">
                    <label for="title" class="layui-form-label">
                        ${model.managementTeam}
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

            <#if model.auditState==7 || model.auditState==8 || model.auditState==9 >
                <div class="layui-form-item">
                    <label for="title" class="layui-form-label">
                        协助提供商机:
                    </label>
                    <div class="layui-input-block">
                        <label for="title" class="layui-form-label">
                            <#if model.isSub==1> 是
                            <#else>
                                否
                            </#if>
                        </label>

                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="title" class="layui-form-label">
                        协助洽谈:
                    </label>
                    <div class="layui-input-block">
                        <label for="title" class="layui-form-label">
                            <#if model.isTalk==1> 是
                            <#else>
                                否
                            </#if>
                        </label>

                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="title" class="layui-form-label">
                        协助缴纳意向金
                    </label>
                    <div class="layui-input-block">
                        <label for="title" class="layui-form-label">
                            <#if model.isPurpose==1> 是
                            <#else>
                                否
                            </#if>
                        </label>

                    </div>
                </div>
                <div class="layui-form-item">
                    <label for="title" class="layui-form-label">
                        协助缴纳定金
                    </label>
                    <div class="layui-input-block">
                        <label for="title" class="layui-form-label">
                            <#if model.isSeated==1> 是
                            <#else>
                                否
                            </#if>
                        </label>

                    </div>
                </div>

            </#if>
            <div class="layui-form-item">
                <label for="title" class="layui-form-label">
                    总佣金
                </label>
                <div class="layui-input-block">
                    <input type="text" value="${model.thisCommission}" name="thisCommission" id="thisCommission"
                           class="layui-input">
                </div>
                <div class="layui-input-block">
                    <#if model.auditState<=8 >
                        <button class="layui-btn layui-btn-normal" lay-filter="add" lay-submit="">
                            确认修改
                        </button>
                    </#if>

                    <#if model.auditState==8 >
                        <button class="layui-btn layui-btn-normal" lay-filter="senddAdd" lay-submit="">
                            确认发放
                        </button>
                    </#if>
                </div>
            </div>
        </div>
        <div style="width: 100%;height: 55px;background-color: white;border-top:1px solid #e6e6e6;
  position: fixed;bottom: 1px;margin-left:-20px;">
            <div class="layui-form-item" style=" float: right;margin-right: 30px;margin-top: 8px">
                <button class="layui-btn layui-btn-primary" id="close">
                    关闭
                </button>
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


        //监听提交
        form.on('submit(senddAdd)', function (data) {
            //获取ID 金额值
            var s = $('#thisCommission').val();
            var data = {id:$('#id').val(), price:s};
            $.ajax({
                url:"senddAdd",
                type:"post",
                data:data,
                success:function(d){
                    if(d.flag){
                        $('#thisCommission').val(s)
                        window.top.layer.msg(d.msg,{icon:6,offset: 'rb',area:['120px','80px'],anim:2});
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                        table.reload('proList')
                    }else{
                        window.top.layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                    }
                },error:function(){
                    alert('error');
                }
            });

        });

        //监听提交
        form.on('submit(add)', function (data) {
            //获取ID 金额值
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
                        //table.reload('proList')
                    }else{
                        window.top.layer.msg(d.msg,{icon:5,offset: 'rb',area:['120px','80px'],anim:2});
                    }
                },error:function(){
                    alert('error');
                }
            });

        });
        form.render();
    });
</script>
</body>

</html>