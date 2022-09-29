<%-- 
    Document   : newroom
    Created on : Sep 28, 2022, 4:02:17 AM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form action="add_room" method="POST">
                    Room name: <input type="text" name="name" required/><br/>
                    Number of bed: <input type="number" name="numOfBed" min="0" value="0"/><br/>
                    Area: <select name="area">
                        <c:forEach items="${requestScope.areas}" var="a">
                            <option value="${a.id}">${a.name}</option>
                        </c:forEach>
                    </select><br/>
                    Doctor manage: <select name="doctor">
                        <c:forEach items="${requestScope.medical}" var="d">
                            <c:if test="${d.account.role.id eq 2}">
                                <option value="${d.account.userName}">${d.fullName}</option>
                            </c:if>
                        </c:forEach>
                    </select><br/>
                    Nurse manage: <select name="nurse">
                        <c:forEach items="${requestScope.medical}" var="d">
                            <c:if test="${d.account.role.id eq 3}">
                                <option value="${d.account.userName}">${d.fullName}</option>
                            </c:if>
                        </c:forEach>
                    </select><br/>
                    Status: <input type="radio" name="status" value="true" checked/>Active <input type="radio" name="status" value="false"/>Inactive<br/>
                    <input type="submit" value="Add"/>
                </form>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
