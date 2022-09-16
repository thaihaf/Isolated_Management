<%-- 
    Document   : home
    Created on : Sep 14, 2022, 10:33:58 AM
    Author     : hapro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--todo-->
<c:set var="role" value="admin"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./assets/css/base.css"/>
        <link rel="stylesheet" href="./assets/css/base/home.css"/>
    </head>
    <body>
        <jsp:include page="./sidebar.jsp" />

        <jsp:include page="./header.jsp" />

        <div class=<c:if test="${role eq 'admin'}">"wrapper wrapperAdmin"</c:if>
             <c:if test="${role ne 'admin'}">"wrapper wrapperUser"</c:if>
                 >
                 <div class="container">
                     Code vào đây là oke
                 </div>
             <jsp:include page="./footer.jsp" />   
        </div>


    </body>
</html>
