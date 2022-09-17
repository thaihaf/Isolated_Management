
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="users" method="POST">
            <select name="role">
                <c:forEach items="${requestScope.roles}" var="r">
                    <option value="${r.id}">${r.role}</option>
                </c:forEach>
            </select>
        </form>
        <table>
            <tr>
                <td>Name</td>
                <td>Gender</td>
                <td>Phone</td>
                <td>Address</td>
                <td>Role</td>
                <td>Action</td>
            </tr>
            <c:forEach items="${requestScope.accounts}" var="a">
                <tr>
                    <td>${a.fullName}</td>
                    <td><c:if test="${a.gender eq true}">Male</c:if><c:if test="${a.gender eq false}">Female</c:if></td>
                    <td>${a.phone}</td>
                    <td>${a.address}</td>
                    <td>${a.account.role.role}</td>
                    <td><a href="profile?user=${a.account.userName}">Edit</a></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
