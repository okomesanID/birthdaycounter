<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	
	<div class=respons>
	<a href="/birthdaycounter/Home">BirthdayCounter</a>
		<!-- hanberger -->
		<div class=hamburger>
			<div class="icon">
				<span></span>
				<span></span>
				<span></span>
			</div>
		</div>
	</div>
	
	<script src="js/main.js"></script>
	<!--▽▽jQuery▽▽-->
	<script src="js/jquery-3.6.1.min.js"></script>
		<script>
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
	</script>
	<!--△△jQuery△△-->	