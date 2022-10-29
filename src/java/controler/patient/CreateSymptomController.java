/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.patient;

import dao.AccountDetailDBContext;
import dao.InjectionDBContext;
import dao.SymptomDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.InjectionReport;
import entity.Symptom;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CreateSymptomController extends HttpServlet {

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
            out.println("<title>Servlet CreateSymptomController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateSymptomController at " + request.getContextPath() + "</h1>");
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
        ArrayList<InjectionReport> list2 = new ArrayList<>();
        String username = request.getParameter("username");
        AccountDetailDBContext adb = new AccountDetailDBContext();
        AccountDetail info = adb.get(username);
        request.setAttribute("info", info);
        InjectionDBContext jdb = new InjectionDBContext();
        ArrayList<InjectionReport> list1 = new ArrayList<>();
        list1 = jdb.getInjectionResultByID(username);
        for (InjectionReport ir : list1) {
            if (ir.isTaken() == false) {
                list2.add(ir);
            }
        }
        request.setAttribute("list2", list2);
        request.getRequestDispatcher("../patient/createSymptom.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        String s1 = request.getParameter("s1");
        String s2 = request.getParameter("s2");
        String s3 = request.getParameter("s3");
        String s4 = request.getParameter("s4");
        String s5 = request.getParameter("s5");
        String s6 = request.getParameter("s6");
        String s7 = request.getParameter("s7");
        String symptoms = new String();
        if (s1 != "") {
            symptoms += s1;
        }
        if (s2 != "") {
            symptoms = symptoms + "," + s2;
        }
        if (s3 != "") {
            symptoms = symptoms + "," + s3;
        }
        if (s4 != "") {
            symptoms = symptoms + "," + s4;
        }
        if (s5 != "") {
            symptoms = symptoms + "," + s5;
        }
        if (s6 != "") {
            symptoms = symptoms + "," + s6;
        }
        if (s7 != "") {
            symptoms = symptoms + "," + s7;
        }
        String username = acc.getUserName();
        Account account = new Account();
        account.setUserName(username);
        AccountDetail ad = new AccountDetail();
        ad.setAccount(account);
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        Symptom symptom = new Symptom();
        symptom.setUsername(ad);
        symptom.setNote(symptoms);
        symptom.setDate(sqlDate);
        SymptomDBContext db = new SymptomDBContext();
        db.insert(symptom);
        request.setAttribute("action", "Declare Symptoms");
        request.getRequestDispatcher("../view/declareConfirm.jsp").forward(request, response);
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
