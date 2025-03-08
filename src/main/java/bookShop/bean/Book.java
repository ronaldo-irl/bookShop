package bookShop.bean;

import bookShop.enums.Genre;

import java.math.BigDecimal;

public abstract sealed class Book permits EBook, PhysicalBook {
    private Integer bookId;
    private String title;
    private String author;
    private String isbn;
    private int publicationYear;
    private Genre genre;
    private String publisher;
    private int numberOfPages;

    public Book(Integer bookId, String title, String author, String isbn, int publicationYear, Genre genre, String publisher, int numberOfPages) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationYear = publicationYear;
        this.genre = genre;
        this.publisher = publisher;
        this.numberOfPages = numberOfPages;
    }

    public abstract BigDecimal getPrice();

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;

        if (getPublicationYear() != book.getPublicationYear()) return false;
        if (getNumberOfPages() != book.getNumberOfPages()) return false;
        if (getBookId() != null ? !getBookId().equals(book.getBookId()) : book.getBookId() != null) return false;
        if (getTitle() != null ? !getTitle().equals(book.getTitle()) : book.getTitle() != null) return false;
        if (getAuthor() != null ? !getAuthor().equals(book.getAuthor()) : book.getAuthor() != null) return false;
        if (getIsbn() != null ? !getIsbn().equals(book.getIsbn()) : book.getIsbn() != null) return false;
        if (getGenre() != book.getGenre()) return false;
        return getPublisher() != null ? getPublisher().equals(book.getPublisher()) : book.getPublisher() == null;
    }

    @Override
    public int hashCode() {
        int result = getBookId() != null ? getBookId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getAuthor() != null ? getAuthor().hashCode() : 0);
        result = 31 * result + (getIsbn() != null ? getIsbn().hashCode() : 0);
        result = 31 * result + getPublicationYear();
        result = 31 * result + (getGenre() != null ? getGenre().hashCode() : 0);
        result = 31 * result + (getPublisher() != null ? getPublisher().hashCode() : 0);
        result = 31 * result + getNumberOfPages();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationYear=" + publicationYear +
                ", genre=" + genre +
                ", publisher='" + publisher + '\'' +
                ", numberOfPages=" + numberOfPages +
                '}';
    }
}
