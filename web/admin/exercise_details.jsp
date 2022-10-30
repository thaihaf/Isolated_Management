<%-- 
    Document   : exercise_details
    Created on : Oct 30, 2022, 7:41:55 PM
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
    </head>
    <body>
        <c:set var="ex" value="${exercise}" scope="request"></c:set>
        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />

        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form id="exerciseForm">
                    <input type="hidden" name="id" value="${ex.id}"/>
                    <table>
                        <tr>
                            <td>Exercise name:</td>
                            <td><input type="text" value="${ex.name}" name="name"/></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Exercise description:</td>
                            <td><textarea name="note">${ex.note}</textarea></td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Current exercise source:</td>
                            <td id='source'>
                                <input type='hidden' id="srcType" name="srcType" value="${ex.sourceType.id}"></div>
                                <input type="hidden" id="srcContent" name="srcContent" value="${ex.source}"></div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td>Change exercise source:</td>
                            <td><input id="change_source" type="checkbox" name="change_source"/></td>
                        </tr>
                        <tr>
                            <td>Exercise source:</td> 
                            <td><c:forEach items="${requestScope.sourcetype}" var="st"><input type="radio" name="type" id="type" value="${st.id}" disabled/>${st.type}</c:forEach></td>
                                <td></td>    
                            </tr>
                            <tr>
                                <td>Exercise content:</td> 
                                <td><input type="text" name="src" id="src" disabled/></td>
                                <td></td>
                            </tr>
                            <tr>
                                <td colspan="3">
                                    <button type="button" id="btnSubmit">Save</button>
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
        <script src="../assets/js/admin/exercise_edit.js" type="text/javascript"></script>
    </body>
</html>
