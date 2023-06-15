package com.airsets.demo.DAO;

import com.airsets.demo.models.Order;

public interface OrderDao {
    boolean createOrder(Order order);
    Order getOrderById(int id);
    boolean removeOrder(Order order);
}
