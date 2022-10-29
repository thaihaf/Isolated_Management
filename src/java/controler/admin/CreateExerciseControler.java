/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.admin;

import dao.ExerciseDBContext;
import dao.ExerciseSourceTypeDBContext;
import dao.PatientDBContext;
import entity.Exercise;
import entity.Exercise_Source_Type;
import entity.Patient;
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
public class CreateExerciseControler extends HttpServlet {

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
        ExerciseSourceTypeDBContext estDB = new ExerciseSourceTypeDBContext();
        ArrayList<Exercise_Source_Type> exSourceType = estDB.list();
        request.setAttribute("sourcetype", exSourceType);
        request.getRequestDispatcher("exercise_create.jsp").forward(request, response);
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
        String name = request.getParameter("name");
        String note = request.getParameter("note");
        int type = Integer.parseInt(request.getParameter("type"));
        String src = request.getParameter("src");
        String src_validate = (src.length() > 0 && src != null) ? src : null;
        Exercise e = new Exercise();
        e.setName(name);
        e.setNote(note);
        Exercise_Source_Type exType = new Exercise_Source_Type();
        exType.setId(type);
        e.setSourceType(exType);
        e.setSource(src_validate);
        ExerciseDBContext exerciseDB = new ExerciseDBContext();
        exerciseDB.insert(e);
        PrintWriter out = response.getWriter();
        out.print("Add successfully.");
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
