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
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="../assets/css/base/sidebar3.css"/>
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
                          <!-- comment -->
                          <dt class="first">System Admin</dt>
                          <a class="sidebar_item select" href="users">
                              <img class="sidebar_item_img" src="../assets/img/users.png" alt="alt"/>
                              <div>Account Lists</div>
                          </a>
                          <a class="sidebar_item" href="rooms">
                              <img class="sidebar_item_img" src="../assets/img/room.png" alt="alt"/>
                              <div>Room Lists</div>
                          </a>
                          <a class="sidebar_item" href="areas">
                              <img class="sidebar_item_img" src="../assets/img/map.png" alt="alt"/>
                              <div>Area Lists</div>
                          </a>

                          <!-- comment -->
                          <dt>Food Manager</dt>
                          <a class="sidebar_item" href="foodlist">
                              <img class="sidebar_item_img" src="../assets/img/foods.png" alt="alt"/>
                              <div>Food List</div>
                          </a>

                          <!-- comment -->
                          <dt>Request & Notification Manager</dt>
                          <a class="sidebar_item" href="notif_create">
                              <img class="sidebar_item_img" src="../assets/img/noti.png" alt="alt"/>
                              <div>Create Notification</div>
                          </a>

                          <!-- comment -->
                          <dt>Exercise Management</dt>
                          <a class="sidebar_item" href="create_exercise">
                              <img class="sidebar_item_img" src="../assets/img/exs.png" alt="alt"/>
                              <div>Create new Exercise</div>
                          </a>
                          <a class="sidebar_item" href="exercise_list">
                              <img class="sidebar_item_img" src="../assets/img/exercise.png" alt="alt"/>
                              <div>Exercise list</div>
                          </a>


                          <!-- comment -->
                          <dt>Schedule Management</dt>
                          <a class="sidebar_item" href="sched_create">
                              <img class="sidebar_item_img" src="../assets/img/sches.png" alt="alt"/>
                              <div>Create Schedule</div>
                          </a>
                          <a class="sidebar_item" href="sched_list">
                              <img class="sidebar_item_img" src="../assets/img/sche.png" alt="alt"/>
                              <div>Schedule list</div>
                          </a>
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
                              <a class="sidebar_item" href="viewInjection?username=${sessionScope.account.userName}">
                                  <img class="sidebar_item_img" src="../assets/icons/testIcon.png" alt="alt"/>
                                  <div>Injection</div>
                              </a>
                              <a class="sidebar_item" href="../foodmenu">
                                  <img class="sidebar_item_img" src="../assets/icons/foodmenu.png" alt="alt"/>
                                  <div>Food Menu</div>
                              </a>
                              <a class="sidebar_item" href="javascript:contactConfirm()">
                                  <img class="sidebar_item_img" src="../assets/icons/emergency_icon.jpg" alt="alt"/>
                                  <div>Emergency contact</div>
                              </a>
                              <a class="sidebar_item" href="exercise_sched">
                                  <img class="sidebar_item_img" src="../assets/icons/exercise.png" alt="alt"/>
                                  <div>Exercise Schedule</div>
                              </a>
                          </c:if>

                          <a class="sidebar_item" href="profile">
                              <img class="sidebar_item_img img_profile" src="../assets/icons/profileIcon.png" alt="alt"/>
                              <div>Profile</div>
                          </a>
                          <br>
                          <a class="sidebar_item" href="schedule">
                              <img class="sidebar_item_img img_profile" src="../assets/icons/schedule.png" alt="alt"/>
                              <div>Schedule</div>
                          </a>
                          <c:if test="${role.id eq 2}">
                              <dt>Manager</dt>
                              <dd><a href="../foodlist">Food List</a></dd>
                          </c:if>
                      </c:otherwise>
                  </c:choose>
             </div>
             <a class="sidebar_item nb" href="../logout">
                 <div class="sidebar_btnLogout">
                     <img class="sidebar_btnLogout_img" src="../assets/icons/logout.png" alt="alt"/>
                 </div>
             </a>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <<script type="text/javascript" src="../assets/js/base/sidebar2.js"></script>
    </body>
</html>