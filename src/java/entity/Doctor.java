/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class Doctor {

    private String DoctorID;
    private String DoctorName;
    private boolean gender;
    private String Phone;
    private String Adress;
    private Account account;

    public Doctor() {
    }

    public Doctor(String DoctorID, String DoctorName, boolean gender, String Phone, String Adress, Account account) {
        this.DoctorID = DoctorID;
        this.DoctorName = DoctorName;
        this.gender = gender;
        this.Phone = Phone;
        this.Adress = Adress;
        this.account = account;
    }

    public String getDoctorID() {
        return DoctorID;
    }

    public void setDoctorID(String DoctorID) {
        this.DoctorID = DoctorID;
    }

    public String getDoctorName() {
        return DoctorName;
    }

    public void setDoctorName(String DoctorName) {
        this.DoctorName = DoctorName;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String Adress) {
        this.Adress = Adress;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
