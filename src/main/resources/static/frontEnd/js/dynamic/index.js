EnterPress();
/*本周被转载文章*/
var table1;
var InitiateArticleDataTable = function () {
    return {
        init: function () {
        	var scrollCon = '';
        	if($('body').width()<768){
        		scrollCon = true;
        	}
            //Datatable Initiating
           table1 = $('.table-reprinted-most').DataTable({
        	   	scrollX: scrollCon,
            	serverSide: true,//标示从服务器获取数据
                sAjaxSource : ctx+'/api/news/pageRequestNews',//服务器请求
                fnServerData : retrieveData,//用于替换默认发到服务端的请求操作,默认方法为：$.getJSON
                iDisplayLength : 10,//每页显示条数
                fnServerParams : function ( aoData ) {
                	
                	var requestId = null;
                	
                	var startTime = new Date($('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/"));
                	var endTime = new Date($('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"));
                	
                	
                	$('.sidebar-menu').find('li').each(function(index){
                		if(index == 0){
                			requestId = null;
                		}else{
                			if($(this).hasClass('active')){
                				requestId = $(this).attr('data-requestid');
                    		}
                		}
                		
                	});
                	
//                	给服务器传的值
                	aoData.push(
                		{"name":"requestId","value":requestId},
                		{"name":"startTime","value":startTime},
                		{"name":"endTime","value":endTime}
                	);
                },
                
//                服务器传过来的值
                "rowCallback" : function(row, data, index) {
                	
//                	var startTime = new Date($('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/"));
//                	var endTime = new Date($('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"));
                	
                	var startTimeVal = $('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/");
                	var endTimeVal = $('#time-slice').val().substring(13).replace("-", "/").replace("-", "/");
                	
                	var checkbox;
                	checkbox = '<div class="checker"><span class=""><input type="checkbox" class="checkboxes colored-blue" value="'+data.newsId+'"/></span></div>'
                	$('td:eq(0)', row).html(checkbox);
                	var title;
                	title = '<a href="'+ctx+'/gotoArticlerePrintedDetail?newsId='+data.newsId+'&startTime='+startTimeVal+'&endTime='+endTimeVal+'" target="_blank">'+data.title+'</a>'
                	$('td:eq(2)', row).html(title);
                	$('td:eq(2)', row).addClass('title-ellipsis');
                	
                	if($('body').width()<768){
        	        	$('.table-reprinted-most').css({
        	        		'width':'800px'
        	        	});
                	}
                },
                
//                服务器传过来的值
                columns: [//显示的列
                    {data: 'newsId', "bSortable": false,'sWidth':'34px'},
                    {data: 'newsSection', "bSortable": false},
                    {data: 'title', "bSortable": false},
                    {data: 'reprintCount', "bSortable": true},
                    { data: 'reportDatetime', "bSortable": true,
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
                    { data: 'newsAuthor', "bSortable": false}
                ],
                "aaSorting": [[3, "desc"]],
            });
	
			//Check All 
            imonitorStyle.checkAll('.table-reprinted-most');
        }
    };
}();

/*本周被转载文章 end*/

/*最新转载情况*/
var table2;
var republishedWeekDataTable = function () {
    return {
        init: function () {
        	var scrollCon1 = '';
        	if($('body').width()<768){
        		scrollCon1 = true;
        	}
            //Datatable Initiating
           table2 = $('#republished-week-table').DataTable({
        	   scrollX: scrollCon1,
                serverSide: true,//标示从服务器获取数据
                sAjaxSource : ctx+'/api/news/pageReprintNews',//服务器请求
                fnServerData : retrieveData,//用于替换默认发到服务端的请求操作,默认方法为：$.getJSON
                iDisplayLength : 10,//每页显示条数
                fnServerParams : function ( aoData ) {
                	
                	var requestId = null;
                	var reprintType = $('.reprintType').val();
                	var status = $('.status').val();
                	var startTime = new Date($('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/"));
                	var endTime = new Date($('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"));
                	
                	$('.sidebar-menu').find('li').each(function(index){
                		if(index == 0){
                			requestId = null;
                		}else{
                			if($(this).hasClass('active')){
                				requestId = $(this).attr('data-requestid');
                    		}
                		}
                		
                	});
                	
//                	给服务器传的值
                	aoData.push(
                		{"name":"requestId","value":requestId},
                		{"name":"reprintType","value":reprintType},
                		{"name":"status","value":status},
                		{"name":"startTime","value":startTime},
                		{"name":"endTime","value":endTime}
                	);
                },
                
//                服务器传过来的值
                "rowCallback" : function(row, data, index) {
//                	var startTime = new Date($('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/"));
//                	var endTime = new Date($('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"));
                	
                	var startTimeVal = $('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/");
                	var endTimeVal = $('#time-slice').val().substring(13).replace("-", "/").replace("-", "/");
                	
                	var content;
                	content='<a href="'+ctx+'/gotoReprintedDatail?webpageCode='+data.webpageCode+'&newsId='+data.newsId+'&contentSimilarity='+data.contentSimilarity+'" target="_blank"><i class="fa fa-file-text-o"></i></a>';
                	
                	var checkbox;
                	checkbox = '<div class="checker"><span class=""><input type="checkbox" class="checkboxes colored-blue" value="'+data.webpageCode+'"/><input type="hidden" name="webpageCode" value="'+data.webpageCode+'"/></span></div>'
                	
                	var reshipmentTitle;
                	reshipmentTitle = '<a href="'+data.webpageUrl+'" target="_blank">'+data.title+'</a>';
                	
                	var originalTitle;
                	originalTitle = '<a href="'+ctx+'/gotoArticlerePrintedDetail?newsId='+data.newsId+'&startTime='+startTimeVal+'&endTime='+endTimeVal+'" target="_blank">'+data.reqNewsTitle+'</a>';
                	
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
        	        	$('#republished-week-table').css({
        	        		'width':'1200px'
        	        	});
                	}
                	
                },
                
//                服务器传过来的值
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
                "aaSorting": [[1, "desc"]],
            });
            //Check All 
            imonitorStyle.checkAll('#republished-week-table');
        }

    }

}();

/*最新转载情况 end*/



/*本周转载单位排行*/
function reprintedRankingWeekEchartsAjax(){
	
	var reprintedRankingWeekEchartsData = {
			yAxisData:[],
			data:[]
	}
	
	var requestId = null;
	var startTime = new Date($('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/"));
	var endTime = new Date($('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"));
	
	$('.sidebar-menu').find('li').each(function(index){
		if(index == 0){
			requestId = null;
		}else{
			if($(this).hasClass('active')){
				requestId = $(this).attr('data-requestid');
    		}
		}
		
	});
	$.ajax({
        url : ctx+'/api/news/reprintWebsiteRanking',//这个就是请求地址对应sAjaxSource
        data:{'requestId':requestId,'startTime':startTime,'endTime':endTime},
        type : 'get',
        dataType : 'json',
        async : false,
        success : function(data) {
	       if(data.result){
	    	   var obj = data.resultObj;
	    	   for ( var key in obj) {
	    		   reprintedRankingWeekEchartsData.yAxisData.unshift(key);
	    		   reprintedRankingWeekEchartsData.data.unshift(obj[key]);
	    	   }
	       }
        },
        error : function(msg) {
        }
	});
	
	return reprintedRankingWeekEchartsData;
}

function reprintedRankingWeekEcharts(){
	var reprintedRankingWeekEchartsData = reprintedRankingWeekEchartsAjax();
	var reprintedRankingWeekEcharts = echarts.init(document.getElementById('reprinted-ranking-week-echarts'));
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
			data: reprintedRankingWeekEchartsData.yAxisData,
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
	        data: reprintedRankingWeekEchartsData.data,
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
	reprintedRankingWeekEcharts.on('click',function(params){
//		console.log(params.name);
		var unitName = params.name;
		var startTimeVal = $('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/");
    	var endTimeVal = $('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"); 
    	
		if(unitName != '' && unitName != '输入转载单位名称'){
			unitName = encodeURI(unitName);
	    	window.open(ctx+'/gotoUnitreProducedDetail?queryStr=' + unitName + '&sourceCrawl=' + unitName+ '&startTime='+startTimeVal+'&endTime='+endTimeVal);
		}
	});

}

/*本周转载单位排行 end*/

/*本周版权存疑单位排行*/
function copyrightRankingEchartsAjax(){
	var copyrightRankingEchartsData = {
			yAxisData:[],
			data:[]
	}
	
	var requestId = null;
	var startTime = new Date($('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/"));
	var endTime = new Date($('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"));
	
	$('.sidebar-menu').find('li').each(function(index){
		if(index == 0){
			requestId = null;
		}else{
			if($(this).hasClass('active')){
				requestId = $(this).attr('data-requestid');
    		}
		}
		
	});
	
	
	$.ajax({
        url : ctx+'/api/news/tortWebsiteRanking',//这个就是请求地址对应sAjaxSource
        data:{'requestId':requestId,'startTime':startTime,'endTime':endTime},
        type : 'get',
        dataType : 'json',
        async : false,
        success : function(data) {
	       if(data.result){
	    	   var obj = data.resultObj;
	    	   for ( var key in obj) {
	    		   copyrightRankingEchartsData.yAxisData.unshift(key);
	    		   copyrightRankingEchartsData.data.unshift(obj[key]);
	    	   }
	       }
        },
        error : function(msg) {
        }
	});
	
	return copyrightRankingEchartsData;
}

function copyrightRankingEcharts(){
	
	var copyrightRankingEchartsData = copyrightRankingEchartsAjax();
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
			data: copyrightRankingEchartsData.yAxisData,
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
	        data: copyrightRankingEchartsData.data,
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
	
	copyrightRankingEcharts.on('click',function(params){
//		console.log(params.name);
		var unitName = params.name;
		var startTimeVal = $('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/");
    	var endTimeVal = $('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"); 
    	
		if(unitName != '' && unitName != '输入转载单位名称'){
			unitName = encodeURI(unitName);
	    	window.open(ctx+'/gotoUnitreProducedDetail?queryStr=' + unitName + '&sourceCrawl=' + unitName+ '&startTime='+startTimeVal+'&endTime='+endTimeVal);
		}
	});

}

/*本周版权存疑单位排行 end*/

/*本周转载数/版权存疑占比*/
function reprintedCopyrightCostLeftAjax(){
	var reprintedCopyrightCostLeftData = {
			copyrightCount:'',
			labelSource:'',
			reshipmentCount:'',
			scale:'',
			scaleOther:'',
			colors:['rgba(46,195,232,0.8)','rgba(251,110,82,0.8)'],
	}
	
	var requestId = null;
	var startTime = new Date($('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/"));
	var endTime = new Date($('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"));
	
	$('.sidebar-menu').find('li').each(function(index){
		if(index == 0){
			requestId = null;
		}else{
			if($(this).hasClass('active')){
				requestId = $(this).attr('data-requestid');
    		}
		}
		
	});
	
	
	$.ajax({
        url : ctx+'/api/news/tortRatio',//这个就是请求地址对应sAjaxSource
        data:{'requestId':requestId,'startTime':startTime,'endTime':endTime},
        type : 'get',
        dataType : 'json',
        async : false,
        success : function(data) {
	       if(data.result){
	    	   var obj = data.resultObj;
	    	   
	    	   if(obj == null){
	    		   reprintedCopyrightCostLeftData.copyrightCount = 0;
	    		   reprintedCopyrightCostLeftData.labelSource = 100;
	    		   reprintedCopyrightCostLeftData.scale = 0+'%';
	    	   }else{
	    		   for ( var key in obj) {
		    		   if(key == 0){
		    			   reprintedCopyrightCostLeftData.copyrightCount = obj[key];
		    		   }else{
		    			   reprintedCopyrightCostLeftData.labelSource = obj[key];
		    		   }
		    	   }
	    		   
	    		   if( (reprintedCopyrightCostLeftData.copyrightCount == 0 || reprintedCopyrightCostLeftData.copyrightCount == NaN || reprintedCopyrightCostLeftData.copyrightCount == null || reprintedCopyrightCostLeftData.copyrightCount == '' || reprintedCopyrightCostLeftData.copyrightCount == undefined) && (reprintedCopyrightCostLeftData.labelSource == 0 || reprintedCopyrightCostLeftData.labelSource == NaN || reprintedCopyrightCostLeftData.labelSource == null || reprintedCopyrightCostLeftData.labelSource == '' || reprintedCopyrightCostLeftData.labelSource == undefined) ){
	    			   
	    			   reprintedCopyrightCostLeftData.copyrightCount = 0;
	    			   reprintedCopyrightCostLeftData.labelSource = 0;
	    			   reprintedCopyrightCostLeftData.scale = 0;
	    			   reprintedCopyrightCostLeftData.reshipmentCount = 0;
	    			   reprintedCopyrightCostLeftData.colors=['rgba(46,195,232,0.4)'];
	    			   
	    		   }else if(reprintedCopyrightCostLeftData.copyrightCount == 0 || reprintedCopyrightCostLeftData.copyrightCount == NaN || reprintedCopyrightCostLeftData.copyrightCount == null || reprintedCopyrightCostLeftData.copyrightCount == '' || reprintedCopyrightCostLeftData.copyrightCount == undefined){
	    			   
	    			   reprintedCopyrightCostLeftData.copyrightCount = 0;
//	    			   reprintedCopyrightCostLeftData.labelSource = 100;
	    			   reprintedCopyrightCostLeftData.scale = 0;
	    			   reprintedCopyrightCostLeftData.reshipmentCount = reprintedCopyrightCostLeftData.labelSource;
	    			   
	    		   }else if(reprintedCopyrightCostLeftData.labelSource == 0 || reprintedCopyrightCostLeftData.labelSource == NaN || reprintedCopyrightCostLeftData.labelSource == null || reprintedCopyrightCostLeftData.labelSource == '' || reprintedCopyrightCostLeftData.labelSource == undefined){
	    			   
//	    			   reprintedCopyrightCostLeftData.copyrightCount = 100;
	    			   reprintedCopyrightCostLeftData.labelSource = 0;
	    			   reprintedCopyrightCostLeftData.scale = 100;
	    			   reprintedCopyrightCostLeftData.reshipmentCount = reprintedCopyrightCostLeftData.copyrightCount;
	    			   
	    		   }else{
	    			   
	    			   reprintedCopyrightCostLeftData.reshipmentCount = reprintedCopyrightCostLeftData.copyrightCount+reprintedCopyrightCostLeftData.labelSource;
	    			   reprintedCopyrightCostLeftData.scale = parseInt(reprintedCopyrightCostLeftData.copyrightCount/reprintedCopyrightCostLeftData.reshipmentCount*100);
	    			   reprintedCopyrightCostLeftData.scaleOther = 100-reprintedCopyrightCostLeftData.scale;
	    		   }
	    	   }
	       }
        },
        error : function(msg) {
        }
	});
	
	return reprintedCopyrightCostLeftData;
}
function reprintedCopyrightCostLeft(){
	
	var reprintedCopyrightCostLeftData = reprintedCopyrightCostLeftAjax();
	
	var reprintedCopyrightCostLeft = echarts.init(document.getElementById('reprinted-copyright-cost-left'));

	var option = {
//		color:['rgba(46,195,232,0.8)','rgba(251,110,82,0.8)'],
		color:reprintedCopyrightCostLeftData.colors,
	    /*tooltip: {
	        trigger: 'item',
	        formatter: "{a}：{b}"
	    },*/
	    series: [
	        {
	            name:'本周转载数',
	            type:'pie',
	            radius : '65%',
	            center: ['50%', '50%'],
	            avoidLabelOverlap: false,
	            label:{
	            	normal:{
	            		textStyle:{
	            			fontSize:14
	            		}
	            	},
	            },
	            data:[
	                {value:reprintedCopyrightCostLeftData.copyrightCount, name:'版权存疑\n'+reprintedCopyrightCostLeftData.copyrightCount+'次 ('+reprintedCopyrightCostLeftData.scale+'%)'},
	                {value:reprintedCopyrightCostLeftData.labelSource, name:'标注来源\n'+reprintedCopyrightCostLeftData.labelSource+'次 ('+reprintedCopyrightCostLeftData.scaleOther+'%)'},
	            ]
	        }
	    ]
	};


	reprintedCopyrightCostLeft.setOption(option);
	
	$('.reprinted-copyright-cost').find('.reprinted-copyright-count span').text(reprintedCopyrightCostLeftData.reshipmentCount);
}

/*本周转载数/版权存疑占比 end*/


/*点击侧边栏，进行数据更新*/
function reloadDataSidebar(){
	
	table1.ajax.reload();
	table2.ajax.reload();
	reprintedRankingWeekEcharts();
	copyrightRankingEcharts();
	reprintedCopyrightCostLeft();
}

//最新转载情况，点击下拉框更新数据
function changeSelectedData(){
	$('.reprintType').change(function(){
		table2.ajax.reload();
	});
	$('.status').change(function(){
		table2.ajax.reload();
	});
}

//点击查询，进入查询页
function queryResult(){
//	$('#queryBtn').click(function(){
//		var newsId = $('#newsId').val();
//		
//		var queryStr = $('#query_str').val();
//		var unitName = $('#homecity_name').val();
//		
//		var startTimeVal = $('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/");
//    	var endTimeVal = $('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"); 
//    	
//		if(newsId != ''){
//			window.open(ctx+'/gotoArticlerePrintedDetail?newsId=' + newsId + '&startTime='+startTimeVal+'&endTime='+endTimeVal);
//		}else if(unitName != ''){
//			unitName = encodeURI(unitName);
//			window.open(ctx+'/gotoUnitreProducedDetail?queryStr=' + unitName + '&sourceCrawl=' + unitName+ '&startTime='+startTimeVal+'&endTime='+endTimeVal);
//		}
//	});
	
	$('.query_str_btn').click(function(){
		
		var newsId = $('#newsId').val();
		var queryStr = $('#query_str').val();
		var startTimeVal = $('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/");
    	var endTimeVal = $('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"); 
    	
    	if(newsId != ''){
    		window.open(ctx+'/gotoArticlerePrintedDetail?newsId=' + newsId + '&startTime='+startTimeVal+'&endTime='+endTimeVal);
    	}
		
	});
	
	$('.unit_name_btn').click(function(){
		var unitName = $.trim($('#homecity_name').val());
		var startTimeVal = $('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/");
    	var endTimeVal = $('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"); 
    	
		if(unitName != '' && unitName != '输入转载单位名称'){
			unitName = encodeURI(unitName);
	    	window.open(ctx+'/gotoUnitreProducedDetail?queryStr=' + unitName + '&sourceCrawl=' + unitName+ '&startTime='+startTimeVal+'&endTime='+endTimeVal);
		}
	});
}

/*enter键进入*/
function EnterPress(){
	var inputVal = $('#cityid').val();
	$(document).keydown(function(event){ 
		var e = event || window.event; 
		var k = e.keyCode || e.which; 
		if(k == 13){
			if($('#query_str').is(":focus")==true){
				$('.query_str_btn').click();
			}
			if($('#homecity_name').is(":focus")==true){
				$('#homecity_name').blur();
				$('.unit_name_btn').click();
			}
		}
	});
}
//标题或稿号查询
var titleSearchData = {
		data:[]
};
function titleSearchDataAjax(){
	
	$.ajax({
        url : ctx+'/api/news/listRequestNewsCode',//这个就是请求地址对应sAjaxSource
        type : 'get',
        dataType : 'json',
//        async : false,
        success : function(data) {
	       if(data.result){
	    	   var obj = data.resultObj;
	    	   for(var count = 0;obj.length>count;count++){
	    		   var dataItem = {
//		    			   'title':'<p class="manuscript-num">稿号：'+obj[count].originalCode+'</p>'+'<p class="manuscript-content" style="">'+obj[count].title+'</p>',
	    				   'title':'<p class="manuscript-content" style="">'+obj[count].originalCode+'&nbsp;&nbsp;'+obj[count].title+'</p>',
		    			   'result':{
		    				   'newsId':obj[count].newsId,
		    			   }
		    	   };
	    		   titleSearchData.data.push(dataItem);
	    	   }
	    	   
	    	   $("#query_str").bigAutocomplete({
	    	        data:titleSearchData.data,
	    	        callback:function(data){
	    	            $('#newsId').val(data.result.newsId);
	    	        }
	    	    });
	    	   
	    	   return titleSearchData;
	       }
	       
        },
        error : function(msg) {
        }
	});
	
}



//本周被转载文章 excel PIO报表
function excelPOI(){
	$('#excelPOI').click(function(){
		var codeArr = [];
		/*$(":checkbox:checked").each(function(){
		  var code = $(this).parents("tr").find("td:eq(1)").html();
		  codeArr.push(code);
		});*/
		$(":checkbox:checked").each(function(){
			var code = $(this).val();
			codeArr.push(code);
		});
		var selectedData = codeArr.join(",");
		//date
		var startTime = new Date($('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/"));
		var endTime = new Date($('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"));
		var fileName = formatDateTime(startTime) + '至' + formatDateTime(endTime);
		fileName = encodeURI(fileName);
		var startTimeDate = formatDate(startTime);
		var endTimeDate = formatDate(endTime);
		location.href = ctx+'/api/excel/exportexcel?selectedData='+selectedData+'&startTime='+startTimeDate+'&endTime='+endTimeDate+'&fileName='+fileName;
	});
}
//最新转载情况excel PIO报表
function latestReprint(){
	$('#latestReprint').click(function(){
		var codeArr = [];
		$(":checkbox:checked + input").each(function(){
		  var code = $(this).val();
		  codeArr.push(code);
		});
		
		var selectedData = codeArr.join(",");
		//date
		var startTime = new Date($('#time-slice').val().substring(0,10).replace("-", "/").replace("-", "/"));
		var endTime = new Date($('#time-slice').val().substring(13).replace("-", "/").replace("-", "/"));
		var fileName = formatDateTime(startTime) + '至' + formatDateTime(endTime);
		fileName = encodeURI(fileName);
		var startTimeDate = formatDate(startTime);
		var endTimeDate = formatDate(endTime);
		
		//转载类型、状态
		var reprintType = $('.reprintType').val();
    	var status = $('.status').val()
    	location.href = ctx+'/api/excel/latestReprint?selectedData='+selectedData+
    	'&startTime='+startTimeDate+'&endTime='+endTimeDate+'&fileName='+fileName+
    	'&reprintType='+reprintType+'&status='+status;
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

function getUpdateTime(){
	$.ajax({
        url : ctx+"/api/news/getLatestUpdateTime",
        type : 'GET',
        dataType : 'json',
        success : function(data) {
//        	数据更新时间
        	var uptime = new Date();
        	if(data.result){
        		uptime = new Date(data.resultObj);
        	}
        	$('.updata-time em').text(uptime.formatDate('yyyy-MM-dd hh:mm'));
        },
        error : function() {
        }
	});
}

//获取转载类型列表
function listReprintType(){
	$.ajax({
        url : ctx+"/api/news/listReprintType",
        type : 'GET',
        dataType : 'json',
        async:false,
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
        async:false,
        success : function(data) {
        	var content = "";
        	content += '<option value="">全部</option>';
        	if(data.result){
        		var map = data.resultObj;
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
		'endDate':moment(),
		'startDate':moment().subtract(7, 'days'),
		'opens':'left',
        "ranges": {
            '最近7天': [moment().subtract(6, 'days'), moment()],
            '最近30天': [moment().subtract(29, 'days'), moment()],
            '本月': [moment().startOf('month'), moment().endOf('month')],
            '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
        },
        "alwaysShowCalendars": true,
    };
	$('#time-slice').daterangepicker(options, function(start, end, label) {});
	$('#time-slice').on('hide.daterangepicker',function(ev, picker){
		table1.ajax.reload();
		table2.ajax.reload();
		reprintedRankingWeekEcharts();
		copyrightRankingEcharts();
		reprintedCopyrightCostLeft();
	});

//单位名称-携程方式
function initCompanySource(){
	
	$.ajax({
        url : ctx+"/api/excel/getcompnaysource",
        type : 'GET',
        dataType : 'json',
        success : function(data) {
        	$$.module.address.source.hotel = data.resultObj[0];
        	$$.module.address.source.hotel_hotData = eval('(' +data.resultObj[1]+')');
        },
        error : function() {
        }
	});
}

	$('#query_str').keyup(function(){
		$('#newsId').val('');
	});

	//获取更新时间
	getUpdateTime();
	//获取转载类型列表
	listReprintType();
	//获取转载状态列表
	listReprintStatus();

//	页面初始化
	InitiateArticleDataTable.init();
	republishedWeekDataTable.init();
	
	reprintedRankingWeekEcharts();
	copyrightRankingEcharts();
	reprintedCopyrightCostLeft();
//	页面初始化 end
	changeSelectedData();
	queryResult();
	titleSearchDataAjax();
	
//POI
	excelPOI();
	latestReprint();
	//初始化单位来源
	initCompanySource();
	
	
});
