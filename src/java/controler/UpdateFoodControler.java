/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

import dao.FoodDBContext;
import entity.Food;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class UpdateFoodControler extends HttpServlet {

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
            out.println("<title>Servlet UpdateFoodControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateFoodControler at " + request.getContextPath() + "</h1>");
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
//        int id = Integer.parseInt(request.getParameter("id"));
        response.setContentType("text/html;charset=UTF-8");
        String id = request.getParameter("id");
        String food = request.getParameter("food");
        String type = request.getParameter("type");
        String addedDate = request.getParameter("addedDate");
//        Date addedDate = Date.valueOf(request.getParameter("addedDate"));
        FoodDBContext fdb = new FoodDBContext();
        Food f = fdb.loadFoodById(id);
        request.setAttribute("food", f);
//        String mess = "";
        if (fdb.updateFood(food, type, addedDate, id) == true) {
            fdb.updateFood(food, type, addedDate, id);
            request.setAttribute("food", fdb.loadFoodById(id));
            request.setAttribute("mess", "Change food success");
            request.getRequestDispatcher("updatefood.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("updatefood.jsp").forward(request, response);
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

//        String mess = "";
//        if(fdb.updateFood(food, type, addedDate, id) == true){
//            fdb.updateFood(food, type, addedDate, id);
//            mess = "Change food success";
//            request.setAttribute("mess", mess);
        request.getRequestDispatcher("updatefood.jsp").forward(request, response);
//        }else{
//            mess = "Change food fail";
//            request.setAttribute("mess", mess);
//            request.getRequestDispatcher("admin/updatefood.jsp").forward(request, response);
//        }
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
