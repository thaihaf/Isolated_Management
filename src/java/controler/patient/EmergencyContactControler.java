/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.patient;

import dao.AccountDBContext;
import dao.AccountDetailDBContext;
import dao.NotificationDBContext;
import dao.RoomDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.Notification;
import entity.Room;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Mountain
 */
public class EmergencyContactControler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account.getRole().getId() != 4) {
            request.setAttribute("mess", "This account cannot make an emergency contact.");
            request.getRequestDispatcher("../view/contact_status.jsp").forward(request, response);
        }
        RoomDBContext roomDB = new RoomDBContext();
        Room room = roomDB.getPatientRoom(account.getUserName());
        AccountDBContext accDB = new AccountDBContext();
        AccountDetailDBContext accDetailDB = new AccountDetailDBContext();
        AccountDetail patientAccDetail = accDetailDB.get(account.getUserName());
        Account notifReceive = accDB.getNotificationReceiver(room.getId());
        Notification notif = new Notification();
        notif.setSenderID(account);
        notif.setReceiveID(notifReceive);
        notif.setTitle("Emergency in room " + room.getName());
        notif.setContent("Patient " + patientAccDetail.getFullName() + " request an emergency in room " + room.getName() + ".");
        notif.setReadMark(false);
        Date date = new Date();
        java.sql.Timestamp d = new Timestamp(date.getTime());
        notif.setCreatedDate(d);
        NotificationDBContext notifDB = new NotificationDBContext();
        notifDB.insert(notif);
        request.setAttribute("mess", "Sending emergency successfully.");
        request.getRequestDispatcher("../view/contact_status.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
