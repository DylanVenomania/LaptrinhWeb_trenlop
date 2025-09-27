<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>Login - Clothing Shop</title></head>
<body>
<h1>Login to Clothing Shop</h1>
<form action="login_mvc" method="post">
    Username: <input type="text" name="username" value="${cookie.username.value}"><br>
    Password: <input type="password" name="password" value="${cookie.password.value}"><br>
    <input type="checkbox" name="remember"> Remember Me<br>
    <input type="submit" value="Login">
</form>
<p>${message}</p> 
</body>
</html>