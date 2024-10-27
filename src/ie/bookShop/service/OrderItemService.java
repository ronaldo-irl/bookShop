package ie.bookShop.service;

import ie.bookShop.bean.OrderItem;

import java.util.List;

public interface OrderItemService {

    OrderItem createOrderItem(OrderItem orderItem);
    OrderItem getOrderItemById(Long id);
    List<OrderItem> getAllOrderItems();
    List<OrderItem> getOrderItemsByOrderId(Long orderId);
    OrderItem updateOrderItem(OrderItem orderItem);
    void deleteOrderItem(Long id);

}
