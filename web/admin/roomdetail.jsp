<%-- 
    Document   : roomdetail
    Created on : Sep 28, 2022, 3:15:11 AM
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
                <form action="room" method="POST">
                    ID: ${requestScope.room.id}<br/>
                    <input type="hidden" name="id" value="${requestScope.room.id}"/>
                    Room name: <input type="text" name="name" value="${requestScope.room.name}"/><br/>
                    Number of bed: <input type="number" name="numOfBed" value="${requestScope.room.numOfBed}"/><br/>
                    Area: <select name="area">
                        <c:forEach items="${requestScope.areas}" var="a">
                            <option <c:if test="${requestScope.room.area.id eq a.id}">selected</c:if> value="${a.id}">${a.name}</option>
                        </c:forEach>
                    </select><br/>
                    Doctor manage: <select name="doctor">
                        <c:forEach items="${requestScope.medical}" var="d">
                            <c:if test="${d.account.role.id eq 2}">
                                <option <c:if test="${requestScope.room.doctorManage eq d.account.userName}">selected</c:if> value="${d.account.userName}">${d.fullName}</option>
                            </c:if>
                        </c:forEach>
                    </select><br/>
                    Nurse manage: <select name="nurse">
                        <c:forEach items="${requestScope.medical}" var="d">
                            <c:if test="${d.account.role.id eq 3}">
                                <option <c:if test="${requestScope.room.nurseManage eq d.account.userName}">selected</c:if> value="${d.account.userName}">${d.fullName}</option>
                            </c:if>
                        </c:forEach>
                    </select><br/>
                    Status: <input type="radio" name="status" value="true" <c:if test="${requestScope.room.available eq true}">checked</c:if>/>Active <input type="radio" name="status" value="false" <c:if test="${requestScope.room.available eq false}">checked</c:if>/>Inactive<br/>
                        <input type="submit" value="Save"/>
                    </form>
                </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
