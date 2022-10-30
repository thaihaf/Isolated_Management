/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

window.addEventListener('load', () => {
    var cal = new Date();
    cal.setMinutes(cal.getMinutes() - cal.getTimezoneOffset());
    cal.setMilliseconds(null);
    cal.setSeconds(null);
    document.getElementById('date').value = cal.toISOString().substr(0, 10);
    document.schedule.time[0].checked = true;
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
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401) {
                    alert('Access denied.');
                } else {
                    alert(jqXHR.status);
                }
                window.location.href = '../login';
            }
        });
    });
});