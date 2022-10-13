/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.AccountDetail;
import entity.Report;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mountain
 */
public class ReportDBContext extends DBContext<Report> {

    @Override
    public ArrayList<Report> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Report get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Report model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Report model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Report model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Report> listByNurseAccountWithSearchAndPaginated(String nurseID, String patientID, Integer roomID, int pageIndex, int pageSize) {
        ArrayList<Report> reports = new ArrayList<>();
        try {
            String sql = "SELECT [Report].[ID]\n"
                    + "      ,[Patient_ID]\n"
                    + "	  ,[Account_Details].[Fullname]\n"
                    + "      ,[CreateDate]\n"
                    + "      ,[Report].[Note]\n"
                    + "  FROM [Report]\n"
                    + "  INNER JOIN [Patient] ON [Report].[Patient_ID] = [Patient].[ID]\n"
                    + "  INNER JOIN [Room] ON [Patient].[Room_ID] = [Room].[ID]\n"
                    + "  INNER JOIN [Account_Details] ON [Report].[Patient_ID] = [Account_Details].[ID]\n"
                    + "  WHERE [Room].[NurseManage] = ?\n";
            int count = 1;
            HashMap<Integer, Object> values = new HashMap<>();
            values.put(count, nurseID);
            if (patientID != null) {
                sql += "AND [Patient_ID] = ?\n";
                count++;
                values.put(count, patientID);
            }
            if (roomID != null) {
                sql += "AND [Room_ID] = ?\n";
                count++;
                values.put(count, roomID);
            }
            sql += "ORDER BY [ID] ASC\n"
                    + "  OFFSET (? - 1) * ? ROWS\n"
                    + "  FETCH NEXT ? ROWS ONLY";
            for (int i = 0; i < 3; i++) {
                count++;
                if (i == 0) {
                    values.put(count, pageIndex);
                } else {
                    values.put(count, pageSize);
                }
            }
            PreparedStatement stm = connection.prepareCall(sql);
            for (Map.Entry<Integer, Object> entry : values.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Report r = new Report();
                r.setId(rs.getInt("ID"));
                AccountDetail patientDetail = new AccountDetail();
                Account patientAcc = new Account();
                patientAcc.setUserName(rs.getString("Patient_ID"));
                patientDetail.setAccount(patientAcc);
                patientDetail.setFullName(rs.getNString("Fullname"));
                r.setPatient(patientDetail);
                r.setCreatedDate(rs.getDate("CreateDate"));
                r.setNote(rs.getNString("Note"));
                reports.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reports;
    }

    public int countRecord(String nurseID, String patientToSearch, Integer roomToSearch) {
        try {
            String sql = "SELECT COUNT(*) AS Total\n"
                    + "  FROM [Report]\n"
                    + "  INNER JOIN [Patient] ON [Report].[Patient_ID] = [Patient].[ID]\n"
                    + "  INNER JOIN [Room] ON [Patient].[Room_ID] = [Room].[ID]\n"
                    + "  WHERE 1 = 1 AND [Room].[NurseManage] = ?\n";
            int count = 1;
            HashMap<Integer, Object> sets = new HashMap<>();
            sets.put(count, nurseID);
            if (patientToSearch != null) {
                sql += "AND [Report].[Patient_ID] = ?\n";
                count++;
                sets.put(count, patientToSearch);
            }
            if (roomToSearch != null) {
                sql += "AND [Room].[ID] = ?\n";
                count++;
                sets.put(count, roomToSearch);
            }
            PreparedStatement stm = connection.prepareCall(sql);
            for (Map.Entry<Integer, Object> entry : sets.entrySet()) {
                Integer key = entry.getKey();
                Object value = entry.getValue();
                stm.setObject(key, value);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
        }
        return -1;
    }
}
