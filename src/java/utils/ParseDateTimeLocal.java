/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 *
 * @author Mountain
 */
public class ParseDateTimeLocal {

    public LocalDate parseDate(String dateTimeLocal) {
        LocalDateTime ldt = LocalDateTime.parse(dateTimeLocal);
        return ldt.toLocalDate();
    }

    public LocalTime parseTime(String dateTimeLocal) {
        LocalDateTime ldt = LocalDateTime.parse(dateTimeLocal);
        return ldt.toLocalTime();
    }
}
