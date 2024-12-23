pacakge servlet;

import data.DAO.CourtDAO;
import business.CourtManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/searchCourt")
public class SearchCourtServlet extends HttpServlet
{
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		
		String courtType = request.getParameter("courtType");
		String searchDate = request.getParameter("searchDate");

		if(courtType == null || searchDate == null || courtType.isEmpty() || searchDate.isEmpty())
		{
			
			request.setAttribute("message", "Both court type and date are required.");
			request.getRequestDispatcher("/mvc/view/SearchCourtView.jsp").forward(request, response);
			return;
			
		}
		
		java.sql.Date date = java.sql.Date.valueOf(searchDate);
		boolean ct = courtType.equals("Indoor");
		
		CourtManager cm = new CourtManager();
		List<CourtDAO> courts = cm.requestAvailableCourtsByTypeAndDate(ct, date);
		
		if(courts == null || courts.isEmpty())
		{
			
			request.setAttribute("message", "No courts found for the selected criteria.");
			
		}
		
		request.setAttribute("courts", courts);
		
		request.getRequestDispatcher("/mvc/view/SearchCourtView.jsp").forward(request, response);
		
	}
	
}