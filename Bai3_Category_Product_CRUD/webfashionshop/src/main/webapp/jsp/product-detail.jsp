<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hcmute.vn.model.Product" %>
<html>
<head>
    <title>Product Detail</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <%
        Product p = (Product) request.getAttribute("product");
    %>
    <div class="row">
        <div class="col-md-5">
            <img class="img-fluid rounded" src="<%= p.getImageUrl() != null ? p.getImageUrl() : "https://via.placeholder.com/500x400" %>" alt="">
        </div>
        <div class="col-md-7">
            <h2><%= p.getName() %></h2>
            <h4 class="text-success">$<%= p.getPrice() %></h4>
            <p class="mt-3"><%= p.getDescription() %></p>
            <a href="<%=request.getContextPath()%>/products" class="btn btn-secondary mt-3">Back to Products</a>
        </div>
    </div>
    <div class="mt-3">
        <a href="<%=request.getContextPath()%>/logout" class="btn btn-outline-danger">Logout</a>
    </div>
</div>
</body>
</html>


