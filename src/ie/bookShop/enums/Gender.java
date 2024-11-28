package ie.bookShop.enums;

public enum Gender {
    MALE,
    FEMALE,
    NOT_PROVIDED;

    public static Gender getGender(String genderString) {
        try {
            return Gender.valueOf(genderString.toUpperCase());
        } catch (IllegalArgumentException e) {
            //In case the customer doesn't provide (Male or Female) input
            //JVM will throw this an unchecked exception. Then we set the Gender to NOT_PROVIDED
            return NOT_PROVIDED;
        }
    }
}
