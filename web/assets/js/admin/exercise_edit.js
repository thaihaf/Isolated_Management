/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


$.validator.addMethod('filesize', function (value, element, param) {
    return this.optional(element) || (element.files[0].size <= param * 1000000);
}, 'File size must be less than {0} MB');
$.validator.addMethod('ytb', function (value) {
    var p = /^(?:https?:\/\/)?(?:www\.)?(?:youtu\.be\/|youtube\.com\/(?:embed\/|v\/|watch\?v=|watch\?.+&v=))((\w|-){11})(?:\S+)?$/;
    return (value.match(p)) ? RegExp.$1 : false;
}, "Enter correct Youtube URL");

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

$().ready(function () {
    var srcType = $("#srcType").attr('value');
    var srcContent = $("#srcContent").attr('value');
    if (srcType === '1') {
        $("#source").append('None');
    } else if (srcType === '3') {
        $("#source").append("<img width=\"100%\" height=\"100%\" src=\"" + srcContent + "\"/>");
    } else if (srcType === '2') {
        getHtml(srcContent).then(function (result) {
            console.log(result);
            $("#source").append(result);
            $("iframe").attr('width', '100%');
            $("iframe").attr('height', '100%');
        });
    }
    $("#change_source").change(function () {
        if ($("#change_source").is(':checked')) {
            $("input[name=type]").attr('disabled', false);
            $("input[name=type]:first-child").prop('checked', true).trigger('change');
        } else {
            $("input[name=type]").prop({disabled: true, checked: false});
            $("#src").attr('disabled', true);
        }
    });

    $("#exerciseForm").validate({
        onfocusout: false,
        onkeyup: false,
        onclick: false,
        rules: {
            "name": {
                required: true,
                minlength: 3,
                maxlength: 20
            },
            "note": {
                required: true
            },
            "type": {
                required: true
            },
            "src": {
                required: true
            }
        },
        errorPlacement: function (error, element) {
            error.appendTo(element.parent("td").next("td"));
        }
    });
    $("#btnSubmit").click(function (event) {
        if ($("#exerciseForm").valid()) {
            var data = $('#exerciseForm').serializeArray();
            if ($("#change_source").is(':checked')) {
                if (data.find(obj => obj.name === 'type').value === '1') {
                    data.push({name: 'src', value: ''});
                }
            }
            if ($(".img-file").val()) {
                event.stopImmediatePropagation();
                var apiUrl = "https://api.imgur.com/3/image";
                var apiKey = "a4160a64c5f7306";
                var settings = {
                    async: false,
                    crossDomain: true,
                    processData: false,
                    contentType: false,
                    type: 'POST',
                    url: apiUrl,
                    headers: {
                        Authorization: 'Client-ID ' + apiKey,
                        Accept: 'application/json'
                    },
                    mimeType: 'multipart/form-data'
                };
                var formData = new FormData();
                formData.append('image', $('input[type=file]').get(0).files[0]);
                settings.data = formData;
                $.ajax(settings).done(function (response) {
                    var txt = JSON.parse(response);
                    //console.log(txt.data.link);
                    data.push({name: 'src', value: txt.data.link});
                });
            }
            console.log(data);
            event.preventDefault();
            $.ajax({
                url: 'exercise_edit',
                method: 'POST',
                data: data,
                success: function (response) {
                    alert(response);
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
    $("input[type=radio][name=type]").on('change', function () {
        $("#exerciseForm").validate().resetForm();
        var value = $(this).val();
        $("#src").val('');
        if (value === '1') {
            $("#src").prop('type', 'text');
            $("#src").prop('disabled', true);
            $("#src").rules('remove', 'ytb');
            $("#src").rules('remove', 'filesize');
        } else {
            $("#src").prop('disabled', false);
            if (value === '2') {
                $("#src").prop('type', 'url');
                $("#src").addClass("ytb-url").removeClass("img-file");
                $(".ytb-url").rules('add', {
                    ytb: true
                });
                $("#src").rules('remove', 'filesize');
            } else {
                $("#src").prop('type', 'file');
                $("#src").prop('accept', 'image/*');
                $("#src").addClass("img-file").removeClass("ytb-url");
                $(".img-file").rules('add', {
                    filesize: 5
                });
                $("#src").rules('remove', 'ytb');
            }
        }
    });
});