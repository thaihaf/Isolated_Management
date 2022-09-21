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
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/view/register.css"/>
    </head>
    <body>
        <div class="login" style="background-image: url(../assets/img/bg_login.png)">
            <div class="login_link">
                <div class="item item_language">
                    <select name="language" class="switch_option">
                        <option value="vn">Vietnamese</option>
                        <option value="en">English</option>
                        <option value="jp">Japanese</option>
                    </select>
                </div>
                <a class="item">FAQ</a>
                <a class="item">Guide</a>
                <a class="item">Contact us</a>
            </div>

            <div class="login_inputTab">
                <div class="intro">
                    <div class="intro_title">welcome!</div>
                    <div class="intro_small">
                        Have account,
                        <a href="./login.jsp">Login</a>
                    </div>
                </div>

                <form action="register" method="post" class="login_form" onsubmit ="return verifyPassword()">
                    <span style="color: red;">${register_success}</span><br>

                    <div class="group">
                        <div class="item">
                            <div class="item_title">Email</div>

                            <input required class="item_input" type="email" name="Email" id="email" placeholder="abc@gmail.com">
                        </div>
                        <div class="item">
                            <div class="item_title">Username</div>

                            <input required class="item_input" placeholder="thaihaf" type="text" name ="Username"><span style="color: red;">${sign_exist_username}</span><br>
                        </div>
                    </div>

                    <div class="group">
                        <div class="item">
                            <div class="item_title">Password</div>

                            <input required class="item_input" type="password" name="Password" id = "pswd" value = ""><span id = "message" style="color:red"></span><br>
                        </div>
                        <div class="item">
                            <div class="item_title">Confirm password</div>

                            <input required class="item_input" type="password" name="confirm_password"><span style="color: red;">${mess}</span><br>
                        </div>
                    </div>

                    <div class="group">
                        <div class="item">
                            <div class="item_title">Full name </div>

                            <input required class="item_input" type ="text" name="Fullname" placeholder="Nguyen thai ha">
                        </div>

                        <div class="item">
                            <div class="item_title">Phone</div>

                            <input required class="item_input" type="text" name="Phone" pattern="[0-9]{10}" placeholder="+84 819889962">
                        </div>
                    </div>

                    <div class="group">
                        <div class="item">
                            <div class="item_title">Address</div>

                            <input required class="item_input" type ="text" name="Address">
                        </div>
                    </div>
                        
                    <div class="group">
                        <div class="item">
                            <div class="item_title">Gender</div>

                            <select name="Gender">
                                <option value="Male">Male</option>
                                <option value="Female">Female</option>
                            </select>
                        </div>

                        <div class="item">
                            <div class="item_title">Nationality</div>

                            <select name="Nation">
                                <option value="Viet Nam">Viet Nam</option>
                            </select><br>
                        </div>
                    </div>

                    <input type="submit" value="Register" class="btn_submit">
                </form>
            </div>


        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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


                    const validateEmail = (email) => {
                        return email.match(
                                /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                                );
                    };

                    const validate = () => {
                        const $result = $('#result');
                        const email = $('#email').val();
                        $result.text('');

                        if (validateEmail(email)) {
                            $result.text(email + ' is valid :)');
                            $result.css('color', 'green');
                        } else {
                            $result.text(email + ' is not valid :(');
                            $result.css('color', 'red');
                        }
                        return false;
                    }

                    $('#email').on('input', validate);
        </script>  

    </body>
</html>
