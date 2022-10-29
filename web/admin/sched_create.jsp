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
    </head>
    <body>
        <jsp:include page="../base/sidebar.jsp" />

        <jsp:include page="../base/header.jsp" />

        <div class="wrapper wrapperAdmin">
            <div class="container">
                <h2>Create schedule</h2>
                <form id="schedule" action="sched_create" method="POST" class="schedule" name="schedule">
                    Person assigned: <select name="person">
                        <c:forEach items="${requestScope.account}" var="a">
                            <option value="${a.account.userName}">${a.fullName}</option>
                        </c:forEach>
                    </select><br/>
                    Room to assigned: <select name="room" id="room">
                        <c:forEach items="${requestScope.room}" var="r">
                            <option value="${r.id}">${r.name}</option>
                        </c:forEach>
                    </select><br/>
                    Schedule date: <input type="date" id="date" name="date"/><br/>
                    Schedule time: <c:forEach items="${requestScope.schedtime}" var="time"><input type="radio" name="time" value="${time.id}" id="time" required/> ${time.name}</c:forEach><br/>
                        Description:<br/> <textarea name="desc" required style="resize:none;"></textarea><br/>
                        <button id="submitButton" type="submit">Add</button><br/>
                    </form>
                    <div class="response"></div>
                </div>
            <jsp:include page="../base/footer.jsp" />   
        </div>
    </body>
</html>
