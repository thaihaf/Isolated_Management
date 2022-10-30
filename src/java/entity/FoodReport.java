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
public class FoodReport {
    private int id;
    private String userName;
    private String reportFood;
    private Date dateReportFood;
    private Time timeReportFood;

    public FoodReport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReportFood() {
        return reportFood;
    }

    public void setReportFood(String reportFood) {
        this.reportFood = reportFood;
    }

    public Date getDateReportFood() {
        return dateReportFood;
    }

    public void setDateReportFood(Date dateReportFood) {
        this.dateReportFood = dateReportFood;
    }

    public Time getTimeReportFood() {
        return timeReportFood;
    }

    public void setTimeReportFood(Time timeReportFood) {
        this.timeReportFood = timeReportFood;
    }
}
