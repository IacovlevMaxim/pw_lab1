<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Modify User</title>
    <link rel="stylesheet" type="text/css" href="../../css/styles.css">
</head>
<body>
<div class="form-container">
    <h2>Modify User Data</h2>
    <%
        // If some error happens during modifying the user, it will be displayed as following
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
    <form action="../controller/ModifyUserController.jsp" method="post">
        <%-- Input fields' value should be initially set to that from the Bean        --%>
        <input type="text" name="name" placeholder="Full Name" value="" required>

        <input type="email" name="email" placeholder="Email" readonly>

        <label for="birth" style="display: block; text-align: left; margin: 10px 0 5px;">Birth Date</label>
        <input type="date" id="birth" name="birth" value="2023-12-12" min="1970-01-01" max="2018-12-31" required/>

        <input type="password" name="password" placeholder="Password" required>

        <div style="white-space: nowrap;display:inline">
            <label for="type">Type</label>
            <%-- Should be set to 'checked' if is admin            --%>
            <input type="checkbox" style="width: 10%" id="type" readonly>
        </div>
        <button type="submit">Modify</button>
        <div class="link">
            <a href="index.jsp">Go to Homepage</a>
        </div>
    </form>
</div>
</body>
</html>
