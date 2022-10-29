/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function todaySched() {
    $.ajax({
        url: 'today_sched',
        method: 'GET',
        success: function (response) {
            $(".container").html(response);
        }
    });
}

function room() {
    $(function () {
        $("#modal").dialog({
            autoOpen: false,
            modal: true,
            title: "Patients in room",
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
        $(document).delegate("#room", 'click', function () {
            $.ajax({
                url: 'get_room',
                method: 'POST',
                data: {
                    room: $(this).attr('room-id')
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
    todaySched();

    room();
});