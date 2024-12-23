<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
    <title>Create material</title>
    <link rel="stylesheet" type="text/css" href="../../../css/styles.css">
</head>
<body>
<div class="form-container">
    <h2>Create material</h2>
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
   	if(success!=null && success.equals("true"))
   	{
   		%> Material created successfully!
		<br/>	
		<%
   	}
 
    %>
    <form action="${pageContext.request.contextPath}/CreateMaterialServlet" method="post">
        <label for="type">Type: </label>
		    <select name="type" id="type" required>
		        <option value="BALL">Ball</option>
		        <option value="CONE" selected>Cone</option>
		        <option value="BASKET" selected>Basket</option>
		    </select>	
		<br/>
        <label for="usage">Usage: </label>
		    <select name="usage" id="usage" required>
		        <option value="true">Indoors</option>
		        <option value="false" selected>Outdoors</option>
		    </select>	
		<br/>
		<br/>
		<br/>
        <button type="submit">Create</button>
		<br/>
        <div class="link">
            <a href="../../controller/MainPageController.jsp">Go back</a>
        </div>
    </form>
</div>
</body>
</html>
