package edu.hw5.task2;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public final class YearUtils {

    private static final int FRIDAY_THE_13TH = 13;

    private YearUtils() {
    }

    public static List<LocalDate> getAllFridaysThe13thOfYear(int year) {
        if (year <= 0) {
            throw new IllegalArgumentException("year should be > 0");
        }
        List<LocalDate> result = new ArrayList<>();
        LocalDate date = LocalDate.of(year, Month.JANUARY, 1);
        while (date.getYear() == year) {
            date = date.with((TemporalAdjusters.next(DayOfWeek.FRIDAY)));
            if (date.getDayOfMonth() == FRIDAY_THE_13TH) {
                result.add(date);
            }
        }
        return result;
    }

    public static LocalDate getTheClosestNextFridayThe13th(LocalDate dateFrom) {
        if (dateFrom == null) {
            throw new IllegalArgumentException("dateFrom should not be null");
        }
        LocalDate date = dateFrom;
        //пятница 13-ое рано или поздно встретится
        while (true) {
            date = date.with((TemporalAdjusters.next(DayOfWeek.FRIDAY)));
            if (date.getDayOfMonth() == FRIDAY_THE_13TH) {
                return date;
            }
        }
    }
}
