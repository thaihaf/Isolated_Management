/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package utils;

import java.time.LocalDate;

/**
 *
 * @author Mountain
 */
public class ParseDateTimeLocal {

    public LocalDate parseDate(String dateTimeLocal) {
        LocalDate ldt = LocalDate.parse(dateTimeLocal);
        return ldt;
    }
}
