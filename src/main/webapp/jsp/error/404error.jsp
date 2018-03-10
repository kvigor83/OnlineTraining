<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>404 Error</title>
</head>
<body>
<div align="center">
<h2>
    Apologize, we could not find the page you were looking for:
</h2>
<h3>${pageContext.errorData.requestURI}</h3>
</div>
<br>

<p align="center"> You can go to the <a href="${pageContext.request.contextPath}"> Home page</a> and start over again.</p>

</body>

</html>
