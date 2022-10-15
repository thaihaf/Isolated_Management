/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.Role;
import java.sql.Date;
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
public class AccountDBContext extends DBContext<Account> {

    public Account getAccount(String UserName, String Password) {
        try {
            String sql = "SELECT [Username]\n"
                    + "      ,[Password]\n"
                    + "      ,[Role_ID]\n"
                    + "	  ,[Role].[Role]\n"
                    + "  FROM [Account]\n"
                    + "  INNER JOIN [Role] ON [Account].[Role_ID] = [Role].[ID]\n"
                    + "  WHERE [Account].[Username] = ? AND [Account].[Password] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, UserName);
            stm.setString(2, Password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setUserName(rs.getString("Username"));
                account.setPassword(rs.getString("Password"));
                Role role = new Role();
                role.setId(rs.getInt("Role_ID"));
                role.setRole(rs.getString("Role"));
                account.setRole(role);
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    //
    public String checkUser(String userName) {
//        Account account = new Account();
        String sql = "  select * from Account where Username = ?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
//                Role role = new Role();
//                account = new Account(rs.getString(1), rs.getString(2), role);
                return "This username is existed";
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Regist successfully";
    }

    public Boolean Register(String ID, String fullName, Boolean gender, String phone, String address,
            String email, String nation, String password, Date dateofbirth) {
        String sql = "insert into Account values (?, ?, 4)\n"
                + "insert into Account_Details values\n"
                + "(?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, ID);
            stm.setString(2, password);
            stm.setString(3, ID);
            stm.setString(4, fullName);
            stm.setBoolean(5, gender);
            stm.setString(6, phone);
            stm.setString(7, address);
            stm.setString(8, email);
            stm.setString(9, nation);
            stm.setDate(10, dateofbirth);
//            stm.setString(1, ID);
//            stm.setString(2, fullName);
//            stm.setBoolean(3, gender);
//            stm.setString(4, phone);
//            stm.setString(5, address);
//            stm.setString(6, email);
//            stm.setString(7, nation);
//            stm.setString(8, ID);
//            stm.setString(9, password);
            stm.executeQuery();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            String sql = "Update Account set Password = ? where UserName = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getPassword());
            stm.setString(2, model.getUserName());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Account model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public Account getNotificationReceiver(int roomID) {
        try {
            String sql = "SELECT [Username]\n"
                    + "  FROM [Account]\n"
                    + "  INNER JOIN [Room] ON [Account].[Username] = [Room].[DoctorManage]\n"
                    + "  WHERE [Room].[ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, roomID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUserName(rs.getString("Username"));
                return a;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public ArrayList<Account> listInRoom(int roomID) {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT [Username]\n"
                    + "  FROM [Account]\n"
                    + "  INNER JOIN [Patient] ON [Account].[Username] = [Patient].[ID]\n"
                    + "  INNER JOIN [Room] ON [Patient].[Room_ID] = [Room].[ID]\n"
                    + "  WHERE [Room].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, roomID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUserName(rs.getString("Username"));
                accounts.add(a);
            }
        } catch (SQLException ex) {
        }
        return accounts;
    }

    public ArrayList<Account> listByRole(int roleID) {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = "SELECT [Username]\n"
                    + "  FROM [Account]\n"
                    + "  WHERE [Account].[Role_ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, roleID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUserName(rs.getString("Username"));
                accounts.add(a);
            }
        } catch (SQLException ex) {
        }
        return accounts;
    }
}
