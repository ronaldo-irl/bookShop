package ie.bookShop.service;

import ie.bookShop.bean.OrderItem;

import java.util.ArrayList;
import java.util.List;

public class OrderItemServiceImpl implements OrderItemService{

    private List<OrderItem> orderItemList = new ArrayList<>();

    @Override
    public void createOrderItem(OrderItem orderItem) {
            this.orderItemList.add(orderItem);
    }
    @Override
    public OrderItem getOrderItemById(Long id) {
        return null;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return this.orderItemList;
    }

    @Override
    public List<OrderItem> getOrderItemsByOrderId(Long orderId) {
        return null;
    }

    @Override
    public OrderItem updateOrderItem(OrderItem orderItem) {
        return null;
    }

    @Override
    public void deleteOrderItem(Long id) {

    }
}
