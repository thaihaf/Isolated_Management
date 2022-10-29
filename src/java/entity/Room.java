/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Admin
 */
public class Room {

    private int id;
    private String name;
    private int numOfUse;
    private int numOfBed;
    private Area area;
    private AccountDetail doctorManage;
    private AccountDetail nurseManage;
    private boolean available;
    private int level;

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfBed() {
        return numOfBed;
    }

    public void setNumOfBed(int numOfBed) {
        this.numOfBed = numOfBed;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public AccountDetail getDoctorManage() {
        return doctorManage;
    }

    public void setDoctorManage(AccountDetail doctorManage) {
        this.doctorManage = doctorManage;
    }

    public AccountDetail getNurseManage() {
        return nurseManage;
    }

    public void setNurseManage(AccountDetail nurseManage) {
        this.nurseManage = nurseManage;
    }

    public int getNumOfUse() {
        return numOfUse;
    }

    public void setNumOfUse(int numOfUse) {
        this.numOfUse = numOfUse;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", name=" + name + ", numOfBed=" + numOfBed + ", area=" + area + ", doctorManage=" + doctorManage + ", nurseManage=" + nurseManage + ", toString=" +  '}';
    }
}
