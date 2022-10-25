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
        <title>Food List</title>
        <!--        <link rel="stylesheet" href="../assets/css/base2.css"/>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
                <link rel="stylesheet" href="../assets/css/doctor/create_prescription.css"/>-->
        <link rel="stylesheet" href="../assets/css/base2.css"/>
        <link rel="stylesheet" href="../assets/css/base/home.css"/>
    </head>
    <style>
        /*        *{
                    margin: 0;
                    padding: 0;
                    box-sizing: border-box;
                    user-select: none;
                }
                table, th, td {
                    border:1px solid black;
                }*/
    </style>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
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
            </div>
            <jsp:include page="/base/footer.jsp" />
        </div>

        <!--        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
                <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
                <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
                <script src="../assets/js/doctor/perscriptionList.js" type="text/javascript"></script>-->
    </body>
</html>
