<!DOCTYPE html>
<html>
<head>
    <title>客户开发计划新增/编辑</title>
    <#include "../common.ftl">
</head>
<body style="padding:15px;">
<form class="layui-form" lay-filter="cusDevPlanForm">
    <input type="hidden" name="id" value="${(plan.id)!''}">
    <input type="hidden" name="saleChanceId" value="${sid!''}">

    <div class="layui-form-item">
        <label class="layui-form-label">开发内容</label>
        <div class="layui-input-block">
            <input type="text" name="planItem" value="${(plan.planItem)!''}" lay-verify="required" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开发时间</label>
        <div class="layui-input-block">
            <input type="text" name="planDate" value="${(plan.planDate)!''}" id="planDate" lay-verify="required" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">开发效果</label>
        <div class="layui-input-block">
            <textarea name="planResult" class="layui-textarea">${(plan.planResult)!''}</textarea>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="submitBtn">保存</button>
        </div>
    </div>
</form>

<script>
    layui.use(['form','laydate'],function(){
        var form = layui.form,
            laydate = layui.laydate,
            $ = layui.jquery;

        laydate.render({elem:'#planDate'});

        // 表单提交
        form.on('submit(submitBtn)',function(data){
            $.ajax({
                url:ctx+'/cusDevPlan/saveOrUpdate',
                type:'post',
                data:data.field,
                success:function(res){
                    if(res.code===200){
                        layer.msg("保存成功",{icon:6},function(){
                            parent.layer.closeAll();
                            parent.tableIns.reload();
                        })
                    }else{
                        layer.msg(res.msg,{icon:5})
                    }
                }
            })
            return false;
        })
    })
</script>
</body>
</html>