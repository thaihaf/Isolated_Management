/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Vaccine;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class VaccineDBContext extends DBContext<Vaccine> {

    @Override
    public ArrayList<Vaccine> list() {
        ArrayList<Vaccine> list = new ArrayList<>();
        try {
            String sql = "select VaccineID,VaccineName from Vaccine";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Vaccine vaccine = new Vaccine();
                vaccine.setId(rs.getInt("VaccineID"));
                vaccine.setVaccineName(rs.getString("VaccineName"));
                list.add(vaccine);
            }
        } catch (SQLException ex) {
            Logger.getLogger(VaccineDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Vaccine get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Vaccine model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Vaccine model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Vaccine model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
