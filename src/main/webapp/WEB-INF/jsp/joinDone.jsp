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
		<jsp:include page="/header.jsp" />
			<p>登録完了しました</p>
			<p>ログインして始めましょう</p>
			<div class="submit">
				<button type ="button"class="b b-back" onclick="location.href= '/birthdaycounter/Login'">ログイン画面へ</button>
			</div>
			
	</body>

</html>