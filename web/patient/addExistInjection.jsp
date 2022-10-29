<%-- 
    Document   : addExistInjection
    Created on : Oct 24, 2022, 2:59:11 AM
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

    <body>
        <jsp:include page="../base/sidebar.jsp" />
        <jsp:include page="../base/header.jsp" />
        <div class="wrapper wrapperUser">
            <form action="addExistInjection" method="POST" class="profilForm container">
                <div class="container">
                    <!--Code vào đây là oke-->

                    <h2>Add injection</h2>
                    Vaccine Type:
                    <select name="vaccine" style="margin-right: 60px">
                        <c:forEach items="${requestScope.vaccine}" var="v">
                            <option value="${v.id}" style="font-size: 15px">${v.vaccineName}</option>
                        </c:forEach>
                    </select> 
                    Injection Date:<input style="margin-right: 60px" type="date" class="item_input" name="date">
                    <input type="hidden" name="creator" value="">
                    <input type="hidden" name="taken" value="false">
                    Note:<input type="text" name="note" style="margin-right: 60px">
                    <input type="submit" value="ADD Injection">
                </div>
            </form>
            <jsp:include page="../base/footer.jsp" />   
        </div>
    </body>
</html>
