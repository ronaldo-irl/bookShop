package bookShop.service;

import bookShop.bean.OrderItem;

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
}
