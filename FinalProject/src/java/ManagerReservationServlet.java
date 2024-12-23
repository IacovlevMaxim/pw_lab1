package servlet;

import data.DAO.ReservationDAO;
import business.AdultReservationDTO;
import business.FamilyReservationDTO;
import business.ChildrenReservationDTO;
import data.DAO.PackageDAO;
import business.PackageDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class ManagerReservationServlet extends HttpServlet
{
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		String reservationId = request.getParameter("reservationId");
        String action = request.getParameter("action");
        
        if (reservationId == null || reservationId.isEmpty()) 
        {
        	
            request.setAttribute("message", "Reservation ID is required.");
            request.getRequestDispatcher("/mvc/view/ManageReservationView.jsp").forward(request, response);
            return;
            
        }
        
        ReservationDAO res = getReservationById(reservationId);
        
        if (res == null) 
        {
        	
            request.setAttribute("message", "Reservation not found.");
            request.getRequestDispatcher("/mvc/view/ManageReservationView.jsp").forward(request, response);
            return;
        
        }
        
        if ("MODIFY".equals(action)) 
        {

            if (courtType != null) 
            {
            	
                request.setAttribute("message", "Court type cannot be modified for voucher reservations.");
                request.getRequestDispatcher("/mvc/view/ManageReservation.jsp").forward(request, response);
                return;
                
            }
            
            int adults = Integer.parseInt(request.getParameter("adults"));
            int children = Integer.parseInt(request.getParameter("children"));
            int duration = Integer.parseInt(request.getParameter("duration"));
            LocalDate date = LocalDate.parse(request.getParameter("date"));

            res.setAdults(adults);
            res.setChildren(children);
            res.setDuration(duration);
            res.setDate(date);
            
            request.setAttribute("message", "Reservation modified successfully.");
            
        }
        
        else if("CANCEL".equals(action))
        {
        	
        	deleteReservation(res);
            request.setAttribute("message", "Reservation cancelled successfully.");
        	
        }
        
        else
        {
        	
            request.setAttribute("message", "Invalid action selected.");        	
        	
        }
        
        request.getRequestDispatcher("/mvc/view/ManageReservation.jsp").forward(request, response);   
		
	}
	
}