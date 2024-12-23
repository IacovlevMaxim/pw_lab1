pacakge servlet;

import data.DAO.CourtDAO;
import business.CourtManager;
import business.enums.CourtSize;
import business.AdultReservationDTO;
import data.DAO.ReservationDAO;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
import java.sql.Date;

public class IndividualBookingServlet extends HttpServlet
{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String courtType = request.getParameter("courtType");
		String bookingDate = request.getParameter("bookingDate");
		
		if(courtType == null || bookingDate == null)
		{
			
			request.setAttribute("message", "Please specify all fields.");
			request.getRequestDispatcher("/mvc/view/IdividualBookingView.jsp").forward(request, response);
			return;
			
		}
		
		java.sql.Date date = java.sql.Date.valueOf(bookingDate);
		boolean ct = courtType.equals("Indoor");
		
		CourtDAO cm = new CourtDAO();
		List<CourtDAO> courts = cm.requestAvailableCourtsByTypeAndDate(ct, date);
		
		request.setAttribute("courts", courts);
		request.getRequestDispatcher("/mvc/view/IndividualBookingView.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String bookingDate = request.getParameter("bookingDate");
        int numParticipants = Integer.parseInt(request.getParameter("numParticipants"));
        String courtType = request.getParameter("courtType");
        String courtName = request.getParameter("courtName");
        String courtSize = request.getParameter("courtSize");
        String courtId = request.getParameter("courtId");
        float price = Float.parseFloat(request.getParameter("price"));
        String userId = request.getParameter("userId");
        int duration = Integer.parseInt(request.getParameter("duration"));
		
		if(bookingDate == null || courtType == null || courtName == null || numParticipants <= 0 || courtSize == null || courtId == null || price < 0.0 || userId == null) 
		{
			
	        request.setAttribute("message", "Invalid data. Please try again.");
	        request.getRequestDispatcher("/individualBookingView.jsp").forward(request, response);
	        return;
	        
	    }
		
		CourtSize size = CourtSize.valueOf(courtSize.toUpperCase());
		
		CourtDAO cm2 = new CourtDAO();
		List<CourtDAO> fitting = cm2.requestFittingAvailableCourts(numParticipants, size);
		
		CourtDAO c = null;
		
		for(CourtDAO crt : fitting)
		{
			
			if(crt.requestCourtByName(courtName) == c)
			{
				
				c = crt;
				break;
				
			}
			
		}
		
		if(c == null)
		{
			
			request.setAttribute("message", "The selected court does not fit the required number of participants.");
	        request.getRequestDispatcher("/mvc/view/IndividualBookingView.jsp").forward(request, response);
	        return;
			
		}
		
		java.sql.Date date = java.sql.Date.valueOf(bookingDate);
		
		c.reserveCourtByName(courtName);
		AdultReservationDTO a = new AdultReservationDTO(userId, date, duration, courtId, price, numParticipants);
		
		boolean success = insertReservation(a);
		
		request.getRequestDispatcher("/mvc/view/IndividualBookingView.jsp").forward(request, response);
		
	}
	
}