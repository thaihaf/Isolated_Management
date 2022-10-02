<%-- 
    Document   : newarea
    Created on : Sep 30, 2022, 10:24:20 PM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            <c:set var="medical" value="${requestScope.medical}"></c:set>
            var count = 0;
            function addArea() {
                count++;
                var container = document.getElementById('room_container');
                var content = "<div id=\"room_container_" + count + "\">";
                content += "Room name: <input type=\"text\" name=\"room_name_" + count + "\" required/><br/>";
                content += "Room total beds: <input type=\"number\" name=\"room_beds_" + count + "\" min=\"0\" value=\"0\"/><br/>";
                content += "Doctor manage: <select name=\"doctor_" + count + "\">";
                content += "<option value=\"-1\">None</option>";
                content += "<c:forEach items='${requestScope.medical}' var='d'>"
                content += "<c:if test='${d.account.role.id eq 2}'>";
                content += "<option value=\"${d.account.userName}\">${d.fullName}</option>";
                content += "</c:if>";
                content += "</c:forEach>";
                content += "</select><br/>"
                content += "Nurse manage: <select name=\"nurse_" + count + "\">";
                content += "<option value=\"-1\">None</option>";
                content += "<c:forEach items='${requestScope.medical}' var='d'>";
                content += "<c:if test='${d.account.role.id eq 3}'>";
                content += "<option value=\"${d.account.userName}\">${d.fullName}</option>";
                content += "</c:if>";
                content += "</c:forEach>";
                content += "</select><br/>";
                content += "<input type=\"button\" value=\"Remove\" onclick=\"removeArea(" + count + ")\"/>";
                content += "<input type=\"hidden\" name=\"index\" value=\"" + count + "\"/>";
                content += "</div>";
                container.innerHTML += content;
            }
            function removeArea(count) {
                var container = document.getElementById('room_container');
                var item = document.getElementById('room_container_' + count);
                container.removeChild(item);
            }
            </script>
        </head>
        <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form action="add_area" method="POST">
                    Area name: <input type="text" name="name" required/><br/>
                    Address: <input type="text" name="address" required/><br/>
                    Area type: <select name="areatype">
                        <c:forEach items="${requestScope.areatype}" var="a">
                            <option value="${a.id}">${a.type}</option>
                        </c:forEach>
                    </select><br/>
                    Status: <input type="radio" name="status" value="true" checked/>Active <input type="radio" name="status" value="false"/>Inactive<br/>
                    <input type="button" value="Add room to area" onclick="addArea()"/>
                    <div id="room_container">
                    </div>
                    <input type="submit" value="Add"/>
                </form>
            </div>
            <jsp:include page="/base/footer.jsp" />  
        </div>
    </body>
</html>
