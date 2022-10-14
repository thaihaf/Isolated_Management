<%-- 
    Document   : testdetail
    Created on : Sep 18, 2022, 2:37:47 PM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update test detail - IMS</title>
        <link rel="stylesheet" href="../assets/css/base2.css"/>
        <link rel="stylesheet" href="../assets/css/base/home.css"/>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form method="POST" action="testupdate">
                    ID: ${result.id}<input type="hidden" name="id" value="${result.id}"/><br/>
                    Name: <input type="text" name="name" readonly value="${result.patientAccount.fullName}"/><br/>
                    Result: <input type="radio" name="result" <c:if test="${result.result eq true}">checked="checked"</c:if> value="true"/> Positive <input type="radio" name="result" <c:if test="${result.result eq false}">checked="checked"</c:if> value="false"/> Negative<br/>
                        Test type: <select name="type">
                        <c:forEach items="${requestScope.types}" var="t">
                            <option value="${t.id}" <c:if test="${result.testType.id eq t.id}">selected="selected"</c:if>>${t.typeName}</option>
                        </c:forEach>
                    </select><br/>
                    Person test: <select name="testperson">
                        <c:forEach items="${requestScope.doctor}" var="d">
                            <option value="${d.account.userName}" <c:if test="${result.personTest.account.userName eq d.account.userName}">selected="selected"</c:if>>${d.fullName}</option>
                        </c:forEach>
                    </select><br/>
                    Status: <input type="radio" name="status" <c:if test="${result.status eq true}">checked="checked"</c:if> value="true"/> Active <input type="radio" name="status" <c:if test="${result.status eq false}">checked="checked"</c:if> value="false"/> Inactive<br/>
                        <input type="submit" value="Save"/>
                    </form>
                </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
