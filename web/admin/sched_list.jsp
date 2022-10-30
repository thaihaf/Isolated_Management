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
        <link href="../assets/css/admin/sched_create.css" rel="stylesheet" type="text/css"/>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../assets/js/admin/sched_manage.js" type="text/javascript"></script>
        <style type="text/css">
            <%@include file="../assets/css/admin/sched_create.css"%>
        </style>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />

        <div class="wrapper wrapperAdmin">
            <div class="container">
                <h2 style="line-height: 2rem;margin-bottom:1rem;">Search schedule</h2>
                <form id="view">
                    <div class="row" style="justify-content: center;column-gap:2rem;">
                        <div class="schedule-column">
                            <label for="selectPerson" class="schedule-title">Account:</label>
                            <select id="selectPerson" class="schedule__form-control" name="account">
                                <c:forEach items="${requestScope.account}" var="a">
                                    <option value="${a.account.userName}">${a.fullName}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="schedule-column">
                            <label for="year" class="schedule-title">Year</label>
                            <select name="year" id="year" class="schedule__form-control">
                                <c:forEach items="${requestScope.year}" var="year">
                                    <option value="${year}" <c:if test="${year eq requestScope.currentyear}">selected="selected"</c:if> >${year}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="row" style="justify-content: center;">
                        <div class="schedule-column">
                            <label for="year" class="schedule-title">Week</label>
                            <select style="width:415px;" name="week" id="year" class="schedule__form-control">
                                <c:forEach items="${requestScope.date}" var="date">
                                    <option value="${date.key}" <c:if test="${date.key eq requestScope.currentweek}">selected="selected"</c:if> >${date.value}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <button id="submitForm" type="button" class="button">View</button>
                </form>
                <h2 style="line-height: 2rem;margin-bottom:1rem;">Schedule List</h2>

                <div class="table" style="width:100%;"></div>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>

    </body>
</html>
