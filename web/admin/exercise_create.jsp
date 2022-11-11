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
        <meta name="referrer" content="no-referrer">

        <link rel="stylesheet" href="../assets/css/admin/notif_create2.css"/>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/base/homeNew.css"/>

        <script src="https://code.jquery.com/jquery-3.5.0.js"></script>
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
                            <li class="breadcrumb-item active" aria-current="page">Create Exercise</li>
                        </ol>
                    </nav>
                </div>

                <div class="top2 d-flex justify-content-between">
                    <h1>Create Exercise</h1>
                </div>

                <form action="exercise_create" class="d-flex align-items-center justify-content-center" method="POST" id="exerciseForm" name="exerciseForm" enctype="multipart/form-data">

                    <div class="updateInfo_actions d-block" style="resize:none; width:50%;" >

                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Name</div>
                                <input name="name" type="text" class="item_input"/>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Content</div>
                                <input type="text" name="src" id="src" disabled class="item_input"/>
                            </div>
                        </div>
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Description</div>
                                <textarea name="note" class="item_input"
                                          style="resize:none; width:100%; height: 150px; padding: 5px 5px; box-sizing: border-box; border: 2px solid #ccc; border-radius: 1rem; background-color: #f8f8f8;" required></textarea>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Source</div>
                                <div style="border: 1px solid gray; margin-bottom: 2rem">
                                    <c:forEach items="${requestScope.sourcetype}" var="st"><input type="radio" name="type" id="type" value="${st.id}"/>${st.type}</c:forEach>
                                    </div>
                                    <button type="button" style="padding: 1.5rem" class="btn btn-primary btn-add" id="btnSubmit">Add</button>
                                </div>
                            </div>
                        </div>

                    </form>
                </div>
            <jsp:include page="/base/footer.jsp" />   
        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.2/dist/additional-methods.js"></script>
        <script src="../assets/js/admin/exercise_create.js" type="text/javascript"></script>
    </body>
</html>
