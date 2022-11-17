<!-- 新規登録画面を出力するデフォルトページ -->

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
		
		<!-- 新規登録確認 -->
		<div class="form-container">
			<div class="formtitle">
				<p>登録確認</p>
			</div>
			
			<!-- フォーム部分 -->
			<div class="inputform">
				<p>以下の内容でアカウント登録します</p><br><br>
				<p>ユーザ名：<c:out value= "${joinUser.name}" /></p>
				<p>生年月日：<c:out value= "${joinUser.year}" />年
							<c:out value= "${joinUser.month}"/>月
							<c:out value= "${joinUser.day}" />日</p>
				<p>年齢：<c:out value= "${joinUser.age}" />歳</p>
				<div class="submit">
					<button type ="button"class="b b-back" onclick="location.href= '/birthdaycounter/Join'">　戻る　</button>
					<button type="button" class="b b-back" onclick="location.href= '/birthdaycounter/Join?action=done'">登録確定</button>
				</div>
			</div>
			
		</div>
		
	</body>

</html>