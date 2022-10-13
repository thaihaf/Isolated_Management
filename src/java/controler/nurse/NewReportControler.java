/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.nurse;

import dao.AccountDBContext;
import dao.AccountDetailDBContext;
import dao.ReportDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.Report;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Mountain
 */
public class NewReportControler extends HttpServlet {

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
            out.println("<title>Servlet NewReportControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewReportControler at " + request.getContextPath() + "</h1>");
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
        Account account = (Account) request.getSession().getAttribute("account");
        AccountDetailDBContext accDB = new AccountDetailDBContext();
        ArrayList<AccountDetail> patients = accDB.listPatientByNurseID(account.getUserName());
        request.setAttribute("patient", patients);
        request.getRequestDispatcher("../nurse/createReport.jsp").forward(request, response);
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
        String patient_id = request.getParameter("patient");
        String note = request.getParameter("note");
        Report r = new Report();
        AccountDetail acc = new AccountDetail();
        Account a = new Account();
        a.setUserName(patient_id);
        acc.setAccount(a);
        r.setPatient(acc);
        r.setNote((note != null && note.length() > 0) ? note : null);
        Date date = new Date();
        java.sql.Timestamp d = new Timestamp(date.getTime());
        r.setCreatedDate(d);
        ReportDBContext reportDB = new ReportDBContext();
        reportDB.insert(r);
        request.setAttribute("mess", "Add report successfully.");
        request.getRequestDispatcher("../nurse/report_status.jsp").forward(request, response);
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
