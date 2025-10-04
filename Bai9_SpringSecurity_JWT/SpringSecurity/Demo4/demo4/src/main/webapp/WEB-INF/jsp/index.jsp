<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1>Welcome to Home Page</h1>
        <c:if test="${pageContext.request.userPrincipal != null}">
            <p>Hello, ${pageContext.request.userPrincipal.name}!</p>
            <a href="<c:url value="/logout" />" class="btn btn-danger">Logout</a>
        </c:if>
        <c:if test="${pageContext.request.userPrincipal == null}">
            <a href="<c:url value="/login" />" class="btn btn-primary">Login</a>
        </c:if>
    </div>
</body>
</html>