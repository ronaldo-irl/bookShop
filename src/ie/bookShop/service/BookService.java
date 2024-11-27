package ie.bookShop.service;

import ie.bookShop.bean.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    static String formatPrice(BigDecimal price) {
        return "€" + String.format("%.2f", price);
    }

    void createBook();

    Book getBook(String name);

    Book getBook(Integer bookId);

    List<Book> bookBasket();

    List<Book> bookBasket(String name);
}
