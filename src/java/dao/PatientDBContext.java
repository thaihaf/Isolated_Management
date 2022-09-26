/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.AccountDetail;
import entity.Patient;
import entity.Area;
import entity.Patient;
import entity.Room;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mountain
 */
public class PatientDBContext extends DBContext<Patient> {
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
                p.setNote(rs.getNString("Note"));
                p.setBloodType(rs.getNString("Blood Type"));
                p.setBackgroundDisease(rs.getBoolean("BackgroundDisease"));
                return p;

            }
        } catch (SQLException ex) {
            Logger.getLogger(PatientDBContext.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
                patient.setBloodType(rs.getString("BloodType"));
                patient.setNote(rs.getString("Note"));
                return patient;
            }
        } catch (SQLException ex) {
//            Logger.getLogger(DoctorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Patient> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
