package ie.bookShop.bean;

import ie.bookShop.utils.BookUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Order {
    private Integer orderId;
    private Integer customerId;
    private LocalDate orderDate;
    private String  orderStatus;
    private BigDecimal totalPrice;

    private List<OrderItem> orderItemList;

    public Order(){}
    public Order(Integer orderId, Integer customerId, LocalDate orderDate, String orderStatus,
                 BigDecimal totalPrice, List<OrderItem> orderItemList) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.totalPrice = totalPrice;
        this.orderItemList = orderItemList;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        //return the date on format: ddMMyyyy
        return BookUtils.formatDate(orderDate,"ddMMyyyy");
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public BigDecimal getTotalPrice() {
        //TotalPrice is calculated by summing up the items the customer chose.
        if(!orderItemList.isEmpty()){
            return  orderItemList.stream().filter(total -> null != total.getUnitPrice())
                    .map(OrderItem::getUnitPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

        }
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }

    // Determines whether two objects are equal or not based on content they hold.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order order)) return false;

        if (getOrderId() != null ? !getOrderId().equals(order.getOrderId()) : order.getOrderId() != null) return false;
        if (getCustomerId() != null ? !getCustomerId().equals(order.getCustomerId()) : order.getCustomerId() != null)
            return false;
        if (getOrderDate() != null ? !getOrderDate().equals(order.getOrderDate()) : order.getOrderDate() != null)
            return false;
        if (getOrderStatus() != null ? !getOrderStatus().equals(order.getOrderStatus()) : order.getOrderStatus() != null)
            return false;
        if (getTotalPrice() != null ? !getTotalPrice().equals(order.getTotalPrice()) : order.getTotalPrice() != null)
            return false;
        return getOrderItemList() != null ? getOrderItemList().equals(order.getOrderItemList()) : order.getOrderItemList() == null;
    }

    //Generates a hash code  for an object.
    @Override
    public int hashCode() {
        int result = getOrderId() != null ? getOrderId().hashCode() : 0;
        result = 31 * result + (getCustomerId() != null ? getCustomerId().hashCode() : 0);
        result = 31 * result + (getOrderDate() != null ? getOrderDate().hashCode() : 0);
        result = 31 * result + (getOrderStatus() != null ? getOrderStatus().hashCode() : 0);
        result = 31 * result + (getTotalPrice() != null ? getTotalPrice().hashCode() : 0);
        result = 31 * result + (getOrderItemList() != null ? getOrderItemList().hashCode() : 0);
        return result;
    }

    //Provides the result of printing the object in human-readable way, not a memory address
    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", orderDate=" + orderDate +
                ", orderStatus='" + orderStatus + '\'' +
                ", totalPrice=" + totalPrice +
                ", orderItemList=" + orderItemList +
                '}';
    }
}
