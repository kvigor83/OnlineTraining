<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <title>Home</title>
    <style>
        p {
            border: 2px solid red;
            padding: 10px;
        }
    </style>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<c:if test="${empty user}">
    <div class="container text-center">
        <p><fmt:message key="homepage.message.need" bundle="${i18n}"/><a
                href="${pageContext.request.contextPath}/frontController?command=login"><fmt:message
                key="header.login" bundle="${i18n}"/></a><fmt:message key="homepage.message.or" bundle="${i18n}"/> <a
                href="${pageContext.request.contextPath}/frontController?command=registration"><fmt:message
                key="header.singout" bundle="${i18n}"/></a><fmt:message key="homepage.message.for" bundle="${i18n}"/>
        </p>
    </div>
</c:if>
<div align="left">
    <H2>Курсы</H2>
    <div class="col-md-4">
        <H3>Java</H3>
        <ul>
            <li>Java - Основы</li>
            <li>Java - Средний уровень</li>
            <li>Java - Эксперт</li>
            <li>ООП в Java</li>
            <li>Паттерны проектирования (Java)</li>
        </ul>
    </div>
    <div class="col-md-4">
        <H3>Java EE</H3>
        <ul>
            <li>Servlets</li>
            <li>JSP / Servlets</li>
            <li>JDBC</li>
            <li>Spring Framework</li>
        </ul>
    </div>
    <div class="col-md-4">
        <H3>Веб технологии</H3>
        <ul>
            <li>HTML - Основы</li>
            <li>HTML - Средний уровень</li>
            <li>CSS - Основы</li>
            <li>jQuery - Основы</li>
            <li>JavaScript - Основы</li>
            <li>Bootstrap3</li>
        </ul>
    </div>
</div>

</body>
