/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.Calendar;
import entity.Food;
import entity.FoodSchedule;
import entity.FoodScheduleNurse;
import entity.Room;
import entity.Week;
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
public class FoodScheduleDBContext extends DBContext<FoodSchedule> {

    public ArrayList<FoodScheduleNurse> fsns = new ArrayList<>();
    public ArrayList<FoodScheduleNurse> foodScheduleForNurse(String userName, String calendarID) {
        try {
            String sql = "Select Food_Schedule.Date, Food_Schedule.Time, \n"
                    + "Week.dayName, Food.Name, Food.Type, Food.AddedDate,\n"
                    + "Food_Schedule.AsignedUser, Food_Schedule.Meal, Food.Quantity, \n"
                    + "Food_Schedule.Note, Room.Name, Calendar.dayFrom, Calendar.dayTo,\n"
                    + "Food_Schedule.QuantityBringToPatient from Food_Schedule inner join Week\n"
                    + "on Week.id = Food_Schedule.WeekID inner join Calendar\n"
                    + "on Calendar.id = Food_Schedule.CalendarID inner join\n"
                    + "Account on Account.Username = Food_Schedule.AsignedUser\n"
                    + "inner join Room on Room.ID = Food_Schedule.RoomID\n"
                    + "inner join Food on Food.ID = Food_Schedule.Food_ID\n"
                    + "where Food_Schedule.Date between Calendar.dayFrom and Calendar.dayTo\n"
                    + "and AsignedUser = ? and Calendar.id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, userName);
            stm.setString(2, calendarID);
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                FoodSchedule fs = new FoodSchedule();
                Food food = new Food();
                Week week = new Week();
                Account acc = new Account();
                Calendar cal = new Calendar();
                Room room = new Room();
                fs.setDate(rs.getDate("date"));
                fs.setTime(rs.getTime("time"));
                week.setDayName(rs.getString("dayName"));
//                food.setFoodName(rs.getString("foodName"));
                food.setType(rs.getString("type"));
                acc.setUserName(rs.getString("userName"));
                fs.setMeal(rs.getString("meal"));
                fs.setNote(rs.getString("note"));
                room.setName(rs.getString("name"));
                cal.setDayFrom(rs.getDate("dayFrom"));
                cal.setDayTo(rs.getDate("dayTo"));
                fs.setQuantityBringToPatient(rs.getInt(""));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FoodScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fsns;
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
