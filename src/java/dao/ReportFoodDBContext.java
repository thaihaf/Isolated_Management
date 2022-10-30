/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.FoodReport;
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
public class ReportFoodDBContext extends DBContext<FoodReport> {
    ArrayList<FoodReport> frs = new ArrayList<>();
    public ArrayList<FoodReport> showAllFoodReport() {
        try {
            String sql = "select * from FoodReport";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                FoodReport fr = new FoodReport();
                fr.setId(rs.getInt("id"));
                fr.setUserName(rs.getString("userName"));
                fr.setTimeReportFood(rs.getTime("timeReportFood"));
                fr.setDateReportFood(rs.getDate("dateReportFood"));
                fr.setReportFood(rs.getString("reportFood"));
                frs.add(fr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportFoodDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return frs;
    }
    
    public void showFoodReportByUsername(String userName) {
        try {
            String sql = "select * from FoodReport inner join Account\n"
                    + "on Account.Username = FoodReport.username\n"
                    + "where Account.Username = ? and Account.Role_ID = 4";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                FoodReport fr = new FoodReport();
                fr.setId(rs.getInt("id"));
                fr.setTimeReportFood(rs.getTime("timeReportFood"));
                fr.setDateReportFood(rs.getDate("dateReportFood"));
                fr.setReportFood(rs.getString("reportFood"));
                Account acc = new Account();
                acc.setUserName(rs.getString("userName"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReportFoodDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<FoodReport> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FoodReport get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(FoodReport model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(FoodReport model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(FoodReport model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
