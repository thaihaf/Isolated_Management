<%-- 
    Document   : createMedicine
    Created on : Oct 11, 2022, 3:08:08 PM
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
        <link rel="stylesheet" href="../assets/css/doctor/create_prescription.css"/>

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
                                 <li class="breadcrumb-item"><a href="medicine-list">List Medicine</a></li>
                                 <li class="breadcrumb-item active" aria-current="page">Add Medicine</li>
                             </ol>
                         </nav>
                     </div>

                     <div class="top2  d-flex justify-content-center align-items-start gap-5">
                         <form class="needs-validation pt-5 right" novalidate>
                             <div class="form-row title-create">Create new Medicine</div>

                             <div class="form-row">
                                 <div class="col-md-12 mb-3">
                                     <label for="validationCustom01" class="lable">Shipment ID</label>
                                     <input type="text" class="form-control" id="validationCustom01" required>
                                     <div class="valid-feedback">
                                         Looks good!
                                     </div>
                                 </div>
                                 <div class="col-md-12 mb-3">
                                     <label for="validationCustom02" class="lable">Name Medicine</label>
                                     <input type="text" class="form-control" id="validationCustom02" required>
                                     <div class="valid-feedback">
                                         Looks good!
                                     </div>
                                 </div>
                             </div>
                             <div class="form-row">
                                 <div class="col-md-12 mb-3">
                                     <label for="validationCustom03" class="lable">Description</label>
                                     <textarea class="form-control" id="validationCustom06" rows="8" required></textarea>
                                     <div class="invalid-feedback">
                                         Please provide a valid city.
                                     </div>
                                 </div>
                             </div>

                             <div class="form-row">
                                 <div class="col-md-6 mb-3">
                                     <label for="validationCustom04" class="lable">Date Of Manufacture</label>
                                     <input type="date" class="form-control date1" name="date1" id="validationCustom04"  required>
                                     <div class="invalid-feedback">
                                         Please provide a valid state.
                                     </div>
                                 </div>
                                 <div class="col-md-6 mb-3">
                                     <label for="validationCustom05" class="lable">Expiration Date</label>
                                     <input type="date" class="form-control date2" name="date2" id="validationCustom05" required>
                                     <div class="invalid-feedback">
                                         Please provide a valid zip.
                                     </div>
                                 </div>
                             </div>

                             <div class="form-row">
                                 <div class="col-md-3 mb-3">
                                     <label for="validationCustom05" class="lable">Quanty</label>
                                     <input type="number" id="validationCustom08"  class="form-control date2" name="quantity" value="1" min="1" max="5" required>
                                     <div class="invalid-feedback">
                                         Please provide a valid zip.
                                     </div>
                                 </div>

                                 <div class="col-md-9 mb-3">
                                     <label for="validationCustom04" class="lable">Type Medicine</label>
                                     <select class="form-control date1" name="type" id="validationCustom07"  required>
                                         <option>1</option>
                                         <option>2</option>
                                         <option>3</option>
                                         <option>4</option>
                                         <option>5</option>
                                     </select>
                                     <div class="invalid-feedback">
                                         Please provide a valid state.
                                     </div>
                                 </div>
                             </div>

                             <div class="mt-5 d-flex justify-content-end">
                                 <button class="btn btn-primary btn-lg mr-3" type="submit">Cancel</button>
                                 <button class="btn btn-primary btn-lg" type="submit">Submit</button>
                             </div>
                         </form>

                         <div class="left ml-5">
                             <img src="../assets/img/create-medicine-bg.png" alt="alt"/>
                         </div>

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
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict';
                window.addEventListener('load', function () {
                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                    var forms = document.getElementsByClassName('needs-validation');
                    // Loop over them and prevent submission
                    var validation = Array.prototype.filter.call(forms, function (form) {
                        form.addEventListener('submit', function (event) {
                            if (form.checkValidity() === false) {
                                event.preventDefault();
                                event.stopPropagation();
                            }
                            form.classList.add('was-validated');
                        }, false);
                    });
                }, false);
            })();
        </script>
    </body>
</html>
