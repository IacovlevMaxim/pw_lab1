<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
	<title>Create Court</title>
	<link rel="stylesheet" type="text/css" href="../../../css/styles.css">
</head>
<body>
<div class="form-container">
	<h2>Create Court</h2>

	<%
		String nextPage = "../../index.jsp";
		if (customerBean == null || customerBean.getEmail().equals("")) {
			// Should not be here -> flow jumps to index
			%>
            <jsp:forward page="<%=nextPage%>"></jsp:forward>
	}
	if (customerBean.getType() != true) {
	nextPage = "../controller/MainPageController.jsp"; %>
	<jsp:forward page="<%=nextPage%>"></jsp:forward>
	}

	String success = request.getParameter("success");
	if (success != null && success.equals("true")) {
	%> <p style="color: green; font-weight: bold;">Court created successfully!</p>
	<% } else if (success != null && !success.equals("true")) { %>
	<p class="error-message">ERROR: A court with that name already exists!</p>
	<% }
	%>

	<form action="${pageContext.request.contextPath}/CreateCourtServlet" method="post">
		<input type="text" name="name" placeholder="Court Name" required>
		<label for="type">Type:</label>
		<select name="type" id="type" required>
			<option value="true">Indoors</option>
			<option value="false" selected>Outdoors</option>
		</select>

		<input type="number" name="maxNum" placeholder="Max Number of Players" min="1" required>

		<label for="size">Size:</label>
		<select name="size" id="size" required>
			<option value="MINIBASKET">Mini-basket</option>
			<option value="ADULTS" selected>Adults</option>
			<option value="THREE_VS_THREE">Three vs. Three</option>
		</select>

		<button type="submit">Create</button>
		<div class="link">
			<a href="../../controller/MainPageController.jsp">Go back</a>
		</div>
	</form>
</div>
</body>
</html>
