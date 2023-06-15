package com.airsets.demo.DAO;

import com.airsets.demo.models.OrderItem;

public interface OrderItemDao {
    boolean createOrderItem(OrderItem orderItem);
    OrderItem getOrderItemById(int id);
    boolean updateOrderItem(OrderItem orderItem);
    boolean removeOrderItem(OrderItem orderItem);
}
