/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Calendar {
    private int id;
    private Date dayTo;
    private Date dayFrom;

    public Calendar() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDayTo() {
        return dayTo;
    }

    public void setDayTo(Date dayTo) {
        this.dayTo = dayTo;
    }

    public Date getDayFrom() {
        return dayFrom;
    }

    public void setDayFrom(Date dayFrom) {
        this.dayFrom = dayFrom;
    }
}
