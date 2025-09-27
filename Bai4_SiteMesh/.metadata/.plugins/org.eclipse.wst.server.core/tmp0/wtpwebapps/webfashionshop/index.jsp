<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Web Fashion Shop</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
  	<style> 
  	.banner { background : url('https://via.placeholder.com/1200x300') no-repeat center/cover; height: 300px;}
  	.btn-custom { background-color: #ff6f61; border-color: #ff6f61; }
  	.btn-custom:hover { background-color: #e65b52; border-color: #e65b52; }
  	</style>
</head>
<body class ="bg-light">
<div class="container text-center mt-5">
    <h1 class="mb-4 text-primary fw-bold">✨ Welcome to Web Fashion Shop ✨</h1>
    <p class="lead mb-4">Register or login to start shopping !</p>
    <div class="mt-4">
    	<a href="jsp/login.jsp" class="btn btn-primary btn-lg me-3 text-white">Login</a>
    	<a href="jsp/register.jsp" class="btn btn-success btn-lg me-3">Register</a>
    	<a href="products" class="btn btn-outline-primary btn-lg">Browse Products</a>
	</div>
</div>
</body>
</html>