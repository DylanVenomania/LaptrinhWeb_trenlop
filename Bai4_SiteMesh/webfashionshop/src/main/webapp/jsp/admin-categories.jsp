<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="hcmute.vn.model.Category" %>
<html>
<head>
    <title>Admin - Categories</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h3>Categories</h3>
        <a href="<%=request.getContextPath()%>/admin/categories?action=new" class="btn btn-primary">New Category</a>
    </div>
    <table class="table table-striped table-bordered bg-white">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Description</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
        @SuppressWarnings("unchecked")
            List<Category> categories = (List<Category>) request.getAttribute("categories");
            if (categories != null)
            for (Category c : categories) {
        %>
        <tr>
            <td><%= c.getId() %></td>
            <td><%= c.getName() %></td>
            <td><%= c.getDescription() %></td>
            <td>
                <a class="btn btn-sm btn-secondary" href="<%=request.getContextPath()%>/admin/categories?action=edit&id=<%=c.getId()%>">Edit</a>
                <a class="btn btn-sm btn-danger" href="<%=request.getContextPath()%>/admin/categories?action=delete&id=<%=c.getId()%>" onclick="return confirm('Delete this category?')">Delete</a>
            </td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <a href="<%=request.getContextPath()%>/admin/products" class="btn btn-outline-secondary">Manage Products</a>
    <a href="<%=request.getContextPath()%>/jsp/success.jsp" class="btn btn-outline-primary">Dashboard</a>
    <a href="<%=request.getContextPath()%>/logout" class="btn btn-outline-danger">Logout</a>
 </div>
</body>
</html>


