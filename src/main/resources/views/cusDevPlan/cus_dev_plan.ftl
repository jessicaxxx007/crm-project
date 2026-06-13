<!DOCTYPE html>
<html>
<head>
	<title>客户开发计划管理</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form">
	<blockquote class="layui-elem-quote quoteBox">
		<div class="layui-inline">
			<div class="layui-input-inline">
				<input type="text" name="customerName" class="layui-input searchVal" placeholder="客户名" />
			</div>
			<div class="layui-input-inline">
				<input type="text" name="createMan" class="layui-input searchVal" placeholder="创建人" />
			</div>
			<div class="layui-input-inline">
				<select name="devState" id="devState">
					<option value="">开发状态</option>
					<option value="0">未开发</option>
					<option value="1">开发中</option>
					<option value="2">开发成功</option>
					<option value="3">开发失败</option>
				</select>
			</div>
			<a class="layui-btn search_btn" data-type="reload">
				<i class="layui-icon">&#xe615;</i> 搜索
			</a>
		</div>
	</blockquote>

	<table id="cus_dev_planList" class="layui-table" lay-filter="cus_dev_plan" toolbar="#toolbarDemo"></table>

	<script type="text/html" id="toolbarDemo">
		<div class="layui-btn-container">
			<a class="layui-btn layui-btn-normal" lay-event="add">
				<i class="layui-icon">&#xe608;</i> 新增客户开发计划
			</a>
			<a class="layui-btn layui-btn-danger" lay-event="batchDel">
				<i class="layui-icon">&#xe640;</i> 批量删除
			</a>
		</div>
	</script>

	<script type="text/html" id="cus_dev_planListBar">
		<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		<a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="detail">详情</a>
	</script>

</form>

<script type="text/javascript" src="${ctx}/js/cusDevPlan/cus.dev.plan.js"></script>
</body>
</html>