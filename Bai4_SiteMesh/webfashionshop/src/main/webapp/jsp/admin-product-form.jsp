<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="hcmute.vn.model.Product" %>
<%@ page import="hcmute.vn.model.Category" %>
<html>
<head>
    <title>Admin - Product Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container mt-4">
    <h3>Product Form</h3>
    <%
        Product product = (Product) request.getAttribute("product");
        boolean editing = product != null;
        @SuppressWarnings("unchecked")
        List<Category> categories = (List<Category>) request.getAttribute("categories");
    %>
    <form action="<%=request.getContextPath()%>/admin/products" method="post" class="bg-white p-3 rounded shadow-sm">
        <input type="hidden" name="id" value="<%= editing ? product.getId() : "" %>">
        <div class="mb-3">
            <label class="form-label">Category</label>
            <select name="categoryId" class="form-select" required>
                <% for (Category c : categories) { %>
                <option value="<%=c.getId()%>" <%= editing && product.getCategoryId()==c.getId()?"selected":"" %>><%= c.getName() %></option>
                <% } %>
            </select>
        </div>
        <div class="mb-3">
            <label class="form-label">Name</label>
            <input type="text" name="name" class="form-control" value="<%= editing ? product.getName() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Price</label>
            <input type="number" step="0.01" name="price" class="form-control" value="<%= editing ? product.getPrice() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Image URL</label>
            <input type="text" name="imageUrl" class="form-control" value="<%= editing ? product.getImageUrl() : "" %>">
        </div>
        <div class="mb-3">
            <label class="form-label">Description</label>
            <textarea name="description" class="form-control"><%= editing ? product.getDescription() : "" %></textarea>
        </div>
        <button class="btn btn-primary"><%= editing ? "Update" : "Create" %></button>
        <a href="<%=request.getContextPath()%>/admin/products" class="btn btn-secondary">Cancel</a>
    </form>
</div>
</body>
</html>


