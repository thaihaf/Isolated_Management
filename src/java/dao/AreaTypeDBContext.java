/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.AreaType;
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
public class AreaTypeDBContext extends DBContext<AreaType> {

    @Override
    public ArrayList<AreaType> list() {
        ArrayList<AreaType> areaTypes = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[AreaType]\n"
                    + "  FROM [AreaType]";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                AreaType at = new AreaType();
                at.setId(rs.getInt("ID"));
                at.setType(rs.getNString("AreaType"));
                areaTypes.add(at);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AreaTypeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return areaTypes;
    }

    @Override
    public AreaType get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(AreaType model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(AreaType model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(AreaType model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
