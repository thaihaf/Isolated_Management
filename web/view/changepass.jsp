<%-- 
    Document   : changePass
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
        <link rel="stylesheet" href="../assets/css/base2.css"/>
        <link rel="stylesheet" href="../assets/css/view/changePass.css"/>
    </head>
    <body>
        <div class="changePass" style="background-image: url(../assets/img/bg_login.png)">
            <div class="changePass_link">
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

            <div class="changePass_inputTab">
                <div class="intro">
                    <div class="intro_title">change password</div>
                    <div class="intro_small">
                        Some step to have new password, 
                        <span>Get it’s</span>
                    </div>
                </div>

                <form action="changepass" method="post" class="changePass_form">
                    <div class="item item_currentPass">
                        <div class="item_title">Current Password</div>

                        <input class="item_input" type="password" placeholder="Enter Current Password" name="old" required>
                    </div>

                    <div class="item item_newPass">
                        <div class="item_title">New Password</div>

                        <input class="item_input" type="password" placeholder="Enter New Password" name="new" required>
                    </div>

                    <div class="item item_confirmPass">
                        <div class="item_title">Confirm Password</div>

                        <input class="item_input" type="password" placeholder="Re-enter new password" name="cfnew" required>
                    </div>

                    <p class="text-danger">${mess}</p>

                    <input class="btn_submit" type="submit" value="Change">
                </form>
            </div>


        </div>

    </body>
</html>
