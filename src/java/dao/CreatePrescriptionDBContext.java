/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Prescription2;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hapro
 */
public class CreatePrescriptionDBContext extends DBContext<Prescription2> {

    public int insertPrescription(Prescription2 model) {
        try {
            connection.setAutoCommit(false);
            
            int primkey = 0;
            String sql = "INSERT INTO [dbo].[Prescription]\n"
                    + "           ([Patient_ID]\n"
                    + "           ,[Doctor_ID]\n"
                    + "           ,[PrescriptionDate]\n"
                    + "           ,[Title]\n"
                    + "           ,[Guide]\n"
                    + "           ,[Status])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?)\n";

            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stm.setString(1, model.getPatientID());
            stm.setString(2, model.getDoctorID());
            stm.setString(3, model.getPrescriptionDate());
            stm.setString(4, model.getTitle());
            stm.setString(5, model.getGuide());
            stm.setInt(6, model.getStatus());

            if (stm.executeUpdate() > 0) {
                java.sql.ResultSet generatedKeys = stm.getGeneratedKeys();
                if (generatedKeys.next()) {
                    primkey = generatedKeys.getInt(1);
                }
                
                connection.commit();
                return primkey;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }

    @Override
    public ArrayList<Prescription2> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Prescription2 get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Prescription2 model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public void update(Prescription2 model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Prescription2 model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
