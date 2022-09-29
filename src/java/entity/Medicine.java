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
public class Medicine {

    private int shipmentID;
    private String name;
    private int quantity;
    private String description;
    private String dateOfManufacture;
    private String expirationDate;
    private int medicineTypeID;

    public Medicine() {
    }

    public Medicine(int shipmentID, String name, int quantity, String description, String dateOfManufacture, String expirationDate, int medicineTypeID) {
        this.shipmentID = shipmentID;
        this.name = name;
        this.quantity = quantity;
        this.description = description;
        this.dateOfManufacture = dateOfManufacture;
        this.expirationDate = expirationDate;
        this.medicineTypeID = medicineTypeID;
    }

    public int getShipmentID() {
        return shipmentID;
    }

    public void setShipmentID(int shipmentID) {
        this.shipmentID = shipmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(String dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getMedicineTypeID() {
        return medicineTypeID;
    }

    public void setMedicineTypeID(int medicineTypeID) {
        this.medicineTypeID = medicineTypeID;
    }

}
