/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Account;
import entity.AccountDetail;
import entity.TestResult;
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
public class TestResultDBContext extends DBContext<TestResult> {
    
    @Override
    public ArrayList<TestResult> list() {
        ArrayList<TestResult> testResult = new ArrayList<>();
        try {
            String sql = "SELECT [Test_Result].[ID]\n"
                    + "      ,[Patient_ID]\n"
                    + "	  ,[ad1].[Fullname] AS PatientFullname\n"
                    + "      ,[Result]\n"
                    + "      ,[TestType_ID]\n"
                    + "	  ,[Test_Type].[Type]\n"
                    + "      ,[TestTime]\n"
                    + "      ,[Person_Test]\n"
                    + "	  ,[ad2].[Fullname] AS DoctorFullname\n"
                    + "      ,[Status]\n"
                    + "  FROM [Test_Result]\n"
                    + "  INNER JOIN [Test_Type] ON [Test_Result].[TestType_ID] = [Test_Type].[ID]\n"
                    + "  INNER JOIN [Account_Details] ad1 ON [Test_Result].[Patient_ID] = ad1.[ID]\n"
                    + "  INNER JOIN [Account_Details] ad2 ON [Test_Result].[Person_Test] = ad2.[ID]";
            PreparedStatement stm = connection.prepareCall(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TestResult tr = new TestResult();
                AccountDetail patient = new AccountDetail();
                Account patientAcc = new Account();
                AccountDetail doc = new AccountDetail();
                Account docAcc = new Account();
                tr.setId(rs.getInt("ID"));
                patientAcc.setUserName(rs.getString("Patient_ID"));
                patient.setAccount(patientAcc);
                patient.setFullName(rs.getNString("PatientFullname"));
                tr.setPatientAccount(patient);
                tr.setResult(rs.getBoolean("Result"));
                TestType tt = new TestType();
                tt.setId(rs.getInt("TestType_ID"));
                tt.setTypeName(rs.getNString("Type"));
                tr.setTestType(tt);
                tr.setTestTime(rs.getTimestamp("TestTime"));
                docAcc.setUserName(rs.getString("Person_Test"));
                doc.setAccount(docAcc);
                doc.setFullName(rs.getNString("DoctorFullname"));
                tr.setPersonTest(doc);
                tr.setStatus(rs.getBoolean("Status"));
                testResult.add(tr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return testResult;
    }
    
    @Override
    public TestResult get(int id) {
        try {
            String sql = "SELECT [Test_Result].[ID]\n"
                    + "      ,[Patient_ID]\n"
                    + "	  ,ad1.Fullname AS PatientFullname\n"
                    + "      ,[Result]\n"
                    + "      ,[TestType_ID]\n"
                    + "      ,[TestTime]\n"
                    + "      ,[Person_Test]\n"
                    + "      ,[Status]\n"
                    + "  FROM [Test_Result]\n"
                    + "  INNER JOIN [Account_Details] ad1 ON [Test_Result].[Patient_ID] = ad1.[ID]\n"
                    + "  WHERE [Test_Result].[ID] = ?";
            PreparedStatement stm = connection.prepareCall(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TestResult tr = new TestResult();
                AccountDetail patient = new AccountDetail();
                TestType tt = new TestType();
                AccountDetail personTest = new AccountDetail();
                Account p = new Account();
                p.setUserName(rs.getString("Patient_ID"));
                patient.setAccount(p);
                patient.setFullName(rs.getNString("PatientFullname"));
                tr.setId(rs.getInt("ID"));
                tr.setPatientAccount(patient);
                tr.setResult(rs.getBoolean("Result"));
                tt.setId(rs.getInt("TestType_ID"));
                tr.setTestType(tt);
                tr.setTestTime(rs.getTimestamp("TestTime"));
                Account d = new Account();
                d.setUserName(rs.getString("Person_Test"));
                personTest.setAccount(d);
                tr.setPersonTest(personTest);
                tr.setStatus(rs.getBoolean("Status"));
                return tr;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public void insert(TestResult model) {
        try {
            String sql = "INSERT INTO [dbo].[Test_Result]\n"
                    + "           ([Patient_ID]\n"
                    + "           ,[Result]\n"
                    + "           ,[TestType_ID]\n"
                    + "           ,[TestTime]\n"
                    + "           ,[Person_Test]\n"
                    + "           ,[Status])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getPatientAccount().getAccount().getUserName());
            stm.setBoolean(2, model.isResult());
            stm.setInt(3, model.getTestType().getId());
            stm.setTimestamp(4, model.getTestTime());
            stm.setString(5, model.getPersonTest().getAccount().getUserName());
            stm.setBoolean(6, true);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<TestResult> getTestResultByID(String username) {
        ArrayList<TestResult> results = new ArrayList<>();
        try {
            String sql = "select tr.ID,tr.Patient_ID,tr.Result,tt.Type,tr.Status,ad.Fullname,tr.TestTime \n"
                    + "from Test_Result tr join Test_Type tt \n"
                    + "on tr.TestType_ID = tt.ID join Account_Details ad on ad.ID = tr.Person_Test\n"
                    + "where tr.Patient_ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                TestResult tr = new TestResult();
                AccountDetail patient = new AccountDetail();
                Account patientAcc = new Account();
                AccountDetail nurse = new AccountDetail();
                patientAcc.setUserName(rs.getString("Patient_ID"));
                patient.setAccount(patientAcc);
                tr.setPatientAccount(patient);
                tr.setResult(rs.getBoolean("Result"));
                TestType tt = new TestType();
                tt.setTypeName(rs.getNString("Type"));
                tr.setTestType(tt);
                tr.setTestTime(rs.getTimestamp("TestTime"));
                nurse.setFullName(rs.getString("Fullname"));
                tr.setId(rs.getInt("ID"));
                tr.setPersonTest(nurse);
                tr.setStatus(rs.getBoolean("Status"));
                results.add(tr);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return results;
    }
    
    @Override
    public void update(TestResult model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void delete(TestResult model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public boolean changeStatus(boolean status, int id) {
        try {
            String sql = "UPDATE [Test_Result]\n"
                    + "   SET \n"
                    + "      [Status] = ?\n"
                    + " WHERE ID = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setBoolean(1, status);
            stm.setInt(2, id);
            stm.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(TestResultDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
