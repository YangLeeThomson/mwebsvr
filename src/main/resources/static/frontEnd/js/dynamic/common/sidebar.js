
function getTenant(){
	$.ajax({
        url : ctx+'/api/tenant/getTenant',//这个就是请求地址对应sAjaxSource
        type : 'get',
        dataType : 'json',
        async : true,
        success : function(data) {
	       if(data.result){
//	    	   data.resultObj [tenantId  tenantName]
//	    	   alert(data.resultObj.tenantName);
	    	   
	    	   var tenantContent = '';
	    	   tenantContent = '<li class="active" onclick="reloadDataSidebar()"><a href="javascript:void(0)" class="menu-dropdown"><i class="menu-icon glyphicon glyphicon-home"></i><span class="menu-text">'+data.resultObj.tenantName+' </span><i class="menu-expand"></i></a></li>';
	    	   $('.sidebar-menu').html(tenantContent);
	    	   getRequest();
//	    	   添加3个假页面的链接
//	    	   var content = '';
//	    	   content += '<li><a  target="_black" href="'+ctx+'/gotoIndex"><i class="menu-icon glyphicon glyphicon-tasks"></i><span class="menu-text">综合传播力分析</span></a></li><li><a href="'+ctx+'/gotoMediaForwarding" target="_black"><i class="menu-icon fa fa-th"></i><span class="menu-text">被转发情况分析</span></a></li><li><a href="'+ctx+'/gotoHotImpact" target="_black"><i class="menu-icon fa fa-weibo"></i><span class="menu-text">热点话题影响分析</span></a></li>';
//	    	   $('.sidebar-menu').append(content);
	       }
        },
        error : function(msg) {
        }
	});
}

function getRequest(){
	$.ajax({
        url : ctx+'/api/request/getTenantRequest',//这个就是请求地址对应sAjaxSource
        type : 'get',
        dataType : 'json',
        async : true,
        success : function(data) {
	       if(data.result){
	    	   var requestList = data.resultObj;
	    	   for (var int = 0; int < requestList.length; int++) {
//	    		   request.requestId; 点击时传值
//	    		   request.requestName;  显示的名字
	    		   var request = requestList[int];
	    		   var requestContent = '';
//		    	   requestContent = '<li class="sidebarItem" onclick="reloadDataSidebar()"  data-requestid ='+ request.requestId +'><a href="javascript:void(0)"><i class="menu-icon fa fa-laptop"></i><span class="menu-text"> '+request.requestName+' </span></a></li>'
		    	   requestContent = '<ul class="submenu" style="display: none;"><li data-requestid ='+ request.requestId +'><a href="javascript:void(0)"><span class="menu-text">'+request.requestName+'</span></a></li></ul>'
		    	   
		    	   $('.sidebar-menu').find('li:eq(0)').append(requestContent);
	    	   }
	    	   
	    	   changeActive();
	    	   

	       }
        },
        error : function(msg) {
        }
	});
}

function changeActive(){
	$('.sidebar-menu').find('li').click(function(){
//		$('.sidebar-menu').find('li').removeClass('active');
		$(this).addClass('active').siblings('li').removeClass('active').find('li').removeClass('active');
	});
}


$(function(){
	getTenant();
});
	
