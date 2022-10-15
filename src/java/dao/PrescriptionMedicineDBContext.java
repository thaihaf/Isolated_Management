/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Medicine2;
import entity.Prescription;
import entity.Prescription2;
import entity.PrescriptionMedicine2;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    public ArrayList<PrescriptionMedicine2> getPMs(int id) {
        ArrayList<PrescriptionMedicine2> listPms = new ArrayList<>();

        try {
            String sql = "SELECT *\n"
                    + "FROM [SWP391].[dbo].[Prescription_Medicine]\n"
                    + "where  Prescription_ID = ?\n";

            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                PrescriptionMedicine2 pm = new PrescriptionMedicine2();
                Medicine2 medicine2 = new Medicine2();

                pm.setPrescriptionId(rs.getInt("Prescription_ID"));
                pm.setQuantity(rs.getInt("Quantity"));

                medicine2.setShipmentId(rs.getInt("Medicine_ID"));
                pm.setMedicine(medicine2);

                listPms.add(pm);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public boolean deletePms(int id) {
        try {
            connection.setAutoCommit(false);

            String sql = "DELETE FROM [dbo].[Prescription_Medicine]\n"
                    + "      WHERE Prescription_ID = ?";

            PreparedStatement stm = connection.prepareCall(sql);

            stm.setInt(1, id);

            if (stm.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
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
