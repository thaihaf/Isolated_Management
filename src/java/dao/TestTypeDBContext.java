/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.TestType;
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
public class TestTypeDBContext extends DBContext<TestType> {

    @Override
    public ArrayList<TestType> list() {
        ArrayList<TestType> testTypes = new ArrayList<>();
        try {
            String sql = "SELECT [ID]\n"
                    + "      ,[Type]\n"
                    + "  FROM [Test_Type]";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TestType tt = new TestType();
                tt.setId(rs.getInt("ID"));
                tt.setTypeName(rs.getNString("Type"));
                testTypes.add(tt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return testTypes;
    }

    @Override
    public TestType get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insert(TestType model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean update(TestType model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean delete(TestType model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
