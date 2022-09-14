/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.doctor;

import dao.DBContext;
import entity.Account;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DoctorDBContext extends DBContext{
    public void update(Account acc) {
        try {
            String sql = "";
            PreparedStatement stm = connection.prepareStatement(sql);
       
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DoctorDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
