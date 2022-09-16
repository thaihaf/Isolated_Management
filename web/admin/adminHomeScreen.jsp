<%-- 
    Document   : adminHomeScreen
    Created on : Sep 13, 2022, 2:40:43 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--todo-->
<c:set var="role" value="doctor"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/homeScreenAdmin.css"/>
    </head>
    <body>
        <jsp:include page="../base/sidebar.jsp" />

        <jsp:include page="../base/header.jsp" />

        <div class=<c:if test="${role eq 'admin'}">"wrapper wrapperAdmin"</c:if>
        <c:if test="${role ne 'admin'}">"wrapper wrapperUser"</c:if>
        >
        <div class="container">
            Code vào đây là oke
        </div>
        <jsp:include page="../base/footer.jsp" />   
    </div>


</body>
</html>

