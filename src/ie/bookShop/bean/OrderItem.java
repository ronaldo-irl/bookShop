package ie.bookShop.bean;

import java.math.BigDecimal;
import java.util.Objects;

public class OrderItem {

    private Integer itemId;
    private Book book;
    private int quantity;
    private BigDecimal unitPrice;

    public OrderItem(Integer itemId, Book book, int quantity, BigDecimal unitPrice) {
        this.itemId = itemId;
        this.book = book;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public OrderItem(OrderItem item) {
        this.itemId = item.itemId;
        this.book = item.book;
        this.quantity = item.quantity;
        this.unitPrice = item.unitPrice;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book bookId) {
        this.book = book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderItem orderItem)) return false;

        if (getQuantity() != orderItem.getQuantity()) return false;
        if (getItemId() != null ? !getItemId().equals(orderItem.getItemId()) : orderItem.getItemId() != null)
            return false;
        if (getBook() != null ? !getBook().equals(orderItem.getBook()) : orderItem.getBook() != null) return false;
        return getUnitPrice() != null ? getUnitPrice().equals(orderItem.getUnitPrice()) : orderItem.getUnitPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getItemId() != null ? getItemId().hashCode() : 0;
        result = 31 * result + (getBook() != null ? getBook().hashCode() : 0);
        result = 31 * result + getQuantity();
        result = 31 * result + (getUnitPrice() != null ? getUnitPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "itemId=" + itemId +
                ", book=" + book +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
