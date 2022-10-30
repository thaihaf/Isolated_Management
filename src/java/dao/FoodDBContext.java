/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Food;
import entity.Schedule;
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
public class FoodDBContext extends DBContext<Food> {

    public ArrayList<Food> FoodList() {
        ArrayList<Food> foods = new ArrayList<>();
        try {
            String sql = "Select Food.ID, Food.[Name], Food.[Type], Food.AddedDate, \n"
                    + "Food.SourcesOfSupply, Food.Quantity, Food.DateOfManufacture, \n"
                    + "Food.Expiry From Food";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setId(rs.getInt("id"));
                food.setName(rs.getString("name"));
                food.setType(rs.getString("type"));
                food.setAddedDate(rs.getDate("addedDate"));
                food.setSourcesOfSupply(rs.getString("sourcesOfSupply"));
                food.setQuantity(rs.getInt("quantity"));
                food.setDateOfManufacture(rs.getDate("dateOfManufacture"));
                food.setExpiry(rs.getDate("expiry"));
                foods.add(food);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return foods;
    }

    public Boolean addFood(String name, String type, Date addedDate, String sourcesOfSupply,
            int quantity, Date dateOfManufacture, Date expiry) {
        try {
            String sql = "Insert into Food values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, type);
            stm.setDate(3, addedDate);
            stm.setString(4, sourcesOfSupply);
            stm.setInt(5, quantity);
            stm.setDate(6, dateOfManufacture);
            stm.setDate(7, expiry);
            stm.executeQuery();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FoodDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Boolean deleteFood(String id) {
        try {
            String sql = "delete from Food where ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FoodDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Boolean updateFood(String name, String type, String addedDate,
            String sourcesOfSupply, String quantity, String dateOfManufacture,
            String expiry, String id) {
        try {
            String sql = "update Food set Name = ?,\n"
                    + "Type = ?, AddedDate = ?, Quantity = ?, \n"
                    + "SourcesOfSupply = ?, DateOfManufacture = ?, \n"
                    + "Expiry = ? where ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setString(2, type);
            stm.setString(3, addedDate);
            stm.setString(4, quantity);
            stm.setString(5, sourcesOfSupply);
            stm.setString(6, dateOfManufacture);
            stm.setString(7, expiry);
            stm.setString(8, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(FoodDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Food loadFoodById(String id) {
        try {
            String sql = "Select * From Food where Food.ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Food food = new Food();
//                food.setId(rs.getInt("id"));
//                food.setName(rs.getString("name"));
//                food.setType(rs.getString("type"));
//                food.setAddedDate(rs.getDate("addedDate"));
                food.setId(rs.getInt("id"));
                food.setName(rs.getString("name"));
                food.setType(rs.getString("type"));
                food.setAddedDate(rs.getDate("addedDate"));
                food.setSourcesOfSupply(rs.getString("sourcesOfSupply"));
                food.setQuantity(rs.getInt("quantity"));
                food.setDateOfManufacture(rs.getDate("dateOfManufacture"));
                food.setExpiry(rs.getDate("expiry"));
                return food;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

//    public ArrayList<Schedule> scheduleForPatient(Date day) {
//        ArrayList<Schedule> schedules = new ArrayList<>();
//        try {
//            String sql = "select FoodMenu.day, FoodMenu.dayOfMonth, FoodMenu.meal,\n"
//                    + "Food.AddedDate, Food.Name, Food.Type,\n"
//                    + "Week.dayFrom, Week.dayTo from FoodMenu\n"
//                    + "inner join Food on FoodMenu.foodID = Food.ID\n"
//                    + "inner join Week on Week.id = FoodMenu.weekID\n"
//                    + "where FoodMenu.day = ?";
//            PreparedStatement stm = connection.prepareStatement(sql);
//            stm.setDate(1, day);
//            ResultSet rs = stm.executeQuery();
//            while (rs.next()) {
//                FoodMenu fm = new FoodMenu();
//                fm.setDay(rs.getDate("day"));
//                foodmenus.add(fm);
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(FoodDBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return foodmenus;
//    }
    @Override
    public ArrayList<Food> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Food get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Food model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Food model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Food model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
