<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.db.JoinDB"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	JoinDB joinDB=JoinDB.getInstance();
	
	String id=request.getParameter("id");
	String pw=request.getParameter("pw");
	
	String returns=joinDB.connectionDB(id,pw);
	
	System.out.println(returns);
	
	out.println(returns);
	
%>
</body>
</html>