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
        <p>Full Name: <%= ((hcmute.vn.model.User) session.getAttribute("user")).getFullname() != null ? ((hcmute.vn.model.User) session.getAttribute("user")).getFullname() : "Not set" %></p>
        <p>Phone: <%= ((hcmute.vn.model.User) session.getAttribute("user")).getPhone() != null ? ((hcmute.vn.model.User) session.getAttribute("user")).getPhone() : "Not set" %></p>
        <% if (((hcmute.vn.model.User) session.getAttribute("user")).getImageUrl() != null) { %>
            <img src="<%= ((hcmute.vn.model.User) session.getAttribute("user")).getImageUrl() %>" width="100" class="mt-2">
        <% } %>
        <a href="${pageContext.request.contextPath}/profile" class="btn btn-info mt-3">Edit Profile</a>
        <a href="${pageContext.request.contextPath}/logout" class="btn btn-logout text-white mt-3">Logout</a>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-primary mt-3">Back to Home</a>
    </div>
</div>
</body>
</html>