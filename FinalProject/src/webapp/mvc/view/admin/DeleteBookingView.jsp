<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
	<title>Delete Bookings</title>
	<link rel="stylesheet" type="text/css" href="../../../css/styles.css">
</head>
<body>
<div class="form-container">
	<h2>Delete Bookings</h2>

	<%
		String nextPage = "../../index.jsp";
		if (customerBean == null || customerBean.getEmail().equals("")) {
			// Should not be here -> flow jumps to index
            %><jsp:forward page="<%=nextPage%>"></jsp:forward>
	}
	if (customerBean.getType() != true) {
	nextPage = "../controller/MainPageController.jsp"; %>
	<jsp:forward page="<%=nextPage%>"></jsp:forward>
	}

	String success = request.getParameter("success");
	String found = request.getParameter("found");
	String isPackage = request.getParameter("package");

	if (success != null) {
	if (success.equals("true")) {
	%> <p style="color: green; font-weight: bold;">Booking deleted successfully!</p>
	<% } else {
	%> <p class="error-message">ERROR: Could not delete the booking. The booking was not found.</p>
	<% }
		}

		if (found != null && found.equals("false")) {
	%> <p class="error-message">ERROR: Booking not found.</p>
	}

	if (isPackage != null && isPackage.equals("true")) {
	%> <p style="color: blue; font-weight: bold;">This is a package booking, please ensure all associated items are considered before deletion.</p>
	}
	%>

	<form action="${pageContext.request.contextPath}/DeleteBookingServlet" method="post">
		<label for="bookingId">Booking ID:</label>
		<input type="text" name="bookingId" id="bookingId" placeholder="Enter Booking ID" required>
		<br/><br/>

		<button type="submit">Delete Booking</button>

		<div class="link">
			<a href="../../controller/MainPageController.jsp">Go back</a>
		</div>
	</form>
</div>
</body>
</html>
