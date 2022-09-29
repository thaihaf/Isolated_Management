/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author hapro
 */
public class PrescriptionMedicine {
    private int prescriptionId;
    private int medicineId;
    private int quantity;

    public PrescriptionMedicine() {
    }

    public PrescriptionMedicine(int prescriptionId, int medicineId, int quantity) {
        this.prescriptionId = prescriptionId;
        this.medicineId = medicineId;
        this.quantity = quantity;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PrescriptionMedicine{" + "prescriptionId=" + prescriptionId + ", medicineId=" + medicineId + ", quantity=" + quantity + '}';
    }
    
    
}
