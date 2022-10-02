/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

$('#searchByName').on('input', function () {
    let text = $('#searchByName').val();
    let search = $(location).attr('search');

    if ($('.search-list').hasClass("hidden")) {
        $('.search-list').removeClass("hidden");
    }

    $.ajax({
        url: "/Isolated_Management/base/create-prescription" + search,
        type: "post",
        data: {
            searchByName: text
        },
        success: function (data) {
            $(".list-group").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});
$('#searchByType').on('input', function () {
    let text = $('#searchByType').val();
    let search = $(location).attr('search');

    if ($('.search-list').hasClass("hidden")) {
        $('.search-list').removeClass("hidden");
    }

    $.ajax({
        url: "/Isolated_Management/base/create-prescription" + search,
        type: "post",
        data: {
            searchByType: text
        },
        success: function (data) {
            $(".list-group").html(data);
        },
        error: function (xhr) {
            console.log("error");
            //Do Something to handle error
        }
    });
});

let listPm = [];

$(document).on('click', '.btn-add', function () {
    let search = $(location).attr('search');
    let id = Number(this.id);

    let isObjectPresent = listPm.find((o) => o.id === id);
    if (isObjectPresent) {
        listPm = listPm.map((item) => {
            if (item.id === id) {
                return {id: id, quantity: item.quantity + 1};
            } else {
                return item;
            }
        });
    } else {
        listPm.push({id: id, quantity: 1});

    }

    $.ajax({
        url: "/Isolated_Management/base/create-prescription" + search,
        type: "post",
        data: {
            listPm: JSON.stringify(listPm)
        },
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        success: function (data) {
            $("#tBody").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});

$("#close-medicines").click(() => {
    $('.search-list').addClass("hidden");
});

$("#btn_submit").click(() => {
    let title_text = $("#title-text").val().toString();
    let guide_text = $("#guide-text").val().toString();

    if (listPm.length === 0) {
        alert("You must choose Medicine");
    } else if (title_text === ""){
        alert("You must input Symptom");
        
    } else {
        let search = $(location).attr('search');

        let json = JSON.stringify(listPm);
        let val = JSON.stringify({title: title_text, guide: guide_text});

        console.log(json);
        $.ajax({
            url: "/Isolated_Management/base/create-prescription" + search,
            type: "post",
            data: {
                value: val,
                listPm: json
            },
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            success: function (data) {
                console.log("succ");
//            $("#tBody").html(data);
            },
            error: function (xhr) {
                console.log("err");
                //Do Something to handle error
            }
        });
    }

});

$("#btn_reset").click(() => {
    $('.search-list').addClass("hidden");

    listPm = [];
    $("#tBody").html("");
});

