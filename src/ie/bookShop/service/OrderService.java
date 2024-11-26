package ie.bookShop.service;

import ie.bookShop.bean.Order;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order);

    List<Order> getAllOrders();

    void deleteOrder(Order order);
}
