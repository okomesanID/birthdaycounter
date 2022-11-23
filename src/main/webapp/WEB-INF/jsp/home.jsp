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
		<!-- hanberger -->
		<!--<div class=hum>
			<span></span>
			<span></span>
			<span></span>
			<ul class="slide-menu">
				<li><a href="Login">ログイン</a></li>
				<li><a href="Join">新規登録</a></li>
			</ul>
		</div>-->
			
		<!-- header -->
		<section id="header">
			<jsp:include page="/header.jsp" />
			<nav id="nav">
				<c:choose>	
					<c:when test ="${logincheck == 0}">
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
		</section>
		
		<!-- Wrapper -->
		<div id="wrapper">
		    <!-- Main -->
		    <div id="main">
				<!-- 投稿画面 -->
				<section id="one">
					<div class=user-nav>
						<c:choose>	
							<c:when test ="${logincheck == 0}">
								<p>ログインしてください</p>
							</c:when>
							<c:when test ="${logincheck == 1}">
								<p><c:out value= "${loginUser.name}" />さん
								<c:out value= "${loginUser.age}" />歳</p>
								<form action="/birthdaycounter/Home" method="post">
									<input type="text" name="text"class="m-form-text"><input type="submit" class="t t-back" value="送信">
								</form>
								<p>投稿すると名前,年齢,誕生日までの残り日数が公開されます</p>
							</c:when>
							<c:otherwise>
								<p><c:out value= "${loginUser.name}" />さん
								<c:out value= "${loginUser.age}" />歳</p>
								<p>投稿済みです（再投稿可能まで残り${CounterThread}日）<p>
							</c:otherwise>
						</c:choose>
					</div>
				</section>
				
				<!-- スレッドリスト -->
				<section id="two">
					<div class="threadlist"> 
						<c:choose>
							<c:when test ="${ThreadList.size()-1 < 0}">
							<p>まだスレッドがありません。</p>
							</c:when>
							<c:otherwise>
								<!-- 画面にスレッドを出力する処理 -->
								<c:forEach var="i" begin="0" end="${ThreadList.size()-1}" step="1">
								<div class="thread"> 
									<a href= "/birthdaycounter/Comment?ThreadNo=${ThreadList.get(i).getId()}">
										<c:choose>	
											<c:when test="${counterbirth.get(i) == 1}">
												<c:out value="${ThreadList.get(i).getName()}" />さん
												<c:out value="${ThreadList.get(i).getAge()+1}" />歳
											</c:when>
											<c:otherwise>
												<c:out value="${ThreadList.get(i).getName()}" />さん
												<c:out value="${ThreadList.get(i).getAge()}" />歳
											</c:otherwise>
										</c:choose>
										<c:choose>	
										<c:when test="${counterbirth.get(i) == 365}">
											誕生日おめでとうございます！
											　(post:<c:out value="${ThreadList.get(i).getPostDate()}" />)
											<br><br>
										</c:when>
										<c:otherwise>
											誕生日まであと<c:out value="${counterbirth.get(i)}" />日
											　(post:<c:out value="${ThreadList.get(i).getPostDate()}" />)
											<br><br>
										</c:otherwise>
										</c:choose>
										　　<c:out value="${ThreadList.get(i).getText()}" /><br>
									</a>
								</div>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</div>
				</section>
				
			</div>
		</div>
		<script src="js/main.js"></script>
	</body>
</html>