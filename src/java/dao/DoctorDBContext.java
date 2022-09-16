/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.AccountDetail;
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

    @Override
    public Doctor get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Doctor getInfo(String username) {
        try {
            String sql = "select ad.ID, ad.Fullname,ad.Email,ad.Gender,ad.Address,ad.Phone,ad.Nation,ms.Level_of_education,ms.Hospital\n"
                    + "from Medical_Staff ms join Account a on ms.ID = a.Username \n"
                    + "join Account_Details ad on a.Username = ad.ID where ms.ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setUserName(rs.getString("ID"));
                AccountDetail ad = new AccountDetail();
                ad.setFullName(rs.getString("Fullname"));
                ad.setGender(rs.getBoolean("Gender"));
                ad.setPhone(rs.getString("Phone"));
                ad.setAddress(rs.getString("Address"));
                ad.setEmail(rs.getString("Email"));
                ad.setNation(rs.getString("Nation"));
                Doctor doc = new Doctor();
                doc.setEducation(rs.getString("Level_of_education"));
                doc.setHospital(rs.getString("Hospital"));
                doc.setAccount(acc);
                doc.setAccDetail(ad);
                return doc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
