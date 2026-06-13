layui.use(['table','layer','form'],function(){
    var inIframe = parent.layer !== undefined;
    var layer = inIframe ? top.layer : layui.layer,
        $ = layui.jquery,
        table = layui.table,
        form = layui.form;
    var stateVal = $("input[name='state']").val();
    var tableIns = table.render({
        elem: '#serveList', url: ctx+'/customer_serve/list?state='+stateVal, cellMinWidth: 95,
        page: true, height: "full-125", limits: [10,15,20,25], limit: 10,
        toolbar: "#toolbarDemo", id: "serveListTable",
        cols: [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号', fixed:"true", width:80},
            {field: 'serveType', title: '服务类型', align:"center"},
            {field: 'customer', title: '客户', align:'center'},
            {field: 'overview', title: '概要', align:'center'},
            {field: 'createPeople', title: '创建人', align:'center'},
            {field: 'assigner', title: '分配人', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {title: '操作', templet:'#serveListBar', fixed:"right", align:"center", minWidth:120}
        ]]
    });

    // ========== 暴露给分配弹窗iframe调用的方法(挂载到top窗口) ==========
    top.doAssign = function(id, assigner){
        $.post(ctx+"/customer_serve/update",{id:id, assigner:assigner, state:"fw_002"},function(data){
            if(data.code==200){ layer.msg("分配成功"); tableIns.reload(); } else layer.msg(data.msg);
        });
    };

    // 搜索
    $(".search_btn").on("click",function(){
        tableIns.reload({
            page: { curr: 1 },
            where: {
                customer: $("input[name='customer']").val(),
                serveType: $("input[name='serveType']").val()
            }
        });
    });

    // 工具栏事件(创建服务)
    table.on('toolbar(serves)',function(obj){
        if(obj.event=="add") openAddOrUpdateDialog();
    });

    // 行操作事件
    table.on('tool(serves)',function(obj){
        if(obj.event=="edit") {
            openAddOrUpdateDialog(obj.data.id);
        } else if(obj.event=="del"){
            layer.confirm("确认删除?",function(index){
                $.post(ctx+"/customer_serve/delete",{id:obj.data.id},function(data){
                    if(data.code==200){ layer.msg("删除成功"); tableIns.reload(); } else layer.msg(data.msg);
                });
            });
        } else if(obj.event=="assign"){
            openAssignDialog(obj.data.id);
        } else if(obj.event=="process"){
            openProcessDialog(obj.data.id);
        } else if(obj.event=="feedback"){
            openFeedbackDialog(obj.data.id);
        } else if(obj.event=="detail"){
            openAddOrUpdateDialog(obj.data.id);
        }
    });

    // ============ 分配对话框(type:2 iframe,无跨窗口问题) ============
    function openAssignDialog(id){
        top.layer.open({
            title: '服务分配',
            type: 2,
            area: ['450px','220px'],
            content: ctx+'/customer_serve/assignPage?id='+id
        });
    }

    // ============ 处理对话框 ============
    function openProcessDialog(id){
        layer.prompt({title:'输入处理内容',formType:2,area:['400px','250px']},function(text,index){
            layer.close(index);
            if(!text || text.trim()===''){ layer.msg('处理内容不能为空'); return; }
            $.post(ctx+"/customer_serve/update",{id:id, serviceProce:text, state:"fw_003"},function(data){
                if(data.code==200){ layer.msg("处理成功"); tableIns.reload(); } else layer.msg(data.msg);
            });
        });
    }

    // ============ 反馈对话框 ============
    function openFeedbackDialog(id){
        layer.prompt({title:'输入反馈内容(提交后自动归档)',formType:2,area:['400px','250px']},function(text,index){
            layer.close(index);
            if(!text || text.trim()===''){ layer.msg('反馈内容不能为空'); return; }
            $.post(ctx+"/customer_serve/update",{id:id, serviceProceResult:text, state:"fw_004", myd:"5"},function(data){
                if(data.code==200){ layer.msg("反馈成功,已归档"); tableIns.reload(); } else layer.msg(data.msg);
            });
        });
    }

    // ============ 添加/编辑/详情对话框 ============
    function openAddOrUpdateDialog(id){
        var title="服务管理-创建", url=ctx+"/customer_serve/addOrUpdateServePage?stateNum="+stateNum;
        if(id){ title="服务管理-更新"; url=url+"&id="+id; }
        layui.layer.open({ title:title, type:2, area:["700px","500px"], maxmin:true, content:url });
    }
});
