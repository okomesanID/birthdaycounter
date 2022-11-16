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
		<!-- header -->
		<jsp:include page="/header.jsp" />
		
		<!-- スレッドタイトル -->
		<div class="ThreadTitle"> 
			<c:out value="${ThreadList.get(ThreadID-1).getName()}" />さん
			<c:out value="${ThreadList.get(ThreadID-1).getAge()}" />歳
			<c:choose>	
				<c:when test="${counterbirth.get(ThreadID-1) == 365}">
					本日が誕生日です！！<br>
				</c:when>
				<c:otherwise>
					誕生日まであと<c:out value="${counterbirth.get(ThreadID-1)}" />日
					　(post:<c:out value="${ThreadList.get(ThreadID-1).getPostDate()}" />)<br>
				</c:otherwise>
			</c:choose>
			<c:out value="${ThreadList.get(ThreadID-1).getText()}" /><br>
		</div>
		
		<!-- コメントリスト -->
		<div class="CommentList">
			<c:choose>
				<c:when test ="${CommentList.size()-1 < 0}">
					<p>まだコメントがありません。</p>
				</c:when>
				<c:otherwise>
					<!-- 画面にコメントを出力する処理 -->
					<c:forEach var="i" begin="0" end="${CommentList.size()-1}" step="1">
						<p>
							<c:out value="${i+1}" />:
							<c:out value="${CommentList.get(i).getName()}" />さん
							<c:out value="${CommentList.get(i).getAge()}" />歳
							（<c:out value="${CommentList.get(i).getPostDate()}" />）<br>
							<c:out value="${CommentList.get(i).getComment()}" />
						</p>
					</c:forEach>
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
					<form action="/birthdaycounter/Comment?ThreadNo=${ThreadID}" method="post">
						<input type="text" name="comment"><input type="submit" value="送信">
					</form>
				</c:when>
			</c:choose>
		</div>
	
	</body>
</html>