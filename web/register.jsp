<%-- 
    Document   : register
    Created on : Sep 14, 2022, 5:07:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="register" method="post">
            <h1>REGISTER</h1>
            Họ và tên <input type="text" name="Name"><br>
            Tuổi <select name="age">
                <option></option>
            </select>
            Giới tính <select name="gender">
                <option value="male">Nam</option>
                <option value="female">Nữ</option>
            </select>
            Quốc tịch <select name="Nation">
                <option value="Việt Nam">Việt Nam</option>
            </select><br>
            Địa chỉ <input type="text" name="address"><br>
            Email đăng kí <input type="text" name="Email"><br>
            Tên đăng nhập <input type="text" name="UserName"><br>
            Mật khẩu <input type="password" name="Password"><br>
            Xác nhận mật khẩu <input type="password" name="Password_confirm">
            <p class="text-danger">${mess}</p>
            <a href="login.jsp">Bạn đã có tài khoản ?</a><br>
            <input type="submit" value="Sign up">
        </form>
    </body>
</html>
