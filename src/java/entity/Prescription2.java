/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author hapro
 */
public class Prescription2 {
    private int id;
    private String patientID;
    private String doctorID;
    private String prescriptionDate;
    private String title;
    private String guide;
    private int status;

    public Prescription2() {
    }

    public Prescription2(int id, String patientID, String doctorID, String prescriptionDate, String title, String guide, int status) {
        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.prescriptionDate = prescriptionDate;
        this.title = title;
        this.guide = guide;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }

    public String getDoctorID() {
        return doctorID;
    }

    public void setDoctorID(String doctorID) {
        this.doctorID = doctorID;
    }

    public String getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(String prescriptionDate) {
        this.prescriptionDate = prescriptionDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGuide() {
        return guide;
    }

    public void setGuide(String guide) {
        this.guide = guide;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
    
}
