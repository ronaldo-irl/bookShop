package ie.bookShop.service;

import ie.bookShop.bean.OrderItem;

import java.util.List;

public interface OrderItemService {

    void createOrderItem(OrderItem orderItem);
    OrderItem getOrderItemById(Long id);
    List<OrderItem> getAllOrderItems();
}
