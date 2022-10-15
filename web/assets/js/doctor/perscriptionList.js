$('#inputSearch').on('input', function () {
    let text = $('#inputSearch').val();
    let search = $(location).attr('search');

    $.ajax({
        url: "/Isolated_Management/base/prescription-list" + search,
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

$('#sortByDate').on('click', function () {
    let search = $(location).attr('search');

    $.ajax({
        url: "/Isolated_Management/base/prescription-list" + search,
        type: "post",
        data: {
            sort: "date"
        },
        success: function (data) {
            $("#tBody").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});
$('#sortByStatus').on('click', function () {
    let search = $(location).attr('search');

    $.ajax({
        url: "/Isolated_Management/base/prescription-list" + search,
        type: "post",
        data: {
            sort: "status"
        },
        success: function (data) {
            $("#tBody").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});
$('#sortByStatus').on('click', function () {
    let search = $(location).attr('search');

    $.ajax({
        url: "/Isolated_Management/base/prescription-list" + search,
        type: "post",
        data: {
            sort: "status"
        },
        success: function (data) {
            $("#tBody").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});

$('#filterDay').on('click', function () {
    let df = $('#dateFrom').val().split("T")[0];
    let dt = $('#dateTo').val().split("T")[0];
    let search = $(location).attr('search');

    $.ajax({
        url: "/Isolated_Management/base/prescription-list" + search,
        type: "post",
        data: {
            dateFrom: df,
            dateTo: dt
        },
        success: function (data) {
            $("#tBody").html(data);
        },
        error: function (xhr) {
            //Do Something to handle error
        }
    });
});

