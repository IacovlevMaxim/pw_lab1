<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
    <title>Modify Court Status</title>
    <link rel="stylesheet" type="text/css" href="../../../css/styles.css">
</head>
<body>
<div class="form-container">
    <h2>Create court</h2>
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
   	String found = request.getParameter("found");
   	if(success!=null) {
   		if(success.equals("true") && found.equals("true"))
	   	{
	   		%> Court modified successfully!
			<br/>	
			<%
	   	} else if(!found.equals("true")){
	   		%> ERROR: Court not found.
			<br/>	
			<%
	   	} else if(!success.equals("true")){
	   		%> ERROR: Could not modify the court.
			<br/>	
			<%
	   	}
        session.removeAttribute("success");
        session.removeAttribute("found");
   	}
 
    %>
    <form action="${pageContext.request.contextPath}/ModifyStatusCourtServlet" method="post">
        <input type="text" name="name" placeholder="Name of the court" required>
		<br/>
        <label for="status">Status: </label>
		    <select name="status" id="status" required>
		        <option value="true" selected>Available</option>
		        <option value="false">Reserved</option>
		    </select>		
		<br/>	
		<br/>
        <button type="submit">Modify</button>
        <div class="link">
            <a href="../../controller/MainPageController.jsp">Go back</a>
        </div>
    </form>
</div>
</body>
</html>
