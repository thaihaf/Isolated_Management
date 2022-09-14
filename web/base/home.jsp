<%-- 
    Document   : home
    Created on : Sep 14, 2022, 10:33:58 AM
    Author     : hapro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base/home.css"/>
    </head>
    <body>
        <jsp:include page="./sidebarAdmin.jsp" />

        <jsp:include page="./header.jsp" />

        <div class="wrapper">
            <div class="container">
                Code vào đây là oke
            </div>
            <jsp:include page="./footer.jsp" />   
        </div>


    </body>
</html>
