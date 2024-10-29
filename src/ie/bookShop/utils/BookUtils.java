package ie.bookShop.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public final class BookUtils {

    private static int nextId = 1;

    public static int getNextId() {
        return nextId++;
    }

    //format date from format (yyyy-MM-dd) to (dd/MM/yyyy)
    //and handles in case a wrong format is typed in
    public static String formatDate(String dateToFormat){

        try{
            DateTimeFormatter inputFormatter  = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            LocalDate localDate = LocalDate.parse(dateToFormat, inputFormatter);
            String formattedDate = localDate.format(outputFormatter);

            return formattedDate;
        }catch (DateTimeParseException e){
            System.out.println("Invalid date: "+dateToFormat);
        }
        return null;
    }
}
