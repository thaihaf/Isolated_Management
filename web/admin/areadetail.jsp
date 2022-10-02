<%-- 
    Document   : areadetail
    Created on : Oct 1, 2022, 4:37:36 AM
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
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form action="area" method="POST">
                    <input type="hidden" name="id" value="${requestScope.area.id}"/>
                    Area name: <input type="text" name="name" value="${requestScope.area.name}"/><br/>
                    Area address: <input type="text" name="address" value="${requestScope.area.address}"/><br/>
                    Area type: <select name="areatype">
                        <c:forEach items="${requestScope.areatype}" var="a">
                            <option <c:if test="${requestScope.area.areaType.id eq a.id}">selected</c:if> value="${a.id}">${a.type}</option>
                        </c:forEach>
                    </select><br/>
                    <input type="hidden" name="status_before" value="${requestScope.area.available}"/>
                    Status: <input type="radio" name="status" value="true" <c:if test="${requestScope.area.available eq true}">checked</c:if>/>Active <input type="radio" name="status" value="false" <c:if test="${requestScope.area.available eq false}">checked</c:if>/>Inactive<br/>
                        <div>
                            Edit individual rooms:
                        </div>
                        <ul>
                        <c:forEach items="${requestScope.area.rooms}" var="r">
                            <li>Room <a href="room?id=${r.id}">${r.name}</a></li>
                            </c:forEach>
                    </ul>
                    <input type="submit" value="Save"/>
                </form>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
