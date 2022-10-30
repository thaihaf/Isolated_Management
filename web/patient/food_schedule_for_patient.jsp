<%-- 
    Document   : food_schedule_for_patient
    Created on : Oct 26, 2022, 9:23:16 PM
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
    <body>
        <form action="food_schedule_for_patient" method="post">
            <input type="hidden" name="userName" value="${f.userName}">
            <table>
                <tr>
                    <td>
                        <select name="dayFrom">
                            <c:forEach items="${requestScope.fs}" var="fs">
                                <option value="${fs.dayFrom}">${fs.dayFrom} - ${fs.dayTo}</option>
                            </c:forEach>
                        </select>
                    </td>
                    <td>Monday</td>
                    <td>Tuesday</td>
                    <td>Wednesday</td>
                    <td>Thursday</td>
                    <td>Friday</td>
                    <td>Saturday</td>
                    <td>Sunday</td>
                </tr>
                <tr>
                    <td>Buổi sáng</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Buổi trưa</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td>Buổi chiều</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </form>
    </body>
</html>
