<%-- 
    Document   : exercise_list.jsp
    Created on : Oct 30, 2022, 2:01:05 AM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.2/themes/smoothness/jquery-ui.css" />
        <link href="../assets/css/admin/exercise_list.css" rel="stylesheet" type="text/css"/>
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />

        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form action="exercise_list" method="POST">
                    Search: <input type="text" name="search" value="${param.search}"/>
                    <input type="submit" value="Search"><br/>
                </form>
                <c:if test="${requestScope.exercise.size() eq 0}">
                    There is no such exercise in database.
                </c:if>
                <c:if test="${requestScope.exercise.size() ne 0}">
                    <table>
                        <tr>
                            <th>Name</th>
                            <th>Note</th>
                            <th>Source</th>
                            <th colspan="2">Action</th>
                        </tr>
                        <c:forEach items="${requestScope.exercise}" var="e">
                            <tr>
                                <td>${e.name}</td>
                                <td>${e.note}</td>
                                <td>
                                    <c:if test="${e.sourceType.id eq 1}">None</c:if>
                                    <c:if test="${e.sourceType.id ne 1}"><a href="#${e.id}" id="src" data="${e.source}" data-id="${e.sourceType.id}">${e.sourceType.type}</a></c:if>
                                    </td>
                                    <td>
                                        <a href="exercise_edit?id=${e.id}">Edit</a>
                                </td>
                                <td>
                                    <button id="btnDelete" data-id="${e.id}">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div id="paginate" class="paginate"></div>
                    <script src="../assets/js/paginate.js" type="text/javascript"></script>
                    <script>
                        paginate("paginate",${requestScope.pageindex},${requestScope.totalpage}, 1, "${pageContext.servletContext.contextPath}/admin/exercise_list");
                    </script>
                </c:if>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
        <div id="content" style="display:none;"></div>
        <script src="https://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
        <script src="../assets/js/admin/exercise_source.js" type="text/javascript"></script>
    </body>
</html>
