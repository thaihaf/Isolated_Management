/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

import dao.AccountDetailDBContext;
import entity.Account;
import entity.AccountDetail;
import jakarta.mail.MessagingException;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Mail;
import utils.RandomPassword;

/**
 *
 * @author Mountain
 */
public class ForgotPasswordControler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */

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
        //processRequest(request, response);
        request.getRequestDispatcher("view/forgotpass.jsp").forward(request, response);
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
        //processRequest(request, response);
        String user = request.getParameter("user");
        AccountDetailDBContext accDB = new AccountDetailDBContext();
        AccountDetail acc = accDB.getEmail(user);
        if (acc != null) {
            RandomPassword ran = new RandomPassword();
            String pass = ran.RandomPassword();
            Account a = new Account();
            a.setPassword(pass);
            acc.setAccount(a);
            accDB.UpdatePass(acc);
            try {
                Mail sendMail = new Mail();
                sendMail.SendMail(acc.getEmail(), pass);
                request.setAttribute("message", "New password has sent to your email.");
                request.getRequestDispatcher("view/forgotpass.jsp").forward(request, response);
            } catch (MessagingException ex) {
                Logger.getLogger(ForgotPasswordControler.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            request.setAttribute("message", "Email not found. Please check and try again.");
            request.getRequestDispatcher("view/forgotpass.jsp").forward(request, response);
        }
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
