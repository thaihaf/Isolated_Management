/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.admin;

import dao.AccountDetailDBContext;
import dao.ScheduleDBContext;
import dao.ScheduleTimeDBContext;
import entity.AccountDetail;
import entity.Schedule;
import entity.Schedule_Time;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import utils.DaysOfWeek;

/**
 *
 * @author Mountain
 */
public class ScheduleListControler extends HttpServlet {

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
        AccountDetailDBContext accDB = new AccountDetailDBContext();
        ArrayList<AccountDetail> acc = accDB.listDoctorAndNurse();
        DaysOfWeek days = new DaysOfWeek();
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = 3; i >= -1; i--) {
            years.add(Year.now().minusYears(i).getValue());
        }
        Year currentYear = Year.now();
        int currentWeek = days.getCurrentWeekNumber(Locale.UK);
        request.setAttribute("account", acc);
        request.setAttribute("currentyear", currentYear.getValue());
        request.setAttribute("year", years);
        request.setAttribute("currentweek", currentWeek);
        request.setAttribute("date", days.getDayInWeekOfYear(currentYear.getValue(), Locale.UK));
        request.setAttribute("daterange", days.getDaysInWeek(currentYear.getValue(), currentWeek, Locale.UK));
        request.getRequestDispatcher("sched_list.jsp").forward(request, response);
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
        String account = request.getParameter("account");
        int year = Integer.parseInt(request.getParameter("year"));
        int week = Integer.parseInt(request.getParameter("week"));
        DaysOfWeek days = new DaysOfWeek();
        LocalDate firstDayOfWeek = days.getFirstDayOfWeek(year, week, Locale.UK);
        LocalDate lastDayOfWeek = days.getLastDayOfWeek(year, week, Locale.UK);
        List<String> listDaysInWeek = days.getDaysInWeek(year, week, Locale.UK);
        ScheduleDBContext schedDB = new ScheduleDBContext();
        ArrayList<Schedule> sched = schedDB.listByAccount(account, firstDayOfWeek, lastDayOfWeek);
        ScheduleTimeDBContext schedTimeDB = new ScheduleTimeDBContext();
        ArrayList<Schedule_Time> schedTime = schedTimeDB.list();
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("<table>\n"
                    + "        <tr>\n"
                    + "            <th rowspan=\"2\">Schedule</th>\n"
                    + "            <th>Mon</th>\n"
                    + "            <th>Tue</th>\n"
                    + "            <th>Wed</th>\n"
                    + "            <th>Thu</th>\n"
                    + "            <th>Fri</th>\n"
                    + "            <th>Sat</th>\n"
                    + "            <th>Sun</th>\n"
                    + "        </tr>\n"
                    + "        <tr>\n");
            for (String day : listDaysInWeek) {
                out.println("<th>" + day + "</th>");
            }
            out.println("        </tr>\n");
            for (Schedule_Time schedule_Time : schedTime) {
                out.println("<tr><th>" + schedule_Time.getName() + "</th>");
                for (String day : listDaysInWeek) {
                    LocalDate localdate = LocalDate.parse(day + "-" + year, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                    Schedule currentSched = null;
                    for (Schedule schedule : sched) {
                        if (schedule.getDate().equals(localdate) && schedule.getTime().getId() == schedule_Time.getId()) {
                            currentSched = schedule;
                        }
                    }
                    if (currentSched != null) {
                        out.println("<th>" + currentSched.getDescription() + " at " + currentSched.getRoom().getName() + "<br/>");
                        out.println("<a href=\"sched_edit?id=" + currentSched.getId() + "\">Edit</a> | <a href=\"#" + currentSched.getId() + "\" id=\"deleteBtn\" data-id=\"" + currentSched.getId() + "\">Delete</a>");
                        out.println("</th>");
                    } else {
                        out.println("<th> - </th>");
                    }
                }
                out.println("</tr>");
            }
            out.println("    </table>");
        }
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
