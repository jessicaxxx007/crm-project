<!DOCTYPE html>
<html>
<head><#include "../common.ftl"></head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" value="${(serve.id)!}">
    <input type="hidden" name="state" value="${(serve.state)!'fw_001'}">
    <div class="layui-form-item"><label class="layui-form-label">服务类型</label><div class="layui-input-block"><input type="text" class="layui-input" lay-verify="required" name="serveType" value="${(serve.serveType)!}" placeholder="服务类型"></div></div>
    <div class="layui-form-item"><label class="layui-form-label">客户</label><div class="layui-input-block"><input type="text" class="layui-input" lay-verify="required" name="customer" value="${(serve.customer)!}" placeholder="客户名称"></div></div>
    <div class="layui-form-item"><label class="layui-form-label">概要</label><div class="layui-input-block"><input type="text" class="layui-input" name="overview" value="${(serve.overview)!}" placeholder="概要"></div></div>
    <div class="layui-form-item"><label class="layui-form-label">服务请求</label><div class="layui-input-block"><textarea name="serviceRequest" class="layui-textarea">${(serve.serviceRequest)!}</textarea></div></div>
    <br/>
    <div class="layui-form-item"><div class="layui-input-block">
        <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateServe">确认</button>
        <button class="layui-btn layui-btn-lg layui-btn-normal">取消</button>
    </div></div>
</form>
<script type="text/javascript" src="${ctx}/js/customerServe/add.update.js"></script>
</body>
</html>
