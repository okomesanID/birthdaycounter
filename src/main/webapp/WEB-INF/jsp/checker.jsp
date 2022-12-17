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
							<li class="gnav-item"><a href="Home">ホーム</a></li>
						</ul>
					</c:when>
					<c:otherwise>
						<ul class="gnav-list">
							<li class="gnav-item"><a href="Logout">ログアウト</a></li>
						</ul>
							<ul class="gnav-list">
							<li class="gnav-item"><a href="Home">ホーム</a></li>
						</ul>
					</c:otherwise>
				</c:choose>
			</nav>
		</section>
		
		<!-- Wrapper -->
		<div id="wrapper">
			<div id="checkermain">
			<div class="formtitle2">
				<p>誕生日から年齢などを計算出来ます</p>
			</div>
				<!-- 日付の入力フォーム -->
				<form action="/birthdaycounter/Checker" method="post">
					<div class="inputform">
						<div class="checker">
							<!-- 生年月日テキストボックス -->
							<section id="three">
								<div class="birth"> 	
									<p>西暦で誕生日を入力してください</p>
									<input type="number" class="y-form-text"name="year" min="1" pattern="^[1-9][0-9]{0,4}$" placeholder="年" required>
									<input type="number"  class="y-form-text"name="month"  min="1" max="12"pattern="^[1-9][0-9]{0,2}$"placeholder="月"required>
									<input type="number"  class="y-form-text"name="day"min="1" max="31" pattern="^[1-9][0-9]{0,2}$"placeholder="日" required>
								</div>
							</section>
							<section id="three">
								<div class="birth"> 	
									<p>西暦で計算日を入力してください</p>
									<input type="number" id="input1" class="y-form-text"name="foryear" min="1" pattern="^[1-9][0-9]{0,4}$"value=""required>
									<input type="number" id="input2" class="y-form-text"name="formonth" min="1" max="12"pattern="^[1-9][0-9]{0,2}$"placeholder="月"required>
									<input type="number" id="input3" class="y-form-text"name="forday"min="1" max="31" pattern="^[1-9][0-9]{0,2}$"placeholder="日"required>
								</div>
							</section>
						</div>
						<!-- 送信ボタン -->
						<div class="submit">
							<input class="b b-back" type ="submit" value="  送信  "><br>
						</div>
						
						<c:if test="${not empty checkDate}">	
							<div class="error"> <p>${checkDate}</p> </div>
						</c:if>
						<c:if test="${not empty usercheck2}">	
							<div class="error"> <p>${usercheck2}</p> </div>
						</c:if>
					</div>
				</form>
				
				<div class="formtitle2"><p><!-- 下線用 --></p></div>
				
				<!-- 結果の出力 -->
				<div class =Result>
					<div class="resultage">
						<p>満年齢：<input type="text" name="ReadOnlyField" class="r-form-text"readonly value=<c:out value= "${Checkage}" />>歳</p>
						<p>数え年：<input type="text" name="ReadOnlyField" class="r-form-text"readonly value=<c:out value= "${Checkage2}" />>歳</p>
						<p>干支：<input type="text" name="ReadOnlyField" class="r-form-text"readonly value=<c:out value= "${eto}" />></p>
						<p>星座：<input type="text" name="ReadOnlyField" class="r-form-text"readonly value=<c:out value= "${seiza}" />></p>
						<p>生まれてからの経過日数：<input type="text" name="ReadOnlyField" class="r-form-text"readonly value=<c:out value= "${progress}" />>日</p>
						<p>誕生日まであと：<input type="text" name="ReadOnlyField" class="r-form-text" readonly value=<c:out value= "${days}" />>日</p>
					</div>
				</div>
			</div>
		</div>
		
		<script src="js/birth.js"></script>
		<script src="js/number.js"></script>
		
		<script>
		var today=new Date(); 
		var toyear = today.getFullYear();
		var tomonth = today.getMonth()+1;
		var today = today.getDate();
		document.getElementById('input1').value = toyear;
		document.getElementById('input2').value = tomonth;
		document.getElementById('input3').value = today;
		</script>
	</body>
</html>