<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Purchase Booking Voucher</title>
  <link rel="stylesheet" type="text/css" href="../../css/styles.css">
</head>
<body>
<div class="form-container">
  <h2>Purchase a Booking Voucher</h2>

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

  <form action="../controller/VoucherController.jsp" method="post">
    <!-- Email Input -->
    <label for="email" style="display: block; text-align: left; margin: 10px 0 5px;">Email</label>
    <input type="email" id="email" name="email" placeholder="Enter your email" required>

    <!-- Reservation Type -->
    <label for="reservationType" style="display: block; text-align: left; margin: 10px 0 5px;">Reservation Type</label>
    <select id="reservationType" name="reservationType" required>
      <option value="ADULT">Adult</option>
      <option value="CHILDREN">Children</option>
      <option value="FAMILY">Family</option>
    </select>

    <!-- Court Type -->
    <label for="courtType" style="display: block; text-align: left; margin: 10px 0 5px;">Court Type</label>
    <select id="courtType" name="courtType" required>
      <option value="INDOORS">Indoors</option>
      <option value="OUTDOORS">Outdoors</option>
    </select>

    <!-- Individual Reservation Details -->
    <h3>Individual Reservations</h3>
    <% for (int i = 1; i <= 5; i++) { %>
    <fieldset style="margin-bottom: 20px; border: 1px solid #ccc; padding: 10px; border-radius: 5px;">
      <legend>Reservation <%= i %></legend>

      <!-- Date -->
      <label for="date<%= i %>" style="display: block; text-align: left; margin: 10px 0 5px;">Date</label>
      <input type="date" id="date<%= i %>" name="reservation[<%= i %>][date]" required>

      <!-- Duration -->
      <label for="duration<%= i %>" style="display: block; text-align: left; margin: 10px 0 5px;">Duration (Hours)</label>
      <input type="number" id="duration<%= i %>" name="reservation[<%= i %>][duration]" min="1" required>

      <!-- Number of Adults -->
      <label for="adults<%= i %>" style="display: block; text-align: left; margin: 10px 0 5px;">Number of Adults</label>
      <input type="number" id="adults<%= i %>" name="reservation[<%= i %>][adults]" min="0" required>

      <!-- Number of Children -->
      <label for="children<%= i %>" style="display: block; text-align: left; margin: 10px 0 5px;">Number of Children</label>
      <input type="number" id="children<%= i %>" name="reservation[<%= i %>][children]" min="0" required>
    </fieldset>
    <% } %>

    <!-- Submit Button -->
    <button type="submit">Purchase Voucher</button>
  </form>
</div>
</body>
</html>
