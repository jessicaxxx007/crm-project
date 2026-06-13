<!DOCTYPE html>
<html>
<head><#include "../common.ftl"></head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" value="${id!}">
    <div class="layui-form-item" style="margin-top:30px;">
        <label class="layui-form-label">选择分配人</label>
        <div class="layui-input-block">
            <select name="assigner" lay-verify="required" lay-search="">
                <option value="">请选择</option>
                <#if users??>
                    <#list users as u>
                        <option value="${u.trueName}">${u.trueName} (${u.userName})</option>
                    </#list>
                </#if>
            </select>
        </div>
    </div>
    <br/>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn layui-btn-lg" lay-submit lay-filter="assignSubmit">确定分配</button>
            <button type="button" class="layui-btn layui-btn-lg layui-btn-normal" onclick="cancelAssign()">取消</button>
        </div>
    </div>
</form>
<script type="text/javascript">
    layui.use(['form'], function(){
        var form = layui.form, $ = layui.jquery;
        form.render('select');
        form.on('submit(assignSubmit)', function(data){
            parent.layer.closeAll('iframe');
            parent.doAssign($("input[name='id']").val(), data.field.assigner);
            return false;
        });
    });
    function cancelAssign(){
        parent.layer.closeAll('iframe');
    }
</script>
</body>
</html>
