<%-- 
    Document   : sched_list
    Created on : Oct 26, 2022, 3:10:06 AM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../assets/js/admin/sched_manage.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />

        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form id="view">
                    Account: <select name="account">
                        <c:forEach items="${requestScope.account}" var="a">
                            <option value="${a.account.userName}">${a.fullName}</option>
                        </c:forEach>
                    </select><br/>
                    Year: <select name="year" id="year">
                        <c:forEach items="${requestScope.year}" var="year">
                            <option value="${year}" <c:if test="${year eq requestScope.currentyear}">selected="selected"</c:if> >${year}</option>
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
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>

    </body>
</html>
