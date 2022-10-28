/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.AccountDetail;
import entity.Area;
import entity.Patient;
import entity.Room;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mountain
 */
public class PatientDBContext extends DBContext<Patient> {

    @Override
    public ArrayList<Patient> list() {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            String sql = "SELECT [Patient].[ID]\n"
                    + "      ,[Account_Details].[Fullname]\n"
                    + "  FROM [Patient]\n"
                    + "  INNER JOIN [Account_Details] ON [Patient].[ID] = [Account_Details].[ID]";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                AccountDetail ad = new AccountDetail();
                Account a = new Account();
                a.setUserName(rs.getString("ID"));
                ad.setAccount(a);
                ad.setFullName(rs.getNString("Fullname"));
                p.setAccDetail(ad);
                patients.add(p);
            }
        } catch (SQLException ex) {

        }
        return patients;
    }

    @Override
    public Patient get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Patient get(AccountDetail acc) {
        try {
            String sql = "SELECT [Patient].[ID]\n"
                    + "	  ,[Room].[Name] AS RoomName\n"
                    + "      ,[Room_ID]\n"
                    + "      ,[Note]\n"
                    + "      ,[BackgroundDisease]\n"
                    + "      ,[Blood Type]\n"
                    + "	  ,[Room_ID]\n"
                    + "	  ,ad1.[Fullname] AS DoctorFullname\n"
                    + "	  ,ad2.[Fullname] AS NurseFullname\n"
                    + "	  ,[Area_ID]\n"
                    + "	  ,[Area].[Name] AS AreaName\n"
                    + "	  ,[Area].[Address]\n"
                    + "  FROM [Patient]\n"
                    + "  INNER JOIN [Account] ON [Patient].[ID] = [Account].[Username]\n"
                    + "  INNER JOIN [Room] ON [Patient].[Room_ID] = [Room].[ID]\n"
                    + "  INNER JOIN [Area] ON [Room].[Area_ID] = [Area].[ID]\n"
                    + "  INNER JOIN [Account_Details] ad1 ON [Room].[DoctorManage] = ad1.[ID]\n"
                    + "  INNER JOIN [Account_Details] ad2 ON [Room].[NurseManage] = ad2.[ID]\n"
                    + "  WHERE [Account].[Username] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, acc.getAccount().getUserName());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                Room room = new Room();
                Area area = new Area();
                AccountDetail doc = new AccountDetail();
                AccountDetail nurse = new AccountDetail();
                nurse.setFullName(rs.getNString("NurseFullname"));
                doc.setFullName(rs.getNString("DoctorFullname"));
                room.setDoctorManage(doc);
                room.setNurseManage(nurse);
                area.setId(rs.getInt("Area_ID"));
                area.setName(rs.getNString("AreaName"));
                room.setArea(area);
                room.setId(rs.getInt("Room_ID"));
                room.setName(rs.getNString("RoomName"));
                p.setRoom(room);
                p.setAccount(acc.getAccount());
                p.setAccDetail(acc);
                p.setNote(rs.getNString("Note"));
                p.setBloodType(rs.getNString("Blood Type"));
                p.setBackgroundDisease(rs.getBoolean("BackgroundDisease"));
                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ArrayList<Patient> filter(String id, String name, Boolean gender, Date from, Date to, String username, int role) {
        ArrayList<Patient> patients = new ArrayList<>();
        try {
            String sql = "select ad.ID,ad.Fullname,ad.Gender,ad.Phone,ad.Address,ad.Email,ad.Nation,ad.DateOfBirth, \n"
                    + "p.BackgroundDisease,p.[Blood Type] \n"
                    + "from Patient p join Room r on \n"
                    + "p.Room_ID = r.ID join Account a on a.Username = p.ID\n"
                    + "join Account_Details ad on ad.ID = a.Username where 1 = 1";
            int count = 0;
            HashMap<Integer, Object> params = new HashMap<>();
            if (id != null) {
                count++;
                sql += "AND ad.ID like '%' + ? + '%'\n";
                params.put(count, id);
            }
            if (name != null) {
                count++;
                sql += "AND ad.Fullname like '%' + ? + '%'\n";
                params.put(count, name);
            }
            if (gender != null) {
                count++;
                sql += "AND ad.Gender=?\n";
                params.put(count, gender);
            }
            if (from != null) {
                count++;
                sql += "AND ad.DateOfBirth >= ?\n";
                params.put(count, from);
            }
            if (to != null) {
                count++;
                sql += "AND ad.DateOfBirth <= ?\n";
                params.put(count, to);
            }
            if (role == 3) {
                if (username != null) {
                    count++;
                    sql += "AND r.NurseManage = ?\n";
                    params.put(count, username);
                }
            } else if (role == 2) {
                count++;
                sql += "AND r.DoctorManage = ?\n";
                params.put(count, username);
            }

            PreparedStatement stm = connection.prepareCall(sql);
            for (Map.Entry<Integer, Object> entry : params.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUserName(rs.getString("ID"));
                AccountDetail ad = new AccountDetail();
                ad.setFullName(rs.getString("Fullname"));
                ad.setGender(rs.getBoolean("Gender"));
                ad.setPhone(rs.getString("Phone"));
                ad.setAddress(rs.getString("Address"));
                ad.setEmail(rs.getString("Email"));
                ad.setNation(rs.getString("Nation"));
                ad.setDateofbirth(rs.getDate("DateOfBirth"));
                ad.setAccount(a);
                Patient p = new Patient();
                p.setAccDetail(ad);
                p.setBackgroundDisease(rs.getBoolean("BackgroundDisease"));
                p.setBloodType(rs.getString("Blood Type"));
                patients.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PatientDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return patients;
    }

    @Override
    public void insert(Patient model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Patient model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Patient model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Patient getInfo(String username) {
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[Room_ID]\n"
                    + "      ,[Note]\n"
                    + "      ,[BackgroundDisease]\n"
                    + "      ,[Blood Type]\n"
                    + "  FROM [Patient]\n"
                    + "  INNER JOIN [Account] ON [Patient].[ID] = [Account].[Username]\n"
                    + "  WHERE [ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setBackgroundDisease(rs.getBoolean("BackgroundDisease"));
                patient.setBloodType(rs.getString("Blood Type"));
                patient.setNote(rs.getString("Note"));
                return patient;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
