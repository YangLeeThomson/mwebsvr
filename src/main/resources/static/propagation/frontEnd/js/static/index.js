function transmissibilityIndexEcharts(){
	var transmissibilityIndexEcharts = echarts.init(document.getElementById('transmissibility-index-echarts'));

	var options = {
		tooltip : {
	        formatter: "{a} <br/>{b} : {c}%"
	    },
	    series: [
	        {
	            name: '业务指标',
	            type: 'gauge',
	            radius: '100%',
	            pointer:{
	                length:'50%',
	                width:'3'
	            },
	            axisLabel:{
	                distance:1
	            },
	            axisTick:{
	                length:4,
	            },
	            axisLine: {
			           lineStyle: {
		                color: [
		                    [0, '#ffffff'],
		                    [1, 'rgba(46,195,232,0.8)']
		                ],
		                width: 5
		            }
		        },
		        splitLine: {
		            length: 5,
		            lineStyle: {
		                color: '#ffffff',
		                width: 2
		            }
		        },
	            detail : {
                    offsetCenter: [0, '70%'],       // x, y，单位px
                    formatter:'7205',
                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                        fontWeight: 'bolder',
                        color: 'rgba(0,0,0,0.7)',
                        fontSize:18
                    }
                },
	            data: [{value: 70}]
	        }
	    ]
	}

	transmissibilityIndexEcharts.setOption(options);
};


function issuedAmountCompare(){
	var issuedAmountCompare = echarts.init(document.getElementById('issued-amount-compare'));

	var option = {
		color:['rgba(251,110,82,0.8)','rgba(46,195,232,0.8)'],
	    series : [
	        {
	            name: '访问来源',
	            type: 'pie',
	            radius : '85%',
	            label: {
	                normal: {
	                    position: 'outside',
	                    formatter:'{b}({d}%)\n{c}次',
	                    textStyle:{
	                    	fontSize:14,
	                    }
	                }
	            },
	            center: ['50%', '50%'],
	            data:[
	                {value:1250, name:'原始'},
	                {value:3105, name:'转载'},
	            ],
	        }
	    ]
	};


	issuedAmountCompare.setOption(option);

}



var clientContrast = function(){
	return {
		pcecharts : function(){

			var clientContrastPC = echarts.init(document.getElementById('client-contrast-pc'));

			var options = {
				tooltip : {
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    series: [
			        {
			            name: '业务指标',
			            type: 'gauge',
			            radius: '100%',
			            pointer:{
			                length:'50%',
			                width:'3'
			            },

			            axisLine: {
				            lineStyle: {
				                color: [
				                    [0, '#ffffff'],
				                    [1, 'rgba(46,195,232,0.8)']
				                ],
				                width: 5
				            }
				        },
				        splitLine: {
				            length: 5,
				            lineStyle: {
				                color: '#ffffff',
				                width: 2
				            }
				        },

			            axisLabel:{
			                distance:1
			            },
			            axisTick:{
			                length:4,
			            },
			            detail : {
		                    offsetCenter: [0, '70%'],       // x, y，单位px
		                    formatter:'3478',
		                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                        fontWeight: 'bolder',
		                        color: 'rgba(0,0,0,0.7)',
		                        fontSize:18
		                    }
		                },
			            data: [{value: 30}]
			        }
			    ]
			}

			clientContrastPC.setOption(options);
		},

		weixinecharts : function(){
			var clientContrastWeixin = echarts.init(document.getElementById('client-contrast-weixin'));

			var options = {
				tooltip : {
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    series: [
			        {
			            name: '业务指标',
			            type: 'gauge',
			            radius: '100%',
			            pointer:{
			                length:'50%',
			                width:'3'
			            },
			            axisLabel:{
			                distance:1
			            },
			            axisTick:{
			                length:4,
			            },
			            axisLine: {
				            lineStyle: {
				                color: [
				                    [0, '#ffffff'],
				                    [1, 'rgba(46,195,232,0.8)']
				                ],
				                width: 5
				            }
				        },
				        splitLine: {
				            length: 5,
				            lineStyle: {
				                color: '#ffffff',
				                width: 2
				            }
				        },
			            detail : {
		                    offsetCenter: [0, '70%'], 
		                    formatter:'2489',      // x, y，单位px
		                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                        fontWeight: 'bolder',
		                        color: 'rgba(0,0,0,0.7)',
		                        fontSize:18
		                    }
		                },
			            data: [{value: 67}]
			        }
			    ]
			}

			clientContrastWeixin.setOption(options);
		},

		weiboecharts : function(){
			var clientContrastWeibo = echarts.init(document.getElementById('client-contrast-weibo'));

			var options = {
				tooltip : {
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    series: [
			        {
			            name: '业务指标',
			            type: 'gauge',
			            radius: '100%',
			            pointer:{
			                length:'50%',
			                width:'3'
			            },
			            axisLabel:{
			                distance:1
			            },
			            axisTick:{
			                length:4,
			            },
			            axisLine: {
				            lineStyle: {
				                color: [
				                    [0, '#ffffff'],
				                    [1, 'rgba(46,195,232,0.8)']
				                ],
				                width: 5
				            }
				        },
				        splitLine: {
				            length: 5,
				            lineStyle: {
				                color: '#ffffff',
				                width: 2
				            }
				        },
			            detail : {
		                    offsetCenter: [0, '70%'],       // x, y，单位px
		                    formatter:'789',
		                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                        fontWeight: 'bolder',
		                        color: 'rgba(0,0,0,0.7)',
		                        fontSize:18
		                    }
		                },
			            data: [{value: 30}]
			        }
			    ]
			}

			clientContrastWeibo.setOption(options);
		},
		
		appcharts : function(){
			var clientContrastApp = echarts.init(document.getElementById('client-contrast-app'));

			var options = {
				tooltip : {
			        formatter: "{a} <br/>{b} : {c}%"
			    },
			    series: [
			        {
			            name: '业务指标',
			            type: 'gauge',
			            radius: '100%',
			            pointer:{
			                length:'50%',
			                width:'3'
			            },
			            axisLabel:{
			                distance:1
			            },
			            axisTick:{
			                length:4,
			            },
			            axisLine: {
				            lineStyle: {
				                color: [
				                    [0, '#ffffff'],
				                    [1, 'rgba(46,195,232,0.8)']
				                ],
				                width: 5
				            }
				        },
				        splitLine: {
				            length: 5,
				            lineStyle: {
				                color: '#ffffff',
				                width: 2
				            }
				        },
			            detail : {
		                    offsetCenter: [0, '70%'],       // x, y，单位px
		                    formatter:'989',
		                    textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
		                        fontWeight: 'bolder',
		                        color: 'rgba(0,0,0,0.7)',
		                        fontSize:18
		                    }
		                },
			            data: [{value: 40}]
			        }
			    ]
			}

			clientContrastApp.setOption(options);
		},

	}
}();


var table1;
function microLetters(){
	table1=$('.micro-letters-table').DataTable({
		oLanguage: { // 对表格国际化
	    	"sInfo" : "第 _START_ - _END_ 条　共 _TOTAL_ 条",
	    	"sInfoEmpty" : "第0条　共0条",
	    },
		columns: [//显示的列
                  {"bSortable": false},
                  {"bSortable": false},
                  {"bSortable": true},
                  {"bSortable": true},
              ],
              "aaSorting": [[0, "desc"]],
	});
}

$(function(){
	transmissibilityIndexEcharts();
	issuedAmountCompare();
	clientContrast.pcecharts();
	clientContrast.weixinecharts();
	clientContrast.weiboecharts();
	clientContrast.appcharts();

	InitiateEasyPieChart.init();
	
	microLetters();
});