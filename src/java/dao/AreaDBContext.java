/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

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

    public ArrayList<Area> listWithRoom() {
        ArrayList<Area> areas = new ArrayList<>();
        try {
            String sql = "SELECT [Area].[ID]\n"
                    + "      ,[Area].[Name]\n"
                    + "      ,[Address]\n"
                    + "	  ,[Area].[Available]\n"
                    + "      ,[AreaType].[ID] AS AreaTypeID\n"
                    + "	  ,[AreaType].[AreaType]\n"
                    + "	  ,[Room].[ID] AS RoomID\n"
                    + "	  ,[Room].[Name] AS RoomName\n"
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
                    at.setId(rs.getInt("AreaTypeID"));
                    at.setType(rs.getNString("AreaType"));
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
                areas.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return areas;
    }

    @Override
    public Area get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Area model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Area model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Area model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
