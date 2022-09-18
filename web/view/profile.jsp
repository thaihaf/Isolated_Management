<%-- 
    Document   : profile
    Created on : Sep 17, 2022, 7:54:21 PM
    Author     : hapro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:set var="role" value="nurse"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/view/profile.css"/>
    </head>
    <body>
        <jsp:include page="../base/sidebar.jsp" />

        <jsp:include page="../base/header.jsp" />

        <div class=<c:if test="${role eq 'admin'}">"wrapper wrapperAdmin"</c:if>
             <c:if test="${role ne 'admin'}">"wrapper wrapperUser"</c:if>
                 >
                 <div class="container">

                     <div class="info">
                         <div class="info_title">Profile</div>
                         <img src="../assets/img/avt.jpg" alt="alt" class="info_avt"/>
                         <div class="info_fullname">Nguyen Thai Ha</div>
                         <div class="info_role">Doctor</div>
                         <div class="info_btn_UploadAvt">Upload new avatar</div>
                         <div class="info_national">Viet Nam</div>
                         <div class="info_description">Hello everyone, I am here to monitor and take care of your health.</div>
                     </div>

                     <div class="updateInfo">
                         <div class="updateInfo_actions">
                             <div class="updateInfo_title">basic info</div>
                             <div class="updateInfo_btns">
                                 <input type="reset" class="updateInfo_btnReset" title="reset"/>
                                 <input type="submit" class="updateInfo_btnSubmit" title="save"/>
                             </div>
                         </div>

                         <div class="updateInfo_group">
                             <div class="updateInfo_item left">
                                 <div class="item_title">fullname</div>
                                 <input type="text"  class="item_input">
                             </div>
                             <div class="updateInfo_item right">
                                 <div class="item_title">gender</div>
                                 <div class="btns_radio">
                                     <div class="btn_radioGroup">
                                         <input type="radio" title="Male" class="btn_radioInput" >
                                         <div class="btn_radioText">male</div>
                                     </div>
                                     <div class="btn_radioGroup">
                                         <input type="radio" title="Female" class="btn_radioInput">
                                         <div class="btn_radioText">female</div>
                                     </div>

                                 </div>
                             </div>
                         </div>

                         <div class="updateInfo_group">
                             <div class="updateInfo_item left">
                                 <div class="item_title">email</div>
                                 <input type="text"  class="item_input">
                             </div>
                             <div class="updateInfo_item right">
                                 <div class="item_title">age</div>
                                 <input type="text"  class="item_input">
                             </div>
                         </div>

                         <div class="updateInfo_group">
                             <div class="updateInfo_item left">
                                 <div class="item_title">phone</div>
                                 <input type="text"  class="item_input">
                             </div>
                             <div class="updateInfo_item right">
                                 <div class="item_title">national</div>
                                 <input type="text"  class="item_input">
                             </div>
                         </div>

                         <div class="updateInfo_group">
                             <div class="updateInfo_item full">
                                 <div class="item_title">address</div>
                                 <input type="text"  class="item_input">
                             </div>
                         </div>

                         <div class="updateInfo_group">
                             <div class="updateInfo_item full">
                                 <div class="item_title">about me</div>
                                 <textarea rows="5" cols="25" placeholder="" class="item_input"></textarea>
                             </div>
                         </div>
                     </div>
                 </div>
             <jsp:include page="../base/footer.jsp" />   
        </div>
    </body>
</html>
