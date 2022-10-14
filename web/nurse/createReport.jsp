<%-- 
    Document   : createReport
    Created on : Oct 12, 2022, 1:55:58 AM
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
        <jsp:include page="../base/sidebar.jsp" />

        <jsp:include page="../base/header.jsp" />

        <div class="wrapper wrapperUser">
            <div class="container" style="text-align: center;">
                <form action="add_report" method="POST">
                    Patient: <select name="patient" style="width: 25%; padding: 2px 2px; border: 1px solid #ccc; border-radius: 4px; background-color: #f1f1f1;">
                        <c:forEach items="${requestScope.patient}" var="p">
                            <option value="${p.account.userName}">${p.fullName}</option>
                        </c:forEach>
                    </select><br/>
                    Note:<br/> <textarea name="note" style="resize:none; width:35%; height: 150px; padding: 5px 5px; box-sizing: border-box; border: 2px solid #ccc; border-radius: 4px; background-color: #f8f8f8;"></textarea><br/>
                    <input type="submit" value="Add"/>
                </form>
            </div>
            <jsp:include page="../base/footer.jsp" />   
        </div>
    </body>
</html>
