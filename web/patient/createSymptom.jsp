<%-- 
    Document   : createSymptom
    Created on : Oct 27, 2022, 8:15:34 PM
    Author     : Admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../assets/css/base.css"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="../assets/css/base/home.css"/>     
        <title>JSP Page</title>
        <script>
            function removeStu(id)
            {
                var result = confirm('are you sure?');
                if (result)
                {
                    window.location.href = 'deleteInjection?id=' + id;
                }
            }
        </script>
    </head>
    <body>
        <jsp:include page="../base/sidebar.jsp" />
        <jsp:include page="../base/header.jsp" />
        <c:set var="username" value="${sessionScope.account.userName}" />
        <div class="wrapper wrapperUser">
            <form action="createSymptom" method="POST" class="profilForm container">
                <div class="container">
                    <!--Code vào đây là oke-->            
                    <h1 style="text-align: center">Symptom Declaration Form</h1>         
                    Full Name:<input style="margin-right: 100px;margin-left: 5px" type="text" value="${info.fullName}">
                    Date Of Birth:<input style="margin-right: 100px;margin-left: 5px" type="text" value="${info.dateofbirth}">
                    Gender: <input type="radio" title="Male" class="btn_radioInput" style=" margin: 0 0 0 10px;" <c:if test="${info.gender eq true}">
                                   checked="checked"
                        </c:if> name="gender"> Male
                    <input type="radio" title="Female" class="btn_radioInput" style=" margin: 0 0 0 10px;"<c:if
                               test="${info.gender eq false}">
                               checked="checked"
                           </c:if> name="gender"> Female<br/><br/>
                    Phone:<input type="text" value="${info.phone}" style="margin-right: 100px;margin-left: 5px">
                    Address:<input type="text" value="${info.address}" style="margin-right: 100px; margin-left: 5px">
                    Nation:<input type="text" value="${info.nation}" style="margin-left: 5px"><br><br>
                    <h1>Injection Information:</h1>
                    <a href="addExistInjection?username=${sessionScope.account.userName}">To add more injections click here</a>                
                    <c:set var="count" value="${1}"/>
                    <table class="table">
                        <tr style="color: red;">
                            <td></td>
                            <td>Patient ID</td>
                            <td>Vaccine</td>                  
                            <td>Inject Date</td>
                            <td>Note</td>
                            <td></td>
                        </tr>    
                        <c:choose>
                            <c:when test="${requestScope.list2 eq null}">
                                <h2 style="text-align: center">You do not have any injection or not declared</h2>
                            </c:when>
                            <c:when test="${requestScope.list2 ne null}">
                                <c:forEach items="${requestScope.list2}" var="l">
                                    <tr>
                                        <td>${count}</td>
                                        <td>${l.patientAccount.account.userName}</td>
                                        <td>${l.vaccine.vaccineName}</td>
                                        <td><c:choose>
                                                <c:when test="${l.date eq null}">
                                                    Not Remember
                                                </c:when>
                                                <c:when test="${l.date ne null}">
                                                    ${l.date}
                                                </c:when>
                                            </c:choose></td>
                                        <td><c:choose>
                                                <c:when test="${l.note eq null}">
                                                    None
                                                </c:when>
                                                <c:when test="${l.note ne null}">
                                                    ${l.note}
                                                </c:when>
                                            </c:choose></td>
                                        <td><input type="button" value="Delete" onclick="removeStu(${l.id})"></td>
                                    </tr>
                                    <c:set var="count" value="${count+1}" />  
                                </c:forEach>
                                <p>You have got ${count-1} injections</p>
                            </c:when>
                        </c:choose>
                    </table>

                    <h1> I. Symptoms</h1>
                    <c:if test="${check eq false}">
                        <h2>You do not have any symptom report, please complete this form to create it</h2>    
                    </c:if>
                    <c:if test="${check eq true}">
                        <a href="viewSymptom?username=${info.account.userName}">To view your symptom report history click here</a>  
                    </c:if>
                    <table class="table"> 
                        <tr>
                            <td>1</td>
                            <td>Đau họng, ho khan hoặc ho có đờm, đau rát họng dai dẳng.</td>
                            <td><input style=" margin: 0 0 0 20px;" type="radio" name="s1" value="Đau họng, ho khan hoặc ho có đờm, đau rát họng dai dẳng"/>YES
                                <input style=" margin: 0 0 0 20px;" checked="checked" type="radio" name="s1" value="" /> NO</td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>Đau đầu, đau nhức cơ.</td>
                            <td><input style=" margin: 0 0 0 20px;" type="radio" name="s2" value="Đau đầu, đau nhức cơ"/>YES
                                <input style=" margin: 0 0 0 20px;" checked="checked" type="radio" name="s2" value="" /> NO</td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>Mất vị giác, mất khứu giác.</td>
                            <td><input style=" margin: 0 0 0 20px;" type="radio" name="s3" value="Mất vị giác, mất khứu giác"/>YES
                                <input style=" margin: 0 0 0 20px;" checked="checked" type="radio" name="s3" value="" /> NO</td>
                        </tr>
                        <tr>
                            <td>4</td>
                            <td>Tiêu chảy.</td>
                            <td><input style=" margin: 0 0 0 20px;" type="radio" name="s4" value="Tiêu chảy"/>YES
                                <input style=" margin: 0 0 0 20px;" checked="checked" type="radio" name="s4" value="" /> NO</td>
                        </tr>
                        <tr>
                            <td>5</td>
                            <td>Trên da xuất hiện các nốt mẩn đỏ.</td>
                            <td><input style=" margin: 0 0 0 20px;" type="radio" name="s5" value="Trên da xuất hiện các nốt mẩn đỏ"/>YES
                                <input style=" margin: 0 0 0 20px;" checked="checked" type="radio" name="s5" value="" /> NO</td>
                        </tr>
                        <tr>
                            <td>6</td>
                            <td>Đau tức ngực kèm khó thở.</td>
                            <td><input style=" margin: 0 0 0 20px;" type="radio" name="s6" value="Đau tức ngực kèm khó thở"/>YES
                                <input style=" margin: 0 0 0 20px;" checked="checked" type="radio" name="s6" value="" /> NO</td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                            <td></td>
                        </tr>
                    </table>
                    <div style="margin-bottom: 20px">
                        <h3>Others Symptom:</h3> <input style="width: 100%; height: 100px; border-radius: 10px;" type="text" name="s7"><br><br>
                        <input style="margin-left: 86%; width: 150px; border-radius: 10px" type="submit" value="Send">
                    </div>
                </div>
            </form>
            <jsp:include page="../base/footer.jsp" />   
        </div>
    </body>
</html>
