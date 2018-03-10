<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" uri="/WEB-INF/tags/util/icon.tagx" %>
<%@ taglib prefix="com" tagdir="/WEB-INF/tags/common/" %>
<%--<%@ taglib prefix="com" tagdir="/WEB-INF/tags/common/header-navigation.tagx" %>--%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.css" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/utils.js" type="text/javascript">
        <jsp:text>                                                                                          //!!!!!
    </script>


    <style>
        /*    .navbar {
                    margin-bottom: 0;
                    border-radius: 0;
                }

        /* Add a gray background color and some padding to the footer */
        footer {
            background-color: #00f2f3;
            padding: 50px;
        }
    </style>
    <title>OnlineTraining</title>
    <!--    <title>${title}</title>-->
    <u: file="favicon-32x32.png"/>
</head>
<body>
<com:mytag/>
<div class="jumbotron" style="background-color:aliceblue">
     <div class="container text-center">
        <c:url var="bodyUrl" value="../home.jsp">
        <c:if task="${not empty sessionScope.pagePath}">
            <c:url var="bodyUrl" value="../${sessionScope.pagePath}"/>
        </c:if>
        <%--<jsp:include page="${bodyUrl}"/>--%>
        <c:import url="${bodyUrl}"/>
    </div>
</div>
<%--<jsp:directive.include file="footer.jspx"/>--%>
<%@ include file="footer.jspx"%>

</body>
</html>