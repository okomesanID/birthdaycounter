$(function() {
	setTimeout(function(){
		$('.start p').fadeIn(1600);
	},500);
	setTimeout(function(){
		$('.start').fadeOut(500);
		window.location.href = "/birthdaycounter/Home";
	},2500); 
});