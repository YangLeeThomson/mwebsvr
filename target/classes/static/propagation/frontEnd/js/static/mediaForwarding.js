function forwardingWayEcharts(){
	var forwardingWayEcharts = echarts.init(document.getElementById('forwarding-way-echarts'));

	var option = {
		color:['rgba(46,195,232,0.8)','rgba(251,110,82,0.8)','rgba(244,180,0,0.8)'],
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name: '转发媒介',
	            type: 'pie',
	            radius : '55%',
	            center: ['50%', '50%'],
	            data:[
	                {value:335, name:'微信'},
	                {value:310, name:'微博'},
	                {value:234, name:'PC'},
	            ],
	            label:{
	            	normal:{
	            		formatter:'{b}\n{d}%',
	            		textStyle:{
	            			fontSize:14
	            		}
	            	}
	            },
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};


	forwardingWayEcharts.setOption(option);
}

function forwardingRankingEcharts(){
	var forwardingRankingEcharts = echarts.init(document.getElementById('forwarding-ranking-echarts'));

	var option = {
		color: ['rgba(251,110,82,0.8)'],
		grid: {
	        left: '3%',
	        right: '5%',
	        bottom: '3%',
	        top:'8%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : ['新浪财经','东方财富网','营口日报','中金在线','多赢财富网','河南金融网','同花顺','同花顺金融服务网','红商网','中金股票'],
	            splitLine: {
		            show: false
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
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
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
	        }
	    ],
	    series : [
	        {
	            name:'蒸发量',
	            type:'bar',
	            data:[103,39,35,19,17,13,12,11,10,5],
	            barMaxWidth:'20px',
	            markPoint : {
	                data : [
	                    {type : 'max', name: '最大值'},
	                    {type : 'min', name: '最小值'}
	                ]
	            },
	            markLine : {
	                data : [
	                    {type : 'average', name: '平均值'}
	                ]
	            }
	        },
	    ]
	}

	forwardingRankingEcharts.setOption(option);
}

$(function(){
	forwardingWayEcharts();
	forwardingRankingEcharts();
});