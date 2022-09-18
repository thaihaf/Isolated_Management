/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Timestamp;

/**
 *
 * @author Mountain
 */
public class TestResult {

    private int id;
    private AccountDetail patientAccount;
    private boolean result;
    private TestType testType;
    private Timestamp testTime;
    private AccountDetail personTest;
    private boolean status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public TestType getTestType() {
        return testType;
    }

    public void setTestType(TestType testType) {
        this.testType = testType;
    }

    public AccountDetail getPatientAccount() {
        return patientAccount;
    }

    public void setPatientAccount(AccountDetail patientAccount) {
        this.patientAccount = patientAccount;
    }

    public Timestamp getTestTime() {
        return testTime;
    }

    public void setTestTime(Timestamp testTime) {
        this.testTime = testTime;
    }

    public AccountDetail getPersonTest() {
        return personTest;
    }

    public void setPersonTest(AccountDetail personTest) {
        this.personTest = personTest;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
