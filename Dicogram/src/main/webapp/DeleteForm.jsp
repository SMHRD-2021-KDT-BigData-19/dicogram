<%@page import="com.dicogram.domain.Users"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>회원 탈퇴</title>
</head>
<body>
<% 
	Users loginUser = (Users)session.getAttribute("loginUser");
%>
	<h4>회원 탈퇴</h4>
	<form action="UsersDeleteCon" method="post">
		<p>아이디를 입력하세요
		<input type="text" name="chkId">
		<p>비밀번호를 입력하세요.
		<input type="password" id="check_pw" name="chkPw">
		<input type="submit" value="확인">
	</form>	
</body>
</html>