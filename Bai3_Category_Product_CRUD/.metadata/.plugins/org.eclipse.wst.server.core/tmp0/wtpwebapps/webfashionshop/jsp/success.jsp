<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome - Web Fashion Shop</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .success-card { border: none; border-radius: 15px; background: #f8f9fa; }
        .btn-logout { background-color: #dc3545; border-color: #dc3545; }
        .btn-logout:hover { background-color: #c82333; border-color: #c82333; }
    </style>
</head>
<body class="bg-light">
<div class="container text-center mt-5">
    <div class="card success-card shadow-lg p-4">
        <h2 class="text-success">Welcome, <%= ((hcmute.vn.model.User) session.getAttribute("user")).getUsername() %>!</h2>
        <p class="lead mt-3">You have logged in successfully.</p>
        <p>Email: <%= ((hcmute.vn.model.User) session.getAttribute("user")).getEmail() %></p>
        <a href="<%=request.getContextPath()%>/logout" class="btn btn-logout text-white mt-3">Logout</a>
        <a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-primary mt-3 me-2">Back to Home</a>
        <% if (((hcmute.vn.model.User) session.getAttribute("user")).getRole()!=null && ((hcmute.vn.model.User) session.getAttribute("user")).getRole().equalsIgnoreCase("admin")) { %>
            <a href="<%=request.getContextPath()%>/admin/categories" class="btn btn-warning mt-3 me-2">Manage Categories</a>
            <a href="<%=request.getContextPath()%>/admin/products" class="btn btn-warning mt-3">Manage Products</a>
        <% } %>
        <a href="<%=request.getContextPath()%>/products" class="btn btn-outline-primary mt-3">Browse Products</a>
    </div>
</div>
</body>
</html>
