/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

function refresh() {
    $.ajax({
        url: 'sched_list',
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
            url: "sched_list",
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

function changeYear() {
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

function deleteSchedule() {
    $(document).delegate("#deleteBtn", 'click', function (event) {
        event.preventDefault();
        if (confirm('Are you sure you want to delete this?')) {
            $.ajax({
                url: 'sched_delete',
                method: 'GET',
                data: {
                    sid: $(this).attr('data-id')
                },
                success: function () {
                    alert('Delete successfully');
                    refresh();
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
        }
    });
}

$(document).ready(function () {
    get();

    changeYear();

    deleteSchedule();

    refresh();
});