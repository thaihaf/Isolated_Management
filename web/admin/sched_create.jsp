<%-- 
    Document   : sched_create
    Created on : Oct 24, 2022, 9:17:42 PM
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

        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
        <link href="../assets/css/admin/sched_create.css" rel="stylesheet" type="text/css"/>
        <script src="../assets/js/admin/sched_create.js" type="text/javascript"></script>

    </head>
    <body>
        <jsp:include page="../base/sidebar.jsp" />
        <jsp:include page="../base/header.jsp" />

        <div class="wrapper wrapperAdmin">
            <div class="container-fluid">
                <div class="top1">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb">
                            <li class="breadcrumb-item"><a href="../base/home.jsp">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Create Schedule</li>
                        </ol>
                    </nav>
                </div>

                <div class="top2 d-flex justify-content-between">
                    <h1>Create Schedule</h1>
                </div>

                <form id="schedule" class="d-flex align-items-center justify-content-center" action="sched_create" method="POST" class="schedule" name="schedule">
                    <div class="updateInfo_actions d-block" style="resize:none; width:50%;" >
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Person assigned</div>
                                <!--<input type="text" class="item_input" value="${info.fullName}">-->
                                <select id="selectPerson" class="selection item_input" name="person">
                                    <c:forEach items="${requestScope.account}" var="a">
                                        <option value="${a.account.userName}">${a.fullName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Schedule date</div>
                                <input type="date" class="schedule__form-control item_input" id="date" name="date"/>
                            </div>
                        </div>
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Room to assigned</div>
                                <select id="room" name="room" class="selection item_input">
                                    <c:forEach items="${requestScope.room}" var="r">
                                        <option value="${r.id}">${r.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Schedule time</div>
                                <div style="border: 1px solid gray; margin-bottom: 2rem">
                                    <c:forEach items="${requestScope.schedtime}" var="time">
                                        <input type="radio" id="${time.name}" name="time" value="${time.id}" id="time" required/>
                                        <label style="cursor: pointer;" for="${time.name}">${time.name}</label>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Description</div>
                                <textarea id="txtDescription" name="desc" class="item_input"
                                          style="resize:none; width:100%; height: 150px; padding: 5px 5px; box-sizing: border-box; border: 2px solid #ccc; border-radius: 1rem; background-color: #f8f8f8;" required></textarea>
                                </select>
                            </div>
                            <div class="updateInfo_item right">
                                <button id="submitButton" style="padding: 1.5rem" class="btn btn-primary btn-add mt-auto" type="submit">Add</button><br/>
                            </div>
                        </div>
                    </div>
                </form>
                <div class="response"></div>
            </div>
            <jsp:include page="../base/footer.jsp" />   
        </div>
    </body>
</html>
