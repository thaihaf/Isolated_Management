/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

window.addEventListener('load', () => {
    var cal = new Date();
    cal.setMinutes(cal.getMinutes() - cal.getTimezoneOffset());
    cal.setMilliseconds(null);
    cal.setSeconds(null);
    document.getElementById('time').value = cal.toISOString().slice(0, -1);
});
$(document).ready(function () {
    $(".schedule").submit(function (event) {
        event.preventDefault();
        $.ajax({
            method: $(this).attr('method'),
            data: $(this).serialize(),
            url: $(this).attr('action'),
            processData: false,
            cache: false,
            success: function (response) {
                $(".response").html(response);
            }
        });
    });
});