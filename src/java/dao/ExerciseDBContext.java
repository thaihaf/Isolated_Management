/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Exercise;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Mountain
 */
public class ExerciseDBContext extends DBContext<Exercise> {

    @Override
    public ArrayList<Exercise> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Exercise get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Exercise model) {
        try {
            String sql = "INSERT INTO [Exercise]\n"
                    + "           ([ExerciseName]\n"
                    + "           ,[Note]\n"
                    + "           ,[Exercise_Source_Type]\n"
                    + "           ,[Exercise_Source])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, model.getName());
            stm.setString(2, model.getNote());
            stm.setInt(3, model.getSourceType().getId());
            stm.setString(4, model.getSource());
            stm.executeUpdate();
        } catch (SQLException ex) {
        }
    }

    @Override
    public void update(Exercise model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Exercise model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
