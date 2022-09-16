<%-- 
    Document   : header
    Created on : Sep 14, 2022, 10:38:09 AM
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
        <link rel="stylesheet" href="./assets/css/base/header.css"/>
    </head>
    <body>
        <div class=<c:if test="${role eq 'admin'}">"header headerAdmin"</c:if>
             <c:if test="${role ne 'admin'}">"header headerUser"</c:if>
                 >
                 <img src="./assets/img/Logo.png" alt="alt" class="header_logo"/>

                 <div class="header_user">
                     <div class="noti">
                         <img class="noti_img" src="./assets/icons/noti.png" alt="alt"/>
                         <div class="noti_count">8</div>
                     </div>

                     <div class="user">
                         <img class="user_avt" src="src" width="width" height="height" alt="alt"/>

                         <div class="user_info">
                             <div class="user_name">thaihaf</div>
                             <div class="user_role">Admin</div>
                         </div>
                     </div>

                 </div>

             <jsp:include page="./menu.jsp" />
        </div>
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <<script src="./assets/js/base/header.js"></script>
    </body>
</html>
