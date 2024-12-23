<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
    <title>Associate Material to court</title>
    <link rel="stylesheet" type="text/css" href="../../../css/styles.css">
</head>
<body>
<div class="form-container">
    <h2>Associate materials</h2>
   	<%
	String nextPage = "../../index.jsp";
   	if (customerBean == null || customerBean.getEmail().equals("")) {
    	//Should not be here -> flow jumps to index %>
		<jsp:forward page="<%=nextPage%>"></jsp:forward>
    	<%
    }
   	if(customerBean.getType()!=true)
   	{
   		nextPage = "../controller/MainPageController.jsp"; %>
		<jsp:forward page="<%=nextPage%>"></jsp:forward>
    	<%
   	}
   	String success = request.getParameter("success");
   	String mFound = request.getParameter("mfound");
   	String cFound = request.getParameter("cfound");
   	if(success!=null) // COMES FROM THE SERVLET
   	{
   		if(success.equals("true") && mFound.equals("true") && cFound.equals("true")) {
	   		%> Material associated successfully!
			<br/>	
			<%
   		} else if(!mFound.equals("true")) {
  	   		%> ERROR: Material not found.
  			<br/>	
  			<%
   		} else if(!cFound.equals("true")) {
  	   		%> ERROR: Court not found.
  			<br/>	
  			<%
   		} else if(!success.equals("true")) {
  	   		%> ERROR: Could not associate the material to the court.
  			<br/>	
  			<%
   		}
        session.removeAttribute("success");
        session.removeAttribute("mfound");
        session.removeAttribute("cfound");
   	}
 
    %>
    <form action="${pageContext.request.contextPath}/AssociateMaterialToCourtServlet" method="post">
        <input type="text" name="name" placeholder="Name" required>
		<br/>
        <input type="number" name="id" placeholder="ID of the material" required>
		<br/>
		<br/>
		<br/>
        <button type="submit">Save changes</button>
		<br/>
        <div class="link">
            <a href="../../controller/MainPageController.jsp">Go back</a>
        </div>
    </form>
</div>
</body>
</html>
