package ie.bookShop.service;

import ie.bookShop.bean.OrderItem;

import java.util.List;

public class OrderItemServiceImpl implements OrderItemService{


    @Override
    public OrderItem createOrderItem(OrderItem orderItem) {
        this.getAllOrderItems().add(orderItem);
        return null;
    }

    @Override
    public OrderItem getOrderItemById(Long id) {
        return null;
    }

    @Override
    public List<OrderItem> getAllOrderItems() {
        return null;
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
