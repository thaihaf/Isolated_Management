<%-- 
    Document   : foodlist
    Created on : Oct 7, 2022, 5:07:39 PM
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
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <form action="foodlist" method="get"> 
            <h1>FOOD LISTS</h1>
            <button><a style="text-decoration: none" href="addfood">Add more food</a></button>
            <table>
                <tr>
                    <td>Name</td>
                    <td>Type</td>
                    <td>Added Date</td>
                    <td></td>
                </tr>
                <c:forEach items="${requestScope.food}" var="food">
                <tr>
                    <td>${food.name}</td>
                    <td>${food.type}</td>
                    <td>${food.addedDate}</td>
                    <td><button><a style="text-decoration: none" href="updatefood?id=${food.id}">Edit</a></button></td>
                </tr>
                </c:forEach>
            </table>
        </form>
        <jsp:include page="/base/footer.jsp" />
    </body>
</html>
