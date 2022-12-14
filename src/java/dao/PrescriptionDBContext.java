/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Medicine;
import entity.MedicineType;
import entity.Prescription;
import entity.Prescription2;
import entity.PrescriptionMedicine;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.FormatDate;

/**
 *
 * @author hapro
 */
public class PrescriptionDBContext extends DBContext<Prescription> {

    FormatDate fd = new FormatDate();

    public ArrayList<Prescription> getListPrescription(String doctorId, String patientId, String searchVal, String df, String dt, String sort) {
        ArrayList<Prescription> listPrescription = new ArrayList<>();

        try {
            String sql = "SELECT Prescription.*\n"
                    + "FROM Prescription\n"
                    + "where Doctor_ID = ?\n"
                    + "and Patient_ID = ?\n";

            if (searchVal != null) {
                sql += "and Title like ? \n";
            }

            if (df != null && dt != null) {
                sql += "and (PrescriptionDate BETWEEN cast(? as datetime) and cast(? as datetime)) \n";
            }

            if (sort != null) {
                switch (sort) {
                    case "date":
                        sql += "ORDER BY PrescriptionDate \n";
                        break;
                    case "status":
                        sql += "ORDER BY Status \n";
                        break;
                }
            }

            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, doctorId);
            stm.setString(2, patientId);

            if (searchVal != null) {
                stm.setString(3, "%" + searchVal + "%");

                if (df != null && dt != null) {
                    stm.setDate(4, Date.valueOf(df));
                    stm.setDate(5, Date.valueOf(dt));
                }
            } else {
                if (df != null && dt != null) {
                    stm.setDate(3, Date.valueOf(df));
                    stm.setDate(4, Date.valueOf(dt));
                }
            }

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Prescription prescription = new Prescription();

                prescription.setId(rs.getInt("ID"));
                prescription.setPatientID(rs.getString("Patient_ID"));
                prescription.setDoctorID(rs.getString("Doctor_ID"));

                prescription.setPrescriptionDate(fd.formatDatetime(rs.getString("PrescriptionDate")));
                prescription.setTitle(rs.getString("Title"));
                prescription.setGuide(rs.getString("Guide"));
                prescription.setStatus(rs.getInt("Status"));

                listPrescription.add(prescription);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listPrescription;
    }

    public ArrayList<Prescription> getListPrescriptionDetails(String doctorId, String patientId, String searchVal, String df, String dt, String sort) {

        ArrayList<Prescription> listPrescription = getListPrescription(doctorId, patientId, searchVal, df, dt, sort);

        String sql = "SELECT        \n"
                + "Prescription_Medicine.Prescription_ID, \n"
                + "Prescription_Medicine.Medicine_ID, \n"
                + "Medicine.Name, \n"
                + "Prescription_Medicine.Quantity, \n"
                + "Medicine.Quantity AS Stock, \n"
                + "Medicine.Description, \n"
                + "Medicine.DateOfManufacture, \n"
                + "Medicine.ExpirationDate, \n"
                + "Medicine.MedicineTypeID,\n"
                + "MedicineType.Type, \n"
                + "MedicineType.Dosage\n"
                + "FROM            \n"
                + "Medicine \n"
                + "INNER JOIN\n"
                + "Prescription_Medicine ON Medicine.ShipmentID = Prescription_Medicine.Medicine_ID \n"
                + "INNER JOIN\n"
                + "MedicineType ON Medicine.MedicineTypeID = MedicineType.ID\n"
                + "where Prescription_ID = ?";

        for (Prescription p : listPrescription) {
            ArrayList<Medicine> medicines = new ArrayList<>();
            ArrayList<MedicineType> medicineTypes = new ArrayList<>();
            ArrayList<PrescriptionMedicine> prescriptionMedicines = new ArrayList<>();

            try {
                PreparedStatement stm = connection.prepareCall(sql);
                stm.setInt(1, p.getId());

                ResultSet rs = stm.executeQuery();
                while (rs.next()) {
                    Medicine medicine = new Medicine();
                    MedicineType medicineType = new MedicineType();
                    PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();

                    medicine.setShipmentID(rs.getInt("Medicine_ID"));
                    medicine.setName(rs.getString("Name"));
                    medicine.setQuantity(rs.getInt("Stock"));
                    medicine.setDescription(rs.getString("Description"));
                    medicine.setDateOfManufacture(fd.formatDate(rs.getString("DateOfManufacture")));
                    medicine.setExpirationDate(fd.formatDate(rs.getString("ExpirationDate")));
                    medicine.setMedicineTypeID(rs.getInt("MedicineTypeID"));

                    medicineType.setId(rs.getInt("MedicineTypeID"));
                    medicineType.setType(rs.getString("Type"));
                    medicineType.setDosage(rs.getString("Dosage"));

                    prescriptionMedicine.setPrescriptionId(rs.getInt("Prescription_ID"));
                    prescriptionMedicine.setMedicineId(rs.getInt("Medicine_ID"));
                    prescriptionMedicine.setQuantity(rs.getInt("Quantity"));

                    medicines.add(medicine);
                    medicineTypes.add(medicineType);
                    prescriptionMedicines.add(prescriptionMedicine);

                }
            } catch (SQLException ex) {
                Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }

            p.setMedicines(medicines);
            p.setMedicineTypes(medicineTypes);
            p.setPrescriptionMedicines(prescriptionMedicines);
        }

        return listPrescription;
    }

    public Prescription getPrescription(String doctorId, String patientId, int id) {
        Prescription prescription = new Prescription();

        try {
            String sql = "SELECT Prescription.*\n"
                    + "FROM Prescription\n"
                    + "where Doctor_ID = ?\n"
                    + "and Patient_ID = ?\n"
                    + "and ID = ?\n";

            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, doctorId);
            stm.setString(2, patientId);
            stm.setInt(3, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                prescription.setId(rs.getInt("ID"));
                prescription.setPatientID(rs.getString("Patient_ID"));
                prescription.setDoctorID(rs.getString("Doctor_ID"));

                prescription.setPrescriptionDate(fd.formatDatetime(rs.getString("PrescriptionDate")));
                prescription.setTitle(rs.getString("Title"));
                prescription.setGuide(rs.getString("Guide"));
                prescription.setStatus(rs.getInt("Status"));

                return prescription;

            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Prescription getPrescriptionDetails(String doctorId, String patientId, int id) {
        Prescription prescription = getPrescription(doctorId, patientId, id);

        String sql = "SELECT        \n"
                + "Prescription_Medicine.Prescription_ID, \n"
                + "Prescription_Medicine.Medicine_ID, \n"
                + "Medicine.Name, \n"
                + "Prescription_Medicine.Quantity, \n"
                + "Medicine.Quantity AS Stock, \n"
                + "Medicine.Description, \n"
                + "Medicine.DateOfManufacture, \n"
                + "Medicine.ExpirationDate, \n"
                + "Medicine.MedicineTypeID,\n"
                + "MedicineType.Type, \n"
                + "MedicineType.Dosage\n"
                + "FROM            \n"
                + "Medicine \n"
                + "INNER JOIN\n"
                + "Prescription_Medicine ON Medicine.ShipmentID = Prescription_Medicine.Medicine_ID \n"
                + "INNER JOIN\n"
                + "MedicineType ON Medicine.MedicineTypeID = MedicineType.ID\n"
                + "where Prescription_ID = ?";

        ArrayList<Medicine> medicines = new ArrayList<>();
        ArrayList<MedicineType> medicineTypes = new ArrayList<>();
        ArrayList<PrescriptionMedicine> prescriptionMedicines = new ArrayList<>();

        try {
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Medicine medicine = new Medicine();
                MedicineType medicineType = new MedicineType();
                PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();

                medicine.setShipmentID(rs.getInt("Medicine_ID"));
                medicine.setName(rs.getString("Name"));
                medicine.setQuantity(rs.getInt("Stock"));
                medicine.setDescription(rs.getString("Description"));
                medicine.setDateOfManufacture(fd.formatDate(rs.getString("DateOfManufacture")));
                medicine.setExpirationDate(fd.formatDate(rs.getString("ExpirationDate")));
                medicine.setMedicineTypeID(rs.getInt("MedicineTypeID"));

                medicineType.setId(rs.getInt("MedicineTypeID"));
                medicineType.setType(rs.getString("Type"));
                medicineType.setDosage(rs.getString("Dosage"));

                prescriptionMedicine.setPrescriptionId(rs.getInt("Prescription_ID"));
                prescriptionMedicine.setMedicineId(rs.getInt("Medicine_ID"));
                prescriptionMedicine.setQuantity(rs.getInt("Quantity"));

                medicines.add(medicine);
                medicineTypes.add(medicineType);
                prescriptionMedicines.add(prescriptionMedicine);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        prescription.setMedicines(medicines);
        prescription.setMedicineTypes(medicineTypes);
        prescription.setPrescriptionMedicines(prescriptionMedicines);

        return prescription;
    }

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

    public boolean updatePrescription(Prescription2 model) {
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE [dbo].[Prescription]\n"
                    + "   SET [Patient_ID] = ?\n"
                    + "      ,[Doctor_ID] = ?\n"
                    + "      ,[PrescriptionDate] = ?\n"
                    + "      ,[Title] = ?\n"
                    + "      ,[Guide] = ?\n"
                    + "      ,[Status] = ?\n"
                    + " WHERE [Prescription].ID = ?";

            PreparedStatement stm = connection.prepareCall(sql);

            stm.setString(1, model.getPatientID());
            stm.setString(2, model.getDoctorID());
            stm.setString(3, model.getPrescriptionDate());
            stm.setString(4, model.getTitle());
            stm.setString(5, model.getGuide());
            stm.setInt(6, model.getStatus());
            stm.setInt(7, model.getId());

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
