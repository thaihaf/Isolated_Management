/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

import com.google.gson.Gson;
import dao.PrescriptionDBContext;
import entity.Account;
import entity.Medicine;
import entity.MedicineType;
import entity.Prescription;
import entity.PrescriptionMedicine;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hapro
 */
public class PrescriptionListController extends HttpServlet {

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
            PrescriptionDBContext pDB = new PrescriptionDBContext();
            ArrayList<Prescription> p = pDB.getListPrescriptionDetails(acc.getUserName(), request.getParameter("username"), null, null, null, null);

            request.setAttribute("prescriptions", p);
            request.getRequestDispatcher("../doctor/prescription_list.jsp").forward(request, response);
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
//        processRequest(request, response);

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            request.getRequestDispatcher("../view/checkSession.jsp").forward(request, response);
        } else {
            PrescriptionDBContext pDB = new PrescriptionDBContext();

            String username = request.getParameter("username");
            String searchVal = request.getParameter("searchVal");
            String df = request.getParameter("dateFrom");
            String dt = request.getParameter("dateTo");
            String sort = request.getParameter("sort");

//            Gson gson = new Gson();
//            Integer[] days = gson.fromJson(request.getParameter("days"), Integer[].class);
//            Object[] subjects = gson.fromJson(request.getParameter("ids"), Object[].class);
            ArrayList<Prescription> prescriptions = pDB.getListPrescriptionDetails(
                    acc.getUserName(),
                    username,
                    searchVal,
                    df,
                    dt, sort);

            for (Prescription p : prescriptions) {
                out.println("<tr>\n"
                        + "                                     <th scope=\"row\">" + p.getId() + "</th>\n"
                        + "                                     <td>" + p.getPrescriptionDate() + "</td>\n"
                        + "                                     <td>" + p.getTitle() + "</td><td>"
                );

                for (Medicine m : p.getMedicines()) {
                    int index = p.getMedicines().indexOf(m);
                    int loop = index + 1;
                    List<PrescriptionMedicine> listP = p.getPrescriptionMedicines();
                    List<MedicineType> listE = p.getMedicineTypes();

                    out.println("<div class=\"info_group\">\n"
                            + "                                                 <div class=\"info_main\">\n"
                            + "                                                     <ion-icon  name=\"help-circle\" class=\"info_icon\"></ion-icon>\n"
                            + "                                                     <div class=\"info_description\">\n"
                            + "                                                         <div>S??? l?? : " + m.getShipmentID() + "</div>\n"
                            + "                                                         <div>Ng??y s???n xu???t : " + m.getDateOfManufacture() + "</div>\n"
                            + "                                                         <div>C??ng d???ng : " + m.getDescription() + "</div>\n"
                            + "                                                     </div>\n"
                            + "                                                 </div>\n"
                            + "                                                 <div>\n"
                            + "                                                     <div>\n"
                            + "                                                         <span class=\"medicine_caption name\">Medicine " + loop + "</span> : \n"
                            + "                                                         " + m.getName() + " - \n"
                            + "                                                         " + listP.get(index).getQuantity() + " \n"
                            + "                                                         " + listE.get(index).getDosage() + "\n"
                            + "                                                     </div>\n"
                            + "                                                     <div><span class=\"medicine_caption\">Expiration date </span> : " + m.getExpirationDate() + "</div>\n"
                            + "                                                     <div><span class=\"medicine_caption\">Type </span> : " + listE.get(index).getType() + "</div>\n"
                            + "                                                 </div>\n"
                            + "                                             </div>");
                }

                out.println("</td><td>" + p.getGuide() + "</td>\n");

                switch (p.getStatus()) {
                    case 0:
                        out.println("<td>Ch??a ph??t</td>\n");
                        break;
                    case 1:
                        out.println("<td>???? ph??t</td>\n");
                        break;
                    case 2:
                        out.println("<td>???? edit, ch??a ph??t</td>\n");
                        break;
                }

                if(p.getStatus() != 1){
                    out.println("<td><a class=\"btn btn-info btn-lg\" href=\"/Isolated_Management/base/update-prescription?username="+username+"&pId="+p.getId()+"\">Update</a></td>\n");
                }
            }

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
