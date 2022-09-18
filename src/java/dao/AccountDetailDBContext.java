/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.AccountDetail;
import entity.Role;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class AccountDetailDBContext extends DBContext<AccountDetail> {

    public AccountDetail getEmail(String user) {
        try {
            String sql = "SELECT [Email]\n"
                    + "  FROM [Account]\n"
                    + "  INNER JOIN [Account_Details] ON [Account].[Username] = [Account_Details].[ID]\n"
                    + "  WHERE [Account].[Username] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, user);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                AccountDetail a = new AccountDetail();
                a.setEmail(rs.getNString("Email"));
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public AccountDetail get(String user) {
        try {
            String sql = "SELECT [Username]\n"
                    + "      ,[Role].[ID]\n"
                    + "      ,[Role].[Role]\n"
                    + "	  ,[Fullname]\n"
                    + "	  ,[Gender]\n"
                    + "	  ,[Phone]\n"
                    + "	  ,[Address]\n"
                    + "	  ,[Email]\n"
                    + "	  ,[Nation]\n"
                    + "  FROM [Account]\n"
                    + "  INNER JOIN [Role] ON [Account].[Role_ID] = [Role].[ID]\n"
                    + "  INNER JOIN [Account_Details] ON [Account].[Username] = [Account_Details].[ID]\n"
                    + "  WHERE [Account].[Username] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, user);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                AccountDetail acc = new AccountDetail();
                Account a = new Account();
                Role r = new Role();
                r.setId(rs.getInt("ID"));
                r.setRole(rs.getNString("Role"));
                a.setRole(r);
                a.setUserName(rs.getString("Username"));
                acc.setAccount(a);
                acc.setFullName(rs.getNString("Fullname"));
                acc.setGender(rs.getBoolean("Gender"));
                acc.setPhone(rs.getNString("Phone"));
                acc.setAddress(rs.getNString("Address"));
                acc.setEmail(rs.getNString("Email"));
                acc.setNation(rs.getNString("Nation"));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void UpdatePass(AccountDetail acc) {
        try {
            String sql = "UPDATE [dbo].[Account]\n"
                    + "   SET [Password] = ?\n"
                    + "   FROM [Account] INNER JOIN [Account_Details] ON [Account].[Username] = [Account_Details].[ID]\n"
                    + " WHERE [Account_Details].[Email] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, acc.getAccount().getPassword());
            stm.setNString(2, acc.getEmail());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void insert(AccountDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(AccountDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(AccountDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<AccountDetail> list() {
        ArrayList<AccountDetail> accounts = new ArrayList<>();
        try {
            String sql = "SELECT [Username]\n"
                    + "	  ,[Role_ID]\n"
                    + "	  ,[Role].[Role]\n"
                    + "	  ,[Fullname]\n"
                    + "	  ,[Gender]\n"
                    + "	  ,[Phone]\n"
                    + "	  ,[Address]\n"
                    + "  FROM [Account]\n"
                    + "  INNER JOIN [Role] ON [Account].[Role_ID] = [Role].[ID]\n"
                    + "  INNER JOIN [Account_Details] ON [Account].[Username] = [Account_Details].[ID]";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                AccountDetail acc = new AccountDetail();
                Account a = new Account();
                a.setUserName(rs.getString("Username"));
                Role r = new Role();
                r.setId(rs.getInt("Role_ID"));
                r.setRole(rs.getNString("Role"));
                a.setRole(r);
                acc.setAccount(a);
                acc.setFullName(rs.getNString("Fullname"));
                acc.setGender(rs.getBoolean("Gender"));
                acc.setPhone(rs.getNString("Phone"));
                acc.setAddress(rs.getNString("Address"));
                accounts.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    public ArrayList<AccountDetail> listByCriteria(Integer role, String criteria) {
        ArrayList<AccountDetail> accounts = new ArrayList<>();
        try {
            String sql = "SELECT [Username]\n"
                    + "	  ,[Role].[ID]\n"
                    + "      ,[Fullname]\n"
                    + "      ,[Gender]\n"
                    + "      ,[Phone]\n"
                    + "      ,[Address]\n"
                    + "	  ,[Role]\n"
                    + "  FROM [Account_Details]\n"
                    + "  INNER JOIN [Account] ON [Account_Details].[ID] = [Account].[Username]\n"
                    + "  INNER JOIN [Role] ON [Account].[Role_ID] = [Role].[ID]\n"
                    + "  WHERE (1 = 1)";
            int count = 0;
            HashMap<Integer, Object> set = new HashMap<>();
            if (role != null) {
                count++;
                sql += "\nAND [Account].[Role_ID] = ?";
                set.put(count, role);
            }
            if (criteria != null) {
                sql += "\nAND ([Account_Details].Fullname LIKE '%' + ? + '%' OR [Account_Details].[Phone] LIKE '%' + ? + '%' OR [Account_Details].[Email] LIKE '%' + ? + '%')";
                for (int i = 0; i < 3; i++) {
                    count++;
                    set.put(count, criteria);
                }
            }
            PreparedStatement stm = connection.prepareCall(sql);
            for (Map.Entry<Integer, Object> entry : set.entrySet()) {
                Integer key = entry.getKey();
                Object value = entry.getValue();
                stm.setObject(key, value);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                AccountDetail acc = new AccountDetail();
                Account a = new Account();
                Role r = new Role();
                r.setId(rs.getInt("ID"));
                r.setRole(rs.getNString("Role"));
                a.setRole(r);
                a.setUserName(rs.getString("Username"));
                acc.setAccount(a);
                acc.setFullName(rs.getNString("Fullname"));
                acc.setGender(rs.getBoolean("Gender"));
                acc.setPhone(rs.getNString("Phone"));
                acc.setAddress(rs.getNString("Address"));
                accounts.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts.size() > 0 ? accounts : null;
    }

    public ArrayList<AccountDetail> listDoctorAndNurse() {
        ArrayList<AccountDetail> accounts = new ArrayList<>();
        try {
            String sql = "SELECT [Username]\n"
                    + "      ,[Fullname]\n"
                    + "  FROM [Account_Details]\n"
                    + "  INNER JOIN [Account] ON [Account_Details].[ID] = [Account].[Username]\n"
                    + "  INNER JOIN [Role] ON [Account].[Role_ID] = [Role].[ID]\n"
                    + "  WHERE [Role].[ID] = 2 OR [Role].[ID] = 3";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                AccountDetail a = new AccountDetail();
                Account acc = new Account();
                acc.setUserName(rs.getString("Username"));
                a.setAccount(acc);
                a.setFullName(rs.getNString("Fullname"));
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    @Override
    public AccountDetail get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
