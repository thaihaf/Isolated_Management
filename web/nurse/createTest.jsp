<%-- 
    Document   : createTest
    Created on : Sep 28, 2022, 2:53:20 AM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base.css" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/view/profile.css" />
        <link rel="stylesheet" href="../assets/css/nurse/createTest.css" />
    </head>

    <body>
        <c:set var="role" value="${info.account.role}" />
        <jsp:include page="../base/sidebar.jsp" />

        <jsp:include page="../base/header.jsp" />

        <div class=<c:if test="${role.id eq 1}">"wrapper wrapperAdmin"</c:if>
             <c:if test="${role.id ne 1}">"wrapper wrapperUser"</c:if>
                 >
                 <form action="createTest" method="POST" class="profilForm container">
                     <div class="info">

                         <div class="updateInfo_group">
                             <div class="updateInfo_item left">
                                 <div class="item_title">Blood Type</div>
                                 <input type="text" class="item_input" value="${patient.bloodType}">
                         </div>
                         <div class="updateInfo_item right">
                             <div class="item_title">Note</div>
                             <input type="text" class="item_input" value="${patient.note}">
                         </div>
                     </div>


                     <div class="updateInfo_group">
                         <div class="updateInfo_item full">
                             <div class="item_title">Background Diseases</div>
                             <textarea rows="5" cols="25" placeholder="${patient.backgroundDisease}" class="item_input"></textarea>
                         </div>
                     </div>

                     <div class="updateInfo_group">
                         <div class="updateInfo_item left">
                             <div class="item_title">test type</div>
                             <select name="test">
                                 <c:forEach items="${requestScope.types}" var="t">
                                     <option value="${t.id}" style="font-size: 15px">${t.typeName}</option>
                                 </c:forEach>
                             </select> 
                         </div>
                     </div>
                     <div class="updateInfo_group">
                         <div class="updateInfo_item right">
                             <div class="item_title">result</div>
                             <div class="btns_radio">
                                 <div class="btn_radioGroup">
                                     <input type="radio" name="result" value="positive" />
                                     <div class="btn_radioText">Positive</div>
                                 </div>
                                 <div class="btn_radioGroup">
                                     <input checked="checked" type="radio" name="result" value="negative" />
                                     <div class="btn_radioText">Negative</div>
                                 </div>
                             </div>
                         </div>
                     </div>
                     <div class="updateInfo_group">
                         <div class="updateInfo_item left">
                             <div class="item_title">Test Creator</div>
                             <input type="text" class="item_input" name="creator">
                             <p class="text-danger">${mess}</p>
                         </div>


                     </div>

                     <c:if test="${requestScope.isFirstTest}">
                         <div class="updateInfo_group">
                             <div>This is the first test, please choose placement</div>
                             <div></div>
                             <button type="button" class="btn btn-primary btn-add" data-toggle="modal" data-target="#exampleModalLong">
                                 Choose Room
                             </button><!-- comment -->

                             <div class="modal fade bd-example-modal-lg" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                                 <div class="modal-dialog" role="document">
                                     <div class="modal-content">
                                         <div class="modal-header">
                                             <h5 class="modal-title" id="exampleModalLongTitle">Add into room</h5>
                                             <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                 <span aria-hidden="true">&times;</span>
                                             </button>
                                         </div>
                                         <div class="modal-body px-5">
                                             <div class="form-row">
                                                 <div class="col-md-6 mb-3">
                                                     <div  class="lable">Area Select</div>
                                                     <select name="areaSelect" id="areaSelect">
                                                         <c:forEach items="${requestScope.listArea}" var="a">
                                                             <option value="${a.id}" style="font-size: 15px">${a.name} - ${a.areaType.type}</option>
                                                         </c:forEach>
                                                     </select> 
                                                 </div>
                                                 <div class="col-md-6 mb-3">
                                                     <div  class="lable">Room Select</div>
                                                     <select name="roomSelect" id="roomSelect">
                                                         <option selected value="" style="font-size: 15px">---- Choose Room ----</option>
                                                         <c:forEach items="${requestScope.listArea}" var="b" varStatus="loop">
                                                             <c:if test="${loop.first}">
                                                                 <c:forEach items="${b.rooms}" var="r">
                                                                     <option value="${r.id}" style="font-size: 15px">${r.name}</option>
                                                                 </c:forEach>
                                                             </c:if>
                                                         </c:forEach>
                                                     </select> 
                                                 </div>
                                                 <c:if test="${fn:length(requestScope.listArea) gt 0}">
                                                     <div class="col-md-12 mb-3 mt-5">
                                                         <label class="lable">List Room in Area</label>
                                                         <div class="form-row mr-1 d-flex justify-content-end">
                                                             <div class="search">
                                                                 <input 
                                                                     type="text" 
                                                                     class="form-control list input-group-lg" 
                                                                     placeholder="Search name ..." 
                                                                     id="searchRoomByName"
                                                                     >
                                                             </div>
                                                         </div>
                                                         <table class="table table-hover">
                                                             <thead>
                                                                 <tr>
                                                                     <th >Name</th>
                                                                     <th >Num of Use</th>
                                                                     <th >Num of Bed</th>
                                                                     <th >Doctor</th>
                                                                     <th >Nurse</th>
                                                                     <th >Level of Room</th>
                                                                 </tr>
                                                             </thead>
                                                             <tbody id="roomListBody">
                                                                 <c:forEach items="${requestScope.listArea}" var="b" varStatus="loop">
                                                                     <c:if test="${loop.first}">
                                                                         <c:forEach items="${b.rooms}" var="r">
                                                                             <tr>
                                                                                 <td>${r.name}</td>
                                                                                 <td>${r.numOfUse}</td>
                                                                                 <td>${r.numOfBed}</td>
                                                                                 <td>${r.doctorManage.account.userName}</td>
                                                                                 <td>${r.nurseManage.account.userName}</td>
                                                                                 <td>
                                                                                     <c:set var = "level" scope = "session" value = "${r.level}"/>
                                                                                     <c:choose>
                                                                                         <c:when test = "${level == -1}">
                                                                                             <div class="des_show" >
                                                                                                 <button type="button" class="btn btn-secondary">??ang ch???</button>
                                                                                             </div>
                                                                                         </c:when>
                                                                                         
                                                                                         <c:when test = "${level == 0}">
                                                                                             <div class="des_show" >
                                                                                                 <button type="button" class="btn btn-secondary">Kh??ng tri???u ch???ng</button>
                                                                                                 <div class="des_hidden border border-secondary border-10">
                                                                                                     <p>Ch??a c?? tri???u ch???ng</p>
                                                                                                     <p>B??n c???nh ????, ng?????i b???nh t???nh t??o, t??? ph???c v??? ???????c</p>
                                                                                                 </div>
                                                                                             </div>
                                                                                         </c:when>
                                                                                         <c:when test = "${level == 1}">
                                                                                             <div class="des_show" >
                                                                                                 <button type="button" class="btn btn-secondary">M???c ????? nh???</button>
                                                                                                 <div class="des_hidden border border-secondary border-10">
                                                                                                     <p>F0 c?? c??c tri???u ch???ng l??m s??ng kh??ng ?????c hi???u nh?? s???t, ho khan, ??au h???ng, ngh???t m??i, m???t m???i, ??au ?????u, ??au m???i c??, m???t v??? gi??c, kh???u gi??c, ti??u ch???y??? Nh???p th??? < 20 l???n/ph??t, SpO2 > 96% khi th??? kh?? tr???i. </p>
                                                                                                     <p>B??n c???nh ????, ng?????i b???nh t???nh t??o, t??? ph???c v??? ???????c; X-quang ph???i b??nh th?????ng ho???c c?? nh??ng t???n th????ng ??t.</p>
                                                                                                 </div>
                                                                                             </div>
                                                                                         </c:when>

                                                                                         <c:when test = "${level == 2}">
                                                                                             <div class="des_show" >
                                                                                                 <button type="button" class="btn btn-info">M???c ????? trung b??nh</button>
                                                                                                 <div class="des_hidden border border-info">
                                                                                                     <p>????nh gi?? to??n tr???ng, ng?????i b???nh c?? c??c tri???u ch???ng l??m s??ng kh??ng ?????c hi???u nh?? m???c ????? nh???. </p>
                                                                                                     <p>F0 c?? th??? kh?? th??? khi g???ng s???c (??i l???i trong nh??, l??n c???u thang).</p>
                                                                                                     <p>M???ch c???a ng?????i b???nh c?? th??? nhanh ho???c ch???m, da kh??, nh???p tim nhanh, huy???t ??p b??nh th?????ng, ?? th???c t???nh t??o. </p>
                                                                                                     <p>Ngo??i ra, ch???p X-quang ng???c v?? c???t l???p vi t??nh ng???c ph??t hi???n c?? t???n th????ng, t???n th????ng d?????i 50%. </p>
                                                                                                     <p>Si??u ??m th???y h??nh ???nh s??ng B, kh?? m??u ?????ng m???ch: PaO2/FiO2 > 300.</p>
                                                                                                 </div>
                                                                                             </div>
                                                                                         </c:when>

                                                                                         <c:when test = "${level == 3}">
                                                                                             <div class="des_show" >
                                                                                                 <button type="button" class="btn btn-warning">M???c ????? n???ng</button>
                                                                                                 <div class="des_hidden border border-warning border-10">
                                                                                                     <p>F0 ???????c ph??n lo???i thu???c nh??m n???ng n???u h?? h???p c?? d???u hi???u vi??m ph???i k??m theo b???t k??? m???t trong c??c d???u hi???u sau: nh???p th??? > 25 l???n/ph??t; kh?? th??? n???ng, co k??o c?? h?? h???p ph???; SpO2 < 94% khi th??? kh?? ph??ng.</p>
                                                                                                     <p>V??? tu???n ho??n, nh???p tim ng?????i b???nh c?? th??? nhanh ho???c ch???m, HA b??nh th?????ng hay t??ng. </p>
                                                                                                     <p>V??? th???n kinh, ng?????i b???nh c?? th??? b???t r???t ho???c ?????, m???t. Ch???p X-quang ng???c v?? c???t l???p vi t??nh ng???c: c?? t???n th????ng, t???n th????ng tr??n 50%. </p>
                                                                                                     <p>Si??u ??m th???y h??nh ???nh s??ng B nhi???u, kh?? m??u ?????ng m???ch: PaO2/FiO2 kho???ng 200-300.</p>
                                                                                                 </div>
                                                                                             </div>
                                                                                         </c:when>

                                                                                         <c:when test = "${level == 4}">
                                                                                             <div class="des_show" >
                                                                                                 <button type="button" class="btn btn-danger">M???c ????? nguy k???ch</button>
                                                                                                 <div class="des_hidden border border-danger border-10">
                                                                                                     <p>F0 thu???c nh??m nguy k???ch c?? bi???u hi???n th??? nhanh > 30 l???n/ph??t ho???c < 10 l???n/ph??t, c?? d???u hi???u suy h?? h???p n???ng, th??? g???ng s???c nhi???u, th??? b???t th?????ng ho???c c???n h??? tr??? h?? h???p b???ng th??? oxy d??ng cao (HFNC), th??? m??y. </p>
                                                                                                     <p>?? th???c ng?????i b???nh gi???m ho???c h??n m??.</p>
                                                                                                     <p>Nh???p tim b???nh nh??n c?? th??? nhanh ho???c ch???m, huy???t ??p t???t, ti???u ??t ho???c v?? ni???u. </p>
                                                                                                     <p>K???t qu??? X-quang ng???c v?? c???t l???p vi t??nh ng???c ph??t hi???n c?? t???n th????ng, t???n th????ng tr??n 50%.</p> 
                                                                                                     <p>Si??u ??m h??nh ???nh s??ng B nhi???u, kh?? m??u ?????ng m???ch: PaO2/FiO2 < 200, toan h?? h???p, lactat m??u > 2 mmol/L.</p>
                                                                                                 </div>
                                                                                             </div>
                                                                                         </c:when>
                                                                                     </c:choose>
                                                                                 </td>
                                                                             </tr>
                                                                         </c:forEach>
                                                                     </c:if>
                                                                 </c:forEach>
                                                             </tbody>
                                                         </table> 
                                                     </div>
                                                 </c:if>

                                             </div>
                                             <div class="form-row mb-5 mr-1 d-flex justify-content-end">
                                                 <button class="btn btn-primary btn-lg" id="btn_submit" type="button">Ok</button>
                                             </div>
                                         </div>
                                     </div>
                                 </div>
                             </div>
                         </div>
                     </c:if> 

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
                         <input type="submit" class="updateInfo_btnSubmit" value="Submit" />
                     </div>
                 </div>
             </form>
        </div>

        <jsp:include page="../base/footer.jsp" />
    </div>

    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="../assets/js/nurse/createTest.js" type="text/javascript"></script>
    <script>
        $(function () {
            $('[data-toggle="popover"]').popover({
                trigger: 'focus'
            });
        });
    </script>
</body>

</html>

