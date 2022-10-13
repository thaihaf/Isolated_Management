<%-- 
    Document   : reportlist
    Created on : Oct 11, 2022, 1:52:11 AM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="../assets/js/paginate.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="../base/sidebar.jsp" />

        <jsp:include page="../base/header.jsp" />

        <div class="wrapper wrapperUser">
            <div class="container">
                <form action="nurseReport" method="POST">
                    <select name="patient">
                        <option value="-1">All patients</option>
                        <c:forEach items="${requestScope.patients}" var="p">
                            <option value="${p.account.userName}" <c:if test="${param.patient eq p.account.userName}">selected="selected"</c:if>>${p.fullName}</option>
                        </c:forEach>
                    </select>
                    <select name="room">
                        <option value="-1">All rooms</option>
                        <c:forEach items="${requestScope.rooms}" var="r">
                            <option value="${r.id}" <c:if test="${param.room eq r.id}">selected="selected"</c:if>>${r.name}</option>
                        </c:forEach>
                    </select>
                    <input type="submit" value="Search"/>
                </form>
                <a href="#">Add new report</a>
                <c:if test="${requestScope.reports.isEmpty() eq true}">
                    <div>There is no test report of patients that you are manage.</div>
                </c:if>
                <c:if test="${requestScope.reports.isEmpty() eq false}">
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Patient name</th>
                            <th>Created Date</th>
                            <th>Note</th>
                        </tr>
                        <c:forEach items="${requestScope.reports}" var="re">
                            <tr>
                                <td>${re.id}</td>
                                <td>${re.patient.fullName}</td>
                                <td>${re.createdDate}</td>
                                <td><c:if test="${re.note eq null}">None</c:if><c:if test="${re.note ne null}">${re.note}</c:if></td>
                                </tr>
                        </c:forEach>
                    </table>
                    <div id="paginate"></div>
                    <script>
                        paginate("paginate",${requestScope.pageindex},${requestScope.totalpage}, 1, "${pageContext.servletContext.contextPath}/base/nurseReport");
                    </script>
                </c:if>
            </div>
            <jsp:include page="../base/footer.jsp" />   
        </div>

    </body>
</html>
