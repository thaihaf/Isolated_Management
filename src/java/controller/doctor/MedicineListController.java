/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

import dao.MedicineDBContext;
import dao.PrescriptionDBContext;
import entity.Account;
import entity.Medicine2;
import entity.Prescription;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hapro
 */
public class MedicineListController extends HttpServlet {

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
            out.println("<title>Servlet MedicineListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MedicineListController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) {
            request.getRequestDispatcher("../view/checkSession.jsp").forward(request, response);
        } else {
            MedicineDBContext mDB = new MedicineDBContext();
            ArrayList<Medicine2> m = mDB.getMedicines(null, null);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

//            if (m.size() > 0) {
//                int inStockNum = 0;
//                int outStockNum = 0;
//                int ExpirationNum = 0;
//                int ExpiredNum = 0;
//
//                for (Medicine2 medicine : m) {
//                    if (medicine.getStock() > 0) {
//                        inStockNum++;
//                    }
//
//                    try {
//                        Date a = dateFormat.parse(medicine.getExpirationDate());
//                        Date b = dateFormat.parse(new Date().toJSON().slice(0,10).replace(/-/g,'/'));
//
//                        if (a.after(Date.now())  {
//                            
//                        }
//                    } catch (Exception e) {
//                    }
//                }
//            }

            request.setAttribute("medicines", m);
            request.getRequestDispatcher("../doctor/listMedicine.jsp").forward(request, response);
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
