/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$('#areaSelect').on('change', function () {
    let search = $(location).attr('search');
    var areaId = $("#areaSelect option:selected").val();

    $.ajax({
        url: "/Isolated_Management/base/createTest" + search,
        type: "post",
        data: {
            addToRoom: true,
            areaId: areaId
        },
        success: function (data) {
            var arrOutput = data.split("|split|");

            $("#roomSelect").html(arrOutput[0]);
            $("#roomListBody").html(arrOutput[1]);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});

$(document).on('input', '#searchRoomByName', function () {
    let search = $(location).attr('search');
    var areaId = $("#areaSelect option:selected").val();
    let roomName = $('#searchRoomByName').val();

    $.ajax({
        url: "/Isolated_Management/base/createTest" + search,
        type: "post",
        data: {
            addToRoom: true,
            areaId: areaId,
            roomName: roomName
        },
        success: function (data) {
            $("#roomListBody").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});
//$(document).on('click', '.updateInfo_btnSubmit', function () {
//    let search = $(location).attr('search');
//    var areaId = $("#areaSelect option:selected").val();
//    let roomName = $('#searchRoomByName').val();
//
//    $.ajax({
//        url: "/Isolated_Management/base/createTest" + search,
//        type: "post",
//        data: {
//            addToRoom: true,
//            areaId: areaId,
//            roomName: roomName
//        },
//        success: function (data) {
//            $("#roomListBody").html(data);
//        },
//        error: function (xhr) {
//            //Do Something to handle error
//        }
//    });
//});