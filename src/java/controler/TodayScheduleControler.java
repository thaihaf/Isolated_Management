/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

import dao.PatientDBContext;
import dao.RoomDBContext;
import dao.ScheduleDBContext;
import dao.ScheduleTimeDBContext;
import entity.Account;
import entity.Room;
import entity.Schedule;
import entity.Schedule_Time;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import utils.DaysOfWeek;

/**
 *
 * @author Mountain
 */
public class TodayScheduleControler extends HttpServlet {

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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        LocalDate currentDate = LocalDate.now();
        ScheduleDBContext schedDB = new ScheduleDBContext();
        ArrayList<Schedule> scheds = new ArrayList<>();
        ScheduleTimeDBContext schedTimeDB = new ScheduleTimeDBContext();
        ArrayList<Schedule_Time> schedTime = schedTimeDB.list();
        HashMap<Integer, Integer> count = new HashMap<>();
        switch (account.getRole().getId()) {
            case 2:
            case 3: {
                scheds = schedDB.listByAccountToday(account.getUserName(), currentDate);
                break;
            }
            case 4: {
                RoomDBContext roomDB = new RoomDBContext();
                Room r = roomDB.getPatientRoom(account.getUserName());
                scheds = schedDB.listByRoomToday(r, currentDate);
                break;
            }
        }
        for (Schedule_Time schedule_Time : schedTime) {
            int schedTimeCount = 1;
            for (Schedule schedule : scheds) {
                if (schedule_Time.getId() == schedule.getTime().getId()) {
                    schedTimeCount++;
                }
                count.put(schedule_Time.getId(), schedTimeCount > 1 ? schedTimeCount - 1 : schedTimeCount);
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<h2>Today's Schedule</h2>");
            out.println("<table>\n"
                    + "        <tr>");
            if (!count.isEmpty()) {
                for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
                    Object key = entry.getKey();
                    Object val = entry.getValue();
                    out.println("<th colspan=\"" + val + "\">" + schedTime.get(((int) key) - 1).getName() + "</th>");
                }
            } else {
                for (Schedule_Time schedule_Time : schedTime) {
                    out.println("<th colspan=\"1\">" + schedule_Time.getName() + "</th>");
                }
            }
            out.println("</tr>");
            out.println("<tr>");
            switch (account.getRole().getId()) {
                case 2:
                case 3: {
                    for (Schedule schedule : scheds) {
                        out.println("<td>" + schedule.getDescription() + " at <a id=\"room\" room-id=\"" + schedule.getRoom().getId() + "\" href=\"#" + schedule.getId() + "\">" + schedule.getRoom().getName() + "</a></td>");
                    }
                    break;
                }
                case 4: {
                    for (Schedule schedule : scheds) {
                        out.println("<td>" + schedule.getDescription() + " by " + schedule.getAssignedUser().getFullName() + "</td>");
                    }
                    break;
                }
            }
            for (int i = 0; i < (schedTime.size() - scheds.size()); i++) {
                out.println("<td> - </td>");
            }
            out.println("</tr>");
            out.println("</table>");
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
