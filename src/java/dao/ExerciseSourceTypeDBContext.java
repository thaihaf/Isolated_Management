/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Exercise_Source_Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mountain
 */
public class ExerciseSourceTypeDBContext extends DBContext<Exercise_Source_Type> {

    @Override
    public ArrayList<Exercise_Source_Type> list() {
        ArrayList<Exercise_Source_Type> exSourceType = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[Type]\n"
                    + "  FROM [Exercise_Source_Type]";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Exercise_Source_Type est = new Exercise_Source_Type();
                est.setId(rs.getInt("ID"));
                est.setType(rs.getNString("Type"));
                exSourceType.add(est);
            }
        } catch (SQLException ex) {
        }
        return exSourceType;
    }

    @Override
    public Exercise_Source_Type get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Exercise_Source_Type model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Exercise_Source_Type model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Exercise_Source_Type model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
