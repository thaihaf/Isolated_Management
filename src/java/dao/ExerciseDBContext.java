/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Exercise;
import entity.Exercise_Source_Type;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        try {
            String sql = "SELECT [Exercise].[ID]\n"
                    + "      ,[ExerciseName]\n"
                    + "      ,[Note]\n"
                    + "      ,[Exercise_Source_Type] AS [SourceType]\n"
                    + "      ,[Exercise_Source]\n"
                    + "  FROM [Exercise]\n"
                    + "  INNER JOIN [Exercise_Source_Type] ON [Exercise].[Exercise_Source_Type] = [Exercise_Source_Type].[ID]\n"
                    + "  WHERE [Exercise].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Exercise e = new Exercise();
                e.setId(rs.getInt("ID"));
                e.setName(rs.getNString("ExerciseName"));
                e.setNote(rs.getNString("Note"));
                Exercise_Source_Type est = new Exercise_Source_Type();
                est.setId(rs.getInt("SourceType"));
                e.setSourceType(est);
                e.setSource(rs.getNString("Exercise_Source"));
                return e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExerciseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
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
            Logger.getLogger(ExerciseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Exercise model) {
        try {
            String sql = "UPDATE [Exercise]\n"
                    + "   SET [ExerciseName] = ?\n"
                    + "      ,[Note] = ?\n"
                    + "      ,[Exercise_Source_Type] = ?\n"
                    + "      ,[Exercise_Source] = ?\n"
                    + " WHERE [Exercise].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, model.getName());
            stm.setString(2, model.getNote());
            stm.setInt(3, model.getSourceType().getId());
            stm.setString(4, model.getSource());
            stm.setInt(5, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExerciseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Exercise model) {
        try {
            String sql = "DELETE FROM [Exercise]\n"
                    + "      WHERE [Exercise].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, model.getId());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ExerciseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int count(String searchCriteria) {
        try {
            String sql = "SELECT COUNT(*) AS [Total]\n"
                    + "  FROM [Exercise]\n"
                    + "  WHERE 1 = 1\n";
            int count = 1;
            HashMap<Integer, Object> set = new HashMap<>();
            if (searchCriteria != null) {
                sql += "AND ([Exercise].[ExerciseName] = ? OR [Exercise].[Note] = ?)";
                for (int i = 0; i < 2; i++) {
                    set.put(count + i, searchCriteria);
                }
            }
            PreparedStatement stm = connection.prepareCall(sql);
            for (Map.Entry<Integer, Object> entry : set.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExerciseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public ArrayList<Exercise> listWithSearchAndPaginate(String searchCriteria, int pageIndex, int pageSize) {
        ArrayList<Exercise> exercises = new ArrayList<>();
        try {
            String sql = "SELECT [Exercise].[ID]\n"
                    + "      ,[ExerciseName]\n"
                    + "      ,[Note]\n"
                    + "      ,[Exercise_Source_Type].[ID] AS [SourceType]\n"
                    + "	  ,[Exercise_Source_Type].[Type]\n"
                    + "      ,[Exercise_Source]\n"
                    + "  FROM [Exercise]\n"
                    + "  INNER JOIN [Exercise_Source_Type] ON [Exercise].[Exercise_Source_Type] = [Exercise_Source_Type].[ID]\n"
                    + "  WHERE 1 = 1\n";
            int count = 1;
            HashMap<Integer, Object> set = new HashMap<>();
            if (searchCriteria != null) {
                sql += "AND ([Exercise].[ExerciseName] LIKE '%' + ? + '%' OR [Exercise].[Note] LIKE '%' + ? + '%')\n";
                for (int i = 0; i < 2; i++) {
                    set.put(count, searchCriteria);
                    count++;
                }
            }
            sql += "ORDER BY [ID] ASC\n"
                    + "  OFFSET (? - 1) * ? ROWS\n"
                    + "  FETCH NEXT ? ROWS ONLY";
            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    set.put(count, pageIndex);
                } else {
                    set.put(count, pageSize);
                }
                count++;
            }
            PreparedStatement stm = connection.prepareCall(sql);
            for (Map.Entry<Integer, Object> entry : set.entrySet()) {
                Integer key = entry.getKey();
                Object val = entry.getValue();
                stm.setObject(key, val);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Exercise e = new Exercise();
                e.setId(rs.getInt("ID"));
                e.setName(rs.getNString("ExerciseName"));
                e.setNote(rs.getNString("Note"));
                Exercise_Source_Type est = new Exercise_Source_Type();
                est.setId(rs.getInt("SourceType"));
                est.setType(rs.getNString("Type"));
                e.setSourceType(est);
                e.setSource(rs.getNString("Exercise_Source"));
                exercises.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ExerciseDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return exercises;
    }

}
