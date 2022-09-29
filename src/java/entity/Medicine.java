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
    private Date dateOfManufacture;
    private Date expirationDate;
    private int medicineTypeID;

    public Medicine() {
    }

    public Medicine(int shipmentID, String name, int quantity, String description, Date dateOfManufacture, Date expirationDate, int medicineTypeID) {
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

    public Date getDateOfManufacture() {
        return dateOfManufacture;
    }

    public void setDateOfManufacture(Date dateOfManufacture) {
        this.dateOfManufacture = dateOfManufacture;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getMedicineTypeID() {
        return medicineTypeID;
    }

    public void setMedicineTypeID(int medicineTypeID) {
        this.medicineTypeID = medicineTypeID;
    }

    @Override
    public String toString() {
        return "Medicine{" + "shipmentID=" + shipmentID + ", name=" + name + ", quantity=" + quantity + ", description=" + description + ", dateOfManufacture=" + dateOfManufacture + ", expirationDate=" + expirationDate + ", medicineTypeID=" + medicineTypeID + '}';
    }
    
    
}
