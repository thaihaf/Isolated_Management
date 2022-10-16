/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$('#validationCustom01').on('input', function () {
    let text = $('#validationCustom01').val();


    if (text.length == 6) {
        let search = $(location).attr('search');
        let id = search.split("=")[1];

        if (id == text) {
            $(".validationCustom01").hide();
            $('#btn_submit').prop('disabled', false);
        } else {
            $.ajax({
                url: "/Isolated_Management/base/create-medicine",
                type: "post",
                data: {
                    idCheck: text
                },
                success: function (data) {
                    console.log(data);
                    if (data == "") {
                        $(".validationCustom01").hide();
                        $('#btn_submit').prop('disabled', false);
                    } else {
                        $(".validationCustom01").text(data);
                        $(".validationCustom01").show();
                        $('#btn_submit').prop('disabled', true);
                    }
                },
                error: function (err) {
                    console.log(err);
                }
            });
        }


    } else {
        $(".validationCustom01").show();
        $(".validationCustom01").text("Shipment Id must has 6 character")
    }

});