<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/validate.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/registrationStyle.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" type="text/css"/>
</head>
<body>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<c:if test="${not empty requestScope.infoMsg}">
    <p align="left" style="color:red;"><fmt:message key="${requestScope.infoMsg}" bundle="${i18n}"/></p>
</c:if>
<c:set var="infoMsg" value="" scope="session"/>
<div class="container">
    <form name="registration" action="frontController?command=registration"
          method="post" accept-charset="UTF-8">
        <div class="row">
            <div class="col-25"><fmt:message key="registration.form.fio" bundle="${i18n}"/></div>
            <div class="col-50"><input type="text" name="fio" id="fio" size="38" value=""
                                        onblur="ClearFields()"/></div>
            <div class="col-25" >
                <div id="fio_correct"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-25" ><fmt:message key="registration.form.login" bundle="${i18n}"/></div>
            <div class="col-50" >
                <input type="text" title="login" name="login" id="login_id" size="38" value=""/>
                <div class="mini">
                    <fmt:message key="registration.mess.one" bundle="${i18n}"/>
                </div>
            </div>
            <div class="col-25" >
                <div id="login_correct"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-25"><fmt:message key="registration.form.password" bundle="${i18n}"/></div>
            <div class="col-50">
                <input type="password" title="password" name="pass" id="pass_id" maxlength="20" size="38" value=""/>
                <div class="mini">
                    <fmt:message key="registration.mess.two" bundle="${i18n}"/>
                </div>
            </div>
            <div class="col-25">
                <div id="pass_correct"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-25"><fmt:message key="registration.form.repassword" bundle="${i18n}"/></div>
            <div class="col-50">
                <input title="repassword"
                       type="password" name="repass" id="repass_id" maxlength="20" size="38" value=""/>
            </div>
            <div class="col-25">
                <div id="repass_correct"></div>
            </div>
        </div>
        <div class="row">
            <div class="col-25"><fmt:message key="registration.form.email" bundle="${i18n}"/></div>
            <div class="col-50">
                <input title="email"
                       type="text" name="email" size="38" id="email_id" value="" onblur="ClearFields()"/>
            </div>
            <div class="col-25">
                <div id="email_correct"></div>
            </div>
        </div>
        <%--<input type="hidden" name="check_login" id="check_login" value="0"/>--%>
        <%--<input type="hidden" name="check_pass" id="check_pass" value="0"/>--%>
        <%--<input type="hidden" name="check_repass" id="check_repass" value="0"/>--%>
        <%--<input type="hidden" name="check_email" id="check_email" value="0"/>--%>
        <%--<input type="hidden" name="check_all" id="check_all" value="0"/>--%>
        <input type="submit" value="<fmt:message key="registration.button.register" bundle="${i18n}"/>" onclick="return validateForm(this.form);"/>
    </form>
</div>
</body>