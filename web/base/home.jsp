<%-- 
    Document   : home
    Created on : Sep 14, 2022, 10:33:58 AM
    Author     : hapro
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--todo-->


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><c:set var="role" value="${sessionScope.account.role}"/>${sessionScope.account.userName}</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/doctor/doctorViewPrescription.css"/>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/smoothness/jquery-ui.css" />
        <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    </head>
    <body>
        <c:set var="role" value="${sessionScope.account.role}"/>
        <jsp:include page="./sidebar.jsp" />

        <jsp:include page="./header.jsp" />

        <div class="wrapper wrapperUser">
            <div class="container">
                <div id="modal" style="display:none;"></div>
            </div>
            <jsp:include page="./footer.jsp" />
        </div>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <script src="../assets/js/base/today_sched.js" type="text/javascript"></script>
    </body>
</html>
