<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
	<title>Modify Material Status</title>
	<link rel="stylesheet" type="text/css" href="../../../css/styles.css">
</head>
<body>
<div class="form-container">
	<h2>Modify Material Status</h2>

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
	String cFound = request.getParameter("cfound");
	String mFound = request.getParameter("mfound");

	if (success != null) {
	if (success.equals("true") && mFound.equals("true") && cFound.equals("true")) {
	%> <p style="color: green; font-weight: bold;">Material status changed successfully!</p>
	<% } else if (!mFound.equals("true")) {
	%> <p class="error-message">ERROR: Material not found.</p>
	<% } else if (!cFound.equals("true")) {
	%> <p class="error-message">ERROR: Court not found.</p>
	<% } else if (!success.equals("true")) {
	%> <p class="error-message">ERROR: Could not change the status of the material.</p>
	<% }
		session.removeAttribute("success");
		session.removeAttribute("cfound");
		session.removeAttribute("mfound");
		}
	%>

	<form action="${pageContext.request.contextPath}/ModifyStatusMaterialServlet" method="post">
		<input type="number" name="id" placeholder="ID of the material" required>
		<br/>

		<label for="status">Status: </label>
		<select name="status" id="status" required>
			<option value="AVAILABLE" selected>Available</option>
			<option value="RESERVED">Reserved</option>
			<option value="BAD_CONDITION">In bad condition</option>
		</select>
		<br/><br/>

		<label for="name">Name of the court (Only required if reserving the material): </label>
		<br/>
		<input type="text" name="name" placeholder="Court name">
		<br/><br/>

		<button type="submit">Modify</button>

		<div class="link">
			<a href="../../controller/MainPageController.jsp">Go back</a>
		</div>
	</form>
</div>
</body>
</html>
