<%-- 
    Document   : menu
    Created on : Sep 16, 2022, 8:12:14 AM
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
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/base/menu.css"/>
    </head>
    <body>
        <div class="menu hidden">
            <a class="menu_item" href="/">Home</a>
            <a class="menu_item" href="/">User Profile</a>
            <a class="menu_item" href="/">Change Password</a>
            <a class="menu_item" href="/">Logout</a>
        </div>
        
    </body>
</html>
