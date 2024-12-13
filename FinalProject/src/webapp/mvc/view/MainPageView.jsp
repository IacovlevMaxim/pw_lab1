<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.time.LocalDate" %>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View for the main page</title>
</head>
<body>
<%
/* Possible flows:
1) customerBean is logged in (!= null && != "") -> We show options
2) customerBean is not logged in -> Should not be here, we redirect to index.jsp
*/
String nextPage = "../../index.jsp";
String firstBooking = request.getParameter("firstBooking");
String nextBooking = request.getParameter("nextBooking");
String infoList = request.getParameter("adminList");
%>
Welcome <jsp:getProperty property="email" name="customerBean"/>!
<br/>
<br/>
<%

// CASE 1
if (customerBean != null && !customerBean.getEmail().equals("")) {
	if(customerBean.getType()==false) // CLIENT
	{
		LocalDate date = LocalDate.now();
		String today = date.toString();
		%>
		Today is <%= today %>.<br/>
		<%if(nextBooking!=null) // The user has a next and first reservation
		{%>
			Your first ever reservation was on the <%= firstBooking %>.<br/>
			Remember! Your next reservation is on the <%= nextBooking %>.<br/>
		<%}
		else if(firstBooking!=null) // The user has not ongoing reservations, but have made at least one
		{%>
			Your first ever reservation was on the <%= firstBooking %>.<br/>
			You have no ongoing reservations right now.<br/>
		<%}
		else // The user has no reservations
		{%>
			You have not yet made a reservation.<br/>
		<%}
	}
	else // ADMINISTRATOR
	{
		%> <%= infoList %> <%
	} %>
	
	WHAT DO YOU WANT TO DO?
	<br/>
	<br/>
	<a href="../../index.jsp">Log out</a>
	<br/>
	<a href="/FinalProject/src/main/webapp/mvc/controller/modifyController.jsp">Register</a>
	
<% 
	
} else { // SHOULDN'T BE HERE %> 
	<jsp:forward page="<%=nextPage%>"> </jsp:forward>
<%} %>

</body>
</html>
