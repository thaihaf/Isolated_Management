<%-- 
    Document   : profile
    Created on : Sep 16, 2022, 1:59:03 AM
    Author     : Mountain
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
        <form action="update" method="POST">
            <input type="hidden" value="${account.account.userName}"/>
            Full name: <input type="text" name="fullname" value="${account.fullName}"/><br/>
            Gender: <Input type="radio" name="gender" value="true" <c:if test="${account.gender eq true}">checked="checked"</c:if> /> Male <Input type="radio" name="gender" value="false" <c:if test="${account.gender eq false}">checked="checked"</c:if> /> Female <br/>
            Phone: <input type="text" name="phone" value="${account.phone}"/><br/>
            Address: <input type="text" name="address" value="${account.address}"/><br/>
            Email: <input type="text" value="${account.email}" disabled/><br/>
            Nation: <input type="text" name="nation" value="${account.nation}"/><br/>
            Role: <select name="role">
                <c:forEach items="${requestScope.roles}" var="r">
                    <option value="${r.id}" <c:if test="${account.account.role.id eq r.id}">selected="selected"</c:if>>
                        ${r.role}
                    </option>
                </c:forEach>
            </select><br/>
            <c:if test="${requestScope.account.account.role.id eq 2 || requestScope.account.account.role.id eq 3}">
                Level of education: <input type="text" name="lvlofedu" value="${requestScope.medical.levelOfEducation}"/><br/>
                Hospital: <input type="text" name="hospital" value="${requestScope.medical.hospital}"/><br/>
            </c:if>
            <c:if test="${requestScope.account.account.role.id eq 4}">
                Room: ${requestScope.patient.room.name}<br/>
                Area: ${patient.room.area.name}<br/>
                Background disease: <c:if test="${requestScope.patient.backgroundDisease eq true}">Yes</c:if>
                <c:if test="${requestScope.patient.backgroundDisease eq false}">No</c:if><br/>
                Blood type: <input type="text" name="blood" value="${patient.bloodType}"/><br/>
            </c:if>
            <input type="submit" value="Save">
        </form>
    </body>
</html>
