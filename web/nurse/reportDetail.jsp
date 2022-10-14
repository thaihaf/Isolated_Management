<%-- 
    Document   : reportDetail
    Created on : Oct 13, 2022, 12:56:46 AM
    Author     : Mountain
--%>

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
            <div class="container" style="text-align:center;">
                <h1>Report details</h1>
                <form action="reportDetail" method="POST">
                    <input type="hidden" value="${requestScope.report.id}" name="id"/>
                    Report for patient: ${requestScope.report.patient.fullName}<br/>
                    Note:<br/> <textarea name="note" style="resize:none; width:35%; height: 150px; padding: 5px 5px; box-sizing: border-box; border: 2px solid #ccc; border-radius: 4px; background-color: #f8f8f8;">${requestScope.report.note}</textarea><br/>
                    <input type="submit" value="Save"/>
                </form>
            </div>
            <jsp:include page="../base/footer.jsp" />   
        </div>
    </body>
</html>
