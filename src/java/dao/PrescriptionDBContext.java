/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.AccountDetail;
import entity.Medicine;
import entity.Prescription;
import entity.PrescriptionMedicine;
import entity.TestResult;
import entity.TestType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hapro
 */
public class PrescriptionDBContext extends DBContext<Prescription> {

    public ArrayList<Prescription> getListPrescription(String doctorId, String patientId) {
        ArrayList<Prescription> listPrescription = new ArrayList<>();
        try {
            String sql = "SELECT        \n"
                    + "Prescription.ID, \n"
                    + "Prescription.Patient_ID, \n"
                    + "Prescription.Doctor_ID, \n"
                    + "Prescription.PrescriptionDate, \n"
                    + "Prescription.Title, \n"
                    + "Prescription.Guide, \n"
                    + "Prescription.Status, \n"
                    + "Medicine.ShipmentID,\n"
                    + "Medicine.Name, \n"
                    + "Prescription_Medicine.Quantity, \n"
                    + "Medicine.Quantity AS Stock, \n"
                    + "Medicine.Description, \n"
                    + "Medicine.DateOfManufacture, \n"
                    + "Medicine.ExpirationDate, \n"
                    + "Medicine.MedicineTypeID\n"
                    + "FROM            \n"
                    + "Medicine \n"
                    + "INNER JOIN\n"
                    + "Prescription_Medicine ON Medicine.ShipmentID = Prescription_Medicine.Medicine_ID \n"
                    + "INNER JOIN\n"
                    + "Prescription ON Prescription_Medicine.Prescription_ID = Prescription.ID\n"
                    + "where \n"
                    + "Prescription.Doctor_ID = ? \n"
                    + "and \n"
                    + "Prescription.Patient_ID = ?";

            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, doctorId);
            stm.setString(2, patientId);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Prescription prescription = new Prescription();

                Medicine medicine = new Medicine();
                PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();

                medicine.setShipmentID(rs.getInt("ShipmentID"));
                medicine.setName(rs.getString("Name"));
                medicine.setQuantity(rs.getInt("Stock"));
                medicine.setDescription(rs.getString("Description"));
                medicine.setDateOfManufacture(rs.getDate("DateOfManufacture"));
                medicine.setExpirationDate(rs.getDate("ExpirationDate"));
                medicine.setMedicineTypeID(rs.getInt("MedicineTypeID"));

                prescriptionMedicine.setPrescriptionId(rs.getInt("ID"));
                prescriptionMedicine.setMedicineId(rs.getInt("ShipmentID"));
                prescriptionMedicine.setQuantity(rs.getInt("Quantity"));

                prescription.setId(rs.getInt("ID"));
                prescription.setPatientID(rs.getString("Patient_ID"));
                prescription.setDoctorID(rs.getString("Doctor_ID"));
                prescription.setPrescriptionDate(rs.getDate("PrescriptionDate"));
                prescription.setTitle(rs.getString("Title"));
                prescription.setGuide(rs.getString("Guide"));
                prescription.setStatus(rs.getInt("Status"));
                prescription.setMedicine(medicine);
                prescription.setPrescriptionMedicine(prescriptionMedicine);

                listPrescription.add(prescription);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPrescription;
    }

    @Override
    public ArrayList<Prescription> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Prescription get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Prescription model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Prescription model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Prescription model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
