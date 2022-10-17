/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$(".overview_item").on('click', function () {
    let typeSearch = $(this).attr("id");

    console.log(typeSearch);
    $.ajax({
        url: "/Isolated_Management/base/medicine-list",
        type: "post",
        data: {
            typeSearch: typeSearch
        },
        success: function (data) {
            $("#tBody").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});

$('#inputSearch').on('input', function () {
    let text = $('#inputSearch').val();
    console.log(text);

    $.ajax({
        url: "/Isolated_Management/base/medicine-list",
        type: "post",
        data: {
            searchVal: text
        },
        success: function (data) {
            $("#tBody").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});
$('.dropdown-item').on('click', function () {
    let typeSort = $(this).attr("id");

    $.ajax({
        url: "/Isolated_Management/base/medicine-list",
        type: "post",
        data: {
            typeSort: typeSort
        },
        success: function (data) {
            $("#tBody").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});