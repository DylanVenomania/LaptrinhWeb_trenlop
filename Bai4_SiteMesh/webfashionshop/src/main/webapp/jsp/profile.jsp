<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-5">
    <h2 class="text-center mb-4">User Profile</h2>
    <% if (request.getAttribute("error") != null) { %>
        <div class="alert alert-danger text-center"><%= request.getAttribute("error") %></div>
    <% } %>
    <% if (request.getAttribute("success") != null) { %>
        <div class="alert alert-success text-center"><%= request.getAttribute("success") %></div>
    <% } %>
    <form action="${pageContext.request.contextPath}/profile" method="post" enctype="multipart/form-data">
        <div class="mb-3">
            <label class="form-label">Username</label>
            <input type="text" class="form-control" value="<%= ((hcmute.vn.model.User) session.getAttribute("user")).getUsername() %>" readonly>
        </div>
        <div class="mb-3">
            <label class="form-label">Full Name</label>
            <input type="text" name="fullname" class="form-control" value="<%= ((hcmute.vn.model.User) session.getAttribute("user")).getFullname() != null ? ((hcmute.vn.model.User) session.getAttribute("user")).getFullname() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Phone</label>
            <input type="text" name="phone" class="form-control" value="<%= ((hcmute.vn.model.User) session.getAttribute("user")).getPhone() != null ? ((hcmute.vn.model.User) session.getAttribute("user")).getPhone() : "" %>" required>
        </div>
        <div class="mb-3">
            <label class="form-label">Image</label>
            <input type="file" name="image" class="form-control">
            <% if (((hcmute.vn.model.User) session.getAttribute("user")).getImageUrl() != null) { %>
                <img src="<%= ((hcmute.vn.model.User) session.getAttribute("user")).getImageUrl() %>" width="100">
            <% } %>
        </div>
        <button type="submit" class="btn btn-primary w-100">Update Profile</button>
    </form>
    <a href="${pageContext.request.contextPath}/jsp/success.jsp" class="btn btn-secondary mt-3">Back</a>
</div>
</body>
</html>