<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean  id="customerBean" scope="session" class="display.CustomerBean"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
    <title>Create court</title>
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
   	if(success!=null && success.equals("true"))
   	{
   		%> Court created successfully!
		<br/>	
		<%
   	} else if(success!=null && !success.equals("true")){
   		%> ERROR: A court with that name already exists!
		<br/>	
		<%
   	}
 
    %>
    <form action="${pageContext.request.contextPath}/CreateCourtServlet" method="post">
        <input type="text" name="name" placeholder="Name" required>
		<br/>
        <label for="type">Type: </label>
		    <select name="type" id="type" required>
		        <option value="true">Indoors</option>
		        <option value="false" selected>Outdoors</option>
		    </select>		
		<br/>	
        <input type="number" name="maxNum" placeholder="Maximum number of players" min=1 required>
		<br/>
        <label for="size">Size: </label>
		    <select name="size" id="size" required>
		        <option value="MINIBASKET">Mini-basket</option>
		        <option value="ADULTS" selected>Adults</option>
		        <option value="THREE_VS_THREE" selected>Three vs. Three</option>
		    </select>			
		<br/>	
		<br/>
        <button type="submit">Create</button>
        <div class="link">
            <a href="../../controller/MainPageController.jsp">Go back</a>
        </div>
    </form>
</div>
</body>
</html>
