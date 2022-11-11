<%-- 
    Document   : adduser
    Created on : Oct 1, 2022, 10:00:50 AM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add user - IMS</title>
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
                            <li class="breadcrumb-item"><a href="users">User List</a></li>
                            <li class="breadcrumb-item active" aria-current="page">New User</li>
                        </ol>
                    </nav>
                </div>

                <div class="top2 d-flex justify-content-between">
                    <h1>New User</h1>
                </div>


                <form action="adduser" method="post" class="d-flex align-items-center justify-content-center">
                    <input type="hidden" value=""/>
                    <span style="color: red;">${mess}</span><br>

                    <div class="updateInfo_actions d-block" style="resize:none; width:50%;" >
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Username</div>
                                <input required class="item_input" type="text" name="username">
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Password</div>
                                <input required class="item_input" type="password" name="password">
                            </div>
                        </div>
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Fullname</div>
                                <input required class="item_input" type="text" name="fullName" value=""/>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Gender</div>
                                <div class="d-flex align-items-center">
                                    <input type="radio" class="mr-2" name="gender" value="True"/>Male 
                                    <input type="radio"class="ml-5 mr-2" name="gender" value="False"/>Female 
                                </div>
                            </div>
                        </div>
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Phone</div>
                                <input required class="item_input" type="text" name="phone" value=""/>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Address</div>
                                <input required class="item_input" type="text" name="address" value=""/>
                            </div>
                        </div>
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Email</div>
                                <input required class="item_input" type="email" name='email' value=""/>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Nationality</div>
                                <select name="nation"  class="selection item_input">
                                    <option value="Viet Nam">Việt Nam</option>
                                    <option value="Thailand">Thái Lan</option>
                                    <option value="Laos">Lào</option>
                                    <option value="Campuchia">Campuchia</option>
                                    <option value="Myanmar">Myanmar</option>
                                    <option value="China">Trung Quốc</option>
                                    <option value="Indonesia">Indonesia</option>
                                    <option value="Malaysia">Malaysia</option>
                                    <option value="Brunei">Brunei</option>
                                    <option value="Timor Leste">Đông Timo</option>
                                    <option value="Phillipine">Phillipine</option>
                                    <option value="Singapore">Singapore</option>
                                    <option value="Korea">Hàn Quốc</option>
                                    <option value="Japan">Nhật Bản</option>
                                    <option value="Germani">Đức</option>
                                    <option value="England">Anh</option>
                                    <option value="Russia">Nga</option>
                                    <option value="France">Pháp</option>
                                    <option value="USA">Mỹ</option>
                                </select>
                            </div>
                        </div>
                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Date of Birth</div>
                                <input required class="selection item_input" type="date" name="dateofbirth" value="">
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Role</div>
                                <select name="role"  class="selection item_input">
                                    <option value="2">Doctor</option>
                                    <option value="3">Nurse</option>
                                </select>
                            </div>
                        </div>

                        <div class="updateInfo_group">
                            <div class="updateInfo_item left">
                                <div class="item_title">Level of education</div>
                                <input type="text" class="item_input" name="leveleducation" value=""/>
                            </div>
                            <div class="updateInfo_item right">
                                <div class="item_title">Hospital</div>
                                <input type="text" class="item_input" name="hospital" value=""/>
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
