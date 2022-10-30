/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.admin;

import dao.ExerciseDBContext;
import dao.ExerciseSourceTypeDBContext;
import entity.Exercise;
import entity.Exercise_Source_Type;
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
public class ExerciseEditControler extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        ExerciseDBContext exerciseDB = new ExerciseDBContext();
        Exercise ex = exerciseDB.get(id);
        ExerciseSourceTypeDBContext estDB = new ExerciseSourceTypeDBContext();
        ArrayList<Exercise_Source_Type> est = estDB.list();
        request.setAttribute("exercise", ex);
        request.setAttribute("sourcetype", est);
        request.getRequestDispatcher("exercise_details.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String note = request.getParameter("note");
        int srcType_before = Integer.parseInt(request.getParameter("srcType"));
        String src_before = request.getParameter("srcContent");
        String srcType_raw = request.getParameter("type");
        String src_raw = request.getParameter("src");
        Integer srcType = (srcType_raw != null && srcType_raw.length() > 0) ? Integer.parseInt(srcType_raw) : srcType_before;
        String src = (src_raw != null && src_raw.length() > 0) ? src_raw : src_before;
        Exercise ex = new Exercise();
        ex.setId(id);
        ex.setName(name);
        ex.setNote(note);
        Exercise_Source_Type est = new Exercise_Source_Type();
        est.setId(srcType);
        ex.setSourceType(est);
        ex.setSource(src);
        ExerciseDBContext exerciseDB = new ExerciseDBContext();
        exerciseDB.update(ex);
        PrintWriter out = response.getWriter();
        out.print("Update successfully.");
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
