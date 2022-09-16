<%-- 
    Document   : sidebar_menu
    Created on : Sep 14, 2022, 12:04:16 PM
    Author     : hapro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--todo-->
<c:set var="role" value="admin"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="./assets/css/base.css"/>
        <link rel="stylesheet" href="./assets/css/base/sidebar.css"/>
    </head>
    <body>

        <div class=<c:if test="${role eq 'admin'}">"sidebar sidebarAdmin"</c:if>
             <c:if test="${role ne 'admin'}">"sidebar sidebarUser"</c:if>
                 >
                 <div class="sidebar_btnMenu">
                     <img class="sidebar_btnMenu_img" src="./assets/icons/menu.png" alt="alt"/>
                 </div>

                 <div id="style-1" class=<c:if test="${role eq 'admin'}">"sidebar_list sidebar_list_Admin"</c:if>
                  <c:if test="${role ne 'admin'}">"sidebar_list sidebar_list_User"</c:if>
                      >
                  <c:choose>
                      <c:when test="${role=='admin'}">
                          <dl>
                              <dt>Home</dt>

                              <dt>System Admin</dt>
                              <dd><a href="url" class="selected">Account List</a></dd>
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
                          <a class="sidebar_item" href="url">
                              <img class="sidebar_item_img" src="./assets/icons/homeIcon.png" alt="alt"/>
                              <div>Home</div>
                          </a>

                          <!--// doctor-->
                          <c:if test="${role=='doctor'}">
                              <a class="sidebar_item" href="url">
                                  <img class="sidebar_item_img" src="./assets/icons/patientIcon.png" alt="alt"/>
                                  <div>Patient</div>
                              </a>
                              <a class="sidebar_item" href="url">
                                  <img class="sidebar_item_img" src="./assets/icons/donthuoc.png" alt="alt"/>
                                  <div>Prescription</div>
                              </a>
                          </c:if>

                          <!--// nurse-->
                          <c:if test="${role=='nurse'}">
                              <a class="sidebar_item" href="url">
                                  <img class="sidebar_item_img" src="./assets/icons/patientIcon.png" alt="alt"/>
                                  <div>Patient</div>
                              </a>
                              <a class="sidebar_item" href="url">
                                  <img class="sidebar_item_img" src="./assets/icons/reportIcon.png" alt="alt"/>
                                  <div>Report</div>
                              </a>
                          </c:if>

                          <!--//patient-->
                          <c:if test="${role=='patient'}">
                              <a class="sidebar_item" href="url">
                                  <img class="sidebar_item_img" src="./assets/icons/testIcon.png" alt="alt"/>
                                  <div>Test result</div>
                              </a>
                              <a class="sidebar_item" href="url">
                                  <img class="sidebar_item_img" src="./assets/icons/donthuoc.png" alt="alt"/>
                                  <div>Prescription</div>
                              </a>
                          </c:if>

                          <a class="sidebar_item" href="url">
                              <img class="sidebar_item_img img_profile" src="./assets/icons/profileIcon.png" alt="alt"/>
                              <div>Profile</div>
                          </a>
                          <br />
                      </c:otherwise>
                  </c:choose>


             </div>

             <div class="sidebar_btnLogout">
                 <img class="sidebar_btnLogout_img" src="./assets/icons/logout.png" alt="alt"/>
             </div>
        </div>
    </body>
</html>
