<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	
	long creationTime = session.getCreationTime();
	String creationTimeStr = dateFormat.format(new Date(creationTime));
	
	long lastTime = session.getLastAccessedTime();
	String lastTimeStr = dateFormat.format(new Date(lastTime));
	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>세션 설정 확인</h2>
	<ul>
		<li>세션 유지 시간: <%= session.getMaxInactiveInterval() %></li>
		<li>세션 id: <%= session.getId() %></li>
		<li>최초요청시간: <%= creationTimeStr %></li>
		<li>마지막요청 시간: <%= lastTimeStr %></li>
		
	</ul>
</body>
</html>
