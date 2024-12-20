<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.time.LocalDate, es.uco.pw.business.factories.Reservation" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Reservations</title>
    <link rel="stylesheet" type="text/css" href="../../css/styles.css">
</head>
<body>
<div class="form-container">
    <h2>View Reservations</h2>
    <%
        // If there's an error message, display it
        String errorMessage = (String) request.getAttribute("message");
        if (errorMessage != null) {
    %>
    <div style="color: red; text-align: center; margin-bottom: 10px;">
        <%= errorMessage %>
    </div>
    <%
        }

        // Retrieve the list of reservations from the controller
        List<Reservation> reservations = (List<Reservation>) request.getAttribute("reservations");
        LocalDate today = LocalDate.now();
    %>
    <form action="../controller/ReservationController.jsp" method="post">
        <label for="startDate" style="display: block; text-align: left; margin: 10px 0 5px;">Start Date</label>
        <input type="date" id="startDate" name="startDate" required>

        <label for="endDate" style="display: block; text-align: left; margin: 10px 0 5px;">End Date</label>
        <input type="date" id="endDate" name="endDate" required>

        <button type="submit">Find Reservations</button>
    </form>

    <%
        if (reservations != null && !reservations.isEmpty()) {
    %>
    <div style="margin-top: 20px;">
        <h3>Completed Reservations</h3>
        <%
            boolean hasCompletedReservations = false;
            for (Reservation reservation : reservations) {
                if (reservation.getDate().isBefore(today)) { // Compare using LocalDate's isBefore()
                    hasCompletedReservations = true;
        %>
        <div class="booking-card">
            <p><strong>Date:</strong> <%= reservation.getDate() %></p>
            <p><strong>Duration:</strong> <%= reservation.getDuration() %> hours</p>
            <p><strong>Court:</strong> <%= reservation.getCourtId() %></p>
            <p><strong>Price:</strong> $<%= reservation.getPrice() %> (Discount: $<%= reservation.getDiscount() %>)</p>
            <p><strong>Session:</strong> <%= reservation.getSessionNumber() %></p>
<%--            <p><strong>Adults:</strong> <%= reservation.getAdultNumber() %>, <strong>Children:</strong> <%= reservation.getChildrenNumber() %></p>--%>
        </div>
        <%
                }
            }
            if (!hasCompletedReservations) {
        %>
        <p>No completed reservations found.</p>
        <%
            }
        %>
    </div>

    <div style="margin-top: 20px;">
        <h3>Future Reservations</h3>
        <%
            boolean hasFutureReservations = false;
            for (Reservation reservation : reservations) {
                if (!reservation.getDate().isBefore(today)) { // Compare using LocalDate's isBefore()
                    hasFutureReservations = true;
        %>
        <div class="booking-card">
            <p><strong>Date:</strong> <%= reservation.getDate() %></p>
            <p><strong>Duration:</strong> <%= reservation.getDuration() %> hours</p>
            <p><strong>Court:</strong> <%= reservation.getCourtId() %></p>
            <p><strong>Price:</strong> $<%= reservation.getPrice() %> (Discount: $<%= reservation.getDiscount() %>)</p>
            <p><strong>Session:</strong> <%= reservation.getSessionNumber() %></p>
            <%--            <p><strong>Adults:</strong> <%= reservation.getAdultNumber() %>, <strong>Children:</strong> <%= reservation.getChildrenNumber() %></p>--%>
        </div>
        <%
                }
            }
            if (!hasFutureReservations) {
        %>
        <p>No future reservations found.</p>
        <%
            }
        %>
    </div>
    <%
    } else if (reservations != null) {
    %>
    <p>No reservations found in the specified range.</p>
    <%
        }
    %>
</div>
</body>
</html>
