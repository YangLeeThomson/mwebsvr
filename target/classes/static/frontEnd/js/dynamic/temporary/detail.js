$(function(){
	var webpageCode = $('#webpageCode').val();
	console.log(webpageCode);
	$.ajax({
        url : ctx+'/peoplesdailydetail',//这个就是请求地址对应sAjaxSource
        data:{webpageCode:webpageCode},
        type : 'get',
        dataType : 'json',
        async : true,
        success : function(data) {
        	console.log(data);
        	if(data.result){
        		if(data.resultObj != null && data.resultObj != ''){
        			$('.detailShow').html(data.resultObj.content);
        		}
        		
        	}
        }
	})
	
})