<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Reset Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .reset-card { border: none; border-radius: 15px; }
    </style>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card reset-card shadow-lg p-4">
                <div class="card-body">
                    <h2 class="text-center mb-4 text-primary">Reset Password</h2>
                    <form action="<%=request.getContextPath()%>/reset-password" method="post">
                        <input type="hidden" name="email" value="<%= request.getAttribute("email") %>">
                        <div class="mb-3">
                            <label class="form-label">New Password</label>
                            <input type="password" name="newPassword" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">Reset Password</button>
                    </form>
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger mt-3 text-center"><%= request.getAttribute("error") %></div>
                    <% } %>
                    <% if (request.getAttribute("success") != null) { %>
                        <div class="alert alert-success mt-3 text-center"><%= request.getAttribute("success") %></div>
                    <% } %>
                    <p class="mt-3 text-center">
                        <a href="login.jsp" class="text-decoration-none">Back to Login</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>