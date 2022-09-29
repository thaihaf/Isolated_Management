$('#inputSearch').on('input', function () {
    text = $('#inputSearch').val();
    console.log(text);

    $.ajax({
        url: "/Isolated_Management/base/prescription-list?username=thanhnt",
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

$('#filterDay').on('click', function () {
    var df = new Date($('#dateFrom').val());
    var dt = new Date($('#dateTo').val());

    $.ajax({
        url: "/Isolated_Management/base/prescription-list?username=thanhnt",
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

