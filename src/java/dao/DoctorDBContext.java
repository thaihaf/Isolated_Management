/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.Doctor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mountain
 */
public class DoctorDBContext extends DBContext<Doctor> {

    @Override
    public ArrayList<Doctor> list() {
        ArrayList<Doctor> doctors = new ArrayList<>();
        try {
            String sql = "SELECT [DoctorID]\n"
                    + "      ,[DoctorName]\n"
                    + "      ,[Gender]\n"
                    + "      ,[Phone]\n"
                    + "      ,[Address]\n"
                    + "      ,[Account_ID]\n"
                    + "	  ,[Email]\n"
                    + "	  ,[AccountType]\n"
                    + "  FROM [Doctor]\n"
                    + "  INNER JOIN [Account] on [Doctor].[Account_ID] = [Account].[AccountID]";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Doctor d = new Doctor();
                d.setDoctorID(rs.getNString("DoctorID"));
                d.setDoctorName(rs.getNString("DoctorName"));
                d.setGender(rs.getBoolean("Gender"));
                d.setPhone(rs.getNString("Phone"));
                d.setAddress(rs.getNString("Address"));
                Account a = new Account();
                a.setAccountID(rs.getNString("Account_ID"));
                a.setEmail(rs.getNString("Email"));
                a.setAccountType(rs.getNString("AccountType"));
                d.setAccount(a);
                doctors.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return doctors;
    }

    @Override
    public Doctor get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Doctor model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Doctor model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Doctor model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
