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
    private Schedule schedule;

    public ArrayList<Exercise> getExercise() {
        return exercise;
    }

    public void setExercise(ArrayList<Exercise> exercise) {
        this.exercise = exercise;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
