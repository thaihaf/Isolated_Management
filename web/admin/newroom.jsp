<%-- 
    Document   : newroom
    Created on : Sep 28, 2022, 4:02:17 AM
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
                            <li class="breadcrumb-item"><a href="rooms">Room List</a></li>
                            <li class="breadcrumb-item active" aria-current="page">New Room</li>
                        </ol>
                    </nav>
                </div>

                <div class="top2 d-flex justify-content-between">
                    <h1>New Room</h1>
                </div>

                <form action="add_room" method="POST" class="d-flex align-items-center justify-content-center">
                    <div class="updateInfo_actions d-block" style="resize:none; width:50%;" >
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Name</div>
                                <input type="text" class="item_input" name="name" required/>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Number of Bed</div>
                                <input type="number" class="item_input" name="numOfBed" min="0" value="0"/>
                            </div>
                        </div>
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Area</div>
                                <select name="area" class="selection item_input">
                                    <c:forEach items="${requestScope.areas}" var="a">
                                        <option value="${a.id}">${a.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Status</div>
                                <div class="d-flex align-items-center">
                                    <input type="radio" class="mr-2" name="status" value="true" checked/>Active 
                                    <input type="radio" class="ml-5 mr-2" name="status" value="false"/>Inactive
                                </div>
                            </div>
                        </div>

                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Doctor manage</div>
                                <select name="doctor" class="selection item_input">
                                    <c:forEach items="${requestScope.medical}" var="d">
                                        <c:if test="${d.account.role.id eq 2}">
                                            <option value="${d.account.userName}">${d.fullName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Nurse manage</div>
                                <select name="nurse" class="selection item_input">
                                    <c:forEach items="${requestScope.medical}" var="d">
                                        <c:if test="${d.account.role.id eq 3}">
                                            <option value="${d.account.userName}">${d.fullName}</option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>

                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <input type="submit" class="btn btn-success btn-add p-3" value="Submit"/>
                            </div>
                            <div class="updateInfo_item right">
                                <input type="reset" class="btn btn-success btn-add p-3" value="Reset"/>
                            </div>
                        </div>

                    </div>
                </form>
            </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
    </body>
</html>
