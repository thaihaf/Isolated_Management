<%-- 
    Document   : checkSession
    Created on : Jul 17, 2022, 11:03:50 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            var count = 5;
            function redirect()
            {
                count--;
                document.getElementById('timer').innerHTML = count;
                if (count <= 0)
                    window.location.href = 'login';
            }
            setInterval(redirect, 1000);
        </script>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/view/checkSession.css"/>
    </head>
    <body>
        <div class="container">
            <h1>Acess Denied! You must login first </h1>
            <h3 style="text-align: center"> Go back to Login page after <span id="timer">5</span> seconds </h3>
        </div>
    </body>
</html>
