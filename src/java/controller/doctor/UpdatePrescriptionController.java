/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.doctor;

import com.google.gson.Gson;
import dao.AccountDetailDBContext;
import dao.MedicineDBContext;
import dao.PatientDBContext;
import dao.PrescriptionDBContext;
import dao.PrescriptionMedicineDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.Medicine2;
import entity.MedicineType;
import entity.Patient;
import entity.Prescription;
import entity.Prescription2;
import entity.PrescriptionMedicine2;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author hapro
 */
public class UpdatePrescriptionController extends HttpServlet {

    Gson gson = new Gson();

    public static class Data {

        private int id;
        private int quantity;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Data() {
        }
    }

    public static class TranferVal {

        private String title;
        private String guide;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getGuide() {
            return guide;
        }

        public void setGuide(String guide) {
            this.guide = guide;
        }

        public TranferVal() {
        }
    }

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
            out.println("<title>Servlet UpdatePrescriptionController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdatePrescriptionController at " + request.getContextPath() + "</h1>");
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
            String patientId = request.getParameter("username");
            int pId = Integer.parseInt(request.getParameter("pId"));

            AccountDetailDBContext detailDBContext = new AccountDetailDBContext();
            AccountDetail accountDetails = detailDBContext.get(patientId);

            PatientDBContext patientDBContext = new PatientDBContext();
            Patient patient = patientDBContext.get(accountDetails);

            PrescriptionDBContext pdbc = new PrescriptionDBContext();
            Prescription p = pdbc.getPrescriptionDetails(acc.getUserName(), patientId, pId);

            request.setAttribute("patient", patient);
            request.setAttribute("prescription", p);
            request.getRequestDispatcher("../doctor/updatePrescription.jsp").forward(request, response);

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

        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();

        Account acc = (Account) session.getAttribute("account");

        if (acc == null) {
            request.getRequestDispatcher("../view/checkSession.jsp").forward(request, response);
        } else {
            MedicineDBContext mDBContext = new MedicineDBContext();

            String searchByName = request.getParameter("searchByName");
            String searchByType = request.getParameter("searchByType");
            String listPmJson = request.getParameter("listPm");
            String value = request.getParameter("value");

            if (searchByName != null || searchByType != null) {
                ArrayList<Medicine2> medicines = mDBContext.getMedicines(searchByName, searchByType);

                for (Medicine2 m : medicines) {
                    out.print("<li class=\"list-group-item d-flex justify-content-between align-items-center\">\n"
                            + "                                             <span class=\"btn-add\" id=\"" + m.getShipmentId() + "\">\n"
                            + "                                                 <ion-icon name=\"add-outline\"></ion-icon>\n"
                            + "                                             </span>\n"
                            + "                                             <span class=\"item_name\">" + m.getName() + "</span>\n"
                            + "                                             <span class=\"badge badge-primary badge-pill\">" + m.getStock() + "</span>\n"
                            + "                                         </li>");
                }
            }

            if (value == null && listPmJson != null) {

                CreatePrescriptionController.Data[] listPm = gson.fromJson(listPmJson, CreatePrescriptionController.Data[].class);

                ArrayList<PrescriptionMedicine2> pres = new ArrayList<>();

                ArrayList<Medicine2> medicines = mDBContext.getMedicines(null, null);

                for (Medicine2 medicine : medicines) {
                    for (CreatePrescriptionController.Data item : listPm) {
                        if (item.getId() == medicine.getShipmentId()) {
                            PrescriptionMedicine2 pm = new PrescriptionMedicine2();

                            pm.setQuantity(item.getQuantity());
                            pm.setMedicine(medicine);

                            pres.add(pm);
                        }
                    }
                }

                for (PrescriptionMedicine2 p : pres) {
                    String output = "<tr class=\"medicine_item\" id=\"" + p.getMedicine().getShipmentId() + "-" + p.getQuantity() + "\">\n"
                            + "                                     <td>" + p.getMedicine().getName() + "</td>\n"
                            + "                                     <td>" + p.getQuantity() + "</td>\n"
                            + "                                     <td>" + p.getMedicine().getStock() + "</td>\n"
                            + "                                     <td>" + p.getMedicine().getMedicineType().getDosage() + "</td>\n"
                            + "                                     <td>" + p.getMedicine().getDateManafacture() + "</td>\n"
                            + "                                     <td>" + p.getMedicine().getExpirationDate() + "</td>\n"
                            + "                                     <td>" + p.getMedicine().getMedicineType().getType() + "</td>\n"
                            + "                                     <td>\n"
                            + "                                         <button class=\"btn btn-success\" id=\"" + p.getMedicine().getShipmentId() + "\">Delete</button>\n"
                            + "                                     </td>\n"
                            + "                                 </tr>";

                    out.print(output);
                }
            }

            if (value != null && listPmJson != null) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();

                CreatePrescriptionController.Data[] listPm = gson.fromJson(listPmJson, CreatePrescriptionController.Data[].class);
                CreatePrescriptionController.TranferVal tranferValue = gson.fromJson(value, CreatePrescriptionController.TranferVal.class);

                String titleVal = tranferValue.getTitle();
                String guideVal = tranferValue.getGuide();
                int pId = Integer.parseInt(request.getParameter("pId"));

                PrescriptionDBContext createPrescriptionDBContext = new PrescriptionDBContext();
                Prescription2 prescription2 = new Prescription2();

                prescription2.setId(pId);
                prescription2.setDoctorID(acc.getUserName());
                prescription2.setPatientID(request.getParameter("username"));
                prescription2.setTitle(titleVal);
                prescription2.setGuide(guideVal);
                prescription2.setPrescriptionDate(dateFormat.format(date));
                prescription2.setStatus(2);

                boolean isSuccess = createPrescriptionDBContext.updatePrescription(prescription2);

                System.out.println("Record updated with id = " + isSuccess);

                if (isSuccess) {
                    PrescriptionMedicineDBContext pmdbc = new PrescriptionMedicineDBContext();

                    int p = 0;

                    boolean deleteSuccess = pmdbc.deletePms(pId);

                    for (CreatePrescriptionController.Data item : listPm) {
                        PrescriptionMedicine2 pm = new PrescriptionMedicine2();

                        Medicine2 m = new Medicine2();
                        m.setShipmentId(item.getId());

                        pm.setPrescriptionId(pId);
                        pm.setQuantity(item.getQuantity());
                        pm.setMedicine(m);

                        p = pmdbc.insertPM(pm);
                    }

                    if (p != 0) {
                        response.sendRedirect("/prescription-list");
                    }
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
