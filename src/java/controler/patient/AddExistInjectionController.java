/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.patient;

import dao.InjectionDBContext;
import dao.VaccineDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.InjectionReport;
import entity.Vaccine;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AddExistInjectionController extends HttpServlet {

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
            out.println("<title>Servlet AddExistInjection</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddExistInjection at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        VaccineDBContext vdb = new VaccineDBContext();
        ArrayList<Vaccine> vaccines = vdb.list();
        request.setAttribute("vaccine", vaccines);
        request.getRequestDispatcher("../patient/addExistInjection.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        InjectionReport ir = new InjectionReport();
        String id = acc.getUserName();
        AccountDetail ad = new AccountDetail();//account detail of patient
        Account a = new Account();
        a.setUserName(id);
        ad.setAccount(a);
        ir.setPatientAccount(ad);
        String date = request.getParameter("date");
        ir.setDate((date.length() > 0 || !date.equals("")) ? Date.valueOf(date) : null);
        Account a1 = new Account();
        String creator = request.getParameter("creator");
        a1.setUserName((date.length() > 0 || !date.equals("")) ? creator : null);
        AccountDetail ad1 = new AccountDetail();//account detail of medicalstaff
        ad1.setAccount(a1);
        ir.setPersonInject(ad1);
        String note = request.getParameter("note");
        ir.setNote((note.length() > 0 || !note.equals("")) ? note : null);
        int vac = Integer.parseInt(request.getParameter("vaccine"));
        Vaccine vaccine = new Vaccine();
        vaccine.setId(vac);
        ir.setVaccine(vaccine);
        InjectionDBContext jdb = new InjectionDBContext();
        jdb.insert(ir);
        request.setAttribute("action", "Declare Injection");
        request.getRequestDispatcher("../view/declareConfirm.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
