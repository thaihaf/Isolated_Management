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
        <link rel="stylesheet" href="../assets/css/doctor/medicine_list.css"/>

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
                                 <li class="breadcrumb-item active" aria-current="page">Medicine List</li>
                             </ol>
                         </nav>
                     </div>

                     <div class="overview">
                         <div class="overview_item">
                             <div class="overview_item-title">Total Medicine</div>
                             <div class="overview_item-number">100</div>
                         </div>
                         <div class="overview_item">
                             <div class="overview_item-title">In stock of Medicine</div>
                             <div class="overview_item-number">100</div>
                         </div>
                         <div class="overview_item">
                             <div class="overview_item-title">Out stock of Medicine</div>
                             <div class="overview_item-number">100</div>
                         </div>
                         <div class="overview_item">
                             <div class="overview_item-title">Expiration Medicine</div>
                             <div class="overview_item-number">100</div>
                         </div>
                         <div class="overview_item">
                             <div class="overview_item-title">Expired Medicine</div>
                             <div class="overview_item-number">100</div>
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
                     <tbody>
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
                 <nav aria-label="Page navigation example">
                     <ul class="pagination pagination-lg justify-content-center">
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

