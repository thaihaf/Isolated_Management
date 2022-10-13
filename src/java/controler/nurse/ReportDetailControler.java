/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.nurse;

import dao.ReportDBContext;
import entity.Account;
import entity.Report;
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
public class ReportDetailControler extends HttpServlet {

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
            out.println("<title>Servlet ReportDetailControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ReportDetailControler at " + request.getContextPath() + "</h1>");
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
        //processRequest(request, response);
        ReportDBContext reportDB = new ReportDBContext();
        String id = request.getParameter("id");
        Report r = reportDB.get(Integer.parseInt(id));
        if (r != null) {
            request.setAttribute("report", r);
            request.getRequestDispatcher("../nurse/reportDetail.jsp").forward(request, response);
        } else {
            request.setAttribute("mess", "ID not found, please try again.");
            request.getRequestDispatcher("../nurse/report_status.jsp").forward(request, response);
        }
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
        Account account = (Account) request.getSession().getAttribute("account");
        int id = Integer.parseInt(request.getParameter("id"));
        String note = request.getParameter("note");
        ReportDBContext reportDB = new ReportDBContext();
        boolean reportIDExist = reportDB.checkReportIDInNurseProperty(id, account.getUserName());
        if (reportIDExist) {
            Report r = new Report();
            r.setId(id);
            r.setNote((note != null && note.length() > 0) ? note : null);
            Date date = new Date();
            java.sql.Timestamp d = new Timestamp(date.getTime());
            r.setCreatedDate(d);
            reportDB.update(r);
            request.setAttribute("mess", "Update report successfully!");
        } else {
            request.setAttribute("mess", "ID not found for this account, please try again.");
        }
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
