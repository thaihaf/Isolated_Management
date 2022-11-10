/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


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
    var srcType = $("#type").attr('data-id');
    var srcContent = $("#src").attr('data-id');
    console.log(srcType);
    if (srcType === '1') {
        $("#content").append('None');
    } else if (srcType === '3') {
        $("#content").append("<img width=\"50%\" height=\"50%\" src=\"" + srcContent + "\"/>");
    } else if (srcType === '2') {
        getHtml(srcContent).then(function (result) {
            $("#content").append(result);
            $("iframe").attr('width', '100%');
            $("iframe").attr('height', '100%');
        });
    }
});

