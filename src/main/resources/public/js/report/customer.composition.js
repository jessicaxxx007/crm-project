layui.use(['element','echarts'],function(){
    var $ = layui.jquery, echarts = layui.echarts;
    var levelDom = document.getElementById('levelChart');
    var areaDom = document.getElementById('areaChart');
    if(!levelDom || !areaDom) return;
    var levelChart = echarts.init(levelDom);
    var areaChart = echarts.init(areaDom);
    $.get(ctx+'/report/customerLevel',function(data){
        if(data && data.length > 0) {
            levelChart.setOption({
                title:{text:'客户等级分布',left:'center'},tooltip:{},xAxis:{type:'category',data:data.map(function(d){return d.name||d.level})},yAxis:{type:'value'},
                series:[{data:data.map(function(d){return d.value}),type:'bar'}]
            });
        }
    });
    $.get(ctx+'/report/customerArea',function(data){
        if(data && data.length > 0) {
            areaChart.setOption({
                title:{text:'客户地区分布',left:'center'},tooltip:{trigger:'item'},
                series:[{type:'pie',radius:'50%',data:data.map(function(d){return {name:d.name||d.area,value:d.value};})}]
            });
        }
    });
});
