<%-- 
    Document   : listpatient
    Created on : Sep 25, 2022, 3:52:50 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/patient/listpatient.css"/>
    </head>

    <body>
        <jsp:include page="../base/sidebar.jsp" />
        <jsp:include page="../base/header.jsp" />

        <c:set var="role" value="${sessionScope.account.role}" />
        <div class=<c:if test="${role.id eq 1}">"wrapper wrapperAdmin"</c:if>
             <c:if test="${role.id ne 1}">"wrapper wrapperUser"</c:if>
                 >
                 <div class="container-fluid">
                     <div class="top1">
                         <nav aria-label="breadcrumb">
                             <ol class="breadcrumb">
                                 <li class="breadcrumb-item"><a href="../base/home.jsp">Home</a></li>
                                 <li class="breadcrumb-item active" aria-current="page">Patient List</li>
                             </ol>
                         </nav>
                     </div>

                     <form action="listpatient" method="POST" class="top2">
                         <div class="filter1">
                             <div class="filter1_children">Room: <input type="text" name="id" value="${param.username}" class="abc"/></div>
                         <div class="filter1_children">Full Name: <input type="text" name="name" value="${param.name}" class="abc"/></div>
                     </div>

                     <div class="filter2">
                         Gender: 
                         <div class="gender">

                             <span>
                                 <input 
                                     <c:if test="${param.gender eq 'male'}">
                                         checked="checked"
                                     </c:if>
                                     type="radio" name="gender" value="male" /> Male</span>

                             <span>
                                 <input type="radio"
                                        <c:if test="${param.gender eq null or param.gender eq 'both'}">
                                            checked="checked"
                                        </c:if>
                                        name="gender" value="both"/> Both
                             </span>

                             <span>
                                 <input type="radio"
                                        <c:if test="${param.gender eq 'female'}">
                                            checked="checked"
                                        </c:if>
                                        name="gender" value="female"/> Female
                             </span>

                         </div>

                     </div>

                     <div class="filter3">
                         <span>Date of birth:</span>
                         <div class="dateOfBirth">
                             <input type="date" name="from" value="${param.from}"/>
                             <span>to</span>
                             <input type="date" name="to" value="${param.to}"/>
                         </div>
                     </div>

                     <div class="filterBtns">
                         <input type="submit" value="Search" class="btn btn-info"/>
                         <input type="reset" value="Reset" class="btn btn-secondary"/>
                     </div>
                 </form>

                 <div>
                     <table class="table">
                         <thead class="thead-light">
                             <tr>
                                 <th></th>
                                 <th>Full name</th>
                                 <th>Gender</th>
                                 <th>Date Of Birth</th>
                                 <th>Address</th>
                                 <th>National</th>
                                 <th>Phone</th>
                                 <th>Background Disease</th>
                                 <th>Blood Type</th>
                             </tr>
                         </thead>

                         <tbody id="tBody"> 
                             <c:forEach items="${requestScope.patients}" var="p">
                                 <tr>
                                     <td>
                                         <div>Room : A301</div>
                                         <div>Bed : 6</div>
                                     </td>
                                     <td>${p.accDetail.fullName}</td>
                                     <c:if test="${p.accDetail.gender eq 'True'}">
                                         <td>Male</td>
                                     </c:if>
                                     <c:if test="${p.accDetail.gender eq 'False'}">
                                         <td>Female</td>
                                     </c:if>
                                     <td>${p.accDetail.dateofbirth}</td>
                                     <td>${p.accDetail.address}</td>
                                     <td>${p.accDetail.nation}</td>
                                     <td>${p.accDetail.phone}</td>
                                     <c:if test="${p.backgroundDisease eq 'True'}">
                                         <td>Yes</td>
                                     </c:if>
                                     <c:if test="${p.backgroundDisease eq 'False'}">
                                         <td>No</td>
                                     </c:if>
                                     <td>${p.bloodType}</td>
                                     <c:if test="${role.id eq 3}">
                                         <td><a href="createTest?username=${p.accDetail.account.userName}" >Create Test</a></td>    
                                         <td><a href="viewTest?username=${p.accDetail.account.userName}" >View Test</a></td>  
                                         <td><a href="createInjection?username=${p.accDetail.account.userName}" >Create Ịnjection</a></td>  
                                         <td><a href="viewInjection?username=${p.accDetail.account.userName}" >View Ịnjection</a></td>  
                                         <td><a href="viewSymptom?username=${p.accDetail.account.userName}">View Symptom</a></td> 
                                     </c:if>
                                     <c:if test="${role.id eq 2}">
                                         <td>1/1/2022</td>    
                                         <td><a href="prescription-list?username=${p.accDetail.account.userName}" >View Prescription</a></td>  
                                         <td><a href="viewSymptom?username=${p.accDetail.account.userName}">View Symptom</a></td> 
                                     </c:if>
                                 </tr>
                             </c:forEach>
                         </tbody>
                     </table>
                 </div>
             </div>
             <jsp:include page="../base/footer.jsp" />   
        </div>

        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <<script src="../assets/js/doctor/perscriptionList.js" type="text/javascript"></script>
        <script>
            $(function () {
                $('[data-toggle="popover"]').popover({
                    trigger: 'focus'
                });
            });
        </script>
    </body>
</html>
