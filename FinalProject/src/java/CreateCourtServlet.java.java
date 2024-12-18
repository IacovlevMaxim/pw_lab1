package servlet;


import java.io.IOException;

import business.CourtManager;
import business.enums.CourtSize;
import business.exceptions.CourtAlreadyExistsException;
import display.CustomerBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CreateCourtServlet")
public class CreateCourtServlet extends HttpServlet{

	/** Serial ID */
	private static final long serialVersionUID = 4852333670670107286L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		if (customerBean == null || customerBean.getEmail().equals("")) {
			response.sendRedirect(request.getContextPath() + "/mvc/index.jsp");
		}
		
		String name = request.getParameter("name");
		CourtSize size = CourtSize.valueOf(request.getParameter("size"));
		Boolean type = Boolean.valueOf(request.getParameter("usage"));
		Integer maxNum = Integer.parseInt(request.getParameter("maxNum"));
		
		Boolean success = true;
		
		if(maxNum<1) {success=false;}
		else {	
			CourtManager cManager = new CourtManager();
			try {
				cManager.createCourt(name, true, type, size, maxNum);
			} catch (CourtAlreadyExistsException e) {
				success = false;
			}
		}
		
		response.sendRedirect(request.getContextPath() + "/mvc/view/admin/CreateCourtView.jsp?success=" + success);
	}

}
