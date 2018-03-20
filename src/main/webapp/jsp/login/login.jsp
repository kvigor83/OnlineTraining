<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" type="text/css"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<div class="container text-center">
    <c:if test="${not empty sessionScope.infoMsg}">
        <p align="center" style="color:red;"><fmt:message key="${sessionScope.infoMsg}" bundle="${i18n}"/></p>
    </c:if>
    <c:set var="infoMsg" value="" scope="session"/>
    <form action="frontController" method="post">
        <input type="hidden" name="command" value="login"/>
        <div style="color:red;"> ${requestScope.infoMsg}</div>
        <div class="row">
            <p><fmt:message key="login.login" bundle="${i18n}"/></p>
            <div class="col-25"></div>
            <div class="col-50"><input type="text" name="login" maxlength="30" title="login"/></div>
        </div>
        <br>
        <div class="row">
            <p><fmt:message key="login.password" bundle="${i18n}"/></p>
            <div class="col-25"></div>
            <div class="col-50"><input type="password" name="password" maxlength="30" title="password"/></div>
        </div>
        <div class="row">
            <div style="color:red">
                <c:if test="${not empty requestScope.errorMsg}">
                    <p><fmt:message key="${requestScope.errorMsg}" bundle="${i18n}"/></p>
                </c:if>
            </div>
        </div>
        <br>
        <div class="row">
            <input type="submit" value="<fmt:message key="login.submit" bundle="${i18n}"/>">
        </div>
    </form>
</div>
