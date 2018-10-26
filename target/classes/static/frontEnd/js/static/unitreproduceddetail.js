function chooseTimeSlice(){
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
}

function rankingStatisticEcharts(){
	var rankingStatisticEcharts = echarts.init(document.getElementById('ranking-statistic-echarts'));

	var xAxisData = ['新华网','人民网','21经济网','网易新闻','央视新闻','百度新闻','搜狐新闻','中国新闻网','东方头条','参考消息'];
	var data1 = ['11','14','13','20','24','28','29','34','38','40'];

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

	rankingStatisticEcharts.setOption(option);
}

function reprintedStatisticalProportion(){
	var reprintedStatisticalProportion = echarts.init(document.getElementById('reprinted-statistical-proportion'));
	var option = {
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    series : [
	        {
	            name: '转载统计',
	            type: 'pie',
	            radius : '60%',
	            center: ['50%', '50%'],
	            data:[
	                {value:40, name:'参考消息'},
	                {value:38, name:'东方头条'},
	                {value:34, name:'中国新闻网'},
	                {value:29, name:'搜狐新闻'},
	                {value:28, name:'百度新闻'},
	                {value:24, name:'央视新闻'},
	                {value:20, name:'网易新闻'},
	                {value:14, name:'21经济网'},
	                {value:13, name:'人民网'},
	                {value:10, name:'新华网'},
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 10,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	}

	reprintedStatisticalProportion.setOption(option);
}

function reprintedPlateRankingEcharts(){
	var reprintedPlateRankingEcharts = echarts.init(document.getElementById('reprinted-plate-ranking-echarts'));
	var xAxisData = ['金融证券','中经电视','产业市场','时政社会','评论理论','文化','人事','商用车','东方头条','脱贫攻坚'];
	var data1 = ['11','14','13','20','24','28','29','34','38','40'];
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

	reprintedPlateRankingEcharts.setOption(option);
}

function reprintedSituationTable(){
	var oTable = $('#reprinted-situation-table').dataTable({
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
    });    //Check All Functionality
    jQuery('#reprinted-situation-table .group-checkable').change(function () {
        var set = $('#reprinted-situation-table').find(".checkboxes");
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

function reprintedOtherSituationTable(){
	var oTable = $('#reprinted-othersituation-table').dataTable({
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
        ],
        "aaSorting": []
    });    //Check All Functionality
    jQuery('#reprinted-othersituation-table .group-checkable').change(function () {
        var set = $('#reprinted-othersituation-table').find(".checkboxes");
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
$(function(){
	chooseTimeSlice();
	rankingStatisticEcharts();
	reprintedStatisticalProportion();
	reprintedPlateRankingEcharts();
	reprintedSituationTable();
	reprintedOtherSituationTable();
});