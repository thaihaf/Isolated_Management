<%-- 
    Document   : updateSymptom
    Created on : Oct 30, 2022, 2:55:19 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/base/home.css"/>     
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="../base/sidebar.jsp" />
        <jsp:include page="../base/header.jsp" />
        <div class="wrapper wrapperUser">
            <form action="updateSymptom" method="POST" class="profilForm container">
                <div class="container">
                    <h1 style="text-align: center">Symptom Report</h1> 
                    Full Name:<input style="margin-right: 100px;margin-left: 5px" type="text" value="${info.fullName}">
                    Date Of Birth:<input style="margin-right: 100px;margin-left: 5px" type="text" value="${info.dateofbirth}">
                    Gender: <input type="radio" title="Male" class="btn_radioInput" style=" margin: 0 0 0 10px;" <c:if test="${info.gender eq true}">
                                   checked="checked"
                        </c:if>> Male
                    <input type="radio" title="Female" class="btn_radioInput" style=" margin: 0 0 0 10px;"<c:if
                               test="${info.gender eq false}">
                               checked="checked"
                           </c:if>> Female<br/><br/>
                    Phone:<input type="text" value="${info.phone}" style="margin-right: 100px;margin-left: 5px">
                    Address:<input type="text" value="${info.address}" style="margin-right: 100px; margin-left: 5px">
                    Nation:<input type="text" value="${info.nation}" style="margin-left: 5px"><br><br>
                    <h3>Symptoms</h3>
                    <input style="width: 100%; height: 100px; border-radius: 10px; text-align: center" type="text" value="${symptom.note}" name="symptom">
                    <br><br>
                    <input style="margin-left: 86%; width: 150px; border-radius: 10px" type="submit" value="Send">
                </div>
            </form>
            <jsp:include page="../base/footer.jsp" />   
        </div>
    </body>
</html>
