
/**
 *
 * 插件名：common.js
 * Author：xlYang
 */
(function(factory) {
  "use strict";
  if (typeof define === "function" && (define.amd || define.cmd)) {
    define(["jquery"], factory);
  } else {
    factory((typeof(jQuery) != "undefined") ? jQuery : window.Zepto);
  }
});
(function($) {
	/**
	 * 时间对象的格式化，只要是时间对象，都可以调用该方法
	 * @param format 传入值,日期格式，比如"yyyy-MM-dd hh:mm:ss"
	 * @returns {String} 格式化之后的时间
	 */
	Date.prototype.formatDate = function(format) {
		/*
		 * eg:format="yyyy-MM-dd hh:mm:ss";
		 */
		var o = {
			"M+" : this.getMonth() + 1, // month
			"d+" : this.getDate(), // day
			"h+" : this.getHours(), // hour
			"m+" : this.getMinutes(), // minute
			"s+" : this.getSeconds(), // second
			"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
			"S" : this.getMilliseconds()
			// millisecond
		}

		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "").substr(4
							- RegExp.$1.length));
		}

		for (var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1
								? o[k]
								: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
	
	$.ajaxSetup ({
        cache: false //关闭AJAX缓存
    });
	
	

	imonitorStyle = function(){

		return {
			/*datatable表格中，全选*/
			checkAll : function(nameId){
				
				jQuery( nameId+' .group-checkable').change(function () {
	                var set = $(nameId).find(".checkboxes");
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
		}
			
	}();

})(window.jQuery);	




   