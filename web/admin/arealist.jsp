<%-- 
    Document   : arealist
    Created on : Sep 29, 2022, 11:25:20 PM
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
                <h1>Area Lists</h1><a href="add_area">Add new area</a>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Area Type</th>
                        <th>Total rooms</th>
                        <th>Available</th>
                        <th>Action</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${requestScope.areas}" var="a">
                        <tr>
                            <td>${a.id}</td>
                            <td>${a.name}</td>
                            <td>${a.address}</td>
                            <td>${a.areaType.type}</td>
                            <td>${a.rooms.size()}</td>
                            <td><c:if test="${a.available eq true}">Yes</c:if><c:if test="${a.available eq false}">No</c:if></td>
                        <td><a href="area?id=${a.id}">Edit</a></td>
                        <td><a href="areastatus?id=${a.id}&status=${!a.available}">Switch Status</a></td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
