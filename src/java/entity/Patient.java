/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Patient {

    private Account account;
    private Room room;
    private String note;
    private boolean backgroundDisease;
    private String bloodType;
    private AccountDetail accDetail;
    private Bed bed;

    public Bed getBed() {
        return bed;
    }

    public void setBed(Bed bed) {
        this.bed = bed;
    }
    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isBackgroundDisease() {
        return backgroundDisease;
    }

    public void setBackgroundDisease(boolean backgroundDisease) {
        this.backgroundDisease = backgroundDisease;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public AccountDetail getAccDetail() {
        return accDetail;
    }

    public void setAccDetail(AccountDetail accDetail) {
        this.accDetail = accDetail;
    }

}
