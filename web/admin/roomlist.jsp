<%-- 
    Document   : roomlist
    Created on : Sep 28, 2022, 1:08:30 AM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room list</title>
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
                            <li class="breadcrumb-item active" aria-current="page">Room List</li>
                        </ol>
                    </nav>
                </div>

                <div class="top2 d-flex justify-content-between">
                    <h1>Room Lists</h1>
                    <a href="add_room" class="btn btn-primary btn-add">Add new room</a>
                </div>

                <table class="table table-hover">
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Number of beds</th>
                        <th>Area</th>
                        <th>Doctor Managing</th>
                        <th>Nurse Managing</th>
                        <th>Available</th>
                        <th>Action</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${requestScope.rooms}" var="r">
                        <tr>
                            <td>${r.id}</td>
                            <td>${r.name}</td>
                            <td>${r.numOfBed}</td>
                            <td>${r.area.name} - ${r.area.areaType.type}</td>
                            <td>${r.doctorManage.fullName}</td>
                            <td>${r.nurseManage.fullName}</td>
                            <td><c:if test="${r.available eq true}">Yes</c:if><c:if test="${r.available eq false}">No</c:if></td>
                            <td><a href="room?id=${r.id}">Edit</a></td>
                            <td><a href="roomstatus?id=${r.id}&status=${!r.available}">Switch Status</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
