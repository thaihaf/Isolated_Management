<%-- 
    Document   : updatefood
    Created on : Oct 15, 2022, 11:26:52 PM
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
        <form action="updatefood" method="get">
            <span style="color: red;">${mess}</span><br>
                <input type="hidden" name="id" value="${food.id}">
                Food name: <input type="text" name="food" value="${food.name}"><br>
                <c:if test="${food.type == 'Drink'}">
                    Type: <select name="type">
                        <option value="${food.type}">${food.type}</option>
                        <option value="Food">Food</option>
                    </select><br>
                </c:if>
                <c:if test="${food.type == 'Food'}">
                    Type: <select name="type">
                        <option value="${food.type}">${food.type}</option>
                        <option value="Drink">Drink</option>
                    </select><br>
                </c:if>
                Added date: <input type="date" name="addedDate" value="${food.addedDate}"><br>
                <input type="Submit" value="Change">
                <button><a style="text-decoration: none" href="deletefood?id=${food.id}">Delete</a></button>
                <button><a style="text-decoration: none" href="foodlist">Return to Food List</a></button><br>
        </form>
    </body>
</html>
