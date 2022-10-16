<%-- 
    Document   : notif_create
    Created on : Oct 16, 2022, 4:10:38 AM
    Author     : Mountain
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <<link rel="stylesheet" href="../assets/css/admin/notif_create.css"/>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />

        <jsp:include page="/base/header.jsp" />

        <div class="wrapper wrapperUser">
            <div class="container" style="text-align: center;">
                <form action="notif_create" method="POST">
                    <h1>Create notification</h1>
                    Selection: <select name="choice" id="choice" class="selection" onchange="choiceChange()">
                        <option value="1" selected="selected">Send to individual account</option>
                        <option value="2">Send to all patients in specific room</option>
                        <option value="3">Send to specific role</option>
                    </select><br/>
                    <div id="selection">
                        Account to send: <select name="account" class="selection">
                            <c:forEach items="${requestScope.accounts}" var="a">
                                <option value="${a.account.userName}">${a.fullName}</option>
                            </c:forEach>
                        </select>
                    </div><br/>
                    Title: <input type="text" name="title" required/><br/>
                    Content:<br/>
                    <textarea name="content" style="resize:none; width:100%; height: 150px; padding: 5px 5px; box-sizing: border-box; border: 2px solid #ccc; border-radius: 4px; background-color: #f8f8f8;" required></textarea>
                    <input type="submit" value="Create"/>
                </form>
                <script>
                    function choiceChange() {
                        var content = document.getElementById('choice').value;
                        var selection;
                        switch (content) {
                            case '1':
                            {
                                selection = "Account to send: <select name=\"account\" class=\"selection\">";
                                selection += "<c:forEach items="${requestScope.accounts}" var="a">";
                                selection += "<option value=\"${a.account.userName}\">${a.fullName}</option>";
                                selection += "</c:forEach>";
                                selection += "</select>";
                                break;
                            }
                            case '2':
                            {
                                selection = "Room to send: <select name=\"room\" class=\"selection small\">";
                                selection += "<c:forEach items="${requestScope.rooms}" var="r">";
                                selection += "<option value=\"${r.id}\">${r.name}</option>";
                                selection += "</c:forEach>";
                                selection += "</select>";
                                break;
                            }
                            case '3':
                            {
                                selection = "Role to send: <select name=\"role\" class=\"selection small\">";
                                selection += "<c:forEach items="${requestScope.roles}" var="r">";
                                selection += "<option value=\"${r.id}\">${r.role}</option>";
                                selection += "</c:forEach>";
                                selection += "</select>";
                                break;
                            }
                            default:
                            {
                                selection = "";
                            }
                        }
                        document.getElementById('selection').innerHTML = selection;
                    }
                    </script>
                </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
