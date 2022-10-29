/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

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
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import utils.CountArray;
import utils.DaysOfWeek;

/**
 *
 * @author Mountain
 */
public class ScheduleControler extends HttpServlet {

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
        DaysOfWeek days = new DaysOfWeek();
        ArrayList<Integer> years = new ArrayList<>();
        for (int i = 3; i >= -1; i--) {
            years.add(Year.now().minusYears(i).getValue());
        }
        Year currentYear = Year.now();
        int currentWeek = days.getCurrentWeekNumber(Locale.UK);
        request.setAttribute("currentyear", currentYear.getValue());
        request.setAttribute("year", years);
        request.setAttribute("currentweek", currentWeek);
        request.setAttribute("date", days.getDayInWeekOfYear(currentYear.getValue(), Locale.UK));
        request.setAttribute("daterange", days.getDaysInWeek(currentYear.getValue(), currentWeek, Locale.UK));
        request.getRequestDispatcher("../view/schedule.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        Account account = (Account) session.getAttribute("account");
        int year = Integer.parseInt(request.getParameter("year"));
        int week = Integer.parseInt(request.getParameter("week"));
        DaysOfWeek days = new DaysOfWeek();
        LocalDate firstDayOfWeek = days.getFirstDayOfWeek(year, week, Locale.UK);
        LocalDate lastDayOfWeek = days.getLastDayOfWeek(year, week, Locale.UK);
        List<String> listDaysInWeek = days.getDaysInWeek(year, week, Locale.UK);
        ScheduleDBContext schedDB = new ScheduleDBContext();
        ArrayList<Schedule> scheds = new ArrayList<>();
        HashMap<Integer, Integer> count = new HashMap<>();
        switch (account.getRole().getId()) {
            case 2:
            case 3: {
                scheds = schedDB.listByAccount(account.getUserName(), firstDayOfWeek, lastDayOfWeek);
                break;
            }
            case 4: {
                RoomDBContext roomDB = new RoomDBContext();
                Room r = roomDB.getPatientRoom(account.getUserName());
                scheds = schedDB.listByRoom(r.getId(), firstDayOfWeek, lastDayOfWeek);
                break;
            }
        }
        ScheduleTimeDBContext schedTimeDB = new ScheduleTimeDBContext();
        ArrayList<Schedule_Time> schedTime = schedTimeDB.list();
        for (String day : listDaysInWeek) {
            LocalDate date = LocalDate.parse(day + "-" + year, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            for (Schedule_Time schedule_Time : schedTime) {
                int schedTimeCount = 0;
                for (Schedule schedule : scheds) {
                    if ((schedule_Time.getId() == schedule.getTime().getId()) && (date.equals(schedule.getDate()))) {
                        schedTimeCount++;
                    }
                }
                if (!count.containsKey(schedule_Time.getId())) {
                    count.put(schedule_Time.getId(), schedTimeCount > 0 ? schedTimeCount : 1);
                } else {
                    int val = count.get(schedule_Time.getId());
                    if (val < schedTimeCount) {
                        count.replace(schedule_Time.getId(), schedTimeCount);
                    }
                }
            }
        }
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            if ((int) val % 2 != 0) {
                for (Map.Entry<Integer, Integer> entry1 : count.entrySet()) {
                    Object key1 = entry1.getKey();
                    Object val1 = entry1.getValue();
                    count.replace((int) key1, (int) val1 * 2);
                }
            }
        }
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<table>");
        out.println("<tr>");
        out.println("<th colspan=\"2\">Schedule</th>");
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            Object key = entry.getKey();
            Object val = entry.getValue();
            out.println("<th colspan=\"" + val + "\">" + schedTime.get((int) key - 1).getName() + "</th>");
        }
        out.println("</tr>");
        for (String day : listDaysInWeek) {
            LocalDate date = LocalDate.parse(day + "-" + year, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            String name = date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.UK);
            out.println("<tr>");
            out.println("<th>" + name + "</th>");
            out.println("<th>" + day + "</th>");
            for (Schedule_Time schedule_Time : schedTime) {
                boolean checked = false;
                for (Schedule schedule : scheds) {
                    if (schedule.getDate().equals(date) && schedule.getTime().getId() == schedule_Time.getId()) {
                        checked = true;
                        int amount = CountArray.countOccurenceInSchedule(scheds, date, schedule_Time.getId());
                        switch (account.getRole().getId()) {
                            case 2:
                            case 3: {
                                out.println("<td>" + schedule.getDescription() + " at <a id=\"room\" room-id=\"" + schedule.getRoom().getId() + "\" href=\"#" + schedule.getId() + "\">" + schedule.getRoom().getName() + "</a></td>");
                                break;
                            }
                            case 4: {
                                out.println("<th colspan=\"" + count.get(schedule_Time.getId()) / amount + "\">" + schedule.getDescription() + " by " + schedule.getAssignedUser().getFullName() + "</th>");
                                break;
                            }
                        }
                    }
                }
                if (!checked) {
                    out.println("<th colspan=\"" + count.get(schedule_Time.getId()) + "\"> - </th>");
                }
            }
            out.println("</tr>");
        }
        out.println("</table>");
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
