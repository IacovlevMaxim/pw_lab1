<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="es.uco.pw.business.CourtDTO, es.uco.pw.business.enums.CourtSize" %>
<!DOCTYPE html>
<html>
<head>
  <title>Individual Booking</title>
  <link rel="stylesheet" type="text/css" href="../../css/styles.css">
</head>
<body>
<div class="form-container">
  <h2>Make an Individual Booking</h2>

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

    String mapCourtSize(CourtSize size) {
      switch (size) {
        case MINIBASKET: return "Mini Basket";
        case ADULTS: return "Adults";
        case THREE_VS_THREE: return "Three vs Three";
        default: return "Unknown";
      }
    }
  %>

  <form action="../controller/IndividualBookingController.jsp" method="post">
    <input type="hidden" name="userId" value="<%= session.getAttribute("userId") %>">

    <label for="bookingDate" style="display: block; text-align: left; margin: 10px 0 5px;">Date</label>
    <input type="date" id="bookingDate" name="bookingDate" required>

	<label for="duration" style="display: block; text-align: left; margin: 10px 0 5px;">Duration (hours)</label>
    <input type="number" id="duration" name="duration" min="1" required>

    <label for="numParticipants" style="display: block; text-align: left; margin: 10px 0 5px;">Number of Participants</label>
    <input type="number" id="numParticipants" name="numParticipants" min="1" required>

    <label for="courtType" style="display: block; text-align: left; margin: 10px 0 5px;">Court Type</label>
    <select id="courtType" name="courtType" required>
      <option value="INDOORS">Indoors</option>
      <option value="OUTDOORS">Outdoors</option>
    </select>

    <%
      List<CourtDTO> availableCourts = (List<CourtDTO>) request.getAttribute("availableCourts");
      if (availableCourts != null && !availableCourts.isEmpty()) {
    %>
    <label for="courtSelection" style="display: block; text-align: left; margin: 10px 0 5px;">Select a Court</label>
    <select id="courtSelection" name="courtSelection" required>
      <% for (CourtDTO court : availableCourts) { %>
      <option value="<%= court.getCourtId() %>">
        <%= court.getName() %> - <%= mapCourtSize(court.getSize()) %> (Max Players: <%= court.getMaxNum() %>)
      </option>
      <% } %>
    </select>
    <%
      } else {
    %>
    <p>No available courts match the criteria. Try again.</p>
    <%
      }
    %>
    
	<label for="price" style="display: block; text-align: left; margin: 10px 0 5px;">Price (€)</label>
    <input type="number" id="price" name="price" step="0.01" min="0" required>  

    <button type="submit" <%= (availableCourts == null || availableCourts.isEmpty()) ? "disabled" : "" %>>Book Now</button>
  </form>
</div>
</body>
</html>
