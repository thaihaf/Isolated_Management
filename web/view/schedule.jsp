<%-- 
    Document   : schedule
    Created on : Oct 28, 2022, 2:42:13 PM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/smoothness/jquery-ui.css" />
        <script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
    </head>
    <body>
        <jsp:include page="../base/sidebar.jsp" />

        <jsp:include page="../base/header.jsp" />

        <div class="wrapper wrapperUser">
            <div class="container">
                <form id="view">
                    Year: <select name="year" id="year">
                        <c:forEach items="${requestScope.year}" var="y">
                            <option value="${y}" <c:if test="${y eq requestScope.currentyear}">selected="selected"</c:if> >${y}</option>
                        </c:forEach>
                    </select><br/>
                    <div id="week">
                        Week: <select name="week">
                            <c:forEach items="${requestScope.date}" var="date">
                                <option value="${date.key}" <c:if test="${date.key eq requestScope.currentweek}">selected="selected"</c:if> >${date.value}</option>
                            </c:forEach>
                        </select><br/>
                    </div>
                    <button type="button" class="button">View</button>
                </form>
                <div class="table"></div>
                <div id="modal" style="display:none;"></div>
            </div>
            <jsp:include page="../base/footer.jsp" />
        </div>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <script src="../assets/js/base/schedule.js" type="text/javascript"></script>
    </body>
</html>
