<%-- 
    Document   : notifList
    Created on : Oct 14, 2022, 3:10:47 PM
    Author     : Mountain
--%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/view/notiflist.css"/>
        <script src="../assets/js/notifList.js" type="text/javascript"></script>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />

        <div class="wrapper wrapperUser">
            <div class="container">
                <h1 style="text-align: center;">Notification List</h1>
                <c:if test="${requestScope.notifs.size() le 0}">
                    <div>There is no notification on this account.</div> 
                </c:if>
                <c:if test="${requestScope.notifs.size() gt 0}">
                    <table>
                        <tr>
                            <th>Title</th>
                            <th>Content</th>
                            <th>Time</th>
                            <th colspan="2">Action</th>
                        </tr>
                        <c:forEach items="${requestScope.notifs}" var="n">
                            <tr>
                                <td <c:if test="${n.readMark eq false}">class="notreaded"</c:if>>${n.title}</td>
                                <td <c:if test="${n.readMark eq false}">class="notreaded"</c:if>>${n.content}</td>
                                <td><fmt:formatDate value="${n.createdDate}" pattern="dd-MM-yyyy HH:mm:ss"/></td>
                                <c:if test="${n.readMark eq false}">
                                    <td><a href="markasread?id=${n.id}">Mark as read</a></td>
                                </c:if>
                                <td><a href="javascript:deleteConfirm(${n.id})">Delete</a></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
