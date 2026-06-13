<!DOCTYPE html>
<html>
<head><#include "../common.ftl"></head>
<body class="childrenBody">
<form class="layui-form" style="width:80%;">
    <input type="hidden" name="id" value="${(customer.id)!}">
    <div class="layui-form-item"><label class="layui-form-label">客户名称</label><div class="layui-input-block"><input type="text" class="layui-input" lay-verify="required" name="name" value="${(customer.name)!}" placeholder="请输入客户名称"></div></div>
    <div class="layui-form-item"><label class="layui-form-label">客户编号</label><div class="layui-input-block"><input type="text" class="layui-input" name="khno" value="${(customer.khno)!}" placeholder="请输入客户编号"></div></div>
    <div class="layui-form-item"><label class="layui-form-label">地区</label><div class="layui-input-block"><input type="text" class="layui-input" name="area" value="${(customer.area)!}" placeholder="请输入地区"></div></div>
    <div class="layui-form-item"><label class="layui-form-label">客户经理</label><div class="layui-input-block"><input type="text" class="layui-input" name="cusManager" value="${(customer.cusManager)!}" placeholder="请输入客户经理"></div></div>
    <div class="layui-form-item"><label class="layui-form-label">客户等级</label><div class="layui-input-block"><input type="text" class="layui-input" name="level" value="${(customer.level)!}" placeholder="请输入客户等级"></div></div>
    <div class="layui-form-item"><label class="layui-form-label">联系电话</label><div class="layui-input-block"><input type="text" class="layui-input" name="phone" value="${(customer.phone)!}" placeholder="请输入联系电话"></div></div>
    <div class="layui-form-item"><label class="layui-form-label">地址</label><div class="layui-input-block"><input type="text" class="layui-input" name="address" value="${(customer.address)!}" placeholder="请输入地址"></div></div>
    <br/>
    <div class="layui-form-item"><div class="layui-input-block">
        <button class="layui-btn layui-btn-lg" lay-submit="" lay-filter="addOrUpdateCustomer">确认</button>
        <button class="layui-btn layui-btn-lg layui-btn-normal">取消</button>
    </div></div>
</form>
<script type="text/javascript" src="${ctx}/js/customer/add.update.js"></script>
</body>
</html>
