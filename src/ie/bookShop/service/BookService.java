package ie.bookShop.service;

import ie.bookShop.bean.Book;

import java.util.List;

public interface BookService {

    void createBook();

    Book getBook(String name);

    Book getBook(Integer bookId);

    List<Book> bookBasket();

    List<Book> bookBasket(String name);
}
