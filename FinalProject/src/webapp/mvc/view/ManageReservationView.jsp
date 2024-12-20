<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <title>Modify or Cancel Reservation</title>
  <link rel="stylesheet" type="text/css" href="../../css/styles.css">
</head>
<body>
<div class="form-container">
  <h2>Modify or Cancel Reservation</h2>

  <%
    String message = (String) request.getAttribute("message");
    if (message != null) {
  %>
  <div class="info-message">
    <%= message %>
  </div>
  <%
    }
  %>

  <form action="../controller/ReservationController.jsp" method="post">
    <label for="reservationId">Reservation ID</label>
    <input type="text" id="reservationId" name="reservationId" placeholder="Enter Reservation ID" required>

    <label for="action">Action</label>
    <select id="action" name="action" required>
      <option value="MODIFY">Modify Reservation</option>
      <option value="CANCEL">Cancel Reservation</option>
    </select>

    <div id="modificationSection" style="display: none;">
      <h3>Modify Reservation Details</h3>

      <label for="adults">Number of Adults</label>
      <input type="number" id="adults" name="adults" min="0">

      <label for="children">Number of Children</label>
      <input type="number" id="children" name="children" min="0">

      <label for="duration">Duration (Hours)</label>
      <input type="number" id="duration" name="duration" min="1">

      <label for="date">Date</label>
      <input type="date" id="date" name="date">

      <div id="courtTypeSection">
        <label for="courtType">Court Type</label>
        <select id="courtType" name="courtType">
          <option value="INDOORS">Indoors</option>
          <option value="OUTDOORS">Outdoors</option>
        </select>
      </div>
    </div>

    <button type="submit">Submit</button>
  </form>
</div>

<script>
  //Some Javascript to fit both functionalities in one JSP
  const actionDropdown = document.getElementById("action");
  const modificationSection = document.getElementById("modificationSection");
  const courtTypeSection = document.getElementById("courtTypeSection");

  // Toggle based on action
  actionDropdown.addEventListener("change", function () {
    const action = actionDropdown.value;
    if (action === "MODIFY") {
      modificationSection.style.display = "block";
    } else {
      modificationSection.style.display = "none";
    }
  });

  // Hide court type if reservation is in a voucher
  <%
      boolean isVoucher = (boolean) request.getAttribute("isVoucher");
      if (isVoucher) {
  %>
  courtTypeSection.style.display = "none";
  <% } %>
</script>
</body>
</html>
