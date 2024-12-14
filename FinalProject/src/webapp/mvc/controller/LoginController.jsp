<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import ="business.UserDTO, data.DAO.UserDAO" %>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<%
  /* Possible flows:
      1) User is already logged in (customerBean != null && customerBean.getEmail() != "") -> Redirect to the dashboard
      2) User is not logged in:
          a) Login form has been submitted (parameters in request -> validate credentials)
          b) Login form not submitted -> Redirect to login view
  */
// Default next page
  String nextPage = "LoginView.jsp";
  String messageNextPage = "";

// Case 1: User is already logged in
  if (customerBean != null && customerBean.getEmail() != null && !customerBean.getEmail().isEmpty()) {
    // Redirect to the appropriate dashboard
    nextPage = "MainPageView.jsp";
  } else {
    // Case 2: User is not logged in
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    // Case 2.a: Form has been submitted, validate credentials
    if (email != null && password != null) {
      UserDAO userDAO = new UserDAO();
      UserDTO user = userDAO.requestUserByEmail(email);

      if (user != null && user.getPassword().equals(password)) {
        // Valid credentials, set the session userBean properties
        %>
          <jsp:setProperty name="userBean" property="email" value="<%=email%>" />
          <jsp:setProperty name="userBean" property="password" value="<%=password%>" />
          <jsp:setProperty name="userBean" property="type" value="<%=user.getType()%>" />
        <%
        // Redirect to the appropriate dashboard
        nextPage = "MainPageView.jsp";
      } else {
        // Invalid credentials
        messageNextPage = "Invalid email or password. Please try again.";
        nextPage = "LoginView.jsp";
      }
    } else {
      // Case 2.b: No parameters, first access to the login page
      nextPage = "LoginView.jsp";
    }
  }
%>
<jsp:forward page="<%=nextPage%>">
  <jsp:param name="message" value="<%=messageNextPage%>" />
</jsp:forward>
