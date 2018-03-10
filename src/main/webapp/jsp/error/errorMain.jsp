<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>Error Page</title>
</head>
<body>
<div align="center">
    <h1>ONLINE TRAINING</h1>
    <h2>
        Error: ${pageContext.exception} <br><br>
    </h2>
    <h3>
        <c:if test="${not empty requestScope.errorMsg}">
            ${requestScope.errorMsg}
        </c:if>
        <c:if test="${not empty requestScope.errorMsg}">
            Can not connect to the database server.
        </c:if>
    </h3>

    <%--Request from ${pageContext.errorData.requestURI} is failed <br/>--%>
    <%--Servlet name: ${pageContext.errorData.servletName}--%>
    <%--<br/> Status code: ${pageContext.errorData.statusCode} <br/>--%>
    <%--Exception: ${pageContext.exception} <br/>--%>

    <p> You can go to the <a href="${pageContext.request.contextPath}"> home page</a> and start over again.</p>
</div>
<%--Exception stack trace:<br/>--%>
<%--<c:forEach var="trace" items="${pageContext.exception.stackTrace}">--%>
<%--${trace}<br/>--%>
<%--</c:forEach>--%>
<p style="font-size: 100px"> </p>

<footer style="text-align: center">©2018 Demoproject Kastsiuchenka Ihar / Group HTP-9 Java</footer>
</body>
</html>
