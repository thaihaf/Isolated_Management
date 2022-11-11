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
                            <li class="breadcrumb-item active" aria-current="page">Create Notification</li>
                        </ol>
                    </nav>
                </div>

                <div class="top2 d-flex justify-content-between">
                    <h1>Create Notification</h1>
                </div>

                <form action="notif_create" class="d-flex align-items-center justify-content-center"  method="POST">
                    <div class="updateInfo_actions d-block" style="resize:none; width:50%;" >
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Selection</div>
                                <!--<input type="text" class="item_input" value="${info.fullName}">-->
                                <select name="choice" id="choice" class="selection item_input" onchange="choiceChange()">
                                    <option value="1" selected="selected">Send to individual account</option>
                                    <option value="2">Send to all patients in specific room</option>
                                    <option value="3">Send to specific role</option>
                                </select>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Account to send</div>
                                <select name="account" class="selection item_input">
                                    <c:forEach items="${requestScope.accounts}" var="a">
                                        <option value="${a.account.userName}">${a.fullName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Content</div>
                                <textarea name="content" class="item_input"
                                          style="resize:none; width:100%; height: 150px; padding: 5px 5px; box-sizing: border-box; border: 2px solid #ccc; border-radius: 1rem; background-color: #f8f8f8;" required></textarea>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Title</div>
                                <input type="text" class="item_input" name="title" required/><br/>
                                <input type="submit" style="padding: 1.5rem" class="btn btn-primary btn-add" value="Create"/>
                            </div>
                        </div>
                    </div>

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
