<%-- 
    Document   : listpatient
    Created on : Sep 25, 2022, 3:52:50 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.ListPatient" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List Patient</title>
    </head>
    <style>
        *{
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            user-select: none;
        }
        table, th, td {
            border:2px solid black;
        }
    </style>
    <body>
        <form action="listpatient" method="get">
            Search Patient <input type="text" name="searchPatient">
            <input type="submit" value="Search"><br>
            <table>
                <tr style="color: red;">
                    <td>Username</td>
                    <td>Full name</td>
                    <td>Gender</td>
                    <td>Address</td>
                    <td>National</td>
                    <td>Phone</td>
                    <td>Email</td>
                </tr>
                <c:forEach items="${requestScope.lps}" var="lps">
                    <tr>
                        <td><a>${lps.username}</a></td>
                        <td>${lps.fullname}</td>
                        <c:if test="${lps.gender eq 'True'}">
                            <td>Male</td>
                        </c:if>
                        <c:if test="${lps.gender eq 'False'}">
                            <td>Female</td>
                        </c:if>
                        <td>${lps.address}</td>
                        <td>${lps.nation}</td>
                        <td>${lps.phone}</td>
                        <td>${lps.email}</td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
