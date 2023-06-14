<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		try{
		int myAge = Integer.parseInt(request.getParameter("age")) + 10;
		out.println("10년후내나이" + myAge);
		}
	catch (Exception e) {
		out.println("예외발생: 매개변수 age가 null임.");
	}
	%>
</body>
</html>
