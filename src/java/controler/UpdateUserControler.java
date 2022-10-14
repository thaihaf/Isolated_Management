/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

import dao.AccountDetailDBContext;
import dao.MedicalStaffDBContext;
import dao.PatientDBContext;
import dao.RoleDBContext;
import dao.UserDBContext;
import entity.AccountDetail;
import entity.MedicalStaff;
import entity.Patient;
import entity.Role;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;

public class UpdateUserControler extends HttpServlet {

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
            out.println("<title>Servlet ProfileControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileControler at " + request.getContextPath() + "</h1>");
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
        String accID = request.getParameter("user");
//        String username = request.getParameter("username");
        String fullName = request.getParameter("fullName");
        Boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String email = request.getParameter("email");
//        String nation = request.getParameter("nation");
//        String nation = "hhhh";
//        Date dateofbirth = Date.valueOf(request.getParameter("dateofbirth"));
//        int role = Integer.parseInt(request.getParameter("role"));
        String dateofbirth = request.getParameter("dateofbirth");
        String role = request.getParameter("role");
        String leveleducation = request.getParameter("leveleducation");
        String hospital = request.getParameter("hospital");
        String blood_Type = request.getParameter("blood_Type");
        Boolean background_disease = Boolean.parseBoolean(request.getParameter("background_disease"));
        String note = request.getParameter("note");
        AccountDetailDBContext accDB = new AccountDetailDBContext();
        AccountDetail acc = accDB.get(accID);
        RoleDBContext roleDB = new RoleDBContext();
        ArrayList<Role> roles = roleDB.list();
        UserDBContext udb = new UserDBContext();
        request.setAttribute("account", acc);
        switch (acc.getAccount().getRole().getId()) {
            case 2: 
            case 3: {
                MedicalStaffDBContext medDB = new MedicalStaffDBContext();
                MedicalStaff med = medDB.getByAccountDetail(acc);
                request.setAttribute("account", acc);
                request.setAttribute("medical", med);
                udb.updateDoctorAndNurse(fullName, gender, phone, address, email, dateofbirth, accID, role, leveleducation, hospital);
                break;
            }
            case 4: {
                PatientDBContext patientDB = new PatientDBContext();
                Patient p = patientDB.get(acc);
                request.setAttribute("account", acc);
                request.setAttribute("patient", p);
                udb.updatePatient(fullName, gender, phone, address, email, dateofbirth, accID, note, blood_Type, background_disease);
            }
        }
        String mess = "Update successful";
        request.setAttribute(mess, "mess");
        request.setAttribute("roles", roles);
//        request.setAttribute("account", acc);
        request.getRequestDispatcher("updateuser.jsp").forward(request, response);
//        response.sendRedirect("updateuser");
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
