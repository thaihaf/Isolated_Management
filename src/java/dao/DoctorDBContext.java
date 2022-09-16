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
 * @author Admin
 */
public class DoctorDBContext extends DBContext<Doctor> {
    
    @Override
    public ArrayList<Doctor> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public Doctor get(int id) {
        try {
            String sql = "select a.AccountID, d.DoctorID, d.DoctorName, d.Gender,d.Phone,d.Address, d.[Level of education], d.Hospital \n"
                    + "from Doctor d join Account a \n"
                    + "on d.Account_ID = a.AccountID where a.AccountID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Doctor doc = new Doctor();
                doc.setDoctorID(rs.getString("DoctorID"));
                doc.setDoctorName(rs.getString("DoctorName"));
                doc.setGender(rs.getBoolean("Gender"));
                doc.setPhone(rs.getString("Phone"));
                doc.setAdress(rs.getString("Address"));
                Account acc = new Account();
                acc.setAccountID(rs.getString("AccountID"));
                doc.setAccount(acc);
                return doc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
