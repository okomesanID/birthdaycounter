$('.hamburger').on('click', function(){
	if($('#header').hasClass('off')){
		$('#header').removeClass('off');
		$('#header').animate({'marginRight':'-500px'},300).addClass('on');
		$('.icon').addClass('on');
		$('.icon').removeClass('off');
		$('#header').slideToggle(10);
	}
	else{
		$('#header').slideToggle(10);
		$('#header').removeClass('on');
		$('#header').animate({'marginRight':'500px'},300).addClass('off');
		$('.icon').addClass('off');
		$('.icon').removeClass('on');
	}
});