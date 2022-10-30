/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controler.nurse;

import dao.AccountDetailDBContext;
import dao.AreaDBContext;
import dao.MedicalStaffDBContext;
import dao.PatientDBContext;
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
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">Đang chờ</button>\n"
                                    + "                                                                                             </div>";
                            break;

                        case 0:
                            roomList += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">Không triệu chứng</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-secondary border-10\">\n"
                                    + "                                                                                                     <p>Chưa có triệu chứng</p>\n"
                                    + "                                                                                                     <p>Bên cạnh đó, người bệnh tỉnh táo, tự phục vụ được</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 1:
                            roomList += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">Mức độ nhẹ</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-secondary border-10\">\n"
                                    + "                                                                                                     <p>F0 có các triệu chứng lâm sàng không đặc hiệu như sốt, ho khan, đau họng, nghẹt mũi, mệt mỏi, đau đầu, đau mỏi cơ, mất vị giác, khứu giác, tiêu chảy… Nhịp thở < 20 lần/phút, SpO2 > 96% khi thở khí trời. </p>\n"
                                    + "                                                                                                     <p>Bên cạnh đó, người bệnh tỉnh táo, tự phục vụ được; X-quang phổi bình thường hoặc có nhưng tổn thương ít.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 2:
                            roomList += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-info\">Mức độ trung bình</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-info\">\n"
                                    + "                                                                                                     <p>Đánh giá toàn trạng, người bệnh có các triệu chứng lâm sàng không đặc hiệu như mức độ nhẹ. </p>\n"
                                    + "                                                                                                     <p>F0 có thể khó thở khi gắng sức (đi lại trong nhà, lên cầu thang).</p>\n"
                                    + "                                                                                                     <p>Mạch của người bệnh có thể nhanh hoặc chậm, da khô, nhịp tim nhanh, huyết áp bình thường, ý thức tỉnh táo. </p>\n"
                                    + "                                                                                                     <p>Ngoài ra, chụp X-quang ngực và cắt lớp vi tính ngực phát hiện có tổn thương, tổn thương dưới 50%. </p>\n"
                                    + "                                                                                                     <p>Siêu âm thấy hình ảnh sóng B, khí máu động mạch: PaO2/FiO2 > 300.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 3:
                            roomList += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-warning\">Mức độ nặng</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-warning border-10\">\n"
                                    + "                                                                                                     <p>F0 được phân loại thuộc nhóm nặng nếu hô hấp có dấu hiệu viêm phổi kèm theo bất kỳ một trong các dấu hiệu sau: nhịp thở > 25 lần/phút; khó thở nặng, co kéo cơ hô hấp phụ; SpO2 < 94% khi thở khí phòng.</p>\n"
                                    + "                                                                                                     <p>Về tuần hoàn, nhịp tim người bệnh có thể nhanh hoặc chậm, HA bình thường hay tăng. </p>\n"
                                    + "                                                                                                     <p>Về thần kinh, người bệnh có thể bứt rứt hoặc đừ, mệt. Chụp X-quang ngực và cắt lớp vi tính ngực: có tổn thương, tổn thương trên 50%. </p>\n"
                                    + "                                                                                                     <p>Siêu âm thấy hình ảnh sóng B nhiều, khí máu động mạch: PaO2/FiO2 khoảng 200-300.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 4:
                            roomList += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-danger\">Mức độ nguy kịch</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-danger border-10\">\n"
                                    + "                                                                                                     <p>F0 thuộc nhóm nguy kịch có biểu hiện thở nhanh > 30 lần/phút hoặc < 10 lần/phút, có dấu hiệu suy hô hấp nặng, thở gắng sức nhiều, thở bất thường hoặc cần hỗ trợ hô hấp bằng thở oxy dòng cao (HFNC), thở máy. </p>\n"
                                    + "                                                                                                     <p>Ý thức người bệnh giảm hoặc hôn mê.</p>\n"
                                    + "                                                                                                     <p>Nhịp tim bệnh nhân có thể nhanh hoặc chậm, huyết áp tụt, tiểu ít hoặc vô niệu. </p>\n"
                                    + "                                                                                                     <p>Kết quả X-quang ngực và cắt lớp vi tính ngực phát hiện có tổn thương, tổn thương trên 50%.</p> \n"
                                    + "                                                                                                     <p>Siêu âm hình ảnh sóng B nhiều, khí máu động mạch: PaO2/FiO2 < 200, toan hô hấp, lactat máu > 2 mmol/L.</p>\n"
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
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">Đang chờ</button>\n"
                                    + "                                                                                             </div>";
                            break;

                        case 0:
                            output += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">Không triệu chứng</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-secondary border-10\">\n"
                                    + "                                                                                                     <p>Chưa có triệu chứng</p>\n"
                                    + "                                                                                                     <p>Bên cạnh đó, người bệnh tỉnh táo, tự phục vụ được</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 1:
                            output += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-secondary\">Mức độ nhẹ</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-secondary border-10\">\n"
                                    + "                                                                                                     <p>F0 có các triệu chứng lâm sàng không đặc hiệu như sốt, ho khan, đau họng, nghẹt mũi, mệt mỏi, đau đầu, đau mỏi cơ, mất vị giác, khứu giác, tiêu chảy… Nhịp thở < 20 lần/phút, SpO2 > 96% khi thở khí trời. </p>\n"
                                    + "                                                                                                     <p>Bên cạnh đó, người bệnh tỉnh táo, tự phục vụ được; X-quang phổi bình thường hoặc có nhưng tổn thương ít.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 2:
                            output += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-info\">Mức độ trung bình</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-info\">\n"
                                    + "                                                                                                     <p>Đánh giá toàn trạng, người bệnh có các triệu chứng lâm sàng không đặc hiệu như mức độ nhẹ. </p>\n"
                                    + "                                                                                                     <p>F0 có thể khó thở khi gắng sức (đi lại trong nhà, lên cầu thang).</p>\n"
                                    + "                                                                                                     <p>Mạch của người bệnh có thể nhanh hoặc chậm, da khô, nhịp tim nhanh, huyết áp bình thường, ý thức tỉnh táo. </p>\n"
                                    + "                                                                                                     <p>Ngoài ra, chụp X-quang ngực và cắt lớp vi tính ngực phát hiện có tổn thương, tổn thương dưới 50%. </p>\n"
                                    + "                                                                                                     <p>Siêu âm thấy hình ảnh sóng B, khí máu động mạch: PaO2/FiO2 > 300.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 3:
                            output += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-warning\">Mức độ nặng</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-warning border-10\">\n"
                                    + "                                                                                                     <p>F0 được phân loại thuộc nhóm nặng nếu hô hấp có dấu hiệu viêm phổi kèm theo bất kỳ một trong các dấu hiệu sau: nhịp thở > 25 lần/phút; khó thở nặng, co kéo cơ hô hấp phụ; SpO2 < 94% khi thở khí phòng.</p>\n"
                                    + "                                                                                                     <p>Về tuần hoàn, nhịp tim người bệnh có thể nhanh hoặc chậm, HA bình thường hay tăng. </p>\n"
                                    + "                                                                                                     <p>Về thần kinh, người bệnh có thể bứt rứt hoặc đừ, mệt. Chụp X-quang ngực và cắt lớp vi tính ngực: có tổn thương, tổn thương trên 50%. </p>\n"
                                    + "                                                                                                     <p>Siêu âm thấy hình ảnh sóng B nhiều, khí máu động mạch: PaO2/FiO2 khoảng 200-300.</p>\n"
                                    + "                                                                                                 </div>\n"
                                    + "                                                                                             </div>";
                            break;
                        case 4:
                            output += "<td><div class=\"des_show\" >\n"
                                    + "                                                                                                 <button type=\"button\" class=\"btn btn-danger\">Mức độ nguy kịch</button>\n"
                                    + "                                                                                                 <div class=\"des_hidden border border-danger border-10\">\n"
                                    + "                                                                                                     <p>F0 thuộc nhóm nguy kịch có biểu hiện thở nhanh > 30 lần/phút hoặc < 10 lần/phút, có dấu hiệu suy hô hấp nặng, thở gắng sức nhiều, thở bất thường hoặc cần hỗ trợ hô hấp bằng thở oxy dòng cao (HFNC), thở máy. </p>\n"
                                    + "                                                                                                     <p>Ý thức người bệnh giảm hoặc hôn mê.</p>\n"
                                    + "                                                                                                     <p>Nhịp tim bệnh nhân có thể nhanh hoặc chậm, huyết áp tụt, tiểu ít hoặc vô niệu. </p>\n"
                                    + "                                                                                                     <p>Kết quả X-quang ngực và cắt lớp vi tính ngực phát hiện có tổn thương, tổn thương trên 50%.</p> \n"
                                    + "                                                                                                     <p>Siêu âm hình ảnh sóng B nhiều, khí máu động mạch: PaO2/FiO2 < 200, toan hô hấp, lactat máu > 2 mmol/L.</p>\n"
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
