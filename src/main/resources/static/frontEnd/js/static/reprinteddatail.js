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

$(function(){
	changeContrastWay();
});