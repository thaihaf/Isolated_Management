/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

public class Patient {

    private String note;
    private String bloodType;
    private Account account;
    private AccountDetail accDetail;
    private boolean backgroudDisea;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountDetail getAccDetail() {
        return accDetail;
    }

    public void setAccDetail(AccountDetail accDetail) {
        this.accDetail = accDetail;
    }

    public boolean isBackgroudDisea() {
        return backgroudDisea;
    }

    public void setBackgroudDisea(boolean backgroudDisea) {
        this.backgroudDisea = backgroudDisea;
    }

}
