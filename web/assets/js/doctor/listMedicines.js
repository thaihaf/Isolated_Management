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
            tp: "listMedicine",
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
            tp: "listMedicine",
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
            tp: "listMedicine",
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

$("#btn_submit").on('click', function () {
    let medicineType = $("#medicineType").val();
    let medicineDosage = $("#medicineDosage").val();

    if (medicineType.trim() === "") {
        alert("You must input Medicine Type")
    } else if (medicineDosage.trim() === "") {
        alert("You must input Medicine Dosage")
    } else {
        $.ajax({
            url: "/Isolated_Management/base/medicine-list",
            type: "post",
            data: {
                tp: "createMT",
                medicineType: medicineType,
                medicineDosage: medicineDosage
            },
            success: function (data) {
                $("#medicineTypeBody").html(data);
                $("#medicineType").val("");
                $("#medicineDosage").val("");
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });
    }

});
$('#searchMTByType').on('input', function () {
    let text = $('#searchMTByType').val();
    console.log(text);

    $.ajax({
        url: "/Isolated_Management/base/medicine-list",
        type: "post",
        data: {
            tp: "searchMT",
            searchByType: text
        },
        success: function (data) {
            $("#medicineTypeBody").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});
$('#searchMTByDosage').on('input', function () {
    let text = $('#searchMTByDosage').val();
    console.log(text);

    $.ajax({
        url: "/Isolated_Management/base/medicine-list",
        type: "post",
        data: {
            tp: "searchMT",
            searchByDosage: text
        },
        success: function (data) {
            $("#medicineTypeBody").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});
$(document).on('click', '.btn-update-mt', function () {
    let parent = $(this).parent().parent();

    let id = Number(this.id);
    let medicineType = parent.find('input[name="medicineType"]').val();
    let medicineDosage = parent.find('input[name="medicineDosage"]').val();

    if (medicineType.toString().trim() == "") {
        alert("Update : Medicine Type cant empty");
    } else if (medicineDosage.toString().trim() == "") {
        alert("Update : Medicine Dosage cant empty");
    } else {
        $.ajax({
            url: "/Isolated_Management/base/medicine-list",
            type: "post",
            data: {
                tp: "updateMT",
                medicineId: id,
                medicineType: medicineType,
                medicineDosage: medicineDosage
            },
            success: function (data) {
                if (data == "true") {
                    alert("Update successfully");
                } else {
                    alert("Update failed");
                }
            },
            error: function (xhr) {
                //Do Something to handle error
            }
        });

    }


});
