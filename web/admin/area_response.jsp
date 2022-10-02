<%-- 
    Document   : area_response
    Created on : Oct 1, 2022, 3:26:14 AM
    Author     : Mountain
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>IMS - Update to area</title>
        <script>
            var count = 3;
            function redirect()
            {
                count--;
                document.getElementById('timer').innerHTML = count;
                if (count <= 0)
                    window.location.href = 'areas';
            }
            setInterval(redirect, 1000);
        </script>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
                ${requestScope.mess}<br/>
                Redirect to area lists in <span id="timer">3</span> seconds...
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
