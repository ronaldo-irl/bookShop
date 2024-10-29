package ie.bookShop.bean;


import ie.bookShop.enums.Genre;

import java.math.BigDecimal;

public class EBook extends Book {
    private String format;
    private String downloadLink;

    private BigDecimal price;

    public EBook(Integer bookId, String title, String author, String isbn, int publicationYear, Genre genre, String publisher, int numberOfPages, BigDecimal price, String format, String downloadLink) {
        super(bookId, title, author, isbn, publicationYear, genre, publisher, numberOfPages);
        this.format = format;
        this.downloadLink = downloadLink;
        this.price = price;
    }

    public EBook(Integer bookId, String title, String author, String isbn, int publicationYear, Genre genre, String publisher, int numberOfPages, String downloadLink) {
        this(bookId, title, author, isbn, publicationYear, genre, publisher, numberOfPages, BigDecimal.ZERO, "PDF", downloadLink);
    }

    @Override
    public BigDecimal getPrice() {
        return this.price;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EBook eBook)) return false;
        if (!super.equals(o)) return false;

        if (getFormat() != null ? !getFormat().equals(eBook.getFormat()) : eBook.getFormat() != null) return false;
        if (getDownloadLink() != null ? !getDownloadLink().equals(eBook.getDownloadLink()) : eBook.getDownloadLink() != null)
            return false;
        return getPrice() != null ? getPrice().equals(eBook.getPrice()) : eBook.getPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getFormat() != null ? getFormat().hashCode() : 0);
        result = 31 * result + (getDownloadLink() != null ? getDownloadLink().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + ", EBook{" +
                "format='" + format + '\'' +
                ", downloadLink='" + downloadLink + '\'' +
                ", price=" + price +
                '}';
    }
}
