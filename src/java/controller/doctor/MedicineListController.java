/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

import dao.MedicineDBContext;
import dao.PrescriptionDBContext;
import entity.Account;
import entity.Medicine2;
import entity.MedicineType;
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
            ArrayList<Medicine2> m = mDB.getMedicines(null, null, null, null, null);
            ArrayList<MedicineType> mt = mDB.getMedicineTypes();

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            int inStockNum = 0;
            int outStockNum = 0;
            int expirationNum = 0;
            int expiredNum = 0;

            if (m.size() > 0) {
                for (Medicine2 medicine : m) {
                    if (medicine.getStock() == 0) {
                        outStockNum++;
                    } else {
                        inStockNum++;
                    }

                    try {
                        String s = medicine.getExpirationDate();
                        Date a = dateFormat.parse(medicine.getExpirationDate());
                        Date b = new Date();

                        if (b.after(a)) {
                            expiredNum++;
                        } else {
                            expirationNum++;
                        }
                    } catch (Exception e) {
                    }
                }
            }

            request.setAttribute("total", m.size());
            request.setAttribute("inStockNum", inStockNum);
            request.setAttribute("outStockNum", outStockNum);
            request.setAttribute("expirationNum", expirationNum);
            request.setAttribute("expiredNum", expiredNum);

            request.setAttribute("medicines", m);
            request.setAttribute("medicineTypes", mt);
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
        request.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        MedicineDBContext mDB = new MedicineDBContext();

        String type = request.getParameter("tp");

        switch (type) {
            case "listMedicine":
                String typeSearch = request.getParameter("typeSearch");
                String searchName = request.getParameter("searchVal");
                String typeSort = request.getParameter("typeSort");

                ArrayList<Medicine2> medicines = mDB.getMedicines(null, null, typeSearch, searchName, typeSort);

                for (Medicine2 m : medicines) {
                    out.print("<tr>\n"
                            + "                                 <th scope=\"row\">" + m.getShipmentId() + "</th>\n"
                            + "                                 <td>" + m.getName() + "</td>\n"
                            + "                                 <td>\n"
                            + "                                     <div class=\"des_show\" >\n"
                            + "                                         <button type=\"button\" class=\"btn btn-success\">View</button>\n"
                            + "                                         <div class=\"des_hidden\">\n"
                            + "                                             " + m.getDescription() + "\n"
                            + "                                         </div>\n"
                            + "                                     </div>\n"
                            + "                                 </td>\n"
                            + "                                 <td>" + m.getStock() + "</td>\n"
                            + "                                 <td>" + m.getDateManafacture() + "</td>\n"
                            + "                                 <td>" + m.getExpirationDate() + "</td>\n"
                            + "                                 <td>" + m.getMedicineType().getType() + "</td>\n"
                            + "                                 <td>" + m.getMedicineType().getDosage() + "</td>\n"
                            + "                                 <td>\n"
                            + "                                     <a href=\"/Isolated_Management/base/update-medicine?id=" + m.getShipmentId() + "\" class=\"btn btn-success\">Update</a>\n"
                            + "                                 </td>\n"
                            + "                             </tr>");
                }
                break;
            case "createMT":
                String medicineType = request.getParameter("medicineType");
                String medicineDosage = request.getParameter("medicineDosage");

                boolean createSuccess = mDB.insertMedicineType(medicineType, medicineDosage);

                if (createSuccess) {
                    ArrayList<MedicineType> medicineTypes = mDB.getMedicineTypes();

                    for (MedicineType mt : medicineTypes) {
                        out.print("<tr>\n"
                                + "                                                     <th scope=\"row\">\n"
                                + "                                                         <input \n"
                                + "                                                             type=\"text\" \n"
                                + "                                                             class=\"form-control date2\" \n"
                                + "                                                             name=\"date2\" \n"
                                + "                                                             id=\"validationCustom05\" \n"
                                + "                                                             required\n"
                                + "                                                             value=\"" + mt.getType() + "\"\n"
                                + "                                                             >\n"
                                + "                                                     </th>\n"
                                + "                                                     <td> \n"
                                + "                                                         <input \n"
                                + "                                                             type=\"text\" \n"
                                + "                                                             class=\"form-control date2\" \n"
                                + "                                                             name=\"date2\" \n"
                                + "                                                             id=\"validationCustom05\" \n"
                                + "                                                             required\n"
                                + "                                                             value=\"" + mt.getDosage() + "\"\n"
                                + "                                                             >\n"
                                + "                                                     </td>\n"
                                + "                                                     <td><button type=\"button\" updateId=\"" + mt.getId() + "\" class=\"btn btn-success\">Update</button></td>\n"
                                + "                                                     <td><button type=\"button\"  deleteId=\"" + mt.getId() + "\" class=\"btn btn-secondary\">Delete</button></td>\n"
                                + "                                                 </tr>");
                    }
                } else {
                    out.print("failed");
                }
                break;

            case "searchMT":
                String searchByType = request.getParameter("searchByType");
                String searchByDosage = request.getParameter("searchByDosage");

                ArrayList<MedicineType> medicineTypes = mDB.searchMedicineTypes(searchByType, searchByDosage);

                for (MedicineType mt : medicineTypes) {
                    out.print("<tr>\n"
                            + "                                                     <th scope=\"row\">\n"
                            + "                                                         <input \n"
                            + "                                                             type=\"text\" \n"
                            + "                                                             class=\"form-control date2\" \n"
                            + "                                                             name=\"date2\" \n"
                            + "                                                             id=\"validationCustom05\" \n"
                            + "                                                             required\n"
                            + "                                                             value=\"" + mt.getType() + "\"\n"
                            + "                                                             >\n"
                            + "                                                     </th>\n"
                            + "                                                     <td> \n"
                            + "                                                         <input \n"
                            + "                                                             type=\"text\" \n"
                            + "                                                             class=\"form-control date2\" \n"
                            + "                                                             name=\"date2\" \n"
                            + "                                                             id=\"validationCustom05\" \n"
                            + "                                                             required\n"
                            + "                                                             value=\"" + mt.getDosage() + "\"\n"
                            + "                                                             >\n"
                            + "                                                     </td>\n"
                            + "                                                     <td><button type=\"button\" updateId=\"" + mt.getId() + "\" class=\"btn btn-success\">Update</button></td>\n"
                            + "                                                     <td><button type=\"button\"  deleteId=\"" + mt.getId() + "\" class=\"btn btn-secondary\">Delete</button></td>\n"
                            + "                                                 </tr>");
                }
                break;
            case "updateMT":
                int medicineIdVal = Integer.parseInt(request.getParameter("medicineId"));
                String medicineTypeVal = request.getParameter("medicineType");
                String medicineDosageVal = request.getParameter("medicineDosage");

                boolean updateSuccess = mDB.updateMedicineType(medicineIdVal, medicineTypeVal, medicineDosageVal);

                if (updateSuccess) {
                    out.print("true");
                } else {
                    out.print("failed");
                }
                break;

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
