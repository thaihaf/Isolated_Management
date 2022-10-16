/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.admin;

import dao.AccountDBContext;
import dao.AccountDetailDBContext;
import dao.NotificationDBContext;
import dao.RoleDBContext;
import dao.RoomDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.Notification;
import entity.Role;
import entity.Room;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Mountain
 */
public class CreateNotificationControler extends HttpServlet {

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
        AccountDetailDBContext accDB = new AccountDetailDBContext();
        ArrayList<AccountDetail> accounts = accDB.list();
        RoomDBContext roomDB = new RoomDBContext();
        ArrayList<Room> rooms = roomDB.list();
        RoleDBContext roleDB = new RoleDBContext();
        ArrayList<Role> roles = roleDB.list();
        request.setAttribute("accounts", accounts);
        request.setAttribute("rooms", rooms);
        request.setAttribute("roles", roles);
        request.getRequestDispatcher("notif_create.jsp").forward(request, response);
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
        Account account = (Account) request.getSession().getAttribute("account");
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        int choice = Integer.parseInt(request.getParameter("choice"));
        NotificationDBContext notifDB = new NotificationDBContext();
        Notification n = new Notification();
        Account sender = new Account();
        sender.setUserName(account.getUserName());
        n.setSenderID(sender);
        n.setTitle(title);
        n.setContent(content);
        Date date = new Date();
        java.sql.Timestamp d = new Timestamp(date.getTime());
        n.setCreatedDate(d);
        n.setReadMark(false);
        switch (choice) {
            case 1: {
                Account receiver = new Account();
                receiver.setUserName(request.getParameter("account"));
                n.setReceiveID(receiver);
                notifDB.insert(n);
                break;
            }
            case 2: {
                AccountDBContext accDB = new AccountDBContext();
                ArrayList<Account> accounts = accDB.listInRoom(Integer.parseInt(request.getParameter("room")));
                if (!accounts.isEmpty()) {
                    notifDB.insert(n, accounts);
                }
                break;
            }
            case 3: {
                AccountDBContext accDB = new AccountDBContext();
                ArrayList<Account> accounts = accDB.listByRole(Integer.parseInt(request.getParameter("role")));
                if (!accounts.isEmpty()) {
                    notifDB.insert(n, accounts);
                }
                break;
            }
        }
        request.setAttribute("mess", "Sending notification successfully!");
        request.getRequestDispatcher("notif_status.jsp").forward(request, response);
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
