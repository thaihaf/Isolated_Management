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
    private int ID;
    private String Name;
    private int NumOfBed;
    private Area area;
    private String DoctorManage;
    private String NurseManage;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getNumOfBed() {
        return NumOfBed;
    }

    public void setNumOfBed(int NumOfBed) {
        this.NumOfBed = NumOfBed;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getDoctorManage() {
        return DoctorManage;
    }

    public void setDoctorManage(String DoctorManage) {
        this.DoctorManage = DoctorManage;
    }

    public String getNurseManage() {
        return NurseManage;
    }

    public void setNurseManage(String NurseManage) {
        this.NurseManage = NurseManage;
    }
}
