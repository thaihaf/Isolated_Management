<%-- 
    Document   : foodmenu
    Created on : Oct 12, 2022, 10:23:12 PM
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
        <form method="post" action="foodmenu">
            <table>
                <tr>
                    <td>
                        <select>
                            <c:forEach items="${requestScope.foodmenus}" var="foodmenus">
                            <option>${foodmenus.dayFrom} - ${foodmenus.dayTo}</option>
                        </c:forEach>
                        </select>
                    </td>
                    <td>Monday<br>
                        <p name="day"></p>
                    </td>
                    <td>Tuesday</td>
                    <td>Wednesday</td>
                    <td>Thursday</td>
                    <td>Friday</td>
                    <td>Saturday</td>
                    <td>Sunday</td>
                </tr>
                <c:forEach items="${requestScope.foodmenus}" var="foodmenus">
                <tr>
                    <td></td>
                    <td>${foodmenus.name}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                </c:forEach>
            </table>
            
            <!--<input type="datetime-local">--> 
        </form>
    </body>
</html>
