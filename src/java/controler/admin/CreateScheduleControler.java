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
public class CreateScheduleControler extends HttpServlet {

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
        RoomDBContext roomDB = new RoomDBContext();
        ArrayList<Room> rooms = roomDB.list();
        ScheduleTimeDBContext schedTimeDB = new ScheduleTimeDBContext();
        request.setAttribute("schedtime", schedTimeDB.list());
        request.setAttribute("account", acc);
        request.setAttribute("room", rooms);
        request.getRequestDispatcher("sched_create.jsp").forward(request, response);
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
        String person = request.getParameter("person");
        int room = Integer.parseInt(request.getParameter("room"));
        String dateInput = request.getParameter("date");
        int time = Integer.parseInt(request.getParameter("time"));
        String desc = request.getParameter("desc");
        ParseDateTimeLocal pdtl = new ParseDateTimeLocal();
        Schedule s = new Schedule();
        Room r = new Room();
        r.setId(room);
        AccountDetail a = new AccountDetail();
        Account acc = new Account();
        acc.setUserName(person);
        a.setAccount(acc);
        s.setRoom(r);
        s.setAssignedUser(a);
        s.setDescription(desc);
        s.setDate(pdtl.parseDate(dateInput));
        Schedule_Time st = new Schedule_Time();
        st.setId(time);
        s.setTime(st);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String message;
        ScheduleDBContext schedDB = new ScheduleDBContext();
        if (!schedDB.validate(s)) {
            schedDB.insert(s);
            message = "Add successfully.";
        } else {
            message = "There is existed schedule in database, please check and try again.";
        }
        out.println(message);
    }
}
