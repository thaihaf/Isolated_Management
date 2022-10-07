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

//$('#inputSearch').on('input', function () {
//    let search = $(location).attr('search');
//
//    var array = [
//        {id: 1, q: 2},
//        {id: 1, q: 2},
//        {id: 1, q: 2},
//        {id: 1, q: 2}
//    ];
//
//    $.ajax({
//        url: "/Isolated_Management/base/prescription-list" + search,
//        type: "post",
//        data: {
//            action: "load",
//            subjects: JSON.stringify(["a", "b", "c"]),
//            days: JSON.stringify([1, 2, 3]),
//            ids: JSON.stringify(array)
//        },
//        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
//        dataType: "json",
//        success: function (result) {
//            console.log(result);
//        },
//        error: function (xhr) {
//            //Do Something to handle error
//        }
//    });
//});

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

