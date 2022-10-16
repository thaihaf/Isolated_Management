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
        <!--        <link rel="stylesheet" href="../assets/css/base2.css"/>
                <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
                <link rel="stylesheet" href="../assets/css/doctor/create_prescription.css"/>-->
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
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
