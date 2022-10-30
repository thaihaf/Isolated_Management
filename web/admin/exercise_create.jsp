<%-- 
    Document   : exercise_create
    Created on : Oct 29, 2022, 8:39:47 AM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
        <meta name="referrer" content="no-referrer">
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />

        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form action="exercise_create" method="POST" id="exerciseForm" name="exerciseForm" enctype="multipart/form-data">
                    <h2>Create exercise</h2>
                    <table>
                        <tr>
                            <td>Exercise name:</td>
                            <td><input name="name" type="text"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Exercise description:</td> 
                            <td><textarea name="note"></textarea></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Exercise source:</td> 
                            <td><c:forEach items="${requestScope.sourcetype}" var="st"><input type="radio" name="type" id="type" value="${st.id}"/>${st.type}</c:forEach></td>
                                <td></td>    
                            </tr>
                            <tr>
                                <td>Exercise content:</td> 
                                <td><input type="text" name="src" id="src" disabled/></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <button type="button" id="btnSubmit">Add</button>
                                </td>
                                <td></td>
                            </tr>
                        </table>
                    </form>
                </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.2/dist/additional-methods.js"></script>
        <script src="../assets/js/admin/exercise_create.js" type="text/javascript"></script>
    </body>
</html>
