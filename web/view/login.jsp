<%-- 
    Document   : login
    Created on : Sep 13, 2022, 2:26:42 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="./assets/css/base.css"/>
        <link rel="stylesheet" href="./assets/css/view/login.css"/>
    </head>
    <body>
        <div class="login" style="background-image: url(../assets/img/bg_login.png)">
            <div class="login_link">
                <div class="item item_language">
                    <select name="language" class="switch_option">
                        <option value="vn">Vietnamese</option>
                        <option value="en">English</option>
                        <option value="jp">Japanese</option>
                    </select>
                </div>
                <a class="item">FAQ</a>
                <a class="item">Guide</a>
                <a class="item">Contact us</a>
            </div>

            <div class="login_inputTab">
                <div class="intro">
                    <div class="intro_title">welcome!</div>
                    <div class="intro_small">
                        Don't have account,
                        <a href="view/register.jsp">Register</a>
                    </div>
                </div>

                <form action="login" method="post" class="login_form">
                    <div class="item item_username">
                        <div class="item_title">Username</div>

                        <input class="item_input" type ="text" name="UserName" placeholder="thaihaf">
                    </div>

                    <div class="item item_password">
                        <div class="item_title">Password</div>

                        <input class="item_input" type="password" name="Password">
                    </div>

                    <p class="text-danger">${mess}</p>

                    <a href="url" class="switch_link">Forget password?</a>


                    <input class="btn_submit" type="submit" value="Sign in">
                </form>
            </div>


        </div>

    </body>
</html>
