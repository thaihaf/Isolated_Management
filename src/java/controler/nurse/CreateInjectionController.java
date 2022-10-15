/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.nurse;

import dao.AccountDetailDBContext;
import dao.InjectionDBContext;
import dao.PatientDBContext;
import dao.VaccineDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.InjectionReport;
import entity.Patient;
import entity.Vaccine;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class CreateInjectionController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CreateInjection</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateInjection at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) {
            request.getRequestDispatcher("../view/checkSession.jsp").forward(request, response);
        } else {
            String username = request.getParameter("username");
            VaccineDBContext vdb = new VaccineDBContext();
            ArrayList<Vaccine> vaccines = vdb.list();
            request.setAttribute("vaccine", vaccines);
            AccountDetailDBContext db = new AccountDetailDBContext();
            AccountDetail info = db.get(username);
            request.setAttribute("info", info);
            PatientDBContext pdb = new PatientDBContext();
            Patient patient = pdb.getInfo(username);
            request.setAttribute("patient", patient);
            session.setAttribute("patient", patient);
            session.setAttribute("vaccines", vaccines);
            request.getRequestDispatcher("../nurse/createInjection.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        String id = request.getParameter("id");//id patient
        Account a = new Account();
        a.setUserName(id);
        AccountDetail ad = new AccountDetail();//account detail of patient
        ad.setAccount(a);
        InjectionReport ir = new InjectionReport();
        ir.setPatientAccount(ad);
        String creator = request.getParameter("creator");
        Account a1 = new Account();
        if (creator == "") {
            a1.setUserName(acc.getUserName());
        } else {
            a1.setUserName(creator);
        }
        AccountDetail ad1 = new AccountDetail();
        ad1.setAccount(a1);
        ir.setPersonInject(ad1);
        int vac = Integer.parseInt(request.getParameter("vaccine"));
        Vaccine vaccine = new Vaccine();
        vaccine.setId(vac);
        ir.setVaccine(vaccine);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        ir.setDate(sqlDate);
        InjectionDBContext jdb = new InjectionDBContext();
        jdb.insert(ir);
        request.setAttribute("action", "Create Injection");
        request.getRequestDispatcher("../view/createConfirm.jsp").forward(request, response);
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
