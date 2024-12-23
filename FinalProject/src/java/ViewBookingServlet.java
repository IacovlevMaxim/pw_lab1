package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import data.DAO.ReservationDAO;
import business.factories.Reservation;


/**
 * Servlet implementation class ViewBookingServlet
 */
@WebServlet("/ViewBookingServlet")
public class ViewBookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		ReservationDAO r = new ReservationDAO();
		
		try
		{
			
			List<Reservation> completedReservations = r.getCompletedReservations(startDate, endDate);
			List<Reservation> futureReservations = r.getFutureReservations(startDate, endDate);
			
			request.setAttribute("completedReservations", completedReservations);
			request.setAttribute("futureReservations", futureReservations);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/mvc/view/BookingView.jsp");
			
			dispatcher.forward(request, response);
			
		}
		
		catch (Exception e)
		{
			
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			
		}
		
	}

}
