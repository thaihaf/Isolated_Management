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
        <title>User account - IMS</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/base/home.css"/>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
                <h1>User List</h1>
                <form action="users" method="POST">
                    <select name="role">
                        <option value="-1">All Roles</option>
                        <c:forEach items="${requestScope.roles}" var="r">
                            <option
                                <c:if test="${param.role eq r.id}">
                                    selected="selected"
                                </c:if>
                                value="${r.id}">${r.role}</option>
                        </c:forEach>
                    </select>
                    <input type="text" value="${param.searchfield}" name="searchfield"/>
                    <input type="submit" value="Search"/>
                    <a href="../adduser">Add more user</a>
                </form>
                <c:if test="${requestScope.accounts eq null}">There is no account that you searched.</c:if>
                <c:if test="${requestScope.accounts ne null}">
                    <table>
                        <tr>
                            <th>Name</th>
                            <th>Gender</th>
                            <th>Phone</th>
                            <th>Address</th>
                            <th>Role</th>
                            <th>Action</th>
                        </tr>
                        <c:forEach items="${requestScope.accounts}" var="a">
                            <tr>
                                <td>${a.fullName}</td>
                                <td><c:if test="${a.gender eq true}">Male</c:if><c:if test="${a.gender eq false}">Female</c:if></td>
                                <td>${a.phone}</td>
                                <td>${a.address}</td>
                                <td>${a.account.role.role}</td>
                                <td><a href="updateuser?user=${a.account.userName}">Edit</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
