/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Medicine2;
import entity.MedicineType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.FormatDate;

/**
 *
 * @author hapro
 */
public class MedicineDBContext extends DBContext<Medicine2> {

    FormatDate fd = new FormatDate();

    public ArrayList<Medicine2> getMedicines(String searchByName, String searchByType) {
        ArrayList<Medicine2> medicines = new ArrayList<>();

        try {
            String sql = "SELECT        \n"
                    + "Medicine.*, \n"
                    + "MedicineType.Type, \n"
                    + "MedicineType.Dosage\n"
                    + "FROM            \n"
                    + "Medicine \n"
                    + "INNER JOIN\n"
                    + "MedicineType \n"
                    + "ON Medicine.MedicineTypeID = MedicineType.ID \n";

            if (searchByName != null) {
                sql += "where Medicine.Name like ?";
            }
            if (searchByType != null) {
                sql += "where MedicineType.Type like ?";
            }

            PreparedStatement stm = connection.prepareCall(sql);

            if (searchByName != null) {
                stm.setString(1, "%" + searchByName + "%");
            }
            if (searchByType != null) {
                stm.setString(1, "%" + searchByType + "%");
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Medicine2 medicine = new Medicine2();
                MedicineType medicineType = new MedicineType();

                medicine.setShipmentId(rs.getInt("ShipmentID"));
                medicine.setName(rs.getString("Name"));
                medicine.setStock(rs.getInt("Quantity"));
                medicine.setDescription(rs.getString("Description"));
                medicine.setDateManafacture(fd.formatDate(rs.getString("DateOfManufacture")));
                medicine.setExpirationDate(fd.formatDate(rs.getString("ExpirationDate")));
                medicine.setMedicineTypeId(rs.getInt("MedicineTypeID"));

                medicineType.setId(rs.getInt("MedicineTypeID"));
                medicineType.setType(rs.getString("Type"));
                medicineType.setDosage(rs.getString("Dosage"));

                medicine.setMedicineType(medicineType);

                medicines.add(medicine);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medicines;
    }

    @Override
    public ArrayList<Medicine2> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public Medicine2 get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Medicine2 model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Medicine2 model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Medicine2 model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
