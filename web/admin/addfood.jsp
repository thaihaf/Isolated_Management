<%-- 
    Document   : addfood
    Created on : Oct 13, 2022, 6:28:04 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="addfood" method="post">
            <span style="color: red;">${mess}</span><br>
            <input type="hidden" name="id" value="">
            Food name: <input required type="text" name="food" value=""><br>
            Type: <select name="type">
                <option value="Drink">Drink</option>
                <option value="Food">Food</option>
            </select><br>
            Added date: <input required type="date" name="addedDate" value=""><br>
            <input type="Submit" value="Add">
            <button><a style="text-decoration: none;" href="foodlist">Return to Food List</a></button>
        </form>
    </body>
</html>
