function timeInfluenceEcharts(){
	var timeInfluenceChart = echarts.init(document.getElementById('time-influence-echarts'));
	var data_val=[2220, 1682, 2791, 3000, 4090, 3230, 2910],
	    xAxis_val=['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];
	var data_val1=[0,0,0,0,0,0,0];
	var option = {
		color: ['rgba(46,195,232,0.8)','rgba(251,110,82,0.8)'],
	    title: {
	        text: '日均评论量/点击量',
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    legend: {
	        data:['日均评论量','日均点击量']
	    },
	    xAxis:  {
	        type: 'category',
	        boundaryGap: false,
	        data : ['周一','周二','周三','周四','周五','周六','周日'],
	        splitLine: {
		        show: false,
	        },
	        axisTick:{
	        	show:true,
	        },
	        axisLine:{
	        	lineStyle:{
                    color: function (value, index) {
                        return '#666';
                    }
                }
	        },
	    },
	    yAxis: {
	        type: 'value',
	        axisLabel: {
	            formatter: '{value}'
	        },
	        splitLine: {
		            show: true,
	        },
	        axisTick:{
	        	show:false,
	        },
	        axisLine:{
	        	lineStyle:{
	                   color: function (value, index) {
	                       return '#666';
	                   }
	               }
	        },
	    },
	    series: [
	        {
	            name:'日均评论量',
	            type:'line',
	            data:[130, 122, 91, 34, 90, 200, 110],
	            markPoint: {
	                data: [
	                    {type: 'max', name: '最大值'},
	                    {type: 'min', name: '最小值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name: '平均值'},
	                    [{
	                        symbol: 'none',
	                        x: '90%',
	                        yAxis: 'max'
	                    }, {
	                        symbol: 'circle',
	                        label: {
	                            normal: {
	                                position: 'start',
	                                formatter: '最大值'
	                            }
	                        },
	                        type: 'max',
	                        name: '最高点'
	                    }]
	                ]
	            }
	        },
	        {
	            name:'日均点击量',
	            type:'line',
	            data:[120, 132, 101, 134, 90, 230, 210],
	            markPoint: {
	                data: [
	                    {type: 'max', name: '最大值'},
	                    {type: 'min', name: '最小值'}
	                ]
	            },
	            markLine: {
	                data: [
	                    {type: 'average', name: '平均值'},
	                    [{
	                        symbol: 'none',
	                        x: '90%',
	                        yAxis: 'max'
	                    }, {
	                        symbol: 'circle',
	                        label: {
	                            normal: {
	                                position: 'start',
	                                formatter: '最大值'
	                            }
	                        },
	                        type: 'max',
	                        name: '最高点'
	                    }]
	                ]
	            }
	        }
	    ]

	}
	
	timeInfluenceChart.setOption(option);
}


function chainTransmissionEcharts(){
	// 路径配置
    require.config({
        paths: {
            echarts: 'http://echarts.baidu.com/build/dist'
        }
    });
    
    // 使用
    require(
        [
            'echarts',
            'echarts/chart/tree' // 使用柱状图就加载bar模块，按需加载
        ],
        function (ec) {
            // 基于准备好的dom，初始化echarts图表
            var treeChart = ec.init(document.getElementById('chain-transmission-echarts')); 
            
            var option = {
                series : [
                    {
                        name:'树图',
                        type:'tree',
                        orient: 'horizontal',  // vertical horizontal
                        rootLocation: {x: 90,y: 'center'}, // 根节点位置  {x: 100, y: 'center'}
                        layerPadding: 200,
                        hoverable: false,
                        roam: true,
                        symbolSize: 10,
                        itemStyle: {
                            normal: {
                                color: 'rgba(251,110,82,0.8)',
                                label: {
                                    show: true,
                                    position: 'right',
                                    formatter: "{b}",
                                    textStyle: {
                                        color: 'rgba(0,0,0,0.7)',
                                        fontSize: 5,
                                        fontFamily:'Microsoft YaHei,Open Sans,Segoe UI'
                                    }
                                },
                                lineStyle: {
                                    color: '#ccc',
                                    type: 'curve' //'curve'|'broken'|'solid'|'dotted'|'dashed'  
                                },                           
                            },
                            emphasis: {
                                color: '#333',
                                label: {
                                    show: false
                                },
                                borderWidth: 0
                            }
                        },
                        
                        data: [
                            {"name":"苏州电视台",
                                "children":[
                                    {"name":"白楠广播电视台"},
                                    {"name":"华龙网",
                                      'children':[
                                            {'name':'中国经济网',
                                                'children':[{
                                                    'name':'东北网'
                                                }]
                                            },
                                            {'name':'新浪网'},
                                            {'name':'中国新闻网',
                                                'children':[{
                                                    'name':'中国青年网'
                                                },{
                                                    'name':'大众网',
                                                },{
                                                    'name':'中国网江苏',
                                                }]
                                            },
                                            {'name':'贵阳网'},
                                            {'name':'中国青年网'},
                                            {'name':'中国新闻网辽宁'},
                                        ]              
                                    },
                                ]
                        }]                    }
                ]
            };
    
            // 为echarts对象加载数据 
            treeChart.setOption(option); 
        }
    );
}

$(function(){
	/*页面初始化*/
	chainTransmissionEcharts();
	timeInfluenceEcharts();
});