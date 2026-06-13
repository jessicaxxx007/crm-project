<!DOCTYPE html>
<html>
<head><#include "../common.ftl"></head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" value="${(datadic.id)!}">
    <div class="layui-form-item"><label class="layui-form-label">字典名称</label><div class="layui-input-block"><input type="text" class="layui-input" lay-verify="required" name="dataDicName" value="${(datadic.dataDicName)!}" placeholder="字典名称"></div></div>
    <div class="layui-form-item"><label class="layui-form-label">字典值</label><div class="layui-input-block"><input type="text" class="layui-input" lay-verify="required" name="dataDicValue" value="${(datadic.dataDicValue)!}" placeholder="字典值"></div></div>
    <br/>
    <div class="layui-form-item"><div class="layui-input-block">
        <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateDic">确认</button>
        <button class="layui-btn layui-btn-lg layui-btn-normal">取消</button>
    </div></div>
</form>
<script type="text/javascript" src="${ctx}/js/dataDic/add.update.js"></script>
</body>
</html>
