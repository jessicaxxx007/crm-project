layui.use(['element','echarts'],function(){
    var $ = layui.jquery, echarts = layui.echarts;
    var dom = document.getElementById('main');
    if(!dom) return;
    var myChart = echarts.init(dom);
    $.get(ctx+'/report/customerLoss',function(data){
        if(data && data.length > 0) {
            myChart.setOption({
                title:{text:'客户流失分析',left:'center'},
                tooltip:{trigger:'item'},
                legend:{orient:'vertical',left:'left'},
                series:[{
                    name:'流失分析',
                    type:'pie',
                    radius:'50%',
                    data:data.map(function(d){ return {name:d.name,value:d.value}; }),
                    emphasis:{itemStyle:{shadowBlur:10,shadowOffsetX:0,shadowColor:'rgba(0,0,0,0.5)'}}
                }]
            });
        } else {
            myChart.setOption({
                title:{text:'客户流失分析',left:'center'},
                tooltip:{trigger:'item'},
                series:[{name:'流失分析',type:'pie',radius:'50%',data:[{name:'暂缓流失',value:0},{name:'已确认流失',value:0}]}]
            });
        }
    }).fail(function(){
        myChart.setOption({
            title:{text:'客户流失分析',left:'center'},
            tooltip:{trigger:'item'},
            series:[{name:'流失分析',type:'pie',radius:'50%',data:[{name:'暂缓流失',value:0},{name:'已确认流失',value:0}]}]
        });
    });
});
