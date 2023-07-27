<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>로그인 페이지</h2>
	<span style="color: red; font-size: 1.2em;">
		<%= request.getAttribute("LoginErrMsg") == null ? "" : request.getAttribute("LoginErrMsg") %>
	</span>
	<%
		if (session.getAttribute("UserId") == null) {
	%>
	<script>
		function validateForm(form) {
			if (!form.user_id.value) {
				alert("아이디입력");
				return false;	
			}
			if (form.user_pw.value == "") {
				alert("비번입력")''
			}
		}
	</script>
	<form action="LoginProcess.jsp" method="post" name="LoginForm"
	onsubmit="return validateForm(this);">
		아이디: <input type="text" name="user_id" /><br />
		비번: <input type="text" name="user_pw" /><br />
		<input type="submit" value="로그인하기" />
	</form>
	<%
		} else {
	%>
		<%= session.getAttribute("userName") %> 회원아 로그인 했다 <br />
		<a href="Logout.jsp">[로그아웃]</a>
	<%
		}
	%>
</body>
</html>
