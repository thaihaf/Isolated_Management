/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.Calendar;
import entity.Food;
import entity.FoodSchedule;
import entity.FoodSchedule2;
import entity.Room;
import entity.Week;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class FoodScheduleDBContext extends DBContext<FoodSchedule> {

    public ArrayList<FoodSchedule2> fsns = new ArrayList<>();
    public ArrayList<FoodSchedule2> foodScheduleForNurse(String userName) {
        try {
            String sql = "Select Food_Schedule.Date, Food_Schedule.Time, \n"
                    + "Week.dayName, Food.Name, Food.Type, Food.AddedDate,\n"
                    + "Food_Schedule.AsignedUser, Food_Schedule.Meal, \n"
                    + "Food_Schedule.Note, Room.Name, Food.Quantity, \n"
                    + "Food_Schedule.QuantityBringToPatient from Food_Schedule inner join Week\n"
                    + "on Week.id = Food_Schedule.WeekID inner join \n"
                    + "Account on Account.Username = Food_Schedule.AsignedUser\n"
                    + "inner join Room on Room.ID = Food_Schedule.RoomID\n"
                    + "inner join Food on Food.ID = Food_Schedule.Food_ID\n"
                    + "where Account.Username = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                FoodSchedule2 fsn = new FoodSchedule2();
                fsn.setDate(rs.getDate("date"));
                fsn.setTime(rs.getTime("time"));
                fsn.setDayName(rs.getString("dayName"));
                fsn.setFoodName(rs.getString("Name"));
                fsn.setType(rs.getString("type"));
                fsn.setAddedDate(rs.getDate("addedDate"));
                fsn.setUserName(rs.getString("AsignedUser"));
                fsn.setMeal(rs.getString("meal"));
                fsn.setNote(rs.getString("note"));
                fsn.setRoomName(rs.getString("Name"));
                fsn.setQuantityBringToPatient(rs.getInt("quantityBringToPatient"));
                fsn.setQuantity(rs.getInt("quantity"));
                fsns.add(fsn);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fsns;
    }

//    public static void main(String[] args) throws ParseException {
//        FoodScheduleDBContext d = new FoodScheduleDBContext();
//        ArrayList<FoodSchedule2> list = d.foodScheduleForNurse("halh");
//        System.out.println(list.get(0).getFoodName());
//    }

    public void addFoodToSchedule() {
        String sql = "";
    }

    @Override
    public ArrayList<FoodSchedule> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public FoodSchedule get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(FoodSchedule model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(FoodSchedule model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(FoodSchedule model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
