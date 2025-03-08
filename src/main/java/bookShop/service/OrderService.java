package bookShop.service;

import bookShop.bean.Order;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order);

    List<Order> getAllOrders();

    void deleteOrder(Order order);
}
