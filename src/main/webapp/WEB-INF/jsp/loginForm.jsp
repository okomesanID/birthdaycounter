<!-- ログイン画面 -->

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
		
		<!-- ログインフォーム -->
		<div class="form-container">
			<form action="/birthdaycounter/Login" method="post">		
				<div class="formtitle">
					<p>ログイン</p>
				</div>
				<!-- フォーム部分 -->
				<div class="inputform">
					<!-- nameテキストボックス -->
					<div class="name">
						<p>ユーザー名</p>
						<input type="text" name="name"><br>
						<c:if test="${not empty NameerrorMsg}">	
							<div class="error"> <p>${NameerrorMsg}</p> </div>
						</c:if>
					</div>
					
					<!-- passテキストボックス -->
					<div class="pass"> 	
						<p>パスワード</p>
						<input type="password" name="pass"><br>
						<c:if test="${not empty PasserrorMsg}">	
							<div class="error"> <p>${PasserrorMsg}</p> </div>
						</c:if>
					</div>
					
					<!-- 送信ボタン -->
					<div class="submit">
						<button type="submit"class="b b-back">ログイン</button>
					</div>
				</div>
			</form>
		</div>
		 	<script src="js/birth.js"></script>
	</body>
</html>