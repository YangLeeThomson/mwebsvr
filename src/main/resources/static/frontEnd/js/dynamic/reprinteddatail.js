function changeContrastWay(){
	$('.change-contrast').find('a').each(function(){
		$(this).click(function(event) {
			$(this).removeClass('btn-default').addClass('btn-info').siblings('a').removeClass('btn-info').addClass('btn-default');
			if($.trim($(this).text()) == '截图对比'){
				$('.screenshots-contrast').removeClass('hide');
				$('.maincontent-contrast').addClass('hide');
			}else{
				$('.screenshots-contrast').addClass('hide');
				$('.maincontent-contrast').removeClass('hide');
			}
		});
	});
}

/*转载新闻*/
function reprintNews(){
	
	var webpageCode = $('#webpageCode').val();
	var newsId = $('#newsId').val();
	
	$.ajax({
        url : ctx+'/api/news/findNewsWebpage?webpageCode='+ webpageCode+'&newsId='+ newsId,//这个就是请求地址对应sAjaxSource
        type : 'get',
        dataType : 'json',
        async : true,
        success : function(data) {
	       if(data.result){
	    	   
	    	   if(data.resultObj.picPath == null){
	    		   $('.reprintNewsContrast').find('.screenshots-contrast').find('.reprinted-main').html('<img src="">');
	    	   }else{
	    		   $('.reprintNewsContrast').find('.screenshots-contrast').find('.reprinted-main').html('<img id="reprintNewsContrastImg" src="'+data.resultObj.picPath+'">');
	    	   }
	    		  
	    	   $('.reprintNewsContrast').find('.reprintNewsContrastTitle span').text(new Date(data.resultObj.picDatetime).formatDate('yyyy-MM-dd hh:mm'));
	    	   
	    	   var content = data.resultObj.content;
	    	   content = content.replace(/\n/g,'</p><p>');
	    	   $('.reprintNewsContrast').find('.maincontent-contrast').find('.reprinted-main').html('<p>'+content+'</p>');
	    	   $('.reprintNewsContrast').find('.maincontent-contrast').find('.reprinted-bottom span').text(data.resultObj.picDatetime);
	    	   
	    	   
	    	   $('.reprintNewsTable').find('.widget-header').find('.widget-caption-right').text(data.resultObj.sourceCrawl);
	    	   
	    	   $('.reprintNewsTable').find('table>tbody').find('tr:eq(0)>td:eq(0)').text(data.resultObj.title);
	    	   
	    	   var webpageUrl;
	    	   if(data.resultObj.webpageUrl.length>50){
	    		   webpageUrl = data.resultObj.webpageUrl.substring(0,50)+'...';
	    	   }else{
	    		   webpageUrl = data.resultObj.webpageUrl;
	    	   }
	    		   
	    	   $('.reprintNewsTable').find('table>tbody').find('tr:eq(1)>td:eq(0)').html('<a href="'+data.resultObj.webpageUrl+'" target="_blank">'+webpageUrl+'</a>');
	    	   
	    	   var releaseDatetime = new Date(data.resultObj.releaseDatetime);
	    	   var releaseDatetime = releaseDatetime.formatDate('yyyy-MM-dd hh:mm');
	    	   $('.reprintNewsTable').find('table>tbody').find('tr:eq(2)>td:eq(0)').text(releaseDatetime);
	    	   $('.reprintNewsTable').find('table>tbody').find('tr:eq(3)>td:eq(0)').text(data.resultObj.newsType);
	    	   $('.reprintNewsTable').find('table>tbody').find('tr:eq(4)>td:eq(0)').text(data.resultObj.newsSection);
	    	   $('.reprintNewsTable').find('table>tbody').find('tr:eq(5)>td:eq(0)').text(data.resultObj.wordCount);
	    	   $('.reprintNewsTable').find('table>tbody').find('tr:eq(6)>td:eq(0)').text(data.resultObj.sourceReport);
	    	   
	    	   $("#reprintNewsContrastImg").imgZoom();
	       }
        },
        error : function(msg) {
        }
	});
}

/*原始新闻*/
function originalNews(){
	var newsId = $('#newsId').val();
	
	$.ajax({
        url : ctx+'/api/news/findRequestNews?newsId='+ newsId,//这个就是请求地址对应sAjaxSource
        type : 'get',
        dataType : 'json',
        async : true,
        success : function(data) {
	       if(data.result){
	    	   
	    	   var content = data.resultObj.content.replace(/\r\n/g,'</p><p>');
	    	   
	    	   if(data.resultObj.picPath == null){
	    		   $('.originalNewsContrast').find('.screenshots-contrast').find('.reprinted-main').html('<p>'+content+'</p>');
	    		   $('.originalNewsContrast').find('.originalNewsContrastTitle span').text('无');
	    	   }else{
	    		   $('.originalNewsContrast').find('.screenshots-contrast').find('.reprinted-main').html('<img src="'+data.resultObj.picPath+'">').addClass('text-center');
	    		   $('.originalNewsContrast').find('.originalNewsContrastTitle span').text(data.resultObj.picDatetime);
	    	   }
	    	   
	    	   
	    	   
	    	   $('.originalNewsContrast').find('.maincontent-contrast').find('.reprinted-main').html('<p></p><p>'+content+'</p>');
	    	   $('.originalNewsContrast').find('.maincontent-contrast').find('.reprinted-bottom span').text(data.resultObj.picDatetime);
	    	   
	    	   
	    	   $('.originalNewsTable').find('.widget-header').find('.widget-caption-right').text(data.resultObj.newsSection);
	    	   $('.originalNewsTable').find('table>tbody').find('tr:eq(0)>td:eq(1)').text(data.resultObj.title);
	    	   $('.breadcrumbs-title').text(data.resultObj.title);
	    	   var webpageUrl;
	    	   if(data.resultObj.webpageUrl!=null && data.resultObj.webpageUrl.length>50){
	    		   webpageUrl = data.resultObj.webpageUrl.substring(0,50)+'...';
	    	   }else{
	    		   webpageUrl = data.resultObj.webpageUrl;
	    	   }
	    	   if(data.resultObj.webpageUrl == null){
	    		   $('.originalNewsTable').find('table>tbody').find('tr:eq(1)>td:eq(1)').html('无');
	    	   }else{
	    		   $('.originalNewsTable').find('table>tbody').find('tr:eq(1)>td:eq(1)').html('<a href="'+data.resultObj.webpageUrl+'" target="_blank">'+webpageUrl+'</a>');
	    	   }
	    	   
	    	   var reportDatetime = new Date(data.resultObj.reportDatetime);
	    	   var reportDatetime = reportDatetime.formatDate('yyyy-MM-dd hh:mm');
	    	   $('.originalNewsTable').find('table>tbody').find('tr:eq(2)>td:eq(1)').text(reportDatetime);
	    	   $('.originalNewsTable').find('table>tbody').find('tr:eq(3)>td:eq(1)').text(data.resultObj.newsType);
	    	   $('.originalNewsTable').find('table>tbody').find('tr:eq(4)>td:eq(1)').text(data.resultObj.newsSection);
	    	   $('.originalNewsTable').find('table>tbody').find('tr:eq(5)>td:eq(1)').text(data.resultObj.wordCount);
	    	   $('.originalNewsTable').find('table>tbody').find('tr:eq(6)>td:eq(1)').text(data.resultObj.originalCode);
	    	   
	       }
        },
        error : function(msg) {
        }
	});
}

$(function(){
	changeContrastWay();
	reprintNews();
	originalNews();
	
	var contentSimilarity=$('#contentSimilarity').val().substring(0,4)*100+'%';
	$('.change-contrast-similarity span').html(contentSimilarity);
});