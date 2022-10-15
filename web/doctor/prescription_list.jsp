<%-- 
    Document   : doctorViewPrescription
    Created on : Sep 25, 2022, 10:10:53 PM
    Author     : hapro
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:set var = "pId" scope = "session" value = ""/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/doctor/list_prescriptions.css"/>

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
                                 <li class="breadcrumb-item active" aria-current="page">Pressciption List</li>
                             </ol>
                         </nav>

                         <div class="info">
                             <div class="info_name">Patient : Nguyễn Thái Hà</div>
                             <div class="info_bed">Area : A301 - Bed : 3</div>
                         </div>
                     </div>

                     <div class="top2">
                         <div class="filter">
                             <a href="create-prescription?username=${param.username}" class="btn btn-primary btn-add">Add new +</a>

                             <div class="dropdown show">
                                 <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                     Sort  by
                                 </a>

                                 <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                     <div class="dropdown-item" id="sortByDate">Date</div>
                                     <div class="dropdown-item" id="sortByStatus">Status</div>
                                 </div>
                             </div>

                             <div class="dateFilter">
                                 Date Create From <input type="date" name="from" value="${param.from}" id="dateFrom"/>
                             To <input type="date" name="to" value="${param.to}" id="dateTo"/>
                             <button 
                                 type="button" 
                                 class="btn btn-primary btn-info"
                                 id="filterDay">
                                 Apply
                             </button>
                         </div>
                     </div>

                     <div class="search">
                         <input 
                             type="text" 
                             class="form-control list input-group-lg" 
                             placeholder="Search title ..." 
                             aria-label="Search title..." 
                             aria-describedby="basic-addon2"
                             id="inputSearch"
                             >
                     </div>
                 </div>

                 <div>
                     <table class="table">
                         <thead class="thead-light">
                             <tr>
                                 <th scope="col"></th>
                                 <th scope="col">Date Create</th>
                                 <th scope="col">Expression</th>
                                 <th scope="col" class="thMedicine">Medicines</th>
                                 <th scope="col">Guides</th>
                                 <th scope="col">Status</th>
                             </tr>
                         </thead>
                         <tbody id="tBody">
                             <c:forEach items="${requestScope.prescriptions}" var="p">
                                 <tr>
                                     <th scope="row">${p.id}</th>
                                     <td>${p.prescriptionDate}</td>
                                     <td>${p.title}</td>
                                     <td>
                                         <c:forEach items="${p.medicines}" var="m" varStatus="loop">
                                             <div class="info_group">
                                                 <div class="info_main">
                                                     <ion-icon  name="help-circle" class="info_icon"></ion-icon>
                                                     <div class="info_description">
                                                         <div>Số lô : ${m.shipmentID}</div>
                                                         <div>Ngày sản xuất : ${m.dateOfManufacture}</div>
                                                         <div>Công dụng : ${m.description}</div>
                                                     </div>
                                                 </div>
                                                 <div>
                                                     <div>
                                                         <span class="medicine_caption">Medicine ${loop.index + 1}</span> : 
                                                         ${m.name} - 
                                                         ${p.prescriptionMedicines[loop.index].quantity} 
                                                         ${p.medicineTypes[loop.index].dosage}
                                                     </div>
                                                     <div><span class="medicine_caption">Expiration date </span> : ${m.expirationDate}</div>
                                                     <div><span class="medicine_caption">Type </span> : ${p.medicineTypes[loop.index].type}</div>
                                                 </div>
                                             </div>
                                         </c:forEach>
                                     </td>
                                     <td>${p.guide}</td>
                                     <td>
                                         <c:set var = "type" scope = "session" value = "${p.status}"/>
                                         <c:choose>
                                             <c:when test = "${type == 0}">
                                                 Đang phát thuốc
                                             </c:when>

                                             <c:when test = "${type == 1}">
                                                 Đã phát thuốc
                                             </c:when>
                                         </c:choose>

                                     </td>
                                 </tr>

                             </c:forEach>
                         </tbody>
                     </table>

                     <nav aria-label="Page navigation example">
                         <ul class="pagination justify-content-center pagination-lg">
                             <li class="page-item disabled">
                                 <a class="page-link" href="#" tabindex="-1">Previous</a>
                             </li>
                             <li class="page-item"><a class="page-link" href="#">1</a></li>
                             <li class="page-item"><a class="page-link" href="#">2</a></li>
                             <li class="page-item"><a class="page-link" href="#">3</a></li>
                             <li class="page-item">
                                 <a class="page-link" href="#">Next</a>
                             </li>
                         </ul>
                     </nav>
                 </div>
             </div>
             <jsp:include page="../base/footer.jsp" />   
        </div>

        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <script src="../assets/js/doctor/perscriptionList.js" type="text/javascript"></script>
        <script>
            $(function () {
                $('[data-toggle="popover"]').popover({
                    trigger: 'focus'
                });
            });
        </script>
    </body>
</html>
