/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.admin;

import dao.AccountDBContext;
import dao.UserDBContext;
import entity.Account;
import entity.AccountDetail;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.Properties;
import utils.Mail;

/**
 *
 * @author Admin
 */
public class AddUserControler extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddUserControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddUserControler at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
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
//        Mail mail = new Mail();
//        Account acc = new Account();
//        AccountDetail ad = new AccountDetail();
//        mail.sendAccAndPassToEmail(ad, acc);
        request.getRequestDispatcher("admin/adduser.jsp").forward(request, response);
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        Boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
        String nation = request.getParameter("nation");
        Date dateofbirth = Date.valueOf(request.getParameter("dateofbirth"));
        int role = Integer.parseInt(request.getParameter("role"));
        String leveleducation = request.getParameter("leveleducation");
        String hospital = request.getParameter("hospital");
        AccountDBContext adb = new AccountDBContext();
        String checkUsername = adb.checkUser(username);
        UserDBContext udb = new UserDBContext();
        String mes = "";
        if (checkUsername == "This username is existed") {
            request.setAttribute("mess", checkUsername);
            request.getRequestDispatcher("admin/adduser.jsp").forward(request, response);
        } else {
            udb.addUser(username, fullName, gender, phone, address, role, email, nation, password, dateofbirth, leveleducation, hospital);
            mes = "Add more user successful";
            request.setAttribute("mes", mes);
            request.getRequestDispatcher("admin/adduser.jsp").forward(request, response);
        }
        Mail mail = new Mail();
        
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
