/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.threeten.extra.YearWeek;

/**
 *
 * @author Mountain
 */
public class DaysOfWeek {

    public Map<Integer, String> getDayInWeekOfYear(int year, Locale locale) {
        Map<Integer, String> dates = new HashMap<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
        int weeksInYears = YearWeek.of(year, 1).is53WeekYear() ? 53 : 52;
        for (int i = 1; i <= weeksInYears; i++) {
            LocalDate beginDay = getFirstDayOfWeek(year, i, locale);
            LocalDate endDay = getLastDayOfWeek(year, i, locale);
            dates.put(i, beginDay.format(formatter) + " to " + endDay.format(formatter));
        }
        return dates;
    }

    public LocalDate getFirstDayOfWeek(int year, int weekNumber, Locale locale) {
        return LocalDate
                .of(year, 2, 1)
                .with(WeekFields.of(locale).getFirstDayOfWeek())
                .with(WeekFields.of(locale).weekOfWeekBasedYear(), weekNumber);
    }

    public LocalDate getLastDayOfWeek(int year, int weekNumber, Locale locale) {
        return getFirstDayOfWeek(year, weekNumber, locale).plusDays(6);
    }

    public int getCurrentWeekNumber(Locale locale) {
        LocalDate date = LocalDate.now();
        TemporalField weekOfYear = WeekFields.of(locale).weekOfWeekBasedYear();
        return date.get(weekOfYear);// tuan thu may cua nam
    }

    public List<String> getDaysInWeek(int year, int weekNumber, Locale locale) {
        List<String> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM");
        LocalDate firstDate = getFirstDayOfWeek(year, weekNumber, locale);
        for (int i = 0; i < 7; i++) {
            dates.add(firstDate.plusDays(i).format(formatter));
        }
        return dates;
    }
}
