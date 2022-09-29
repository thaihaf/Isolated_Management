/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author hapro
 */
public class FormatDate {

    private static final SimpleDateFormat sdf
            = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");
    private static final SimpleDateFormat sdfDatetime
            = new SimpleDateFormat("EEEE, dd-MM-yyyy HH:mm:ss");
    
    private static final SimpleDateFormat sdf2
            = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat sdfDate
            = new SimpleDateFormat("dd-MM-yyyy");

    public String formatDatetime(String dateString) {
        try {
            Date date = sdf.parse(dateString);

            return sdfDatetime.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public String formatDate(String dateString) {
        try {
            Date date = sdf2.parse(dateString);

            return sdfDate.format(date);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }
}
