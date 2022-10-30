/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.AccountDetail;
import entity.Exercise;
import entity.Schedule;
import entity.Schedule_Exercise;
import entity.Schedule_Time;
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

    public ArrayList<Schedule_Exercise> listAllScheduleByRoom(int roomID) {
        ArrayList<Schedule_Exercise> sce = new ArrayList<>();
        try {
            String sql = "SELECT [Schedule_ID]\n"
                    + ",[Schedule].[Date]\n"
                    + ",[Schedule_Time].[Name] AS [Time]\n"
                    + ",[Account_Details].[Fullname] AS [AssignedUser]\n"
                    + "  FROM [Schedule_Exercise]\n"
                    + "  INNER JOIN [Schedule] ON [Schedule_Exercise].[Schedule_ID] = [Schedule].[ID]\n"
                    + "  INNER JOIN [Schedule_Time] ON [Schedule].[Time] = [Schedule_Time].[ID]\n"
                    + "  INNER JOIN [Account_Details] ON [Schedule].[AssignedUser] = [Account_Details].[ID]\n"
                    + "  WHERE [Schedule].[Room_ID] = ?\n"
                    + "  GROUP BY [Schedule_Exercise].[Schedule_ID],[Schedule].[Date],[Schedule_Time].[Name],[Account_Details].[Fullname]";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, roomID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule_Exercise se = new Schedule_Exercise();
                Schedule s = new Schedule();
                s.setId(rs.getInt("Schedule_ID"));
                s.setDate(rs.getDate("Date").toLocalDate());
                Schedule_Time st = new Schedule_Time();
                st.setName(rs.getNString("Time"));
                s.setTime(st);
                AccountDetail ad = new AccountDetail();
                ad.setFullName(rs.getNString("AssignedUser"));
                s.setAssignedUser(ad);
                se.setSchedule(s);
                sce.add(se);
            }
        } catch (SQLException ex) {
        }
        return sce;
    }

    public Schedule_Exercise getExerciseById(int id) {
        try {
            String sql = "SELECT [Exercise_ID]\n"
                    + "  FROM [Schedule_Exercise]\n"
                    + "  WHERE [Schedule_ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Schedule_Exercise se = new Schedule_Exercise();
            while (rs.next()) {
                Exercise e = new Exercise();
                e.setId(rs.getInt("Exercise_ID"));
                se.getExercise().add(e);
            }
            return se;
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleExerciseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Schedule_Exercise get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Schedule_Exercise model) {

    }

    public int insertReturnRow(Schedule_Exercise model) {
        int count = 0;
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
                count += stm.executeUpdate();
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
        return count;
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
