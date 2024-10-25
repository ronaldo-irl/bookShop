package ie.bookShop.service;

import ie.bookShop.bean.Order;

import java.util.List;

public interface OrderService {

    void save();

    Order getOrder();

    Order updateOrder();

    List<Order> getAllOrders();

    void delete();

}
