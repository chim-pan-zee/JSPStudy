<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String chkVal = request.getParameter("inactiveToday");	//매개변수 값 얻기
	if (chkVal != null && chkVal.equals("1")) {	//객체가 1이면쿠키생성해서 응답객체에 추가
		Cookie cookie = new Cookie("PopupClose", "off");//쿠키생성
		cookie.setPath(request.getContextPath());	//경로설정
		cookie.setMaxAge(60*60*24);	//유지 기간 설정
		response.addCookie(cookie);	//응답객체추가
		out.println("쿠키 : 하루 동안 열지 않음");		
	}
%>
