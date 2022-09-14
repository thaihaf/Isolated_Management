/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

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
            if(rs.next()){
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
