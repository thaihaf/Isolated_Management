/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Symptom;
import java.sql.PreparedStatement;
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

}
