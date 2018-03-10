<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript" src="${pageContext.request.contextPath}/js/confirm.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/registrationStyle.css" type="text/css"/>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<p align="left" style="color:red;"><c:out value="${requestScope.errorMsg}" /></p>
<form name="registration" action="frontController?command=registration" method="post"
      accept-charset="UTF-8">
    <table>
        <tr>
            <td class="td_left"><fmt:message key="registration.form.fio" bundle="${i18n}"/></td>

            <td class="td_right"><input type="text" name="fio" id="fio" size="38" value="" required/>
        </tr>
        <tr>
            <td class="td_left"><fmt:message key="registration.form.login" bundle="${i18n}"/></td>

            <td class="td_right">
                <input title='one'
                       type="text" name="login" id="login_id" size="38" value=""
                       onkeypress="CorrectLogin('login_id')"
                       onfocus="CorrectLogin('login_id')"
                       onkeyup="CorrectLogin('login_id')"
                       onchange="CorrectLogin('login_id')"
                />
                <div class="mini">
                    <fmt:message key="registration.mess.one" bundle="${i18n}"/>
                </div>
            </td>
            <td class="td_info">
                <div id="login_correct"></div>
            </td>
        </tr>
        <tr>
            <td class="td_left"><fmt:message key="registration.form.password" bundle="${i18n}"/></td>
            <td class="td_right">
                <input title="two"
                       type="password" name="pass" id="pass_id" maxlength="20" size="38" value=""
                       onkeypress="CorrectPass('pass_id')"
                       onfocus="CorrectPass('pass_id')"
                       onkeyup="CorrectPass('pass_id')"
                />
                <!--onchange="CorrectPass('pass_id')"-->
                <div class="mini">
                    <fmt:message key="registration.mess.two" bundle="${i18n}"/>
                </div>
            </td>
            <td class="td_info">
                <div id="pass_correct"></div>
            </td>
        </tr>
        <tr>
            <td class="td_left"><fmt:message key="registration.form.repassword" bundle="${i18n}"/></td>
            <td class="td_right">
                <input title="не менее 6 символов, не менее одной буквы в каждом регистре и не менее одной цифры"
                       type="password" name="repass" id="repass_id" maxlength="20" size="38" value=""
                       onkeypress="CorrectRepass('repass_id')"
                       onfocus="CorrectRepass('repass_id')"
                       onkeyup="CorrectRepass('repass_id')"
                />
                </td>
            <td class="td_info">
                <div id="repass_correct"></div>
            </td>
        </tr>
        <tr>
            <td class="td_left"><fmt:message key="registration.form.email" bundle="${i18n}"/></td>
            <td class="td_right">
                <input title="электронная почта"
                       type="text" name="email" size="38" id="email_id" value=""
                       onkeypress="CorrectEmail('email_id')"
                       onfocus="CorrectEmail('email_id')"
                       onkeyup="CorrectEmail('email_id')"
                />

            </td>
            <td class="td_info">
                <div id="email_correct"></div>
            </td>
        </tr>
        <tr>
            <td></td>
            <td class="td_button" colspan="3">
                <input type="submit" name="submit" id="submit_id" value="<fmt:message key="registration.button.register" bundle="${i18n}"/>" hidden/>
            </td>
        </tr>
    </table>
    <input type="hidden" name="check_login" id="check_login" value="0"/>
    <input type="hidden" name="check_pass" id="check_pass" value="0"/>
    <input type="hidden" name="check_repass" id="check_repass" value="0"/>
    <input type="hidden" name="check_email" id="check_email" value="0"/>
    <input type="hidden" name="check_all" id="check_all" value="0"/>
</form>
