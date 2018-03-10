<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/defaultStyle.css" type="text/css"/>
</head>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<div style="font-size: 20px">
    <c:if test="${not empty sessionScope.infoMsg}">
        <p>${sessionScope.infoMsg}</p>
        <c:set var="infoMsg" value="" scope="session"/>
    </c:if>
</div>
<form method="post">
    <input type="hidden" name="command" value="tasks"/>
    <div>
        <div class="container-fluid">
            <div class="col-md-12"><P><fmt:message key="subscription.topic.student" bundle="${i18n}"/></P></div>
            <%--<table class="table" border="collapse">--%>
            <table class="table table-bordered" border=2px>
                <tr>
                    <th class="col-md-3"><fmt:message key="subscription.form.course" bundle="${i18n}"/></th>
                    <th class="col-md-2"><fmt:message key="subscription.form.start" bundle="${i18n}"/></th>
                    <th class="col-md-2"><fmt:message key="subscription.form.end" bundle="${i18n}"/></th>
                    <th class="col-md-1"><fmt:message key="subscription.form.avg-mark" bundle="${i18n}"/></th>
                    <th class="col-md-1"><fmt:message key="subscription.form.complete" bundle="${i18n}"/></th>
                    <th class="col-md-1"><fmt:message key="subscription.form.status" bundle="${i18n}"/></th>
                    <th class="col-md-2"></th>
                </tr>
                <%--<c:forEach var="subscription" items="${sessionScope.subscriptionList}" varStatus="status">--%>
                <c:forEach var="subscription" items="${requestScope.subscriptionList}" varStatus="status">
                    <tr class="info">
                        <td class="col-md-3">${subscription.linkedCourse}</td>
                        <td class="col-md-2">${subscription.dateStart}</td>
                        <td class="col-md-2">${subscription.dateStop}</td>
                        <td class="col-md-1">${subscription.avgMark}</td>
                        <td class="col-md-1">${subscription.numberCompleted}</td>
                        <td class="col-md-1">${subscription.status}</td>
                        <td class="col-md-2">
                            <button class="button-course" name="subscriptionId" value="${subscription.id}"
                                    formaction="${pageContext.request.contextPath}/frontController">
                                <fmt:message key="subscription.button.tasks" bundle="${i18n}"/>
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>
</form>