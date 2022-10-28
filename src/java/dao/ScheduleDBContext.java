/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.AccountDetail;
import entity.Room;
import entity.Schedule;
import entity.Schedule_Time;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[AssignedUser]\n"
                    + "      ,[Room_ID]\n"
                    + "      ,[Date]\n"
                    + "      ,[Time]\n"
                    + "      ,[Description]\n"
                    + "  FROM [Schedule]\n"
                    + "  WHERE [Schedule].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setId(rs.getInt("ID"));
                AccountDetail ad = new AccountDetail();
                Account a = new Account();
                a.setUserName(rs.getString("AssignedUser"));
                ad.setAccount(a);
                s.setAssignedUser(ad);
                Room r = new Room();
                r.setId(rs.getInt("Room_ID"));
                s.setRoom(r);
                s.setDate(rs.getDate("Date").toLocalDate());
                Schedule_Time st = new Schedule_Time();
                st.setId(rs.getInt("Time"));
                s.setTime(st);
                s.setDescription(rs.getNString("Description"));
                return s;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Schedule model) {
        try {
            String sql = "INSERT INTO [Schedule]\n"
                    + "           ([AssignedUser]\n"
                    + "           ,[Room_ID]\n"
                    + "           ,[Date]\n"
                    + "           ,[Time]\n"
                    + "           ,[Description])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, model.getAssignedUser().getAccount().getUserName());
            stm.setInt(2, model.getRoom().getId());
            stm.setObject(3, model.getDate());
            stm.setObject(4, model.getTime().getId());
            stm.setString(5, model.getDescription());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Schedule model) {
        try {
            String sql = "UPDATE [Schedule]\n"
                    + "   SET [AssignedUser] = ?\n"
                    + "      ,[Room_ID] = ?\n"
                    + "      ,[Date] = ?\n"
                    + "      ,[Time] = ?\n"
                    + "      ,[Description] = ?\n"
                    + " WHERE [Schedule].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, model.getAssignedUser().getAccount().getUserName());
            stm.setInt(2, model.getRoom().getId());
            stm.setObject(3, model.getDate());
            stm.setInt(4, model.getTime().getId());
            stm.setString(5, model.getDescription());
            stm.setInt(6, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Schedule model) {
        try {
            String sql = "DELETE FROM [Schedule]\n"
                    + "      WHERE [Schedule].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validate(Schedule model) {
        try {
            String sql = "SELECT [ID]\n"
                    + "  FROM [Schedule]\n"
                    + "  WHERE [Schedule].[AssignedUser] = ?\n"
                    + "  AND [Schedule].[Date] = ?\n"
                    + "  AND [Schedule].[Time] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, model.getAssignedUser().getAccount().getUserName());
            stm.setObject(2, model.getDate());
            stm.setInt(3, model.getTime().getId());
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

    public ArrayList<Schedule> listByAccount(String account, LocalDate firstDay, LocalDate lastDay) {
        ArrayList<Schedule> scheds = new ArrayList<>();
        try {
            String sql = "SELECT [Schedule].[ID]\n"
                    + "      ,[Account_Details].[Fullname] AS [UserName]\n"
                    + "	  ,[Room].[Name] AS [RoomName]\n"
                    + "      ,[Date]\n"
                    + "	  ,[Schedule_Time].[ID] AS [TimeID]\n"
                    + "      ,[Description]\n"
                    + "  FROM [Schedule]\n"
                    + "  INNER JOIN [Account_Details] ON [Schedule].[AssignedUser] = [Account_Details].[ID]\n"
                    + "  INNER JOIN [Room] ON [Schedule].[Room_ID] = [Room].[ID]\n"
                    + "  INNER JOIN [Schedule_Time] ON [Schedule].[Time] = [Schedule_Time].[ID]\n"
                    + "  WHERE ([Schedule].[Date] BETWEEN CAST(? AS DATE) AND CAST(? AS DATE))\n"
                    + "  AND ([Schedule].[AssignedUser] = ?)";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setObject(1, firstDay);
            stm.setObject(2, lastDay);
            stm.setString(3, account);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Schedule s = new Schedule();
                s.setId(rs.getInt("ID"));
                AccountDetail ad = new AccountDetail();
                ad.setFullName(rs.getNString("UserName"));
                s.setAssignedUser(ad);
                Room r = new Room();
                r.setName(rs.getNString("RoomName"));
                s.setRoom(r);
                s.setDate(rs.getDate("Date").toLocalDate());
                Schedule_Time st = new Schedule_Time();
                st.setId(rs.getInt("TimeID"));
                s.setTime(st);
                s.setDescription(rs.getNString("Description"));
                scheds.add(s);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return scheds;
    }

}
