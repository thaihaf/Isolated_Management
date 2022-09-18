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
        <title>Register</title>
    </head>
    <body>
        <form action="register" method="post" onsubmit ="return verifyPassword()">
            <h1>REGISTER</h1>
            <span style="color: red;">${register_success}</span><br>
            Full name <input required type="text" name="Fullname"><br>
            Gender <select name="Gender">
                <option value="Male">Male</option>
                <option value="Female">Female</option>
            </select>
            Nationality <select name="Nation">
                <option value="Viet Nam">Viet Nam</option>
            </select><br>
            Phone <input required type="text" name="Phone"><br>
            Address <input required type="text" name="Address"><br>
            Email <input required type="text" name="Email" id="email" value=""><br>
            Username <input required type="text" name ="Username"><span style="color: red;">${sign_exist_username}</span><br>
            Password <input required type="password" name="Password" id = "pswd" value = ""><span id = "message" style="color:red"></span><br>
            Confirm password <input required type="password" name="confirm_password"><span style="color: red;">${mess}</span><br>
            <input type="submit" value="Register">
        </form>

        <script>
//            function phonenumber(inputtxt)
//            {
//                var phoneno = /^\d{10}$/;
//                if ((inputtxt.value.match(phoneno))
//                {
//                    return true;
//                } else
//                {
//                    alert("message");
//                    return false;
//                }
//            }
//
//            function ValidateEmail(mail)
//            {
//                if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(myForm.emailAddr.value))
//                {
//                    return (true)
//                }
//                alert("You have entered an invalid email address!")
//                return (false)
//            }

            function verifyPassword() {
                var pw = document.getElementById("pswd").value;
                //check empty password field  
                if (pw == "") {
                    document.getElementById("message").innerHTML = "**Fill the password please!";
                    return false;
                }

                //minimum password length validation  
                if (pw.length < 8) {
                    document.getElementById("message").innerHTML = "**Password length must be atleast 8 characters";
                    return false;
                }

//maximum length of password validation  
                if (pw.length > 15) {
                    document.getElementById("message").innerHTML = "**Password length must not exceed 15 characters";
                    return false;
                } else {
//                    alert("Password is correct");
                }
            }
        </script>  
    </body>
</html>
