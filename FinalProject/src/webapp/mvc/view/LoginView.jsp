<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="../../css/styles.css">
</head>
<body>
<div class="form-container">
    <h2>Login</h2>

    <%
        // If some error happens during logging in, it will be displayed as following
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
    <form action="../controller/LoginController.jsp" method="post">
        <input type="email" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <button type="submit">Login</button>
        <div class="link">
            <a href="RegisterView.jsp">No account? Register!</a>
        </div>
    </form>
</div>
</body>
</html>
