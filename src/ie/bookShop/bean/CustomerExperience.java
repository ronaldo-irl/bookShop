package ie.bookShop.bean;

import java.time.LocalDate;

public record CustomerExperience(Integer customerId, String reviewDescription, int rate, LocalDate reviewDate) { }
