/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


function deleteConfirm(id) {
    var result = confirm('Are you sure you want to delete this notification?')
    if (result) {
        window.location.href = 'notif_delete?id=' + id;
    }
}