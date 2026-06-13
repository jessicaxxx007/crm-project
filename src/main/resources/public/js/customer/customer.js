layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery, table = layui.table;
    var tableIns = table.render({
        elem: '#customerList', url: ctx+'/customer/list', cellMinWidth: 95,
        page: true, height: "full-125", limits: [10,15,20,25], limit: 10,
        toolbar: "#toolbarDemo", id: "customerListTable",
        cols: [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号', fixed:"true", width:80},
            {field: 'khno', title: '客户编号', align:"center"},
            {field: 'name', title: '客户名称', align:'center'},
            {field: 'area', title: '地区', align:'center'},
            {field: 'cusManager', title: '客户经理', align:'center'},
            {field: 'level', title: '客户等级', align:'center'},
            {field: 'phone', title: '联系电话', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {title: '操作', templet:'#customerListBar', fixed:"right", align:"center", minWidth:150}
        ]]
    });
    $(".search_btn").on("click",function(){
        tableIns.reload({
            page: { curr: 1 },
            where: {
                name: $("input[name='name']").val(),
                khno: $("input[name='khno']").val(),
                area: $("input[name='area']").val()
            }
        });
    });
    table.on('toolbar(customers)',function(obj){
        if(obj.event=="add") openAddOrUpdateDialog();
        else if(obj.event=="del"){
            var datas = table.checkStatus(obj.config.id).data;
            if(datas.length==0){ layer.msg("请选择待删除记录"); return; }
            layer.confirm("确定删除选中的记录?",{btn:['确定','取消']},function(index){
                layer.close(index);
                var ids="ids=";
                for(var i=0;i<datas.length;i++){ ids=ids+datas[i].id+(i<datas.length-1?"&ids=":""); }
                $.post(ctx+"/customer/delete",ids,function(data){ if(data.code==200) tableIns.reload(); else layer.msg(data.msg); });
            });
        }
    });
    table.on('tool(customers)',function(obj){
        if(obj.event=="edit") openAddOrUpdateDialog(obj.data.id);
        else if(obj.event=="del"){
            layer.confirm("确认删除?",function(index){
                $.post(ctx+"/customer/delete",{ids:obj.data.id},function(data){ if(data.code==200){ layer.msg("删除成功"); tableIns.reload(); } else layer.msg(data.msg); });
            });
        }
    });
    function openAddOrUpdateDialog(id){
        var title="客户信息管理-添加", url=ctx+"/customer/addOrUpdateCustomerPage";
        if(id){ title="客户信息管理-更新"; url=url+"?id="+id; }
        layui.layer.open({ title:title, type:2, area:["700px","500px"], maxmin:true, content:url });
    }
});
