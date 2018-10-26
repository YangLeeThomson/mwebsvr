function channelProportionEcharts(idName,weiboData,weixinData,pcData){
	var channelProportionEcharts = echarts.init(document.getElementById(idName));

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
	            radius : '80%',
	            center: ['50%', '50%'],
	            data:[
	                {value:weiboData, name:'微信'},
	                {value:weixinData, name:'微博'},
	                {value:pcData, name:'PC'},
	            ],
	            label:{
	            	normal:{
	            		position:'inside',
	            		formatter:'{b}\n{d}%',
	            		textStyle:{
	            			fontSize:12
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

	channelProportionEcharts.setOption(option);
}

function networkproportionEcharts(idName,countAll,data){
	/*data:苏州台的数量  countAll：全网数量*/
	var networkproportionEcharts = echarts.init(document.getElementById(idName));

	var option = {
		color:['rgba(244,180,0,0.8)','rgba(46,195,232,0.8)'],
	    tooltip : {
	    	show:false,
	    },
	    minAngle:90,
	    series : [
	        {
			        type: 'pie',
			        center: ['50%', '50%'],
			       	radius : '80%',
			        label: {
			            normal: {
			                position: 'center'
			            }
			        },
			        data: [{
			            value: data,
			            name: '江苏电视台',
			            label: {
			                normal: {
			                    formatter: '{d} %',
			                    textStyle: {
			                        color: '#fff',
			                        fontSize: 16
			                    }
			                }
			            }
			        }, {
			            value: countAll,
			            name: '',
			            tooltip: {
			                show: false
			            },
			            label: {
			                normal: {
			                	formatter: '\n苏州电视台',
			                    textStyle: {
			                        color: '#fff',
			                    },
			                }
			            }
			        }]
	    	}
	    ]
	};

	networkproportionEcharts.setOption(option);
}
function originalReprintedEcharts(idName,original,transshipment){
	var originalReprintedEcharts = echarts.init(document.getElementById(idName));

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
	            radius : '80%',
	            center: ['50%', '50%'],
	            data:[
	                {value:original, name:'原创'},
	                {value:transshipment, name:'转载'},
	            ],
	            label:{
	            	normal:{
	            		position:'inside',
	            		formatter:'{b}\n{d}%',
	            		textStyle:{
	            			fontSize:12
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

	originalReprintedEcharts.setOption(option);
}
$(function(){
	channelProportionEcharts('channel-proportion-echarts',2390,789,1230);
	networkproportionEcharts('network-proportion-echarts',3508,245);
	originalReprintedEcharts('original-reprinted-echarts',234,190);

	channelProportionEcharts('channel-proportion-echarts1',234,345,180);
	networkproportionEcharts('network-proportion-echarts1',769,100);
	originalReprintedEcharts('original-reprinted-echarts1',345,120);

	channelProportionEcharts('channel-proportion-echarts2',1902,527,239);
	networkproportionEcharts('network-proportion-echarts2',2362,300);
	originalReprintedEcharts('original-reprinted-echarts2',890,234);

	channelProportionEcharts('channel-proportion-echarts3',687,234,890);
	networkproportionEcharts('network-proportion-echarts3',719,136);
	originalReprintedEcharts('original-reprinted-echarts3',120,34);

	channelProportionEcharts('channel-proportion-echarts4',568,125,960);
	networkproportionEcharts('network-proportion-echarts4',1269,125);
	originalReprintedEcharts('original-reprinted-echarts4',340,908);

});