<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="hcmute.vn.model.Product" %>
<%@ page import="hcmute.vn.model.Category" %>
<html>
<head>
    <title>Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="row">
        <div class="col-md-3">
            <h5>Categories</h5>
            <ul class="list-group">
                <li class="list-group-item"><a href="<%=request.getContextPath()%>/products">All</a></li>
                <%
                @SuppressWarnings("unchecked")
                    List<Category> categories = (List<Category>) request.getAttribute("categories");
                    if (categories != null)
                    for (Category c : categories) {
                %>
                <li class="list-group-item"><a href="<%=request.getContextPath()%>/products?categoryId=<%=c.getId()%>"><%= c.getName() %></a></li>
                <% } %>
            </ul>
        </div>
        <div class="col-md-9">
            <h3>Products</h3>
            <div class="row">
                <%
                @SuppressWarnings("unchecked")
                    List<Product> products = (List<Product>) request.getAttribute("products");
                    if (products != null)
                    for (Product p : products) {
                %>
                <div class="col-md-4 mb-3">
                    <div class="card h-100">
                        <img src="<%= p.getImageUrl() != null ? p.getImageUrl() : "https://via.placeholder.com/300x200" %>" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title"><%= p.getName() %></h5>
                            <p class="card-text">$<%= p.getPrice() %></p>
                            <a class="btn btn-primary" href="<%=request.getContextPath()%>/products?id=<%=p.getId()%>">View</a>
                        </div>
                    </div>
                </div>
                <% } %>
            </div>
        </div>
    </div>
    <div class="mt-3">
        <a href="<%=request.getContextPath()%>/jsp/success.jsp" class="btn btn-outline-primary">Dashboard</a>
        <a href="<%=request.getContextPath()%>/logout" class="btn btn-outline-danger">Logout</a>
    </div>
</div>
</body>
</html>


