<%-- 
    Document   : sched_edit
    Created on : Oct 27, 2022, 1:27:45 AM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
        <link href="../assets/css/admin/sched_create.css" rel="stylesheet" type="text/css"/>
        <script src="../assets/js/admin/sched_edit.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />

        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form id="edit" method="POST" action="sched_edit">
                    <input type="hidden" name="id" value="${requestScope.sched.id}"/>
                    Person to assigned: <select name="account">
                        <c:forEach items="${requestScope.account}" var="a">
                            <option value="${a.account.userName}" <c:if test="${a.account.userName eq requestScope.sched.assignedUser.account.userName}">selected="selected"</c:if>>${a.fullName}</option>
                        </c:forEach>
                    </select><br/>
                    Room to assigned: <select name="room">
                        <c:forEach items="${requestScope.room}" var="r">
                            <option value="${r.id}" <c:if test="${r.id eq requestScope.sched.room.id}">selected="selected"</c:if>>${r.name}</option>
                        </c:forEach>
                    </select><br/>
                    Schedule date: <input type="date" name="date" value="${requestScope.sched.date}"/><br/>
                    Schedule time: <c:forEach items="${requestScope.schedtime}" var="st"><input type="radio" name="time" value="${st.id}" id="time" required <c:if test="${requestScope.sched.time.id eq st.id}">checked="checked"</c:if>/> ${st.name}</c:forEach><br/>
                    Description:<br/> <textarea name="desc" required style="resize:none;">${requestScope.sched.description}</textarea><br/>
                    <button id="submitButton" type="button">Save</button><br/>
                </form>
                <div id="response"></div>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
