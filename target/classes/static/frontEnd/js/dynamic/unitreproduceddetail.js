//获得时间
var startTimeEcho = new Date($('#startTime').val());
var endTimeEcho = new Date($('#endTime').val());

var startTimeVal = $('#startTime').val();
var endTimeVal = $('#endTime').val();

$('.startTime').text(startTimeEcho.formatDate('yyyy-MM-dd'));
$('.endTime').text(endTimeEcho.formatDate('yyyy-MM-dd'));

var unitName = $('#queryStr').val();
$('.unit-name').text(unitName);
$('.breadcrumbs-title').text(unitName);

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

//以后会实现
function rankingStatisticEcharts(){
	var rankingStatisticEcharts = echarts.init(document.getElementById('ranking-statistic-echarts'));

	var xAxisData = ['新华网','人民网','21经济网','网易新闻','央视新闻','百度新闻','搜狐新闻','中国新闻网','东方头条','参考消息'];
	var data1 = ['11','13','14','20','24','28','29','34','38','40'];

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

function suspectedInfringementDataAjax(){
	
	var suspectedInfringementData = {
			copyrightCount:'',
			labelSource:'',
			reshipmentCount:'',
			scale:'',
			scaleOther:'',
			colors:['rgba(46,195,232,0.8)','rgba(251,110,82,0.8)'],
	}
	var sourceCrawl = $('#sourceCrawl').val();
	var startTime = startTimeEcho;
	var endTime = endTimeEcho;
	
	$.ajax({
        url : ctx+'/api/news/tortRatio',//这个就是请求地址对应sAjaxSource
        data:{'sourceCrawl':sourceCrawl,'startTime':startTime,'endTime':endTime},
        type : 'get',
        dataType : 'json',
        async : false,
        success : function(data) {
	       if(data.result){
	    	   var obj = data.resultObj;
	    	   if(obj == null){
	    		   suspectedInfringementData.copyrightCount = 0;
	    		   suspectedInfringementData.labelSource = 100;
	    		   suspectedInfringementData.scale = 0
	    	   }else{
	    		   for ( var key in obj) {
		    		   if(key == 0){
		    			   suspectedInfringementData.copyrightCount = obj[key];
		    		   }else{
		    			   suspectedInfringementData.labelSource = obj[key];
		    		   }
		    	   }
	    		   
	    		   if( (suspectedInfringementData.copyrightCount == 0 || suspectedInfringementData.copyrightCount == NaN || suspectedInfringementData.copyrightCount == null || suspectedInfringementData.copyrightCount == '' || suspectedInfringementData.copyrightCount == undefined) && (suspectedInfringementData.labelSource == 0 || suspectedInfringementData.labelSource == NaN || suspectedInfringementData.labelSource == null || suspectedInfringementData.labelSource == '' || suspectedInfringementData.labelSource == undefined) ){
	    			   
	    			   suspectedInfringementData.copyrightCount = 0;
	    			   suspectedInfringementData.labelSource = 0;
	    			   suspectedInfringementData.scale = 0;
	    			   suspectedInfringementData.reshipmentCount = 0;
	    			   suspectedInfringementData.colors=['rgba(46,195,232,0.4)'];
	    			   
	    		   }else if(suspectedInfringementData.copyrightCount == 0 || suspectedInfringementData.copyrightCount == NaN || suspectedInfringementData.copyrightCount == null || suspectedInfringementData.copyrightCount == '' || suspectedInfringementData.copyrightCount == undefined){
	    			   
	    			   suspectedInfringementData.copyrightCount = 0;
//	    			   suspectedInfringementData.labelSource = 100;
	    			   suspectedInfringementData.scale = 0;
	    			   suspectedInfringementData.reshipmentCount = suspectedInfringementData.labelSource;
	    			   
	    		   }else if(suspectedInfringementData.labelSource == 0 || suspectedInfringementData.labelSource == NaN || suspectedInfringementData.labelSource == null || suspectedInfringementData.labelSource == '' || suspectedInfringementData.labelSource == undefined){
	    			   
//	    			   suspectedInfringementData.copyrightCount = 100;
	    			   suspectedInfringementData.labelSource = 0;
	    			   suspectedInfringementData.scale = 100;
	    			   suspectedInfringementData.reshipmentCount = suspectedInfringementData.copyrightCount;
	    		   }else{
	    			   
	    			   suspectedInfringementData.reshipmentCount = suspectedInfringementData.copyrightCount+suspectedInfringementData.labelSource;
	    			   suspectedInfringementData.scale = parseInt(suspectedInfringementData.copyrightCount/suspectedInfringementData.reshipmentCount*100);
	    			   suspectedInfringementData.scaleOther = 100-suspectedInfringementData.scale;
	    		   }
	    		   
	    	   }
	       }
        },
        error : function(msg) {
        }
	});
	
	return suspectedInfringementData;
	
}

function suspectedInfringementEcharts(){
	var suspectedInfringementData = suspectedInfringementDataAjax();
    var suspectedInfringementEcharts = echarts.init(document.getElementById('suspected-infringement-echarts'));
 
    var option = {
//    	color:['rgba(46,195,232,0.8)','rgba(251,110,82,0.8)'],
    	color:suspectedInfringementData.colors,
        series: [
            {
            	name:'本周转载数',
	            type:'pie',
	            radius : '70%',
	            center: ['50%', '50%'],
	            stillShowZeroSum:true,
	            label:{
	            	normal:{
	            		textStyle:{
	            			fontSize:14
	            		}
	            	},
	            },
	            data:[
					{value:suspectedInfringementData.copyrightCount, name:'版权存疑\n'+suspectedInfringementData.copyrightCount+'次 ('+suspectedInfringementData.scale+'%)'},
					{value:suspectedInfringementData.labelSource, name:'标注来源\n'+suspectedInfringementData.labelSource+'次 ('+suspectedInfringementData.scaleOther+'%)'},
	            ]
            }
        ]
    };

    suspectedInfringementEcharts.setOption(option);
    
    
	$('.reprinted-copyright-count').find('span').text(suspectedInfringementData.reshipmentCount);
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

function reprintedPlateRankingDataAjax(){
	var reprintedPlateRankingData = {
			yAxisData:[],
			data:[]	
	};
	
	var sourceCrawl = unitName;
	var startTime = startTimeEcho;
	var endTime = endTimeEcho;
	
	$.ajax({
        url : ctx+'/api/news/companyReprintSectionRanking',//这个就是请求地址对应sAjaxSource
        data:{'sourceCrawl':sourceCrawl,'startTime':startTime,'endTime':endTime},
        type : 'get',
        dataType : 'json',
        async : false,
        success : function(data) {
	       if(data.result){
	    	   var obj = data.resultObj;
	    	   for ( var key in obj) {
	    		   reprintedPlateRankingData.yAxisData.unshift(key);
	    		   reprintedPlateRankingData.data.unshift(obj[key]);
	    	   }
	       }
        },
        error : function(msg) {
        }
	});
	
	return reprintedPlateRankingData;
}

function reprintedPlateRankingEcharts(){
	
	var reprintedPlateRankingData = reprintedPlateRankingDataAjax();
	var reprintedPlateRankingEcharts = echarts.init(document.getElementById('reprinted-plate-ranking-echarts'));
	
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
			data: reprintedPlateRankingData.yAxisData,
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
	        data: reprintedPlateRankingData.data,
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

var table1;
function reprintedSituationTable(){
	var scrollCon = '';
	if($('body').width()<768){
		scrollCon = true;
	}
	table1 = $('#reprinted-situation-table').DataTable({
		scrollX: scrollCon,
		serverSide: true,//标示从服务器获取数据
        sAjaxSource : ctx+'/api/news/pageReprintNews',//服务器请求
        fnServerData : retrieveData,//用于替换默认发到服务端的请求操作,默认方法为：$.getJSON
        fnServerParams : function ( aoData ) {
        	
        	var sourceCrawl = unitName;
        	var reprintType = $('.reprintType').val();
        	var status = $('.status').val();
//        	alert(status);
        	var startTime = startTimeEcho;
        	var endTime = endTimeEcho;
        	
//        	给服务器传的值
        	aoData.push(
        		{"name":"sourceCrawl","value":sourceCrawl},
        		{"name":"reprintType","value":reprintType},
        		{"name":"status","value":status},
        		{"name":"startTime","value":startTime},
        		{"name":"endTime","value":endTime}
        	);
        },
		
//      服务器传过来的值
        "rowCallback" : function(row, data, index) {
	      	var content;
	      	content='<a target="_blank" href="'+ctx+'/gotoReprintedDatail?webpageCode='+data.webpageCode+'&newsId='+data.newsId+'&contentSimilarity='+data.contentSimilarity+'"><i class="fa fa-file-text-o"></i></a>';
	      	
	      	var checkbox;
	      	checkbox = '<div class="checker"><span class=""><input type="checkbox" class="checkboxes colored-blue" value="'+data.webpageCode+'"/></span></div>'
	      		
	      	var reshipmentTitle;
        	reshipmentTitle = '<a href="'+data.webpageUrl+'" target="_blank">'+data.title+'</a>';
        	
        	var originalTitle;
        	originalTitle = '<a href="'+ctx+'/gotoArticlerePrintedDetail?newsId='+data.newsId+'&startTime='+startTimeVal+'&endTime='+startTimeVal+'" target="_blank">'+data.reqNewsTitle+'</a>';
        	
        	var similarity;
        	similarity = data.contentSimilarity.toFixed(2)*100+'%';
        	
        	if(data.reqReportDatetime > data.releaseDatetime){
        		$('td:eq(5)', row).addClass('data-error-time');
        	}
        	
        	$('td:eq(9)', row).html(content);
        	$('td:eq(6)', row).html(similarity);
        	$('td:eq(4)', row).html(originalTitle);
        	$('td:eq(3)', row).html(reshipmentTitle);
        	$('td:eq(0)', row).html(checkbox);
        	$('td:eq(3)', row).addClass('title-ellipsis');
        	$('td:eq(4)', row).addClass('title-ellipsis');
        	
        	if($('body').width()<768){
	        	$('#reprinted-situation-table').css({
	        		'width':'1200px'
	        	});
        	}
	      },
	      
	//      服务器传过来的值
	      columns: [//显示的列
	          {data: 'innerid', "bSortable": false,'sWidth':'34px'},
	          { data: 'createDatetime', "bSortable": false,
	          	render:function(data, type, row){
	            		if(null != data && "" != data){
	            			var createDatetime = new Date(data);
	            			var time = createDatetime.formatDate('yyyy-MM-dd hh:mm');
							return time;
	            		}else{
	            			return '-';
	            		}
	              }
	          },
	          { data: 'sourceCrawl', "bSortable": false},
	          { data: 'title', "bSortable": false},
	          { data: 'reqNewsTitle', "bSortable": false},
	          { data: 'releaseDatetime', "bSortable": false ,
	          	render:function(data, type, row){
	            		if(null != data && "" != data){
	            			var releaseDatetime = new Date(data);
	            			var time = releaseDatetime.formatDate('yyyy-MM-dd hh:mm');
							return time;
	            		}else{
	            			return '-';
	            		}
	              }
	          },
	          { data: 'contentSimilarity', "bSortable": false },
	          { data: 'reprintTypeName', "bSortable": false },
	          { data: 'statusName', "bSortable": false },
	          { data: 'innerid',"bSortable": false}
	      ],
	      "aaSorting": [[0, ""]],
    });    
    //Check All 
    imonitorStyle.checkAll('#reprinted-situation-table');
}

function reprintedOtherSituationTable(){
	var oTable = $('#reprinted-othersituation-table').dataTable({
        "aoColumns": [
          { "bSortable": false },
          { "bSortable": false },
          { "bSortable": false },
          { "bSortable": false },
          { "bSortable": false },
        ],
        "aaSorting": []
    });
    //Check All 
    imonitorStyle.checkAll('#reprinted-othersituation-table');
}
//获取转载类型列表
function listReprintType(){
	$.ajax({
        url : ctx+"/api/news/listReprintType",
        type : 'GET',
        dataType : 'json',
        success : function(data) {
        	var content = "";
        	if(data.result){
        		var map = data.resultObj;
        		for ( var key in map) {
					content += '<option value="'+key+'">'+map[key]+'</option>';
				}
        	}
        	content += '<option value="">全部</option>';
        	$('.reprintType').html(content);
        },
        error : function() {
        }
	});
}
//获取转载状态列表
function listReprintStatus(){
	$.ajax({
        url : ctx+"/api/news/listReprintTypeStatus",
        type : 'GET',
        dataType : 'json',
        success : function(data) {
        	var content = "";
        	if(data.result){
        		var map = data.resultObj;
        		content += '<option value="">全部</option>';
        		for ( var key in map) {
					content += '<option value="'+key+'">'+map[key]+'</option>';
				}
        	}
        	$('.status').html(content);
        },
        error : function() {
        }
	});
}

//最新转载情况，点击下拉框更新数据
function changeSelectedData(){
	$('.reprintType').change(function(){
		table1.ajax.reload();
	});
	$('.status').change(function(){
		table1.ajax.reload();
	});
}

//unitrepropoi 最新转载记录列表 
function unitrepropoi(){
	
	$('#unitrepropoi').click(function(){
		var codeArr = [];
		$(":checkbox:checked").each(function(){
			var code = $(this).val();
			codeArr.push(code);
		});
		
		var selectedData = codeArr.join(",");
		var startTime = startTimeEcho;
		var endTime = endTimeEcho;
		var fileName = formatDateTime(startTime) + '至' + formatDateTime(endTime);
		fileName = encodeURI(fileName);
		var startTimeDate = formatDate(startTime);
		var endTimeDate = formatDate(endTime);
		var newsId = $('#newsId').val();
		var sourceCrawl = unitName;
		//转载类型、状态
		var reprintType = $('.reprintType').val();
    	var status = $('.status').val()
    	location.href = ctx+'/api/excel/unitrepropoi?selectedData='+selectedData+
    	'&startTime='+startTimeDate+'&endTime='+endTimeDate+'&fileName='+fileName+
    	'&reprintType='+reprintType+'&status='+status+'&newsId='+newsId+'&sourceCrawl='+sourceCrawl;;
	});
}
var formatDateTime = function (date) {  
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? ('0' + m) : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    return y + '-' + m + '-' + d;  
};
var formatDate = function (date) {  
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? ('0' + m) : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    var h = date.getHours();  
    var minute = date.getMinutes();  
    minute = minute < 10 ? ('0' + minute) : minute; 
    var second = date.getSeconds();  
    second = second < 10 ? ('0' + second) : second; 
    return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;  
}; 

$(function(){
	//获取转载类型列表
	listReprintType();
	//获取转载状态列表
	listReprintStatus();
	
	suspectedInfringementEcharts();
	reprintedStatisticalProportion();
	reprintedPlateRankingEcharts();
	reprintedSituationTable();
	
	changeSelectedData();
	unitrepropoi();
});