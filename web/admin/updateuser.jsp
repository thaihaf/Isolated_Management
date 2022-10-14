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
        <title>Update user account - IMS</title>
        <link rel="stylesheet" href="../assets/css/base2.css"/>
        <link rel="stylesheet" href="../assets/css/base/home.css"/>
        <script>
            function changeRole() {
                var z = document.getElementById("role").value;
                var content;
                switch (z) {
                    case '1':
                    {
                        content = "";
                        break;
                    }
                    case '2':
                    case '3':
                    {
                        content = "Level of education: <input type=\"text\" name=\"lvlofedu\" value=\"${requestScope.medical.levelOfEducation}\"/><br/>";
                        content += "Hospital: <input type=\"text\" name=\"hospital\" value=\"${requestScope.medical.hospital}\"/><br/>";
                        break;
                    }
                    case '4':
                    {
                        content = "Room: ${requestScope.patient.room.name}<br/>" +
                                "Area: ${patient.room.area.name}<br/>" +
                                "Background disease: <c:if test="${requestScope.patient.backgroundDisease eq true}">Yes</c:if>" +
                                "<c:if test="${requestScope.patient.backgroundDisease eq false}">No</c:if><br/>" +
                                "Blood type: <input type=\"text\" name=\"blood\" value=\"${patient.bloodType}\"/><br/>";
                        break;
                    }
                }
                document.getElementById('optional').innerHTML = content;
            }
        </script>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form action="updateuser" method="get">
                    <input type="hidden" value="${account.account.userName}"/>
                    Full name: <input required type="text" name="fullName" value="${account.fullName}"/><br/>
                    Gender: <input required type="radio" name="gender" value="true" <c:if test="${account.gender eq true}">checked="checked"</c:if> /> Male <Input type="radio" name="gender" value="false" <c:if test="${account.gender eq false}">checked="checked"</c:if> /> Female <br/>
                    Phone: <input required type="text" name="phone" value="${account.phone}"/><br/>
                    Address: <input required type="text" name="address" value="${account.address}"/><br/>
                    Email: <input required type="text" value="${account.email}"/><br/>
                    <!--Nationality: <input required type="text" name="nation" value="${account.nation}"/><br/>-->
                    Nationality: <select required name="nation" disabled>
                        <option value="${account.nation}" >${account.nation}</option>
                    </select><br>
                    Date Of Birth: <input type="date" name="dateofbirth" value="${account.dateofbirth}"/><br/>
                    <div id="optional">
                        <c:if test="${requestScope.account.account.role.id eq 2 || requestScope.account.account.role.id eq 3}">
                            Level of education: <input type="text" name="leveleducation" value="${requestScope.medical.levelOfEducation}"/><br/>
                            Hospital: <input type="text" name="hospital" value="${requestScope.medical.hospital}"/><br/>
                        </c:if>
                        <c:if test="${requestScope.account.account.role.id eq 4}">
                            Room: <input type="text" name="room" value="${requestScope.patient.room.name}"/><br/>
                            Area: <input type="text" name="area" value="${patient.room.area.name}"/><br/>
                            Background disease: 
                            <input type="radio" name="background_disease" value="${requestScope.patient.backgroundDisease}" <c:if test="${requestScope.patient.backgroundDisease eq true}">checked="checked"</c:if> />Yes
                            <input type="radio" name="background_disease" value="${requestScope.patient.backgroundDisease}" <c:if test="${requestScope.patient.backgroundDisease eq false}">checked=checked</c:if> />No<br/>
                            Blood type: <input type="text" name="blood_Type" value="${patient.bloodType}"/><br/>
                            Note <input type="text" name="note" value="${patient.note}"/><br/>
                        </c:if>
                    </div>
                    <input type="submit" value="Save">
                </form>
            </div>
            <jsp:include page="/base/footer.jsp" />
        </div>
    </body>
</html>
