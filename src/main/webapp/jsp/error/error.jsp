<%@ page language="java" contentType="text/html; charset=UTF-8" isErrorPage="true" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>

<html>
<head>
    <%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
    <title>Error Page</title>
</head>
<body>
<div align="center">
    <h2>
        Error: ${pageContext.exception} <br><br>
    </h2>
    <h3>
        <div style="font-size: 20px">
            <c:if test="${empty sessionScope.errorMsg}">
                Can not connect to the database server.
            </c:if>
            <c:if test="${not empty sessionScope.errorMsg}">
                ${sessionScope.errorMsg}
                <c:set var="errorMsg" value="" scope="session"/>
            </c:if>

        </div>
    </h3>

    <%--Request from ${pageContext.errorData.requestURI} is failed <br/>--%>
    <%--Servlet name: ${pageContext.errorData.servletName}--%>
    <%--<br/> Status code: ${pageContext.errorData.statusCode} <br/>--%>
    <%--Exception: ${pageContext.exception} <br/>--%>

    <h4> You can go to the <a href="${pageContext.request.contextPath}"> home page</a> and start over again.</h4>
</div>
<%--Exception stack trace:<br/>--%>
<%--<c:forEach var="trace" items="${pageContext.exception.stackTrace}">--%>
<%--${trace}<br/>--%>
<%--</c:forEach>--%>

</body>
</html>
