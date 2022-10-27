<%-- 
    Document   : deleteaccount
    Created on : Oct 2, 2022, 6:26:45 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update user account - IMS</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/base/home.css"/>
        <script>
            function changeRole() {
                var z = document.getElementById("role").value;
                var content;
                switch (z) {
                    case '1':
                    {
                        content = "";
                        break;
                    }
                    case '2':
                    case '3':
                    {
                        content = "Level of education: <input type=\"text\" name=\"lvlofedu\" value=\"${requestScope.medical.levelOfEducation}\"/><br/>";
                        content += "Hospital: <input type=\"text\" name=\"hospital\" value=\"${requestScope.medical.hospital}\"/><br/>";
                        break;
                    }
                    case '4':
                    {
                        content = "Room: ${requestScope.patient.room.name}<br/>" +
                                "Area: ${patient.room.area.name}<br/>" +
                                "Background disease: <c:if test="${requestScope.patient.backgroundDisease eq true}">Yes</c:if>" +
                                "<c:if test="${requestScope.patient.backgroundDisease eq false}">No</c:if><br/>" +
                                "Blood type: <input type=\"text\" name=\"blood\" value=\"${patient.bloodType}\"/><br/>";
                        break;
                    }
                }
                document.getElementById('optional').innerHTML = content;
            }
        </script>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form action="deleteuser" method="get">
                   Text user name of account you want to delete <input type="text" name="username">
                    <input type="submit" value="Delete">    
                </form>
            </div>
            <jsp:include page="/base/footer.jsp" />
        </div>
    </body>
</html>
