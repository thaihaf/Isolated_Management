/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author hapro
 */
public class PrescriptionMedicine2 {

    private int prescriptionId;
    private int quantity;
    private Medicine2 medicine;

    public PrescriptionMedicine2() {
    }

    public PrescriptionMedicine2(int prescriptionId, int quantity, Medicine2 medicine) {
        this.prescriptionId = prescriptionId;
        this.quantity = quantity;
        this.medicine = medicine;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Medicine2 getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine2 medicine) {
        this.medicine = medicine;
    }
    
    
}
