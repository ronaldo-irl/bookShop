package ie.bookShop.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class BookUtils {

    private static int nextId = 1;

    public static int getNextId() {
        return nextId++;
    }

}
