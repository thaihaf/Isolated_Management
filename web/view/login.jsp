<%-- 
    Document   : login
    Created on : Sep 13, 2022, 2:26:42 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Account" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form action="login" method="post">
            <h1>LOGIN</h1>
            <p class="text-danger">${mess}</p>
            Username <input type ="text" name="UserName"><br>
            Password <input type="password" name="Password"><br>
            <select name="AccountType">
                <option value="Doctor">Doctor</option>
                <option value="Patient">Patient</option>
                <option value="Nurse">Nurse</option>
                <option value="Admin">Admin</option>
            </select><br>
            
            <input type="submit" value="Login">
        </form>
    </body>
</html>
