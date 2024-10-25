package ie.bookShop.service;

import ie.bookShop.bean.OrderItem;

import java.util.List;

public interface OrderItemService {

    void saveOrderItem();

    List<OrderItem> getAllItems();

    OrderItem getOrderItem();

    OrderItem updateOrderItem();

    void delete();

}
