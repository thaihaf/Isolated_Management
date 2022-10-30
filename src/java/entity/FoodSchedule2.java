/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author Admin
 */
public class FoodSchedule2 {
    private String roomName;
    private String foodName;
    private String type;
    private Date addedDate;
    private String userName;
    private String meal;
    private String note;
    private String dayName;
    private Date date;
    private Time time;
    private Date dayFrom;
    private Date dayTo;
    private int quantityBringToPatient;
    private int quantity;

    public FoodSchedule2() {
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDayFrom() {
        return dayFrom;
    }

    public void setDayFrom(Date dayFrom) {
        this.dayFrom = dayFrom;
    }

    public Date getDayTo() {
        return dayTo;
    }

    public void setDayTo(Date dayTo) {
        this.dayTo = dayTo;
    }

    public int getQuantityBringToPatient() {
        return quantityBringToPatient;
    }

    public void setQuantityBringToPatient(int quantityBringToPatient) {
        this.quantityBringToPatient = quantityBringToPatient;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
