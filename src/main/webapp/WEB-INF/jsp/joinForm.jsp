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
		<div class=hum>
			
		</div>
		
		<section id="header">
			<jsp:include page="/header.jsp" />
			<nav id="nav">
				<ul class="gnav-list">
					<li class="gnav-item"><a href="Login">ログイン</a></li>
				</ul>
			</nav>
		</section>
		
		<!-- 新規登録フォーム -->
		<div id="wrapper">
			<div class="form-container">
				<div class="form"> 
					<form action="/birthdaycounter/Join" method="post">
						<div class="formtitle">
							<p>新規登録</p>
						</div>
						
						<!-- フォーム部分 -->
						<div class="inputform">
							<!-- nameテキストボックス -->
							<div class="name">
								<p>ユーザー名</p>
								<input type="text" name="name"class="m-form-text"><br>
								<c:if test="${not empty NameerrorMsg}">	
									<div class="error"> <p>${NameerrorMsg}</p> </div>
								</c:if>
								<c:if test="${not empty usercheck}">	
									<div class="error"> <p>${usercheck}</p> </div>
								</c:if>
							</div>
							
							<!-- passテキストボックス -->
							<div class="pass"> 	
								<p>パスワード</p>
								<input type="password" name="pass"class="m-form-text"><br>
									<c:if test="${not empty PasserrorMsg}">	
										<div class="error"> <p>${PasserrorMsg}</p> </div>
									</c:if>
							</div>
							
							<!-- passテキストボックス -->
							<div class="pass"> 	
								<p>パスワード確認</p>
								<input type="password" name="checkpass"class="m-form-text"><br>
								<c:if test="${not empty CasserrorMsg}">	
									<div class="error"> <p>${CasserrorMsg}</p> </div>
								</c:if>
								<c:if test="${not empty SasserrorMsg}">	
									<div class="error"> <p>${SasserrorMsg}</p> </div>
								</c:if>
							</div>
							
							<!-- 生年月日テキストボックス -->
							<div class="birth"> 	
								<p>生年月日</p>
								<select class="year"name="year" class="m-form-text"></select>
								<select class="month"name="month"class="m-form-text"></select>
								<select class="day"name="day"class="m-form-text"></select>
							</div>
							
							<!-- 送信ボタン -->
							<div class="submit">
								<input class="b b-back" type ="submit" value="  送信  "><br>
							</div>
						</div><!-- 入力項目ここまで -->
					</form>
				</div>
			</div>
		</div>
		<script src="js/birth.js"></script>
	</body>

</html>