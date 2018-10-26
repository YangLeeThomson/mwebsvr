/*
  1、控制所有的dataTables中的样式
  2、在使用dataTables时需要引入这个JS
  3、这个js只是控制样式，并不控制数据的传输
*/
(function(window, document, undefined){
var factory = function( $, DataTable ) {
"use strict";


/* Set the defaults for DataTables initialisation */
$.extend( true, DataTable.defaults, {
	dom:
		"<'row'<'col-sm-6'l><'col-sm-6'f>>" +
		"<'row'<'col-sm-12'tr>>" +
		"<'row'<'col-sm-4'i><'col-sm-8'p>>",
 // sEmptyTable: "抱歉， 没有找到",当表格中没有数据（无视因为过滤导致的没有数据）时，该字符串年优先与sZeroRecords显示
//  sZeroRecords: "抱歉， 没有找到", //当对数据进行过滤操作后，如果没有要显示的数据，会在表格记录中显示该字符串
//	bDestroy: true, //是否删除就数据
//	bRetrieve: true, //是否允许生成新表格
	bAutoWidth:false,//自动计算列宽
	bLengthChange: false, //每页显示数量选择
	searching: false,	//搜索框
    iDisplayLength : 10,//每页显示条数
    bInfo : true,  //页脚信息  (当前第 1 - 1 条　共计 1 条)
    bPaginate : true,//是否启用翻页功能
    
    bProcessing: true,//开关，以指定当正在处理数据的时候，是否显示“正在处理”这个提示信息;true or false, defualt false;
	// serverSide: true,//标示从服务器获取数据
    oLanguage: { // 对表格国际化
    	// "sLengthMenu" : "每页显示 _MENU_条",
    	"sZeroRecords" : "没有找到符合条件的数据",/*在查询的时候没有查找到相关信息的时候显示*/
    	"sProcessing" : "数据加载中...",
    	"sInfo" : "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
    	"sInfoEmpty" : "当前第0条　共计0条",
    	"sEmptyTable":"没有可以显示的数据",/*没有数据的显示 No data available in table*/
    	"sInfoFiltered" : "",
    	"sSearch" : "搜索：",
        "sLengthMenu":  "_MENU_ ",/*自定义页面显示条数*/
    	"oPaginate" : {
    		"sFirst" : "<i class='fa fa-fw fa-angle-double-left'></i>",
    		"sPrevious" : "<i class='fa fa-fw fa-angle-left'></i>",
    		"sNext" : "<i class='fa fa-fw fa-angle-right'></i>",
    		"sLast" : "<i class='fa fa-fw fa-angle-double-right'></i>"
    	}
    },

	renderer: 'bootstrap'
} );


}; // /factory

if ( typeof define === 'function' && define.amd ) {
	define( ['jquery', 'datatables'], factory );
}
else if ( typeof exports === 'object' ) {
    factory( require('jquery'), require('datatables') );
}
else if ( jQuery ) {
	factory( jQuery, jQuery.fn.dataTable );
}


})(window, document);




function retrieveData(sSource, aoData, fnCallback) {
    $.ajax({
        url : sSource,//这个就是请求地址对应sAjaxSource
        data : aoData,//这个是把datatable的一些基本数据传给后台,比如起始位置,每页显示的行数
        type : 'get',
        dataType : 'json',
        async : true,
        success : function(data) {
        	if(data.result){
        		fnCallback(data.resultObj);//把返回的数据传给这个方法就可以了,datatable会自动绑定数据的
        	}else{
        	}
	       
        },
        error : function(msg) {
        }
    });
}

