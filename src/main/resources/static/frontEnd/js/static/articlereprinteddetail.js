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
function suspectedInfringementEcharts(){
    var suspectedInfringementEcharts = echarts.init(document.getElementById('suspected-infringement-echarts'));
 
    var option = {
        color:['#ffce55','rgba(153,153,153,0.2)'],
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

    suspectedInfringementEcharts.setOption(option);
}

function terminalProportionEcharts(){
    var terminalProportionEcharts = echarts.init(document.getElementById('terminal-proportion-echarts'));
 
    var option = {
        tooltip : {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c}"
        },
        series : [
            {
                name: '转载统计',
                type: 'pie',
                radius : '60%',
                center: ['50%', '50%'],
                data:[
                    {value:40, name:'微博\n17.62%'},
                    {value:38, name:'微信\n16.74%'},
                    {value:34, name:'中国新闻网\n14.98%'},
                    {value:29, name:'搜狐新闻\n12.78%'},
                    {value:28, name:'百度新闻\n12.33%'},
                    {value:24, name:'央视新闻\n10.57%'},
                    {value:20, name:'网易新闻\n8.81%'},
                    {value:14, name:'21经济网\n6.17%'},
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

    terminalProportionEcharts.setOption(option);
}

function suspectedRankingEcharts(){
    var suspectedRankingEcharts = echarts.init(document.getElementById('suspected-ranking-echarts'));
    var xAxisData = ['新华网','人民网','21经济网','网易新闻','央视新闻','百度新闻','搜狐新闻','中国新闻网','东方头条','参考消息'];
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

    suspectedRankingEcharts.setOption(option);
}

function reprintedTable(){
    var oTable = $('#reprinted-table').dataTable({
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
    jQuery('#reprinted-table .group-checkable').change(function () {
        var set = $('#reprinted-table').find(".checkboxes");
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
    suspectedInfringementEcharts();
    terminalProportionEcharts();
    suspectedRankingEcharts();
    reprintedTable();
});