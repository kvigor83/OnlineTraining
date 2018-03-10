<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<div class="container text-center">

    <p align="center" style="color:red;">${sessionScope.infoMsg}</p>
    <c:set var="infoMsg" value="" scope="session"/>
    <form action="frontController" method="post">
        <input type="hidden" name="command" value="login" />
        <div style="color:red;"> ${requestScope.infoMsg}</div>
        <p><fmt:message key="login.login" bundle="${i18n}"/></p>
        <input type="text" name="login" maxlength="30" title="login"/>
        <p>
        <p><fmt:message key="login.password" bundle="${i18n}"/></p>
        <input type="password" name="password" maxlength="30" title="password"/>
        <br><br>
        <div style="color:red;"> ${requestScope.errorMsg}</div>
        <br>
        <p></p>
        <input type="submit" value="<fmt:message key="login.submit" bundle="${i18n}"/>">
    </form>
</div>
