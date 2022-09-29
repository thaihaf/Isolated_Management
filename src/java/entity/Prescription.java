/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author hapro
 */
public class Prescription {
    private int id;
    private String patientID;
    private String doctorID;
    private Date prescriptionDate;
    private String title;
    private String guide;
    private int status;
    private Medicine medicine;
    private PrescriptionMedicine prescriptionMedicine;

    public Prescription() {
    }

    public Prescription(int id, String patientID, String doctorID, Date prescriptionDate, String title, String guide, int status, Medicine medicine, PrescriptionMedicine prescriptionMedicine) {
        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.prescriptionDate = prescriptionDate;
        this.title = title;
        this.guide = guide;
        this.status = status;
        this.medicine = medicine;
        this.prescriptionMedicine = prescriptionMedicine;
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

    public Date getPrescriptionDate() {
        return prescriptionDate;
    }

    public void setPrescriptionDate(Date prescriptionDate) {
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

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public PrescriptionMedicine getPrescriptionMedicine() {
        return prescriptionMedicine;
    }

    public void setPrescriptionMedicine(PrescriptionMedicine prescriptionMedicine) {
        this.prescriptionMedicine = prescriptionMedicine;
    }

    @Override
    public String toString() {
        return "Prescription{" + "id=" + id + ", patientID=" + patientID + ", doctorID=" + doctorID + ", prescriptionDate=" + prescriptionDate + ", title=" + title + ", guide=" + guide + ", status=" + status + ", medicine=" + medicine + ", prescriptionMedicine=" + prescriptionMedicine + '}';
    }
    
    
}
