/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.admin;

import dao.AccountDetailDBContext;
import dao.AreaDBContext;
import dao.AreaTypeDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.Area;
import entity.AreaType;
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
public class AddAreaControler extends HttpServlet {

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
            out.println("<title>Servlet AddAreaControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddAreaControler at " + request.getContextPath() + "</h1>");
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
        AreaTypeDBContext areaTypeDB = new AreaTypeDBContext();
        ArrayList<AreaType> areaTypes = areaTypeDB.list();
        AccountDetailDBContext accDB = new AccountDetailDBContext();
        ArrayList<AccountDetail> docAndNurseList = accDB.listDoctorAndNurse();
        request.setAttribute("areatype", areaTypes);
        request.setAttribute("medical", docAndNurseList);
        request.getRequestDispatcher("newarea.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        int areatype = Integer.parseInt(request.getParameter("areatype"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        String[] values = request.getParameterValues("index");
        ArrayList<Room> rooms = new ArrayList<>();
        Area a = new Area();
        a.setName(name);
        a.setAddress(address);
        AreaType at = new AreaType();
        at.setId(areatype);
        a.setAreaType(at);
        a.setAvailable(status);
        if (values != null) {
            for (String value : values) {
                Room r = new Room();
                r.setName(request.getParameter("room_name_" + value));
                r.setNumOfBed(Integer.parseInt(request.getParameter("room_beds_" + value)));
                AccountDetail doctor = new AccountDetail();
                Account docAcc = new Account();
                String docID = request.getParameter("doctor_" + value);
                docAcc.setUserName(docID.equals("-1") ? null : docID);
                AccountDetail nurse = new AccountDetail();
                Account nurAcc = new Account();
                String nurID = request.getParameter("nurse_" + value);
                nurAcc.setUserName(nurID.equals("-1") ? null : nurID);
                doctor.setAccount(docAcc);
                nurse.setAccount(nurAcc);
                r.setDoctorManage(doctor);
                r.setNurseManage(nurse);
                r.setAvailable(status);
                rooms.add(r);
            }
        }
        a.setRooms(rooms);
        AreaDBContext areaDB = new AreaDBContext();
        areaDB.insert(a);
        request.setAttribute("mess", "Add area successfully.");
        request.getRequestDispatcher("area_response.jsp").forward(request, response);
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
