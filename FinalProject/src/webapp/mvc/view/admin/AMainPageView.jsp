<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.time.LocalDate" %>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>Home</title>
    <link rel="stylesheet" type="text/css" href="../../css/styles.css">
</head>
<body>
<%
/* Possible flows:
1) customerBean is logged in (!= null && != "") -> We show options
2) customerBean is not logged in -> Should not be here, we redirect to index.jsp
*/
String nextPage = "../../index.jsp";
String messageNextPage = "";
String infoList = request.getParameter("list");
%>
Welcome <jsp:getProperty property="email" name="customerBean"/>!
<br/>
<br/>
<%

// CASE 1
if (customerBean != null && !customerBean.getEmail().equals("")) {%>
	<%= infoList %> 
	<br/>
	WHAT DO YOU WANT TO DO?
	<br/>
	<br/>
	<a href="../../index.jsp">Log out</a>
	<br/>
	<a href="../controller/ModifyUserController.jsp">Modify my data</a>
	<br/>
	<a href="../view/admin/CreateMaterialView.jsp">Create a new material</a>
	<br/>
	<a href="../view/admin/CreateCourtView.jsp">Create a new court</a>
	<br/>
	<a href="../view/admin/ModifyStatusMaterialView.jsp">Change material status</a>
	<br/>
	<a href="../view/admin/ModifyStatusCourtView.jsp">Change court status</a>
	<br/>
	<a href="../view/admin/AssociateMaterialToCourtView.jsp">Associate materials</a>
	<br/>
	<a href="../view/admin/DeleteBookingsView.jsp">Delete bookings</a>
	
<% 
} else { // CASE 2: SHOULDN'T BE HERE -> GO BACK TO INDEX %> 
	<jsp:forward page="<%=nextPage%>"> 
		<jsp:param value="<%=messageNextPage%>" name="logout"/>
	</jsp:forward>
<%} %>

</body>
</html>
