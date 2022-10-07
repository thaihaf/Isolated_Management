/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class InjectionReport {

    int id;
    private AccountDetail patientAccount;
    private AccountDetail personInject;
    private Vaccine vaccine;
    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AccountDetail getPatientAccount() {
        return patientAccount;
    }

    public void setPatientAccount(AccountDetail patientAccount) {
        this.patientAccount = patientAccount;
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AccountDetail getPersonInject() {
        return personInject;
    }

    public void setPersonInject(AccountDetail personInject) {
        this.personInject = personInject;
    }

}
