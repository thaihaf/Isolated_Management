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
import java.sql.Statement;
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

    public ArrayList<Medicine2> getMedicines(String searchByName, String searchByType, String typeSearch, String searchName, String typeSort) {
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
            if (searchName != null) {
                sql += "where Medicine.Name like ?";
            }
            if (typeSearch != null) {
                switch (typeSearch) {
                    case "get-total":
                        break;
                    case "get-in-stock":
                        sql += "where Medicine.Quantity > 0";
                        break;
                    case "get-out-stock":
                        sql += "where Medicine.Quantity = 0";
                        break;
                    case "get-expiration-date":
                        sql += "where Medicine.ExpirationDate > GETDATE()";
                        break;
                    case "get-expired-date":
                        sql += "where Medicine.ExpirationDate < GETDATE()";
                        break;
                }
            }
            if (typeSort != null) {
                switch (typeSort) {
                    case "sortByName":
                        sql += "order by Medicine.Name";
                        break;
                    case "sortByQuantity":
                        sql += "order by Medicine.Quantity";
                        break;
                    case "sortByType":
                        sql += "order by MedicineType.Type";
                        break;
                }
            }

            PreparedStatement stm = connection.prepareCall(sql);

            if (searchByName != null) {
                stm.setString(1, "%" + searchByName + "%");
            }
            if (searchByType != null) {
                stm.setString(1, "%" + searchByType + "%");
            }
            if (searchName != null) {
                stm.setString(1, "%" + searchName + "%");
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

    public ArrayList<MedicineType> getMedicineTypes() {
        ArrayList<MedicineType> medicineTypes = new ArrayList<>();

        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[Type]\n"
                    + "      ,[Dosage]\n"
                    + "  FROM [dbo].[MedicineType]\n";

            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                MedicineType mt = new MedicineType();

                mt.setId(rs.getInt("ID"));
                mt.setType(rs.getString("Type"));
                mt.setDosage(rs.getString("Dosage"));

                medicineTypes.add(mt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return medicineTypes;
    }

    public boolean createMedicine(Medicine2 m) {
        try {
            connection.setAutoCommit(false);

            int primkey = 0;
            String sql = "INSERT INTO [dbo].[Medicine]\n"
                    + "           ([ShipmentID]\n"
                    + "           ,[Name]\n"
                    + "           ,[Quantity]\n"
                    + "           ,[Description]\n"
                    + "           ,[DateOfManufacture]\n"
                    + "           ,[ExpirationDate]\n"
                    + "           ,[MedicineTypeID])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?)\n";

            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            java.sql.Date date1 = fd.formatDateMedicine(m.getDateManafacture());
            java.sql.Date date2 = fd.formatDateMedicine(m.getExpirationDate());

            stm.setInt(1, m.getShipmentId());
            stm.setString(2, m.getName());
            stm.setInt(3, m.getStock());
            stm.setString(4, m.getDescription());
            stm.setString(5, date1.toString());
            stm.setString(6, date2.toString());
            stm.setInt(7, m.getMedicineType().getId());

            if (stm.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicineDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean updateMedicine(Medicine2 m) {
        try {
            connection.setAutoCommit(false);

            int primkey = 0;
            String sql = "UPDATE [dbo].[Medicine]\n"
                    + "   SET [ShipmentID] = ?\n"
                    + "      ,[Name] = ?\n"
                    + "      ,[Quantity] = ?\n"
                    + "      ,[Description] = ?\n"
                    + "      ,[DateOfManufacture] = ?\n"
                    + "      ,[ExpirationDate] = ?\n"
                    + "      ,[MedicineTypeID] = ?\n"
                    + " WHERE ShipmentID = ?";

            PreparedStatement stm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            java.sql.Date date1 = fd.formatDateMedicine(m.getDateManafacture());
            java.sql.Date date2 = fd.formatDateMedicine(m.getExpirationDate());

            stm.setInt(1, m.getShipmentId());
            stm.setString(2, m.getName());
            stm.setInt(3, m.getStock());
            stm.setString(4, m.getDescription().toString());
            stm.setString(5, date1.toString());
            stm.setString(6, date2.toString());
            stm.setInt(7, m.getMedicineType().getId());
            stm.setInt(8, m.getShipmentId());

            if (stm.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicineDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Medicine2 getMedicine(int id) {

        try {
            String sql = "SELECT   \n"
                    + "Medicine.*, \n"
                    + "MedicineType.Type, \n"
                    + "MedicineType.Dosage\n"
                    + "FROM      \n"
                    + "Medicine \n"
                    + "INNER JOIN\n"
                    + "MedicineType ON Medicine.MedicineTypeID = MedicineType.ID\n"
                    + "where Medicine.ShipmentID = ? \n";

            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Medicine2 m = new Medicine2();
                MedicineType mt = new MedicineType();

                m.setShipmentId(rs.getInt("ShipmentID"));
                m.setName(rs.getString("Name"));
                m.setStock(rs.getInt("Quantity"));
                m.setDescription(rs.getString("Description"));
                m.setDateManafacture(rs.getString("DateOfManufacture"));
                m.setExpirationDate(rs.getString("ExpirationDate"));

                mt.setId(rs.getInt("MedicineTypeID"));
                mt.setType(rs.getString("Type"));
                mt.setDosage(rs.getString("Dosage"));

                m.setMedicineType(mt);

                return m;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean checkExistID(int id) {
        try {
            String sql = "SELECT TOP 1 1 as 'count'FROM Medicine WHERE ShipmentID = ? \n";

            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public Medicine2 getQuantiy(int id) {
        Medicine2 m = new Medicine2();

        try {
            String sql = "SELECT [ShipmentID],[Quantity]\n"
                    + "FROM [SWP391].[dbo].[Medicine]\n"
                    + "where ShipmentID = ?\n";

            PreparedStatement stm = connection.prepareCall(sql);

            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                m.setShipmentId(rs.getInt("ShipmentID"));
                m.setStock(rs.getInt("Quantity"));

                return m;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean updateQuantity(int id, int quantity) {
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE [dbo].[Medicine]\n"
                    + "   SET [Quantity] = ?\n"
                    + " WHERE ShipmentID = ?";

            PreparedStatement stm = connection.prepareCall(sql);

            stm.setInt(1, quantity);
            stm.setInt(2, id);

            if (stm.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MedicineDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
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
