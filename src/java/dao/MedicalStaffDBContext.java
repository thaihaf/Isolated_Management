/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.AccountDetail;
import entity.MedicalStaff;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mountain
 */
public class MedicalStaffDBContext extends DBContext<MedicalStaff> {

    @Override
    public ArrayList<MedicalStaff> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public MedicalStaff get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insert(MedicalStaff model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(MedicalStaff model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(MedicalStaff model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public MedicalStaff getByAccountDetail(AccountDetail acc) {
        try {
            String sql = "SELECT [Medical_Staff].[ID]\n"
                    + "      ,[Level of education]\n"
                    + "      ,[Hospital]\n"
                    + "  FROM [Medical_Staff]\n"
                    + "  INNER JOIN [Account_Details] ON [Medical_Staff].[ID] = [Account_Details].[ID]\n"
                    + "  WHERE [Account_Details].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, acc.getAccount().getUserName());
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                MedicalStaff med = new MedicalStaff();
                med.setAccount(acc);
                med.setLevelOfEducation(rs.getNString("Level of education"));
                med.setHospital(rs.getNString("Hospital"));
                return med;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
