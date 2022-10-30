<%-- 
    Document   : sched_create
    Created on : Oct 24, 2022, 9:17:42 PM
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
        <script src="../assets/js/admin/sched_create.js" type="text/javascript"></script>
        <style type="text/css">
            <%@include file="../assets/css/admin/sched_create.css"%>
        </style>
    </head>
    <body>
        <jsp:include page="../base/sidebar.jsp" />

        <jsp:include page="../base/header.jsp" />

        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form id="schedule" action="sched_create" method="POST" class="schedule" name="schedule">
                <h2 style="line-height: 2rem;margin-bottom:1rem;">Create schedule</h2>
                    <div class="row">
                        <div class="schedule-column">
                            <label for="selectPerson" class="schedule-title">Person assigned</label>
                            <select id="selectPerson" class="schedule__form-control" name="person">
                                <c:forEach items="${requestScope.account}" var="a">
                                    <option value="${a.account.userName}">${a.fullName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="schedule-column">
                            <label for="room" class="schedule-title">Room to assigned</label>
                            <select id="room" name="room" class="schedule__form-control">
                                <c:forEach items="${requestScope.room}" var="r">
                                    <option value="${r.id}">${r.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="schedule-column">
                            <label for="date" class="schedule-title">Schedule date</label>
                            <input type="date" class="schedule__form-control" id="date" name="date"/>
                        </div>
                        <div class="schedule-column">
                            <label for="date" class="schedule-title">Schedule time</label>
                            <div>
                                <c:forEach items="${requestScope.schedtime}" var="time">
                                    <input type="radio" id="${time.name}" name="time" value="${time.id}" id="time" required/>
                                    <label style="cursor: pointer;" for="${time.name}">${time.name}</label>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                <div style="display: flex;flex-direction: column;">
                    <label for="txtDescription" class="schedule-title">Description</label>
                    <textarea id="txtDescription" name="desc" required style="resize:none;"></textarea><br/>                    
                </div>
                    <button id="submitButton" type="submit">Add</button><br/>
                </form>
                <div class="response"></div>
            </div>
            <jsp:include page="../base/footer.jsp" />   
        </div>
    </body>
</html>
