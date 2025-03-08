package bookShop.service;

import bookShop.bean.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private List<Order> orderList = new ArrayList<>();

    @Override
    public void saveOrder(Order order) {
        this.orderList.add(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return this.orderList;
    }

    @Override
    public void deleteOrder(Order order) {
        this.orderList.removeIf(o -> o.equals(order));
    }
}
