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
<div class="col-md-12"><P><fmt:message key="subscription.topic.tutor" bundle="${i18n}"/></P></div>
<form method="post">
    <input type="hidden" name="command" value="tasks"/>
    <table class="table table-bordered">
        <tr>
            <th width="20%"><fmt:message key="subscription.form.student" bundle="${i18n}"/></th>
            <th width="25%"><fmt:message key="subscription.form.course" bundle="${i18n}"/></th>
            <th width="10%"><fmt:message key="subscription.form.start" bundle="${i18n}"/></th>
            <th width="10%"><fmt:message key="subscription.form.end" bundle="${i18n}"/></th>
            <th width="5%"><fmt:message key="subscription.form.avg-mark" bundle="${i18n}"/></th>
            <th width="5%"><fmt:message key="subscription.form.complete" bundle="${i18n}"/></th>
            <th width="10%"><fmt:message key="subscription.form.status" bundle="${i18n}"/></th>
            <th width="15%"></th>

        </tr>
        <%--<c:forEach var="subscription" items="${sessionScope.subscriptionList}" varStatus="status">--%>
        <c:forEach var="subscription" items="${requestScope.subscriptionList}" varStatus="status">
            <tr class="info">
                <td width="20%">${subscription.linkedStudent}</td>
                <td width="25%">${subscription.linkedCourse}</td>
                <td width="10%">${subscription.dateStart}</td>
                <td width="10%">${subscription.dateStop}</td>
                <td width="5%">${subscription.avgMark}</td>
                <td width="5%">${subscription.numberCompleted}</td>
                <td width="10%">${subscription.status}</td>
                <td width="15%">
                    <button class="button-course" name="subscriptionId" value="${subscription.id}"
                            formaction="${pageContext.request.contextPath}/frontController">
                        <fmt:message key="subscription.button.tasks" bundle="${i18n}"/>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>

</form>