<%-- 
    Document   : header
    Created on : Sep 14, 2022, 10:38:09 AM
    Author     : hapro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--todo-->


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/base/header.css"/>
    </head>
    <body>
        <c:set var="name" value="${sessionScope.account.userName}"/>
        <c:set var="role" value="${sessionScope.account.role}"/>
        <div class=<c:if test="${role.id eq 1}">"header headerAdmin"</c:if>
             <c:if test="${role.id ne 1}">"header headerUser"</c:if>
                 >
                 <div class="header_btnMenu" id="header_btnMenu">
                     <img class="header_btnMenu_img" src="../assets/icons/menu.png" alt="alt"/>
                 </div>


                 <a href="home"><img src="../assets/img/Logo.png" alt="alt" class="header_logo"/>
                 </a>


                 <div class="header_user">
                     <a class="noti" <c:if test="${sessionScope.account.role.id ne 1}">href="notification"</c:if>>
                         <img class="noti_img" src="../assets/icons/noti.png" alt="alt"/>
                         <div class="noti_count">8</div>
                     </a>

                     <div class="user">
                         <img class="user_avt" src="../assets/img/avt.jpg" width="width" height="height" alt="alt"/>

                         <div class="user_info">
                             <div class="user_name">${name}</div>
                         <div class="user_role">${role.role}</div>
                     </div>
                 </div>

             </div>

             <jsp:include page="./menu.jsp" />
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <<script src="../assets/js/base/header.js"></script>
    </body>
</html>
