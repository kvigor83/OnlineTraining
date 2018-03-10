<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/form.css" type="text/css"/>
    <script>
        window.onload = function () {
            document.getElementById("mark").value = "${requestScope.currentTask.mark}";
            document.getElementById("status").value = "${requestScope.currentTask.status}";
            if (${not empty sessionScope.user and sessionScope.user.role eq 'tutor'}) {
                document.getElementById("answer").placeholder = "";
                document.getElementById("answer").disabled = true;
            }
            if (${not empty sessionScope.user and sessionScope.user.role eq 'user'}) {
                document.getElementById("title").disabled = true;
                document.getElementById("body").disabled = true;
                document.getElementById("start").disabled = true;
                document.getElementById("deadline").disabled = true;
                document.getElementById("mark").disabled = true;
                document.getElementById("review").placeholder = "";
                document.getElementById("review").disabled = true;
                document.getElementById("status").disabled = true;
            }
        }
    </script>
</head>
<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages" var="i18n"/>
<h2><fmt:message key="task.topic.edit" bundle="${i18n}"/></h2>
<div class="container">
    <form method="post" action="${pageContext.request.contextPath}/frontController">
        <div class="row">
            <div class="col-25">
                <label for="title"><fmt:message key="task.form.name" bundle="${i18n}"/></label>
            </div>
            <div class="col-75">
                <input type="text" id="title" name="title" required value="${requestScope.currentTask.title}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="start"><fmt:message key="task.form.start" bundle="${i18n}"/></label>
            </div>
            <div class="col-25">
                <%--<input type="datetime-local"  id="start" name="start">--%>
                <input type="date" id="start" name="start" required value="${requestScope.currentTask.startTime}">
            </div>
            <div class="col-25">
                <label for="deadline"><fmt:message key="task.form.deadline" bundle="${i18n}"/></label>
            </div>
            <div class="col-25">
                <%--<input type="datetime-local"  id="deadline" name="deadline">--%>
                <input type="date" id="deadline" name="deadline" value="${requestScope.currentTask.endTime}">
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="body"><fmt:message key="task.form.content" bundle="${i18n}"/></label>
            </div>
            <div class="col-75">
                <textarea id="body" name="body" style="height:200px"
                          required>${requestScope.currentTask.body}</textarea>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="mark"><fmt:message key="task.form.mark" bundle="${i18n}"/></label>
            </div>
            <div class="col-25">
                <select id="mark" name="mark">
                    <option value="0">0</option>
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                    <option value="6">6</option>
                    <option value="7">7</option>
                    <option value="8">8</option>
                    <option value="9">9</option>
                    <option value="10">10</option>
                </select>
            </div>
            <div class="col-25">
                <label for="status"><fmt:message key="task.form.status" bundle="${i18n}"/></label>
            </div>
            <div class="col-25">
                <select id="status" name="status">
                    <option value="New">New</option>
                    <option value="Passed">Passed</option>
                    <option value="Verified">Verified</option>
                </select>
            </div>
        </div>
        <div class="row">
            <div class="col-25">
                <label for="review"><fmt:message key="task.form.review" bundle="${i18n}"/></label>
            </div>
            <div class="col-75">
                    <textarea id="review" name="review" placeholder="Write your review.."
                              style="height:200px">${requestScope.currentTask.review}</textarea>
            </div>
        </div>

        <div class="row">
            <div class="col-25">
                <label for="answer"><fmt:message key="task.form.answer" bundle="${i18n}"/></label>
            </div>
            <div class="col-75">
                <c:choose>
                    <c:when test="${requestScope.currentTask.status eq 'Verified'}">
                    <textarea id="answer" name="answer" placeholder="Write your answer.."
                              style="height:200px" disabled>${requestScope.currentTask.answer}</textarea>
                        <input type="hidden" name="answer" value="${requestScope.currentTask.answer}"/>
                    </c:when>
                    <c:otherwise>
                    <textarea id="answer" name="answer" placeholder="Write your answer.."
                              style="height:200px">${requestScope.currentTask.answer}</textarea>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>

        <div class="row">
            <input type="submit" value=<fmt:message key="task.button.save" bundle="${i18n}"/>>
        </div>
        <input type="hidden" name="taskId" value="${requestScope.currentTask.id}"/>
        <c:if test="${not empty sessionScope.user and sessionScope.user.role eq 'user'}">
            <input type="hidden" name="command" value="editTaskUser"/>
            <input type="hidden" name="title" value="${requestScope.currentTask.title}"/>
            <input type="hidden" name="body" value="${requestScope.currentTask.body}"/>
            <input type="hidden" name="start" value="${requestScope.currentTask.startTime}"/>
            <input type="hidden" name="deadline" value="${requestScope.currentTask.endTime}"/>
            <input type="hidden" name="mark" value="${requestScope.currentTask.mark}"/>
            <input type="hidden" name="review" value="${requestScope.currentTask.review}"/>
            <input type="hidden" name="status" value="${requestScope.currentTask.status}"/>
        </c:if>
        <c:if test="${not empty sessionScope.user and sessionScope.user.role eq 'tutor'}">
            <input type="hidden" name="command" value="editTaskUser"/>
            <input type="hidden" name="answer" value="${requestScope.currentTask.answer}"/>
        </c:if>
    </form>
</div>
