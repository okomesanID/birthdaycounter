<!-- ホーム画面 -->

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
		<div class=header-nav>
			<nav class="gnav">
				<c:choose>	
					<c:when test ="${logincheck == 1}">
						<ul class="gnav-list">
							<li class="gnav-item"><a href="Login">ログイン</a></li>
							<li class="gnav-item"><a href="Join">新規登録</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="gnav-list">
							<li class="gnav-item"><a href="Logout">ログアウト</a></li>
						</ul>
					</c:otherwise>
				</c:choose>
			 </nav>
		</div>
		<!-- 投稿画面 -->
		<div class=user-nav>
			<c:choose>	
				<c:when test ="${logincheck == 0}">
					<p>ログインしてください</p>
				</c:when>
				<c:when test ="${logincheck == 1}">
					<p><c:out value= "${loginUser.name}" />さん
					<c:out value= "${loginUser.age}" />歳</p>
					<form action="/birthdaycounter/Home" method="post">
						<input type="text" name="text"><input type="submit" value="送信">
					</form>
					<p>投稿すると名前,年齢,誕生日までの残り日数が公開されます</p>
				</c:when>
				<c:otherwise><!--　機能が出来上がったら書き換えること -->
					<p><c:out value= "${loginUser.name}" />さん
					<c:out value= "${loginUser.age}" />歳</p>
					<p>投稿済みです（再投稿可能まで残り○○日）<p>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- スレッドリスト -->
		<div class="ThreadList"> 
			  	<!-- 画面につぶやきを出力する処理 -->
				<c:forEach var="Thread" items="${ThreadList}">
					<a>
						<c:out value="${Thread.name}" />さん
						<c:out value="${Thread.age}" />歳
						誕生日まであと<c:out value="${counterbirth}" />日<br>
						<c:out value="${Thread.text}" />
					</a>
				</c:forEach>
		</div>
	</body>

</html>