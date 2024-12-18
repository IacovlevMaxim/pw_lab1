package servlet;


import java.io.IOException;

import business.CourtManager;
import business.enums.MaterialStatus;
import business.enums.MaterialType;
import display.CustomerBean;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/CreateMaterialServlet")
public class CreateMaterialServlet extends HttpServlet{

	/** Serial ID */
	private static final long serialVersionUID = 4852333670670107286L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		CustomerBean customerBean = (CustomerBean) session.getAttribute("customerBean");
		if (customerBean == null || customerBean.getEmail().equals("")) {
			response.sendRedirect(request.getContextPath() + "/mvc/index.jsp");
		}
		
		MaterialType type = MaterialType.valueOf(request.getParameter("type"));
		Boolean usage = Boolean.valueOf(request.getParameter("usage"));
		
		
		CourtManager cManager = new CourtManager();
		cManager.createMaterial(type, usage, MaterialStatus.AVAILABLE);
		
		response.sendRedirect(request.getContextPath() + "/mvc/view/admin/CreateMaterialView.jsp?success=true");
	}

}
