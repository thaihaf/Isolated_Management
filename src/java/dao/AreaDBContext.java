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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mountain
 */
public class AreaDBContext extends DBContext<Area> {

    @Override
    public ArrayList<Area> list() {
        ArrayList<Area> areas = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[Name]\n"
                    + "  FROM [Area]";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Area a = new Area();
                a.setId(rs.getInt("ID"));
                a.setName(rs.getNString("Name"));
                areas.add(a);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AreaDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return areas;
    }

    public ArrayList<Area> listAreaActiveWithRoom(String areaId, String roomId) {
        ArrayList<Area> areas = new ArrayList<>();
        try {
            String sql = "SELECT        \n"
                    + "Area.ID, \n"
                    + "Area.Name, \n"
                    + "Area.Address,\n"
                    + "AreaType.ID as AreaID,  \n"
                    + "AreaType.AreaType, \n"
                    + "Room.ID AS RoomID, \n"
                    + "Room.Name AS RoomName, \n"
                    + "Room.NumOfUse, \n"
                    + "Room.NumOfBed, \n"
                    + "Room.DoctorManage, \n"
                    + "Room.NurseManage, \n"
                    + "Room.Available, \n"
                    + "Room.[Level]\n"
                    + "FROM [Area]\n"
                    + "INNER JOIN [Room] ON [Area].[ID] = [Room].[Area_ID]\n"
                    + "INNER JOIN [AreaType] ON [AreaType].[ID] = [Area].[AreaType]\n"
                    + "where Room.Available = 1\n"
                    + "and Room.NumOfBed > Room.NumOfUse\n";

            if (areaId != null) {
                sql += "and Area.ID = ?\n";
            };
            if (roomId != null) {
                sql += "and Room.Name like ?\n";
            };

            PreparedStatement stm = connection.prepareCall(sql);

            if (areaId != null) {
                stm.setInt(1, Integer.parseInt(areaId));
            }
            if (roomId != null) {
                stm.setInt(1, Integer.parseInt(areaId));
                stm.setString(2, "%" + roomId + "%");
            }

            ResultSet rs = stm.executeQuery();

            Area a = new Area();
            AreaType at = new AreaType();

            ArrayList<Room> rooms = new ArrayList<>();

            while (rs.next()) {
                int tmp = rs.getInt("ID");
                if (a.getId() != tmp) {
                    if (a.getId() != 0) {
                        a.setRooms(rooms);
                        areas.add(a);
                        at = new AreaType();
                        rooms = new ArrayList<>();
                    }
                    a = new Area();
                    a.setId(tmp);
                    a.setName(rs.getString("Name"));
                    a.setAddress(rs.getString("Address"));
                    at.setId(rs.getInt("AreaID"));
                    at.setType(rs.getString("AreaType"));
                    a.setAreaType(at);
                    a.setAvailable(rs.getBoolean("Available"));
                }

                Room r = new Room();

                AccountDetail doctorDetails = new AccountDetail();
                Account accDoctor = new Account();

                AccountDetail nurseDetails = new AccountDetail();
                Account accNurse = new Account();

                accDoctor.setUserName(rs.getString("DoctorManage"));
                accNurse.setUserName(rs.getString("NurseManage"));
                doctorDetails.setAccount(accDoctor);
                nurseDetails.setAccount(accNurse);

                r.setId(rs.getInt("RoomID"));
                r.setName(rs.getNString("RoomName"));
                r.setNumOfUse(rs.getInt("NumOfUse"));
                r.setNumOfBed(rs.getInt("NumOfBed"));
                r.setDoctorManage(doctorDetails);
                r.setNurseManage(nurseDetails);
                r.setAvailable(rs.getBoolean("Available"));
                r.setLevel(rs.getInt("Level"));

                rooms.add(r);

            }
            if (rs.isAfterLast()) {
                a.setRooms(rooms);
                areas.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return areas;
    }

    public ArrayList<Area> listWithRoom() {
        ArrayList<Area> areas = new ArrayList<>();
        try {
            String sql = "SELECT [Area].[ID]\n"
                    + "      ,[Area].[Name]\n"
                    + "      ,[Address]\n"
                    + "	  ,[Area].[Available]\n"
                    + "	  ,[AreaType].[AreaType]\n"
                    + "	  ,[Room].[ID] AS RoomID\n"
                    + "  FROM [Area]\n"
                    + "  INNER JOIN [Room] ON [Area].[ID] = [Room].[Area_ID]\n"
                    + "  INNER JOIN [AreaType] ON [AreaType].[ID] = [Area].[AreaType]";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            Area a = new Area();
            AreaType at = new AreaType();
            ArrayList<Room> rooms = new ArrayList<>();
            while (rs.next()) {
                int tmp = rs.getInt("ID");
                if (a.getId() != tmp) {
                    if (a.getId() != 0) {
                        a.setRooms(rooms);
                        areas.add(a);
                        rooms.clear();
                    }
                    a = new Area();
                    a.setId(tmp);
                    a.setName(rs.getNString("Name"));
                    a.setAddress(rs.getNString("Address"));
                    at.setType(rs.getNString("AreaType"));
                    a.setAreaType(at);
                    a.setAvailable(rs.getBoolean("Available"));
                }
                Room r = new Room();
                r.setId(rs.getInt("RoomID"));
                rooms.add(r);
            }
            if (rs.isAfterLast()) {
                a.setRooms(rooms);
                areas.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return areas;
    }

    public Area getWithRoomList(int id) {
        try {
            String sql = "SELECT [Area].[ID] AS AreaID\n"
                    + "      ,[Area].[Name] AS AreaName\n"
                    + "      ,[Address]\n"
                    + "      ,[AreaType]\n"
                    + "      ,[Area].[Available]\n"
                    + "	  ,[Room].[ID] AS RoomID\n"
                    + "	  ,[Room].[Name] AS RoomName\n"
                    + "  FROM [Area]\n"
                    + "  INNER JOIN [Room] ON [Area].[ID] = [Room].[Area_ID]\n"
                    + "  WHERE [Area].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Area a = null;
            ArrayList<Room> rooms = new ArrayList<>();
            while (rs.next()) {
                if (a == null) {
                    a = new Area();
                    a.setId(rs.getInt("AreaID"));
                    a.setName(rs.getNString("AreaName"));
                    a.setAddress(rs.getNString("Address"));
                    AreaType at = new AreaType();
                    at.setId(rs.getInt("AreaType"));
                    a.setAreaType(at);
                    a.setAvailable(rs.getBoolean("Available"));
                }
                Room r = new Room();
                r.setId(rs.getInt("RoomID"));
                r.setName(rs.getNString("RoomName"));
                rooms.add(r);
            }
            if (rs.isAfterLast()) {
                a.setRooms(rooms);
                return a;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Area get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Area model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_area = "INSERT INTO [Area]\n"
                    + "           ([Name]\n"
                    + "           ,[Address]\n"
                    + "           ,[AreaType]\n"
                    + "           ,[Available])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm_insert_area = connection.prepareStatement(sql_insert_area, Statement.RETURN_GENERATED_KEYS);
            stm_insert_area.setString(1, model.getName());
            stm_insert_area.setString(2, model.getAddress());
            stm_insert_area.setInt(3, model.getAreaType().getId());
            stm_insert_area.setBoolean(4, model.isAvailable());
            stm_insert_area.executeUpdate();
            ResultSet rs = stm_insert_area.getGeneratedKeys();
            if (rs.next()) {
                model.setId(rs.getInt(1));
            } else {
                throw new SQLException("Error in adding to database.");
            }
            for (Room room : model.getRooms()) {
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
                PreparedStatement stm = connection.prepareStatement(sql);
                stm.setString(1, room.getName());
                stm.setInt(2, room.getNumOfBed());
                stm.setInt(3, model.getId());
                stm.setString(4, room.getDoctorManage().getAccount().getUserName());
                stm.setString(5, room.getNurseManage().getAccount().getUserName());
                stm.setBoolean(6, room.isAvailable());
                stm.executeUpdate();
            }
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AreaDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AreaDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                Logger.getLogger(AreaDBContext.class.getName()).log(Level.SEVERE, null, e);
            }
        }
    }

    @Override
    public void update(Area model) {
        try {
            String sql = "UPDATE [Area]\n"
                    + "   SET [Name] = ?\n"
                    + "      ,[Address] = ?\n"
                    + "      ,[AreaType] = ?\n"
                    + "      ,[Available] = ?\n"
                    + " WHERE [Area].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, model.getName());
            stm.setString(2, model.getAddress());
            stm.setInt(3, model.getAreaType().getId());
            stm.setBoolean(4, model.isAvailable());
            stm.setInt(5, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    public boolean switchStatus(int id, boolean status) {
        try {
            String sql = "UPDATE [Area]\n"
                    + "   SET [Available] = ?\n"
                    + " WHERE [Area].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    @Override
    public void delete(Area model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
