<%-- 
    Document   : home
    Created on : Sep 14, 2022, 10:33:58 AM
    Author     : hapro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--todo-->


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:set var="role" value="${sessionScope.account.role}"/>${sessionScope.account.userName}</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/doctor/doctorViewPrescription.css"/>
    </head>
    <body>
        <c:set var="role" value="${sessionScope.account.role}"/>
        <jsp:include page="./sidebar.jsp" />

        <jsp:include page="./header.jsp" />

        <div class="wrapper wrapperUser">
            <div class="container">
                <!--                     Code vào đây là oke-->
            </div>
            <jsp:include page="./footer.jsp" />   
        </div>
    </body>
</html>
