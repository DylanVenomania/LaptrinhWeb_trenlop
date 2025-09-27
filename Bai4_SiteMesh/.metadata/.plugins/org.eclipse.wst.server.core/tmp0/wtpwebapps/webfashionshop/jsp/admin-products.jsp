<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="hcmute.vn.model.Product" %>
<html>
<head>
    <title>Admin - Products</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Products</h3>
        <a href="<%=request.getContextPath()%>/admin/products?action=new" class="btn btn-primary">New Product</a>
    </div>
    <table class="table table-striped table-bordered bg-white">
        <thead>
        <tr>
            <th>ID</th>
            <th>Category</th>
            <th>Name</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Product> products = (List<Product>) request.getAttribute("products");
            if (products != null)
            for (Product p : products) {
        %>
        <tr>
            <td><%= p.getId() %></td>
            <td><%= p.getCategoryId() %></td>
            <td><%= p.getName() %></td>
            <td><%= p.getPrice() %></td>
            <td>
                <a class="btn btn-sm btn-secondary" href="<%=request.getContextPath()%>/admin/products?action=edit&id=<%=p.getId()%>">Edit</a>
                <a class="btn btn-sm btn-danger" href="<%=request.getContextPath()%>/admin/products?action=delete&id=<%=p.getId()%>" onclick="return confirm('Delete this product?')">Delete</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <a href="<%=request.getContextPath()%>/admin/categories" class="btn btn-outline-secondary">Manage Categories</a>
    <a href="<%=request.getContextPath()%>/jsp/success.jsp" class="btn btn-outline-primary">Dashboard</a>
    <a href="<%=request.getContextPath()%>/logout" class="btn btn-outline-danger">Logout</a>
 </div>
</body>
</html>


