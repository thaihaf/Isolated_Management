<%-- 
    Document   : food_schedule_for_nurse
    Created on : Oct 26, 2022, 9:22:58 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            user-select: none;
        }
        table, th, td {
            border:1px solid black;
        }
    </style>
    <body>
        <form action="food_schedule_for_nurse" method="get">
            <c:forEach items="${requestScope.fsns}" var="fsns">
            <input type="hidden" name="userName" value="${fsns.userName}">
            </c:forEach>
            <table>
                <tr>
                    <td>Date</td>
                    <td>Meal - Time</td>
                    <td>Food</td>
                    <td>Quantity</td>
                    <td>Note</td>
                    <td>Room</td>
                    <td>Assigned User</td>
                    <td></td>
                </tr>
                <c:forEach items="${requestScope.fsns}" var="fsns">
                        <tr>
                            <td>${fsns.date}</td>
                            <td>${fsns.meal} - ${fsns.time}</td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
