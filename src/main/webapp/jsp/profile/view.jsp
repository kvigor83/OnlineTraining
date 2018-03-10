<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/defaultStyle.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/modal.js" type="text/javascript"></script>
</head>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<div style="font-size: large">
    <c:if test="${not empty message}">INFO : ${message}</c:if> <br/>
</div>
<div>
    <div class="container-fluid">
        <div class="col-md-12"><P><fmt:message key="profile.form.title" bundle="${i18n}"/></P></div>
        <form method="post" action="${pageContext.request.contextPath}/frontController">
            <input type="hidden" name="command" value="task">
            <table class="table table-bordered" border=2px>
                <tr>
                    <div class="col-md-8">
                        <th class="col-md-3"><fmt:message key="registration.form.fio" bundle="${i18n}"/></th>
                        <th class="col-md-2"><fmt:message key="registration.form.login" bundle="${i18n}"/></th>
                        <th class="col-md-2"><fmt:message key="registration.form.email" bundle="${i18n}"/></th>
                        <th class="col-md-1"><fmt:message key="registration.form.role" bundle="${i18n}"/></th>
                        <th class="col-md-2"><fmt:message key="registration.form.status" bundle="${i18n}"/></th>
                    </div>
                </tr>
                <tr class="info">
                    <div class="col-md-8">
                        <td class="col-md-3">${user.fio}</td>
                        <td class="col-md-2">${user.login}</td>
                        <td class="col-md-2">${user.email}</td>
                        <td class="col-md-1">${user.role}</td>
                        <td class="col-md-2">${user.status}</td>
                        </td>
                    </div>
                </tr>
            </table>
        </form>
    </div>
</div>
<button id="addBtn" class="button-course" onclick="displayBlock()">
    <fmt:message key="profile.button.edit" bundle="${i18n}"/>
</button>
<div id="addModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="displayNone()">&times;</span>
        <h2><fmt:message key="profile.form.edit.title" bundle="${i18n}"/></h2>
        <div class="container">
            <form method="post" action="${pageContext.request.contextPath}/frontController">
                <input type="hidden" name="command" value="editUser"/>
                <input type="hidden" name="userId" value="${sessionScope.user.id}"/>
                <input type="hidden" name="role" value="${sessionScope.user.role}"/>
                <div class="row">
                    <div class="col-25">
                        <label for="fio"><fmt:message key="registration.form.fio" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="fio" name="fio" required value="${sessionScope.user.fio}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="login"><fmt:message key="registration.form.login" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="login" name="login" required minlength="5"
                               value="${sessionScope.user.login}">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="password"><fmt:message key="registration.form.password" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-75">
                        <input type="password" id="password" name="password">
                        <%--<input type="password" id="password" name="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,15}"}>--%>
                    </div>
                </div>
                <%--<div class="row">--%>
                <%--<div class="col-25">--%>
                <%--<label for="tutor"><fmt:message key="course.form.tutor" bundle="${i18n}"/></label>--%>
                <%--</div>--%>
                <%--<div class="col-25">--%>
                <%--<select id="tutorId" name="tutorId">--%>
                <%--<c:forEach var="tutor" items="${requestScope.tutorList}" varStatus="status">--%>
                <%--<option value="${tutor.id}">${tutor.fio}</option>--%>
                <%--</c:forEach>--%>
                <%--</select>--%>
                <%--</div>--%>
                <%--</div>--%>
                <div class="row">
                    <div class="col-25">
                        <label for="email"><fmt:message key="registration.form.email" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="email" name="email" required pattern="\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,6}"
                               value="${sessionScope.user.email}">
                    </div>
                </div>
                <div class="row">
                    <button class="button-course">
                        <fmt:message key="profile.button.save" bundle="${i18n}"/>
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>