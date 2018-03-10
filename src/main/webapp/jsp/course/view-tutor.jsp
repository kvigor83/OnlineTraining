<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
    <%--<link rel="stylesheet" href="${pageContext.request.contextPath}/css/defaultStyle.css" type="text/css"/>--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/modal.js" type="text/javascript"></script>
</head>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<form method="post" action="${pageContext.request.contextPath}/frontController">
    <input type="hidden" name="command" value="course"/>
    <div class="container-fluid">
        <c:if test="${sessionScope.user.role eq 'tutor'}">
            <div class="col-md-12"><P><fmt:message key="course.topic.my-header" bundle="${i18n}"/></P></div>
        </c:if>
        <c:if test="${sessionScope.user.role eq 'admin'}">
            <div class="col-md-12"><P><fmt:message key="course.topic.courses" bundle="${i18n}"/></P></div>
        </c:if>
        <table class="table table-bordered" border=2px>
            <tr>
                <th class="col-md-2"><fmt:message key="course.form.course" bundle="${i18n}"/></th>
                <th class="col-md-1"><fmt:message key="course.form.hours" bundle="${i18n}"/></th>
                <th class="col-md-6"><fmt:message key="course.form.description" bundle="${i18n}"/></th>
                <th class="col-md-1"><fmt:message key="course.form.cost" bundle="${i18n}"/></th>
                <th class="col-md-2"></th>
            </tr>
            <c:forEach var="course" items="${requestScope.courseList}" varStatus="status">
                <tr class="info">
                    <td class="col-md-2">${course.courseName}</td>
                    <td class="col-md-1">${course.hours}</td>
                    <td class="col-md-6">${course.description}</td>
                    <td class="col-md-1">${course.cost}</td>
                    <td class="col-md-2">
                        <button class="button-course" name="courseId" value="${course.id}">
                            <fmt:message key="course.button.edit" bundle="${i18n}"/>
                        </button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <%--<button id="addBtn" class="button-course" name="courseId" value="new">--%>
        <%--<fmt:message key="course.button.add" bundle="${i18n}"/>--%>
        <%--</button>--%>
    </div>
</form>
<c:if test="${sessionScope.user.role eq 'admin'}">
    <button id="addBtn" class="button-course" onclick="displayBlock()">
        <fmt:message key="course.button.add" bundle="${i18n}"/>
    </button>
</c:if>
<div id="addModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="displayNone()">&times;</span>
        <h2><fmt:message key="course.topic.new" bundle="${i18n}"/></h2>
        <div class="container">
            <form method="post" action="${pageContext.request.contextPath}/frontController">
                <input type="hidden" name="command" value="addCourse"/>
                <div class="row">
                    <div class="col-25">
                        <label for="courseName"><fmt:message key="course.form.course" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="courseName" name="courseName" required>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="hours"><fmt:message key="course.form.hours" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-25">
                        <input type="text" id="hours" name="hours" required pattern="\d+">
                    </div>
                    <div class="col-25">
                        <label for="cost"><fmt:message key="course.form.cost" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-25">
                        <input type="text" id="cost" name="cost" required pattern="\d+">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="tutor"><fmt:message key="course.form.tutor" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-25">
                        <select id="tutorId" name="tutorId">
                            <c:forEach var="tutor" items="${requestScope.tutorList}" varStatus="status">
                                <option value="${tutor.id}">${tutor.fio}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="description"><fmt:message key="course.form.description" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-75">
                        <textarea id="description" name="description" style="height:200px" required> </textarea>
                    </div>
                </div>
                <div class="row">
                    <button class="button-course">
                        <fmt:message key="course.button.add" bundle="${i18n}"/>
                    </button>
                </div>
            </form>

        </div>
    </div>
</div>
