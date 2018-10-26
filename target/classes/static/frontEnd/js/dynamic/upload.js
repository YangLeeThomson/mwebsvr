function channelData(){
	$.ajax({
        url : ctx+'/api/request/getTenantRequest',//这个就是请求地址对应sAjaxSource
        type : 'get',
        dataType : 'json',
        async : true,
        success : function(data) {
	       if(data.result){
	    	   var requestList = data.resultObj;
	    	   for (var int = 0; int < requestList.length; int++) {
	    		   var request = requestList[int];
	    		   var requestContent = '';
		    	   requestContent = '<option value="'+request.requestId+'">'+request.requestName+'</option>';
		    	   
		    	   $('#upload-channel').append(requestContent);
	    	   }
	       }
        },
        error : function(msg) {
        }
	});
}


//点击上传，传输数据
function uploadData(){
	var uploadTitle = $('#upload-title').val();
	var uploadContent = $('#upload-content').val();
	var uploadConut = $('#upload-conut').val();
	var occurrenceTime = new Date($('#occurrence-time').val());
	var uploadAuthor = $('#upload-author').val();
	var uploadPlate = $('#upload-plate').val();
	var uploadChannel = $('#upload-channel').val();
	
	var successContent = '<div class="alert alert-success fade in"><button class="close" data-dismiss="alert">×</button><i class="fa-fw fa fa-check"></i><strong></strong> 上传成功！</div>'
	var failedContent = '<div class="alert alert-danger fade in"><button class="close" data-dismiss="alert">×</button><i class="fa-fw fa fa-times"></i<strong></strong>上传失败！</div>'
	
		$.ajax({
        url : ctx+'/api/request/saveRequestNews',//这个就是请求地址对应sAjaxSource
        data:{'title':uploadTitle,'originalCode':uploadConut,'reportDatetime':occurrenceTime,'newsAuthor':uploadAuthor,'newsSection':uploadPlate,'content':uploadContent,'requestId':uploadChannel},
        type : 'post',
        dataType : 'json',
        async : false,
        success : function(data) {
	       if(data.result){
	    	   $('#alertBox').append(successContent);
	       }
        },
        error : function(msg) {
        	$('#alertBox').append(failedContent);
        }
	});
	
}


$(function(){
	
//	发生时间
	$("#occurrence-time").jeDate({
		skinCell:"jedatered",
		format: 'YYYY-MM-DD hh:mm',
//		minDate:$.nowDate(0),
	});
	
	
	$("#registrationForm").bootstrapValidator();
	
	channelData();
	
	$('#upload-btn').click(function(){
		uploadData();
	});
});

