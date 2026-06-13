layui.use(['element','echarts'],function(){
    var $ = layui.jquery, echarts = layui.echarts;
    var dom = document.getElementById('main');
    if(!dom) return;
    var myChart = echarts.init(dom);
    $.get(ctx+'/report/customerServe',function(data){
        if(data && data.length > 0) {
            var names = [], values = [];
            for(var i = 0; i < data.length; i++) {
                names.push(data[i].name);
                values.push(data[i].value);
            }
            myChart.setOption({
                title:{text:'客户服务分析',left:'center'},
                tooltip:{trigger:'axis'},
                legend:{data:names},
                xAxis:{type:'category',data:names},
                yAxis:{type:'value'},
                series:[{name:'服务数',type:'bar',data:values}]
            });
        } else {
            myChart.setOption({
                title:{text:'客户服务分析',left:'center'},
                tooltip:{trigger:'axis'},
                legend:{data:['服务创建','服务分配','服务处理','服务反馈','服务归档']},
                xAxis:{type:'category',data:['创建','分配','处理','反馈','归档']},
                yAxis:{type:'value'},
                series:[{name:'服务数',type:'bar',data:[0,0,0,0,0]}]
            });
        }
    }).fail(function(){
        myChart.setOption({
            title:{text:'客户服务分析',left:'center'},
            tooltip:{trigger:'axis'},
            legend:{data:['服务创建','服务分配','服务处理','服务反馈','服务归档']},
            xAxis:{type:'category',data:['创建','分配','处理','反馈','归档']},
            yAxis:{type:'value'},
            series:[{name:'服务数',type:'bar',data:[0,0,0,0,0]}]
        });
    });
});
