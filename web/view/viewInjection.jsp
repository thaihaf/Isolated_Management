<%-- 
    Document   : viewInjection
    Created on : Oct 1, 2022, 12:11:05 AM
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
        <script>
            function removeStu(id)
            {
                var result = confirm('are you sure?');
                if (result)
                {
                    window.location.href = 'deleteInjection?id=' + id;
                }
            }
        </script>
    </head>

    <jsp:include page="../base/sidebar.jsp" />

    <jsp:include page="../base/header.jsp" />
    <body>
        <c:set var="role" value="${sessionScope.account.role}" />
        <div class=<c:if test="${role.id eq 1}">"wrapper wrapperAdmin"</c:if>
             <c:if test="${role.id ne 1}">"wrapper wrapperUser"</c:if>
                 >
                 <div class="container">
                     <!--Code vào đây là oke-->
                     <h1>Patient ${patient}'s injection history</h1>
                 <table class="table">
                     <tr style="color: red;">
                         <td></td>
                         <td>Patient ID</td>
                         <td>Vaccine</td>
                         <td>Nurse Inject</td>
                         <td>Inject Date</td>
                         <td>Note</td>
                         <td>Take In Qurantine Centre</td>
                     </tr>
                     <c:if test="${role.id eq 3}">
                         <c:set var="count" value="${1}" />
                         <c:forEach items="${requestScope.lists}" var="l">
                             <tr>
                                 <td>${count}</td>
                                 <td>${l.patientAccount.account.userName}</td>
                                 <td>${l.vaccine.vaccineName}</td>
                                 <td><c:choose>
                                         <c:when test="${l.personInject.fullName eq null}">
                                             None
                                         </c:when>
                                         <c:when test="${l.personInject.fullName ne null}">
                                             ${l.personInject.fullName}
                                         </c:when>
                                     </c:choose></td>
                                 <td><c:choose>
                                         <c:when test="${l.date eq null}">
                                             Not Remember
                                         </c:when>
                                         <c:when test="${l.date ne null}">
                                             ${l.date}
                                         </c:when>
                                     </c:choose></td>
                                 <td><c:choose>
                                         <c:when test="${l.note eq null}">
                                             None
                                         </c:when>
                                         <c:when test="${l.note ne null}">
                                             ${l.note}
                                         </c:when>
                                     </c:choose></td>
                                 <td><input type="checkbox" 
                                            <c:if test="${l.taken}">
                                                checked="checked"
                                            </c:if>
                                            /></td>
                                 <td><c:if test="${l.taken eq true}">
                                     <td><input type="button" value="Delete" onclick="removeStu(${l.id})"></td>
                                     </c:if>
                                 </td>
                             </tr>
                             <c:set var="count" value="${count+1}" />  
                         </c:forEach>
                     </c:if>
                     <c:if test="${role.id eq 4}">
                         <c:set var="count" value="${1}" />
                         <c:forEach items="${requestScope.lists}" var="l">
                             <tr>
                                 <td>${count}</td>
                                 <td>${l.patientAccount.account.userName}</td>
                                 <td>${l.vaccine.vaccineName}</td>
                                 <td><c:choose>
                                         <c:when test="${l.personInject.fullName eq null}">
                                             None
                                         </c:when>
                                         <c:when test="${l.personInject.fullName ne null}">
                                             ${l.personInject.fullName}
                                         </c:when>
                                     </c:choose></td>
                                 <td><c:choose>
                                         <c:when test="${l.date eq null}">
                                             Not Remember
                                         </c:when>
                                         <c:when test="${l.date ne null}">
                                             ${l.date}
                                         </c:when>
                                     </c:choose></td>
                                 <td><c:choose>
                                         <c:when test="${l.note eq null}">
                                             None
                                         </c:when>
                                         <c:when test="${l.note ne null}">
                                             ${l.note}
                                         </c:when>
                                     </c:choose></td>
                                 <td><input type="checkbox" 
                                            <c:if test="${l.taken}">
                                                checked="checked"
                                            </c:if>
                                            /></td>
                                 <td><c:if test="${l.taken eq false}">
                                     <td><input type="button" value="Delete" onclick="removeStu(${l.id})"></td>
                                     </c:if>
                                 </td>
                             </tr>
                             <c:set var="count" value="${count+1}" />            
                         </c:forEach>
                         <a href="addExistInjection?username=${sessionScope.account.userName}">Add injection</a>
                         <p>(Note: This is used for patient who already haved injection before coming to quarantine centre)</p>
                     </c:if>
                 </table>

             </div>
             <jsp:include page="../base/footer.jsp" />   
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>