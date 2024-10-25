package ie.bookShop.bean;

import java.math.BigDecimal;

public record Book (Integer bookId, String title, String author, String isbn, int publicationYear,
                    Genre genre, String publisher,  int numberOfPages, BigDecimal price ){
}
