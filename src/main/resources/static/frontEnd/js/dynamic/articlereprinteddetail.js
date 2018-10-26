//获得时间
var startTimeEcho = new Date(Date.parse($('#startTime').val().replace("GMT 0800","")));
var endTimeEcho = new Date(Date.parse($('#endTime').val().replace("GMT 0800","")));

var startTimeVal = $('#startTime').val();
var endTimeVal = $('#endTime').val();

$('.startTime').text(startTimeEcho.formatDate('yyyy-MM-dd'));
$('.endTime').text(endTimeEcho.formatDate('yyyy-MM-dd'));


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

function suspectedInfringementDataAjax(){
	
	var suspectedInfringementData = {
			copyrightCount:'',
			labelSource:'',
			reshipmentCount:'',
			scale:'',
			scaleOther:'',
			colors:['rgba(46,195,232,0.8)','rgba(251,110,82,0.8)'],
	}
	
	var newsId = $('#newsId').val();
	var startTime = startTimeEcho;
	var endTime = endTimeEcho;
	
	$.ajax({
        url : ctx+'/api/news/tortRatio?newsId='+newsId,//这个就是请求地址对应sAjaxSource
        data:{'newsId':newsId,'startTime':startTime,'endTime':endTime},
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
	    			   suspectedInfringementData.reshipmentCount = suspectedInfringementData.copyrightCount
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
	            avoidLabelOverlap: false,
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
    
    
	$('.suspected-infringement-box').find('.reprinted-copyright-count span').text(suspectedInfringementData.reshipmentCount);
}


function terminalProportionDataAjax(){
	
	var terminalProportionData = {
			data:[],
			value:[],
			colors:['rgba(46,195,232,0.8)','rgba(251,110,82,0.8)'],
	}
	
	var newsId = $('#newsId').val();
	var startTime = startTimeEcho;
	var endTime = endTimeEcho;
	
	$.ajax({
        url : ctx+'/api/news/reprintTypeRatio?newsId='+newsId,//这个就是请求地址对应sAjaxSource
        data:{'newsId':newsId,'startTime':startTime,'endTime':endTime},
        type : 'get',
        dataType : 'json',
        async : false,
        success : function(data) {
	       if(data.result){
	    	   var obj = data.resultObj;
	    	   for(var key in obj){
	    		   terminalProportionData.data.push(obj[key]);
	    		   terminalProportionData.value.push(key);
	    	   }
	       }
        },
        error : function(msg) {
        }
	});
	
	return terminalProportionData;
	
}

function terminalProportionEcharts(){
	var terminalProportionData = terminalProportionDataAjax();
    var terminalProportionEcharts = echarts.init(document.getElementById('terminal-proportion-echarts'));
 
    var option = {
    	color:['rgba(46,195,232,0.8)','rgba(255,206,85,0.8)','rgba(251,110,82,0.8)'],
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
                label: {
	                normal: {
	                    formatter:'{b}\n{c}次 ({d}%)',
	                    textStyle:{
	                    	fontSize:14,
	                    }
	                }
	            },
                data:[
                    {value:terminalProportionData.data[0], name:terminalProportionData.value[0]},
                    {value:terminalProportionData.data[1], name:terminalProportionData.value[1]},
                    {value:terminalProportionData.data[2], name:terminalProportionData.value[2]},
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

function suspectedRankingDataAjax(){
	var suspectedRankingData = {
			xAxisData:[],
			legalData:[],
			copyrightData:[],
			totalData:[]
	}
	
	var newsId = $('#newsId').val();
	
	$.ajax({
        url : ctx+'/api/news/reprintNumRanking?newsId='+newsId,//这个就是请求地址对应sAjaxSource
        data:{'newsId':newsId},
        type : 'get',
        dataType : 'json',
        async : false,
        success : function(data) {
	       if(data.result){
	    	   var obj = data.resultObj;
	    	   console.log(obj);
	    	   for ( var key in obj.total) {
	    		   suspectedRankingData.xAxisData.push(key);
	    		   suspectedRankingData.totalData.push(obj.total[key]);
	    	   }
	    	   for ( var key in obj.legal) {
	    		   suspectedRankingData.legalData.push(obj.legal[key]);
	    	   }
	    	   for ( var key in obj.tort) {
	    		   suspectedRankingData.copyrightData.push(obj.tort[key]);
	    	   }
	    	   console.log(suspectedRankingData.xAxisData);
	    	   console.log(suspectedRankingData.totalData);
	    	   console.log(suspectedRankingData.legalData);
	    	   console.log(suspectedRankingData.copyrightData);
	       }
        },
        error : function(msg) {
        }
	});
	
	return suspectedRankingData;
	
}

function suspectedRankingEcharts(){
	
	var suspectedRankingData = suspectedRankingDataAjax();
	
    var suspectedRankingEcharts = echarts.init(document.getElementById('suspected-ranking-echarts'));
    var option = {
        color: ['rgba(46,195,232,0.8)','rgba(251,110,82,0.8)','rgba(255,206,85,0.8)'],
        grid: {
            left: '3%',
            right: '10%',
            bottom: '3%',
            top:'18%',
            containLabel: true
        },
        legend: {
            data:['标注来源','版权存疑','总转载次数']
        },
        tooltip: {},
        xAxis:  {
            type: 'category',
            boundaryGap: false,
            axisLabel:{
            	margin:12
            },
            data: suspectedRankingData.xAxisData,
        },
        yAxis: {
            type: 'value',
            
        },
        series: [
					{
					    name:'标注来源',
					    type:'line',
					    data:suspectedRankingData.legalData,
					},
					{
					    name:'总转载次数',
					    type:'line',
					    data:suspectedRankingData.totalData,
					    markPoint: {
					        data: [
					            {type: 'max', name: '最大值'},
					            {type: 'min', name: '最小值'}
					        ]
					    },
					},
					{
					    name:'版权存疑',
					    type:'line',
					    data:suspectedRankingData.copyrightData,
					}
                 ],
        animationEasing: 'elasticOut',
        animationDelayUpdate: function (idx) {
            return idx * 10;
        }
    };

    suspectedRankingEcharts.setOption(option);
}


var table1;
function reprintedTable(){
	var scrollCon = '';
	if($('body').width()<768){
		scrollCon = true;
	}
	table1 = $('#reprinted-table').DataTable({
		scrollX: scrollCon,
		serverSide: true,//标示从服务器获取数据
        sAjaxSource : ctx+'/api/news/pageReprintNews',//服务器请求
        fnServerData : retrieveData,//用于替换默认发到服务端的请求操作,默认方法为：$.getJSON
        fnServerParams : function ( aoData ) {
        	
        	var newsId = $('#newsId').val();
        	var reprintType = $('.reprintType').val();
        	var status = $('.status').val();
        	var startTime = startTimeEcho;
        	var endTime = endTimeEcho;
        	
//        	给服务器传的值
        	aoData.push(
        		{"name":"newsId","value":newsId},
        		{"name":"reprintType","value":reprintType},
        		{"name":"status","value":status},
        		{"name":"startTime","value":startTime},
        		{"name":"endTime","value":endTime}
        	);
        },
        
//      服务器传过来的值
        "rowCallback" : function(row, data, index) {
	      	var content;
	      	content='<a href="'+ctx+'/gotoReprintedDatail?webpageCode='+data.webpageCode+'&newsId='+data.newsId+'&contentSimilarity='+data.contentSimilarity+'" target="_blank"><i class="fa fa-file-text-o"></i></a>';
	      	
	      	var checkbox;
	      	checkbox = '<div class="checker"><span class=""><input type="checkbox" class="checkboxes colored-blue" value="'+data.webpageCode+'"/></span></div>'
	      	
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
	        	$('#reprinted-table').css({
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
    imonitorStyle.checkAll('#reprinted-table');
    
}

//文章内容获得
function contentDataAjax(){
	var newsId = $('#newsId').val();
	$.ajax({
        url : ctx+'/api/news/findRequestNews?newsId='+newsId,//这个就是请求地址对应sAjaxSource
        type : 'get',
        dataType : 'json',
        async : true,
        success : function(data) {
	       if(data.result){
	    	   
	    	   $('.main-content-title h3').html(data.resultObj.title);
	    	   $('.breadcrumbs-title').text(data.resultObj.title);
	    	   $('.main-content-title .file-time').html(new Date(data.resultObj.reportDatetime).formatDate('yyyy-MM-dd hh:mm:ss'));
	    	   $('.main-content-title .file-author').html(data.resultObj.newsAuthor);
	    	   $('.main-content-title .file-plate').html(data.resultObj.newsSection);
	    	   
	    	   var content = data.resultObj.content;
	    	   content = content.replace(/\r\n/g,'</p><p>');
	    	   $('.main-content-article').html('<p>'+content+'</p>');
	    	   
	    	   $(".main-content-article").mCustomScrollbar({
	    			setHeight:984,
	    			theme:"minimal-dark"
	    		});
	       }
        },
        error : function(msg) {
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

//最新转载情况，点击下拉框更新数据
function changeSelectedData(){
	$('.reprintType').change(function(){
		table1.ajax.reload();
	});
	$('.status').change(function(){
		table1.ajax.reload();
	});
}

//转载统计分析详情页打印
function articlereprintpio(){
	$('#articlereprintpio').click(function(){
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
		//转载类型、状态
		var reprintType = $('.reprintType').val();
    	var status = $('.status').val()
    	location.href = ctx+'/api/excel/articlereprintpio?selectedData='+selectedData+
    	'&startTime='+startTimeDate+'&endTime='+endTimeDate+'&fileName='+fileName+
    	'&reprintType='+reprintType+'&status='+status+'&newsId='+newsId;
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
	
    
    
//	页面首页查询回显
	
	var startTime = new Date($('#startTime').val()).formatDate('yyyy-MM-dd');
	var endTime = new Date($('#endTime').val()).formatDate('yyyy-MM-dd');
	$('#time-slice').daterangepicker({
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
		'startDate':startTime, 
		'endDate':endTime
	});
	
	//获取转载类型列表
	listReprintType();
	//获取转载状态列表
	listReprintStatus();
	
	contentDataAjax();
	
	suspectedInfringementEcharts();
    terminalProportionEcharts();
    suspectedRankingEcharts();
    reprintedTable();
    
    changeSelectedData();
    articlereprintpio();
    
    
});