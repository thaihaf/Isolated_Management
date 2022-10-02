/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.admin;

import dao.AreaDBContext;
import dao.AreaTypeDBContext;
import dao.RoomDBContext;
import entity.Area;
import entity.AreaType;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author Mountain
 */
public class AreaDetailControler extends HttpServlet {

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
            out.println("<title>Servlet AreaDetailControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AreaDetailControler at " + request.getContextPath() + "</h1>");
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
        AreaDBContext areaDB = new AreaDBContext();
        AreaTypeDBContext areaTypeDB = new AreaTypeDBContext();
        request.setAttribute("area", areaDB.getWithRoomList(id));
        request.setAttribute("areatype", areaTypeDB.list());
        request.getRequestDispatcher("areadetail.jsp").forward(request, response);
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
        String address = request.getParameter("address");
        int areaTypeID = Integer.parseInt(request.getParameter("areatype"));
        boolean status_initial = Boolean.parseBoolean(request.getParameter("status_before"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        Area a = new Area();
        a.setId(id);
        a.setName(name);
        a.setAddress(address);
        AreaType at = new AreaType();
        at.setId(areaTypeID);
        a.setAreaType(at);
        a.setAvailable(status);
        AreaDBContext areaDB = new AreaDBContext();
        areaDB.update(a);
        String mess = "";
        if (status != status_initial) {
            RoomDBContext roomDB = new RoomDBContext();
            if (roomDB.updateStatusWithArea(a.getId(), a.isAvailable())) {
                mess += "Update status of room to match with status of area successfully.\n";
            } else {
                mess += "There is something wrong with updating status of room to match with area status.\n";
            }
        }
        mess += "Update area information successfully.";
        request.setAttribute("mess", mess);
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
