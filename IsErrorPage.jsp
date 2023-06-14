<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어 = errorPage/isErrorPage</title>
</head>
<body>
	<h2>서비스 중 일시적인 에러 발생</h2>
	<p></p>
		오류명: <%= exception.getClass().getName() %> <br />
		오류 메시지: <%= exception.getMessage() %>
</body>
</html>
