
$(function(){
	$(".btn").click(function(){
		is_hide();
	});
	$('[data-toggle="popover"]').popover({
		html:true,
		/*trigger:"focus",*/
		placement:'top',
		/*content:'<img src="'+ctx+'/frontEnd/image/login/iconwarn.png"> 用户名或密码不能为空！'*/
	});
	
	var u = $("input[name=userName]");
	var p = $("input[name=password]");
	$('#submit').click(function(){
		var userName = u.val().trim();
		var password = p.val().trim();
		if(userName == '')
		{
			/*$("#ts").html("用户名或密码不能为空~");
			is_show();*/
			$('.username').attr('data-content','<img src="'+ctx+'/frontEnd/images/login/iconwarn.png"> 用户名不能为空！');
			$('.username').popover('show');
			return false;
		}else if( password ==''){
			$('.password').attr('data-content','<img src="'+ctx+'/frontEnd/images/login/iconwarn.png"> 密码不能为空！');
			$('.password').popover('show');
		}else{
			var data = {"userName":userName,"password":sha256_digest(password)};
			$.ajax({
		        url : ctx+"/login",
		        type : 'post',
		        data:data,
		        dataType : 'json',
		        success : function(data) {
		        	if(data.result){
		        		location.href = data.resultObj;
		        	}else{
		        		/*alert(data.errorMsg);*/
		        		$('.username').attr('data-content','<img src="'+ctx+'/frontEnd/images/login/iconwarn.png"> 用户名或密码有误！');
		    			$('.username').popover('show');
		        	}
		        },
		        error : function(msg) {
		        }
		    });
		}
	});

	$(".connect p").eq(0).animate({"left":"0%"}, 600);
	$(".connect p").eq(1).animate({"left":"0%"}, 400);

	EnterPress();
	registerCookie();
	if($('body').width()<768){
		$('.connectBox .inewslogo').addClass('hide');
		$('.ueclogo').find('img:eq(1)').removeClass('sr-only');
	}else{
		$('.connectBox .inewslogo').removeClass('hide');
		$('.ueclogo').find('img:eq(1)').addClass('sr-only');
	}
	
	$('.loginForm').find('input').each(function(){
		
		/*if($(this).is(":focus")==true){
			
			$('[data-toggle="popover"]').popover('hide');
			$('.loginForm').find('input').each(function(){
				$(this).removeAttr('data-content');
			});
		}*/
		$(this).click(function(){
			$('[data-toggle="popover"]').popover('hide');
			$('.loginForm').find('input').each(function(){
				$(this).removeAttr('data-content');
			});
		});
	});
});

/*enter键进入*/
function EnterPress(){
	$(document).keydown(function(event){ 
		var e = event || window.event; 
		var k = e.keyCode || e.which; 
		if(k == 13){
			$('#submit').click();
		}
	});
}

function is_hide(){ 
	$(".alert").animate({"top":"-40%"}, 300) 
}
function is_show(){ 
	$(".alert").show().animate({"top":"45%"}, 300) 
}

function registerCookie(){
	$('input.rememberpwd').click(function(){
		if($(this).attr('checked')==undefined){
			$(this).attr('checked','checked');
		}else{
			$(this).removeAttr('checked');
		}
	});
	
	if($.cookie("username")){
        $(".username").val($.cookie("username"));
	}
	if($.cookie("password")){
		$(".password").val($.cookie("password"));
	}
	if($.cookie("checkBox")){
		$('input.rememberpwd').attr('checked','checked');
	}
  
    $("#submit").click(function () {
        $.cookie("username", $(".username").val(), {path:"/",expires: 7});
        if($('input.rememberpwd').attr('checked')==undefined){
        	$.cookie('checkBox', '', { expires: -1, path: '/' });
        	$.cookie('password', '', { expires: -1, path: '/' });
        }else{
        	$.cookie("password", $(".password").val(), {path:"/",expires: 7});
        	$.cookie("checkBox", $("input.rememberpwd").attr('checked'), {path:"/",expires: 7});
        }
    })
}