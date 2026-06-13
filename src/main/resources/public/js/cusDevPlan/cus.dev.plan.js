layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        table = layui.table;

    // 客户开发计划列表
    var tableIns = table.render({
        elem: '#cus_dev_planList',
        url : ctx+'/cus_dev_plan/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,15,20,25],
        limit : 10,
        toolbar: "#toolbarDemo",
        id : "cus_dev_planListTable",
        cols : [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号',fixed:"true"},
            {field: 'chanceSource', title: '机会来源',align:"center"},
            {field: 'customerName', title: '客户名称',  align:'center'},
            {field: 'cgjl', title: '成功几率', align:'center'},
            {field: 'overview', title: '概要', align:'center'},
            {field: 'linkMan', title: '联系人',  align:'center'},
            {field: 'linkPhone', title: '联系电话', align:'center'},
            {field: 'description', title: '描述', align:'center'},
            {field: 'createMan', title: '创建人', align:'center'},
            {field: 'createDate', title: '创建时间', align:'center'},
            {field: 'devResult', title: '开发状态', align:'center',templet:function (d) {
                    return formatterDevResult(d.devResult);
                }},
            {title: '操作',fixed:"right",align:"center", minWidth:150,templet:"#cus_dev_planListBar"}
        ]]
    });

    // 开发状态样式
    function formatterDevResult(value){
        if(value==0){
            return "<div style='color: yellow'>未开发</div>";
        }else if(value==1){
            return "<div style='color: #00FF00;'>开发中</div>";
        }else if(value==2){
            return "<div style='color: #00B83F'>开发成功</div>";
        }else if(value==3){
            return "<div style='color: red'>开发失败</div>";
        }else {
            return "<div style='color: #af0000'>未知</div>"
        }
    }

    // 顶部工具栏：新增 + 批量删除
    table.on('toolbar(cus_dev_plan)',function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        var data = checkStatus.data;

        // 新增
        if(obj.event === 'add'){
            openAddDialog("新增客户开发计划", null, 1);
        }

        // 批量删除
        if(obj.event === 'batchDel'){
            if(data.length === 0){
                layer.msg("请选择数据",{icon:5});
                return;
            }
            var ids = [];
            for(var i=0; i<data.length; i++){
                ids.push(data[i].id);
            }
            layer.confirm("确定删除?",function(){
                $.post(ctx+"/cus_dev_plan/delete",{ids:ids.join(",")},function(res){
                    if(res.code===200){
                        layer.msg("成功");
                        tableIns.reload();
                    }else{
                        layer.msg(res.msg);
                    }
                })
            })
        }
    });

    // 行工具：编辑、删除、详情
    table.on("tool(cus_dev_plan)",function (obj) {
        var layEvent = obj.event;
        var data = obj.data;

        if(layEvent==="edit"){
            openAddDialog("编辑", data.id, null);
        }

        if(layEvent === "del"){
            layer.confirm("确定删除?",function(){
                $.post(ctx+"/cus_dev_plan/delete",{id:data.id},function(res){
                    if(res.code===200){
                        layer.msg("成功");
                        tableIns.reload();
                    }else{
                        layer.msg(res.msg);
                    }
                })
            })
        }

        if(layEvent === "detail"){
            layer.open({
                title:"详情",
                type:2,
                area:["700px","500px"],
                content:ctx+"/cus_dev_plan/toCusDevPlanDataPage?sid="+data.id
            })
        }
    });

    // 统一打开弹窗（完全匹配你后端地址）
    function openAddDialog(title, id, sid) {
        var url = ctx + "/cus_dev_plan/addOrUpdateCusDevPlanPage";
        var params = [];
        if(id) params.push("id="+id);
        if(sid) params.push("sid="+sid);
        if(params.length>0) url += "?" + params.join("&");

        layer.open({
            title:title,
            type:2,
            area:["700px","500px"],
            maxmin:true,
            content:url
        })
    }

    // 搜索
    $(".search_btn").click(function(){
        table.reload("cus_dev_planListTable",{
            where:{
                customerName:$("input[name='customerName']").val(),
                createMan:$("input[name='createMan']").val(),
                devResult:$("#devState").val()
            },page:{curr:1}
        })
    });
});