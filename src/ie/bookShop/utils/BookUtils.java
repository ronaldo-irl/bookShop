package ie.bookShop.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BookUtils {

    private static int nextId = 1;

    public static int getNextId() {
        return nextId++;
    }

    public static LocalDate formatDate(LocalDate date, String pattern) {
        //return the date on formatt
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(date.format(formatter), formatter);
    }
}
