<%-- 
    Document   : doctorViewPrescription
    Created on : Sep 25, 2022, 10:10:53 PM
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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/doctor/prescription_list.css"/>
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
                                 <li class="breadcrumb-item"><a href="/patient">Patient List</a></li>
                                 <li class="breadcrumb-item active" aria-current="page">Patient Pressciption</li>
                             </ol>
                         </nav>

                         <div class="search">
                             <input 
                                 type="text" 
                                 class="form-control" 
                                 placeholder="Search title ..." 
                                 aria-label="Search title..." 
                                 aria-describedby="basic-addon2">
                             <button type="button" class="btn btn-info">Search</button>
                         </div>
                     </div>

                     <div class="top2">
                         <div class="info">
                             <div class="info_name">Nguyễn Thái Hà</div>
                             <div class="info_bed">Area : A301 - Bed : 3</div>
                         </div>

                         <div class="filter">
                             <div class="dropdown show">
                                 <a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                     Sort  by
                                 </a>

                                 <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                                     <a class="dropdown-item" href="#">Date</a>
                                     <a class="dropdown-item" href="#">Status</a>
                                     <a class="dropdown-item" href="#">Amount</a>
                                 </div>
                             </div>
                             <button type="button" class="btn btn-primary btn-add">Add new +</button>
                         </div>
                     </div>

                     <div>
                         <table class="table">
                             <thead class="thead-light">
                                 <tr>
                                     <th scope="col"></th>
                                     <th scope="col">Date</th>
                                     <th scope="col">Title</th>
                                     <th scope="col">Medicine</th>
                                     <th scope="col">Guide</th>
                                     <th scope="col">Amount</th>
                                     <th scope="col">Status</th>
                                     <th scope="col">Action</th>
                                 </tr>
                             </thead>
                             <tbody>
                                 <tr>
                                     <th scope="row">1</th>
                                     <td>9/9/2022</td>
                                     <td>Abc</td>
                                     <td>
                                         <div class="info_group">
                                             <button 
                                                 type="button" 
                                                 class="info_btn" 
                                                 data-toggle="popover" 
                                                 data-placement="top"  
                                                 title="Popover title" 
                                                 data-content="And here's some amazing content. It's very engaging. Right?">
                                                 <ion-icon 
                                                     name="information-circle" 
                                                     class="info_icon"></ion-icon>
                                             </button> 
                                             Thuốc A - 10 viên
                                         </div>
                                         <div class="info_group">
                                             <button 
                                                 type="button" 
                                                 class="info_btn" 
                                                 data-toggle="popover" 
                                                 data-placement="top"  
                                                 title="Popover title" 
                                                 data-content="And here's some amazing content. It's very engaging. Right?">
                                                 <ion-icon 
                                                     name="information-circle" 
                                                     class="info_icon"></ion-icon>
                                             </button> 
                                             Thuốc A - 10 viên
                                         </div>
                                         <div class="info_group">
                                             <button 
                                                 type="button" 
                                                 class="info_btn" 
                                                 data-toggle="popover" 
                                                 data-placement="top"  
                                                 title="Popover title" 
                                                 data-content="And here's some amazing content. It's very engaging. Right?">
                                                 <ion-icon 
                                                     name="information-circle" 
                                                     class="info_icon"></ion-icon>
                                             </button> 
                                             Thuốc A - 10 viên
                                         </div>
                                         <div class="info_group">
                                             <button 
                                                 type="button" 
                                                 class="info_btn" 
                                                 data-toggle="popover" 
                                                 data-placement="top"  
                                                 title="Popover title" 
                                                 data-content="And here's some amazing content. It's very engaging. Right?">
                                                 <ion-icon 
                                                     name="information-circle" 
                                                     class="info_icon"></ion-icon>
                                             </button> 
                                             Thuốc A - 10 viên
                                         </div>
                                     </td>
                                     <td>
                                         <div class="guide_group">Thuốc 1 uống sau bữa ăn, mỗi lần 2 viên</div>
                                         <div class="guide_group">Thuốc 2 uống sau bữa ăn, mỗi lần 2 viên</div>
                                         <div class="guide_group">Thuốc 3 uống sau bữa ăn, mỗi lần 2 viên</div>
                                         <div class="guide_group">Thuốc 4 uống sau bữa ăn, mỗi lần 2 viên</div>
                                     </td>
                                     <td>210 vnđ</td>
                                     <td>pending</td>
                                     <td>Delete</td>
                                 </tr>
                                 <tr>
                                     <th scope="row">2</th>
                                     <td>5/9/2022</td>
                                     <td>Abc</td>
                                     <td>
                                         <div class="info_group">
                                             <button 
                                                 type="button" 
                                                 class="info_btn" 
                                                 data-toggle="popover" 
                                                 data-placement="top"  
                                                 title="Popover title" 
                                                 data-content="And here's some amazing content. It's very engaging. Right?">
                                                 <ion-icon 
                                                     name="information-circle" 
                                                     class="info_icon"></ion-icon>
                                             </button> 
                                             Thuốc A - 10 viên
                                         </div> 
                                         <div class="info_group">
                                             <button 
                                                 type="button" 
                                                 class="info_btn" 
                                                 data-toggle="popover" 
                                                 data-placement="top"  
                                                 title="Popover title" 
                                                 data-content="And here's some amazing content. It's very engaging. Right?">
                                                 <ion-icon 
                                                     name="information-circle" 
                                                     class="info_icon"></ion-icon>
                                             </button> 
                                             Thuốc A - 10 viên
                                         </div>
                                     </td>
                                     <td>
                                         <div class="guide_group">Thuốc 1 uống sau bữa ăn, mỗi lần 2 viên</div>
                                         <div class="guide_group">Thuốc 2 uống sau bữa ăn, mỗi lần 2 viên</div>
                                     </td>
                                     <td>100 vnđ</td>
                                     <td>done</td>
                                     <td>Delete</td>
                                 </tr>
                                 <tr>
                                     <th scope="row">3</th>
                                     <td>1/9/2022</td>
                                     <td>Abc</td>
                                     <td>
                                         <div class="info_group">
                                             <button 
                                                 type="button" 
                                                 class="info_btn" 
                                                 data-toggle="popover" 
                                                 data-placement="top"  
                                                 title="Popover title" 
                                                 data-content="And here's some amazing content. It's very engaging. Right?">
                                                 <ion-icon 
                                                     name="information-circle" 
                                                     class="info_icon"></ion-icon>
                                             </button> 
                                             Thuốc A - 10 viên
                                         </div>
                                         <div class="info_group">
                                             <button 
                                                 type="button" 
                                                 class="info_btn" 
                                                 data-toggle="popover" 
                                                 data-placement="top"  
                                                 title="Popover title" 
                                                 data-content="And here's some amazing content. It's very engaging. Right?">
                                                 <ion-icon 
                                                     name="information-circle" 
                                                     class="info_icon"></ion-icon>
                                             </button> 
                                             Thuốc A - 10 viên
                                         </div>
                                     </td>
                                     <td>
                                         <div class="guide_group">Thuốc 1 uống sau bữa ăn, mỗi lần 2 viên</div>
                                         <div class="guide_group">Thuốc 2 uống sau bữa ăn, mỗi lần 2 viên</div>
                                     </td>
                                     <td>100 vnđ</td>
                                     <td>done</td>
                                     <td>Delete</td>
                                 </tr>
                             </tbody>
                         </table>
                     </div>
                 </div>
             <jsp:include page="../base/footer.jsp" />   
        </div>


        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
        <script>
            $(function () {
                $('[data-toggle="popover"]').popover({
                    trigger: 'focus'
                });
            });
        </script>
    </body>
</html>
