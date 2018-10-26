var InitiateSimpleDataTable = function () {
    return {
        init: function () {
            //Datatable Initiating
            var oTable = $('#simpledatatable').dataTable({
                "sDom": "t<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
                "iDisplayLength": 10,
                "language": {
                    "search": "",
                    "sLengthMenu": "_MENU_",
                    "oPaginate": {
                        "sPrevious": "上一页",
                        "sNext": "下一页"
                    }
                },
                "aoColumns": [
                  { "bSortable": false },
                  { "bSortable": false },
                  { "bSortable": false },
                  { "bSortable": false },
                  { "bSortable": false },
                  { "bSortable": false },
                  { "bSortable": false },
                  { "bSortable": false },
                  { "bSortable": false }
                ],
                "aaSorting": []
            });

            //Check All Functionality
            jQuery('#simpledatatable .group-checkable').change(function () {
                var set = $('#simpledatatable').find(".checkboxes");
                var checked = jQuery(this).is(":checked");
                jQuery(set).each(function () {
                    if (checked) {
                        $(this).prop("checked", true);
                    } else {
                        $(this).prop("checked", false);
                    }
                });

            });

        }

    };

}();
var InitiateArticleDataTable = function () {
    return {
        init: function () {
            //Datatable Initiating
            var oTable = $('.table-reprinted-most').dataTable({
                "sDom": "t<'row DTTTFooter'<'col-sm-6'i><'col-sm-6'p>>",
                "iDisplayLength": 10,
                "language": {
                    "search": "",
                    "sLengthMenu": "_MENU_",
                    "oPaginate": {
                        "sPrevious": "上一页",
                        "sNext": "下一页"
                    }
                },
                "aoColumns": [
                  { "bSortable": false },
                  { "bSortable": false },
                  { "bSortable": false },
                  null,
                  null,
                  { "bSortable": false },
                ],
                "aaSorting": []
            });

            //Check All Functionality
            jQuery('.table-reprinted-most .group-checkable').change(function () {
                var set = $('.table-reprinted-most').find(".checkboxes");
                var checked = jQuery(this).is(":checked");
                jQuery(set).each(function () {
                    if (checked) {
                        $(this).prop("checked", true);
                    } else {
                        $(this).prop("checked", false);
                    }
                });

            });

        }

    };

}();
function reprintedRankingWeekEcharts(){
	var reprintedRankingWeekEcharts = echarts.init(document.getElementById('reprinted-ranking-week-echarts'));

	var xAxisData = ['新华网','人民网','21经济网','网易新闻','央视新闻','百度新闻','搜狐新闻','中国新闻网','东方头条','参考消息'];
	var data1 = ['12','18','14','20','18','14','30','23','19','16'];

	var option = {
		color: ['rgba(46,195,232,0.8)'],
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        top:'1%',
	        containLabel: true
	    },
	    tooltip: {},
	    xAxis: {
	        type : 'value',
	        silent: false,
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
	    yAxis: {
	    	type : 'category',
			data: xAxisData,
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
	    },
	    series: [{
	        name: '本周转载单位',
	        type: 'bar',
	        label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
	        data: data1,
	        barMaxWidth:'15px',
			barGap : '10px',
	        animationDelay: function (idx) {
	            return idx * 10;
	        }
	    }],
	    animationEasing: 'elasticOut',
	    animationDelayUpdate: function (idx) {
	        return idx * 5;
	    }
	};

	reprintedRankingWeekEcharts.setOption(option);

}
function copyrightRankingEcharts(){
	var copyrightRankingEcharts = echarts.init(document.getElementById('copyright-ranking-echarts'));

	var xAxisData = ['新华网','人民网','21经济网','网易新闻','央视新闻','百度新闻','搜狐新闻','中国新闻网','东方头条','参考消息'];
	var data1 = ['12','18','14','20','18','14','30','23','19','16'];

	var option = {
		color: ['rgba(251,110,82,0.8)'],
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        top:'1%',
	        containLabel: true
	    },
	    tooltip: {},
	    xAxis: {
	        type : 'value',
	        silent: false,
	        splitLine: {
	            show: true
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
	    yAxis: {
	    	type : 'category',
			data: xAxisData,
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
	    },
	    series: [{
	        name: '本周版权存疑单位',
	        type: 'bar',
	        label: {
                normal: {
                    show: true,
                    position: 'inside'
                }
            },
	        data: data1,
	        barMaxWidth:'15px',
			barGap : '10px',
	        animationDelay: function (idx) {
	            return idx * 20;
	        }
	    }],
	    animationEasing: 'elasticOut',
	    animationDelayUpdate: function (idx) {
	        return idx * 10;
	    }
	};

	copyrightRankingEcharts.setOption(option);

}
function reprintedCopyrightCostLeft(){
	var reprintedCopyrightCostLeft = echarts.init(document.getElementById('reprinted-copyright-cost-left'));

	var option = {
		color:['#ffce55','rgba(153,153,153,0.2)'],
	    /*tooltip: {
	        trigger: 'item',
	        formatter: "{a}：{b}"
	    },*/
	    series: [
	        {
	            name:'本周转载数',
	            type:'pie',
	            radius: ['65%', '80%'],
	            avoidLabelOverlap: false,
	            label: {
                    normal: {
                        position: 'center',
                        show: true,
                        textStyle: {
                            fontSize: '16',
                            fontWeight: 'bold',
                            color: '#000'
                        }
                    }
                },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:[
	                {value:100, name:'30%'},
	                {value:300, name:''},
	            ]
	        }
	    ]
	};


	reprintedCopyrightCostLeft.setOption(option);
}
function reprintedCopyrightCostRight(){
	var reprintedCopyrightCostRight = echarts.init(document.getElementById('reprinted-copyright-cost-right'));

	var option = {
		color:['#ffce55','rgba(153,153,153,0.2)'],
	    tooltip: {
	        trigger: 'item',
	        formatter: "{a}：{b}"
	    },
	    series: [
	        {
	            name:'占比',
	            type:'pie',
	            radius: ['65%', '80%'],
	            avoidLabelOverlap: false,
	            label: {
                    normal: {
                        position: 'center',
                        show: true,
                        textStyle: {
                            fontSize: '16',
                            fontWeight: 'bold',
                            color: '#000'
                        }
                    }
                },
	            labelLine: {
	                normal: {
	                    show: false
	                }
	            },
	            data:[
	                {value:100, name:'30%'},
	                {value:300, name:''},
	            ]
	        }
	    ]
	};


	reprintedCopyrightCostRight.setOption(option);
}
$(function(){
	var options = {
		'locale' : {
			format:'YYYY-MM-DD',
			applyLabel: '确定',
            cancelLabel: '取消',
            weekLabel: 'W',
            customRangeLabel: '自定义',
            daysOfWeek:[ '日', '一', '二', '三', '四', '五', '六' ],
            monthNames:[ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月' ],  
		},
		'opens':'left',
        "ranges": {
         	'今天': [moment(), moment()],
            '最近7天': [moment().subtract(6, 'days'), moment()],
            '最近30天': [moment().subtract(29, 'days'), moment()],
            '本月': [moment().startOf('month'), moment().endOf('month')],
            '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        "alwaysShowCalendars": true,
    };
	$('#time-slice').daterangepicker(options, function(start, end, label) {});

	InitiateSimpleDataTable.init();
	InitiateArticleDataTable.init();
	reprintedRankingWeekEcharts();
	copyrightRankingEcharts();
	reprintedCopyrightCostLeft();
	/*reprintedCopyrightCostRight();*/
});