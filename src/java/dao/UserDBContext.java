/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import java.sql.Date;
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
public class UserDBContext extends DBContext<Account> {

    public void updatePatient(String fullName, Boolean gender, String phone, String address,
            String email, Date dateofbirth, String nation, String userName, String note,
            String bloodType, Boolean backgroundDesease) {
        try {
            String sql = "UPDATE Account_Details\n"
                    + "SET Account_Details.Fullname = ?, \n"
                    + "Account_Details.Gender = ?, \n"
                    + "Account_Details.Phone = ?,\n"
                    + "Account_Details.Address = ?,\n"
                    + "Account_Details.Email = ?,\n"
                    + "Account_Details.DateOfBirth = ?,\n"
                    + "Account_Details.Nation = ?\n"
                    + "FROM Account_Details INNER JOIN Account\n"
                    + "ON Account.Username = Account_Details.ID\n"
                    + "WHERE Account.Username = ? and Account.Role_ID = 4\n"
                    + "UPDATE Patient SET Patient.Note = ?,\n"
                    + "Patient.BackgroundDisease = ?,\n"
                    + "Patient.[Blood Type] = ? FROM Patient INNER JOIN Account\n"
                    + "ON Patient.ID = Account.Username\n"
                    + "WHERE Account.Username = ? and Account.Role_ID = 4";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, fullName);
            stm.setBoolean(2, gender);
            stm.setString(3, phone);
            stm.setString(4, address);
            stm.setString(5, email);
            stm.setDate(6, dateofbirth);
            stm.setString(7, nation);
            stm.setString(8, userName);
            stm.setString(9, note);
            stm.setBoolean(10, backgroundDesease);
            stm.setString(11, bloodType);
            stm.setString(12, userName);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateDoctorAndnurse(String fullName, Boolean gender, String phone, String address,
            String email, Date dateofbirth, String nation, String userName,
            int role, String leveleducation, String hospital) {
        try {
            String sql = "UPDATE Account_Details\n"
                    + "SET Account_Details.Fullname = ?, \n"
                    + "Account_Details.Gender = ?, \n"
                    + "Account_Details.Phone = ?,\n"
                    + "Account_Details.Address = ?,\n"
                    + "Account_Details.Email = ?,\n"
                    + "Account_Details.DateOfBirth = ?,\n"
                    + "Account_Details.Nation = ?\n"
                    + "FROM Account_Details INNER JOIN Account\n"
                    + "ON Account.Username = Account_Details.ID\n"
                    + "WHERE Account.Username = ? and Account.Role_ID = ?\n"
                    + "UPDATE Medical_Staff\n"
                    + "SET Medical_Staff.[Level of education] = ?,\n"
                    + "Medical_Staff.Hospital = ? FROM Medical_Staff INNER JOIN Account\n"
                    + "ON Account.Username = Medical_Staff.ID \n"
                    + "WHERE Account.Username = ? and Account.Role_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, fullName);
            stm.setBoolean(2, gender);
            stm.setString(3, phone);
            stm.setString(4, address);
            stm.setString(5, email);
            stm.setDate(6, dateofbirth);
            stm.setString(7, nation);
            stm.setString(8, userName);
            stm.setInt(9, role);
            stm.setString(10, leveleducation);
            stm.setString(11, hospital);
            stm.setString(12, userName);
            stm.setInt(13, role);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Boolean addUser(String ID, String fullName, Boolean gender, String phone, String address, int role,
            String email, String nation, String password, Date dateofbirth, String leveleducation, String hospital) {
        try {
            String sql = "insert into Account values (?, ?, ?) \n"
                    + "insert into Account_Details values \n"
                    + "(?, ?, ?, ?, ?, ?, ?, ?)\n"
                    + "insert into Medical_Staff values (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, ID);
            stm.setString(2, password);
            stm.setInt(3, role);
            stm.setString(4, ID);
            stm.setString(5, fullName);
            stm.setBoolean(6, gender);
            stm.setString(7, phone);
            stm.setString(8, address);
            stm.setString(9, email);
            stm.setString(10, nation);
            stm.setDate(11, dateofbirth);
            stm.setString(12, ID);
            stm.setString(13, leveleducation);
            stm.setString(14, hospital);
            stm.executeQuery();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public ArrayList<Account> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Account get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
