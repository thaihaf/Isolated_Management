<%-- 
    Document   : createInjection
    Created on : Sep 30, 2022, 5:52:18 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base.css" />
        <link rel="stylesheet" href="../assets/css/view/profile.css" />
    </head>

    <body>
        <c:set var="role" value="${info.account.role}" />
        <jsp:include page="../base/sidebar.jsp" />

        <jsp:include page="../base/header.jsp" />

        <div class=<c:if test="${role.id eq 1}">"wrapper wrapperAdmin"</c:if>
             <c:if test="${role.id ne 1}">"wrapper wrapperUser"</c:if>
                 >
                 <form action="createInjection" method="POST" class="profilForm container">
                     <div class="info">

                         <div class="updateInfo_group">
                             <div class="updateInfo_item left">
                                 <div class="item_title">Blood Type</div>
                                 <input type="text" class="item_input" value="${patient.bloodType}">
                         </div>
                         <div class="updateInfo_item right">
                             <div class="item_title">Note</div>
                             <input type="text" class="item_input" name="note">
                         </div>
                     </div>

                     <input type="hidden" name="taken" value="True">
                     <div class="updateInfo_group">
                         <div class="updateInfo_item full">
                             <div class="item_title">Background Diseases</div>
                             <textarea rows="5" cols="25" placeholder="${patient.backgroundDisease}" class="item_input"></textarea>
                         </div>
                     </div>

                     <div class="updateInfo_group">
                         <div class="updateInfo_item left">
                             <div class="item_title">vaccine type</div>
                             <select name="vaccine">
                                 <c:forEach items="${requestScope.vaccine}" var="v">
                                     <option value="${v.id}" style="font-size: 15px">${v.vaccineName}</option>
                                 </c:forEach>
                             </select> 
                         </div>
                     </div>

                     <div class="updateInfo_group">
                         <div class="updateInfo_item left">
                             <div class="item_title">Injection Creator</div>
                             <input type="text" class="item_input" name="creator">
                             <p class="text-danger">${mess}</p>
                         </div>
                     </div>

                 </div>
                 <div class="updateInfo">
                     <div class="updateInfo_actions">
                         <div class="updateInfo_title">CREATE TEST FOR PATIENT: ${info.fullName} - ${info.account.userName} </div>
                     </div>

                     <div class="updateInfo_group">
                         <div class="updateInfo_item left">
                             <div class="item_title">ID</div>
                             <input type="text" class="item_input" value="${info.account.userName}" name="id">
                         </div>
                         <div class="updateInfo_item left">
                             <div class="item_title">fullname</div>
                             <input type="text" class="item_input" value="${info.fullName}">
                         </div>
                         <div class="updateInfo_item right">
                             <div class="item_title">gender</div>
                             <div class="btns_radio">
                                 <div class="btn_radioGroup">
                                     <input type="radio" title="Male" class="btn_radioInput" <c:if test="${info.gender eq true}">
                                            checked="checked"
                                         </c:if>>
                                     <div class="btn_radioText">male</div>
                                 </div>
                                 <div class="btn_radioGroup">
                                     <input type="radio" title="Female" class="btn_radioInput" <c:if
                                                test="${info.gender eq false}">
                                                checked="checked"
                                            </c:if>>
                                     <div class="btn_radioText">female</div>
                                 </div>

                             </div>
                         </div>
                     </div>

                     <div class="updateInfo_group">
                         <div class="updateInfo_item left">
                             <div class="item_title">email</div>
                             <input type="text" class="item_input" value="${info.email}">
                         </div>
                         <div class="updateInfo_item right">
                             <div class="item_title">Date of birth</div>
                             <input type="text" class="item_input" value="${info.dateofbirth}">
                         </div>
                     </div>

                     <div class="updateInfo_group">
                         <div class="updateInfo_item left">
                             <div class="item_title">phone</div>
                             <input type="text" class="item_input" value="${info.phone}">
                         </div>
                         <div class="updateInfo_item right">
                             <div class="item_title">national</div>
                             <input type="text" class="item_input" value="${info.nation}">
                         </div>
                     </div>

                     <div class="updateInfo_group">
                         <div class="updateInfo_item full">
                             <div class="item_title">address</div>
                             <input type="text" class="item_input" value="${info.address}">
                         </div>
                     </div><br>

                     <div class="updateInfo_btns">
                         <input type="submit" class="updateInfo_btnSubmit" value="Create" />
                     </div>
                 </div>
             </form>
        </div>

        <jsp:include page="../base/footer.jsp" />
    </div>
</body>

</html>
