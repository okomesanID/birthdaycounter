<!-- 登録完了を出力するデフォルトページ -->

<!-- pageディレクティブ -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- tagディレクティブCore -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- HTML開始 -->
<!DOCTYPE html>
<html>	
	<jsp:include page="/head.jsp" />
	<body>
		<!-- header -->
		<section id="header">
			<jsp:include page="/header.jsp" />
		</section>
		<div id="wrapper">
			<div class="Done-container">
				<div class="Done-form"> 
					<p>登録完了しました</p>
					<p>ログインして始めましょう</p>
					<div class="submit">
						<button type ="button"class="b b-back" onclick="location.href= '/birthdaycounter/Login'">ログイン画面へ</button>
					</div>
				</div>
			</div>
		</div>	
	</body>

</html>