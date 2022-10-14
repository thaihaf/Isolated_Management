/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

import dao.MedicineDBContext;
import entity.Account;
import entity.Medicine2;
import entity.MedicineType;
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
 * @author hapro
 */
public class UpdateMedicineController extends HttpServlet {

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
            out.println("<title>Servlet UpdateMedicineController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateMedicineController at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) {
            request.getRequestDispatcher("../view/checkSession.jsp").forward(request, response);
        } else {
            MedicineDBContext mDB = new MedicineDBContext();
            ArrayList<MedicineType> medicineTypes = mDB.getMedicineTypes();

            request.setAttribute("medicineTypes", medicineTypes);

            int id = Integer.parseInt(request.getParameter("id"));
            Medicine2 m = mDB.getMedicine(id);

            request.setAttribute("medicine", m);

            request.getRequestDispatcher("../doctor/updateMedicine.jsp").forward(request, response);

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
        request.setCharacterEncoding("UTF-8");

        int shipmentID = Integer.parseInt(request.getParameter("shipmentID"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int type = Integer.parseInt(request.getParameter("type"));
        String nameMedicine = request.getParameter("nameMedicine");
        String descriptions = request.getParameter("descriptions");
        String date1 = request.getParameter("date1");
        String date2 = request.getParameter("date2");

        MedicineType mt = new MedicineType();
        mt.setId(type);

        Medicine2 m = new Medicine2();
        m.setShipmentId(shipmentID);
        m.setName(nameMedicine);
        m.setDescription(descriptions);
        m.setStock(quantity);
        m.setMedicineType(mt);
        m.setDateManafacture(date1);
        m.setExpirationDate(date2);

        MedicineDBContext mDB = new MedicineDBContext();

        if (mDB.updateMedicine(m)) {
            response.sendRedirect("/Isolated_Management/base/medicine-list");
        } else {
            ArrayList<MedicineType> medicineTypes = mDB.getMedicineTypes();
            request.setAttribute("medicineTypes", medicineTypes);
            request.setAttribute("medicine", m);
            request.getRequestDispatcher("../doctor/createMedicine.jsp").forward(request, response);
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
