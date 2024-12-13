<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="data.DAO.UserDAO,data.DAO.PlayerDAO,data.DAO.ReservationDAO,display.CustomerBean,business.UserDTO" %>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Controller for the main page</title>
</head>
<body>
<%
/* Possible flows:
1) customerBean is logged in (!= null && != "") -> We redirect to MainPageView.jsp
2) customerBean is not logged in -> Should not be here, we redirect to index.jsp
*/

String nextPage = "../../index.jsp"; // default next page (for case 1)
String adminList =null;
String firstBooking=null; 
String nextBooking=null; 

// Case 1
if (customerBean != null && !customerBean.getEmail().equals("")) {
	nextPage = "../view/MainPageView.jsp";
	if(customerBean.getType()==false) // PARAMETERS FOR THE CLIENT
	{
		String email = customerBean.getEmail();
		
		PlayerDAO pDAO = new PlayerDAO();
		ReservationDAO rDAO = new ReservationDAO();
		int userId = pDAO.getPlayerId(email);
		
		firstBooking = rDAO.getFirstReservationByUserId(userId).getDate().toString();
		nextBooking = rDAO.getNextReservationByUserId(userId).getDate().toString();
	}
	else { //PARAMETERS FOT THE ADMIN
		UserDAO uDAO = new UserDAO();
		PlayerDAO pDAO = new PlayerDAO();
		ReservationDAO rDAO = new ReservationDAO();
		String fBooking = null;
		Integer reservationsNumber=0;
		adminList = "";
		adminList+="         USER                 FIRST BOOKING               COMPLETED BOOKINGS\n";
		for(UserDTO u : uDAO.requestAllUsers())
		{
			int userId = pDAO.getPlayerId(u.getEmail());
			fBooking = rDAO.getFirstReservationByUserId(userId).getDate().toString();
			adminList+=u.getEmail() + "      ";
			adminList+=fBooking + "          ";
			reservationsNumber=rDAO.getReservationByUserId(userId).size();
			adminList+=reservationsNumber + "      \n";
		}
	}
}
	
%>
<jsp:forward page="<%=nextPage%>"> 
	<jsp:param value="<%=firstBooking%>" name="firstBooking"/>
	<jsp:param value="<%=nextBooking%>" name="nextBooking"/>
	<jsp:param value="<%=adminList%>" name="list"/>
</jsp:forward>

</body>
</html>
