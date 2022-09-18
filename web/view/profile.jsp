<%-- 
    Document   : profile
    Created on : Sep 17, 2022, 10:16:20 PM
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


        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />

        <div class=<c:if test="${role eq 'admin'}">"wrapper wrapperAdmin"</c:if>
             <c:if test="${role ne 'admin'}">"wrapper wrapperUser"</c:if>
                 >
                 <div class="container">
                     <form action="profile" method="POST">

                     <c:set var="role" value="${info.account.role.role}"/>
                     ID:${info.account.userName}</br></br>
                     Name:${info.fullName}</br></br>
                     Gender: <c:if test="${info.gender}">
                         Male
                     </c:if></br></br>
                     Phone:${info.phone}</br></br>
                     Address:${info.address}</br></br>
                     Email:${info.email}</br></br>
                     Nation:${info.nation}</br></br>
                     Role:${role}</br></br>
                     <c:if test="${role=='Doctor'}">
                         Level of education:${staff.education}</br></br>
                         Hospital:${staff.hospital}</br></br>
                     </c:if>
                     <c:if test="${role=='Nurse'}">
                         Level of education:${s.education}</br></br>
                         Hospital:${s.hospital}</br></br>
                     </c:if>
                     <c:if test="${role=='Patient'}">
                         Blood Type:${patient.bloodType}</br></br>
                         Note:${patient.note}</br></br>
                         Backgroud Diseases:${patient.backgroudDisea}</br></br>
                     </c:if>
                 </form>
             </div>
             <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
