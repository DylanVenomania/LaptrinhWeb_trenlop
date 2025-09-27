<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hcmute.vn.model.Category" %>
<html>
<head>
    <title>Admin - Category Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <h3>Category Form</h3>
    <%
        Category category = (Category) request.getAttribute("category");
        boolean editing = category != null;
    %>
    <form action="<%=request.getContextPath()%>/admin/categories" method="post" class="bg-white p-3 rounded shadow-sm">
        <input type="hidden" name="id" value="<%= editing ? category.getId() : "" %>">
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control" value="<%= editing ? category.getName() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="description" class="form-control"><%= editing ? category.getDescription() : "" %></textarea>
        </div>
        <button class="btn btn-primary"><%= editing ? "Update" : "Create" %></button>
        <a href="<%=request.getContextPath()%>/admin/categories" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>


