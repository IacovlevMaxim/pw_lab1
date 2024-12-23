<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ page import="java.time.LocalDate" %>
<jsp:useBean id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Home</title>
	<link rel="stylesheet" type="text/css" href="../../css/styles.css">
</head>
<body>
<div class="form-container">
	<%
		// Handle user session and navigation
		String nextPage = "../../index.jsp";
		String messageNextPage = "";
		String infoList = request.getParameter("list");
	%>
	<h2>Welcome, <jsp:getProperty property="email" name="customerBean"/>!</h2>
	<div class="error-message">
		<%= infoList %>
	</div>

	<%
		if (customerBean != null && !customerBean.getEmail().equals("")) {
	%>
	<h3>What do you want to do?</h3>
	<ul class="actions">
		<li><a class="btn" href="../../index.jsp">Log out</a></li>
		<li><a class="btn" href="../controller/ModifyUserController.jsp">Modify my data</a></li>
		<li><a class="btn" href="../view/admin/CreateMaterialView.jsp">Create a new material</a></li>
		<li><a class="btn" href="../view/admin/CreateCourtView.jsp">Create a new court</a></li>
		<li><a class="btn" href="../view/admin/ModifyStatusMaterialView.jsp">Change material status</a></li>
		<li><a class="btn" href="../view/admin/ModifyStatusCourtView.jsp">Change court status</a></li>
		<li><a class="btn" href="../view/admin/AssociateMaterialToCourtView.jsp">Associate materials</a></li>
		<li><a class="btn" href="../view/admin/DeleteBookingsView.jsp">Delete bookings</a></li>
	</ul>
	<%
	} else {
		// Redirect to index if user is not logged in
	%>
	<jsp:forward page="<%=nextPage%>">
		<jsp:param value="<%=messageNextPage%>" name="logout"/>
	</jsp:forward>
	<%
		}
	%>
</div>
</body>
</html>
