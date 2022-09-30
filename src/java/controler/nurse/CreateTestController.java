/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.nurse;

import dao.AccountDetailDBContext;
import dao.PatientDBContext;
import dao.TestResultDBContext;
import dao.TestTypeDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.Patient;
import entity.TestResult;
import entity.TestType;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class CreateTestController extends HttpServlet {

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
            out.println("<title>Servlet CreateTestController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateTestController at " + request.getContextPath() + "</h1>");
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
            ArrayList<TestType> types = new ArrayList<>();
            TestTypeDBContext tdb = new TestTypeDBContext();
            types = tdb.list();
            request.setAttribute("types", types);
            AccountDetailDBContext db = new AccountDetailDBContext();
            AccountDetail info = db.get(username);
            request.setAttribute("info", info);
            PatientDBContext pdb = new PatientDBContext();
            Patient patient = pdb.getInfo(username);
            request.setAttribute("patient", patient);
            request.getRequestDispatcher("../nurse/createTest.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        String id = request.getParameter("id");
        Boolean result = Boolean.getBoolean(request.getParameter("result"));
        int test = Integer.parseInt(request.getParameter("test"));
        String creator = request.getParameter("creator");
        Date date = new Date();
        Timestamp timestamp2 = new Timestamp(date.getTime());
        String personTest = acc.getUserName();
        Account a = new Account();
        a.setUserName(id);
        Account a1 = new Account();
        if (creator == "") {
            a1.setUserName(acc.getUserName());
        } else {
            a1.setUserName(creator);
        }
        AccountDetail ad = new AccountDetail();
        ad.setAccount(a);
        AccountDetail ad1 = new AccountDetail();
        ad1.setAccount(a1);
        TestType tt = new TestType();
        tt.setId(test);
        TestResult ts = new TestResult();
        ts.setPatientAccount(ad);
        ts.setResult(result);
        ts.setTestType(tt);
        ts.setTestTime(timestamp2);
        ts.setPersonTest(ad1);
        TestResultDBContext db = new TestResultDBContext();
        db.insert(ts);
        request.setAttribute("action", "Create Test");
        request.getRequestDispatcher("../view/createConfirm.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
