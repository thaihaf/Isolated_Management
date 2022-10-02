<%-- 
    Document   : menu
    Created on : Sep 16, 2022, 8:12:14 AM
    Author     : hapro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--todo-->


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/base/menu.css"/>
    </head>
    <body>
        <c:set var="role" value="${sessionScope.account.role}"/>
        <div class="menu hidden">
            <c:choose>
                <c:when test="${role=='admin'}">
                    <a class="menu_item" href="adminHome">Home</a>
                </c:when>
                <c:when test="${role=='doctor'}">
                    <a class="menu_item" href="doctorHome">Home</a>
                </c:when>
                <c:when test="${role=='nurse'}">
                    <a class="menu_item" href="nurseHome">Home</a>
                </c:when>
                <c:when test="${role=='patient'}">
                    <a class="menu_item" href="patientHome">Home</a>
                </c:when>
            </c:choose>

            <a class="menu_item" href="profile">User Profile</a>
            <a class="menu_item" href="changepass">Change Password</a>
            <a class="menu_item" href="../logout">Logout</a>
        </div>

    </body>
</html>
