/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.PrescriptionMedicine2;
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
public class PrescriptionMedicineDBContext extends DBContext<PrescriptionMedicine2> {

    public int insertPM(PrescriptionMedicine2 model) {
        try {
            connection.setAutoCommit(false);
            int primkey = 0;

            String sql = "INSERT INTO [dbo].[Prescription_Medicine]\n"
                    + "           ([Prescription_ID]\n"
                    + "           ,[Medicine_ID]\n"
                    + "           ,[Quantity])\n"
                    + "     VALUES\n"
                    + "           (?,?,?)\n";

            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stm.setInt(1, model.getPrescriptionId());
            stm.setInt(2, model.getMedicine().getShipmentId());
            stm.setInt(3, model.getQuantity());

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
    public ArrayList<PrescriptionMedicine2> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public PrescriptionMedicine2 get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(PrescriptionMedicine2 model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public void update(PrescriptionMedicine2 model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(PrescriptionMedicine2 model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
