<%-- 
    Document   : testresult
    Created on : Sep 18, 2022, 11:11:27 AM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Test results - IMS</title>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
                <h1>Test Result List</h1><br/>
                <table>
                    <tr>
                        <th>ID</th>
                        <th>Patient name</th>
                        <th>Result</th>
                        <th>Test type</th>
                        <th>Test time</th>
                        <th>Person test</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach items="${requestScope.tests}" var="t">
                        <tr>
                            <td>${t.id}</td>
                            <td>${t.patientAccount.fullName}</td>
                            <td><c:if test="${t.result eq true}">Positive</c:if><c:if test="${t.result eq false}">Negative</c:if></td>
                            <td>${t.testType.typeName}</td>
                            <td>${t.testTime}</td>
                            <td>${t.personTest.fullName}</td>
                            <td><c:if test="${t.status eq true}">Active</c:if><c:if test="${t.status eq false}">Inactive</c:if></td>
                            <td><a href="detail?id=${t.id}">Edit</a></td>
                            </tr>
                    </c:forEach>
                </table>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
