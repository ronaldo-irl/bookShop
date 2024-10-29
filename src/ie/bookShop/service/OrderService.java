package ie.bookShop.service;

import ie.bookShop.bean.Order;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public interface OrderService {

    static AtomicInteger orderSequence = new AtomicInteger(1);
    static Integer generateOrderId() {
        return orderSequence.getAndIncrement();
    }
    void save(Order order);

    Order getOrder(Integer orderId);

    Order updateOrder(Order order);

    List<Order> getAllOrders();

    void delete(Integer orderId);

}
