<%@page import="com.dicogram.domain.Users"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�� Ż��</title>
</head>
<body>
<% 
	Users loginUser = (Users)session.getAttribute("loginUser");
%>
	<h4>ȸ�� Ż��</h4>
	<form action="UsersDeleteCon" method="post">
		<p>���̵� �Է��ϼ���
		<input type="text" name="chkId">
		<p>��й�ȣ�� �Է��ϼ���.
		<input type="password" id="check_pw" name="chkPw">
		<input type="submit" value="Ȯ��">
	</form>	
</body>
</html>