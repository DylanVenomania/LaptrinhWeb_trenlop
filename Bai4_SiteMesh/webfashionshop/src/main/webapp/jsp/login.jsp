<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .login-card { border: none; border-radius: 15px; }
        .form-control:focus { border-color: #ff6f61; box-shadow: 0 0 5px rgba(255, 111, 97, 0.5); }
    </style>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card login-card shadow-lg p-4">
                <div class="card-body">
                    <h2 class="text-center mb-4 text-success">Login</h2>
                    <form action="<%=request.getContextPath()%>/login" method="post">
                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <input type="text" name="username" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" name="password" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-success w-100">Login</button>
                    </form>
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger mt-3 text-center"><%= request.getAttribute("error") %></div>
                    <% } %>
                    <p class="mt-3 text-center">
                        Don't have an account? <a href="register.jsp" class="text-decoration-none">Register here</a><br>
                        <a href="forgot.jsp" class="text-decoration-none">Forgot Password?</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>