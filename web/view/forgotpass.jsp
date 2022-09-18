<%-- 
    Document   : forgotpass
    Created on : Sep 14, 2022, 8:59:02 PM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/view/forgotPass.css"/>
    <body>
        <div class="forgotPass" style="background-image: url(../assets/img/bg_login.png)">
            <div class="forgotPass_link">
                <div class="item item_language">
                    <select name="language" class="switch_option">
                        <option value="vn">Vietnamese</option>
                        <option value="en">English</option>
                        <option value="jp">Japanese</option>
                    </select>
                </div>
                <a class="item">FAQ</a>
                <a class="item">Guilde</a>
                <a class="item">Contact us</a>
            </div>

            <div class="forgotPass_inputTab">
                <div class="intro">
                    <div class="intro_title">Forgot password</div>
                    <div class="intro_small">
                        Some step to have new password, 
                        <span>Get it’s</span>
                    </div>
                </div>

                <form action="forgot" method="post" class="forgotPass_form">
                    <div class="item item_username">
                        <div class="item_title">Username</div>

                        <input class="item_input" type ="text" name="user" placeholder="thaihaf">
                    </div>

                    <c:if test="${not empty requestScope.message}">
                        <p class="text-danger">${requestScope.message}</p>
                    </c:if>

                    <input class="btn_submit" type="submit" value="Proceed">
                </form>
            </div>
        </div>
    </body>
</html>
