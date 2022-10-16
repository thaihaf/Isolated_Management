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
        <!--        <link rel="stylesheet" href="../assets/css/base2.css"/>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
                <link rel="stylesheet" href="../assets/css/doctor/create_prescription.css"/>-->
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
                    Added date: <input type="date" name="addedDate" value="${food.addedDate}"><br>
                    <button>Change</button>
                    <button onclick="confirmDelete()"><a style="text-decoration: none" href="deletefood?id=${food.id}">Delete</a></button>
                    <button><a style="text-decoration: none" href="foodlist">Return to Food List</a></button><br>
                    <span style="color: red" id="sign"></span>
                </form>
            </div>
            <jsp:include page="/base/footer.jsp" />
        </div>




        <!--        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
                <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
                <script src="../assets/js/doctor/perscriptionList.js" type="text/javascript"></script>-->
        <script>
            function confirmDelete(confirmDelete) {
                confirm("Confirm to delete ?");
                if (confirm(text) == true) {
                    text = "Delete food success!";
                } else {
                    text = "Delete food fail!";
                }
            }

//            function confirmUpdate(confirmUpdate) {
//                confirm("Confirm to Change ?");
//                if (confirm(text) == true) {
//                    text = "Change food success!";
//                } else {
//                    text = "Change food fail!";
//                }
//            }
            document.getElementById("sign").innerHTML = text;
        </script>
    </body>
</html>
