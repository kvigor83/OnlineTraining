<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/defaultStyle.css" type="text/css"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<h3><fmt:message key="course.topic.view" bundle="${i18n}"/></h3>
<form method="post" action="${pageContext.request.contextPath}/frontController">
    <input type="hidden" name="command" value="addSubscription"/>
    <div class="container1">
        <c:forEach var="course" items="${requestScope.courseList}" varStatus="status">
            <div class="grid-item">${course.courseName}
                <button class="button-course" name="courseId" value="${course.id}">
                <fmt:message key="course.button.subscribe" bundle="${i18n}"/>
                </button>
            </div>
        </c:forEach>
    </div>
</form>
</body>