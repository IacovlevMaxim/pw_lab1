<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.CourtDTO" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Courts</title>
    <link rel="stylesheet" type="text/css" href="../../css/styles.css">
</head>
<body>
<div class="form-container">
    <h2>Search Courts</h2>
    <%
        // Display any error message passed from the controller
        String errorMessage = (String) request.getAttribute("message");
        if (errorMessage != null) {
    %>
    <div class="error-message">
        <%= errorMessage %>
    </div>
    <%
        }
    %>
    <form action="../controller/CourtController.jsp" method="post">
        <label for="courtType" style="display: block; text-align: left; margin: 10px 0 5px;">Court Type</label>
        <select id="courtType" name="courtType" required>
            <option value="INDOORS">Indoors</option>
            <option value="OUTDOORS">Outdoors</option>
        </select>

        <label for="searchDate" style="display: block; text-align: left; margin: 10px 0 5px;">Date</label>
        <input type="date" id="searchDate" name="searchDate" required>

        <button type="submit">Search Courts</button>
    </form>

    <%
        // If the list of courts is passed, display the results
        List<CourtDTO> courts = (List<CourtDTO>) request.getAttribute("courts");
        if (courts != null && !courts.isEmpty()) {
    %>
    <div style="margin-top: 20px;">
        <h3>Search Results</h3>
        <%
            for (CourtDTO court : courts) {
        %>
        <div class="booking-card">
            <p><strong>Court ID:</strong> <%= court.getCourtId() %></p>
            <p><strong>Name:</strong> <%= court.getName() %></p>
            <p><strong>Type:</strong> <%= court.getType() ? "Indoors" : "Outdoors" %></p>
            <p><strong>Size:</strong> <%= court.getSize().name() %></p>
            <p><strong>Max Players:</strong> <%= court.getMaxNum() %></p>
            <p><strong>Status:</strong> <%= court.getStatus() ? "Available" : "Not Available" %></p>
        </div>
        <%
            }
        %>
    </div>
    <%
    } else if (courts != null) { // If no courts found but the list is not null
    %>
    <p>No courts found for the selected criteria.</p>
    <%
        }
    %>
</div>
</body>
</html>
