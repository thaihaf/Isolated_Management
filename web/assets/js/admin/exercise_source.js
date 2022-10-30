/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
function deleteExercise() {
    $(document).delegate("#btnDelete", 'click', function (event) {
        event.preventDefault();
        if (confirm('Are you sure you want to delete this?')) {
            $.ajax({
                url: 'exercise_delete',
                method: 'GET',
                data: {
                    eid: $(this).attr('data-id')
                },
                success: function () {
                    alert('Delete successfully.');
                    window.location.href = 'exercise_list';
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

function content() {
    $(function () {
        $("#content").dialog({
            autoOpen: false,
            modal: true,
            title: 'Exercise content',
            position: {
                my: 'center',
                at: 'center',
                of: window
            },
            buttons: {
                Close: function () {
                    $(this).dialog('close');
                    $("#content").empty();
                }
            },
            width: '20%'
        });
        $(document).delegate("#src", 'click', function () {
            $("#content").empty();
            var type = $(this).attr('data-id');
            var link = $(this).attr('data');
            console.log(type + ' ' + link);
            if (type === '2') {
                getHtml(link).then(function (result) {
                    $("#content").html(result);
                    $("iframe").attr('width', '100%');
                    $("iframe").attr('height', '100%');
                });
            }
            if (type === '3') {
                $("#content").append("<img width=\"100%\" height=\"100%\" src=\"" + link + "\"/><br/>");
            }
            $("#content").dialog('open');
        });
    });
}

function getHtml(link) {
    console.log(link);
    return fetch('https://www.youtube.com/oembed?url=' + link + '&format=json').then((response) => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Server not responding.');
        }
    }).then((data) => {
        const html = data.html;
        return html;
    });
}

$(document).ready(function () {
    content();

    deleteExercise();
});