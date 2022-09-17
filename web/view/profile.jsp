<%-- 
    Document   : profile
    Created on : Sep 17, 2022, 7:54:21 PM
    Author     : hapro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="role" value="admin"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="./sidebar.jsp" />

        <jsp:include page="./header.jsp" />

        <div class=<c:if test="${role eq 'admin'}">"wrapper wrapperAdmin"</c:if>
             <c:if test="${role ne 'admin'}">"wrapper wrapperUser"</c:if>
                 >
                 <div class="container">
                     <form action="profile" method="POST">
                     <c:choose>
                         ID:</br></br>
                         <c:when test="${role=='doctor'}">
                             Name:</br></br>
                             Gender:</br></br>
                             Phone:</br></br>
                             Address:</br></br>
                         </c:when>
                         <c:when test="${role=='nurse'}">
                             ID:</br></br>
                             Name:</br></br>
                             Gender:</br></br>
                             Phone:</br></br>
                             Address:</br></br>
                         </c:when>
                         <c:when test="${role=='patient'}">
                             ID:</br></br>
                             Name:</br></br>
                             Gender:</br></br>
                             Phone:</br></br>
                             Address:</br></br>
                         </c:when>
                     </c:choose>
                 </form>
             </div>
             <jsp:include page="./footer.jsp" />   
        </div>
    </body>
</html>
