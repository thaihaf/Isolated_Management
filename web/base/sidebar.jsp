<%-- 
    Document   : sidebar_menu
    Created on : Sep 14, 2022, 12:04:16 PM
    Author     : hapro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/base/sidebar.css"/>
    <body>
        <div class="sidebar">
            <div class="sidebar_btnMenu">
                <img class="sidebar_btnMenu_img" src="../assets/icons/menu.png" alt="alt"/>
            </div>

            <div class="sidebar_list" id="style-1">
                <dl>
                    <dt>Home</dt>

                    <dt>System Admin</dt>
                    <dd><a href="url" class="selected">Account List</a></dd>
                    <dd><a href="/abc">Add Patient</a></dd>
                    <dd><a href="url">Add Doctor</a></dd>
                    <dd><a href="url">Add Nurse</a></dd>

                    <dt>Manager</dt>
                    <dd><a href="url">Food List</a></dd>
                    <dd><a href="url">Medical List</a></dd>

                    <dt>Request Manager</dt>
                    <dd><a href="url">Request Medical</a></dd>
                    <dd><a href="url">Request Food</a></dd>
                    <dd><a href="url">Request Contact</a></dd>
                </dl>
            </div>
            
            <div class="sidebar_btnLogout">
                <img class="sidebar_btnLogout_img" src="../assets/icons/logout.png" alt="alt"/>
            </div>
        </div>
    </body>
</html>
