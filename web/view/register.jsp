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
            Full name <input required type="text" name="Fullname"><br>
            Gender <select name="Gender">
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </select>
            Nationality <select name="Nation">
                <option value="Viet Nam">Viet Nam</option>
            </select><br>
            Phone <input required type="text" name="Phone"><br>
            Address <input required type="text" name="Address"><br>
            Email <input required type="text" name="Email"><br>
            Username <input required type="text" name ="Username"><br>
            Password <input required type="password" name="Password"><br>
            Confirm password <input required type="password" name="confirm_password"><p>${mess}</p><br>
            <input type="submit" value="Register">
        </form>
    </body>
</html>
