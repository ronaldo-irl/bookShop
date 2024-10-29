package ie.bookShop.service;

import ie.bookShop.bean.Order;

import java.util.List;

public interface OrderService {

    void save(Order order);

    Order getOrder(Integer orderId);

    Order updateOrder(Order order);

    List<Order> getAllOrders();

    void delete(Integer orderId);

}
