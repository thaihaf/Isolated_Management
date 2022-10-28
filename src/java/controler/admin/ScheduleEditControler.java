/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.admin;

import dao.AccountDetailDBContext;
import dao.RoomDBContext;
import dao.ScheduleDBContext;
import dao.ScheduleTimeDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.Room;
import entity.Schedule;
import entity.Schedule_Time;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import utils.ParseDateTimeLocal;

/**
 *
 * @author Mountain
 */
public class ScheduleEditControler extends HttpServlet {

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
        ScheduleDBContext schedDB = new ScheduleDBContext();
        Schedule sched = schedDB.get(id);
        AccountDetailDBContext accDB = new AccountDetailDBContext();
        ArrayList<AccountDetail> acc = accDB.listDoctorAndNurse();
        RoomDBContext roomDB = new RoomDBContext();
        ArrayList<Room> rooms = roomDB.list();
        ScheduleTimeDBContext schedTimeDB = new ScheduleTimeDBContext();
        ArrayList<Schedule_Time> schedTime = schedTimeDB.list();
        request.setAttribute("account", acc);
        request.setAttribute("room", rooms);
        request.setAttribute("schedtime", schedTime);
        request.setAttribute("sched", sched);
        request.getRequestDispatcher("sched_edit.jsp").forward(request, response);
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
        String account = request.getParameter("account");
        int room = Integer.parseInt(request.getParameter("room"));
        String date = request.getParameter("date");
        int time = Integer.parseInt(request.getParameter("time"));
        String desc = request.getParameter("desc");
        ParseDateTimeLocal pdtl = new ParseDateTimeLocal();
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String message;
        ScheduleDBContext schedDB = new ScheduleDBContext();
        if (schedDB.get(id) != null) {
            Schedule s = new Schedule();
            s.setId(id);
            AccountDetail ad = new AccountDetail();
            Account a = new Account();
            a.setUserName(account);
            ad.setAccount(a);
            s.setAssignedUser(ad);
            Room r = new Room();
            r.setId(room);
            s.setRoom(r);
            s.setDate(pdtl.parseDate(date));
            Schedule_Time st = new Schedule_Time();
            st.setId(time);
            s.setTime(st);
            s.setDescription(desc);
            schedDB.update(s);
            message = "Update schedule successfully.";
        } else {
            message = "There is no schedule you are trying to edit right now, please refresh and try again.";
        }
        out.println(message);
        out.println("<div>Return to schedule list after <span id=\"timer\">3</span> seconds...</div>");
        out.println("<script>\n"
                + "            var count = 3;\n"
                + "            function redirect()\n"
                + "            {\n"
                + "                count--;\n"
                + "                document.getElementById('timer').innerHTML = count;\n"
                + "                if (count <= 0)\n"
                + "                    window.location.href = 'sched_list';\n"
                + "            }\n"
                + "            setInterval(redirect, 1000);\n"
                + "        </script>");
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
