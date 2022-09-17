/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

let body = $("body");
let menu = $(".menu");

$(".user_info").click(() => {
    if (menu.hasClass("hidden")) {
        menu.removeClass("hidden");
    } else {
        menu.addClass("hidden");
    }

});

$("#header_btnMenu").click(() => {
    if (body.hasClass("hideSidebar")) {
        body.removeClass("hideSidebar");
    } else {
        body.addClass("hideSidebar");
    }
});