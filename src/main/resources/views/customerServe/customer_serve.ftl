<!DOCTYPE html>
<html>
<head><title>服务管理</title><#include "../common.ftl"></head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <input type="hidden" name="state" value="${state!}">
        <div class="layui-inline">
            <div class="layui-input-inline">
                <input type="text" name="customer" class="layui-input searchVal" placeholder="客户名" />
            </div>
            <div class="layui-input-inline">
                <input type="text" name="serveType" class="layui-input searchVal" placeholder="服务类型" />
            </div>
            <a class="layui-btn search_btn" data-type="reload"><i class="layui-icon">&#xe615;</i> 搜索</a>
        </div>
    </blockquote>
    <table id="serveList" class="layui-table" lay-filter="serves"></table>
    <script id="toolbarDemo" type="text/html">
        <#if stateNum?? && stateNum == 1>
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add"><i class="layui-icon">&#xe608;</i>创建服务</a>
        </div>
        </#if>
    </script>
    <script id="serveListBar" type="text/html">
        <#if stateNum?? && stateNum == 1>
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
        <#elseif stateNum?? && stateNum == 2>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="assign">分配</a>
        <#elseif stateNum?? && stateNum == 3>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="process">处理</a>
        <#elseif stateNum?? && stateNum == 4>
        <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="feedback">反馈</a>
        <#elseif stateNum?? && stateNum == 5>
        <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
        </#if>
    </script>
</form>
<script type="text/javascript">
    var stateNum = ${stateNum!1};
</script>
<script type="text/javascript" src="${ctx}/js/customerServe/customer.serve.js"></script>
</body>
</html>
