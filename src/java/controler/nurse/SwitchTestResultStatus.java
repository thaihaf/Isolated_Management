/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.nurse;

import dao.TestResultDBContext;
import entity.TestResult;
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
public class SwitchTestResultStatus extends HttpServlet {

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
            out.println("<title>Servlet SwitchTestResultStatus</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SwitchTestResultStatus at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        TestResultDBContext tr = new TestResultDBContext();
        boolean change = tr.changeStatus(status, id);
        if (change == true) {
            TestResultDBContext db = new TestResultDBContext();
            ArrayList<TestResult> results = db.getTestResultByID((String) session.getAttribute("patient"));
            request.setAttribute("results", results);
            request.setAttribute("patient", session.getAttribute("patient"));
            request.setAttribute("mess", "Switch status of test result successfully");
            request.getRequestDispatcher("../view/viewTest.jsp").forward(request, response);
        } else {
            request.setAttribute("mess", "Switch status of test result failed");
            request.getRequestDispatcher("../view/viewTest.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
