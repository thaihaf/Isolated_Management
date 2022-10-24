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
            listMedicine: true,
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
            listMedicine: true,
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
            listMedicine: true,
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
                createMT: true,
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
            searchMT: true,
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
            searchMT: true,
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