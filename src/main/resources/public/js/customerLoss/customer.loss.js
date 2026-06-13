layui.use(['table','layer'],function(){
    var layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery, table = layui.table;
    var tableIns = table.render({
        elem: '#customerLossList', url: ctx+'/customer_loss/list', cellMinWidth: 95,
        page: true, height: "full-125", limits: [10,15,20,25], limit: 10,
        toolbar: "#toolbarDemo", id: "customerLossListTable",
        cols: [[
            {type: "checkbox", fixed:"center"},
            {field: "id", title:'编号', fixed:"true", width:80},
            {field: 'cusNo', title: '客户编号', align:"center"},
            {field: 'cusName', title: '客户名称', align:'center'},
            {field: 'cusManager', title: '客户经理', align:'center'},
            {field: 'lastOrderTime', title: '最后下单时间', align:'center'},
            {field: 'state', title: '状态', align:'center', templet:function(d){ return d.state==0?'<span style="color:yellow">暂缓</span>':'<span style="color:red">已流失</span>'; }},
            {field: 'lossReason', title: '流失原因', align:'center'},
            {title: '操作', templet:'#customerLossListBar', fixed:"right", align:"center", minWidth:120}
        ]]
    });
    $(".search_btn").on("click",function(){ tableIns.reload(); });
    table.on('tool(customerLosses)',function(obj){
        if(obj.event=="confirmLoss"){
            layer.prompt({title:'请输入流失原因',formType:2},function(text,index){
                layer.close(index);
                $.post(ctx+"/customer_loss/confirmLoss",{id:obj.data.id,lossReason:text},function(data){
                    if(data.code==200){ layer.msg("确认成功"); tableIns.reload(); } else layer.msg(data.msg);
                });
            });
        }
    });
});
