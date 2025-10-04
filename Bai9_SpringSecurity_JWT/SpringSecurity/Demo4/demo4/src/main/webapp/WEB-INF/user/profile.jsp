<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1>User Profile</h1>
        <p>Welcome, ${pageContext.request.userPrincipal.name}!</p>
        <a href="<c:url value="/logout" />" class="btn btn-danger">Logout</a>
    </div>
</body>
</html>