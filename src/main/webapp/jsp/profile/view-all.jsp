<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/defaultStyle.css" type="text/css"/>
</head>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<div style="font-size: large">
    <c:if test="${not empty message}">INFO : ${message}</c:if> <br/>
</div>
<div>
    <div class="container-fluid">
        <div class="col-md-12"><P><fmt:message key="profile.form.title.all" bundle="${i18n}"/></P></div>
        <form method="post" action="${pageContext.request.contextPath}/frontController">
            <input type="hidden" name="command" value="userDelete">
            <table class="table table-bordered" border=2px>
                <tr>
                    <div class="col-md-8">
                        <th class="col-md-3"><fmt:message key="registration.form.fio" bundle="${i18n}"/></th>
                        <th class="col-md-2"><fmt:message key="registration.form.login" bundle="${i18n}"/></th>
                        <th class="col-md-2"><fmt:message key="registration.form.email" bundle="${i18n}"/></th>
                        <th class="col-md-1"><fmt:message key="registration.form.role" bundle="${i18n}"/></th>
                        <th class="col-md-2"><fmt:message key="registration.form.status" bundle="${i18n}"/></th>
                        <th class="col-md-2"></th>
                    </div>
                </tr>
                <c:forEach var="user1" items="${requestScope.userList}" varStatus="status">
                    <tr class="info">
                        <div class="col-md-8">
                            <td class="col-md-3">${user1.fio}</td>
                            <td class="col-md-2">${user1.login}</td>
                            <td class="col-md-2">${user1.email}</td>
                            <td class="col-md-1">${user1.role}</td>
                            <td class="col-md-2">${user1.status}</td>
                            <td class="col-md-2">
                                <button class="button-course" name="userId" value="${user1.id}">
                                    <fmt:message key="profile.button.delete" bundle="${i18n}"/>
                                </button>
                            </td>
                        </div>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </div>
</div>