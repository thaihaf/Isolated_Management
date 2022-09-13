<%-- 
    Document   : Change
    Created on : Sep 14, 2022, 1:04:35 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="changepassword" method="POST">
            Current Password: <input type="text" placeholder="Enter Current Password" name="old" required><br><br>
            New Password: <input type="text" placeholder="Enter New Password" name="new" required><br><br>
            Confirm New Password: <input type="text" placeholder="Renter new password" name="cfnew" required><br><br>
            <input type="submit" value="Save"/>
        </form>
    </body>
</html>
