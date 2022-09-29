/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author hapro
 */
public class Prescription {

    private int id;
    private String patientID;
    private String doctorID;
    private String prescriptionDate;
    private String title;
    private String guide;
    private int status;
    private List<Medicine> medicines;
    private List<PrescriptionMedicine> prescriptionMedicines;
    private List<MedicineType> medicineTypes;

    public Prescription() {
    }

    public Prescription(int id, String patientID, String doctorID, String prescriptionDate, String title, String guide, int status, List<Medicine> medicines, List<PrescriptionMedicine> prescriptionMedicines, List<MedicineType> medicineTypes) {
        this.id = id;
        this.patientID = patientID;
        this.doctorID = doctorID;
        this.prescriptionDate = prescriptionDate;
        this.title = title;
        this.guide = guide;
        this.status = status;
        this.medicines = medicines;
        this.prescriptionMedicines = prescriptionMedicines;
        this.medicineTypes = medicineTypes;
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

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public List<PrescriptionMedicine> getPrescriptionMedicines() {
        return prescriptionMedicines;
    }

    public void setPrescriptionMedicines(List<PrescriptionMedicine> prescriptionMedicines) {
        this.prescriptionMedicines = prescriptionMedicines;
    }

    public List<MedicineType> getMedicineTypes() {
        return medicineTypes;
    }

    public void setMedicineTypes(List<MedicineType> medicineTypes) {
        this.medicineTypes = medicineTypes;
    }

    
}
