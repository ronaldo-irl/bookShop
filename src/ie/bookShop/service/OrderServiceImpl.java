package ie.bookShop.service;

import ie.bookShop.bean.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private List<Order> orderList = new ArrayList<>();

    @Override
    public void save(Order order) {
        this.orderList.add(order);
    }

    @Override
    public Order getOrder(Integer orderId) {
        return null;
    }

    @Override
    public Order updateOrder(Order order) {
        return order;
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderList;
    }

    @Override
    public void delete(Integer orderId) {
        List<Order>  filteredOrderList = this.orderList.stream().filter(o -> !o.getOrderId().equals(orderId)).collect(Collectors.toList());
        this.orderList = filteredOrderList;
    }
}
