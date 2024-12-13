<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Index page</title>
</head>
<body>

<% // IF WE ARE HERE, WE LOG OUT THE USER NO MATTER WHERE THEY COME FROM 
%>

WELCOME TO BASKET OF COURTS, WHAT DO YOU WANT TO DO? 
<br/><br/>

<jsp:setProperty property="emailUser" value="" name="customerBean"/>
<jsp:setProperty property="password" value="" name="customerBean"/>
<a href="/JSPMVC/mvc/controller/loginController.jsp">Log in</a>
<br/>
<a href="/JSPMVC/mvc/controller/registerController.jsp">Register</a>

</body>
</html>
