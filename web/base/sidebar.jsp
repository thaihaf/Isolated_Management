<%-- 
    Document   : sidebar_menu
    Created on : Sep 14, 2022, 12:04:16 PM
    Author     : hapro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--todo-->


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base2.css"/>
        <link rel="stylesheet" href="../assets/css/base/sidebar.css"/>
    </head>
    <body>
        <c:set var="role" value="${sessionScope.account.role}"/>
        <div class=<c:if test="${role.id eq 1}">"sidebar sidebarAdmin"</c:if>
             <c:if test="${role.id ne 1}">"sidebar sidebarUser"</c:if>
                 >
                 <div id="style-1" class=<c:if test="${role.id eq 1}">"sidebar_list sidebar_list_Admin"</c:if>
                  <c:if test="${role.id ne 1}">"sidebar_list sidebar_list_User"</c:if>
                      >
                  <c:choose>
                      <c:when test="${role.id eq 1}">
                          <dl>
                              <dt>System Admin</dt>
                              <dd><a href="../admin/users" class="selected">Account Lists</a></dd>
                              <dd><a href="../admin/rooms" class="selected">Room Lists</a></dd>
                              <dd><a href="../admin/areas" class="selected">Area Lists</a></dd>
                              <dd><a href="/abc">Add Patient</a></dd>
                              <dd><a href="url">Add Doctor</a></dd>
                              <dd><a href="url">Add Nurse</a></dd>

                              <dt>Manager</dt>
                              <dd><a href="url">Food List</a></dd>
                              <dd><a href="url">Medical List</a></dd>

                              <dt>Request Manager</dt>
                              <dd><a href="url">Request Medical</a></dd>
                              <dd><a href="url">Request Food</a></dd>
                              <dd><a href="url">Request Contact</a></dd>
                          </dl>
                          <br />
                      </c:when>   

                      <c:otherwise>
                          <a class="sidebar_item" href="home">
                              <img class="sidebar_item_img" src="../assets/icons/homeIcon.png" alt="alt"/>
                              <div>Home</div>
                          </a>
                          <!--// doctor-->
                          <c:if test="${role.id eq 2}">
                              <a class="sidebar_item" href="listpatient">
                                  <img class="sidebar_item_img" src="../assets/icons/patientIcon.png" alt="alt"/>
                                  <div>Patient</div>
                              </a>
                              <a class="sidebar_item" href="medicine-list">
                                  <img class="sidebar_item_img" src="../assets/icons/medicine.png" alt="alt"/>
                                  <div>Medicine</div>
                              </a>
                          </c:if>

                          <!--// nurse-->
                          <c:if test="${role.id eq 3}">
                              <a class="sidebar_item" href="listpatient">
                                  <img class="sidebar_item_img" src="../assets/icons/patientIcon.png" alt="alt"/>
                                  <div>Patient</div>
                              </a>
                              <a class="sidebar_item" href="nurseReport">
                                  <img class="sidebar_item_img" src="../assets/icons/reportIcon.png" alt="alt"/>
                                  <div>Report</div>
                              </a>
                          </c:if>

                          <!--//patient-->
                          <c:if test="${role.id eq 4}">
                              <a class="sidebar_item" href="viewTest?username=${sessionScope.account.userName}">
                                  <img class="sidebar_item_img" src="../assets/icons/testIcon.png" alt="alt"/>
                                  <div>Test result</div>
                              </a>
                              <a class="sidebar_item" href="patientPrescription">
                                  <img class="sidebar_item_img" src="../assets/icons/donthuoc.png" alt="alt"/>
                                  <div>Prescription</div>
                              </a>
                              <a class="sidebar_item" href="javascript:contactConfirm()">
                                  <img class="sidebar_item_img" src="../assets/icons/emergency_icon.jpg" alt="alt"/>
                                  <div>Emergency contact</div>
                              </a>
                          </c:if>

                          <a class="sidebar_item" href="profile">
                              <img class="sidebar_item_img img_profile" src="../assets/icons/profileIcon.png" alt="alt"/>
                              <div>Profile</div>
                          </a>
                      </c:otherwise>
                  </c:choose>
             </div>
             <a class="sidebar_item" href="../logout">
                 <div class="sidebar_btnLogout">
                     <img class="sidebar_btnLogout_img" src="../assets/icons/logout.png" alt="alt"/>
                 </div>
             </a>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <<script type="text/javascript" src="../assets/js/base/sidebar.js"></script>
    </body>
</html>