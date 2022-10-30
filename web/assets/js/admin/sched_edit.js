/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function save() {
    $("#submitButton").click(function (event) {
        event.preventDefault();
        var form = $("#edit");
        $.ajax({
            url: form.attr('action'),
            method: form.attr('method'),
            data: form.serialize(),
            success: function (response) {
                $("#response").html(response);
                
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
}

$(document).ready(function () {
    save();
});

