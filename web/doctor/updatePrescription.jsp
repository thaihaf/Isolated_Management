<%-- 
    Document   : updatePrescription
    Created on : Oct 14, 2022, 12:13:58 PM
    Author     : hapro
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
        <link rel="stylesheet" href="../assets/css/doctor/createPrescription.css"/>
    </head>
    <body>
        <c:set var="role" value="${sessionScope.account.role}"/>
        <jsp:include page="../base/sidebar.jsp" />
        <jsp:include page="../base/header.jsp" />

        <div class=<c:if test="${role.id eq 1}">"wrapper wrapperAdmin"</c:if>
             <c:if test="${role.id ne 1}">"wrapper wrapperUser"</c:if>
                 >
                 <div class="container-fluid">
                     <div class="top1">
                         <nav aria-label="breadcrumb">
                             <ol class="breadcrumb">
                                 <li class="breadcrumb-item"><a href="../base/home.jsp">Home</a></li>
                                 <li class="breadcrumb-item"><a href="listpatient">Patient List</a></li>
                                 <li class="breadcrumb-item"><a href="prescription-list?username=${param.username}">Pressciption List</a></li>
                             <li class="breadcrumb-item active" aria-current="page">Update Prescription</li>
                         </ol>
                     </nav>
                 </div>

                 <form class="top2 form">
                     <div class="form-group">
                         <div class="form-item">
                             <label for="recipient-name" class="col-form-label">Symptom</label>
                             <input type="text" class="form-control create" id="title-text" value="${requestScope.prescription.title}">
                         </div>
                         <div class="form-item">
                             <label for="message-text" class="col-form-label">Guide</label>
                             <textarea class="form-control create" id="guide-text" rows="6" cols="25">${requestScope.prescription.guide}</textarea>
                         </div>
                         <div class="form-item search">
                             <div class="form-item">
                                 <label for="message-text" class="col-form-label">Search by name</label>
                                 <input
                                     type="text" 
                                     class="form-control create list" 
                                     placeholder="Search by name ..." 
                                     aria-label="Search by name..." 
                                     aria-describedby="basic-addon2"
                                     id="searchByName"
                                     >
                             </div>
                             <div class="form-item">
                                 <label for="message-text" class="col-form-label">Search by type</label>
                                 <input
                                     type="text" 
                                     class="form-control create list" 
                                     placeholder="Search by type ..." 
                                     aria-label="SSearch by type..." 
                                     aria-describedby="basic-addon2"
                                     id="searchByType"
                                     >
                             </div>

                             <div class="search-list hidden">
                                 <div class="title d-flex align-items-center justify-content-between">
                                     <span>Medicines : </span>
                                     <span id="close-medicines"><ion-icon name="close-outline"></ion-icon></span>
                                 </div>
                                 <ul class="list-group"></ul>
                             </div>
                         </div>
                     </div>
                     <div class="form-group info">
                         <div class="info_1">
                             <div class="info_avt">
                                 <img src="../assets/img/avt.jpg" alt="alt" class="img"/>
                             </div>
                             <table>
                                 <tr>
                                     <td>Fullname</td>
                                     <td>${requestScope.patient.accDetail.fullName}</td>
                                 </tr>
                                 <tr>
                                     <td>Date of birth</td>
                                     <td>${requestScope.patient.accDetail.dateofbirth}</td>
                                 </tr>
                                 <tr>
                                     <td>Gender</td>
                                     <td>${requestScope.patient.accDetail.gender == "0" ? "Male" : "Female"}</td>
                                 </tr>
                                 <tr>
                                     <td>Area</td>
                                     <td>${requestScope.patient.room.area.name}${requestScope.patient.room.name} - Bed : ${requestScope.patient.room.numOfBed}</td>
                                 </tr>
                                 <tr>
                                     <td>Phone</td>
                                     <td>${requestScope.patient.accDetail.phone}</td>
                                 </tr>
                             </table>
                         </div>

                         <div class="info_2">
                             <button type="button" class="btn btn-success btn-lg" id="btn_submit">Submit</button>
                             <button type="reset" class="btn btn-secondary btn-lg"  id="btn_reset">Reset</button>
                         </div>
                     </div>
                 </form>


                 <div>
                     <table class="table">
                         <thead class="thead-light">
                             <tr>
                                 <th scope="col">Medicine name</th>
                                 <th scope="col">Quantity</th>
                                 <th scope="col">Stock</th>
                                 <th scope="col">Unit</th>
                                 <th scope="col">Date of Manufacture</th>
                                 <th scope="col">Expiration date</th>
                                 <th scope="col">Type</th>
                                 <th scope="col">Action</th>
                             </tr>
                         </thead>
                         <tbody id="tBody">
                             <c:forEach items="${requestScope.prescription.medicines}" var="m" varStatus="loop">
                                 <tr class="medicine_item" id="${m.shipmentID}-${requestScope.prescription.prescriptionMedicines[loop.index].quantity}">
                                     <td>${m.name}</td>
                                     <td>${requestScope.prescription.prescriptionMedicines[loop.index].quantity}</td>
                                     <td>${m.quantity}</td>
                                     <td>${requestScope.prescription.medicineTypes[loop.index].dosage}</td>
                                     <td>${m.dateOfManufacture}</td>
                                     <td>${m.expirationDate}</td>
                                     <td>${requestScope.prescription.medicineTypes[loop.index].type}</td>
                                     <td>
                                         <button class="btn btn-success" id="${m.shipmentID}">Delete</button>
                                     </td>
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
        <script src="../assets/js/doctor/updatePres3.js "type="text/javascript"></script>
    </body>
</html>

