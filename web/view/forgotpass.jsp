<%-- 
    Document   : forgotpass
    Created on : Sep 14, 2022, 8:59:02 PM
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
        <c:if test="${not empty requestScope.message}">
            <div>${requestScope.message}</div>
        </c:if>
        <form action="forgot" method="POST">
            Username: <input type="text" name="user" />
            <input type="submit" value="Proceed"/>
        </form>
    </body>
</html>
