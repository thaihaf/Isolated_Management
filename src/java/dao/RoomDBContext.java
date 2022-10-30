/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.AccountDetail;
import entity.Area;
import entity.AreaType;
import entity.Room;
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
public class RoomDBContext extends DBContext<Room> {

    public boolean updateNumOfUseById(int roomId, int numOfUse) {
        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE [dbo].[Room]\n"
                    + "   SET [NumOfUse] = ?\n"
                    + " WHERE Room.ID = ?\n";

            PreparedStatement stm = connection.prepareCall(sql);

            stm.setInt(1, numOfUse);
            stm.setInt(2, roomId);

            if (stm.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public int getNumOfUseByRoomId(int roomId) {
        try {
            String sql = "SELECT [NumOfUse]\n"
                    + "FROM [dbo].[Room]\n"
                    + "where ID = ?";

            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setInt(1, roomId);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                return rs.getInt("NumOfUse");
            }
        } catch (SQLException ex) {
        }
        return -1;
    }

    @Override
    public ArrayList<Room> list() {
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            String sql = "SELECT [Room].[ID]\n"
                    + "                    ,[Room].[Name]\n"
                    + "                    ,[NumOfBed]\n"
                    + "                    ,[Area_ID]\n"
                    + "                    ,[Area].[Name] AS AreaName\n"
                    + "					,[AreaType].[AreaType]\n"
                    + "                    ,ad1.[Fullname] AS DoctorFullName\n"
                    + "                    ,ad2.[Fullname] AS NurseFullName\n"
                    + "                    ,[Room].[Available]\n"
                    + "                    FROM [Room]\n"
                    + "                    INNER JOIN [Area] ON [Area].[ID] = [Room].[Area_ID]\n"
                    + "                    INNER JOIN [Account_Details] ad1 ON ad1.[ID] = [Room].[DoctorManage]\n"
                    + "                    INNER JOIN [Account_Details] ad2 ON ad2.[ID] = [Room].[NurseManage]\n"
                    + "					INNER JOIN [AreaType] ON [Area].[AreaType] = [AreaType].[ID]";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                Area a = new Area();
                AreaType at = new AreaType();
                AccountDetail doc = new AccountDetail();
                AccountDetail nur = new AccountDetail();
                a.setId(rs.getInt("Area_ID"));
                a.setName(rs.getNString("AreaName"));
                at.setType(rs.getNString("AreaType"));
                a.setAreaType(at);
                r.setArea(a);
                r.setId(rs.getInt("ID"));
                r.setName(rs.getNString("Name"));
                r.setNumOfBed(rs.getInt("NumOfBed"));
                doc.setFullName(rs.getNString("DoctorFullName"));
                r.setDoctorManage(doc);
                nur.setFullName(rs.getNString("NurseFullName"));
                r.setNurseManage(nur);
                r.setAvailable(rs.getBoolean("Available"));
                rooms.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rooms;
    }

    @Override
    public Room get(int id) {
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[Name]\n"
                    + "      ,[NumOfBed]\n"
                    + "      ,[Area_ID]\n"
                    + "      ,[DoctorManage]\n"
                    + "      ,[NurseManage]\n"
                    + "      ,[Available]\n"
                    + "  FROM [Room]\n"
                    + "  WHERE [ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("ID"));
                r.setName(rs.getNString("Name"));
                r.setNumOfBed(rs.getInt("NumOfBed"));
                Area a = new Area();
                a.setId(rs.getInt("Area_ID"));
                r.setArea(a);
                AccountDetail doc = new AccountDetail();
                Account docAcc = new Account();
                docAcc.setUserName(rs.getString("DoctorManage"));
                doc.setAccount(docAcc);
                r.setDoctorManage(doc);
                AccountDetail nur = new AccountDetail();
                Account nurAcc = new Account();
                nurAcc.setUserName(rs.getString("NurseManage"));
                nur.setAccount(nurAcc);
                r.setNurseManage(nur);
                r.setAvailable(rs.getBoolean("Available"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void insert(Room model) {
        try {
            String sql = "INSERT INTO [Room]\n"
                    + "           ([Name]\n"
                    + "           ,[NumOfBed]\n"
                    + "           ,[Area_ID]\n"
                    + "           ,[DoctorManage]\n"
                    + "           ,[NurseManage]\n"
                    + "           ,[Available])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, model.getName());
            stm.setInt(2, model.getNumOfBed());
            stm.setInt(3, model.getArea().getId());
            stm.setString(4, model.getDoctorManage().getAccount().getUserName());
            stm.setString(5, model.getNurseManage().getAccount().getUserName());
            stm.setBoolean(6, model.isAvailable());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Room model) {
        try {
            String sql = "UPDATE [Room]\n"
                    + "   SET [Name] = ?\n"
                    + "      ,[NumOfBed] = ?\n"
                    + "      ,[Area_ID] = ?\n"
                    + "      ,[DoctorManage] = ?\n"
                    + "      ,[NurseManage] = ?\n"
                    + "      ,[Available] = ?\n"
                    + " WHERE [Room].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, model.getName());
            stm.setInt(2, model.getNumOfBed());
            stm.setInt(3, model.getArea().getId());
            stm.setString(4, model.getDoctorManage().getAccount().getUserName());
            stm.setString(5, model.getNurseManage().getAccount().getUserName());
            stm.setBoolean(6, model.isAvailable());
            stm.setInt(7, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Room model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean switchStatus(int id, boolean status) {
        try {
            String sql = "UPDATE [Room]\n"
                    + "   SET [Available] = ?\n"
                    + " WHERE [Room].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(2, id);
            stm.setBoolean(1, status);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RoomDBContext.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean updateStatusWithArea(int areaID, boolean status_to_change) {
        try {
            String sql = "UPDATE [Room]\n"
                    + "   SET [Available] = ?\n"
                    + " WHERE [Room].[Area_ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setBoolean(1, status_to_change);
            stm.setInt(2, areaID);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            return false;
        }
    }

    public ArrayList<Room> roomListByNurseID(String nurseID) {
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[Name]\n"
                    + "  FROM [Room]\n"
                    + "  INNER JOIN [Account] ON [Room].[NurseManage] = [Account].[Username]\n"
                    + "  WHERE [Room].[NurseManage] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, nurseID);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("ID"));
                r.setName(rs.getNString("Name"));
                rooms.add(r);
            }
        } catch (SQLException ex) {
        }
        return rooms;
    }

    public Room getPatientRoom(String id) {
        try {
            String sql = "SELECT [Room].[ID]\n"
                    + "	  ,[Room].[Name]\n"
                    + "  FROM [Room]\n"
                    + "  INNER JOIN [Patient] ON [Room].[ID] = [Patient].[Room_ID]\n"
                    + "  WHERE [Patient].[ID] = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Room r = new Room();
                r.setId(rs.getInt("ID"));
                r.setName(rs.getNString("Name"));
                return r;
            }
        } catch (SQLException ex) {
        }
        return null;
    }
}
