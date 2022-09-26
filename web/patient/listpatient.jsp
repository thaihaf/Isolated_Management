<%-- 
    Document   : listpatient
    Created on : Sep 25, 2022, 3:52:50 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/base/home.css"/>     
    </head>

    <jsp:include page="../base/sidebar.jsp" />

    <jsp:include page="../base/header.jsp" />

    <div class=<c:if test="${role.id eq 1}">"wrapper wrapperAdmin"</c:if>
         <c:if test="${role.id ne 1}">"wrapper wrapperUser"</c:if>
             >
             <div class="container">
                 <!--Code vào đây là oke-->
                 <form action="listpatient" method="get">
                     Search Patient <input type="text" name="searchPatient">
                     <input type="submit" value="Search"><br><br>
                     <table class="table">
                         <tr style="color: red;">
                             <td>Username</td>
                             <td>Full name</td>
                             <td>Gender</td>
                             <td>Date Of Birth</td>
                             <td>Address</td>
                             <td>National</td>
                             <td>Phone</td>
                             <td>Email</td>
                             <td>Background Disease</td>
                             <td>Blood Type</td>
                         </tr>
                     <c:forEach items="${requestScope.patients}" var="p">
                         <tr>
                             <td>${p.accDetail.account.userName}</td>
                             <td>${p.accDetail.fullName}</td>
                             <c:if test="${p.accDetail.gender eq 'True'}">
                                 <td>Male</td>
                             </c:if>
                             <c:if test="${p.accDetail.gender eq 'False'}">
                                 <td>Female</td>
                             </c:if>
                             <td>${p.accDetail.dob}</td>
                             <td>${p.accDetail.address}</td>
                             <td>${p.accDetail.nation}</td>
                             <td>${p.accDetail.phone}</td>
                             <td>${p.accDetail.email}</td>
                             <c:if test="${p.backgroundDisease eq 'True'}">
                                 <td>Yes</td>
                             </c:if>
                             <c:if test="${p.backgroundDisease eq 'False'}">
                                 <td>No</td>
                             </c:if>
                             <td>${p.bloodType}</td>
                         </tr>
                     </c:forEach>
                 </table>
             </form>
         </div>
         <jsp:include page="../base/footer.jsp" />   
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
<!--<html>
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

    </body>
</html>-->
