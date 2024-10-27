package ie.bookShop.enums;

public enum Gender {
    MALE,
    FEMALE,
    NOT_PROVIDED;

    public static Gender getGender(String genderString) {
        try {
            return Gender.valueOf(genderString.toUpperCase());
        } catch (IllegalArgumentException e) {
            //In case the user doesn't provide (Male or Female) gender we return NOT_PROVIDED
            return NOT_PROVIDED;
        }
    }
}
