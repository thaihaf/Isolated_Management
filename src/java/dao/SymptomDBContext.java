/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.AccountDetail;
import entity.Symptom;
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
public class SymptomDBContext extends DBContext<Symptom> {

    @Override
    public ArrayList<Symptom> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Symptom get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Symptom model) {
        try {
            String sql = "INSERT INTO [dbo].[Symptom]\n"
                    + "           ([PatientID]\n"
                    + "           ,[Symptom]\n"
                    + "           ,[Date])\n"
                    + "     VALUES\n"
                    + "           (?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getUsername().getAccount().getUserName());
            stm.setString(2, model.getNote());
            stm.setDate(3, model.getDate());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(SymptomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Symptom model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Symptom model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Symptom> getSymptomByID(String username) {
        ArrayList<Symptom> lists = new ArrayList<>();
        try {
            String sql = "select PatientID, Symptom,Date from Symptom where PatientID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Symptom symptom = new Symptom();
                Account acc = new Account();
                acc.setUserName(rs.getString("PatientID"));
                AccountDetail ad = new AccountDetail();
                ad.setAccount(acc);
                symptom.setUsername(ad);
                symptom.setNote(rs.getString("Symptom").trim());
                symptom.setDate(rs.getDate("Date"));
                lists.add(symptom);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SymptomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lists;
    }

    public boolean checkSymptomExist(String username) {
        ArrayList<Symptom> lists = new ArrayList<>();
        try {
            String sql = "select PatientID, Symptom,Date from Symptom where PatientID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(SymptomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Symptom getLastReport(String username) {
        try {
            String sql = "select top 1 PatientID, Symptom,Date from Symptom where PatientID = ? order by ID Desc";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setUserName(rs.getString("PatientID"));
                AccountDetail ad = new AccountDetail();
                ad.setAccount(acc);
                Symptom symptom = new Symptom();
                symptom.setUsername(ad);
                symptom.setNote(rs.getString("Symptom"));
                symptom.setDate(rs.getDate("Date"));
                return symptom;
            }

        } catch (SQLException ex) {
            Logger.getLogger(SymptomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
