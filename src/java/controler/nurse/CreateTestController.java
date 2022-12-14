/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.nurse;

import dao.AccountDetailDBContext;
import dao.AreaDBContext;
import dao.MedicalStaffDBContext;
import dao.PatientDBContext;
import dao.RoomDBContext;
import dao.TestResultDBContext;
import dao.TestTypeDBContext;
import entity.Account;
import entity.AccountDetail;
import entity.Area;
import entity.Patient;
import entity.Room;
import entity.TestResult;
import entity.TestType;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ArrayList;
/**
 *
 * @author Admin
 */
public class CreateTestController extends HttpServlet {
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
            out.println("<title>Servlet CreateTestController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CreateTestController at " + request.getContextPath() + "</h1>");
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
            request.getRequestDispatcher("../view/checkSession.jsp").forward(request, response);
        } else {
            String username = request.getParameter("username");
            ArrayList<TestType> types = new ArrayList<>();
            TestTypeDBContext tdb = new TestTypeDBContext();
            types = tdb.list();
            request.setAttribute("types", types);
            AccountDetailDBContext db = new AccountDetailDBContext();
            AccountDetail info = db.get(username);
            request.setAttribute("info", info);
            PatientDBContext pdb = new PatientDBContext();
            Patient patient = pdb.getInfo(username);
            request.setAttribute("patient", patient);
            session.setAttribute("types", types);
            session.setAttribute("info", info);
            session.setAttribute("patient", patient);

            // thai ha
            TestResultDBContext testDB = new TestResultDBContext();
            AreaDBContext areaDB = new AreaDBContext();

            ArrayList<TestResult> listTestResult = testDB.getTestResultByID(username);
            request.setAttribute("isFirstTest", listTestResult.isEmpty());

            ArrayList<Area> listAreaActiveWithRoom = areaDB.listAreaActiveWithRoom(null, null);
            request.setAttribute("listArea", listAreaActiveWithRoom);

            request.getRequestDispatcher("../nurse/createTest.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        PrintWriter out = response.getWriter();
        Account acc = (Account) session.getAttribute("account");
        AreaDBContext areaDB = new AreaDBContext();

        String addToRoom = request.getParameter("addToRoom");

        if (addToRoom != null) {
            String areaId = request.getParameter("areaId");
            String roomName = request.getParameter("roomName");

            ArrayList<Area> listAreaActiveWithRoom = areaDB.listAreaActiveWithRoom(areaId, roomName);
            ArrayList<Room> rooms = listAreaActiveWithRoom.get(0).getRooms();

            if (roomName == null) {

                String roomSelect = "<option selected value=\"\" style=\"font-size: 15px\">---- Choose Room ----</option>\n";

                String roomList = "";

                for (Room r : rooms) {
                    roomSelect += "<option value=\"" + r.getId() + "\" style=\"font-size: 15px\">" + r.getName() + "</option>";

                    roomList += "<tr>\n"
                            + "                                                                                 <td>" + r.getName() + "</td>\n"
                            + "                                                                                 <td>" + r.getNumOfUse() + "</td>\n"
                            + "                                                                                 <td>" + r.getNumOfBed() + "</td>\n"
                            + "                                                                                 <td>" + r.getDoctorManage().getAccount().getUserName() + "</td>\n"
                            + "                                                                                 <td>" + r.getNurseManage().getAccount().getUserName() + "</td>\n";

                    switch (r.getLevel()) {

                        case -1:
                            roomList += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">??ang ch???</button>\n"
                                    + "                                                                                             </div>";
                            break;

                        case 0:
                            roomList += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">Kh??ng tri???u ch???ng</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-secondary border-10\">\n"
                                    + "                                                                                                     <p>Ch??a c?? tri???u ch???ng</p>\n"
                                    + "                                                                                                     <p>B??n c???nh ????, ng?????i b???nh t???nh t??o, t??? ph???c v??? ???????c</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 1:
                            roomList += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">M???c ????? nh???</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-secondary border-10\">\n"
                                    + "                                                                                                     <p>F0 c?? c??c tri???u ch???ng l??m s??ng kh??ng ?????c hi???u nh?? s???t, ho khan, ??au h???ng, ngh???t m??i, m???t m???i, ??au ?????u, ??au m???i c??, m???t v??? gi??c, kh???u gi??c, ti??u ch???y??? Nh???p th??? < 20 l???n/ph??t, SpO2 > 96% khi th??? kh?? tr???i. </p>\n"
                                    + "                                                                                                     <p>B??n c???nh ????, ng?????i b???nh t???nh t??o, t??? ph???c v??? ???????c; X-quang ph???i b??nh th?????ng ho???c c?? nh??ng t???n th????ng ??t.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 2:
                            roomList += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-info\">M???c ????? trung b??nh</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-info\">\n"
                                    + "                                                                                                     <p>????nh gi?? to??n tr???ng, ng?????i b???nh c?? c??c tri???u ch???ng l??m s??ng kh??ng ?????c hi???u nh?? m???c ????? nh???. </p>\n"
                                    + "                                                                                                     <p>F0 c?? th??? kh?? th??? khi g???ng s???c (??i l???i trong nh??, l??n c???u thang).</p>\n"
                                    + "                                                                                                     <p>M???ch c???a ng?????i b???nh c?? th??? nhanh ho???c ch???m, da kh??, nh???p tim nhanh, huy???t ??p b??nh th?????ng, ?? th???c t???nh t??o. </p>\n"
                                    + "                                                                                                     <p>Ngo??i ra, ch???p X-quang ng???c v?? c???t l???p vi t??nh ng???c ph??t hi???n c?? t???n th????ng, t???n th????ng d?????i 50%. </p>\n"
                                    + "                                                                                                     <p>Si??u ??m th???y h??nh ???nh s??ng B, kh?? m??u ?????ng m???ch: PaO2/FiO2 > 300.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 3:
                            roomList += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-warning\">M???c ????? n???ng</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-warning border-10\">\n"
                                    + "                                                                                                     <p>F0 ???????c ph??n lo???i thu???c nh??m n???ng n???u h?? h???p c?? d???u hi???u vi??m ph???i k??m theo b???t k??? m???t trong c??c d???u hi???u sau: nh???p th??? > 25 l???n/ph??t; kh?? th??? n???ng, co k??o c?? h?? h???p ph???; SpO2 < 94% khi th??? kh?? ph??ng.</p>\n"
                                    + "                                                                                                     <p>V??? tu???n ho??n, nh???p tim ng?????i b???nh c?? th??? nhanh ho???c ch???m, HA b??nh th?????ng hay t??ng. </p>\n"
                                    + "                                                                                                     <p>V??? th???n kinh, ng?????i b???nh c?? th??? b???t r???t ho???c ?????, m???t. Ch???p X-quang ng???c v?? c???t l???p vi t??nh ng???c: c?? t???n th????ng, t???n th????ng tr??n 50%. </p>\n"
                                    + "                                                                                                     <p>Si??u ??m th???y h??nh ???nh s??ng B nhi???u, kh?? m??u ?????ng m???ch: PaO2/FiO2 kho???ng 200-300.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 4:
                            roomList += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-danger\">M???c ????? nguy k???ch</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-danger border-10\">\n"
                                    + "                                                                                                     <p>F0 thu???c nh??m nguy k???ch c?? bi???u hi???n th??? nhanh > 30 l???n/ph??t ho???c < 10 l???n/ph??t, c?? d???u hi???u suy h?? h???p n???ng, th??? g???ng s???c nhi???u, th??? b???t th?????ng ho???c c???n h??? tr??? h?? h???p b???ng th??? oxy d??ng cao (HFNC), th??? m??y. </p>\n"
                                    + "                                                                                                     <p>?? th???c ng?????i b???nh gi???m ho???c h??n m??.</p>\n"
                                    + "                                                                                                     <p>Nh???p tim b???nh nh??n c?? th??? nhanh ho???c ch???m, huy???t ??p t???t, ti???u ??t ho???c v?? ni???u. </p>\n"
                                    + "                                                                                                     <p>K???t qu??? X-quang ng???c v?? c???t l???p vi t??nh ng???c ph??t hi???n c?? t???n th????ng, t???n th????ng tr??n 50%.</p> \n"
                                    + "                                                                                                     <p>Si??u ??m h??nh ???nh s??ng B nhi???u, kh?? m??u ?????ng m???ch: PaO2/FiO2 < 200, toan h?? h???p, lactat m??u > 2 mmol/L.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                    }

                    roomList += "</td></tr>";
                }

                out.print(roomSelect + "|split|" + roomList);

            } else {
                String output = "";

                for (Room r : rooms) {
                    output += "<tr>\n"
                            + "                                                                                 <td>" + r.getName() + "</td>\n"
                            + "                                                                                 <td>" + r.getNumOfUse() + "</td>\n"
                            + "                                                                                 <td>" + r.getNumOfBed() + "</td>\n"
                            + "                                                                                 <td>" + r.getDoctorManage().getAccount().getUserName() + "</td>\n"
                            + "                                                                                 <td>" + r.getNurseManage().getAccount().getUserName() + "</td>\n";

                    switch (r.getLevel()) {

                        case -1:
                            output += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">??ang ch???</button>\n"
                                    + "                                                                                             </div>";
                            break;

                        case 0:
                            output += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">Kh??ng tri???u ch???ng</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-secondary border-10\">\n"
                                    + "                                                                                                     <p>Ch??a c?? tri???u ch???ng</p>\n"
                                    + "                                                                                                     <p>B??n c???nh ????, ng?????i b???nh t???nh t??o, t??? ph???c v??? ???????c</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 1:
                            output += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">M???c ????? nh???</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-secondary border-10\">\n"
                                    + "                                                                                                     <p>F0 c?? c??c tri???u ch???ng l??m s??ng kh??ng ?????c hi???u nh?? s???t, ho khan, ??au h???ng, ngh???t m??i, m???t m???i, ??au ?????u, ??au m???i c??, m???t v??? gi??c, kh???u gi??c, ti??u ch???y??? Nh???p th??? < 20 l???n/ph??t, SpO2 > 96% khi th??? kh?? tr???i. </p>\n"
                                    + "                                                                                                     <p>B??n c???nh ????, ng?????i b???nh t???nh t??o, t??? ph???c v??? ???????c; X-quang ph???i b??nh th?????ng ho???c c?? nh??ng t???n th????ng ??t.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 2:
                            output += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-info\">M???c ????? trung b??nh</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-info\">\n"
                                    + "                                                                                                     <p>????nh gi?? to??n tr???ng, ng?????i b???nh c?? c??c tri???u ch???ng l??m s??ng kh??ng ?????c hi???u nh?? m???c ????? nh???. </p>\n"
                                    + "                                                                                                     <p>F0 c?? th??? kh?? th??? khi g???ng s???c (??i l???i trong nh??, l??n c???u thang).</p>\n"
                                    + "                                                                                                     <p>M???ch c???a ng?????i b???nh c?? th??? nhanh ho???c ch???m, da kh??, nh???p tim nhanh, huy???t ??p b??nh th?????ng, ?? th???c t???nh t??o. </p>\n"
                                    + "                                                                                                     <p>Ngo??i ra, ch???p X-quang ng???c v?? c???t l???p vi t??nh ng???c ph??t hi???n c?? t???n th????ng, t???n th????ng d?????i 50%. </p>\n"
                                    + "                                                                                                     <p>Si??u ??m th???y h??nh ???nh s??ng B, kh?? m??u ?????ng m???ch: PaO2/FiO2 > 300.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 3:
                            output += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-warning\">M???c ????? n???ng</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-warning border-10\">\n"
                                    + "                                                                                                     <p>F0 ???????c ph??n lo???i thu???c nh??m n???ng n???u h?? h???p c?? d???u hi???u vi??m ph???i k??m theo b???t k??? m???t trong c??c d???u hi???u sau: nh???p th??? > 25 l???n/ph??t; kh?? th??? n???ng, co k??o c?? h?? h???p ph???; SpO2 < 94% khi th??? kh?? ph??ng.</p>\n"
                                    + "                                                                                                     <p>V??? tu???n ho??n, nh???p tim ng?????i b???nh c?? th??? nhanh ho???c ch???m, HA b??nh th?????ng hay t??ng. </p>\n"
                                    + "                                                                                                     <p>V??? th???n kinh, ng?????i b???nh c?? th??? b???t r???t ho???c ?????, m???t. Ch???p X-quang ng???c v?? c???t l???p vi t??nh ng???c: c?? t???n th????ng, t???n th????ng tr??n 50%. </p>\n"
                                    + "                                                                                                     <p>Si??u ??m th???y h??nh ???nh s??ng B nhi???u, kh?? m??u ?????ng m???ch: PaO2/FiO2 kho???ng 200-300.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 4:
                            output += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-danger\">M???c ????? nguy k???ch</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-danger border-10\">\n"
                                    + "                                                                                                     <p>F0 thu???c nh??m nguy k???ch c?? bi???u hi???n th??? nhanh > 30 l???n/ph??t ho???c < 10 l???n/ph??t, c?? d???u hi???u suy h?? h???p n???ng, th??? g???ng s???c nhi???u, th??? b???t th?????ng ho???c c???n h??? tr??? h?? h???p b???ng th??? oxy d??ng cao (HFNC), th??? m??y. </p>\n"
                                    + "                                                                                                     <p>?? th???c ng?????i b???nh gi???m ho???c h??n m??.</p>\n"
                                    + "                                                                                                     <p>Nh???p tim b???nh nh??n c?? th??? nhanh ho???c ch???m, huy???t ??p t???t, ti???u ??t ho???c v?? ni???u. </p>\n"
                                    + "                                                                                                     <p>K???t qu??? X-quang ng???c v?? c???t l???p vi t??nh ng???c ph??t hi???n c?? t???n th????ng, t???n th????ng tr??n 50%.</p> \n"
                                    + "                                                                                                     <p>Si??u ??m h??nh ???nh s??ng B nhi???u, kh?? m??u ?????ng m???ch: PaO2/FiO2 < 200, toan h?? h???p, lactat m??u > 2 mmol/L.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                    }

                    output += "</td></tr>";
                }

                out.print(output);
            }

        } else {
            String id = request.getParameter("id");
            Boolean result = Boolean.getBoolean(request.getParameter("result"));
            int test = Integer.parseInt(request.getParameter("test"));
            String creator = request.getParameter("creator");
            Date date = new Date();
            Timestamp timestamp2 = new Timestamp(date.getTime());
            String personTest = acc.getUserName();
            Account a = new Account();
            a.setUserName(id);
            Account a1 = new Account();
            if (creator == "") {
                a1.setUserName(acc.getUserName());
                AccountDetail ad = new AccountDetail();
                ad.setAccount(a);
                AccountDetail ad1 = new AccountDetail();
                ad1.setAccount(a1);
                TestType tt = new TestType();
                tt.setId(test);
                TestResult ts = new TestResult();
                ts.setPatientAccount(ad);
                ts.setResult(result);
                ts.setTestType(tt);
                ts.setTestTime(timestamp2);
                ts.setPersonTest(ad1);
                TestResultDBContext db = new TestResultDBContext();
                db.insert(ts);

                // thai ha
                String roomSelect = request.getParameter("roomSelect");

                if (roomSelect != null || !roomSelect.trim().isEmpty()) {
                    PatientDBContext patientDB = new PatientDBContext();
                    RoomDBContext roomDB = new RoomDBContext();

                    String username = request.getParameter("id");

                    int currentRoomId = patientDB.getInfo(username).getRoom().getId();
                    int newRoomId = Integer.parseInt(roomSelect);

                    boolean changeRoomSuccess = patientDB.changePatientRoom(newRoomId, username);

                    if (changeRoomSuccess) {
                        roomDB.updateNumOfUseById(currentRoomId, roomDB.getNumOfUseByRoomId(currentRoomId) - 1);
                        roomDB.updateNumOfUseById(newRoomId, roomDB.getNumOfUseByRoomId(newRoomId) + 1);
                    }

                }

                request.setAttribute("action", "Create Test");
                request.getRequestDispatcher("../view/createConfirm.jsp").forward(request, response);
            } else {
                MedicalStaffDBContext msdb = new MedicalStaffDBContext();
                boolean check = msdb.checkMedicalStaff(creator);
                if (check == true) {
                    a1.setUserName(creator);
                    AccountDetail ad = new AccountDetail();
                    ad.setAccount(a);
                    AccountDetail ad1 = new AccountDetail();
                    ad1.setAccount(a1);
                    TestType tt = new TestType();
                    tt.setId(test);
                    TestResult ts = new TestResult();
                    ts.setPatientAccount(ad);
                    ts.setResult(result);
                    ts.setTestType(tt);
                    ts.setTestTime(timestamp2);
                    ts.setPersonTest(ad1);
                    TestResultDBContext db = new TestResultDBContext();
                    db.insert(ts);


                    // thai ha
                    String roomSelect = request.getParameter("roomSelect");

                    if (roomSelect != null || !roomSelect.trim().isEmpty()) {
                        PatientDBContext patientDB = new PatientDBContext();
                        RoomDBContext roomDB = new RoomDBContext();

                        String username = request.getParameter("id");

                        int currentRoomId = patientDB.getInfo(username).getRoom().getId();
                        int newRoomId = Integer.parseInt(roomSelect);

                        boolean changeRoomSuccess = patientDB.changePatientRoom(newRoomId, username);

                        if (changeRoomSuccess) {
                            roomDB.updateNumOfUseById(currentRoomId, roomDB.getNumOfUseByRoomId(currentRoomId) - 1);
                            roomDB.updateNumOfUseById(newRoomId, roomDB.getNumOfUseByRoomId(newRoomId) + 1);
                        }

                    }


                    request.setAttribute("action", "Create Test");
                    request.getRequestDispatcher("../view/createConfirm.jsp").forward(request, response);
                } else if (check == false) {
                    Patient patient = (Patient) session.getAttribute("patient");
                    AccountDetail info = (AccountDetail) session.getAttribute("info");
                    ArrayList<TestType> types = (ArrayList<TestType>) session.getAttribute("types");
                    request.setAttribute("patient", patient);
                    request.setAttribute("types", types);
                    request.setAttribute("info", info);
                    request.setAttribute("mess", "Nurse is not exist");
                    request.getRequestDispatcher("../nurse/createTest.jsp").forward(request, response);
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}