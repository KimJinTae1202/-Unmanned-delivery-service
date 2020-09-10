<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.db.ConnectDB"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
	ConnectDB connectDB=ConnectDB.getInstance();
	
	String id=request.getParameter("id");
	String pw=request.getParameter("pw");
	String name=request.getParameter("name");
	String email=request.getParameter("eamil");
	
	String returns=connectDB.connectionDB(id,pw,name,email);
	
	System.out.println(returns);
	
	out.println(returns);
	
%>
</body>
</html>