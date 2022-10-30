<%-- 
    Document   : exercise_detail
    Created on : Oct 31, 2022, 3:51:42 AM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../assets/css/patient/exercise_detail.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
        <script src="../assets/js/base/exsrc_show.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />
        <c:set scope="request" var="ex" value="${exercise}"/>
        <div class="wrapper wrapperUser">
            <div class="container">
                <a href="exercise_sched">Return to exercise schedule</a>
                <table>
                    <tr>
                        <td>Exercise name:</td>
                        <td>${ex.name}</td>
                    </tr>
                    <tr>
                        <td>Exercise note:</td>
                        <td>${ex.note}</td>
                    </tr>
                    <tr>
                        <td>Demonstration:</td>
                        <td id="content">
                            <div data-id="${ex.sourceType.id}" id='type'></div>
                            <div data-id="${ex.source}" id='src'></div>
                        </td>
                    </tr>
                </table>
            </div>
            <jsp:include page="/base/footer.jsp" />
        </div>
    </body>
</html>
