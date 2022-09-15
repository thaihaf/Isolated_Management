/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controler;

import dao.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import entity.Account;

/**
 *
 * @author Admin
 */
public class LoginControler extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginControler</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginControler at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("admin/adminHomeScreen.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String UserName = request.getParameter("UserName");
        String Password = request.getParameter("Password");
        String AccountType = request.getParameter("AccountType");
        AccountDBContext adb = new AccountDBContext();
        Account account = adb.getAccount(UserName, Password, AccountType);
        if(account == null){
            request.setAttribute("mess", "Wrong username or password");
            request.getRequestDispatcher("view/login.jsp").forward(request, response);
        }
        if(account != null){
            HttpSession session = request.getSession();
            session.setAttribute("account", account);
            if(account.getAccountType().equals("Doctor")){
                request.getRequestDispatcher("doctor/doctorHomeScreen.jsp").forward(request, response);
            }
            if(account.getAccountType().equals("Admin")){
                request.getRequestDispatcher("admin/adminHomeScreen.jsp").forward(request, response);
            }
            if(account.getAccountType().equals("Nurse")){
                request.getRequestDispatcher("nurse/nurseHomeScreen.jsp").forward(request, response);
            }
            if(account.getAccountType().equals("Patient")){
                request.getRequestDispatcher("patient/patientHomeScreen.jsp").forward(request, response);
            }
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
