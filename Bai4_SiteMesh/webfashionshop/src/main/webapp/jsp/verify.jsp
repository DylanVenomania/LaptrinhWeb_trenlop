<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Verify OTP</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .verify-card { border: none; border-radius: 15px; }
    </style>
</head>
<body class="bg-light">
<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-5">
            <div class="card verify-card shadow-lg p-4">
                <div class="card-body">
                    <h2 class="text-center mb-4 text-info">Verify OTP</h2>
                    <form action="<%=request.getContextPath()%>/verify-otp" method="post">
                        <div class="mb-3">
                            <label class="form-label">Enter OTP</label>
                            <input type="text" name="otp" class="form-control" required>
                        </div>
                        <button type="submit" class="btn btn-info w-100">Verify</button>
                    </form>
                    <% if (request.getAttribute("error") != null) { %>
                        <div class="alert alert-danger mt-3 text-center"><%= request.getAttribute("error") %></div>
                    <% } %>
                    <p class="mt-3 text-center">
                        <a href="forgot.jsp" class="text-decoration-none">Resend OTP</a>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>