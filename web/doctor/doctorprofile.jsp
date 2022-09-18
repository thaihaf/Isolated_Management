<%-- 
    Document   : doctorprofile
    Created on : Sep 16, 2022, 1:55:03 AM
    Author     : Admin
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
        <form action="doctorprofile" method="GET">
            <c:forEach items="${requestScope.doctors}" var="d">
                ID:${d.account.userName}</br></br>
                Name:${d.accDetail.fullName}</br></br>
                Gender:${d.accDetail.gender}</br></br>
                Phone:${d.accDetail.phone}</br></br>
                Address:${d.accDetail.address}</br></br>
                Email:${d.accDetail.email}</br></br>
                Nation:${d.accDetail.nation}</br></br>
                Level of education:${d.education}</br></br>
                Hospital:${d.hospital}</br></br>
            </c:forEach>
        </form>
    </body>
</html>
