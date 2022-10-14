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
        <link rel="stylesheet" href="../assets/css/base2.css"/>
        <link rel="stylesheet" href="../assets/css/base/home.css"/>
    </head>
    <body>
        <jsp:include page="/base/sidebar.jsp" />
        <jsp:include page="/base/header.jsp" />
        <div class="wrapper wrapperAdmin">
            <div class="container">
                <form action="adduser" method="post">
                    <input type="hidden" value=""/>
                    <span style="color: red;">${mess}</span><br>
                    Username: <input required type="text" name="username"><br>
                    Password: <input required type="password" name="password"><br>
                    Full name: <input required type="text" name="fullName" value=""/><br/>
                    Gender: <Input type="radio" name="gender" value="True"/>Male 
                    <Input type="radio" name="gender" value="False"/>Female <br/>
                    Phone: <input required type="text" name="phone" value=""/><br/>
                    Address: <input required type="text" name="address" value=""/><br/>
                    Email: <input required type="email" name='email' value=""/><br/>
                    Nationality: <select name="nation">
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
                    </select><br>
                    Date Of Birth <input required type="date" name="dateofbirth" value=""><br>
                    Role <select name="role">
                        <option value="2">Doctor</option>
                        <option value="3">Nurse</option>
                    </select>
                    <div id="optional">
                        Level of education: <input type="text" name="leveleducation" value=""/><br/>
                        Hospital: <input type="text" name="hospital" value=""/><br/>
                    </div>
                    <input type="submit" value="Add"><br>
                    <a href="admin/users">Return to User List</a>
                </form>
            </div>
            <jsp:include page="/base/footer.jsp" />
        </div>
    </body>
</html>
