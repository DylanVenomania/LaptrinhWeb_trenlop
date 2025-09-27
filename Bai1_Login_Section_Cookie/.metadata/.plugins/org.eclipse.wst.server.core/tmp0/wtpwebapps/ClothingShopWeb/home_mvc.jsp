<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Home - Clothing Shop</title></head>
<body>
<%
    if (session.getAttribute("user") == null) 
    {
        response.sendRedirect("login_mvc.jsp"); 
    }
%>
<h1>Welcome to Clothing Shop, <%= session.getAttribute("user") %>!</h1>
<a href="logout_mvc">Logout</a>
</body>
</html>