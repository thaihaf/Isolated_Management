/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

import dao.AccountDetailDBContext;
import dao.MedicalStaffDBContext;
import dao.PatientDBContext;
import dao.UserDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.MedicalStaff;
import entity.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class ProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProfileController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String fullName = request.getParameter("fullName");
        String gender = request.getParameter("gender");
        String email = request.getParameter("email");
        String dateOfBirth = request.getParameter("dateOfBirth");
        String phone = request.getParameter("phone");
        String nation = request.getParameter("nation");
        String address = request.getParameter("address");
        String levelOfEducation = request.getParameter("levelOfEducation");
        String hospital = request.getParameter("hospital");
        String bloodType = request.getParameter("bloodType");
        String note = request.getParameter("note");
        String backgroundDesease = request.getParameter("backgroundDesease");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) {
            request.getRequestDispatcher("../view/checkSession.jsp").forward(request, response);
        } else {
            AccountDetailDBContext db = new AccountDetailDBContext();
            AccountDetail info = db.get(acc.getUserName());
            request.setAttribute("info", info);
            String role = acc.getRole().getRole();
            UserDBContext udb = new UserDBContext();
            if (role.equalsIgnoreCase("Doctor") || role.equalsIgnoreCase("Nurse")) {
                MedicalStaffDBContext msdb = new MedicalStaffDBContext();
                MedicalStaff ms = msdb.getInfo(acc.getUserName());
                request.setAttribute("staff", ms);
                udb.updateDoctorAndNurse(fullName, gender, phone, address, email, dateOfBirth, nation, userName, role, levelOfEducation, hospital);
                request.setAttribute("staff", ms);
//request.getRequestDispatcher("view/profile.jsp").forward(request, response);
            } else if (role.equalsIgnoreCase("Patient")) {
                PatientDBContext pdb = new PatientDBContext();
                Patient patient = pdb.getInfo(acc.getUserName());
                request.setAttribute("patient", patient);
                udb.updatePatient(fullName, gender, phone, address, email, dateOfBirth, nation, userName, note, bloodType, backgroundDesease);
                request.setAttribute("patient", patient);
                //request.getRequestDispatcher("view/profile.jsp").forward(request, response);
            }
            request.getRequestDispatcher("../view/profile.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
