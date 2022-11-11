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
                var content = "<div style=\"min-width : 200px \" id=\"room_container_" + count + "\">";
                content += "Room name: <input type=\"text\" class=\"item_input\" name=\"room_name_" + count + "\" required/><br/>";
                content += "Room total beds: <input type=\"number\" class=\"item_input\" name=\"room_beds_" + count + "\" min=\"0\" value=\"0\"/><br/>";
                content += "Doctor manage: <select class=\"selection item_input\" name=\"doctor_" + count + "\">";
                content += "<option value=\"-1\">None</option>";
                content += "<c:forEach items='${requestScope.medical}' var='d'>"
                content += "<c:if test='${d.account.role.id eq 2}'>";
                content += "<option value=\"${d.account.userName}\">${d.fullName}</option>";
                content += "</c:if>";
                content += "</c:forEach>";
                content += "</select><br/>"
                content += "Nurse manage: <select class=\"selection item_input\" name=\"nurse_" + count + "\">";
                content += "<option value=\"-1\">None</option>";
                content += "<c:forEach items='${requestScope.medical}' var='d'>";
                content += "<c:if test='${d.account.role.id eq 3}'>";
                content += "<option value=\"${d.account.userName}\">${d.fullName}</option>";
                content += "</c:if>";
                content += "</c:forEach>";
                content += "</select><br/>";
                content += "<input type=\"button\" class=\"btn btn-danger mt-2 btn-add\" value=\"Remove\" onclick=\"removeArea(" + count + ")\"/>";
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

            <link rel="stylesheet" href="../assets/css/admin/notif_create2.css"/>
            <link rel="stylesheet" href="../assets/css/base.css"/>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
            <link rel="stylesheet" href="../assets/css/base/homeNew.css"/>
        </head>
        <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container-fluid">
                <div class="top1">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="../base/home.jsp">Home</a></li>
                            <li class="breadcrumb-item"><a href="areas">Area List</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Create Area</li>
                        </ol>
                    </nav>
                </div>

                <div class="top2 d-flex justify-content-between">
                    <h1>Create Area</h1>
                </div>

                <form action="add_area" method="POST" class="d-flex align-items-center justify-content-center">
                    <div class="updateInfo_actions d-block" style="resize:none; width:50%;" >
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Name</div>
                                <input type="text" class="item_input" name="name" required/>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Area Type</div>
                                <select name="areatype" class="selection item_input">
                                    <c:forEach items="${requestScope.areatype}" var="a">
                                        <option value="${a.id}">${a.type}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Address</div>
                                <input type="text" class="item_input" name="address" required/>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Status</div>
                                <div class="d-flex align-items-center">
                                    <input type="radio" class="mr-2" name="status" value="true" checked/>Active 
                                    <input type="radio" class="ml-5 mr-2" name="status" value="false"/>Inactive
                                </div>

                            </div>
                        </div>

                        <div id="room_container" class="updateInfo_group" style="overflow-x: auto">
                        </div>

                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <input type="submit" class="btn btn-success btn-add" value="Submit"/>

                            </div>
                            <div class="updateInfo_item right">
                                <input type="button" value="New Room" class="btn btn-primary btn-add" onclick="addArea()"/>
                            </div>
                        </div>
                    </div>


                </form>
            </div>
            <jsp:include page="/base/footer.jsp" />  
        </div>
    </body>
</html>
