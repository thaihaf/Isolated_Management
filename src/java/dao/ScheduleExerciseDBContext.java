/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Exercise;
import entity.Schedule_Exercise;
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
public class ScheduleExerciseDBContext extends DBContext<Schedule_Exercise> {

    @Override
    public ArrayList<Schedule_Exercise> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Schedule_Exercise get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Schedule_Exercise model) {
        try {
            connection.setAutoCommit(false);
            for (Exercise exercise : model.getExercise()) {
                String sql = "INSERT INTO [Schedule_Exercise]\n"
                        + "           ([Exercise_ID]\n"
                        + "           ,[Schedule_ID])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?)";
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setInt(1, exercise.getId());
                stm.setInt(2, model.getSchedule().getId());
                stm.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleExerciseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ScheduleExerciseDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ScheduleExerciseDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean validate(Schedule_Exercise model) {
        try {
            String sql = "SELECT COUNT(*) AS [Exist]\n"
                    + "  FROM [Schedule_Exercise]\n"
                    + "  WHERE [Schedule_Exercise].[Schedule_ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, model.getSchedule().getId());
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Exist") > 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleExerciseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public void update(Schedule_Exercise model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Schedule_Exercise model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
