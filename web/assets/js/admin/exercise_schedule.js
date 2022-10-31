/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
$.validator.addMethod('onecheck', function (value, ele) {
    return $("input:checked").length >= 1;
}, 'Please select at least 1 exercise.');

function handleData()
{
    event.preventDefault();
    var form_data = new FormData(document.querySelector("form"));
    if (!form_data.has("ex[]"))
    {
        document.getElementById("error").style.visibility = "visible";
        return false;
    } else
    {
        document.getElementById("error").style.visibility = "hidden";
        var $form = $("#assignForm");
        $.ajax({
            url: $form.attr('action'),
            method: $form.attr('method'),
            data: $form.serializeArray(),
            success: function (response) {
                alert(response);
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
        return true;
    }

}