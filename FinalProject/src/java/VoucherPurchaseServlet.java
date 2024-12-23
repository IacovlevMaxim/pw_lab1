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
import java.util.UUID;

@WebServlet("/VoucherPurchaseServlet")
public class VoucherPurchaseServlet extends HttpServlet
{
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String reservationType = request.getParameter("reservationType");
        String courtType = request.getParameter("courtType");
        
        if(email == null || reservationType == null || courtType == null)
		{
			
			request.setAttribute("message", "Please specify all fields.");
			request.getRequestDispatcher("/mvc/view/VoucherPurchaseView.jsp").forward(request, response);
			return;
			
		}
        
        List<ReservationDAO> r = new ArrayList<>();
        
        for(int i = 1; i <= 5; i++) 
        {
        	            
            String date = request.getParameter("reservation[" + i + "][date]");
            String expDate = request.getParameter("reservation[" + i + "][expDate]");
            String durationStr = request.getParameter("reservation[" + i + "][duration]");
            String courtId = request.getParameter("reservation[courtId]");
            String priceStr = request.getParameter("reservation[price]");
            String adultsStr = request.getParameter("reservation[" + i + "][adults]");
            String childrenStr = request.getParameter("reservation[" + i + "][children]");
            
            if(date == null || durationStr == null || courtId == null || priceStr == null || adultsStr == null || childrenStr == null)
            {
            	
            	request.setAttribute("message", "Please specify all fields.");
    			request.getRequestDispatcher("/mvc/view/VoucherPurchaseView.jsp").forward(request, response);
    			return;
            	
            }
            
            int duration = Integer.parseInt(durationStr);
            double price = Double.parseDouble(priceStr);
            int adults = Integer.parseInt(adultsStr);
            int children = Integer.parseInt(childrenStr);
            
            ReservationDAO rdao = new ReservationDAO();
            
            if(reservationType.equals("ADULT"))
            {
            	
            	AdultReservationDTO a = new AdultReservationDTO(email, date, duration, courtId, price, adults);
            	boolean success = rdao.insertReservation(a);
            	
            }
            
            else if(reservationType.equals("CHILDREN"))
            {
            	
            	ChildrenReservationDTO c = new ChildrenReservationDTO(email, date, duration, courtId, price, children);
            	boolean success = rdao.insertReservation(c);
            	
            }
            
            else if(reservationType.equals("FAMILY"))
            {
            	
            	FamilyReservationDTO f = new FamilyReservationDTO(email, date, duration, courtId, price, children, adults);
            	boolean success = rdao.insertReservation(f);
            	
            }
        
        }
        
        String voucherId = UUID.randomUUID().toString();
        
        PackageDTO p = new PackageDTO(voucherId, email, date, expDate);
        PackageDAO pdao = new PackageDAO();
        pdao.addNewPackage(p);
        
        request.getRequestDispatcher("/mvc/view/VoucherPurcahseView.jsp").forward(request, response);
	
}

