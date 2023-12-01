<%@page import="com.dicogram.domain.dUsers"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	dUsers loginMember = (dUsers)session.getAttribute("test1");
	if(loginMember != null) {%>
		<h><%= loginMember.getEmail()%></h>
	<%}
	%>
	<form action="test" method="post">
		<input type="text" name="id">
		<input type="password" name="pw">
		<button type="submit">로그인</button>
	</form>
</body>
</html>