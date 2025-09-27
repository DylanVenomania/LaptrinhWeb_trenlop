<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Forgot Password</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .forgot-card { border: none; border-radius: 15px; }
    </style>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card forgot-card shadow-lg p-4">
                <div class="card-body">
                    <h2 class="text-center mb-4 text-warning">Forgot Password</h2>
                    <form action="<%=request.getContextPath()%>/forgot-password" method="post">
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-warning w-100">Send OTP</button>
                    </form>
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger mt-3 text-center"><%= request.getAttribute("error") %></div>
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