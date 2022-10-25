<%-- 
    Document   : listMedicine
    Created on : Oct 11, 2022, 3:25:44 PM
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
        <link rel="stylesheet" href="../assets/css/doctor/listMedicines.css"/>

    </head>
    <body>
        <c:set var="role" value="${sessionScope.account.role}"/>
        <jsp:include page="../base/sidebar.jsp" />
        <jsp:include page="../base/header.jsp" />


        <div class=<c:if test="${role.id eq 1}">"wrapper wrapperAdmin"</c:if>
             <c:if test="${role.id ne 1}">"wrapper wrapperUser"</c:if>
                 >
                 <div class="container-fluid">
                     <div class="modal fade" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                         <div class="modal-dialog" role="document">
                             <div class="modal-content">
                                 <div class="modal-header">
                                     <h5 class="modal-title" id="exampleModalLongTitle">Medicine Type</h5>
                                     <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                         <span aria-hidden="true">&times;</span>
                                     </button>
                                 </div>
                                 <div class="modal-body px-5">
                                     <form  action="medicine-list" method="POST" class="addMedicineType">
                                         <div class="form-row title-create">Create new Medicine Type</div>
                                         <div class="form-row">
                                             <div class="col-md-8 mb-3">
                                                 <label for="validationCustom01" class="lable">Medicine Type</label>
                                                 <input 
                                                     type="text" 
                                                     class="form-control" 
                                                     name="medicineType" 
                                                     id="medicineType" 
                                                     >
                                             </div>
                                             <div class="col-md-4 mb-3">
                                                 <label for="validationCustom02" class="lable">Dosage</label>
                                                 <input 
                                                     type="text" 
                                                     class="form-control" 
                                                     name="medicineDosage" 
                                                     id="medicineDosage" 
                                                     >
                                             </div>
                                         </div>
                                         <div class="form-row mb-5 mr-1 d-flex justify-content-end">
                                             <button class="btn btn-primary btn-lg mr-3" type="reset">Reset</button>
                                             <button class="btn btn-primary btn-lg" id="btn_submit" type="button">Submit</button>
                                         </div>
                                     </form>

                                     <div class="listMedicineType">
                                         <div class="form-row title-create">List Medicine Type</div>
                                         <div class="form-row mr-1 d-flex justify-content-end">
                                             <div class="search">
                                                 <input 
                                                     type="text" 
                                                     class="form-control list input-group-lg" 
                                                     placeholder="Search type ..." 
                                                     aria-label="Search title..." 
                                                     aria-describedby="basic-addon2"
                                                     id="searchMTByType"
                                                     >
                                             </div>
                                             <div class="search">
                                                 <input 
                                                     type="text" 
                                                     class="form-control list input-group-lg" 
                                                     placeholder="Search dosage ..." 
                                                     aria-label="Search title..." 
                                                     aria-describedby="basic-addon2"
                                                     id="searchMTByDosage"
                                                     >
                                             </div>
                                         </div>

                                         <table class="table table-hover">
                                             <thead>
                                                 <tr>
                                                     <th scope="col">Medicine Type</th>
                                                     <th scope="col">Dosage</th>
                                                     <th scope="col">Action</th>
                                                 </tr>
                                             </thead>
                                             <tbody id="medicineTypeBody">
                                             <c:forEach items="${requestScope.medicineTypes}" var="mt">
                                                 <tr class="parent">
                                                     <th scope="row">
                                                         <input 
                                                             type="text" 
                                                             class="form-control medicineType" 
                                                             name="medicineType" 
                                                             value="${mt.type}"
                                                             >
                                                     </th>
                                                     <td> 
                                                         <input 
                                                             type="text" 
                                                             class="form-control medicineDosage" 
                                                             name="medicineDosage" 
                                                             value="${mt.dosage}"
                                                             >
                                                     </td>
                                                     <td><button type="button" id="${mt.id}" class="btn btn-success btn-update-mt">Update</button></td>
                                                 </tr>
                                             </c:forEach>
                                         </tbody>
                                     </table> 
                                 </div>
                             </div>
                         </div>
                     </div>
                 </div>

                 <div class="top1">
                     <nav aria-label="breadcrumb">
                         <ol class="breadcrumb">
                             <li class="breadcrumb-item"><a href="../base/home.jsp">Home</a></li>
                             <li class="breadcrumb-item active" aria-current="page">Medicine List</li>
                         </ol>
                     </nav>
                 </div>

                 <div class="overview">
                     <div class="overview_item" id="get-total">
                         <div class="overview_item-title">Total</div>
                         <div class="overview_item-number">${requestScope.total}</div>
                     </div>
                     <div class="overview_item" id="get-in-stock">
                         <div class="overview_item-title">In stock</div>
                         <div class="overview_item-number">${requestScope.inStockNum}</div>
                     </div>
                     <div class="overview_item" id="get-out-stock">
                         <div class="overview_item-title">Out stock</div>
                         <div class="overview_item-number">${requestScope.outStockNum}</div>
                     </div>
                     <div class="overview_item" id="get-expiration-date">
                         <div class="overview_item-title">Expiration Date</div>
                         <div class="overview_item-number">${requestScope.expirationNum}</div>
                     </div>
                     <div class="overview_item" id="get-expired-date">
                         <div class="overview_item-title">Expired Date</div>
                         <div class="overview_item-number">${requestScope.expiredNum}</div>
                     </div>
                 </div>

                 <div class="top2">
                     <div class="filter">
                         <a href="create-medicine" class="btn btn-primary btn-add">Add Medicine</a>

                         <div class="dropdown show">
                             <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                 Sort  by
                             </a>

                             <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                 <div class="dropdown-item" id="sortByName">Name</div>
                                 <div class="dropdown-item" id="sortByQuantity">Quantity</div>
                                 <div class="dropdown-item" id="sortByType">Type</div>
                             </div>
                         </div>

                         <button type="button" class="btn btn-primary btn-add" data-toggle="modal" data-target="#exampleModalLong">
                             View Medicine Type
                         </button>
                     </div>

                     <div class="search">
                         <input 
                             type="text" 
                             class="form-control list input-group-lg" 
                             placeholder="Search name ..." 
                             aria-label="Search title..." 
                             aria-describedby="basic-addon2"
                             id="inputSearch"
                             >
                     </div>
                 </div>

                 <table class="table table-hover">
                     <thead>
                         <tr>
                             <th scope="col">Shipment ID</th>
                             <th scope="col">Name</th>
                             <th scope="col">Description</th>
                             <th scope="col">Quantity</th>
                             <th scope="col">Date of Manufacture</th>
                             <th scope="col">Expiration Date</th>
                             <th scope="col">Type</th>
                             <th scope="col">Dosage</th>
                             <th scope="col">Action</th>

                         </tr>
                     </thead>
                     <tbody id="tBody">
                         <c:forEach items="${requestScope.medicines}" var="m">
                             <tr>
                                 <th scope="row">${m.shipmentId}</th>
                                 <td>${m.name}</td>
                                 <td>
                                     <div class="des_show" >
                                         <button type="button" class="btn btn-success">View</button>
                                         <div class="des_hidden">
                                             ${m.description}
                                         </div>
                                     </div>
                                 </td>
                                 <td>${m.stock}</td>
                                 <td>${m.dateManafacture}</td>
                                 <td>${m.expirationDate}</td>
                                 <td>${m.medicineType.type}</td>
                                 <td>${m.medicineType.dosage}</td>
                                 <td>
                                     <a href="/Isolated_Management/base/update-medicine?id=${m.shipmentId}" class="btn btn-success">Update</a>
                                 </td>
                             </tr>
                         </c:forEach>

                     </tbody>
                 </table>  


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
        <script src="../assets/js/doctor/listMedicines.js" type="text/javascript"></script>
        <script>
            $(function () {
                $('[data-toggle="popover"]').popover({
                    trigger: 'focus'
                });
            });
        </script>
    </body>
</html>

