<%-- 
    Document   : createConfirm
    Created on : Sep 29, 2022, 10:09:28 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            var count = 3;
            function redirect()
            {
                count--;
                document.getElementById('timer').innerHTML = count;
                if (count <= 0)
                    window.location.href = '../base/listpatient';
            }
            setInterval(redirect, 1000);
        </script>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/view/confirm.css"/>
    </head>
    <body>
        <div class="container">
            <h1 style="text-align: center">${requestScope.action} successfully</h1>
            <h3 style="text-align: center"> Go back to Patient page after <span id="timer">3</span> seconds </h2>
        </div>

    </body>
</html>
