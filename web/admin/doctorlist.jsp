<%-- 
    Document   : doctorlist
    Created on : Sep 15, 2022, 12:41:50 AM
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
        <table>
            <tr>
                <td>ID</td>
                <td>Name</td>
                <td>Gender</td>
                <td>Phone</td>
                <td>Address</td>
                <td>Email</td>
                <td>Role</td>
                <td>Action</td>
            </tr>
            <c:forEach items="${requestScope.doctors}" var="d">
                <tr>
                    <td>${d.doctorID}</td>
                    <td>${d.doctorName}</td>
                    <td><c:if test="${d.gender eq true}">Male</c:if><c:if test="${d.gender eq false}">Female</c:if></td>
                    <td>${d.phone}</td>
                    <td>${d.address}</td>
                    <td>${d.account.email}</td>
                    <td>${d.account.accountType}</td>
                    <td><a href="profile?id=${d.doctorID}">Edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
