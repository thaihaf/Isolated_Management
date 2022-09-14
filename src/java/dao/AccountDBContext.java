/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.Account;

/**
 *
 * @author Admin
 */
public class AccountDBContext extends DBContext<Account> {

    public Account getAccount(String UserName, String Password, String AccountType) {
        try {
            String sql = "select * from Account\n"
                    + "where UserName = ? and Password = ?\n"
                    + "and AccountType = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, UserName);
            stm.setString(2, Password);
            stm.setString(3, AccountType);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account account = new Account();
                account.setAccountID(rs.getString("AccountID"));
                account.setUserName(rs.getString("UserName"));
                account.setPassword(rs.getString("Password"));
                account.setEmail(rs.getString("Email"));
                account.setAccountType(rs.getString("AccountType"));
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account get(String user) {
        try {
            String sql = "SELECT [AccountID]\n"
                    + "      ,[UserName]\n"
                    + "      ,[Password]\n"
                    + "      ,[Email]\n"
                    + "      ,[AccountType]\n"
                    + "  FROM [Account]\n"
                    + "  WHERE [Account].[Email] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setNString(1, user);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setAccountID(rs.getNString("AccountID"));
                a.setUserName(rs.getNString("UserName"));
                a.setPassword(rs.getNString("Password"));
                a.setEmail(rs.getNString("Email"));
                a.setAccountType(rs.getNString("AccountType"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updatePass(Account acc) {
        try {
            String sql = "UPDATE [Account]\n"
                    + "   SET [Password] = ?\n"
                    + " WHERE [Account].[Email] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setNString(1, acc.getPassword());
            stm.setNString(2, acc.getEmail());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
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
