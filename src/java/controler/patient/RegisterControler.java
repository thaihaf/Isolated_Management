/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.patient;

import dao.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
//import java.time.LocalDate;

/**
 *
 * @author Admin
 */
public class RegisterControler extends HttpServlet {

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
            out.println("<title>Servlet RegisterControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterControler at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        request.getRequestDispatcher("view/register.jsp").forward(request, response);
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
        PrintWriter out = response.getWriter();
        String Fullname = request.getParameter("Fullname");
        Boolean Gender = Boolean.parseBoolean(request.getParameter("Gender"));
        String Nation = request.getParameter("Nation");
        String Phone = request.getParameter("Phone");
        String Email = request.getParameter("Email");
        String Username = request.getParameter("Username");
        String Password = request.getParameter("Password");
        String confirm_password = request.getParameter("confirm_password");
        String Address = request.getParameter("Address");
        Date DateOfBirth = Date.valueOf(request.getParameter("DateOfBirth"));
        AccountDBContext adb = new AccountDBContext();
        String checkUsername = adb.checkUser(Username);
        if (Password.equals(confirm_password)) {
//            for(int i = 3; i <= 100; i++){
//                request.setAttribute("age", i);
//                request.getRequestDispatcher("view/login.jsp").forward(request, response);
//            }
            if (checkUsername == "This username is existed") {
                request.setAttribute("sign_exist_username", checkUsername);
                request.getRequestDispatcher("view/register.jsp").forward(request, response);
            } else {
                adb.Register(Username, Fullname, Gender, Phone, Address, Email, Nation, Password, DateOfBirth);
                request.setAttribute("register_success", checkUsername);
                request.getRequestDispatcher("view/register.jsp").forward(request, response);
            }
        }else{
            request.setAttribute("mess", "confirm password is not same password");
            request.getRequestDispatcher("view/register.jsp").forward(request, response);
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
