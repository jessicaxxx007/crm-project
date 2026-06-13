layui.use(['element','echarts'],function(){
    var $ = layui.jquery, echarts = layui.echarts;
    var chartDom = document.getElementById('main');
    if(!chartDom) return;
    var myChart = echarts.init(chartDom);
    $.get(ctx+'/report/customerLevel',function(data){
        if(data && data.length > 0) {
            myChart.setOption({
                title:{text:'客户贡献分析',left:'center'},
                tooltip:{trigger:'item'},
                legend:{orient:'vertical',left:'left'},
                series:[{name:'客户数',type:'pie',radius:'50%',data:data.map(function(d){return {name:d.name||d.level,value:d.value};}),emphasis:{itemStyle:{shadowBlur:10,shadowOffsetX:0,shadowColor:'rgba(0,0,0,0.5)'}}}]
            });
        }
    });
});
