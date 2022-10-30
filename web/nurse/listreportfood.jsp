<%-- 
    Document   : listreportfood
    Created on : Oct 30, 2022, 12:39:50 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List reports Food</title>
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
        <form action="list_reports_food" method="post">
            <table>
                <tr>
                    <td>Ngày</td>
                    <td>Thời gian</td>
                    <td>Tài khoản phản hồi</td>
                    <td>Nội dung phản hồi về đồ ăn</td>
                    <td></td>
                </tr>
                <c:forEach items="${requestScope.frs}" var="frs">
                <tr>
                    <td>${frs.dateReportFood}</td>
                    <td>${frs.timeReportFood}</td>
                    <td>${frs.userName}</td>
                    <td>${frs.reportFood}</td>
                    <td><button><a style="text-decoration: none" href="deletereportfood?id=">Đã xử lý</a></button></td>
                </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
