<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/defaultStyle.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" type="text/css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/modal.css" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/modal.js" type="text/javascript"></script>
</head>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<h3><fmt:message key="task.topic.view" bundle="${i18n}"/></h3><br>
<form method="post" action="${pageContext.request.contextPath}/frontController">
    <input type="hidden" name="command" value="task">
    <table class="table table-bordered">
        <tr>
            <th width="15%"><fmt:message key="task.form.name" bundle="${i18n}"/></th>
            <th width="30%"><fmt:message key="task.form.content" bundle="${i18n}"/></th>
            <th width="10%"><fmt:message key="task.form.start" bundle="${i18n}"/></th>
            <th width="10%"><fmt:message key="task.form.deadline" bundle="${i18n}"/></th>
            <th width="5%"><fmt:message key="task.form.mark" bundle="${i18n}"/></th>
            <th width="15%"><fmt:message key="task.form.review" bundle="${i18n}"/></th>
            <th width="5%"><fmt:message key="task.form.status" bundle="${i18n}"/></th>
            <th width="10%"></th>
        </tr>
        <%--<c:forEach var="task" items="${sessionScope.taskList}" varStatus="status">--%>
        <c:forEach var="task" items="${requestScope.taskList}" varStatus="status">
            <tr class="info">
                <td width="15%">${task.title}</td>
                <td width="30%">${task.body}</td>
                <td width="10%">${task.startTime}</td>
                <td width="10%">${task.endTime}</td>
                <td width="5%">${task.mark}</td>
                <td width="15%">${task.review}</td>
                <td width="5%">${task.status}</td>
                <td width="10%">
                    <button class="button-course" name="taskId" value="${task.id}">
                        <fmt:message key="task.button.go" bundle="${i18n}"/>
                    </button>
                </td>
            </tr>
        </c:forEach>
    </table>
</form>
<c:if test="${sessionScope.user.role eq 'tutor'}">
    <button id="addBtn" class="button-course" onclick="displayBlock()">
        <fmt:message key="task.button.add" bundle="${i18n}"/>
    </button>
</c:if>
<div id="addModal" class="modal">
    <div class="modal-content">
        <span class="close" onclick="displayNone()">&times;</span>
        <h2><fmt:message key="task.topic.new" bundle="${i18n}"/></h2>
        <div class="container">
            <form method="post" action="${pageContext.request.contextPath}/frontController">
                <input type="hidden" name="command" value="addTask"/>
                <div class="row">
                    <div class="col-25">
                        <label for="title"><fmt:message key="task.form.name" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-75">
                        <input type="text" id="title" name="title" required placeholder="Your title..">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="start"><fmt:message key="task.form.start" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-25">
                        <%--<input type="datetime-local"  id="start" name="start">--%>
                        <input type="date" id="start" name="start" required>
                    </div>
                    <div class="col-25">
                        <label for="deadline"><fmt:message key="task.form.deadline" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-25">
                        <%--<input type="datetime-local"  id="deadline" name="deadline">--%>
                        <input type="date" id="deadline" name="deadline">
                    </div>
                </div>
                <div class="row">
                    <div class="col-25">
                        <label for="body"><fmt:message key="task.form.content" bundle="${i18n}"/></label>
                    </div>
                    <div class="col-75">
                        <textarea id="body" name="body" placeholder="Write task.." style="height:200px"
                                  required></textarea>
                    </div>
                </div>
                <div class="row">
                    <input type="submit" value=<fmt:message key="task.button.assign" bundle="${i18n}"/>>
                </div>
            </form>
        </div>
    </div>
</div>
