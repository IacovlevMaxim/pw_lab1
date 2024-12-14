<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" type="text/css" href="../../css/styles.css">
</head>
<body>
<div class="form-container">
    <h2>Register</h2>
    <%
        // If some error happens during registering, it will be displayed as following
        // Request must have attribute "message" containing the error message
        String errorMessage = (String) request.getAttribute("message");
        if (errorMessage != null) {
    %>
    <div style="color: red; text-align: center; margin-bottom: 10px;">
        <%= errorMessage %>
    </div>
    <%
        }
    %>
    <form action="../controller/RegisterController.jsp" method="post">
        <input type="text" name="name" placeholder="Full Name" required>
        <input type="email" name="email" placeholder="Email" required>
        <input type="date" id="start" name="birth" value="2023-12-12" min="1970-01-01" max="2018-12-31" />
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">Register</button>
        <div class="link">
            <a href="LoginView.jsp">Have an account? Login!</a>
        </div>
    </form>
</div>
</body>
</html>
