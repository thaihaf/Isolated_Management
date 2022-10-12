/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.AccountDetail;
import entity.InjectionReport;
import entity.TestResult;
import entity.TestType;
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
public class InjectionDBContext extends DBContext<InjectionReport> {

    @Override
    public ArrayList<InjectionReport> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public InjectionReport get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(InjectionReport model) {
        try {
            String sql = "INSERT INTO [Injection_Report]\n"
                    + "           ([PatientID]\n"
                    + "           ,[VaccineID]\n"
                    + "           ,[Date]\n"
                    + "           ,[PersonInjected])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setString(1, model.getPatientAccount().getAccount().getUserName());
            stm.setInt(2, model.getVaccine().getId());
            stm.setDate(3, model.getDate());
            stm.setString(4, model.getPersonInject().getAccount().getUserName());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InjectionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(InjectionReport model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(InjectionReport model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<InjectionReport> getInjectionResultByID(String username) {
        ArrayList<InjectionReport> lists = new ArrayList<>();
        try {
            String sql = "select PatientID,v.VaccineName,Date,PersonInjected from Injection_Report ir join Vaccine v \n"
                    + "on ir.VaccineID = v.VaccineID\n"
                    + "where PatientID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                InjectionReport ir = new InjectionReport();
                Account acc1 = new Account();
                acc1.setUserName(rs.getString("PatientID"));
                AccountDetail ad = new AccountDetail();
                ad.setAccount(acc1);
                ir.setPatientAccount(ad);
                Vaccine vaccine = new Vaccine();
                vaccine.setVaccineName(rs.getString("VaccineName"));
                ir.setVaccine(vaccine);
                ir.setDate(rs.getDate("Date"));
                Account acc2 = new Account();
                acc2.setUserName(rs.getString("PersonInjected"));
                AccountDetail ad1 = new AccountDetail();
                ad1.setAccount(acc2);
                ir.setPersonInject(ad1);
                lists.add(ir);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lists;
    }

}
