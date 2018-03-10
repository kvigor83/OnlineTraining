<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" type="text/css"/>
</head>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<h2><fmt:message key="course.topic.edit" bundle="${i18n}"/></h2>
<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/frontController">
        <%--<input type="hidden" name="command" value="editCourse"/>--%>
        <input type="hidden" name="courseId" value="${requestScope.currentCourse.id}"/>
        <div class="row">
            <div class="col-25">
                <label for="courseName"><fmt:message key="course.form.course" bundle="${i18n}"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="courseName" name="courseName" required
                       value="${requestScope.currentCourse.courseName}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="hours"><fmt:message key="course.form.hours" bundle="${i18n}"/></label>
            </div>
            <div class="col-25">
                <input type="text" id="hours" name="hours" required value="${requestScope.currentCourse.hours}">
            </div>
            <div class="col-25">
                <label for="cost"><fmt:message key="course.form.cost" bundle="${i18n}"/></label>
            </div>
            <div class="col-25">
                <input type="text" id="cost" name="cost" required value="${requestScope.currentCourse.cost}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="description"><fmt:message key="course.form.description" bundle="${i18n}"/></label>
            </div>
            <div class="col-75">
                <textarea id="description" name="description" style="height:200px"
                          required> ${requestScope.currentCourse.description} </textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25"></div>
            <div class="col-25">

            </div>
            <div class="col-25">
                <button class="button-course" name="command" value="editCourse">
                    <fmt:message key="course.button.save" bundle="${i18n}"/>
                </button>
            </div>

            <div class="col-25">
                <c:if test="${sessionScope.user.role eq 'admin'}">
                    <button class="button-course" name="command" value="deleteCourse">
                        <fmt:message key="course.button.delete" bundle="${i18n}"/>
                    </button>
                </c:if>
            </div>
        </div>
    </form>

</div>
