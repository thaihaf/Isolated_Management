<%-- 
    Document   : exercise_assign
    Created on : Oct 30, 2022, 11:01:28 PM
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
        <link href="../assets/css/admin/exercise_assign.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/smoothness/jquery-ui.css" />
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />

        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form id="assignForm" onsubmit="return handleData()" action="assign_exercise" method="POST">
                    <h2 style="text-align: center;">Assign exercise to schedule</h2>
                    <table>
                        <tr>
                            <td>Schedule to assign:</td>
                            <td>
                                <select name="schedule">
                                    <c:forEach items="${requestScope.schedule}" var="s">
                                        <option value="${s.id}">${s.room.name} - ${s.assignedUser.fullName} - <fmt:parseDate  value="${s.date}"  type="date" pattern="yyyy-MM-dd" var="parsedDate" /><fmt:formatDate pattern="dd-MM-yyyy" value="${parsedDate}"/> - ${s.time.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>Exercise to select:</td>
                            <td>
                                <label for="ex"/>
                                <c:forEach items="${requestScope.exercise}" var="e">
                                    <input class="checkbox-group" type="checkbox" name="ex[]" value="${e.id}"/> ${e.name} <br/>
                                </c:forEach>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <button id="submitBtn">Add</button>
                            </td>
                        </tr>
                    </table>
                </form>
                <div style="visibility:hidden; color:red; " id="error">Please select at least 1 exercise.</div>
                <div id="response"></div>
            </div>
            <jsp:include page="/base/footer.jsp" />   

        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.2/dist/additional-methods.js"></script>
        <script src="../assets/js/admin/exercise_schedule.js" type="text/javascript"></script>
    </body>
</html>
