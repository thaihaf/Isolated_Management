/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import entity.Schedule;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author Mountain
 */
public class CountArray {

    public static int countOccurenceInSchedule(ArrayList<Schedule> array, LocalDate day, int timeID) {
        int result = 0;
        for (Schedule schedule : array) {
            if (schedule.getDate().equals(day) && schedule.getTime().getId() == timeID) {
                result++;
            }
        }
        return result;
    }
}
