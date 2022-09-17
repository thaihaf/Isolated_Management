<%-- 
    Document   : register
    Created on : Sep 14, 2022, 5:07:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <form action="register" method="post">
            <h1>REGISTER</h1>
            Full name <input type="text" name="Fullname"><br>
            Gender <select name="Gender">
                <option></option>
            </select>
            Nationality <select name="nation">
                <option></option>
            </select><br>
            Phone <input type="text" name="Phone"><br>
            Email <input type="text" name="Email"><br>
            Username <input type="text" name ="Username"><br>
            Password <input type="password" name="Password"><br>
            Confirm password <input type="password" name="confirm_password"><br>
            <input type="submit" value="Register">
        </form>
    </body>
</html>
