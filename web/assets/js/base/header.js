/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
$(document).ready();

$(".user_info").click(() => {
    let menu = $(".menu");
    if ($(".menu").hasClass("hidden")) {
        menu.removeClass("hidden");
    } else {
        menu.addClass("hidden");
    }
});
