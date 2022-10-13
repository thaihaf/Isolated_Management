/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.nurse;

import dao.AccountDetailDBContext;
import dao.ReportDBContext;
import dao.RoomDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.Report;
import entity.Room;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

/**
 *
 * @author Mountain
 */
public class ReportListControler extends HttpServlet {

    private String patientToSearch;
    private Integer roomToSearch;

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
        Account account = (Account) request.getSession().getAttribute("account");
        int pageSize = Integer.parseInt(request.getServletContext().getInitParameter("pagesize"));
        String page = request.getParameter("page");
        if (page == null) {
            page = "1";
        }
        int pageIndex = Integer.parseInt(page);
        ReportDBContext reportDB = new ReportDBContext();
        int count = reportDB.countRecord(account.getUserName(), patientToSearch, roomToSearch);
        int totalPage = (count % pageSize == 0) ? count / pageSize : (count / pageSize) + 1;
        ArrayList<Report> reports = reportDB.listByNurseAccountWithSearchAndPaginated(account.getUserName(), patientToSearch, roomToSearch, pageIndex, pageSize);
        AccountDetailDBContext accDetailDB = new AccountDetailDBContext();
        ArrayList<AccountDetail> patients = accDetailDB.listPatientByNurseID(account.getUserName());
        RoomDBContext roomDB = new RoomDBContext();
        ArrayList<Room> rooms = roomDB.roomListByNurseID(account.getUserName());
        request.setAttribute("reports", reports);
        request.setAttribute("patients", patients);
        request.setAttribute("rooms", rooms);
        request.setAttribute("totalpage", totalPage);
        request.setAttribute("pageindex", pageIndex);
        request.getRequestDispatcher("../nurse/reportlist.jsp").forward(request, response);
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
        processRequest(request, response);
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
        String raw_patient = request.getParameter("patient");
        String raw_room = request.getParameter("room");
        patientToSearch = (raw_patient != null && raw_patient.length() > 0 && !raw_patient.equals("-1")) ? raw_patient : null;
        roomToSearch = (raw_room != null && raw_room.length() > 0 && !raw_room.equals("-1")) ? new Integer(raw_room) : null;
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
