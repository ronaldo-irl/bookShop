package ie.bookShop.bean;

import ie.bookShop.enums.Genre;

import java.math.BigDecimal;

public class PhysicalBook extends Book{

    private String format;
    private BigDecimal price;

    public PhysicalBook(Integer bookId, String title, String author, String isbn, int publicationYear, Genre genre, String publisher, int numberOfPages, BigDecimal price, String format) {
        super(bookId, title, author, isbn, publicationYear, genre, publisher, numberOfPages);
        this.format = format;
        this.price = price;
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PhysicalBook that)) return false;
        if (!super.equals(o)) return false;

        if (getFormat() != null ? !getFormat().equals(that.getFormat()) : that.getFormat() != null) return false;
        return getPrice() != null ? getPrice().equals(that.getPrice()) : that.getPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getFormat() != null ? getFormat().hashCode() : 0);
        result = 31 * result + (getPrice() != null ? getPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + ", PhysicalBook{" +
                "format='" + format + '\'' +
                ", price=" + price +
                '}';
    }
}
