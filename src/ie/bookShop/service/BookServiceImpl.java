package ie.bookShop.service;

import ie.bookShop.bean.Book;
import ie.bookShop.bean.EBook;
import ie.bookShop.bean.PhysicalBook;
import ie.bookShop.enums.Genre;
import ie.bookShop.utils.BookUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookServiceImpl implements BookService {

    private List<Book> bookList = new ArrayList<>();

    @Override
    public void createBook() {
        //create 10 default books
        Book book1 = new PhysicalBook(BookUtils.getNextId(),"Learn Java with Projects", "Sean Kennedy", "1837637180", 2023, Genre.SOFTWARE_DEVELOPMENT, "Packt Publishing", 598, new BigDecimal("51.95"), "Paperback");

        Book book2 = new PhysicalBook(BookUtils.getNextId(), "Introduction to Machine Learning with Python", "Andreas Mueller", "1449369413", 2016, Genre.SOFTWARE_DEVELOPMENT, "O'Reilly", 598, new BigDecimal("37.00"), "Hardcover");

        Book book3 = new EBook(BookUtils.getNextId(), "The Alchemist", "Paulo Coelho", "0060886907", 1988, Genre.FICTION, "HarperOne", 192, new BigDecimal("13.99"), "PDF", "http://tuslibrary.ie");

        Book book4 = new PhysicalBook(BookUtils.getNextId(), "The Hitchhiker's Guide to the Galaxy", "Douglas Adams", "0553382218", 1979, Genre.FICTION, "Del Rey", 224, new BigDecimal("14.99"), "Paperback");

        Book book5 = new PhysicalBook(BookUtils.getNextId(), "Pride and Prejudice", "Jane Austen", "0553353352", 1813, Genre.NOVEL, "Penguin Classics", 432, new BigDecimal("12.99"), "Hardcover");

        Book book6 = new EBook(BookUtils.getNextId(), "The Lord of the Rings: The Fellowship of the Ring", "J. R. R. Tolkien", "0395489000", 1954, Genre.FANTASY, "Allen & Unwin", 423, new BigDecimal("19.99"), "PDF", "http://tuslibrary.ie");

        Book book7 = new EBook(BookUtils.getNextId(), "Sapiens: A Brief History of Humankind", "Yuval Noah Harari", "0060889224", 2011, Genre.HISTORY, "Vintage Books", 448, new BigDecimal("24.99"), "PDF", "http://tuslibrary.ie");

        Book book8 = new EBook(BookUtils.getNextId(), "The Art of Computer Programming, Volume 1: Fundamental Algorithms", "Donald Knuth", "0201896834", 1968, Genre.SOFTWARE_DEVELOPMENT, "Addison-Wesley", 657, new BigDecimal("59.99"), "EPUB", "http://tuslibrary.ie");

        Book book9 = new PhysicalBook(BookUtils.getNextId(), "Deep Learning", "Ian Goodfellow, Yoshua Bengio, Aaron Courville", "0262035642", 2016, Genre.SOFTWARE_DEVELOPMENT, "MIT Press", 1136, new BigDecimal("79.99"), "Hardcover");

        Book book10 = new PhysicalBook(BookUtils.getNextId(), "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", "0132350882", 2008, Genre.SOFTWARE_DEVELOPMENT, "Prentice Hall", 464, new BigDecimal("44.99"), "Paperback");

        Book book11 = new EBook(BookUtils.getNextId(), "Free Guide to Software Development", "Ronaldo Martins", "0201896834", 2024, Genre.SOFTWARE_DEVELOPMENT, "TUS", 32, "http://tuslibrary.ie");

        this.bookList.add(book1);
        this.bookList.add(book2);
        this.bookList.add(book3);
        this.bookList.add(book4);
        this.bookList.add(book5);
        this.bookList.add(book6);
        this.bookList.add(book7);
        this.bookList.add(book8);
        this.bookList.add(book9);
        this.bookList.add(book10);
        this.bookList.add(book11);
    }

    @Override
    public Book getBook(String name) {
        return this.bookList.stream().filter(bookName -> bookName.getTitle().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Book getBook(Integer bookId) {
        return  this.bookList.stream().filter(book -> book.getBookId().equals(bookId))
                                .findFirst()
                                .orElse(null);
    }

    @Override
    public List<Book> bookBasket() {
        return this.bookList;
    }

    @Override
    public List<Book> bookBasket(String name) {
        return this.bookList.stream().filter(book -> book.getTitle().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }
}
