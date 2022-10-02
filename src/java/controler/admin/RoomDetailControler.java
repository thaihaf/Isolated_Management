/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.admin;

import dao.AccountDetailDBContext;
import dao.AreaDBContext;
import dao.RoomDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.Area;
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
public class RoomDetailControler extends HttpServlet {

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
            out.println("<title>Servlet RoomDetailControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RoomDetailControler at " + request.getContextPath() + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("id"));
        RoomDBContext roomDB = new RoomDBContext();
        Room room = roomDB.get(id);
        AreaDBContext areaDB = new AreaDBContext();
        ArrayList<Area> areas = areaDB.list();
        AccountDetailDBContext accDB = new AccountDetailDBContext();
        ArrayList<AccountDetail> docAndNurseList = accDB.listDoctorAndNurse();
        request.setAttribute("room", room);
        request.setAttribute("areas", areas);
        request.setAttribute("medical", docAndNurseList);
        request.getRequestDispatcher("roomdetail.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int numOfBed = Integer.parseInt(request.getParameter("numOfBed"));
        int aid = Integer.parseInt(request.getParameter("area"));
        String doc = request.getParameter("doctor");
        String nur = request.getParameter("nurse");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Room r = new Room();
        r.setId(id);
        r.setName(name);
        r.setNumOfBed(numOfBed);
        Area a = new Area();
        a.setId(aid);
        r.setArea(a);
        AccountDetail docDetail = new AccountDetail();
        Account docAcc = new Account();
        docAcc.setUserName(doc);
        docDetail.setAccount(docAcc);
        r.setDoctorManage(docDetail);
        AccountDetail nurDetail = new AccountDetail();
        Account nurAcc = new Account();
        nurAcc.setUserName(nur);
        nurDetail.setAccount(nurAcc);
        r.setNurseManage(nurDetail);
        r.setAvailable(status);
        RoomDBContext roomDB = new RoomDBContext();
        roomDB.update(r);
            request.setAttribute("mess", "Update room successfully.");
//        } else {
//            request.setAttribute("mess", "Update room failed.");
//        }
        request.getRequestDispatcher("room_response.jsp").forward(request, response);
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
