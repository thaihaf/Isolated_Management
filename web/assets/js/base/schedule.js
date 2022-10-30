/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function getWeek() {
    $("#year").on('change', function () {
        $.ajax({
            url: "../get_week",
            method: "GET",
            data: {year: $(this).val()},
            success: function (response) {
                $("#week").html(response);
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

function refresh() {
    $.ajax({
        url: 'schedule',
        method: 'POST',
        data: $('#view').serialize(),
        processData: false,
        cache: false,
        success: function (response) {
            $(".table").html(response);
        }
    });
}

function get() {
    $('.button').click(function (event) {
        event.preventDefault();
        $.ajax({
            url: "schedule",
            method: "POST",
            data: $("#view").serialize(),
            processData: false,
            cache: false,
            success: function (response) {
                $(".table").html(response);
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
    refresh();

    get();

    getWeek();

    room();
});