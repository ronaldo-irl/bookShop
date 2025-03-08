package bookShop.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocalizationUtil {
    private static Locale locale = Locale.getDefault();

    public static void setLocale(Locale newLocale) {
        locale = newLocale;
    }

    public static String getMessage(String key, Object... args) {
        //returns the message on locale ("language chosen by user on this app")
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        return java.text.MessageFormat.format(bundle.getString(key), args);
    }
}
