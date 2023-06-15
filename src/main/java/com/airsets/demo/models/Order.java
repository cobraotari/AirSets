package com.airsets.demo.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Order {
    private int id;
    private String email;
    private int totalAmount;
    private LocalDateTime orderDate;

    public Order() {
    }

    public Order(int id, String email, int totalAmount, LocalDateTime orderDate) {
        this.id = id;
        this.email = email;
        this.totalAmount = totalAmount;
        this.orderDate = orderDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }
}
