/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Schedule;
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
public class ScheduleDBContext extends DBContext<Schedule> {

    @Override
    public ArrayList<Schedule> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Schedule get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Schedule model) {
        try {
            String sql = "INSERT INTO [Schedule]\n"
                    + "           ([AssignedUser]\n"
                    + "           ,[Room_ID]\n"
                    + "           ,[Date]\n"
                    + "           ,[StartTime]\n"
                    + "           ,[Description])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, model.getAssignedUser().getUserName());
            stm.setInt(2, model.getRoom().getId());
            stm.setObject(3, model.getDate());
            stm.setObject(4, model.getStartTime());
            stm.setString(5, model.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Schedule model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Schedule model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean validate(Schedule model) {
        try {
            String sql = "SELECT [ID]\n"
                    + "  FROM [Schedule]\n"
                    + "  WHERE [Schedule].[AssignedUser] = ?\n"
                    + "  AND [Schedule].[Date] = ?\n"
                    + "  AND [Schedule].[StartTime] = CAST(? AS TIME)";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, model.getAssignedUser().getUserName());
            stm.setObject(2, model.getDate());
            stm.setObject(3, model.getStartTime());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return true;
        }
        return false;
    }

}
