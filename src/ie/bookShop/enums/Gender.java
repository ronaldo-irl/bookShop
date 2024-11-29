package ie.bookShop.enums;

import ie.bookShop.BookShopExceptions.GenderException;

public enum Gender {
    MALE,
    FEMALE,
    NOT_PROVIDED;

    public static Gender getGender(String genderString) throws GenderException {
        try {
            return Gender.valueOf(genderString.toUpperCase());
        } catch (IllegalArgumentException e) {
            //In case the customer doesn't provide (Male or Female) input
            //JVM will throw this an personalized checked exception.
           throw  new GenderException("Invalid Gender Provided!");
        }
    }
}
