var table1;
$(function(){
	table1 = $('.table-reprinted-most').DataTable({
    	serverSide: true,//标示从服务器获取数据
        sAjaxSource : ctx+'/pagenews',//服务器请求
        fnServerData : retrieveData,//用于替换默认发到服务端的请求操作,默认方法为：$.getJSON
        iDisplayLength : 10,//每页显示条数
        fnServerParams : function ( aoData ) {
        	
        },
        
//        服务器传过来的值
        "rowCallback" : function(row, data, index) {
        	var title;
        	title = '<a href="'+ctx+'/gotoDetail/'+data.webpageCode+'" target="_blank">'+data.title+'</a>';
        	var opera;
        	opera = '<a href="javascript:void(0)" onclick="okClick(\''+data.webpageCode+'\')">'+ "操作" +'</a>';
        	$('td:eq(1)', row).html(title);
        	$('td:eq(5)', row).html(opera);
        },
        
//        服务器传过来的值
        columns: [//显示的列
            {data: 'webpageCode', "bSortable": false},
            {data: 'title', "bSortable": false},
            {data: 'url', "bSortable": false},
            {data: 'm_url', "bSortable": true},
            { data: 'org', "bSortable": true},
            { data: 'delete', "bSortable": false}
        ],
        "aaSorting": [[3, "desc"]],
    });
	
	
})
 function okClick (webpageCode) {  
	$.ajax({
		type : 'get',
        dataType : 'json',
		   url:ctx + '/peoplesdailyopera' ,
		   data:{webpageCode:webpageCode},
		  success:function(data) {  
			    console.log(data);
			    table1.ajax.reload();
			   }
		});
    }

 