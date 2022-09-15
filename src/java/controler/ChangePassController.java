/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler;

import dao.AccountDBContext;
import entity.Account;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ChangePassController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePassController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePassController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) {
            request.getRequestDispatcher("view/checkSession.jsp").forward(request, response);
        }
        request.getRequestDispatcher("view/changepass.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account acc1 = new Account();
        AccountDBContext db = new AccountDBContext();
        String oldpass = request.getParameter("old");
        String newpass = request.getParameter("new");
        String cfpass = request.getParameter("cfnew");
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        acc1.setUserName(acc.getUserName());
        acc1.setPassword(cfpass);
        if (acc == null) {
            request.getRequestDispatcher("view/checkSession.jsp").forward(request, response);
        } else if (!oldpass.equals(newpass) && newpass.equals(cfpass) && oldpass.equals(acc.getPassword())) {
            db.update(acc1);
            request.getRequestDispatcher("view/confirm.jsp").forward(request, response);
        } else if (!oldpass.equals(acc.getPassword())) {
            request.setAttribute("mess", "password is wrong");
            request.getRequestDispatcher("view/changepass.jsp").forward(request, response);
        } else if (oldpass.equals(newpass)) {
            request.setAttribute("mess", "new pass can not be same as old pass");
            request.getRequestDispatcher("view/changepass.jsp").forward(request, response);
        } else if (!newpass.equals(cfpass)) {
            request.setAttribute("mess", "confirm new pass is wrong");
            request.getRequestDispatcher("view/changepass.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
