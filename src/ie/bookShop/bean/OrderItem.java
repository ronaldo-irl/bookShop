package ie.bookShop.bean;

import java.math.BigDecimal;

public class OrderItem {

    private static Integer itemId;
    private Integer bookId;
    private int quantity;
    private BigDecimal unitPrice;

    public OrderItem(Integer itemId, Integer bookId, int quantity, BigDecimal unitPrice) {
        this.itemId = itemId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public static Integer getItemId() {
        return itemId++;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
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
        if (getBookId() != null ? !getBookId().equals(orderItem.getBookId()) : orderItem.getBookId() != null)
            return false;
        return getUnitPrice() != null ? getUnitPrice().equals(orderItem.getUnitPrice()) : orderItem.getUnitPrice() == null;
    }

    @Override
    public int hashCode() {
        int result = getItemId() != null ? getItemId().hashCode() : 0;
        result = 31 * result + (getBookId() != null ? getBookId().hashCode() : 0);
        result = 31 * result + getQuantity();
        result = 31 * result + (getUnitPrice() != null ? getUnitPrice().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderId=" + itemId +
                ", bookId=" + bookId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
