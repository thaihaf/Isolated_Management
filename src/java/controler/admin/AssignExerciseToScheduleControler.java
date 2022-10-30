/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.admin;

import dao.ExerciseDBContext;
import dao.ScheduleDBContext;
import dao.ScheduleExerciseDBContext;
import dao.ScheduleTimeDBContext;
import entity.Exercise;
import entity.Schedule;
import entity.Schedule_Exercise;
import entity.Schedule_Time;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Mountain
 */
public class AssignExerciseToScheduleControler extends HttpServlet {

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
            out.println("<title>Servlet AssignExerciseToScheduleControler</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AssignExerciseToScheduleControler at " + request.getContextPath() + "</h1>");
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
        ExerciseDBContext exerciseDB = new ExerciseDBContext();
        ArrayList<Exercise> exercises = exerciseDB.list();
        ScheduleDBContext schedDB = new ScheduleDBContext();
        ArrayList<Schedule> scheds = schedDB.list();
        request.setAttribute("exercise", exercises);
        request.setAttribute("schedule", scheds);
        request.getRequestDispatcher("exercise_assign.jsp").forward(request, response);
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
        int schedID = Integer.parseInt(request.getParameter("schedule"));
        String[] exercises = request.getParameterValues("ex[]");
        Schedule_Exercise se = new Schedule_Exercise();
        Schedule schedule = new Schedule();
        schedule.setId(schedID);
        se.setSchedule(schedule);
        for (String exercise : exercises) {
            int exID = Integer.parseInt(exercise);
            Exercise e = new Exercise();
            e.setId(exID);
            se.getExercise().add(e);
        }
        String message;
        ScheduleExerciseDBContext seDB = new ScheduleExerciseDBContext();
        if (!seDB.validate(se)) {
            seDB.insert(se);
            message = "Add successfully.";
        } else {
            message = "Error in system.";
        }
        PrintWriter out = response.getWriter();
        out.print(message);
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
