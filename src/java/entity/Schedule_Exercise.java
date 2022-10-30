/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Mountain
 */
public class Schedule_Exercise {

    private ArrayList<Exercise> exercise = new ArrayList<>();
    private AccountDetail patient;
    private LocalDate date;
    private Schedule_Time time;

    public ArrayList<Exercise> getExercise() {
        return exercise;
    }

    public void setExercise(ArrayList<Exercise> exercise) {
        this.exercise = exercise;
    }

    public AccountDetail getPatient() {
        return patient;
    }

    public void setPatient(AccountDetail patient) {
        this.patient = patient;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Schedule_Time getTime() {
        return time;
    }

    public void setTime(Schedule_Time time) {
        this.time = time;
    }

}
