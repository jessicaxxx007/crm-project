layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery, table = layui.table;
    var tableIns = table.render({
        elem: '#dicList', url: ctx+'/data_dic/list', cellMinWidth: 95,
        page: true, height: "full-125", limits: [10,15,20,25], limit: 10,
        toolbar: "#toolbarDemo", id: "dicListTable",
        cols: [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号', width:80},
            {field: 'dataDicName', title: '字典名称', align:"center"},
            {field: 'dataDicValue', title: '字典值', align:"center"},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'updateDate', title: '更新时间', align:'center'},
            {title: '操作', templet:'#dicListBar', fixed:"right", align:"center", minWidth:150}
        ]]
    });
    $(".search_btn").on("click",function(){
        tableIns.reload({
            page: { curr: 1 },
            where: {
                dataDicName: $("input[name='dataDicName']").val()
            }
        });
    });
    table.on('toolbar(dics)',function(obj){
        if(obj.event=="add") openAddOrUpdateDialog();
        else if(obj.event=="del"){
            var datas = table.checkStatus(obj.config.id).data;
            if(datas.length==0){ layer.msg("请选择待删除记录"); return; }
            layer.confirm("确定删除?",function(index){
                layer.close(index);
                var ids="ids=";
                for(var i=0;i<datas.length;i++){ ids=ids+datas[i].id+(i<datas.length-1?"&ids=":""); }
                $.post(ctx+"/data_dic/delete",ids,function(data){ if(data.code==200) tableIns.reload(); else layer.msg(data.msg); });
            });
        }
    });
    table.on('tool(dics)',function(obj){
        if(obj.event=="edit") openAddOrUpdateDialog(obj.data.id);
        else if(obj.event=="del"){
            layer.confirm("确认删除?",function(index){
                $.post(ctx+"/data_dic/delete",{id:obj.data.id},function(data){ if(data.code==200){ layer.msg("删除成功"); tableIns.reload(); } else layer.msg(data.msg); });
            });
        }
    });
    function openAddOrUpdateDialog(id){
        var title="字典管理-添加", url=ctx+"/data_dic/addOrUpdateDicPage";
        if(id){ title="字典管理-更新"; url=url+"?id="+id; }
        layui.layer.open({ title:title, type:2, area:["700px","500px"], maxmin:true, content:url });
    }
});
