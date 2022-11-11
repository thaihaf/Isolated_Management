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

        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/admin/sched_create.css"/>
        <link rel="stylesheet" href="../assets/css/base/homeNew.css"/>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />

        <div class="wrapper wrapperAdmin">
            <div class="container-fluid">
                <div class="top1">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="../base/home.jsp">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Schedule List</li>
                        </ol>
                    </nav>
                </div>

                <form class="top2 d-flex mb-5 align-items-center"  id="view">
                    <h1 class="mr-auto">Schedule Lists</h1>
                    <div class="schedule-column mr-2">
                        <label for="selectPerson" class="schedule-title">Account</label>
                        <select id="selectPerson" class="schedule__form-control" name="account">
                            <c:forEach items="${requestScope.account}" var="a">
                                <option value="${a.account.userName}">${a.fullName}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="schedule-column mr-2">
                        <label for="year" class="schedule-title">Year</label>
                        <select name="year" id="year" class="schedule__form-control">
                            <c:forEach items="${requestScope.year}" var="year">
                                <option value="${year}" <c:if test="${year eq requestScope.currentyear}">selected="selected"</c:if> >${year}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="schedule-column mr-2">
                        <label for="year" class="schedule-title">Week</label>
                        <select name="week" id="year" class="schedule__form-control">
                            <c:forEach items="${requestScope.date}" var="date">
                                <option value="${date.key}" <c:if test="${date.key eq requestScope.currentweek}">selected="selected"</c:if> >${date.value}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button id="submitForm" type="button" class="btn btn-primary btn-add ml-5">View</button>
                </form>

                <div class="table table-hover" style="width:100%;"></div>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="../assets/js/admin/sched_manage.js" type="text/javascript"></script>
    </body>
</html>
