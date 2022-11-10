<%-- 
    Document   : exercise_sched
    Created on : Oct 31, 2022, 3:07:22 AM
    Author     : Mountain
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="../assets/css/patient/exercise_sched.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/smoothness/jquery-ui.css" />
        <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
        <script src="https://code.jquery.com/jquery-migrate-3.0.0.min.js"></script>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />

        <div class="wrapper wrapperUser">
            <div class="container">
                <h2 style="text-align:center;">Exercise Schedule</h2>
                <table>
                    <tr>
                        <th>Date</th>
                        <th>Time</th>
                        <th>Assigned Person</th>
                        <th>Exercise</th>
                    </tr>
                    <c:forEach items="${requestScope.sched}" var="es">
                        <tr>
                            <th><fmt:parseDate  value="${es.schedule.date}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" /><fmt:formatDate pattern="dd-MM-yyyy" value="${parsedDate}"/></th>
                            <th>${es.schedule.time.name}</th>
                            <th>${es.schedule.assignedUser.fullName}</th>
                            <th><a href="#${es.schedule.id}" id='exercise' sched-id='${es.schedule.id}'">View</a></th>
                        </tr>
                    </c:forEach>
                </table>
                <div id="modal" style="display:none;"></div>
            </div>
            <jsp:include page="/base/footer.jsp" />
        </div>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <script src="../assets/js/base/exercise_modal.js" type="text/javascript"></script>
    </body>
</html>
