<!DOCTYPE html>
<html>
<head><title>客户流失管理</title><#include "../common.ftl"></head>
<body class="childrenBody">
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <a class="layui-btn search_btn" data-type="reload"><i class="layui-icon">&#xe615;</i> 搜索</a>
    </blockquote>
    <table id="customerLossList" class="layui-table" lay-filter="customerLosses"></table>
    <script id="customerLossListBar" type="text/html">
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="confirmLoss">确认流失</a>
    </script>
</form>
<script type="text/javascript" src="${ctx}/js/customerLoss/customer.loss.js"></script>
</body>
</html>
