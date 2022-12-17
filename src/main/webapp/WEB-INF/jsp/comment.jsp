<!-- コメント画面 -->

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
		<!-- hamburger-->
		<jsp:include page="/hamburger.jsp" />
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
						<ul class="gnav-list">
							<li class="gnav-item"><a href="Checker">生まれ年チェッカー</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<nav id="nav">
							<ul class="gnav-list">
								<li class="gnav-item"><a href="Logout">ログアウト</a></li>
							</ul>
							<ul class="gnav-list">
								<li class="gnav-item"><a href="Checker">生まれ年チェッカー</a></li>
							</ul>
						</nav>
					</c:otherwise>
				</c:choose>
			</nav>
		</section>
		
		<div id="wrapper">
			<div class="comment-container">
				<!-- スレッドタイトル -->
				<div class="ThreadTitle"> 
					<strong>
						<c:out value="${ThreadList.get(ThreadID).getName()}" />
						<c:out value="${ThreadList.get(ThreadID).getAge()}" />歳
						<c:choose>	
							<c:when test="${counterbirth.get(ThreadID) == 365}">
								本日が誕生日です！！<br>
							</c:when>
							<c:otherwise>
								誕生日まであと<c:out value="${counterbirth.get(ThreadID)}" />日
								　(post:<c:out value="${ThreadList.get(ThreadID).getPostDate()}" />)<br><br>
							</c:otherwise>
						</c:choose>
					</strong>
					<p>コメント：<c:out value="${ThreadList.get(ThreadID).getText()}" /><p>	
				</div>
					
				<!-- コメントリスト -->
				<div class="CommentList">
					<c:choose>
						<c:when test ="${CommentList.size()-1 < 0}">
							<p>まだコメントがありません。</p>
						</c:when>
						<c:otherwise>
							<div class="comment">
								<!-- 画面にコメントを出力する処理 -->
								<c:forEach var="i" begin="0" end="${CommentList.size()-1}" step="1">
									<p>
										<c:out value="${i+1}" />:
										<c:out value="${CommentList.get(i).getName()}" />
										<c:out value="${CommentList.get(i).getAge()}" />歳
										（<c:out value="${CommentList.get(i).getPostDate()}" />）<br>
										　<c:out value="${CommentList.get(i).getComment()}" />
									</p>
								</c:forEach>
							</div>
						</c:otherwise>
					</c:choose>
				</div>
					
				<!-- コメントフォーム -->
				<div class="CommentForm"> 
					<c:choose>	
						<c:when test ="${logincheck == 0}">
							<p>ログインするとコメントできます</p>
						</c:when>
						<c:when test ="${logincheck == 1}">
							<form action="/birthdaycounter/Comment?ThreadNo=${ThreaNO}" method="post">
								<input type="text" name="comment"class="m-form-text">
								<input type="submit"class="t t-back" value="送信">
							</form>
						</c:when>
					</c:choose>
				</div>
				
				<!-- 削除ボタン -->
				<div class="deleate">
					<c:choose>
						<c:when test="${ThreadList.get(ThreadID).getThread_Id() == loginUser.id}">
							<a href= "/birthdaycounter/Home?action=delete&thread=${ThreaNO}">スレッド削除</a>
						</c:when>
					</c:choose>
				</div>
				
			</div>	
		</div>
	</body>
</html>