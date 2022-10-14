/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
let sidebar = $(".sidebar_item");

sidebar.click(function () {
    sidebar.removeClass("active");
    this.addClass("active");
});

function contactConfirm(){
    var result = confirm('Are you sure you want to make an emergency contact?');
    if(result){
        window.location.href = 'emergency';
    }
}