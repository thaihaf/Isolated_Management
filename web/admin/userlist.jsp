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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/base/homeNew.css"/>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container-fluid">
                <div class="top1">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="../base/home.jsp">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">User List</li>
                        </ol>
                    </nav>
                </div>

                <form action="users" method="POST">
                    <div class="top2 d-flex">
                        <h1 class="mr-auto">Area Lists</h1>
                        <div class="filter">
                            <a href="../adduser" class="btn btn-primary btn-add">Add more user</a>

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
                        </div>

                        <div class="search ml-5">
                            <input type="text" aria-describedby="basic-addon2" class="form-control list input-group-lg"  value="${param.searchfield}" name="searchfield"/>
                            <input type="submit" class="btn btn-primary btn-add" value="Search"/>
                        </div>
                    </div>
                </form>

                <c:if test="${requestScope.accounts eq null}">There is no account that you searched.</c:if>
                <c:if test="${requestScope.accounts ne null}">
                    <table class="table table-hover">
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
