/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function exercise() {
    $(function () {
        $("#modal").dialog({
            autoOpen: false,
            modal: true,
            title: "Exercise in schedule",
            position: {
                my: 'center',
                at: 'center',
                of: window
            },
            buttons: {
                Close: function () {
                    $(this).dialog('close');
                }
            }
        });
        $(document).delegate("#exercise", 'click', function () {
            $.ajax({
                url: '../get_exercise',
                method: 'POST',
                data: {
                    exid: $(this).attr('sched-id')
                },
                success: function (response) {
                    $("#modal").html(response);
                    $("#modal").dialog('open');
                }
            });
        });
    });
}

$(document).ready(function () {
    exercise();
});