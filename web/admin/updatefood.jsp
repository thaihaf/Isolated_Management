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
        <title>Update Food</title>
        <link rel="stylesheet" href="../assets/css/base2.css"/>
        <link rel="stylesheet" href="../assets/css/base/home.css"/>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
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
                    Quantity: <input required type="text" name="quantity" value="${food.quantity}"><br>
                    Added date: <input type="date" name="addedDate" value="${food.addedDate}"><br>
                    Sources of Supply: <input required type="text" name="sourcesOfSupply" value="${food.sourcesOfSupply}"><br>
                    Date of manufacture: <input required type="date" name="dateOfManufacture" value="${food.dateOfManufacture}"><br>
                    Expiry: <input required type="date" name="expiry" value="${food.expiry}"><br>
                    <button>Change</button>
                    <button><a onclick="confirmDelete(${food.id})" style="text-decoration: none" href="#">Delete</a></button>
                    <button><a style="text-decoration: none" href="foodlist">Return to Food List</a></button><br>
                </form>
            </div>
            <jsp:include page="/base/footer.jsp" />
        </div>
        <script>
            function confirmDelete(id) {
                var result = confirm("Confirm to delete ?");
                if (result === true) {
                    alert("Delete food success!");
                    window.location.href = 'deletefood?id='+id;
                }
            }
        </script>
    </body>
</html>
