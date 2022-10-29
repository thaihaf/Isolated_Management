<%-- 
    Document   : viewSymptom
    Created on : Oct 29, 2022, 2:55:40 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/base/home.css"/>     
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../base/sidebar.jsp" />
        <jsp:include page="../base/header.jsp" />
        <div class="wrapper wrapperUser">
            <div class="container">
                <!--Code vào đây là oke-->
                <div>
                    <h1>SYMPTOM REPORTS</h1>
                    <a href="updateSymptom">Update latest symptom report click here</a>  
                </div>
                <table class="table">
                    <tr style="color: red;">
                        <td></td>
                        <td>Patient ID</td>
                        <td>Symptom</td>
                        <td>Date Create</td>
                    </tr>
                    <c:set var="count" value="${1}" />
                    <p class="text-danger">${mess}</p>
                    <c:forEach items="${requestScope.symptoms}" var="s">
                        <tr>
                            <td>${count}</td>
                            <td>${s.username.account.userName}</td>
                            <c:if test="${s.note eq null}">
                                <td>No Symptom</td>
                            </c:if>
                            <c:if test="${s.note ne null}">
                                <td>${s.note}</td>
                            </c:if>
                            <td>${s.date}</td>
                        </tr>
                        <c:set var="count" value="${count+1}" />  
                    </c:forEach>
                </table>
            </div>
            <jsp:include page="../base/footer.jsp" />   
        </div>
    </body>
</html>
